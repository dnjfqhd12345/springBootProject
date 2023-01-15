package com.mysite.zzzz7037.comment;

import com.mysite.zzzz7037.question.Question;
import com.mysite.zzzz7037.user.SiteUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class CommentService {

    @Autowired
    private CommentRepository commentRepository;

    public Comment create(Question question, SiteUser author, String content) {
        Comment c = new Comment();
        c.setContent(content);
        c.setCreateDate(LocalDateTime.now());
        c.setQuestion(question);
        c.setAuthor(author);
        c = commentRepository.save(c);
        return c;
    }

    public Optional<Comment> getComment(Integer id) {
        return commentRepository.findById(id);
    }

    public Comment modify(Comment c, String content) {
        c.setContent(content);
        c.setModifyDate(LocalDateTime.now());
        c = commentRepository.save(c);
        return c;
    }

    public void delete(Comment c) {
        commentRepository.delete(c);
    }

}
