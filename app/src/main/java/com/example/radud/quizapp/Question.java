package com.example.radud.quizapp;

import java.util.List;

/**
 * Created by radud on 20/05/2017.
 */

public class Question {

    private String body;
    private List<String> responseList;
    private int rightResponse;

    public Question() {
    }

    public Question(String body, List<String> responseList, int rightResponse) {
        this.body = body;
        this.responseList = responseList;
        this.rightResponse = rightResponse;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public List<String> getResponseList() {
        return responseList;
    }

    public void setResponseList(List<String> responseList) {
        this.responseList = responseList;
    }

    public int getRightResponse() {
        return rightResponse;
    }

    public void setRightResponse(int rightResponse) {
        this.rightResponse = rightResponse;
    }
}
