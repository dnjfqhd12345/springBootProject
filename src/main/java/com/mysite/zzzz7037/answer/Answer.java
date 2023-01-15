package com.mysite.zzzz7037.answer;

import com.mysite.zzzz7037.comment.Comment;
import com.mysite.zzzz7037.question.Question;
import com.mysite.zzzz7037.user.SiteUser;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@Entity
public class Answer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(columnDefinition = "TEXT")
    private String content;

    private LocalDateTime createDate;

    @ManyToOne
    private Question question;

    @ManyToOne
    private SiteUser author;

    private LocalDateTime modifyDate;

    @OneToMany(mappedBy = "answer")
    private List<Comment> commentList;
}
