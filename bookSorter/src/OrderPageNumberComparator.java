import java.util.Comparator;

public class OrderPageNumberComparator implements Comparator<Book> { // Comparator <Book> interfacesini burada implement edip
                                                                        // compare metodunu Override ediyoruz.
                                                                        // Main Classta TreeSet'in kurucu metoduna OrderPageNumberComparator sınıfını göndereceğiz.
    @Override
    public int compare(Book o1, Book o2) {
        return o1.getPageNumber()-o2.getPageNumber();
    }
}
