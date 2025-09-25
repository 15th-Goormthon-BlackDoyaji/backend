package com.goormthon.dto.response;

import com.goormthon.domain.EducationInfos;
import java.util.List;

public record EducationInfoResponses(
        List<EducationInfoResponse> infos
) {
    public static EducationInfoResponses from(List<EducationInfos> infos) {
        List<EducationInfoResponse> responses = infos.stream()
                .map(EducationInfoResponse::new)
                .toList();
        return new EducationInfoResponses(responses);
    }
}
