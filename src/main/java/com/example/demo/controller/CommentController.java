package com.example.demo.controller;

import com.example.demo.dto.comment.request.CommentRequestDTO;
import com.example.demo.dto.comment.response.CommentResponseDTO;
import com.example.demo.service.CommentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("api/comment")
public class CommentController {
    private final CommentService commentService;

    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @PostMapping
    public ResponseEntity<CommentResponseDTO> addComment(@RequestBody CommentRequestDTO commentRequestDto) {
        CommentResponseDTO commentResponseDTO = commentService.addComment(commentRequestDto.getPostId(), commentRequestDto.getContent(), commentRequestDto.getMemberName());
        return ResponseEntity.ok(commentResponseDTO);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CommentResponseDTO> updateComment(@PathVariable Long id, @RequestBody CommentRequestDTO commentRequestDto) {
        CommentResponseDTO commentResponseDTO = commentService.updateComment(id, commentRequestDto.getContent(), commentRequestDto.getMemberName());
        return ResponseEntity.ok(commentResponseDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteComment(@PathVariable Long id) {
        commentService.deleteComment(id);
        return ResponseEntity.noContent().build();
    }
}
