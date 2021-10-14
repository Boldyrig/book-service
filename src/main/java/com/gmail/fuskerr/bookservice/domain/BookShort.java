package com.gmail.fuskerr.bookservice.domain;

import lombok.RequiredArgsConstructor;
import lombok.Value;

@RequiredArgsConstructor
@Value
public class BookShort {
    long id;
    String title;
    long authorId;
    long genreId;
}
