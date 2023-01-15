package com.mysite.zzzz7037.comment;

import com.mysite.zzzz7037.answer.Answer;
import com.mysite.zzzz7037.question.Question;
import com.mysite.zzzz7037.user.SiteUser;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    private SiteUser author;

    @Column(columnDefinition = "TEXT")
    private String content;

    private LocalDateTime createDate;

    private LocalDateTime modifyDate;

    @ManyToOne
    private Question question;

    @ManyToOne
    private Answer answer;

    public Integer getQuestionId() {
        Integer result = null;
        if(this.question != null) {
            result = this.question.getId();
        } else if(this.answer != null) {
            result = this.answer.getQuestion().getId();
        }
        return result;
    }

}
