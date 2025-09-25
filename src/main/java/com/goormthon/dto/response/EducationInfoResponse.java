package com.goormthon.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.goormthon.domain.EducationInfos;
import java.time.LocalDate;

public record EducationInfoResponse(
        @JsonProperty("due_date")
        LocalDate dueDate,
        String title,
        String summary,
        String url
) {

    public EducationInfoResponse(EducationInfos educationInfos) {
        this(
                educationInfos.getDeadline(),
                educationInfos.getTitle(),
                educationInfos.getSummary(),
                educationInfos.getUrl()
        );
    }
}
