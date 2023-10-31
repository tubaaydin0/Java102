package com.patikadev.Helper;
// Course formundaki patika adlarını comboboxta listelemek için oluşturuldu.
public class Item {
    private  int key; // id
    private  String value;// Patika adı

    public Item(int key, String valeu) {
        this.key = key;
        this.value = valeu;
    }



    //?????????? NEDEN TOSTRING METODU OLDU. BUNU ÇAĞIRMADAN NASIL KULLANIYORUZ???????
    public String toString(){
        return this.value;
    }
    public int getKey() {
        return key;
    }

    public void setKey(int key) {
        this.key = key;
    }

    public String getValeu() {
        return value;
    }

    public void setValeu(String valeu) {
        this.value = valeu;
    }
}
