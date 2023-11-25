package com.lerning.quizapp.controller;
import java.util.List;
import com.lerning.quizapp.service.QuestionService;
import com.lerning.quizapp.model.Question;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;

import java.util.List;

import java.util.List;

// http://localhost:8080/question/allQuestions  this url is called by client
@RestController // we need to tell what this class is like Service,RestController

@RequestMapping("question")  // this will be name of main API in URL
public class questionController {
    @Autowired
    QuestionService questionService;

    // http://localhost:8080/question/allQuestions  this url is called by client which heat allQuestions Api
    @GetMapping("allQuestions")  // @Getmaaping use for get methods, its method in question class
    public ResponseEntity<List<Question>> getQuestions(){
        return questionService.getAllQuestions();
    }
    //// http://localhost:8080/question/allQuestions  this url is called by client which heat getByCategory Api
    // http://localhost:8080/question/Category/java
    // http://localhost:8080/question/Category/Python

    @GetMapping("Category/{category}") // here we used "/" which is not present in above allQuestion bracket because that was end for that API
    // and also after "/" we have variable which will be input
    // in {} bracket variable name and this below function variable after @path need to same
    public ResponseEntity<List<Question> > getByCategory(@PathVariable String category){
        return questionService.getQuestionByCategoy(category);
    }

    // This accept input from client which in json format and convert it to object
    @PostMapping("add")
    public ResponseEntity<String> addQuestion(@RequestBody Question question){
//        return "success";
        return questionService.addQuestion(question);
    }


    @GetMapping("delete/{id}")
    public String deleteQuestion(@PathVariable int id){
        return questionService.deleteQuestion(id);
    }


}
