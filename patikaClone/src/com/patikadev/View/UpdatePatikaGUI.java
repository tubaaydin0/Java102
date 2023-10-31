package com.patikadev.View;
import com.patikadev.Helper.Config;
import com.patikadev.Helper.Helper;
import  com.patikadev.Model.Patika;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class UpdatePatikaGUI extends JFrame {
    private JPanel wrapper;
    private JTextField fld_patika_name;
    private JButton btn_update;
    private Patika patika;

    public UpdatePatikaGUI(Patika patika) {
        this.patika = patika;
        setContentPane(wrapper);
        setSize(300, 200);
        setLocation(Helper.screenCenterPoint("x", getSize()), Helper.screenCenterPoint("y", getSize()));
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setTitle(Config.PROJECT_TITLE);
        setVisible(true);
        fld_patika_name.setText(patika.getPatikaName());


        btn_update.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(Helper.isFieldEmpty(fld_patika_name)){
                    Helper.showMsg("fill");
                }else{
                    if (Patika.update(patika.getId(),fld_patika_name.getText())){//gönderilen id ve name e  göre güncelleştirme gerçekleştiyse
                        Helper.showMsg("done");
                    }
                }
                dispose(); // Güncelleme işlemi yapıldıktan sonra programı kapat

            }
        });
    }

  /*  public static void main(String[] args) {
        Helper.setLayout();
        Patika p=new Patika(1,"Frontend");
        UpdatePatikaGUI up=new UpdatePatikaGUI(p);
    }*/
}
