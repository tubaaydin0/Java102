package com.patikadev.View;

import com.patikadev.Helper.Config;
import com.patikadev.Helper.Helper;
import com.patikadev.Model.Educator;
import com.patikadev.Model.Operator;
import com.patikadev.Model.User;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginGUI extends JFrame {
    private JPanel wrapper;
    private JPanel wtop;
    private JPanel wbuttom;
    private JTextField fld_user_uname;
    private JTextField fld_user_pass;
    private JButton btn_login;

    public LoginGUI() {
        setContentPane(wrapper);
        setSize(400, 500);
        setLocation(Helper.screenCenterPoint("x", getSize()), Helper.screenCenterPoint("y", getSize()));
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setTitle(Config.PROJECT_TITLE);
        setResizable(false);
        setVisible(true);



        btn_login.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (Helper.isFieldEmpty(fld_user_uname) || Helper.isFieldEmpty(fld_user_pass)) {
                    Helper.showMsg("fill");
                } else {

                    User u = User.getFetch(fld_user_uname.getText(), fld_user_pass.getText());
                    if (u == null) {
                        Helper.showMsg("Kullanıcı adı veya parola hatalı !");
                    } else {
                        switch (u.getUserType()) {
                            case "operator":
                                OperatorGUI opGUI = new OperatorGUI((Operator) u);
                                break;
                            case "educator":
                                EducatorGUI edGUI = new EducatorGUI((Educator) u);
                                //edGUI.loadEducationModel(u.getId()); // Sadece o eğitmene ait olan eğitimler sıralandı.
                                break;
                            case "student":
                                StudentGUI stGUI = new StudentGUI();
                                break;
                        }
                        dispose(); // Ekran kapatılsın
                    }


                }
            }
        });
    }

    public static void main(String[] args) {
        LoginGUI log = new LoginGUI();


    }
}