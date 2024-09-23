package com.example.demo.service;

import com.example.demo.domain.Comment;
import com.example.demo.domain.Post;
import com.example.demo.dto.comment.response.CommentResponseDTO;
import com.example.demo.repository.CommentRepository;
import com.example.demo.repository.PostRepository;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Slf4j
@Service
public class CommentService {
    private final CommentRepository commentRepository;
    private final PostRepository postRepository;

    public CommentService(CommentRepository commentRepository, PostRepository postRepository) {
        this.commentRepository = commentRepository;
        this.postRepository = postRepository;
    }

    @Transactional
    public CommentResponseDTO addComment(Long postId, String content, String memberName) {
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new IllegalArgumentException("삭제되었거나 존재하지 않는 게시글입니다."));
        Comment comment = new Comment(memberName, content, post);
        commentRepository.save(comment);
        return CommentResponseDTO.of(comment);
    }

    @Transactional
    public void deleteComment(Long commentId) {
        Comment comment = commentRepository.findById(commentId)
                .orElseThrow(() -> new IllegalArgumentException("댓글을 찾을 수 없습니다."));
        comment.deleteComment();
        commentRepository.delete(comment); // Soft delete 적용
    }

    @Transactional
    public CommentResponseDTO updateComment(Long commentId, String newContent, String newMemberName) {
        Comment comment = commentRepository.findById(commentId)
                .orElseThrow(() -> new IllegalArgumentException("댓글을 찾을 수 없습니다."));
        comment.updateContent(newContent, newMemberName);
        commentRepository.save(comment);
        return CommentResponseDTO.of(comment);
    }
}