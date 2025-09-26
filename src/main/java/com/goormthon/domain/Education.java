package com.goormthon.domain;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum Education {

    UNIVERSITY("대학생"),
    GRADUATION("졸업자"),
    ;

    private final String tag;
}
