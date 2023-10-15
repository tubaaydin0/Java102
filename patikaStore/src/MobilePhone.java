
import java.util.*;

public class MobilePhone extends Products {

    private double batteryPower;
    private String colour;
    private int camera;
    private static  int id=1;
    Brands brands=new Brands();
    LinkedList<MobilePhone> phones = new LinkedList<>();
    boolean isContinue=true;



    public MobilePhone( String productName, double unitPrice, String brand, int memory, double screenSize, int camera, double batteryPower, int ram, String colour, int discountRate, int stockNumber) {
        super(id,productName, unitPrice, brand, memory, screenSize, ram, discountRate, stockNumber);
        id++; // her eleman eklendiğinde id 1 artacak.
        this.batteryPower = batteryPower;
        this.colour = colour;
        this.camera = camera;
    }

    public MobilePhone() {

    }

    @Override
    public void menu() {
        this.firstProductList(); // Listeye eklenen Ürünler

        while(isContinue){
            System.out.println();
            System.out.println("---------------------CEP TELEFONU PANELİ---------------------");
            System.out.println("1-Ürün Listele");
            System.out.println("2-ID veya Markaya göre ürünleri listele");
            System.out.println("3-Ürün Ekle");
            System.out.println("4-Ürün Sil");
            System.out.println("0-Geri");
            System.out.println("--------------------------------------------------------------");

            System.out.print("Yapmak istediğiniz işlemi seçiniz: ");
            int selectCase = inp.nextInt();
            while (!(selectCase>=0 && selectCase<5)){
                System.out.print("Yanlış işlem numarası girdiniz.\nTekrar numara giriniz:");
                selectCase = inp.nextInt();
            }

            switch (selectCase) {

                case 0:
                    isContinue=false;
                break;

                case 1:
                    this.print();
                break;

                case 2:
                    this.filtering();
                break;

                case 3:
                    this.addElement();
                 break;

                case 4:
                    this.deleteElement();
                    break;
             }
        }
    }
    @Override
    public void addElement() {
        if (this.userSelectGroup() == 1) {

            System.out.print("Ürünün Adı: ");
            String name = inp.next();

            System.out.print("Fiyatı: ");
            double price = inp.nextDouble();

            this.brands.userEntriedBrandList();
            System.out.print("Marka seçeneklerimiz listelenmiştir, seçiniz: ");
            int br = inp.nextInt();
            while (!(br > 0 && br < 10)) {
                System.out.print("\nYanlış değer girdiniz.\nSeçtiğiniz markanın numarasını giriniz: ");
                br = inp.nextInt();
            }

            String brand = "";
            switch (br) {
                case 1:
                    brand = "Apple";
                    break;

                case 2:
                    brand = "Asus";
                    break;

                case 3:
                    brand = "Casper";
                    break;

                case 4:
                    brand = "HP";
                    break;

                case 5:
                    brand = "Huawei";
                    break;

                case 6:
                    brand = "Lenovo";
                    break;

                case 7:
                    brand = "Monster";
                    break;

                case 8:
                    brand = "Samsung";
                    break;

                case 9:
                    brand = "Xiaomi";
                    break;
            }

            System.out.print("Hafızası (64 / 128) : ");
            int mem = inp.nextInt();
            while (!(mem == 64 || mem == 128)) {
                System.out.print("\nYanlış değer girdiniz.\n64 GB veya 128 GB seçeneklerinden birini seçiniz: ");
                mem = inp.nextInt();
            }

            System.out.print("Ekran Ölçüsü: ");
            double size = inp.nextDouble();

            System.out.print("Kamera Çözünürlüğü: ");
            int cam = inp.nextInt();

            System.out.print("Batarya Gücü: ");
            double bPow = inp.nextDouble();

            System.out.print("Ram Boyutu: ");
            int ram = inp.nextInt();

            System.out.print("Rengi (Siyah / Kırmızı / Mavi): ");
            String colour = inp.next();
            colour = colour.substring(0, 1).toUpperCase() + colour.substring(1).toLowerCase(); // İlk harf büyük yapıldı.
            while (!(colour.equals("Siyah") || colour.equals("Kırmızı") || colour.equals("Mavi"))) {
                System.out.print("\nYalnızca 3 renk mevcuttur.\nTekrar seçim yapınız: ");
                colour = inp.next();
                colour = colour.substring(0, 1).toUpperCase() + colour.substring(1).toLowerCase();
            }

            System.out.print("İndirim Oranı:");
            int dRate = inp.nextInt();

            System.out.print("Stok Adeti:");
            int stock = inp.nextInt();

            MobilePhone addedPhone = new MobilePhone(name, price, brand, mem, size, cam, bPow, ram, colour, dRate, stock);
            phones.add(addedPhone);
            System.out.println("***********ÜRÜN EKLEME BAŞARILI!***********");
            this.print();

        }
        else {
                System.out.println("Notebook ürünlerine ekleme yapmak için 'Notebook Panelinde' olmanız gerekir.\nPatika Ürün Yönetimi Paneline Yönlendiriliyorsunuz...\n ");
                isContinue = false;

        }
    }

    @Override
    public void deleteElement() {
        System.out.print("Silmek istediğiniz ID numarasını giriniz: ");
        int entryId=inp.nextInt();
        boolean isThere=false;
        for (int i=0;i<phones.size();i++){
            if (i==entryId-1){
                phones.remove(i);
                isThere=true; // girilen id listede varsa 1 yoksa 0 olacak
            }

        }
        if (!isThere){
            System.out.println("Mağazamızda bu ID'ye sahip ürün bulunmamaktadır...");
        }
        System.out.println("***********ÜRÜN SİLME İŞLEMİ BAŞARILI!***********");
        this.print();

    }
    @Override
    public void filtering(){
        System.out.print("1- ID'ye göre listele\n2- Marka'ya göre listele\nFiltreleme yapmak istediğiniz işlemi seçiniz:");
        int selectCase=inp.nextInt();
        LinkedList<MobilePhone> filteredPhone=new LinkedList<>();

        if (selectCase==1) {
            System.out.print("Kaç adet idye sahip ürün listelemek istersiniz?: ");
            int n = inp.nextInt();

            while (n > phones.size()) {  //
                System.out.println("Mağazada o kadar id'ye sahip ürün bulunmamaktadır.\nTekrar değer giriniz:");
                n = inp.nextInt();
            }

            for (int i = 0; i < n; i++) {
                System.out.print(i + 1 + ". ID numarasını giriniz:");
                int entryID = inp.nextInt();

                for (MobilePhone p : phones) {
                    if (p.getId() == entryID) {
                        filteredPhone.add(p); // phones listesindeki istenen id yi filteredPhones listesine ekledik.
                    }
                }
            }
            //Filteleme işlemi yapıldı.
            System.out.println("Mağazada olan idlerin ürünleri:\n");

            System.out.format("%-6s %-23s %-12s %-14s %-12s %-9s %-10s %-9s %-9s %-12s %-17s %-15s\n", "| ID  ", "| ÜRÜN ADI             ", "| FİYAT   ", "| MARKA     ", "| DEPOLAMA  ", "| EKRAN  ", "| KAMERA  ", "| PİL    ", "| RAM    ", "| RENK      ", "| İNDİRİM ORANI  ", "| STOK SAYISI |");
            for (MobilePhone p : filteredPhone) {
                System.out.format("%-6s %-23s %-12s %-14s %-12s %-9s %-10s %-9s %-9s %-12s %-17s %-15s\n", "|" + p.getId() , "|"+p.getProductName(), "|" + p.getUnitPrice() + " TL","|"+ p.getBrand(),"|"+p.getMemory() ,"|"+p.getScreenSize(),"|"+ p.getCamera() ,"|"+ p.getBatteryPower() , "|"+p.getRam() ,"|"+ p.getColour(),"|"+ p.getDiscountRate() ,"|"+ p.getStockNumber() + "|");

            }

        } else if(selectCase==2) {
            System.out.print("Kaç adet markaya sahip ürün listelemek istersiniz?: ");
            int n = inp.nextInt();
            while (n > phones.size()) {  //
                System.out.println("Mağazada o kadar markaya sahip ürün bulunmamaktadır.\nTekrar değer giriniz:");
                n = inp.nextInt();
            }

            for (int i = 0; i < n; i++) {
                System.out.print(i + 1 + ". markanın ismini giriniz:");
                String entryBrand = inp.next();
                entryBrand = entryBrand.substring(0, 1).toUpperCase() + entryBrand.substring(1).toLowerCase();


                for (MobilePhone m : phones) {

                    if (m.getBrand().equals(entryBrand)) {
                        filteredPhone.add(m);
                    }
                }
            }
            System.out.println("Mağazada olan markaların ürünleri:\n");

            System.out.format("%-6s %-23s %-12s %-14s %-12s %-9s %-10s %-9s %-9s %-12s %-17s %-15s\n", "| ID  ", "| ÜRÜN ADI             ", "| FİYAT   ", "| MARKA     ", "| DEPOLAMA  ", "| EKRAN  ", "| KAMERA  ", "| PİL    ", "| RAM    ", "| RENK      ", "| İNDİRİM ORANI  ", "| STOK SAYISI |");
            for (MobilePhone p : filteredPhone) {
                System.out.format("%-6s %-23s %-12s %-14s %-12s %-9s %-10s %-9s %-9s %-12s %-17s %-15s\n", "|" + p.getId() , "|"+p.getProductName(), "|" + p.getUnitPrice() + " TL","|"+ p.getBrand(),"|"+p.getMemory() ,"|"+p.getScreenSize(),"|"+ p.getCamera() ,"|"+ p.getBatteryPower() , "|"+p.getRam() ,"|"+ p.getColour(),"|"+ p.getDiscountRate() ,"|"+ p.getStockNumber() + "|");


            }
        }
        else {
            while(!(selectCase==1 || selectCase==2)){
                System.out.println("Yalnızca id veya marka bilgilerine göre filtreleme yapılmaktadır.\n Tekrar giriniz: ");
                selectCase= inp.nextInt();
            }
        }

    }

    @Override
    public void firstProductList() {

        phones.add(new MobilePhone("SAMSUNG GALAXY A51", 3199.0, "Samsung", 128, 6.5, 32, 4000.0, 6, "Siyah", 10, 100));
        phones.add(new MobilePhone("Iphone 11 64 GB", 7379.0, "Apple", 64, 6.1, 5, 3046.0, 6, "Mavi", 20, 200));
        phones.add(new MobilePhone("Redmi Note 10 Pro 8GB", 4012.0, "Xiaomi ", 128, 6.5, 35, 4000.0, 12, "Beyaz", 30, 300));

    }


    @Override
    public void print() {

        System.out.println("\nÜRÜNLER:");
        System.out.println("----------------------------------------------------------------------------------------------------------------------------------------------------------------------");
        System.out.format("%-6s %-23s %-12s %-14s %-12s %-9s %-10s %-9s %-9s %-12s %-17s %-15s\n", "| ID  ", "| ÜRÜN ADI             ", "| FİYAT   ", "| MARKA     ", "| DEPOLAMA  ", "| EKRAN  ", "| KAMERA  ", "| PİL    ", "| RAM    ", "| RENK      ", "| İNDİRİM ORANI  ", "| STOK SAYISI |");
        for (MobilePhone p : phones) {
            System.out.format("%-6s %-23s %-12s %-14s %-12s %-9s %-10s %-9s %-9s %-12s %-17s %-15s\n", "|" + p.getId() , "|"+p.getProductName(), "|" + p.getUnitPrice() + " TL","|"+ p.getBrand(),"|"+p.getMemory() ,"|"+p.getScreenSize(),"|"+ p.getCamera() ,"|"+ p.getBatteryPower() , "|"+p.getRam() ,"|"+ p.getColour(),"|"+ p.getDiscountRate() ,"|"+ p.getStockNumber() + "|");
        }
        System.out.println("----------------------------------------------------------------------------------------------------------------------------------------------------------------------");
    }


    public double getBatteryPower() {
        return batteryPower;
    }



    public String getColour() {
        return colour;
    }



    public int getCamera() {
        return camera;
    }





}
