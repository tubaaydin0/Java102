package com.patikadev.View;

import com.patikadev.Helper.Config;
import com.patikadev.Helper.Helper;
import com.patikadev.Helper.Item;
import com.patikadev.Model.Content;
import com.patikadev.Model.Course;
import com.patikadev.Model.Educator;
import com.patikadev.Model.Quiz;

import javax.swing.*;

import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class EducatorGUI extends  JFrame {

    private JPanel wrapper;
    private JScrollPane scrl_education_list;
    private JTable tbl_education_list;
    private JTabbedPane tab_education;
    private JPanel pnl_content_list;
    private JPanel pnl_education_list;
    private JPanel pnl_right;
    private JPanel pnl_left;
    private JTextField fld_content_title;
    private JTextField fld_content_description;
    private JTextField fld_content_link;
    private JComboBox cmb_content_course_name;
    private JButton btn_content_add;
    private JTable tbl_content_list;
    private JScrollPane scrl_content_list;
    private JTextField fld_content_Id;
    private JButton btn_content_delete;
    private JTextField fld_conTitle_filter;
    private JButton btn_filter;
    private JComboBox cmb_course_filter;
    private JTextField fld_quiz_question;
    private JPanel pnl_quiz_add;
    private JPanel pnl_quiz_left;
    private JPanel pnl_quiz_right;
    private JScrollPane scrl_quiz_list;
    private JTable tbl_quiz_list;
    private JComboBox cmb_quiz_content;
    private JTextField fld_quiz_answer;
    private JButton btn_quiz_add;
    //Education tablosu için
    private DefaultTableModel mdl_education_list;
    private Object[] row_education_list;
    //##
    //Content Tablosu için
    private DefaultTableModel mdl_content_list;
    private Object[] row_content_list;
    //##

    //Quiz Tablosu
    private DefaultTableModel mdl_quiz_list;
    private Object[] row_quiz_list;
    //##
    private final Educator educator;
    public EducatorGUI(Educator educator){

        this.educator=educator;
        setContentPane(wrapper);
        setSize(650,650);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocation(Helper.screenCenterPoint("x",getSize()),Helper.screenCenterPoint("y",getSize()));

        setTitle(Config.PROJECT_TITLE);
        setVisible(true);

        //mdl_education_list

        //Educator olan birinin eğitim listesini değiştirmesi engellendi.
        mdl_education_list=new DefaultTableModel(){
            @Override
            public boolean isCellEditable(int row, int column) {
                if (column>=0 &&column<5){
                    return false;
                }
                return super.isCellEditable(row, column);
            }
        };
        Object[] col_education_list={"ID","Ders Adı","Programlama Dili","Patika","Eğitmen"};
        mdl_education_list.setColumnIdentifiers(col_education_list);
        row_education_list=new Object[col_education_list.length];
        loadEducationModel();
        tbl_education_list.setModel(mdl_education_list);
        tbl_education_list.setColumnSelectionAllowed(false);
        tbl_education_list.getTableHeader().setReorderingAllowed(false);

        //##
        //mdlcontentlist
        mdl_content_list=new DefaultTableModel();
        Object[] col_content_list={"ID","Başlık","Açıklama","Youtube Link","Ders Adı"};
        mdl_content_list.setColumnIdentifiers(col_content_list);
        row_content_list=new Object[col_content_list.length];
        loadCourseCombo();
        loadCourseFilterCombo();
        loadContentModel();
        tbl_content_list.setModel(mdl_content_list);
        tbl_content_list.getColumnModel().getColumn(0).setMaxWidth(75);
        tbl_content_list.getTableHeader().setReorderingAllowed(false);

       // Tabloda güncelleme yapma işlemi. Tablodaki değişiklikler dinlenir
        tbl_content_list.getModel().addTableModelListener(new TableModelListener() {
            @Override
            public void tableChanged(TableModelEvent e) {
                if (e.getType()==TableModelEvent.UPDATE){ // güncelleme işlemi yapılıyorsa
                    //{"ID","Başlık","Açıklama","Youtube Link","Ders Adı"}
                    int content_id=Integer.parseInt(tbl_content_list.getValueAt(tbl_content_list.getSelectedRow(),0).toString());//Seçilen satırın 0. indisini course_idye atar.
                    String course_title= tbl_content_list.getValueAt(tbl_content_list.getSelectedRow(),1).toString(); //Seçilen satırın 1. indisi başlık a eşit
                    String course_description=tbl_content_list.getValueAt(tbl_content_list.getSelectedRow(),2).toString(); //seçilen satırın 2. indisi Açıklama
                    String course_link=tbl_content_list.getValueAt(tbl_content_list.getSelectedRow(),3).toString(); // Youtube link
                    String course_name=(tbl_content_list.getValueAt(tbl_content_list.getSelectedRow(),4).toString());//Ders Adı
                    //String selectData=(tbl_content_list.getValueAt(tbl_content_list.getSelectedRow(),tbl_content_list.getSelectedColumn()).toString());
                   // System.out.println(selectData);

                   /* String[] tableContent= new String[col_content_list.length];
                    for (int i=0; i<=col_content_list.length;i++){
                        tableContent[i]=tbl_content_list.getValueAt(tbl_content_list.getSelectedRow(),i).toString();
                    }*/

                   if (Content.update(content_id,course_title,course_description,course_link,Course.searchCourseName(course_name))){
                       Helper.showMsg("done");
                       loadContentModel();
                       loadCourseCombo();
                   } else {
                       Helper.showMsg("error");
                    }

                }

            }
        });

        //loadCourseCombo(); // Course comboboxına
        //##mdlContentList


        //mdlQuizList
        mdl_quiz_list=new DefaultTableModel();
        Object[] col_quiz_list={"ID","Soru","Cevap","İçerik Adı"};
        mdl_quiz_list.setColumnIdentifiers(col_quiz_list);
        row_quiz_list=new Object[col_quiz_list.length];
        loadQuizContentCombo();
        loadQuizModel();
        tbl_quiz_list.setModel(mdl_quiz_list);
        tbl_quiz_list.getColumnModel().getColumn(0).setMaxWidth(75);
        tbl_quiz_list.getTableHeader().setReorderingAllowed(false);



        btn_content_add.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Item courseItem=(Item)cmb_content_course_name.getSelectedItem();
                if (Helper.isFieldEmpty(fld_content_title)|| Helper.isFieldEmpty(fld_content_link)|| Helper.isFieldEmpty(fld_content_description)){
                    Helper.showMsg("fill");
                }else{
                    if (Content.add(fld_content_title.getText(),fld_content_description.getText(),fld_content_link.getText(),courseItem.getKey())){
                        loadContentModel();
                        fld_content_link.setText(null);
                        fld_content_title.setText(null);
                        fld_content_description.setText(null);
                    }else{
                        Helper.showMsg("error");
                    }

                }


            }
        });
        btn_content_delete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (Helper.isFieldEmpty(fld_content_Id)){
                    Helper.showMsg("fill");
                }else{
                    if (Helper.confirm("sure")){
                        int content_id=Integer.parseInt(fld_content_Id.getText());
                        if (Content.delete(content_id)){
                            Helper.showMsg("done");
                            loadContentModel();

                        }else{
                            Helper.showMsg("error");
                        }
                    }
                }
            }
        });
        btn_filter.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String content_title=fld_conTitle_filter.getText();
                Item courseItem=(Item)cmb_course_filter.getSelectedItem();// Ders isimleri filtreleme yerine yazıldı.
                String query=Content.filterQuery(content_title,courseItem.getKey()); // key anahtarı idyi getiriyor.
                loadContentModel(Content.filterContentList(query));



            }
        });
        btn_quiz_add.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Item contentItem= (Item) cmb_quiz_content.getSelectedItem();
                if (Helper.isFieldEmpty(fld_quiz_question) || Helper.isFieldEmpty(fld_quiz_answer)){
                    Helper.showMsg("fill");
                }else{
                    //quiz veritabanına eklendi mi
                    if (Quiz.add(fld_quiz_question.getText(),fld_quiz_answer.getText(),contentItem.getKey())){
                        Helper.showMsg("done");
                        loadQuizModel();
                        fld_quiz_question.setText(null);
                        fld_quiz_answer.setText(null);
                    }else {
                        Helper.showMsg("error");
                    }
                }
            }
        });
    }

    private void loadQuizModel() {
        DefaultTableModel clearmodel= (DefaultTableModel) tbl_quiz_list.getModel();
        clearmodel.setRowCount(0);
        int i=0;
        //{"ID","İçerik Adı","Soru","Cevap"}
        for (Quiz obj: Quiz.getList()){
            i=0;
            row_quiz_list[i++]=obj.getId();
            row_quiz_list[i++]=obj.getQuiz_question();
            row_quiz_list[i++]=obj.getQuiz_answer();
            row_quiz_list[i++]=obj.getContent().getContent_title();
            mdl_quiz_list.addRow(row_quiz_list);
        }
    }

    private void loadContentModel() {
        DefaultTableModel clearmodel=(DefaultTableModel) tbl_content_list.getModel();
        clearmodel.setRowCount(0); // Tablo temizlendi.
        int i=0;
        //{"ID","Başlık","Açıklama","Youtube Link","Ders Adı"};
        for (Content obj: Content.getList()){
            i=0;
            row_content_list[i++]=obj.getId();
            row_content_list[i++]=obj.getContent_title();
            row_content_list[i++]=obj.getContent_description();
            row_content_list[i++]=obj.getContent_link();
            row_content_list[i++]=obj.getCourse().getCourse_name();
            mdl_content_list.addRow(row_content_list);

        }
    }
    //Başlığa göre filteleme
    private void loadContentModel(ArrayList<Content> list) {
        DefaultTableModel clearmodel=(DefaultTableModel) tbl_content_list.getModel();
        clearmodel.setRowCount(0); // Tablo temizlendi.
        int i=0;
        //{"ID","Başlık","Açıklama","Youtube Link","Ders Adı"};
        for (Content obj:list){
            i=0;
            row_content_list[i++]=obj.getId();
            row_content_list[i++]=obj.getContent_title();
            row_content_list[i++]=obj.getContent_description();
            row_content_list[i++]=obj.getContent_link();
            row_content_list[i++]=obj.getCourse().getCourse_name();
            mdl_content_list.addRow(row_content_list);

        }
    }

    public void loadQuizContentCombo(){
        cmb_quiz_content.removeAllItems();
        for (Content obj: Content.getList()){
            cmb_quiz_content.addItem(new Item(obj.getId(),obj.getContent_title()));
        }
    }
    public void loadCourseCombo(){
        cmb_content_course_name.removeAllItems();

        for (Course obj: Course.getListByUser(educator.getId())){
            cmb_content_course_name.addItem(new Item(obj.getId(),obj.getCourse_name()));
        }
    }
    public void loadCourseFilterCombo(){
       cmb_course_filter.removeAllItems();

        for (Course obj: Course.getListByUser(educator.getId())){
            cmb_course_filter.addItem(new Item(obj.getId(),obj.getCourse_name()));
        }
    }
    ArrayList <Course> cList;
    public void loadEducationModel() {
        DefaultTableModel clearModel= (DefaultTableModel) tbl_education_list.getModel();
        clearModel.setRowCount(0);
        int i=0;
        cList=Course.getListByUser(educator.getId());
        for (Course obj: cList){
                i = 0;
                row_education_list[i++] = obj.getId();
                row_education_list[i++] = obj.getCourse_name();
                row_education_list[i++] = obj.getLang();
                row_education_list[i++] = obj.getPatika().getPatikaName();
                row_education_list[i++] = obj.getEducator().getName();
                mdl_education_list.addRow(row_education_list);

        }

    }
    public static void main(String[] args){
        Helper.setLayout();
        Educator e=new Educator();
        EducatorGUI eg=new EducatorGUI(e);
    }

}
