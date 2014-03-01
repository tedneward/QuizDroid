package com.example.QuizDroid;

/**
 * Created by ted on 2/21/14.
 */
public class Question {
    public Question(String questionText, String answer1, String answer2, String answer3, String answer4, int correctAnswer) {
        this.questionText = questionText;
        this.answer1 = answer1;
        this.answer2 = answer2;
        this.answer3 = answer3;
        this.answer4 = answer4;
        this.correctAnswer = correctAnswer;
    }

    public String getQuestionText() {
        return questionText;
    }

    public void setQuestionText(String questionText) {
        this.questionText = questionText;
    }

    public String getAnswer1() {
        return answer1;
    }

    public void setAnswer1(String answer1) {
        this.answer1 = answer1;
    }

    public String getAnswer2() {
        return answer2;
    }

    public void setAnswer2(String answer2) {
        this.answer2 = answer2;
    }

    public String getAnswer3() {
        return answer3;
    }

    public void setAnswer3(String answer3) {
        this.answer3 = answer3;
    }

    public String getAnswer4() {
        return answer4;
    }

    public void setAnswer4(String answer4) {
        this.answer4 = answer4;
    }

    public int getCorrectAnswer() {
        return correctAnswer;
    }

    public void setCorrectAnswer(int correctAnswer) {
        this.correctAnswer = correctAnswer;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Question question = (Question) o;

        if (correctAnswer != question.correctAnswer) return false;
        if (answer1 != null ? !answer1.equals(question.answer1) : question.answer1 != null) return false;
        if (answer2 != null ? !answer2.equals(question.answer2) : question.answer2 != null) return false;
        if (answer3 != null ? !answer3.equals(question.answer3) : question.answer3 != null) return false;
        if (answer4 != null ? !answer4.equals(question.answer4) : question.answer4 != null) return false;
        if (!questionText.equals(question.questionText)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = questionText.hashCode();
        result = 31 * result + (answer1 != null ? answer1.hashCode() : 0);
        result = 31 * result + (answer2 != null ? answer2.hashCode() : 0);
        result = 31 * result + (answer3 != null ? answer3.hashCode() : 0);
        result = 31 * result + (answer4 != null ? answer4.hashCode() : 0);
        result = 31 * result + correctAnswer;
        return result;
    }

    @Override
    public String toString() {
        return "Question{" +
                "questionText='" + questionText + '\'' +
                ", answer1='" + answer1 + '\'' +
                ", answer2='" + answer2 + '\'' +
                ", answer3='" + answer3 + '\'' +
                ", answer4='" + answer4 + '\'' +
                ", correctAnswer=" + correctAnswer +
                '}';
    }

    private String questionText;
    private String answer1;
    private String answer2;
    private String answer3;
    private String answer4;
    private int correctAnswer;
}
