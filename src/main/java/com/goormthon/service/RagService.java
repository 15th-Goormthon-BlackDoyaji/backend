package com.goormthon.service;

import com.goormthon.client.AiClient;
import com.goormthon.domain.EducationInfos;
import com.goormthon.domain.Subscriber;
import com.goormthon.dto.request.PromptRequest;
import com.goormthon.dto.response.EducationInfoResponses;
import com.goormthon.repository.EducationInfoRepository;
import com.goormthon.util.RandomNumberPicker;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class RagService {

    private static final String PROMPT_FORMAT = """
            앞에 '''json 없이 정확히 JSON STING 배열로 응답을 보내, 잘 모르겠으면 빈 배열을 반환해
            제주시의 교육훈련 공고 중에 %s 관련 공고를 관련도 순으로 정확한 title을 모아 배열로 반환해줘
            """;
    private final AiClient aiClient;
    private final EducationInfoRepository educationInfoRepository;
    private final RandomNumberPicker randomNumberPicker;

    public List<EducationInfos> rag(Subscriber subscriber, long pageSize) {
        String tags = subscriber.getTags();
        PromptRequest request = new PromptRequest(String.format(PROMPT_FORMAT, tags));
        List<String> analyzedTitles = aiClient.getAnaylzedTitle(request);
        List<EducationInfos> filteredInfos = new ArrayList<>();

        for (String title : analyzedTitles) {
            educationInfoRepository.findByTitleIsContaining(title)
                    .ifPresent(filteredInfos::add);
        }
        log.info(filteredInfos.size() + " infos found");
        log.info(analyzedTitles.size() + " titles found");

        if (filteredInfos.size() < pageSize) {
            List<Long> selectedInfos = filteredInfos.stream()
                    .map(EducationInfos::getId)
                    .toList();
            List<Long> ids = educationInfoRepository.findAll()
                    .stream()
                    .map(edu -> edu.getId())
                    .toList();
            List<Long> addInfo = randomNumberPicker.pickRandomNumbers(ids, pageSize- filteredInfos.size(), selectedInfos);
            List<EducationInfos> educationInfos = educationInfoRepository.findAllByIdIn(addInfo);
            educationInfos.addAll(filteredInfos);
            return educationInfos;
        }

        return filteredInfos;
    }
}
