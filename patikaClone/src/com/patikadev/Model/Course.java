package com.patikadev.Model;

import com.patikadev.Helper.DBConnector;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class Course {
    private int id; // Course idsi
    private  int user_id; // Kullanıcıdan gelen eğitimcinin idsi
    private int patika_id;  // Bootcamp idsi
    private  String course_name;
    private  String lang;
    private  Patika patika; // patika_id ye erişmek için Patika sınıfından nesne oluşturuyoruz.
    private  User educator;// user_id ye erişmek için User sınıfından nesne oluşturuyoruz.

    public Course(int id, int user_id, int patika_id, String course_name, String lang) {
        this.id = id;
        this.user_id = user_id;
        this.patika_id = patika_id;
        this.course_name = course_name;
        this.lang=lang;
        this.patika=Patika.getFetch(patika_id); // getfetchmetodu idye göre nesne döndürüyordu. istenen patika_idye göre nesne döndürecek.
        this.educator=User.getFetch(user_id);// Userda idye göre nesne döndüren bir tane daha  getfetch metodu oluşturacağız. Bu sayede istenen idye göre kullanıcıyı döndürecek.
    }
    public static ArrayList<Course> getList(){// Veritabanındaki course tablosu courseList isimli arrayliste aktarıldı.
        ArrayList<Course> courseList=new ArrayList<>(); // Boş bir courseList oluşturuldu.
        Course obj=null;
        try {
            Statement st= DBConnector.getInstance().createStatement();
            ResultSet data=st.executeQuery("SELECT * FROM course");
            // Her seferinde yeni bir obj nesnesi oluşturulup satırları bu nesneye atıyoruz.
            // obj nesnesi de courseListe atılıyor. Daha sonra sonraki satıra geçilip veritabanındaki tabloda satır bitene kadar bu işlem tekrarlanmış oluyor.
            while (data.next()){

                int id=data.getInt("id");
                int user_id=data.getInt("user_id");
                int patika_id=data.getInt("patika_id");
                String course_name= data.getString("course_name");
                String lang=data.getString("lang");
                obj=new Course(id,user_id,patika_id,course_name,lang);
                courseList.add(obj);

             /* obj=new Course(data.getInt("id"),
                        data.getInt("user_id"),
                        data.getInt("patika_id"),
                        data.getString("course_name"),
                        data.getString("lang"));
                courseList.add(obj);*/
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return courseList;

    }
    //Dışardan gelen userIdye göre kursları listeleten metot.User tablosunda bir educator silinirse course tablosunda da silinmesi için oluşturuldu.
    public static ArrayList<Course> getListByUser(int userID) {
        ArrayList<Course> courseList = new ArrayList<>(); // Boş bir courseList oluşturuldu.
        Course obj = null;
        try {
            Statement st = DBConnector.getInstance().createStatement();
            ResultSet data = st.executeQuery("SELECT * FROM course WHERE user_id=" + userID);

            while (data.next()) {

                int id = data.getInt("id");
                int user_id = data.getInt("user_id");
                int patika_id = data.getInt("patika_id");
                String course_name = data.getString("course_name");
                String lang = data.getString("lang");
                obj = new Course(id, user_id, patika_id, course_name, lang);
                courseList.add(obj);
            }
        } catch(SQLException e){
            throw new RuntimeException(e);
        }
            return courseList;
    }


    /*public static Course getFetch(int courseID){
        Course obj=null;
        String query="SELECT * FROM course WHERE (id) VALUES (?)";
        try {
            PreparedStatement pt=DBConnector.getInstance().prepareStatement(query);
            pt.setInt(1,courseID);
            ResultSet data=pt.executeQuery();
            if (data.next()){
                obj=new Course(data.getInt("id"),
                        data.getInt("user_id"),
                        data.getInt("patika_id"),
                        data.getString("course_name"),
                        data.getString("lang"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return obj;
    }*/
    public static boolean add(int user_id,int patika_id,String course_name,String lang){
        String query="INSERT INTO course (user_id, patika_id, course_name, lang) VALUES (?, ?, ?, ?)";
        try {
            PreparedStatement pt=DBConnector.getInstance().prepareStatement(query);
            pt.setInt(1,user_id);
            pt.setInt(2,patika_id);
            pt.setString(3,course_name);
            pt.setString(4,lang);
            return pt.executeUpdate()!=-1;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
    public static boolean delete(int id){
        String query="DELETE FROM course WHERE id=?";
        try {
            PreparedStatement pt= DBConnector.getInstance().prepareStatement(query);
            pt.setInt(1,id);


            int response=pt.executeUpdate();
            return response!=-1;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        //return true;
    }


    // Content için idye göre geri döndüren fonk.
    public static Course getFetch(int courseId) {
        String query="SELECT * FROM course WHERE id=?";
        Course obj=null;
        try {
            PreparedStatement pt=DBConnector.getInstance().prepareStatement(query);
            pt.setInt(1,courseId);
            ResultSet data=pt.executeQuery();
            if (data.next()){
                int user_id=data.getInt("user_id");
                int patika_id=(data.getInt("patika_id"));
                String course_name=(data.getString("course_name"));
                String lang=data.getString("lang");
                obj=new Course(courseId,user_id,patika_id,course_name,lang);
                return obj;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;

    }
    public static int searchCourseName(String name){
        int id = 0;
        try {
            Statement statement = DBConnector.getInstance().createStatement();
            String query = "SELECT * FROM course WHERE course_name = '" + name + "'";
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                id = resultSet.getInt("id");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return id;

}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public int getPatika_id() {
        return patika_id;
    }

    public void setPatika_id(int patika_id) {
        this.patika_id = patika_id;
    }

    public String getCourse_name() {
        return course_name;
    }

    public void setCourse_name(String course_name) {
        this.course_name = course_name;
    }

    public String getLang() {
        return lang;
    }

    public void setLang(String lang) {
        this.lang = lang;
    }

    public Patika getPatika() {
        return patika;
    }

    public void setPatika(Patika patika) {
        this.patika = patika;
    }

    public User getEducator() {
        return educator;
    }

    public void setEducator(User educator) {
        this.educator = educator;
    }
}
