package com.lerning.quizapp.service;

import com.lerning.quizapp.dao.QuestionDao;
import com.lerning.quizapp.dao.QuizDao;

import com.lerning.quizapp.model.Question;
import com.lerning.quizapp.model.QuestionWrapper;
import com.lerning.quizapp.model.Quiz;
import com.lerning.quizapp.model.Responses;
import org.hibernate.QueryTimeoutException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class QuizService {

    @Autowired
    QuizDao quizDao;
    @Autowired
    QuestionDao questionDao;

    public ResponseEntity<String> createQuiz(String category, int numQ, String title) {

        List<Question> quesList=questionDao.findRandomQuestionsByCategory(category,numQ);
        Quiz quiz=new Quiz();
        quiz.setTitle(title);
        quiz.setQuestions(quesList);
        quizDao.save(quiz);
        return new ResponseEntity<>("success", HttpStatus.OK);

    }

    public ResponseEntity<List<QuestionWrapper>> getQuizQuestions(Integer id) {

        Optional<Quiz> quiz=quizDao.findById(id);
        List<Question> questionFromdBb=quiz.get().getQuestions();
        List<QuestionWrapper> questionsForUser=new ArrayList<>();

        for (Question q:questionFromdBb){
            QuestionWrapper qw=new QuestionWrapper(q.getId(),q.getQuestionTitle(),q.getCategory(),q.getOption1(),q.getOption2(),q.getOption3(),q.getOption4());
            questionsForUser.add(qw);
        }

        return new ResponseEntity<>(questionsForUser,HttpStatus.OK);

    }

    public ResponseEntity<Integer> calculateResult(Integer id, List<Responses> responses) {
        Quiz quiz=quizDao.findById(id).get();
        List<Question> queList=quiz.getQuestions();
        int right=0;
        int i=0;

        for (Responses res:responses){
            if (res.getResponse().equals(queList.get(i).getRightAnswer())){
                right++;
            }
            i++;
        }
        return new ResponseEntity<>(right,HttpStatus.OK);

    }
}
