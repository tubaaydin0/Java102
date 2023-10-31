package com.patikadev.Model;

import com.patikadev.Helper.DBConnector;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.SQLException;
import java.util.ArrayList;

public class Patika {
    private  int id;
    private String patikaName;


    public Patika(int id, String patikaName){
        this.id=id;
        this.patikaName=patikaName;
    }
    public static ArrayList<Patika> getList(){
        ArrayList<Patika> patikaList=new ArrayList<>();
        Patika obj; //obj isimli patika sınıfından nesne oluşturuldu.

        try {
            Statement st = DBConnector.getInstance().createStatement();
            ResultSet data=st.executeQuery("SELECT * FROM patika");
            while (data.next()){
                obj=new Patika(data.getInt("id"),data.getString("patika_name"));
                patikaList.add(obj); // patikaListin içine obj listesindeki elemanlar aktarıldı.

            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return patikaList;
    }

    public static boolean add(String patikaName){
        String query="INSERT INTO patika (patika_name) VALUES (?) ";
        try {
            PreparedStatement pt=DBConnector.getInstance().prepareStatement(query);
            pt.setString(1,patikaName);
            int response=pt.executeUpdate();
            return response!=-1;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


    }
    public static boolean update(int id, String patikaName){
        String query="UPDATE patika SET patika_name=? where id=?";
        try {
            PreparedStatement pt=DBConnector.getInstance().prepareStatement(query);
            pt.setString(1,patikaName);
            pt.setInt(2,id);
            int response= pt.executeUpdate();
            return response!=-1;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
    public static Patika getFetch(int id){ // idye göre veritabanından nesne çeken metot
        String query="SELECT * FROM patika WHERE id=?";
        Patika obj = null;
        try {
            PreparedStatement pt=DBConnector.getInstance().prepareStatement(query);
            pt.setInt(1,id);
            ResultSet data=pt.executeQuery();
            if(data.next()){ // Veritabanında bu id varsa
                obj=new Patika(data.getInt("id"),data.getString("patika_name"));

            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return  obj;
    }
    public static boolean delete(int id){
        ArrayList<Course> courseList= Course.getList(); // Course listesini nesneye atıyoruz.
        for (Course obj: courseList){
            if (obj.getPatika_id()==id){ // Course sınıfına ait nesnenin id si parametre olan idye eşitse
                 Course.delete(obj.getId());
            }
        }
        String query="DELETE FROM patika WHERE id=?";
        try {
            PreparedStatement pt=DBConnector.getInstance().prepareStatement(query);
            pt.setInt(1,id);
            int response=pt.executeUpdate();
            return response!=-1;
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

    public String getPatikaName() {
        return patikaName;
    }

    public void setPatikaName(String patikaName) {
        this.patikaName = patikaName;
    }
}
