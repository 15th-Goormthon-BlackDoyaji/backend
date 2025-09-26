package com.goormthon.domain;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum Region {

    JEJU("제주시"),
    SEOGWIPO("서귀포"),
    ;

    private final String tag;
}
