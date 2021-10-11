package com.gmail.fuskerr.bookservice.domain;

import lombok.RequiredArgsConstructor;
import lombok.Value;

@RequiredArgsConstructor
@Value
public class Book {
    long id;
    Author author;
    Genre genre;
    String title;
}
