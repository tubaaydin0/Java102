import java.io.*;
public class Main {
    public static void main(String[] args)  {
        //sayilar isimli txt dosyası oluşturulup içine istenen sayılar eklenmiştir.
        String path="C:\\Users\\Yaomawk\\java102\\readFile/sayilar.txt";
        File file=new File(path);
/*      try {
            if (!(file.exists())){
                file.createNewFile();
            }
            FileWriter fWriter = new FileWriter(file);
            BufferedWriter bWriter=new BufferedWriter(fWriter);
            int[] sayilar={5,10,20,12,33};
            for (int i:sayilar){
                bWriter.write(i+"\n");
            }
            bWriter.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
*/
        // Dosyadaki sayıları okuyup toplamını ekrana yazdırma.
        try {
            FileReader fReader=new FileReader(file);
            BufferedReader bReader=new BufferedReader(fReader);
            String line;
            int sum=0;
            while ((line=bReader.readLine())!=null){
                sum+=Integer.parseInt(line);
            }
            System.out.println(sum);
            bReader.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


    }
}