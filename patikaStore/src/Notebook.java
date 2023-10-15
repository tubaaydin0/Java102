import java.util.LinkedList;

public class Notebook extends  Products{
    private static int id=1;
    Brands brands=new Brands();
    LinkedList<Notebook> nBooks=new LinkedList<>();
    boolean isContinue=true;
    public Notebook( String productName, double unitPrice, String brand, int memory, double screenSize, int ram, int discountRate, int stockNumber) {
        super(id,productName, unitPrice, brand, memory, screenSize, ram, discountRate, stockNumber);
        id++;
    }

    public Notebook() {
    }

    @Override
    public void menu() {
        this.firstProductList(); // Listeye eklenen Ürünler
        while(isContinue){
            System.out.println();
            System.out.println("---------------------NOTEBOOK PANELİ---------------------");
            System.out.println("1-Ürün Listele");
            System.out.println("2-ID veya Markaya göre ürünleri listele");
            System.out.println("3-Ürün Ekle");
            System.out.println("4-Ürün Sil");
            System.out.println("0-Geri");
            System.out.println("---------------------------------------------------------");
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
        if (this.userSelectGroup() == 2) {
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


            System.out.print("Ram Boyutu: ");
            int ram = inp.nextInt();

            System.out.print("İndirim Oranı:");
            int dRate = inp.nextInt();

            System.out.print("Stok Adeti:");
            int stock = inp.nextInt();

           Notebook addednBook = new Notebook(name, price, brand, mem, size, ram, dRate, stock);
           nBooks.add(addednBook);
           System.out.println("***********ÜRÜN EKLEME İŞLEMİ BAŞARILI!***********");
        } else {
            System.out.println("Cep Telefonu ürünlerine ekleme yapmak için 'Cep Telefonu Panelinde' olmanız gerekir.\nPatika Ürün Yönetimi Paneline Yönlendiriliyorsunuz...\n ");
            isContinue = false;

        }


    }

    @Override
    public void deleteElement() {
        System.out.print("Silmek istediğiniz ID numarasını giriniz: ");
        int entryId=inp.nextInt();
        boolean isThere=false;
        for (int i=0;i<nBooks.size();i++){
            if (i==entryId-1){
               nBooks.remove(i);
                isThere=true; // girilen id listede varsa 1 yoksa 0 olacak
            }

        }
        if (isThere==false){
            System.out.println("Mağazamızda bu ID'ye sahip ürün bulunmamaktadır...");
        }
        System.out.println("***********ÜRÜN SİLME İŞLEMİ BAŞARILI!***********");
        this.print();



    }

    @Override
    public void filtering() {
        System.out.println("Filtreleme yapmak istediğiniz işlemi seçiniz:\n1- ID'ye göre listele\n2- Marka'ya göre listele");
        int selectCase = inp.nextInt();
        LinkedList<Notebook> filterednBook = new LinkedList<>();
        if (selectCase == 1) {
            System.out.print("Kaç farklı id listelemek istersiniz?: ");
            int n = inp.nextInt();
            while (n > nBooks.size()) {  //
                System.out.println("Mağazada o kadar id'ye sahip ürün bulunmamaktadır.\nTekrar değer giriniz:");
                n = inp.nextInt();
            }

            for (int i = 0; i < n; i++) {
                System.out.println(i + 1 + ". ID numarasını giriniz:");
                int entryID = inp.nextInt();

                for (Notebook b : nBooks) {
                    if (b.getId() == entryID) {
                        filterednBook.add(b);
                    }
                }
            }

            System.out.println("Mağazada olan idlerin ürünleri:\n");

            System.out.format("%-6s %-23s %-12s %-14s %-12s %-9s %-9s %-17s %-15s\n", "| ID  ", "| ÜRÜN ADI             ", "| FİYAT   ", "| MARKA     ", "| DEPOLAMA  ", "| EKRAN  ",  "| RAM   ", "| İNDİRİM ORANI  ", "| STOK SAYISI |");
            for (Notebook b : filterednBook) {
                    System.out.format("%-6s %-23s %-12s %-14s %-12s %-9s %-9s %-17s %-15s\n", "|" + b.getId() , "|"+b.getProductName(), "|" + b.getUnitPrice() + " TL","|"+ b.getBrand(),"|"+b.getMemory() ,"|"+b.getScreenSize(), "|"+b.getRam() ,"|"+ b.getDiscountRate() ,"|"+ b.getStockNumber() + "|");
                }


                }
        else if (selectCase == 2) {
            System.out.print("Kaç farklı marka listelemek istersiniz?: ");
            int n = inp.nextInt();
            while (n > nBooks.size()) {  //
                System.out.println("Mağazada o kadar markaya sahip ürün bulunmamaktadır.\nTekrar değer giriniz:");
                n = inp.nextInt();
            }

            for (int i = 0; i < n; i++) {
                System.out.print(i + 1 + ". markanın ismini giriniz:");
                String entryBrand = inp.next();
                entryBrand = entryBrand.substring(0, 1).toUpperCase() + entryBrand.substring(1).toLowerCase();


                for (Notebook b : nBooks) {
                    if (b.getBrand().equals(entryBrand)) {
                        filterednBook.add(b);

                    }
                }
            }
            System.out.println("Mağazada olan markaların ürünleri:\n");

            System.out.format("%-6s %-23s %-12s %-14s %-12s %-9s %-9s %-17s %-15s\n", "| ID  ", "| ÜRÜN ADI             ", "| FİYAT   ", "| MARKA     ", "| DEPOLAMA  ", "| EKRAN  ",  "| RAM   ", "| İNDİRİM ORANI  ", "| STOK SAYISI |");
            for (Notebook b : filterednBook) {
                System.out.format("%-6s %-23s %-12s %-14s %-12s %-9s %-9s %-17s %-15s\n", "|" + b.getId() , "|"+b.getProductName(), "|" + b.getUnitPrice() + " TL","|"+ b.getBrand(),"|"+b.getMemory() ,"|"+b.getScreenSize(), "|"+b.getRam() ,"|"+ b.getDiscountRate() ,"|"+ b.getStockNumber() + "|");
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
        nBooks.add(new Notebook("HUAWEI Matebook 14 ",7000.0,"Huawei",512,14.0,16,20,80));
        nBooks.add(new Notebook("LENOVO V14 IGL",3699.0,"Lenovo",1024,14.0,8,10,100));
        nBooks.add(new Notebook("ASUS Tuf Gaming ",8199.0 ,"Asus",2048,15.6,32,10,100));

    }

    @Override
    public void print() {
        System.out.println("\nÜRÜNLER:");
        System.out.println("--------------------------------------------------------------------------------------------------------------------------------------");
        System.out.format("%-6s %-23s %-12s %-14s %-12s %-9s %-9s %-17s %-15s\n", "| ID  ", "| ÜRÜN ADI             ", "| FİYAT   ", "| MARKA     ", "| DEPOLAMA  ", "| EKRAN  ",  "| RAM   ", "| İNDİRİM ORANI  ", "| STOK SAYISI |");
        for (Notebook p :nBooks) {
            System.out.format("%-6s %-23s %-12s %-14s %-12s %-9s %-9s %-17s %-15s\n", "|" + p.getId() , "|"+p.getProductName(), "|" + p.getUnitPrice() + " TL","|"+ p.getBrand(),"|"+p.getMemory() ,"|"+p.getScreenSize(), "|"+p.getRam() ,"|"+ p.getDiscountRate() ,"|"+ p.getStockNumber() + "|");
        }
        System.out.println("--------------------------------------------------------------------------------------------------------------------------------------");



    }





}
