import java.sql.*;

public class DBconnection {
    public static final String DB_URL="jdbc:mysql://localhost/university";
    public static final String DB_USER="root";
    public static final String DB_PASSWORD="mysql";

    public static void main(String[] args) {
        Connection connect=null;
        String sql="SELECT * FROM employees"; // employees tablosunun tüm verilerine erişildi.

        try {
            connect= DriverManager.getConnection(DB_URL,DB_USER,DB_PASSWORD);
            Statement st=connect.createStatement();  // Statement oluşturuldu.
            ResultSet data= st.executeQuery(sql); //data ile employees tablosuna erişildi.
            while(data.next()){
                System.out.format("%-8s","ID: "+data.getInt("employees_id"));
                System.out.format("%-20s","AD:"+data.getString("employees_name"));
                System.out.format("%-30s","MEVKİ: "+data.getString("employees_position"));
                System.out.format("%-10s","MAAŞ: "+data.getDouble("employees_salary"));
                System.out.println();
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }


    }
}
