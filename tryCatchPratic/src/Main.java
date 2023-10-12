import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        //: 10 elemanlı tek boyutlu bir dizi içerisinde kullanıcıdan alınan indeksteki elemanı döndüren bir Java metodu yazın.
        // Eğer belirtilen indeks dizi boyutunun dışındaysa, metot bir hata mesajı döndürmelidir.
        // Dizinin elmanlarını manuel olarak tanımlayın.
        int[] dizi = {2, 5, 7, 8, 6, 4, 1, 10, 15, 13};

        Scanner inp = new Scanner(System.in);
        System.out.println("Bir indis numarası giriniz: ");
        int indis = inp.nextInt();
        try {
            arrayList(dizi, indis);
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println(e.getMessage());
        }
    }
        public static void  arrayList(int[] dizi,int indis) throws ArrayIndexOutOfBoundsException {
            if (indis>dizi.length-1 || indis<0){
                throw new ArrayIndexOutOfBoundsException("Yazılan indis numarası dizi boyutunun dışındadır!");
            }
            else{
                System.out.println(dizi[indis]);
            }

        }
    }








