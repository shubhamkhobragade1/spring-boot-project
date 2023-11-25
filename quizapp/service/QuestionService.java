
package com.lerning.quizapp.service;
import java.util.ArrayList;
import java.util.List;
import com.lerning.quizapp.dao.QuestionDao;
import com.lerning.quizapp.model.Question;
import com.lerning.quizapp.model.Question;
import org.aspectj.apache.bcel.generic.RET;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuestionService {
    @Autowired
    QuestionDao questionDao;



    public ResponseEntity<List<Question>> getAllQuestions(){
        try {
            return new ResponseEntity(questionDao.findAll(), HttpStatus.OK);
        }catch (Exception e){
            e.printStackTrace();
        }
        return new ResponseEntity(HttpStatus.BAD_REQUEST);

    }
//public ResponseEntity<List<Question>> getAllQuestions() {
//    try {
//        List<Question> questions = questionDao.findAll();
//        return new ResponseEntity<>(questions, HttpStatus.OK);
//    } catch (Exception e) {
//        e.printStackTrace();
//        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
//    }
//}

    public ResponseEntity<List<Question>> getQuestionByCategoy(String category){
        try {
            return new ResponseEntity(questionDao.findByCategory(category),HttpStatus.OK);
        }catch (Exception e){
            e.printStackTrace();
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    // this will take input from url/client and post/present on website


    public ResponseEntity<String> addQuestion(Question question){
        try {
            questionDao.save(question);
            return new ResponseEntity(HttpStatus.CREATED);
        }catch (Exception e){
            e.printStackTrace();

        }
        return new ResponseEntity(HttpStatus.BAD_REQUEST);
    }
    public String deleteQuestion(int id){
        questionDao.deleteById(id);
        return "Success";
    }

//    public String updateQuestion(int id){
//        questionDao.updateById(id);
//        return "Success";
//    }
}
