import java.util.Comparator;
import java.util.TreeSet;
import java.util.Iterator;

public class Main  {
    //Kitap Sıralayıcı
    public static void main(String[] args) {
        Book book1=new Book("Gör Beni", 220,"Azra Kohen",2019);
        Book book2=new Book ("Gece Yarısı Kütüphanesi",195,"Matt Haig",2021);
        Book book3=new Book("Hayvan Çiftliği",152,"George Orwell",1970);
        Book book4=new Book("Simyacı",188,"Paulo Coelho",2010);
        Book book5=new Book("Kürk Mantolu Madonna",160,"Sabahattin Ali",1998);


        //Comparable interfacesini Book sınıfında implement ettiğimiz için new TreeSet<>(...) 'in içini doldurmaya gerek kalmıyor.
        // Yani bir Comparator constroctorı vermeye gerek kalmadı. Olduğu gibi çağırıyoruz.
        TreeSet<Book> books1=new TreeSet<>();
        // Book sınıfından book isimli bir treeset nesnesi oluşturduk.

        //Oluşturduğumuz 5 adet kitap nesnelerini treesete ekliyoruz.
        books1.add(book1);
        books1.add(book2);
        books1.add(book3);
        books1.add(book4);
        books1.add(book5);


        System.out.println("Baş harfine göre sıralandı: ");
       for (Book b: books1){
            System.out.println("Kitabın adı: "+b.getBookName()+
                    "\nSayfa Sayısı: "+b.getPageNumber()+
                    "\nYazarı: "+b.getAuthor()+
                    "\nYayın Yılı: "+b.getReleaseDate());
            System.out.println("**********************************************************************************");
        }


        //Kitapları sayfa numarasına göre sıralama YÖNTEM 1:
        // Bu yöntemde TreeSetin kurucu metotuna   book tipinde Comparator  gönderiyoruz.
       TreeSet<Book> books2=new TreeSet<>(new Comparator<Book>() { // Sonrasında anonim class oluşturuyoruz ve
                                                                    // içine Comparatordan compare metodunu Override ediyoruz.
            @Override
            public int compare(Book o1, Book o2) {
                return o1.getPageNumber()-o2.getPageNumber();
            }
        });
       // book2 TreeSet ine  Book classtan oluşturduğumuz nesneleri ekliyoruz.
        books2.add(book1);
        books2.add(book2);
        books2.add(book3);
        books2.add(book4);
        books2.add(book5);
        System.out.println("Sayfa sayısına göre sıralandı: ");
        for (Book b: books2) {
            System.out.println("Kitabın adı: " + b.getBookName() +
                    "\nSayfa Sayısı: " + b.getPageNumber() +
                    "\nYazarı: " + b.getAuthor() +
                    "\nYayın Yılı: " + b.getReleaseDate());
            System.out.println("**********************************************************************************");
        }


        /*
        //Kitapları sayfa numarasına göre sıralama YÖNTEM 2:
        //OrderPageNumberComparator sınıfı oluşturmuştuk. O sınıfta Comparator interfacesini implement edip
        // burada da treesetin kurucu metoduna OrderPageNumberComparator sınıfını gönderiyoruz.
        TreeSet<Book> books3=new TreeSet<>(new OrderPageNumberComparator());

        books3.add(book1);
        books3.add(book2);
        books3.add(book3);
        books3.add(book4);
        books3.add(book5);
        System.out.println("Sayfa sırasına göre sıralandı: ");
        for (Book b: books2) {
            System.out.println("Kitabın adı: " + b.getBookName() +
                    "\nSayfa Sayısı: " + b.getPageNumber() +
                    "\nYazarı: " + b.getAuthor() +
                    "\nYayın Yılı: " + b.getReleaseDate());
            System.out.println("**********************************************************************************");
        }
*/



    }
}