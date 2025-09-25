package com.goormthon.domain;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum Interest {
    EMPLOYMENT("일자리/취업"),
    CERTIFICATION("자격증"),
    SUBSIDY("보조금"),
    ;

    private final String tag;
}
