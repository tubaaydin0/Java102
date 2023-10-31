package com.patikadev.Model;

import com.patikadev.Helper.DBConnector;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class Quiz {
    private int id;
    private int content_id;
    private String quiz_question;
    private String quiz_answer;
    private Content content;

    public Quiz(int id, int content_id, String quiz_question, String quiz_answer) {
        this.id = id;
        this.content_id = content_id;
        this.quiz_question = quiz_question;
        this.quiz_answer = quiz_answer;
       // this.content=Content.getFetch(content_id);
    }
    public static ArrayList<Quiz> getList(){
        ArrayList<Quiz> quizList=new ArrayList<>();
        Quiz obj=null;
        String query="SELECT * FROM quiz";
        try {
            Statement st= DBConnector.getInstance().createStatement();
            ResultSet data=st.executeQuery(query);
            while (data.next()){
                obj=new Quiz(data.getInt("id"),
                        data.getInt("content_id"),
                        data.getString("quiz_question"),
                        data.getString("quiz_answer"));
                quizList.add(obj);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return quizList;
    }
    public static boolean add(String quiz_question,String quiz_answer,int content_id){
        String query="INSERT INTO quiz (quiz_question, quiz_answer, content_id) VALUES (?, ?, ?)";
        try {
            PreparedStatement pt=DBConnector.getInstance().prepareStatement(query);
            pt.setString(1,quiz_question);
            pt.setString(2,quiz_answer);
            pt.setInt(3,content_id);
            return pt.executeUpdate()!=-1;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getContent_id() {
        return content_id;
    }

    public void setContent_id(int content_id) {
        this.content_id = content_id;
    }

    public String getQuiz_question() {
        return quiz_question;
    }

    public void setQuiz_question(String quiz_question) {
        this.quiz_question = quiz_question;
    }

    public String getQuiz_answer() {
        return quiz_answer;
    }

    public void setQuiz_answer(String quiz_answer) {
        this.quiz_answer = quiz_answer;
    }

    public Content getContent() {
        return content;
    }

    public void setContent(Content content) {
        this.content = content;
    }
}
