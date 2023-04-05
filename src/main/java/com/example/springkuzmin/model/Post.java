package com.example.springkuzmin.model;

import com.example.springkuzmin.dto.post.PostBody;
import com.vladmihalcea.hibernate.type.json.internal.JsonTypeDescriptor;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;
import org.hibernate.type.SqlTypes;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@Entity(name = "post")
@Table(name = "posts")
public class Post implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column(nullable = false, length = 50)
    private String title;

    @JoinColumn(name = "author_id", nullable = false)
    private UUID authorId;


    @JdbcTypeCode(SqlTypes.JSON)
    private List<PostBody> body;

    @CreationTimestamp
    private Date createdAt;
}