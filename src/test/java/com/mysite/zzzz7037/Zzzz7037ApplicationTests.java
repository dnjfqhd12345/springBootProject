package com.mysite.zzzz7037;

import com.mysite.zzzz7037.answer.Answer;
import com.mysite.zzzz7037.answer.AnswerRepository;
import com.mysite.zzzz7037.question.Question;
import com.mysite.zzzz7037.question.QuestionRepository;
import com.mysite.zzzz7037.user.SiteUser;
import com.mysite.zzzz7037.user.UserRepository;
import com.mysite.zzzz7037.user.UserRole;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
class Zzzz7037ApplicationTests {

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private QuestionRepository questionRepository;

	@Autowired
	private AnswerRepository answerRepository;

	@Test
	void contextLoads() {
	}

	@Test
	void testJpa() {
		Optional<Question> oq = questionRepository.findById(2);
		assertTrue(oq.isPresent());
		Question q = oq.get();

		Answer a = new Answer();
		a.setContent("반가워요!");
		a.setQuestion(q);
		a.setCreateDate(LocalDateTime.now());
		answerRepository.save(a);
	}

	@Test
	void adminCreateTest() {
		SiteUser admin = new SiteUser();
		admin.setUsername("전산쟁이");
		String password = "!@dlrl4549";
		admin.setPassword(passwordEncoder.encode(password));
		admin.setEmail("zzzz7037@naver.com");
		admin.setRole(UserRole.ADMIN);
		this.userRepository.save(admin);


	}


}
