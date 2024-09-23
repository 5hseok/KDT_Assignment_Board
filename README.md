# KDT_Assignment_Board

## DB 스키마 
### Post Entity
<img width="462" alt="image" src="https://github.com/user-attachments/assets/a6ed4a9b-96f0-4991-8c75-0e91d1cf7b7b">

### Comment Entity
<img width="487" alt="image" src="https://github.com/user-attachments/assets/25ddb24e-e94f-4cd6-82d4-726b7a0c83f5">

```sql
Hibernate: 
    create table comment (
        comment_create_date datetime(6),
        comment_update_date datetime(6),
        id bigint not null auto_increment,
        post_id bigint,
        content varchar(255),
        member_name varchar(255),
        comment_status enum ('ACTIVE','DELETE'),
        primary key (id)
    ) engine=InnoDB
Hibernate: 
    create table post (
        id bigint not null auto_increment,
        post_create_date datetime(6),
        post_update_date datetime(6),
        member_name varchar(255),
        title varchar(255),
        content longtext,
        post_status enum ('ACTIVE','DELETE'),
        primary key (id)
    ) engine=InnoDB
Hibernate: 
    alter table comment 
       add constraint FKs1slvnkuemjsq2kj4h3vhx7i1 
       foreign key (post_id) 
       references post (id)
``` 
## 게시글 (Post)
- 등록
- 수정
- 삭제
- 조회(최신순)
## 댓글 (Comment)
- 등록
- 수정
- 삭제
