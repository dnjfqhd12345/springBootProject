package com.mysite.zzzz7037.question;

import com.mysite.zzzz7037.answer.AnswerForm;
import com.mysite.zzzz7037.user.SiteUser;
import com.mysite.zzzz7037.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.security.Principal;
import java.util.List;

@RequestMapping("/question")
@RequiredArgsConstructor
@Controller
public class QuestionController {

    private final QuestionService questionService;
    private final UserService userService;

    @GetMapping("/list")
    public String list(Model model, @RequestParam(value="page", defaultValue = "0") int page) {
        Page<Question> paging = questionService.getList(page, "notice");
        model.addAttribute("paging", paging);
        String category = "notice";
        List<Question> questionList = questionService.getListCategory(category);
        model.addAttribute("questionList", questionList);
        return "list";
    }

    @GetMapping("/list/{category}")
    public String listCategory(Model model, @PathVariable("category") String category ,
                               @RequestParam(value="page", defaultValue = "0") int page,
                               @RequestParam(value = "kw", defaultValue = "") String kw) {
        Page<Question> paging = questionService.getListByCategory(page,kw,category);
        model.addAttribute("paging", paging);
        List<Question> questionList = questionService.getListCategory(category);
        String topic;
        if(category.equals("notice")){
            topic = "공지사항";
        } else if(category.equals("sw")) {
            topic = "S/W";
        }else if(category.equals("hw")) {
            topic = "H/W";
        }else if(category.equals("develope")) {
            topic = "개발실";
        }else if(category.equals("free")) {
            topic = "자유수다";
        }else if(category.equals("bug")){
            topic = "버그 및 건의";
        }else {
            topic = "유효하지 않은 접근입니다.";
            return "error";
        }
        model.addAttribute("questionList", questionList);
        model.addAttribute("topic",topic);
        model.addAttribute("category",category);
        model.addAttribute("kw", kw);
        if(category.equals("notice"))
            return "question_notice";
        return "question_list";
    }

    @GetMapping(value = "/detail/{id}")
    public String detail(Model model, @PathVariable("id") Integer id, AnswerForm answerForm){
        Question question = questionService.getQuestion(id);
        model.addAttribute("question", question);
        return "question_detail";
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/create/notice")
    public String questionCreateNotice(QuestionForm questionForm, Model model) {
        model.addAttribute("topic","공지사항");
        return "question_form";
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/create/bug")
    public String questionCreateBug(QuestionForm questionForm, Model model) {
        model.addAttribute("topic","버그 및 건의");
        return "question_form";
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/create/sw")
    public String questionCreateSW(QuestionForm questionForm, Model model) {
        model.addAttribute("topic","S/W 토론");
        return "question_form";
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/create/hw")
    public String questionCreateHW(QuestionForm questionForm, Model model) {
        model.addAttribute("topic", "H/W 토론");
        return "question_form";
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/create/develope")
    public String questionCreateDev(QuestionForm questionForm, Model model) {
        model.addAttribute("topic", "개발실");
        return "question_form";
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/create/free")
    public String questionCreateFree(QuestionForm questionForm, Model model) {
        model.addAttribute("topic", "자유게시판");
        return "question_form";
    }


    @PreAuthorize("isAuthenticated()")
    @PostMapping("/create/notice")
    public String questionCreateNotice(@Valid QuestionForm questionForm, BindingResult bindingResult, Principal principal) {
        if(bindingResult.hasErrors()) {
            return "question_form";
        }
        String category = "notice";
        SiteUser siteUser = userService.getUser(principal.getName());
        questionService.create(questionForm.getSubject(), questionForm.getContent(), siteUser, category);
        return String.format("redirect:/question/list/%s", category);
    }

    @PreAuthorize("isAuthenticated()")
    @PostMapping("/create/bug")
    public String questionCreateBug(@Valid QuestionForm questionForm, BindingResult bindingResult, Principal principal) {
        if(bindingResult.hasErrors()) {
            return "question_form";
        }
        String category = "bug";
        SiteUser siteUser = userService.getUser(principal.getName());
        questionService.create(questionForm.getSubject(), questionForm.getContent(), siteUser, category);
        return String.format("redirect:/question/list/%s", category);
    }

    @PreAuthorize("isAuthenticated()")
    @PostMapping("/create/sw")
    public String questionCreateNoticeSW(@Valid QuestionForm questionForm, BindingResult bindingResult, Principal principal) {
        if(bindingResult.hasErrors()) {
            return "question_form";
        }
        String category = "sw";
        SiteUser siteUser = userService.getUser(principal.getName());
        questionService.create(questionForm.getSubject(), questionForm.getContent(), siteUser, category);
        return String.format("redirect:/question/list/%s", category);
    }

    @PreAuthorize("isAuthenticated()")
    @PostMapping("/create/hw")
    public String questionCreateNoticeHW(@Valid QuestionForm questionForm, BindingResult bindingResult, Principal principal) {
        if(bindingResult.hasErrors()) {
            return "question_form";
        }
        String category = "hw";
        SiteUser siteUser = userService.getUser(principal.getName());
        questionService.create(questionForm.getSubject(), questionForm.getContent(), siteUser, category);
        return String.format("redirect:/question/list/%s", category);
    }

    @PreAuthorize("isAuthenticated()")
    @PostMapping("/create/free")
    public String questionCreateNoticeFree(@Valid QuestionForm questionForm, BindingResult bindingResult, Principal principal) {
        if(bindingResult.hasErrors()) {
            return "question_form";
        }
        String category = "free";
        SiteUser siteUser = userService.getUser(principal.getName());
        questionService.create(questionForm.getSubject(), questionForm.getContent(), siteUser, category);
        return String.format("redirect:/question/list/%s", category);
    }

    @PreAuthorize("isAuthenticated()")
    @PostMapping("/create/develope")
    public String questionCreateDev(@Valid QuestionForm questionForm, BindingResult bindingResult, Principal principal) {
        if(bindingResult.hasErrors()) {
            return "question_form";
        }
        String category = "develope";
        SiteUser siteUser = userService.getUser(principal.getName());
        questionService.create(questionForm.getSubject(), questionForm.getContent(), siteUser, category);
        return String.format("redirect:/question/list/%s", category);
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/modify/{id}")
    public String questionModify(QuestionForm questionForm, @PathVariable("id") Integer id, Principal principal) {
        Question question = this.questionService.getQuestion(id);
        if(!question.getAuthor().getUsername().equals(principal.getName())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "수정권한이 없습니다.");
        }
        questionForm.setSubject(question.getSubject());
        questionForm.setContent(question.getContent());
        return "question_form";
    }

    @PreAuthorize("isAuthenticated()")
    @PostMapping("/modify/{id}")
    public String questionModify(@Valid QuestionForm questionForm, BindingResult bindingResult,
                                 Principal principal, @PathVariable("id") Integer id) {
        if (bindingResult.hasErrors()) {
            return "question_form";
        }
        Question question = this.questionService.getQuestion(id);
        if (!question.getAuthor().getUsername().equals(principal.getName())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "수정권한이 없습니다.");
        }
        this.questionService.modify(question, questionForm.getSubject(), questionForm.getContent());
        return String.format("redirect:/question/detail/%s", id);
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/delete/{id}")
    public String questionDelete(Principal principal, @PathVariable("id") Integer id) {
        Question question = this.questionService.getQuestion(id);
        if (!question.getAuthor().getUsername().equals(principal.getName())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "삭제권한이 없습니다.");
        }
        this.questionService.delete(question);
        return "redirect:/";
    }

    @GetMapping("/test")
    public String test(){
        return "test";
    }

}
