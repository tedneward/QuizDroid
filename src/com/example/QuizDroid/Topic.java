package com.example.QuizDroid;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Created by ted on 2/21/14.
 */
public class Topic {
    private String name;
    private String description;
    private List<Question> questions = new ArrayList<Question>();

    public Topic(String name, String description, Collection<Question> questions) {
        this.name = name;
        this.description = description;
        this.questions.addAll(questions);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Question> getQuestions() {
        return questions;
    }

    @Override
    public String toString() {
        return "Topic{" +
                "name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", questions=" + questions +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Topic topic = (Topic) o;

        if (description != null ? !description.equals(topic.description) : topic.description != null) return false;
        if (!name.equals(topic.name)) return false;
        if (questions != null ? !questions.equals(topic.questions) : topic.questions != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = name.hashCode();
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + (questions != null ? questions.hashCode() : 0);
        return result;
    }
}
