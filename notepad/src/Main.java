import java.io.*;
import java.util.Scanner;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    public static void main(String[] args) {
        File file = new File("notlar.txt");
        if (!(file.exists())) { // Dosya yoksa oluşturuldu.
            try {
                file.createNewFile();
                System.out.println("------------------------------------------"+file+" dosyası oluşturuldu------------------------------------------");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        } else { // Dosya Varsa
            System.out.println("------------------------------------------"+file+" dosyası açıldı------------------------------------------");
            getFileReader(file); // Okuma işlemi yapan metot çağrıldı.
        }
        getPrintWriter(file);// Yazdırma işlemi yapan metot çağrıldı.
    }
    public static void getFileReader(File file){ // Filedan nesne alan getFileReader fonk.
        try {
            FileReader fReader=new FileReader(file);
            BufferedReader bReader=new BufferedReader(fReader);
            String line;
            while ((line= bReader.readLine())!=null){
                System.out.println(line);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void getPrintWriter(File file) {
        Scanner inp = new Scanner(System.in);
        System.out.println("Bir metin yazınız: ");
        String words = inp.nextLine();
        try {
            FileWriter fWriter = new FileWriter(file,true); // true diyerek önceki yazılanlar silinmeden yanına yeni yazı eklenir.
            PrintWriter pWriter = new PrintWriter(fWriter);
            pWriter.print(words+" ");

            pWriter.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }


}
