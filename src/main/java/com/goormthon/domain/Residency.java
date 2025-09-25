package com.goormthon.domain;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum Residency {

    NATIVE("현지인"),
    MIGRATION("이주민"),
    ;

    private final String tag;
}
