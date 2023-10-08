
import java.util.Scanner;

public class Game {
    Player player; // player sınıfına erişildi
    Location location; // Location sınıfına erişildi

    Scanner inp=new Scanner(System.in);
    public  void start(){
        System.out.println("Merhaba yabancı, macera oyununa hoş geldin!!");
        System.out.print("Hazırsan İsminizi yazarak başla!: ");
        String playerName="tuba";
        Player player=new Player(playerName);
        System.out.println(player.getpName()+"\nMacera oyununa tekrar hoş geldin, Tehlikelere karşı çok dikkatli olmalısın. BOL ŞANS...");
        player.selectChar();

        Location location=null; //Location bilgisi başlangıçta null olarak belirlendi.
        while(true){
            System.out.println();
            player.printInfo();
            System.out.println();
            System.out.println("--------------------------------------BÖLGELER--------------------------------");
            System.out.println();
            System.out.println("1. Güvenli Ev       ----> Sizin için güvenli bir yer, içeride düşman yok!");
            System.out.println("2. Eşya Dükkanı     ----> Silah ve zırh satın alabilirsiniz.");
            System.out.println("3. Mağara           ----> Ödül <Yemek> Dikkatli ol, karşına zombi çıkabilir.");
            System.out.println("4. Orman            ----> Ödül <Odun> Dikkatli ol, karşına vampir çıkabilir.");
            System.out.println("5. Nehir            ----> Ödül <Su> Dikkatli ol, karşına ayı çıkabilir.");
            System.out.println("6. Nehir            ----> Ödül <Para, Silah veya Zırh> Dikkatli ol, karşına yılan çıkabilir.");
            System.out.println("0. Çıkış            ----> Oyundan çıkabilirsiniz.");
            System.out.print("Lütfen gitmek istediğiniz bölgeyi seçiniz: ");
            int selectLoc=inp.nextInt();

            switch (selectLoc){
                case 0:
                    location=null;
                    break;
                case 1: // burada ödülü alan yere girmesin yapmak istiyorum?????????????????????
                    location =new SafeHouse(player);
                    break;

                case 2:
                    location=new ToolStore(player);
                    break;
                case 3:

                    location=new Cave(player);
                    break;

                case 4:
                    location=new Forest(player);
                    break;
                case 5:
                    location=new River(player);
                    break;
                case 6:
                    location=new Mine(player);
                    break;
                default:
                    System.out.println("Lütfen geçerli bir bölge giriniz.");
            }
            if (location==null){
                System.out.println("Oyundan çabuk vazgeçtiniz, yine bekleriz...");
                break;
            }
            if (!(location.onLocation())){
                System.out.println("GAME OVER...");
                break;
            }
            if (player.isWin()){
                System.out.println("Yine oynamak istersen uğra!! ;)");
                System.out.println("################################");
                break;
            }



        }
    }
}
