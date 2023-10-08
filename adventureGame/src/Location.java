import java.util.Scanner;

public abstract class Location {
    Player player; // oyuncu sınıfını dahil ediyoruz.
    String locName; // Lokasyon adı
    Scanner inp=new Scanner(System.in);
    public Location(Player player, String locName){
        this.player=player;
        this.locName=locName;
    }
    public abstract boolean onLocation(); // onLocation metotu tüm alt sınıflarda olmak zorunda bu yüzden abstract olarak tanımladık.
    public Player getPlayer(){
        return this.player;
    }
    public void setPlayer(Player player){
        this.player=player;
    }
    public String getLocName(){
        return  this.locName;
    }
    public void setLocName(String locName){
        this.locName=locName;
    }


}
