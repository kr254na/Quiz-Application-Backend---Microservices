package com.krishna.quiz_service.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Data
@Entity
public class Question
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String question;
    private String domain;
    private String level;
    private String option1;
    private String option2;
    private String option3;
    private String option4;
    private String answer;
}
