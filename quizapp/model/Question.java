package com.lerning.quizapp.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;
import org.springframework.boot.autoconfigure.web.WebProperties;

@Data
@Entity
// So if name in programming is //titleName which will be in database as title_name  //titleName=title_name
public class Question {
    @Id
//    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer id;
    private String questionTitle;
    private String category;
    private String option1;
    private String option2;
    private String option3;
    private String option4;
    private String difficultylevel;
    private String rightAnswer;
}
