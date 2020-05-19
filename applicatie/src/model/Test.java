package model;

import java.util.*;

public class Test{
    private List<Question> questions;
    private List<Question> askedQuestions;
    private List<String> feedback;
    private Question currentQuestion;
    private HashMap<String, Integer> score;
    private boolean testTaken = false;

    public Test(List<Question> questions) {
        setQuestions(questions);
        this.askedQuestions = new ArrayList<>();
        this.feedback = new ArrayList<>();
        score = new HashMap<>();
    }

    public List<Question> getQuestions() {
        return questions;
    }

    public void setQuestions(List<Question> questions) {
        this.questions = questions;
    }

    public Question findNextQuestion() {
        Collections.shuffle(questions);
        for (Question question : questions) {
            if (!askedQuestions.contains(question)) {
                askedQuestions.add(question);
                currentQuestion = question;
                return question;
            }
        }
        return null;

    }

    public boolean checkAnswer(String answer) {
        String category = currentQuestion.getCategory();
        if (!score.containsKey(category)) {
            score.put(category, new Integer(0));
        }
        if (currentQuestion.getCorrectanswer().equalsIgnoreCase(answer)) {
            score.put(category, score.get(category) + 1);
            return true;
        } else {
            feedback.add(currentQuestion.getFeedback());
        }
        return false;
    }

    public String printResults() {
        String text = "Your score: " + berekenScore() + "/" + askedQuestions.size() + "\n";
        for (Map.Entry<String, Integer> entry : score.entrySet()) {
            String category = entry.getKey();
            int points = entry.getValue();
            text += category + ": " + points + "/" + getAskedQuestionsOfCategory(category) + "\n";
        }
        if (berekenScore() == askedQuestions.size()) {
            text = "Schitterend! Alles perfect!";
        }
        testTaken = true;
        return text;
    }

    public int berekenScore() {
        int points = 0;
        for (Map.Entry<String, Integer> entry : score.entrySet()) {
            points += entry.getValue();
        }
        return points;
    }

    public List<String> getFeedback() {
        return feedback;
    }

    public Question getCurrentQuestion() {
        return currentQuestion;
    }

    public int getAskedQuestionsOfCategory(String category) {
        int count = 0;
        for (Question question : askedQuestions) {
            if (question != null && question.getCategory().equalsIgnoreCase(category)) {
                count++;
            }
        }
        return count;
    }
}
