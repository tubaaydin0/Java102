import java.util.Scanner;

public class Player {
                            //Karakterin:
    private int id; // idsi,
    private String charName; // adı,
    private  int damage; // hasarı,
    private  int health; // canı
    private int originalHealth;
    private  int money; // parası
    private String pName;
    private Inventory inventory;
    private int totalDamage; // Toplam hasar
    private boolean isWin=false; // oyunu kazanma durumu


    Scanner inp=new Scanner(System.in);
    public Player(String pName) { // Dışarıdan sadece oyuncunun ismini aldığı için kurucu metota onu yazdık.
        this.pName=pName;
        this.inventory=new Inventory(); // Boş inventory tanımladık.
        this.originalHealth=health;


    }
    public void selectChar(){
        //GameCharacters sınıfından karakterleri tutan chars isimli bir dizi oluşturuldu.
        GameCharacters[] chars={new Samurai(),new Archer(),new Knight()};
        //karakter bilgileri listelendi.
        System.out.println("\nOYUNDAKİ KARAKTERLER: ");
        System.out.println("-------------------------------------------------------------------------------------------------------");
        for(GameCharacters i: chars){
            System.out.println("ID: "+i.getId()+
                    "\t\tKarakter:\t"+i.getCharName()+
                    "\t\tHasar:\t"+i.getDamage()+
                    "\t\tSağlık:\t"+i.getHealth()+
                    "\t\tPara:\t"+i.getMoney());
        }


        System.out.print("\nKarakterlerin id numaralarını dikkate alarak oynamak istediğin karakteri seç ve başla!:  ");
        int select=inp.nextInt();
        while(!(select==1) && !(select==2) && !(select==3)) {
            System.out.println("Yanlış seçim yaptın. Tekrar dene: "); // id dışında bir şey yazılırsa samurai  nesnesi varsayılan olarak çalışacak.
            select=inp.nextInt();
        }
        switch (select){
            case 1:
                initPlayer(new Samurai());
                break;
            case 2:
                initPlayer(new Archer());
                break;
            case 3:
                initPlayer(new Knight());
                break;

        }
        System.out.println("\nSEÇTİĞİN KARAKTERİN ÖZELLİKLERİ: ");
        System.out.println("-------------------------------------------------------------------------------------------------------");
        System.out.println("Seçilen karakter: "+this.getCharName()+
                "\t\tHasar: "+this.getDamage()+
                "\t\tSağlık: "+this.getHealth()+
                "\t\tPara: "+this.getMoney());

    }
    public void initPlayer(GameCharacters gameCharacters){ // Gönderilen karakter nesnesine göre bu sınıftaki değişkenler seçilen karakterin değerleriyle güncelleniyor.
        this.setCharName(gameCharacters.getCharName()); // player sınıfındaki charNameyi gameCharacter sınıfındaki CharName ile değiştiriyoruz.
        this.setDamage(gameCharacters.getDamage());
        this.setHealth(gameCharacters.getHealth());
        this.setOriginalHealth((gameCharacters.getHealth()));// karakterin başlangıçtaki can değeri orijinal can değerine atandı.
        this.setMoney(gameCharacters.getMoney());

    }
    public void printInfo(){
        System.out.println("Silahınız: "+this.getInventory().getWeapon().getwName()+
                "\t\tZırhınız: "+this.getInventory().getArmor().getaName()+
                "\t\tBloklama: "+this.getInventory().getArmor().getaBlock()+
                "\t\tHasarınız: "+this.getTotalDamage()+
                "\t\tSağlığı: "+this.getHealth()+
                "\t\tPara: "+this.getMoney());

    }

    public String getpName() {
        return pName;
    }

    public void setpName(String pName) {
        this.pName = pName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCharName() {
        return charName;
    }

    public void setCharName(String charName) {
        this.charName = charName;
    }

    public int getTotalDamage() {
        return damage+this.getInventory().getWeapon().getwDamage();// karakterin hasarı+ silahın hasarı
    }

    public void setTotalDamage(int totalDamage) {
        this.totalDamage = totalDamage;
    }

    public int getDamage() {

        return this.damage;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {

        if(health<0){
            health=0;
        }
        this.health = health;
    }

    public int getOriginalHealth() {
        return originalHealth;
    }

    public void setOriginalHealth(int originalHealth) {
        this.originalHealth = originalHealth;
    }

    public int getMoney() {
        return money;
    }

    public void setMoney(int money) {
        this.money = money;
    }

    public Inventory getInventory() {
        return inventory;
    }

    public void setInventory(Inventory inventory) {
        this.inventory = inventory;
    }

    public boolean isWin() {
        return isWin;
    }

    public void setWin(boolean win) {
        isWin = win;
    }
}
