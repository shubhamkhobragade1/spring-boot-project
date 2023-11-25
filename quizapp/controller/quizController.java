package com.lerning.quizapp.controller;

import com.lerning.quizapp.model.QuestionWrapper;
import com.lerning.quizapp.model.Responses;
import com.lerning.quizapp.service.QuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("quiz")
public class quizController {

    @Autowired
    QuizService quizService;

//    # this will create a quiz
    @PostMapping("create")
    public ResponseEntity<String> createQuiz(@RequestParam String category,@RequestParam int numQ,@RequestParam String title){
        return quizService.createQuiz(category,numQ,title);
    }

    @GetMapping("get/{id}")

    // this will display question and options only
    public ResponseEntity<List<QuestionWrapper>> getQuizQuestions(@PathVariable Integer id){

        return quizService.getQuizQuestions(id);
    }

    // this class will take how many questions r correct from submitted input
    @PostMapping("submit/{id}")
    public ResponseEntity<Integer> submitQuiz(@PathVariable Integer id,@RequestBody List<Responses> response){

            return quizService.calculateResult(id,response);
    }
}
