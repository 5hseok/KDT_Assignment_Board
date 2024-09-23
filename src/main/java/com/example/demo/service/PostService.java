package com.example.demo.service;


import com.example.demo.domain.Comment;
import com.example.demo.domain.Post;
import com.example.demo.dto.comment.response.CommentResponseDTO;
import com.example.demo.dto.post.response.PostResponseDTO;
import com.example.demo.dto.post.response.PostsResponseDTO;
import com.example.demo.repository.CommentRepository;
import com.example.demo.repository.PostRepository;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Slf4j
@Service
public class PostService {
    private final PostRepository postRepository;
    private final CommentRepository commentRepository;

    public PostService(PostRepository postRepository, CommentRepository commentRepository) {
        this.postRepository = postRepository;
        this.commentRepository = commentRepository;
    }

    @Transactional
    public Post createPost(String title, String content, String memberName) {
        Post post = new Post(title, memberName, content);
        postRepository.save(post);
        return post;
    }

    @Transactional
    public void deletePost(Long postId) {
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new IllegalArgumentException("게시글을 찾을 수 없습니다."));
        postRepository.delete(post); // Soft delete 적용
    }

    @Transactional
    public Post updatePost(Long postId, String memberName, String newTitle, String newContent) {
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new IllegalArgumentException("삭제되었거나 존재하지 않는 게시글입니다."));
        post.updatePost(newTitle, memberName, newContent, LocalDateTime.now());

        return post;
    }

    @Transactional
    public Page<PostResponseDTO> getPosts(Pageable pageable) {

        Pageable sortedByDate = PageRequest.of(
                pageable.getPageNumber(),
                pageable.getPageSize(),
                Sort.by(Sort.Direction.DESC, "postCreateDate")  // 최신 글이 먼저 오도록 내림차순 정렬
        );

        Page<Post> posts =  postRepository.findAll(sortedByDate);
        return posts.map(post -> new PostResponseDTO(
                post.getId(),
                post.getTitle(),
                post.getMemberName(),
                post.getContent(),
                post.getPostCreateDate(),
                post.getPostUpdateDate(),
                post.getPostStatus()
        ));
    }

    @Transactional
    public PostsResponseDTO getPost(Long postId) {
        try {
            List<Comment> comments = commentRepository.findAllByPostId(postId);

            Post post = postRepository.findById(postId)
                    .orElseThrow(() -> new IllegalArgumentException("삭제되었거나 존재하지 않는 게시글입니다."));

            // Comment를 CommentDTO로 변환
            List<CommentResponseDTO> commentDTOs = comments.stream()
                    .map(comment -> new CommentResponseDTO(
                            comment.getId(),
                            comment.getMemberName(),
                            comment.getContent(),
                            comment.getPost().getId(),
                            comment.getCommentCreateDate(),
                            comment.getCommentUpdateDate(),
                            comment.getCommentStatus()
                    )).toList();

            return new PostsResponseDTO(
                    post.getId(),
                    post.getTitle(),
                    post.getMemberName(),
                    post.getContent(),
                    post.getPostCreateDate(),
                    post.getPostUpdateDate(),
                    post.getPostStatus(),
                    commentDTOs
            );
        } catch (Exception e) {
            throw new IllegalArgumentException("게시글을 찾을 수 없습니다.");
        }
    }
}