import javax.sound.midi.Soundbank;
import java.util.Scanner;

public  abstract  class Products { // Ürünler Sınıfı
    private int id;
    private String productName;
    private double unitPrice; // birim fiyat
    private int discountRate; // indirim oranı
    private int stockNumber; //Stok adeti
    private String brand; // markası
    private int memory;
    private int ram;
    private double screenSize;
    Scanner inp=new Scanner(System.in);
    public Products(int id, String productName, double unitPrice, String brand,int memory,double screenSize,int ram,int discountRate, int stockNumber) {
        this.id = id;
        this.productName = productName;
        this.unitPrice = unitPrice;
        this.brand = brand;
        this.memory = memory;
        this.screenSize = screenSize;
        this.ram = ram;
        this.discountRate = discountRate;
        this.stockNumber = stockNumber;
    }

    public Products() {
    }
    public int userSelectGroup(){
        System.out.print("1-Cep Telefonu\n2-Notebook\nHangisine ürün eklemek istersiniz: ");
        int selectCase=inp.nextInt();
        while (!(selectCase==1 || selectCase==2)){
            System.out.print("Yanlış değer girdiniz.\nTekrar giriniz:");
            selectCase=inp.nextInt();
        }
        if (selectCase==1){
            return 1;
        }
        return 2;
    }
    public abstract  void menu();
    public abstract void addElement();
    public abstract  void deleteElement();
    public abstract void filtering();
    public abstract void firstProductList();
    public abstract void print();
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public double getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(double unitPrice) {
        this.unitPrice = unitPrice;
    }

    public int getDiscountRate() {
        return discountRate;
    }

    public void setDiscountRate(int discountRate) {
        this.discountRate = discountRate;
    }

    public int getStockNumber() {
        return stockNumber;
    }

    public void setStockNumber(int stockNumber) {
        this.stockNumber = stockNumber;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public int getMemory() {
        return memory;
    }

    public void setMemory(int memory) {
        this.memory = memory;
    }

    public int getRam() {
        return ram;
    }

    public void setRam(int ram) {
        this.ram = ram;
    }

    public double getScreenSize() {
        return screenSize;
    }

    public void setScreenSize(int screenSize) {
        this.screenSize = screenSize;
    }
}
