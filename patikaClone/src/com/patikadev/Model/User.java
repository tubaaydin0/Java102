package com.patikadev.Model;

import com.patikadev.Helper.DBConnector;
import com.patikadev.Helper.Helper;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class User {
    private  int id;
    private String name;
    private  String userName;
    private String password;
    private String userType;

    public User() {

    }
    public User(int id, String name, String userName, String password, String userType) {
        this.id = id;
        this.name = name;
        this.userName = userName;
        this.password = password;
        this.userType = userType;
    }

    // VERİTABANINDAKİ verilerin form üzerinde gösterilmesi
    public static ArrayList<User> getList(){
        ArrayList<User> userList=new ArrayList<>();
        String query="SELECT * FROM user";
        User obj; // Tekrar tekrar nesne oluşturmak yerine bir kere oluşturup onun değerleerini değiştirerek userList e ekleyeceğiz.
        try {
            Statement st= DBConnector.getInstance().createStatement();
            ResultSet data=st.executeQuery(query);
            while (data.next()){// obj isimli bir User nesnesi oluşturduk. veritabanından erişilen bilgiler obj nesnesine aktarılacak. obj nesnesi de userListe eklenecek.
                obj=new User();
                obj.setId(data.getInt("id"));
                obj.setName(data.getString("name"));
                obj.setUserName(data.getString("user_name"));
                obj.setPassword(data.getString("user_pass"));
                obj.setUserType(data.getString("user_type"));
                userList.add(obj);

            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return userList;// userList liste geri döndürüldü.

    }

    // VERİTABANINA form üzerinden veri ekleme
    public static boolean add(String name, String userName,String password, String userType){
        String query="INSERT INTO user (name, user_name, user_pass, user_type) VALUES (?,?,?,?)";
        //User findUser=User.getFetch(userName);
        if (getFetch(userName)!=null){
            Helper.showMsg("Bu kullanıcı ismi başkası tarafından alınmıştır.Lütfen başka bir kullanıcı adı giriniz.");
            return false;
        }

        try {
            PreparedStatement pt=DBConnector.getInstance().prepareStatement(query);
            pt.setString(1,name);
            pt.setString(2,userName);
            pt.setString(3,password);
            pt.setString(4,userType);
            int response= pt.executeUpdate(); //Ekleme işlemi başarılıysa 1 değilse -1 döndürür. -1'e eşit değilse key true , eşitse false dönecek.

            if (response==-1){
               Helper.showMsg("error");

            }
            return  response!=-1;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return true;

    }

    //VERİTABANINDA formdan girilen  kullanıcı ismi varsa o kullanıcıyı döndüren yoksa null kullanıcıyı null döndüren metot.(Add metodunda aynı kullanıcı adı veritabanında varsa ekleme işlemi yaptırmıyoruz.)
    public static User getFetch(String userName){
        User obj=null;
        String query="SELECT * FROM user WHERE user_name=?";

        try {
            PreparedStatement pt=DBConnector.getInstance().prepareStatement(query);
            pt.setString(1,userName);
            ResultSet data= pt.executeQuery();
            if (data.next()){//Eğer veritabanında bu kullanıcı adı varsa
                obj=new User(); // yeni nesne oluşturduk ve bu nesnenin bilgilerine veritabanındaki bilgileri aktarıyoruz.
                obj.setId(data.getInt("id"));
                obj.setName(data.getString("name"));
                obj.setUserName(data.getString("user_name"));
                obj.setPassword(data.getString("user_pass"));
                obj.setUserType(data.getString("user_type"));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return obj; // o kullanıcı adı varsa obje nesne oluşacak ve içerisindeki elemanları döndürecek. Yoksa null olarak dönecektir.
    }
    public static User getFetch(int id){ // idye göre nesne döndüren metot
        String query="SELECT * FROM user WHERE id=?";
        User obj = null; // veritabanından gelen verileri tutan nesne.
        try {
            PreparedStatement pt= DBConnector.getInstance().prepareStatement(query);
            pt.setInt(1,id);
            ResultSet data=pt.executeQuery();
            if (data.next()){ // Veritabanında bu id varsa obj ye diğer elemanları aktaracak.
                obj=new User(data.getInt("id"),
                        data.getString("name"),
                        data.getString("user_name"),
                        data.getString("user_pass"),
                        data.getString("user_type"));

            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return obj;
    }

// LOGIN EKRNINDAN KULLANICI GİRİŞİ YAPARKEN KULLANILAn METOT
    public static User getFetch(String userName,String password){
        User obj=null;
        String query="SELECT * FROM user WHERE user_name=? AND user_pass=?";

        try {
            PreparedStatement pt=DBConnector.getInstance().prepareStatement(query);
            pt.setString(1,userName);
            pt.setString(2,password);
            ResultSet data= pt.executeQuery();
            if (data.next()){//Eğer veritabanında bu kullanıcı adı ve şifre varsa
                switch (data.getString("user_type")){
                    case "operator":
                        obj=new Operator();// user_type operator ise Operator sınıfından nesne üretiyoruz.
                        break;
                    case "educator":
                        obj=new Educator();
                        break;
                    default:
                        obj=new User(); // User tipinde yeni nesne oluşturduk ve bu nesnenin bilgilerine veritabanındaki bilgileri aktarıyoruz.
                }
                // yeni nesne oluşturduk ve bu nesnenin bilgilerine veritabanındaki bilgileri aktarıyoruz.
                obj.setId(data.getInt("id"));
                obj.setName(data.getString("name"));
                obj.setUserName(data.getString("user_name"));
                obj.setPassword(data.getString("user_pass"));
                obj.setUserType(data.getString("user_type"));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return obj; // o kullanıcı adı ve şifre varsa obje nesne oluşacak ve içerisindeki elemanları döndürecek. Yoksa null olarak dönecektir.
    }

    //VERİTABANINDA form üzerinde belirtilen id ye göre veri silme işlemi
    public static boolean delete(int id){
        ArrayList<Course> courseList=Course.getListByUser(id); // Dışardan gelen idye göre silme işlemi yapacağı kişinin (educator) kurs listesine erişilir.Kursa ait idyi barındırıyor.
        for (Course c: courseList){
            // Sonra bu kurs listesinde Course sınıfının delete metotu çağırılarak  kursun idsi üzerinden silme işlemi gerçekleşir.
            Course.delete(c.getId());
        }
        String query="DELETE FROM user WHERE id=?";
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



    //VERİTABANINDA id si ..... olan satırın elemanlarını güncelleme işlemi.
    public static boolean update(int id,String name,String userName,String password,String userType){


        // Tablodaki kullanıcı adlarında bir değişiklik olduğunda aynı kullanıcı adı kullanılmışsa güncelleme yapmaz.
        User findUser=User.getFetch(userName); // Kullanıcı adı değişen bir eleman varsa bunu buldu.
        if (findUser!=null && findUser.getId()!=id){ // Bulduğu eleman boş değil ve bulduğu elemanın idsi ile güncelleme yapılacak olan elemanın id si eşit değilse farklı satırlarda aynı kullanıcı ismi kullanılmamış olur.
            Helper.showMsg("Bu kullanıcı ismi başkası tarafından alınmıştır.Lütfen başka bir kullanıcı adı giriniz.");
            return false;
        }

        // Eğer kullanıcı tipi educator, operator, student ten birine eşit değilse güncelleme işlemi yapma.
        try {
            ArrayList<String> uType=new ArrayList<>();
            uType.add("educator");
            uType.add("operator");
            uType.add("student");
            if (!userType.equals(uType.get(0)) && !userType.equals(uType.get(1)) && !userType.equals(uType.get(2))) {
                Helper.showMsg("error");
                return false;
            }

        }catch (Exception e){
                // Hata varsa bir şey yapma.
        }

        // idye göre güncelleme işleminin yapıldığı alan.
        String query="UPDATE user SET name=?, user_name=?, user_pass=?, user_type=? WHERE id=? ";
        try {
            PreparedStatement pt=DBConnector.getInstance().prepareStatement(query);
            pt.setString(1,name);
            pt.setString(2,userName);
            pt.setString(3,password);
            pt.setString(4,userType);
            pt.setInt(5,id);
            int response=pt.executeUpdate();
            return response!=-1;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


    }

    //FİLTRELEME uygulanan verileri form üzerinde gösterme işlemi.
    public static ArrayList<User> searchUserList(String query){
        ArrayList<User> userList=new ArrayList<>();
        User obj;
        try {
            PreparedStatement pt=DBConnector.getInstance().prepareStatement(query);
            ResultSet data=pt.executeQuery();
            while (data.next()){
                obj=new User();
                obj.setId(data.getInt("id"));
                obj.setName(data.getString("name"));
                obj.setUserName(data.getString("user_name"));
                obj.setPassword(data.getString("user_pass"));
                obj.setUserType(data.getString("user_type"));
                userList.add(obj);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return userList;

    }

    // FİLTRELEME işlemi
    public static String searchQuery(String name,String userName,String userType){
        String query="SELECT * FROM user WHERE name LIKE '%{{name}}%' AND user_name LIKE '%{{user_name}}%'"; // %{{a}}% : a ile başlayan ve biten tüm verileri bulur.
        query=query.replace("{{name}}",name); // Sorgudaki {{}} içindeki ifadeyi name ile değiştir.
        query=query.replace("{{user_name}}",userName);
        if (!userType.isEmpty()){ // userType veritabanında enum olduğu için null durumdayken sorguda sorun çıkartabiliyor. Bu yüzden boş olmadığı durumu dikkate alarak sonradan ekledik.
            query+=" AND user_type LIKE '{{user_type}}'";
            query=query.replace("{{user_type}}",userType);
        }
        return query;
    }



    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }
}
