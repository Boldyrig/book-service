package com.gmail.fuskerr.bookservice.domain;

import lombok.RequiredArgsConstructor;
import lombok.Value;

@RequiredArgsConstructor
@Value
public class Author {
    long id;
    String name;
}
