package com.example.demo.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.*;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.SQLRestriction;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@SQLRestriction("post_status = 'ACTIVE'")
@SQLDelete(sql = "UPDATE post SET post_status = 'DELETE' WHERE id = ?")
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    private String memberName;

    @Lob
    private String content;

    private LocalDateTime postCreateDate;

    private LocalDateTime postUpdateDate;

    @Enumerated(EnumType.STRING)
    private PostStatus postStatus;

    public Post(String title, String memberName, String content) {
        this.title = title;
        this.memberName = memberName;
        this.content = content;
        this.postCreateDate = LocalDateTime.now();
        this.postUpdateDate = postCreateDate;
        this.postStatus = PostStatus.ACTIVE;
        this.comments = new ArrayList<>();
    }

    public void updatePost(String title, String memberName, String content, LocalDateTime postUpdateDate) {
        this.title = title;
        this.memberName = memberName;
        this.content = content;
        this.postUpdateDate = postUpdateDate;
    }

    @OneToMany(mappedBy = "post", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<Comment> comments = new ArrayList<>();

}
