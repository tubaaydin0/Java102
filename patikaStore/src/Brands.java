import java.util.*;

public class Brands implements Comparable<Brands> { // Markalar Sınıfı
    private int id;
    private String brandName;
    TreeSet<Brands> brands=new TreeSet<>();

    public Brands(int id, String brandName){
        this.id=id;
        this.brandName=brandName;

    }
    public Brands(){

    }

    @Override
    public int compareTo(Brands o) {
        return this.getBrandName().compareTo(o.getBrandName());
    }

    public void brandList(){

        brands.add(new Brands(1,"Samsung"));
        brands.add(new Brands(2,"Lenovo"));
        brands.add(new Brands(3,"Apple"));
        brands.add(new Brands(4,"Huawei"));
        brands.add(new Brands(5,"Casper"));
        brands.add(new Brands(6,"Asus"));
        brands.add(new Brands(7,"HP"));
        brands.add(new Brands(8,"Xiaomi"));
        brands.add(new Brands(9,"Monster"));

    }
    public void printBrandList(){ // Marka listelendi.
        this.brandList();
        for (Brands b: brands){
            System.out.println("-"+b.getBrandName());
        }
    }
    public void userEntriedBrandList(){ // Ürünlerdeki add metodunu gönderilecek.
        this.brandList();
        int i=1;
        for (Brands b: brands){
            System.out.println((i++)+"-"+b.getBrandName());
        }

    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getBrandName() {
        return brandName;
    }

    public void setBrandName(String brandName) {
        this.brandName = brandName;
    }
}


