package com.pawel.wojtanka.bootcamp.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum CourseMode {

    NULL(""),
    DAILY("dzienny"),
    EVENING("wieczorowy"),
    WEEKEND("zaoczny");

    private final String description;

}
