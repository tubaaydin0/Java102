import java.util.Scanner;
public class PatikaStore {
    MobilePhone mobilePhone;
    Notebook notebook;
    Brands brands;


    public void run() {
        boolean isStart = true;
        while (isStart) {
            System.out.println("---------------------PATİKA ÜRÜN YÖNETİM PANELİNE HOŞGELDİNİZ!---------------------");
            System.out.println("1-Notebook İşlemleri\n2-Cep Telefonu İşlemleri\n3-Marka Listele\n0-Çıkış yap");
            System.out.println("-----------------------------------------------------------------------------------");
            Scanner inp = new Scanner(System.in);
            System.out.print("Tercihinize göre işlem numarasını giriniz: ");
            int select = inp.nextInt();
            while (!(select>=0 && select<4)){
                System.out.print("Yanlış işlem numarası girdiniz.\nTekrar numara giriniz:");
                select = inp.nextInt();
            }
            switch (select) {
                case 1:

                    notebook = new Notebook();
                    notebook.menu();
                    break;

                case 2:

                    mobilePhone = new MobilePhone(); // mobilePhonedan yeni liste luşturup phonelist e ulaşıldı.
                    mobilePhone.menu();

                    break;

                case 3:
                    brands = new Brands();
                    brands.printBrandList();

                    break;

                case 0:
                    System.out.println("Hoşçakalın!");
                    isStart = false;
                    break;


            }

        }
    }

    

}
