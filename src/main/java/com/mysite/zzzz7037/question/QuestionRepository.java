package com.mysite.zzzz7037.question;

import com.mysite.zzzz7037.question.Question;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;
import java.util.Optional;

public interface QuestionRepository extends JpaRepository<Question, Integer>, JpaSpecificationExecutor<Question> {
    List<Question> findBycategory(String category);
    Page<Question> findAll(Pageable pageable);
    Page<Question> findAllByCategory(Pageable pageable, String category);
    Page<Question> findAll(Specification<Question> spec, Pageable pageable);
}
