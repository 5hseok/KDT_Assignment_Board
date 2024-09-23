package com.example.demo.domain;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.*;

import java.time.LocalDateTime;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@SQLRestriction("comment_status = 'ACTIVE'")
@SQLDelete(sql = "UPDATE comment SET comment_status = 'DELETE' WHERE id = ?")
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String memberName;

    private String content;

    private LocalDateTime commentCreateDate;

    private LocalDateTime commentUpdateDate;

    @Enumerated(EnumType.STRING)
    private CommentStatus commentStatus;

    public Comment(String memberName, String content, Post post) {
        this.memberName = memberName;
        this.content = content;
        this.commentCreateDate = LocalDateTime.now();
        this.commentUpdateDate = commentCreateDate;
        this.commentStatus = CommentStatus.ACTIVE;
        this.post = post;
    }

    // 댓글 수정 메서드 추가
    public void updateContent(String newContent, String newMemberName) {
        this.content = newContent;
        this.memberName = newMemberName;
        this.commentUpdateDate = LocalDateTime.now();
    }

    // 댓글 삭제 메서드 추가
    public void deleteComment() {
        this.commentStatus = CommentStatus.DELETE;
    }

    @ManyToOne
    @JoinColumn(name="post_id")
    private Post post;

}
