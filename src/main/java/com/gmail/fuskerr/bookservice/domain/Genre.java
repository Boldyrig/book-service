package com.gmail.fuskerr.bookservice.domain;

import lombok.RequiredArgsConstructor;
import lombok.Value;

@RequiredArgsConstructor
@Value
public class Genre {
    long id;
    String name;
}
