package com.patikadev.View;

// Patika tablosu kutucuk seçme ödevi
//Corse tablosu güncelle sil alanları
import com.patikadev.Helper.Config;
import com.patikadev.Helper.Helper; // Helper modülündeki Helper sınıfına ulaşmak için import edildi.
import com.patikadev.Helper.Item;
import com.patikadev.Model.Course;
import com.patikadev.Model.Operator;
import com.patikadev.Model.Patika;
import com.patikadev.Model.User;

import javax.swing.*;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class OperatorGUI extends JFrame {
    private JPanel wrapper;
    private JTabbedPane tab_operator;
    private JLabel lbl_welcome;
    private JPanel pnl_top;
    private JButton btn_logout;
    private JPanel pnl_user_list;
    //Kullanıcılar
    private JScrollPane scrl_user_list;
    private JTable tbl_user_list;
    private JPanel pnl_user_form;
    private JTextField fld_user_name;
    private JTextField fld_user_uname;
    private JTextField fld_user_pass;
    private JComboBox cmb_user_type;
    private JButton btn_user_add;
    private JTextField fld_user_id;
    private JButton btn_user_delete;
    private JTextField fld_sh_user_name;// Adına göre filtreleme
    private JTextField fld_sh_user_uname;// Kullanıcı adına göre filtreleme
    private JComboBox cmb_sh_user_type; // üyelik tipine göre filteleme
    private JButton btn_user_sh;
    private JScrollPane scrl_patika_list;
    private JTable tbl_patika_list;
    private JPanel pnl_patika_add;
    private JTextField fld_patika_name;
    private JButton btn_patika_add;
    private JPanel pnl_patika_list;
    private JPanel pnl_course_list;
    private JScrollPane scrl_course_list;
    private JTable tbl_course_list;
    private JPanel pnl_course_add;
    private JTextField fld_course_name;
    private JTextField fld_course_lang;
    private JComboBox cmb_course_patika;
    private JComboBox cmb_course_user;
    private JButton btn_course_add;

    //User Tablosu için
    private DefaultTableModel mdl_user_list; // Tabloiçin. Tablolamada kullandığım diğer isimlere benzer isimlendirildi.
    private Object[] row_user_list; //  Tabloiçin. tipinde satırdaki diziyi tutan bir liste tanımlandı.

    //Patika tablosu için
    private DefaultTableModel mdl_patika_list;
    private  Object[] row_patika_list;

    private JPopupMenu patikaMenu; // Patika tablosunda sağ tıklandığında seçenek çıkması için popupmenu sınıfından patikaMenu nesnesi oluşturuldu.

    //Course Tablosu için

    private DefaultTableModel mdl_course_list;
    private  Object[] row_course_list;

    private final Operator operator; // Operatöre ait tüm işlemleri OperatorGUI de göstereceğimiz için dahil ediyoruz.
    public OperatorGUI(Operator operator){// Bu arayüzü çalıştırırken mecbur bir Operator atmamız gerekiyor. Bu nedenle parametre olarak yazıyoruz.
        this.operator=operator;
        setContentPane(wrapper);
        setSize(600,500);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        // Helper sınıfına parametreler gönderildi ve form ekranın ortasına yerleştirilmiş oldu.
        setLocation( Helper.screenCenterPoint("x",getSize()), Helper.screenCenterPoint("y",getSize()));
        setTitle(Config.PROJECT_TITLE); // Helper pketindeki Config sınıfından Proje başlığı çekildi.
        setVisible(true);
        lbl_welcome.setText("Hoşgeldin!  "+operator.getName()); // Hoşgeldiniz yazısı değiştirildi.

        //ModelUserList

        mdl_user_list=new DefaultTableModel(){// Tablo oluştururken kurucu metotta nesne oluşturuldu.
            //Tablodaki Seçilen satırın ilk sütunun değerinin tıklanınca değiştirilmemesini sağlayan kod.
            @Override
            public boolean isCellEditable(int row, int column) { // 0. kolonun bilgileri değiştirilmesin.
                if (column==0){
                    return false;
                }
                return super.isCellEditable(row, column);
            }
        };
        Object[] col_user_list={"ID","AD SOYAD","KULLANICI ADI","ŞİFRE","ÜYELİK TİPİ"};
        mdl_user_list.setColumnIdentifiers(col_user_list); // Kolon tanımlayıcıları yani kolon isimleri col_user_list ile değiştirildi.
        //Buraya kadar bir model oluşturuldu. Şimdi oluşturulan bu model tabloya aktarılacak.
        row_user_list=new Object[col_user_list.length]; // satırdaki eleman sayısı sütun sayısına eşit.
        loadUserModel();
        tbl_user_list.setModel(mdl_user_list); // Tablonun modeline mdl user list modeli aktarıldı.
        tbl_user_list.getTableHeader().setReorderingAllowed(false); // tablonun başlıklarının yeniden sıralanmasına izin verilmiyor.


        //TABLO üzerindeki kayıtlı ID nin silme işleminde seçilen satırın gösterilme işlemi
        // Seçilen satır üzerinde işlem yapmayı sağlıyor.
        tbl_user_list.getSelectionModel().addListSelectionListener(e -> {
           //Silinmesi istenen satır seçilip silindikten sonra tablo yenilenmiyor çünkü tabloda o satır seçili kalıyor veritabanında boyle bir veri kalmadığı içinde  hata oluşuyor.Hata almamak için try catch yapıyoruz.
            try {
                String select_user_id = tbl_user_list.getValueAt(tbl_user_list.getSelectedRow(), 0).toString();// getValueAt tablonun satır ve süton numarasını seçme işlemi yapar. Bu işlem geriye obje döndürür. to String ile objedeki satır numarası çekilir.
                fld_user_id.setText(select_user_id); // Formda silme işlemi yaparken kullanıcı ıdyi belirten labele satır numarası yazdırılır.Yani talodan seçtiğin satır numarası buraya yazdırılmış oldu.
                } catch (Exception ex){
                // Hata yakalayınca gösterme
                }
        });


        //TABLODAKİ verilerin güncellenmesi ve bu değişikliklerin veritabanında uygulanması
        tbl_user_list.getModel().addTableModelListener(new TableModelListener() { // tablonunu modelini getirip addTableModelListenera yeni listener nesnesi gönderiyoruz.
            @Override
            public void tableChanged(TableModelEvent e) {
                //Yapılan işlem update işlemi mi kontrol edilir
                if (e.getType()==TableModelEvent.UPDATE){

                    int user_id=Integer.parseInt(tbl_user_list.getValueAt(tbl_user_list.getSelectedRow(),0).toString());// 0. sütundaki seçilen satırın elemanı idye eşit.
                    String user=tbl_user_list.getValueAt(tbl_user_list.getSelectedRow(),1).toString(); // 1. sütundaki seçilen satırın elemanı name atılır.
                    String user_name= tbl_user_list.getValueAt(tbl_user_list.getSelectedRow(),2).toString();//2. sütundaki seçilen satırın elemanı user name e eşit
                    String user_pass=tbl_user_list.getValueAt(tbl_user_list.getSelectedRow(),3).toString();// 3. sütundaki seçilen satırın elemanı password a eşit
                    String user_type=tbl_user_list.getValueAt(tbl_user_list.getSelectedRow(),4).toString();//4. sütundaki seçilen satırın elemanı usertype a eşit

                    if (User.update(user_id,user,user_name,user_pass,user_type)){ // Güncelleme başarılıysa
                        Helper.showMsg("done");

                    }

                    loadUserModel();// Güncellenen tablo listelendi.
                    loadEducatorCombo();// Kullanıcı tablosu her yenilendiğinde Course formundaki usercomboboxı da yenilenecek.
                    loadCourseModel();// kurs tablosu da güncellensin


                }

            }
        });
        //##ModelUserList


        //ModelPatikaList

        //POPUP MENU KULLANIMI
        patikaMenu=new JPopupMenu(); // Kurucu metodu çaşıltırıldı.
        JMenuItem updateMenu=new JMenuItem("Güncelle"); // Sağ tıklandığında açılan menüye Güncelle isimli bir seçenek eklendi.
        JMenuItem deleteMenu=new JMenuItem("Sil");//Sağ tıklandığında açılan menüye Sil isimli bir seçenek eklendi.
        patikaMenu.add(updateMenu);// popup menüye güncelle seçeneği eklendi.
        patikaMenu.add(deleteMenu);//popup menüye sil seçeneği eklendi.
        //Aşağıda oluşturulan bu popupmenu patika tablosuna aktarılacak.


        updateMenu.addActionListener(new ActionListener() {// POPUPTA Güncelle menüsüne  tıklandığında
            @Override
            public void actionPerformed(ActionEvent e) {
                int select_id=Integer.parseInt((tbl_patika_list).getValueAt(tbl_patika_list.getSelectedRow(),0).toString()); // seçilen satırın id bilgisini getirir.
                UpdatePatikaGUI UpdatePatikaGUI=new UpdatePatikaGUI(Patika.getFetch(select_id)); //Operator Gui PatikaGuI ye bağlandı. Patika GUI açıldığında seçilen idye göre patika ismi görünecek.

               //Update menü kapatıldığında tabloyu yeniliyoruz.
                UpdatePatikaGUI.addWindowListener(new WindowAdapter() {
                    @Override
                    public void windowClosed(WindowEvent e) {
                       loadPatikaModel();
                       loadPatikaCombo(); // patika tablosu her yenilendiğinde Course formundaki patika comboboxı da yenilenir.
                       loadCourseModel();// Course tablosu da yenilenir
                    }
                });
            }
        });


        deleteMenu.addActionListener(new ActionListener() {// POPUPTA Sil menüsüne tıklanınca
            @Override
            public void actionPerformed(ActionEvent e) {
                if (Helper.confirm("sure")){
                    int select_id=Integer.parseInt((tbl_patika_list).getValueAt(tbl_patika_list.getSelectedRow(),0).toString());// Silinecek satırın idsi
                    if (Patika.delete(select_id)){
                        Helper.showMsg("done");
                        loadPatikaModel();
                        loadPatikaCombo(); // patika tablosu her yenilendiğinde Course formundaki patika comboboxı da yenilenir.
                        loadCourseModel();
                    }else{
                        Helper.showMsg("error");
                    }
                }


            }
        });
        //##POPUP MENU KULLANIMI




        mdl_patika_list=new DefaultTableModel(); // mdl patika list adında yeni model oluşturuldu.
        Object[] col_patika_list={"ID","Patika Adı"}; // Sırasıyla Kolon isimleri belirtildi.
        mdl_patika_list.setColumnIdentifiers(col_patika_list); // modelin kolon isimlerine col_patika_list objesindeki elemanlar yerleştirildi.
        row_patika_list=new Object[col_patika_list.length]; // row_patika_list isimli objenin uzunluğu kolon sayısı kadar yapıldı.
        loadPatikaModel();
        tbl_patika_list.setModel(mdl_patika_list);// patika tablosuna oluşturulan patika modeli aktarıldı.

        tbl_patika_list.setComponentPopupMenu(patikaMenu);// patika tablosuna popup menü aktarıldı.

        tbl_patika_list.getTableHeader().setReorderingAllowed(false); // Patika tablosundaki kolon başlıklarının yer değiştirmesi engellendi.
        tbl_patika_list.getColumnModel().getColumn(0).setMaxWidth(75);// 0. kolonun genişliği 75 olarak ayarlandı.

        // patika tablosunda sağ tık yapıldığında tıklanan yerin seçili olması (koyu renk gösterilmesi) işlemi
        tbl_patika_list.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) { // Mouse basıldığında
                //Point koordinat bilgilerini tutan bir sınıf
               Point point=e.getPoint();//mouseun hareketine göre x y koordinatları point nesnesine aktarıldı.

                // rowAtPoint() fonk. içine hangi koordinat bilgisini tutan nesneyi yazarsak o satırı bize verir.
                int selected_row=tbl_patika_list.rowAtPoint(point);//şeçilen satır belirlendi.

                // ?????????????????NEDEN 2 kere SELECTED_ROW YAZDIK?????????????????
                // sağ tıklayınca satır seçiliyor. 2. kez başka bir satıra sağ tık yapınca seçilmiyor. Nasıl düzeltilir????

                tbl_patika_list.setRowSelectionInterval(selected_row,selected_row); // patika listesindeki satırın seçili aralığına seçilen satır bilgisi girildi.


            }
        });

        //##ModelPatikaList

        //ModelCourseList

        mdl_course_list=new DefaultTableModel();
        Object[] col_course_list={"ID","Ders Adı","Programlama Dili","Patika","Eğitmen"}; //  kolon isimleri belirtildi.
        mdl_course_list.setColumnIdentifiers((col_course_list));// Belirtilen kolon isimleri modelin kolonlarına atandı.
        row_course_list=new Object[col_course_list.length];// satırdaki eleman sayısı col_user_listteki eleman sayısına eşit.
        loadCourseModel();
        tbl_course_list.setModel(mdl_course_list);// model tabloya aktarıldı.
        tbl_course_list.getColumnModel().getColumn(0).setMaxWidth(75); // Tabloda ilk kolonun genişliği max 75 olarak ayarlandı.
        tbl_course_list.getTableHeader().setReorderingAllowed(false);//program çalıştığı sırada Tablonun kolonlarının yer değiştirilmesi engellendi


        // cmb_course_patika.addItem(new Item(1,"1. eleman")); // combobox ın içine  1. eleman yazısını ekler ve 1. elemanın idsini de gönderir.
        // Bunun için loadPatikaCombo metotu oluşturuldu.
        loadPatikaCombo();// patika comboboxını elemanları gösterilecek
        // patika formunda ekle veya sil işlemi yapıldığında patikacomboboxı yenilenmiyor.
        // bunun için Patika formundaki ekle sil yapılan yerlerde patikacomboboxı yineleyen loadPatikaCombo() metotunu da kullanmalıyız.

        loadEducatorCombo();

        //##ModelCourseList


        // FORM üzerinden kullanıcı ekleme işlemleri
        btn_user_add.addActionListener(e -> {
            if (Helper.isFieldEmpty(fld_user_name)|| Helper.isFieldEmpty(fld_user_uname)||Helper.isFieldEmpty(fld_user_pass)){
               Helper.showMsg("fill");
            }else{
               String name= fld_user_name.getText();
               String userName=fld_user_uname.getText();
               String password=fld_user_pass.getText();
               String userType=cmb_user_type.getSelectedItem().toString(); // comboboxtaki seçilen veriyi değişkene atadık.
                if (User.add(name,userName,password,userType)){
                    Helper.showMsg("done");
                    loadUserModel();
                    loadEducatorCombo();// Kullanıcı tablosu her yenilendiğinde Course formundaki usercomboboxı da yenilenecek.
                    // Ekleme işleminden sonra textlerin içini boşaltırız.
                    fld_user_name.setText(null);
                    fld_user_uname.setText(null);
                    fld_user_pass.setText(null);
                }
            }

        });
        // FORMDAKİ TABLODAN kullanıcı silme işlemi
        btn_user_delete.addActionListener(e -> {
            if (Helper.isFieldEmpty(fld_user_id)){ // fld_user_id boşsa  tüm alanları doldurun şeklinde uyarı verecek.
                Helper.showMsg("fill");
            }
            else{
                if (Helper.confirm("sure")) {
                    int userID = Integer.parseInt(fld_user_id.getText());
                    if (User.delete(userID)) {
                        Helper.showMsg("done");
                        loadUserModel(); // Silme işlemi yapılınca tablo yenilendi.
                        loadEducatorCombo();// Kullanıcı tablosu her yenilendiğinde Course formundaki usercomboboxı da yenilenecek.
                        loadCourseModel();// Course u olabilir bu tablodan da silmesi için Course tablosu da refres edilir.
                        fld_user_id.setText(null);

                    } else {
                        Helper.showMsg("error");
                    }
                }



            }
        });

        //TABLODAKİ verileri filtreleme işlemi
        btn_user_sh.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String name= fld_sh_user_name.getText();
                String uname=fld_sh_user_uname.getText();
                String utype=cmb_sh_user_type.getSelectedItem().toString();
                String query=User.searchQuery(name,uname,utype);
                //ArrayList<User> searchingUser= User.searchUserList(query); // searchinUser  Arraylistini,User sınıfındaki searchUserList() metodunun geri döndürdüğü arrayliste eşitledik.
                loadUserModel(User.searchUserList(query));

            }
        });

        //FORMDAKİ ÇIKIŞ butonuna tıklandığında
        btn_logout.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                dispose(); // dispose ile program sonlandırılır.Login ekranına dön.
                LoginGUI login=new LoginGUI();
            }
        });
        // FORMDAKİ patika bölümü ekle butonuna tıklandığında
        btn_patika_add.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (Helper.isFieldEmpty(fld_patika_name)){// patika adının bulunduğu textfield boş ise tüm alan dolu olmalıdır gibi bir uyarı verilsin.
                    Helper.showMsg("fill");
                }else{
                    if (Patika.add(fld_patika_name.getText())){
                        Helper.showMsg("done");
                        loadPatikaModel();
                        loadPatikaCombo(); // ekleme işlemi yapıldığında COurse formundaki patikacomboboxı da yenilenmeli.
                        fld_patika_name.setText(null); // Ekleme işleminden sonra text boş görünsün.
                    }else{
                        Helper.showMsg("error");
                    }

                }

            }
        });
        btn_course_add.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Item patikaItem = (Item) cmb_course_patika.getSelectedItem(); // patikacomboboxlar itemden oluştuğu için item sınıfı oluşturduk.
                // seçilen itemi patikaItem nesnesine atadık.patikaItem üzerinden patika_id (key) ye erişilecek.
                Item userItem= (Item) cmb_course_user.getSelectedItem();// seçilen itemi userItem e atadık. userItem üzerinden user_idye (key) ulaşılacak
                if (Helper.isFieldEmpty(fld_course_name) || Helper.isFieldEmpty(fld_course_lang)){
                    Helper.showMsg("fill");
                }else{
                    if(Course.add(userItem.getKey(),patikaItem.getKey(),fld_course_name.getText(),fld_course_lang.getText())){
                        loadCourseModel();
                        fld_course_name.setText(null);
                        fld_course_lang.setText(null);
                    }else{
                        Helper.showMsg("error");
                    }

                }
            }
        });
    }


    //(cmb_course_patika içine eleman ekleyen metot.)
    //Course formundaki patika isimlerinin seçildiği comboboxa eleman ekler.
    public void loadPatikaCombo(){
        cmb_course_patika.removeAllItems(); // comboboxın içindeki verilerin tümünü siler.

        //Veritabanından çekilen patika isimlerini döndürecek
        for (Patika obj: Patika.getList()){
            cmb_course_patika.addItem(new Item(obj.getId(),obj.getPatikaName())); // combobox ın ilk elemanına obj nesnesinin id ve name bilgilerini ekler.

        }

    }

    //(cmb_course_user içine eleman ekleyen metot.)
    //Course formundaki eğitmen isminin seçildiği comboboxa eleman ekler.
    public  void  loadEducatorCombo(){
        cmb_course_user.removeAllItems(); // comboboxın içindeki tüm veriler silindi.
        //Veritabanından çekilen educator ları döndürecek.
        for (User obj: User.getList()){
            if (obj.getUserType().equals("educator")){
                cmb_course_user.addItem(new Item(obj.getId(),obj.getName()));
            }
        }
    }

    private void loadCourseModel() {
        DefaultTableModel clearModel= (DefaultTableModel) tbl_course_list.getModel();
        clearModel.setRowCount(0); // course tablosu silindi
        int i=0;
        for (Course obj: Course.getList()){
            i=0;
            //{"ID","Ders Adı","Programlama Dili","Patika","Eğitmen"} kolon sırasına göre satırları yanyana yazıyoruz.
            row_course_list[i++]=obj.getId(); // row_course_list in 0. indise id
            row_course_list[i++]=obj.getCourse_name();//1. indise course_name
            row_course_list[i++]=obj.getLang();//2. indise lang bilgileri aktarılır.
            row_course_list[i++]=obj.getPatika().getPatikaName();//3. indise patika adı
            row_course_list[i++]=obj.getEducator().getName();//4. indise eğitimcinin adı

            mdl_course_list.addRow(row_course_list); // course modeline oluşan bu satır eklenir.
            //Veritabanından çekilen satırlar bitene kadar (getList metodundaki obj sayısı kadar) bu işlem tekrarlanır.


        }
    }

    //Patika tablosunun refresh edildiği metot.
    private void loadPatikaModel() {
        DefaultTableModel clearModel=(DefaultTableModel) tbl_patika_list.getModel();
        clearModel.setRowCount(0); // patika tablosu silindi.
        int i=0;
        for(Patika obj: Patika.getList()){
            i=0;
            row_patika_list[i++]=obj.getId();
            row_patika_list[i++]=obj.getPatikaName();
            mdl_patika_list.addRow(row_patika_list); // patika modeline bu row_patika_list isimli objenin elemanları aktarıldı.
        }
    }

    // User Tablosunun refresh edildiği metot.
    //User daki getList metodu burada kullanıldı.
    public  void loadUserModel(){
        //Tabloyu silip tekrar ekleyeceğiz.
        //Tablo silme işlemi
        DefaultTableModel clearModel= (DefaultTableModel) tbl_user_list.getModel();
        clearModel.setRowCount(0);
        int i=0;
        for (User obj: User.getList()) { // User sınıfındaki veritabanından çekilen kullanıcı listesine erişildi.
            //Satırdaki elemanların indis numarasına göre denk gelecek elemanlar  sırasıyla yazılır.
            i=0;
            row_user_list[i++]=obj.getId();
            row_user_list[i++]=obj.getName();
            row_user_list[i++]=obj.getUserName();
            row_user_list[i++]=obj.getPassword();
            row_user_list[i++]=obj.getUserType();
            mdl_user_list.addRow(row_user_list); // Modele bu row dizisi aktarılır.
        }
    }

    //Filrelenen verileri formda göstermek için overloading yaptık.
    public  void loadUserModel(ArrayList<User> list){ // Tablo Yenileme işleminin yapıldığı metot.
        //Tabloyu silip tekrar ekleyeceğiz.
        //Tablo silme işlemi
        DefaultTableModel clearModel= (DefaultTableModel) tbl_user_list.getModel();
        clearModel.setRowCount(0);
        for (User obj: list) { // User sınıfındaki veritabanından çekilen kullanıcı listesine erişildi.
            //Satırdaki elemanların indis numarasına göre denk gelecek elemanlar  sırasıyla yazılır.
            int i=0;
            row_user_list[i++]=obj.getId();
            row_user_list[i++]=obj.getName();
            row_user_list[i++]=obj.getUserName();
            row_user_list[i++]=obj.getPassword();
            row_user_list[i++]=obj.getUserType();
            mdl_user_list.addRow(row_user_list); // Modele bu row dizisi aktarılır.
        }
    }

    public static void main(String[] args) {
        Helper.setLayout();// Tema nimbus olarak ayarlandı.

        Operator op=new Operator();

        OperatorGUI o=new OperatorGUI(op);
           // OperatorGUI o=new OperatorGUI();

    }


}
