package com.example.demo.dto.post.response;

import com.example.demo.domain.PostStatus;
import com.example.demo.dto.comment.response.CommentResponseDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@AllArgsConstructor
public class PostsResponseDTO {

    private final Long id;
    private final String title;
    private final String memberName;
    private final String content;
    private final LocalDateTime postCreateDate;
    private final LocalDateTime postUpdateDate;
    private final PostStatus postStatus;
    private final List<CommentResponseDTO> comments;
}
