package com.example.demo.dto.comment.response;

import com.example.demo.domain.Comment;
import com.example.demo.domain.CommentStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class CommentResponseDTO {
    private final Long id;
    private final String memberName;
    private final String content;
    private final Long postId;
    private final LocalDateTime commentCreateDate;
    private final LocalDateTime commentUpdateDate;
    private final CommentStatus commentStatus;

    // 정적 팩토리 메소드 of
    public static CommentResponseDTO of(Comment comment) {
        return new CommentResponseDTO(
                comment.getId(),
                comment.getMemberName(),
                comment.getContent(),
                comment.getPost().getId(),
                comment.getCommentCreateDate(),
                comment.getCommentUpdateDate(),
                comment.getCommentStatus()
        );
    }
}
