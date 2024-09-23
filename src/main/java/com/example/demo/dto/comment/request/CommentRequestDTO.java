package com.example.demo.dto.comment.request;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class CommentRequestDTO {

    private final Long id;
    private final String content;
    private final String memberName;
    private final Long postId;
}
