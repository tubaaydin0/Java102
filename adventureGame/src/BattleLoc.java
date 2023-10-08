import java.util.Random;

public abstract class BattleLoc extends  Location {//Savaş Alanı

    private String award;// ödül
    private Obstacle obstacle;
    private int maxObstacle; // Savaş alanında max kaç adet canavar olacağını belirtir.
   // private boolean isGetAward;
    private int startPlayer;
    private int startObstacle;
    Random rnd=new Random();
    public BattleLoc(Player player,String locName,Obstacle obstacle,String award,int maxObstacle){
        super(player,locName);
        this.obstacle=obstacle;
        this.award=award;
        this.maxObstacle=maxObstacle;
        this.startPlayer=50; // oyuna başlama olasılığı yüzde 50 olsun diye ikisini de 50 yaptım.
        this.startObstacle=50;
    }

    @Override
    public boolean onLocation() {
        // Ödül alınan bölgeye tekrar girmesi engellendi.
        if ((this.getLocName()=="Mağara" && this.getPlayer().getInventory().isFood()==false) ||(this.getLocName()=="Orman" && this.getPlayer().getInventory().isFirewood()==false) || (this.getLocName()=="Nehir" && this.getPlayer().getInventory().isWater()==false) ||this.getPlayer().getInventory().isRandomAward()==false)
        {
            int obsNumber = this.randomObstacleNumber();
            System.out.println("\nŞu an buradasın: " + this.getLocName());
            System.out.println("Dikkatli ol! Burada " + obsNumber + " tane " + this.getObstacle().getoName() + " yaşıyor!");
            System.out.println("\n<S>avaş veya <K>aç , Tercihine göre istediğin adımın baş harfini yaz: ");
            String selectCase = inp.nextLine().toUpperCase();// Girilen harf küçükse büyük harfe çevrildi.
            while(!(selectCase.equals("S")) && !(selectCase.equals("K"))){
                System.out.println("Yanlış değer girdin. Tekrar dene: ");
                selectCase = inp.nextLine().toUpperCase();

            }

            if (selectCase.equals("S")) {
                // System.out.println("Savaş işlemleri yapılacak.");

                if (combat(obsNumber)) { // combat true ise oyuncu yaşıyor demektir
                    System.out.println();
                    System.out.println("########################################################");
                    System.out.println("Tebrikler," + this.getLocName() + " bölgesinde tüm düşmanları yendin!!!");
                    System.out.println("########################################################");

                    //Ödüller oyuncunun envanterine ekleniyor
                    if (this.award.equals("Food") && this.getPlayer().getInventory().isFood() == false) { // ödül food a eşit ve kullanıcının isfood u false ise oyuncunun envanterindeki food u true yaptık.
                        System.out.println(this.award + " ödülünü kazandın!");
                        System.out.println("########################################################");
                        this.getPlayer().getInventory().setFood(true);

                    } else if (this.award.equals("Firewood") && this.getPlayer().getInventory().isFirewood() == false) {
                        System.out.println(this.award + " ödülünü kazandın!");
                        System.out.println("########################################################");
                        this.getPlayer().getInventory().setFirewood(true);

                    } else if (this.award.equals("Water") && this.getPlayer().getInventory().isWater() == false) {
                        System.out.println(this.award + " ödülünü kazandın!");
                        System.out.println("########################################################");
                        this.getPlayer().getInventory().setWater(true);
                    }
                    else if (this.award.equals("Para, Silah veya Zırh") && this.getPlayer().getInventory().isRandomAward()==false){
                        System.out.println();
                        System.out.println("Yılanın parası yok ama Madenin sana özel bir süprizi olacak!\nPara, Silah veya Zırhtan birini kazanacaksın! Hadi ne kadar şanslısın görelim!.. :)");
                        this.whatEarnFromMine();
                        this.getPlayer().getInventory().setRandomAward(true);
                    }
                }else{ // combat metodu false olduğunda
                    if (this.getPlayer().getHealth() <= 0) {
                        System.out.println();
                        System.out.println("Öldün :( ");
                        return false; // Game sınıfında onLocation metotu false döner ekrana game over mesajı verir.
                    }
                    return true; // oyuncu ölmezse true döndürür.
                }
            }
            else if (selectCase.equals("K")){ // true olduğundan game classta döngü true olur yani tekra çalışır.
                    return true;
            }


        }else{ // Oyuncu bölgede ödül kazandıysa
            System.out.println();
            System.out.println("Ödül kazandığın bölgede tekrar savaşamazsın!!!");
        }

        return true; // buraya kadar false döndürmediyse oyuncu yaşıyor.
    }

    public boolean combat(int obsNumber) {

            for (int i = 1; i <= obsNumber; i++) {
                this.getObstacle().setoHealth(this.getObstacle().getOriginalHealth());// Döngü her başa döndüğünde canavarın canını orijinal can değeriyle güncelleyince istenen sayıda canavarla her defasına savaşılmış oldu.
                System.out.println();
                System.out.println("---------------------------------------------"+i+". ROUND--------------------------------------------------");
                this.playerStats();
                this.obstacleStats(i);
                System.out.println("-------------------------------------------------------------------------------------------------------");


                    if (isStart()) { //İlk atağa geçen oyuncu olursa
                        System.out.println();
                        System.out.println("İlk vuran: " + this.getPlayer().getCharName());
                        System.out.println();

                        while (this.getPlayer().getHealth() > 0 && this.getObstacle().getoHealth() > 0) {
                        System.out.print("<V>ur / <K>aç ");
                        String selectCombat = inp.nextLine().toUpperCase(); // Girilen harf küçükse büyük harfe çevrildi.
                        while (!(selectCombat.equals("V")) && !(selectCombat.equals("K"))) {
                            System.out.println("Yanlış değer girdin, tekrar dene:");
                            selectCombat = inp.nextLine().toUpperCase();

                        }
                        if (selectCombat.equals("V")) { // kullanıcı vur seçeneğini seçerse
                            System.out.println("\nSiz vurdunuz!");
                            System.out.println("-------------------------------------------------------------------------------------------------------");
                            //Canavarın canından oyuncunun toplam hasarını çıkartırız.
                            this.getObstacle().setoHealth(this.getObstacle().getoHealth() - this.getPlayer().getTotalDamage());
                            afterHit();

                            if (this.getObstacle().getoHealth() > 0) {
                                System.out.println("\n" + this.getObstacle().getoName() + " size vurdu...");
                                System.out.println("-------------------------------------------------------------------------------------------------------");
                                int obstacleDamage = this.obstacle.getoDamage() - this.getPlayer().getInventory().getArmor().getaBlock(); // Canavarın hasarı= canavar hasarı-oyuncunun zırhı
                                if (obstacleDamage < 0) { // Eğer canavarın hasarı - olursa hiç hasarı yok demektir. Bu nedenle 0 a eşitliyoruz.
                                    obstacleDamage = 0;
                                }
                                this.getPlayer().setHealth(this.getPlayer().getHealth() - obstacleDamage);// oyuncunun canından canavarın etki edeceği hasarı çıkarıyoruz.
                                afterHit();
                            }
                        } else if (selectCombat.equals("K")) { // Kullanıcı Kaç seçeneğini seçerse.
                            return false;
                        }

                        }

                    } else { // ilk atağa geçen Canavar olursa
                        System.out.println();
                        System.out.println("İlk vuran: " + this.getObstacle().getoName());
                        System.out.println();
                        while (this.getPlayer().getHealth() > 0 && this.getObstacle().getoHealth() > 0) {

                        System.out.println("\n"+this.getObstacle().getoName()+" size vurdu!");
                        System.out.println("-------------------------------------------------------------------------------------------------------");
                        int obstacleDamage = this.obstacle.getoDamage() - this.getPlayer().getInventory().getArmor().getaBlock(); // Canavarın hasarı= canavar hasarı-oyuncunun zırhı
                        if (obstacleDamage < 0) { // Eğer canavarın hasarı - olursa hiç hasarı yok demektir. Bu nedenle 0 a eşitliyoruz.
                            obstacleDamage = 0;
                        }
                        this.getPlayer().setHealth(this.getPlayer().getHealth() - obstacleDamage);// oyuncunun canından canavarın etki edeceği hasarı çıkarıyoruz.
                        afterHit();

                        if (this.getPlayer().getHealth() > 0) {
                            System.out.print("<V>ur / <K>aç ");
                            String selectCombat = inp.nextLine().toUpperCase(); // Girilen harf küçükse büyük harfe çevrildi.
                            while (!(selectCombat.equals("V")) && !(selectCombat.equals("K"))) {
                                System.out.println("Yanlış değer girdin, tekrar dene:");
                                selectCombat = inp.nextLine().toUpperCase();

                            }
                            if (selectCombat.equals("V")) { // kullanıcı vur seçeneğini seçerse
                                System.out.println("\nSiz vurdunuz!");
                                System.out.println("-------------------------------------------------------------------------------------------------------");
                                //Canavarın canından oyuncunun toplam hasarını çıkartırız.
                                this.getObstacle().setoHealth(this.getObstacle().getoHealth() - this.getPlayer().getTotalDamage());
                                afterHit();
                            } else if (selectCombat.equals("K")) { // Kullanıcı Kaç seçeneğini seçerse 61. satır çalışacak.
                                return false;
                            }
                        }

                        }
                    }

                    //Döngüden çıkınca oyuncu ile canavarın canları karşılaştırılır. oyuncununki daha çoksa o yener.
                    if (this.getPlayer().getHealth() > this.getObstacle().getoHealth()) {
                        System.out.println();
                        System.out.println("Düşmanı yendin!");

                        if (this.getLocName().equals("Maden")){
                            return  true;
                        }else {
                            System.out.println(this.getObstacle().getMoneyAward() + " para kazandın! $-)");
                            this.getPlayer().setMoney(this.getPlayer().getMoney() + this.getObstacle().getMoneyAward());// oyuncunun parası kendi parası + canavardan gelen ödül şeklinde güncellendi.
                            System.out.println("Güncel Bakiyen: " + this.getPlayer().getMoney());
                        }
                    } else {
                        return false; // Oyuncu yenildiyse false döndürür.65. satır çalışır. Eğer oyuncunun canı 0 dan da küçükse öldün mesajı verilir.
                    }
            }
            if (this.getPlayer().getInventory().isFood() && this.getPlayer().getInventory().isWater() && this.getPlayer().getInventory().isFirewood()){
                System.out.println();
                System.out.println("Bölgelerdeki tüm ödülleri toplayarak görevleri tamamladın. Şimdi Güvenli Ev'e git! ");
            }

            return true;
    }

    public void whatEarnFromMine() {// Madenden kazanılan ödülü tutan metot
    int random1= rnd.nextInt(100);
    int random2;
    if (random1<15){ // Silah veya zırh kazanır
        //Silah kazanma olasılıkları
        random2=rnd.nextInt(100);
        if (random2<20){ // Tüfek
            System.out.println();
            System.out.println("Tebrikler, Tüfek kazandın!");
            this.getPlayer().getInventory().setWeapon(Weapon.getWeaponObjeById(3));
            System.out.println("######################");
            System.out.println("Yeni silahınız: "+ this.getPlayer().getInventory().getWeapon().getwName());
            System.out.println("######################");
        }
        else if(random2>=20 && random2<50){ // Kılıç
            System.out.println();
            System.out.println("Tebrikler, kılıç kazandın!");
            System.out.println();
            this.getPlayer().getInventory().setWeapon(Weapon.getWeaponObjeById(1));
            System.out.println("######################");
            System.out.println("Yeni silahınız: "+ this.getPlayer().getInventory().getWeapon().getwName());
            System.out.println("######################");

        }
        else{// Tabanca
            System.out.println();
            System.out.println("Tebrikler, tabanca kazandın!");
            System.out.println();
            this.getPlayer().getInventory().setWeapon(Weapon.getWeaponObjeById(2));
            System.out.println("######################");
            System.out.println("Yeni silahınız: "+ this.getPlayer().getInventory().getWeapon().getwName());
            System.out.println("######################");
        }

    } // Zırh kazanma olasılıkları
    else if(random1>=15 && random1<30){
        random2= rnd.nextInt(100);
        if (random2<20){ // Ağır zırh
            System.out.println();
            System.out.println("Tebrikler, Ağır zırh kazandın!");
            System.out.println();
            this.getPlayer().getInventory().setArmor(Armor.getArmorObjeByID(3));
            System.out.println("##########################");
            System.out.println("Yeni zırhınız: "+ this.getPlayer().getInventory().getArmor().getaName());
            System.out.println("##########################");
        }
        else if(random2>=20 && random2<50){ //Orta zırh
            System.out.println();
            System.out.println("Tebrikler, Orta zırh kazandın!");
            System.out.println();
            this.getPlayer().getInventory().setArmor(Armor.getArmorObjeByID(2));
            System.out.println("##########################");
            System.out.println("Yeni zırhınız: "+ this.getPlayer().getInventory().getArmor().getaName());
            System.out.println("##########################");

        }
        else{ // Hafif zırh
            System.out.println();
            System.out.println("Tebrikler, hafif zırh kazandın!");
            System.out.println();
            this.getPlayer().getInventory().setArmor(Armor.getArmorObjeByID(1));
            System.out.println("##########################");
            System.out.println("Yeni zırhınız: "+ this.getPlayer().getInventory().getArmor().getaName());
            System.out.println("##########################");
        }
    }
    // Para kazanma olasılığı
    else if (random1>=30 && random1<55){
        random2=rnd.nextInt(100);
        if (random2<20) { // 10 para
            System.out.println();
            System.out.println("Tebrikler, 10 para kazandın!");
            System.out.println();
            System.out.println("###################");
            System.out.println("Eski bakiyen: "+this.getPlayer().getMoney());
            this.getPlayer().setMoney(this.getPlayer().getMoney()+10);
            System.out.println("Yeni bakiyen: "+this.getPlayer().getMoney());
            System.out.println("###################");
        }
        else if (random2>=20 && random2<50){// 5 para
            System.out.println();
            System.out.println("Tebrikler, 5 para kazandın!");
            System.out.println();
            System.out.println("###################");
            System.out.println("Eski bakiyen: "+this.getPlayer().getMoney());
            this.getPlayer().setMoney(this.getPlayer().getMoney()+5);
            System.out.println("Yeni bakiyen: "+this.getPlayer().getMoney());
            System.out.println("###################");
        }
        else{ // 1 para
            System.out.println();
            System.out.println("Tebrikler, 1 para kazandın!");
            System.out.println();
            System.out.println("###################");
            System.out.println("Eski bakiyen: "+this.getPlayer().getMoney());
            this.getPlayer().setMoney(this.getPlayer().getMoney()+1);
            System.out.println("Yeni bakiyen: "+this.getPlayer().getMoney());
            System.out.println("###################");
        }

    }
    // Hiçbir şey kazanmama olasılığı
    else{
        System.out.println();
        System.out.println("#######################");
        System.out.println("Hiçbir şey kazanamadın!");
        System.out.println("#######################");
    }
    }

    public boolean isStart(){
        int rand=rnd.nextInt(100);
        if (rand<=this.getStartPlayer()){
            return true;
        }else if (rand<=this.getStartObstacle()){
            return false;
        }
        return false;
    }
    public void playerStats(){
        System.out.println();
        System.out.println("Seçilen Oyuncunun Değerleri:" +
                "\n-------------------------------------------------------------------------------------------------------"+
                "\nSağlık:\t"+this.getPlayer().getHealth()+
                "\t\tSilah:\t"+this.getPlayer().getInventory().getWeapon().getwName()+
                "\t\tHasar:\t"+this.getPlayer().getTotalDamage()+
                "\t\tZırh:\t"+this.getPlayer().getInventory().getArmor().getaName()+
                "\t\tBloklama:\t"+this.getPlayer().getInventory().getArmor().getaBlock()+
                "\t\tPara:\t"+this.getPlayer().getMoney());


    }
    public void obstacleStats(int i){
        System.out.println();
        System.out.println(i+". "+this.getObstacle().getoName()+" Değeri:" +
                "\n-------------------------------------------------------------------------------------------------------"+
                "\nSağlık:\t"+this.getObstacle().getoHealth()+
                "\t\tHasar:\t"+this.getObstacle().getoDamage()+
                "\t\tÖdül:\t"+this.getObstacle().getMoneyAward());

    }
    public void afterHit(){ // Oyuncu canavara ve canavar oyuncuya vurduktan sonraki değerler
        System.out.println(this.getPlayer().getCharName()+" Canı: "+this.getPlayer().getHealth()+"\n"+
                this.getObstacle().getoName()+" Canı: "+this.getObstacle().getoHealth());

    }
    // Kaç adet canavar oluşacak onu hesaplayan metot.
    public int randomObstacleNumber(){
        return rnd.nextInt(3)+1;// 0 ile 2 arasında değer türetirken +1 ile 1-3 arası değer üretecek.

    }
    public String getAward() {
        return award;
    }

    public void setAward(String award) {
        this.award = award;
    }

    public Obstacle getObstacle() {
        return obstacle;
    }

    public void setObstacle(Obstacle obstacle) {
        this.obstacle = obstacle;
    }

    public int getStartPlayer() {
        return startPlayer;
    }

    public void setStartPlayer(int startPlayer) {
        this.startPlayer = startPlayer;
    }

    public int getStartObstacle() {
        return startObstacle;
    }

    public void setStartObstacle(int startObstacle) {
        this.startObstacle = startObstacle;
    }
}
