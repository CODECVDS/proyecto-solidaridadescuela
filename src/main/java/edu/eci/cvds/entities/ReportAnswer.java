package edu.eci.cvds.entities;

public class ReportAnswer {
    private String answer;
    private String type;
    private  String name;

    public ReportAnswer(String answer, String type, String name) {
        this.answer = answer;
        this.type = type;
        this.name = name;
    }
    public ReportAnswer(){}

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
