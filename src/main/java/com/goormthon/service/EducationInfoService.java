package com.goormthon.service;

import com.goormthon.domain.EducationInfos;
import com.goormthon.domain.Subscriber;
import com.goormthon.dto.response.EducationInfoResponses;
import com.goormthon.repository.EducationInfoRepository;
import com.goormthon.repository.SubscriberRepository;
import com.goormthon.util.RandomNumberPicker;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EducationInfoService {

    private final EducationInfoRepository educationInfoRepository;
    private final SubscriberRepository subscriberRepository;
    private final RandomNumberPicker randomNumberPicker;

    public EducationInfoResponses findUserInfos(Long userId, long pageSize) {
        long totalCount = educationInfoRepository.count();

        //없을 경우 > 랜덤으로 반환
        if (userId == null) {
            return getRandomInfos(pageSize, totalCount);
        }

        Subscriber subscriber = subscriberRepository.getSubscriber(userId);

        //있을 경우
        List<EducationInfos> filteredInfos = educationInfoRepository.findByConditions(
                subscriber.getRegion(),
                subscriber.getEducation(),
                subscriber.getInterest(),
                subscriber.getResidency()
        );

        //페이지 사이즈만큼 채워주기
        if (filteredInfos.size() < pageSize) {
            List<Long> selectedInfos = filteredInfos.stream()
                    .map(EducationInfos::getId)
                    .toList();

            List<Long> addInfo = randomNumberPicker.pickRandomExcluding(totalCount, pageSize, selectedInfos);
            List<EducationInfos> educationInfos = educationInfoRepository.findAllByIdIn(addInfo);
            return EducationInfoResponses.from(educationInfos);
        }

        return EducationInfoResponses.from(filteredInfos);
    }

    private EducationInfoResponses getRandomInfos(long pageSize, long totalCount) {
        List<Long> selectedInfos = randomNumberPicker.pickRandom(totalCount, pageSize);
        List<EducationInfos> educationInfos = educationInfoRepository.findAllByIdIn(selectedInfos);
        return EducationInfoResponses.from(educationInfos);
    }
}
