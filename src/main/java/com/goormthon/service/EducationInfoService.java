package com.goormthon.service;

import com.goormthon.domain.Education;
import com.goormthon.domain.EducationInfos;
import com.goormthon.domain.Interest;
import com.goormthon.domain.Region;
import com.goormthon.domain.Residency;
import com.goormthon.domain.Subscriber;
import com.goormthon.dto.response.EducationInfoResponses;
import com.goormthon.event.MailEvent;
import com.goormthon.repository.EducationInfoRepository;
import com.goormthon.repository.SubscriberRepository;
import com.goormthon.util.RandomNumberPicker;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class EducationInfoService {

    private final EducationInfoRepository educationInfoRepository;
    private final SubscriberRepository subscriberRepository;
    private final RandomNumberPicker randomNumberPicker;
    private final ApplicationEventPublisher eventPublisher;
    private final RagService ragService;

    public EducationInfoResponses findUserInfos(Long userId, long pageSize) {
        long totalCount = educationInfoRepository.count();

        //없을 경우 > 랜덤으로 반환
        if (userId == null) {
            return getRandomInfos(pageSize, totalCount);
        }

        Subscriber subscriber = subscriberRepository.getSubscriber(userId);
        List<EducationInfos> filteredInfos = new ArrayList<>();

        //있을 경우 : Rag > 실패시 필터링 결과 반환
        try {
            filteredInfos= ragService.rag(subscriber, pageSize);
            return EducationInfoResponses.from(filteredInfos);
        } catch (Exception e) {
            log.error("rag exception", e);
            filteredInfos = educationInfoRepository.findByConditions(
                    subscriber.getRegion(),
                    subscriber.getEducation(),
                    subscriber.getInterest(),
                    subscriber.getResidency(),
                    pageSize
            );

            //페이지 사이즈만큼 채워주기
            if (filteredInfos.size() < pageSize) {
                List<Long> selectedInfos = filteredInfos.stream()
                        .map(EducationInfos::getId)
                        .toList();

                List<Long> addInfo = randomNumberPicker.pickRandomExcluding(totalCount, pageSize, selectedInfos);
                List<EducationInfos> educationInfos = educationInfoRepository.findAllByIdIn(addInfo);
                filteredInfos.addAll(educationInfos);
                return EducationInfoResponses.from(filteredInfos);
            }
            return EducationInfoResponses.from(filteredInfos);
        } finally {
            log.info("send mail event");
            publishMailEvent(subscriber, filteredInfos);
        }
    }

    public EducationInfoResponses search(
            String search,
            Education education,
            Region region,
            Residency residency,
            Interest interest
    ) {

        //있을 경우
        List<EducationInfos> filteredInfos = educationInfoRepository.findByConditions(
                region,
                education,
                interest,
                residency,
                10000
        );

        if (search != null) {
            return filteredInfos.stream()
                    .filter(info -> info.getTitle().contains(search))
                    .collect(Collectors.collectingAndThen(Collectors.toList(), EducationInfoResponses::from));
        }
        return EducationInfoResponses.from(filteredInfos);
    }

    private void publishMailEvent(Subscriber subscriber, List<EducationInfos> educations) {
        log.info("subscriber : {}, educations: {}", subscriber, educations);
        MailEvent mailEvent = new MailEvent(this, subscriber, educations);
        eventPublisher.publishEvent(mailEvent);
    }

    private EducationInfoResponses getRandomInfos(long pageSize, long totalCount) {
        log.info("random Choice");
        List<Long> selectedInfos = randomNumberPicker.pickRandom(totalCount, pageSize);
        List<EducationInfos> educationInfos = educationInfoRepository.findAllByIdIn(selectedInfos);
        return EducationInfoResponses.from(educationInfos);
    }
}
