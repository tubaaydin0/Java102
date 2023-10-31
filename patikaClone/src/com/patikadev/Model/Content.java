package com.patikadev.Model;

import com.patikadev.Helper.DBConnector;

import java.security.PrivateKey;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class Content {
    private  int id; // content id

    private int course_id;// içeriğin ait olduğu ders idsi
    private String content_title;
    private String content_description;
    private String content_link;
    private int quiz_id;
    private Course course;

    public Content(int id, int course_id, String content_title, String content_description, String content_link) {
        this.id = id;
        this.course_id = course_id;
        this.content_title = content_title;
        this.content_description = content_description;
        this.course=Course.getFetch(course_id);
        this.content_link=content_link;

    }


    //VERİTABANINDAN CONTENT VERİLERİNE ERİŞİM
    public static ArrayList<Content> getList(){
        ArrayList<Content> contentList=new ArrayList<>();
        Content obj=null;
        try {
            Statement st= DBConnector.getInstance().createStatement();
            ResultSet data=st.executeQuery("SELECT * FROM content");
            while (data.next()){
                obj=new Content(data.getInt("id"),
                        data.getInt("course_id"),
                        data.getString("content_title"),
                        data.getString("content_description"),
                        data.getString("content_link"));
                contentList.add(obj);

            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return contentList;
    }
    //Ekleme İşlemleri
    public static boolean add( String content_title, String content_description, String link, int course_id){

        String query="INSERT INTO content (content_title, content_description, content_link, course_id) VALUES(?, ?, ?, ?)" ;

        try {
           PreparedStatement pt = DBConnector.getInstance().prepareStatement(query);
           pt.setString(1,content_title);
           pt.setString(2,content_description);
           pt.setString(3,link);
           pt.setInt(4,course_id);
           return pt.executeUpdate()!=-1;
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }


    }
//Silme İşlemi
  public static boolean delete(int id){
        String query="DELETE FROM content WHERE id= ?";
      try {
          PreparedStatement pt=DBConnector.getInstance().prepareStatement(query);
          pt.setInt(1,id);
          return pt.executeUpdate()!=-1;
      } catch (SQLException e) {
          throw new RuntimeException(e);
      }
  }
  // Güncelleme işlemi

    public static boolean update(int content_id, String content_title, String content_description, String content_link,int course_id){
        String query= "UPDATE content SET content_title=?, content_description=?, content_link=?, course_id=? WHERE id=?";
        try {
            PreparedStatement pt= DBConnector.getInstance().prepareStatement(query);
            pt.setString(1,content_title);
            pt.setString(2,content_description);
            pt.setString(3,content_link);
            pt.setInt(4,course_id);
            pt.setInt(5,content_id);
            return pt.executeUpdate() !=-1;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

//Filtreleme İşlemleri
  public static String filterQuery(String content_title,int course_id){
        String query="SELECT * FROM content WHERE content_title LIKE '%{{content_title}}%' and course_id="+course_id;
        query=query.replace("{{content_title}}",content_title);
        return query;
  }
  public static ArrayList<Content> filterContentList(String query){
        ArrayList<Content> contentList=new ArrayList<>();
        Content obj;
      try {
          PreparedStatement pt=DBConnector.getInstance().prepareStatement(query);
          ResultSet data=pt.executeQuery();
          while (data.next()){
              int id=data.getInt("id");
              int course_id=data.getInt("course_id");
              String content_title= data.getString("content_title");
              String content_description= data.getString("content_description");
              String content_link=data.getString("content_link");
              obj=new Content(id,course_id,content_title,content_description,content_link);
              contentList.add(obj);
          }
      } catch (SQLException e) {
          throw new RuntimeException(e);
      }
      return  contentList;


  }



    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCourse_id() {
        return course_id;
    }

    public void setCourse_id(int course_id) {
        this.course_id = course_id;
    }

    public String getContent_title() {
        return content_title;
    }

    public void setContent_title(String content_title) {
        this.content_title = content_title;
    }

    public String getContent_description() {
        return content_description;
    }

    public void setContent_description(String content_description) {
        this.content_description = content_description;
    }

    public String getContent_link() {
        return content_link;
    }

    public void setContent_link(String content_link) {
        this.content_link = content_link;
    }
    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }
}
