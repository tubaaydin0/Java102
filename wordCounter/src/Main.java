import java.util.HashMap;
import java.util.Scanner;
//Kullanıcı tarafından girilen bir metinde en çok geçen kelimeyi bulup ekrana yazdıran bir Java projesi yazınız.
public class Main {
    public static void main(String[] args) {
        Scanner inp = new Scanner(System.in);
        System.out.println("Bir cümle yazınız: ");
        String sentence = inp.nextLine();
        String[] words=sentence.trim().toLowerCase().split(" ");
        //trim metodu, metnin sonunda ve başında yer alan boşlukları yok ederken kelime aralarındaki boşluklara dokunmaz.


        HashMap<String,Integer> hMap=new HashMap<>();
        //   key:words[i] value: count
        for (int i=0; i< words.length;i++){
            Integer count=hMap.get(words[i]);// get(key) metodu kullanıldı. count= words[i] key'inin bulunduğu count değer
            if (hMap.containsKey(words[i])){ // containsKey(key) metodu kullanıldı. İçindeki key varsa true yoksa false döndürür.
                count++;
                hMap.put(words[i],count);
            }
            else{
                count=1;
                hMap.put(words[i],count);
            }

        }
        System.out.println("----------------------------------------");
        for (String  s: hMap.keySet()){
            System.out.println(s+" "+hMap.get(s));

        }

        Integer mostWordNumber=0; // En çok kelime değeri
        String mostWordName=null; // En çok kelime adı

        // Örnek Cümle: sen ben  ben sen biz herşeyiz sen benken
        // biz:1,benken:1 ,herşeyiz:1 , ben:2, sen:3

        for (String wordName: hMap.keySet()){
            if (hMap.get(wordName)>mostWordNumber){
                mostWordNumber=hMap.get(wordName);//1,-,-,2,3
                mostWordName=wordName;//biz,(benken döngüye giremedi.),(herşeyiz döngüye giremedi.), ben, sen
            }
        }
        // Döngünün çıktısı  mostWordNumber=3, mostWordName=sen
        System.out.println("Yazılan cümlede en çok geçen kelime: "+mostWordName+" Sayısı: "+ mostWordNumber);




    }
}
