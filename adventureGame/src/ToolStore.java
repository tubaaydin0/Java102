public class ToolStore extends NormalLocation{

    public ToolStore(Player player){
        super(player,"Mağaza");

    }
    @Override
    public boolean onLocation() {
        boolean showMenu = true;
        while (showMenu) {
            System.out.println("\nMağazaya hoş geldin!");
            System.out.println("1- Silahlar");
            System.out.println("2- Zırlar");
            System.out.println("3- Çıkış Yap");
            System.out.print("Yapmak istediğin işlemi seç: ");
            int selectCase = inp.nextInt(); // Location sınıfındaki scanner ile işlem yapıldı.

            while (selectCase < 1 || selectCase > 3) { // selectCase bu koşulu sağlayınca tekrar değer girmesini sağlıyoruz.
                System.out.print("Geçersiz bir seçim yapım yaptın. Tekrar dene:");
                selectCase = inp.nextInt();
            }
            switch (selectCase) {
                case 1:
                    printWeapon();
                    buyWeapon();
                    break;
                case 2:
                    printArmor();
                    buyArmor();
                    break;
                case 3:
                    System.out.println("Yine bekleriz...");
                    showMenu=false;
                    break;//return true;

            }

        }
                return true;
    }
    public void printWeapon(){ // Silahları gösteren metot
        System.out.println("\nSilahlar: ");
        //Weapon sınıfındaki weapons() metodu static olmasaydı nasıl yapardık??????

        for (Weapon i: Weapon.weapons() ){
            System.out.println(i.getwId()+"-"+i.getwName()+
                    "  \tHasar:\t"+i.getwDamage()+
                    "  \tFiyat:\t"+i.getwPrice());
        }
        System.out.println("0-Çıkış");
    }
    public void buyWeapon() { // Silah satın alma işlemlerinin yapıldığı metot.

            System.out.print("Almak istediğin silahı seçebilirsin: ");
            int selectWeaponID = inp.nextInt();

            //Seçilen değer 0 dan küçük veya weaponList dizisinin eleman sayısından büyükse
            while (selectWeaponID < 0 || selectWeaponID > Weapon.weapons().length) {// metot Weapon sınıfında static olduğu için Weapon.weapons() şeklinde eriştik.
                System.out.print("Geçersiz bir değer girdin. Tekrar dene:");
                selectWeaponID = inp.nextInt();
            }
            if (selectWeaponID!=0){// 0- Çıkış seçeneği seçilmezse

                // Seçilen id Weapon sınıfındaki getObjeById metotuna parametre olarak gönderildi.
                // Metotun sonucu Weapon dan üretilen  selectedWeapon(Seçilen silah nesnesi)a eşitlendi.
                Weapon selectedWeapon = Weapon.getWeaponObjeById(selectWeaponID);
                if (selectedWeapon != null) {
                    //Seçilen nesnenin fiyatı oyuncunun parasından büyükse
                    if (selectedWeapon.getwPrice() > this.getPlayer().getMoney()) { // player.getMoney de diyebilirdik.
                        System.out.println("Bakiye yeterli değil!");
                    } else {
                        int balance; // Oyuncunun kalan parası.
                        balance = this.getPlayer().getMoney() - selectedWeapon.getwPrice();
                        this.getPlayer().setMoney(balance); // oyuncunun parası güncellendi.
                        System.out.println(selectedWeapon.getwName() + " silahını aldın.");
                        System.out.println("Kalan paran: " + this.getPlayer().getMoney());

                        //System.out.println("Önceki silahınız: "+this.getPlayer().getInventory().getWeapon().getwName());
                        this.getPlayer().getInventory().setWeapon(selectedWeapon); // oyuncunun silahı değiştirildi.
                        System.out.println("Yeni silahın: " + this.getPlayer().getInventory().getWeapon().getwName());
                    }


                }



            }
        }

    public void printArmor(){ // Zırhları gösteren metot
        System.out.println();
        for (Armor a: Armor.armors()){
            System.out.println(a.getaId()+"-"+a.getaName()+
                    "\t\tZırh gücü: "+a.getaBlock()+
                    "\t\tFiyat: "+a.getaPrice());
        }
        System.out.println("0-Çıkış");
    }
    public void buyArmor() {

            System.out.print("Almak istediğin zırhı seçebilirsin: ");
            int selectArmorID = inp.nextInt();
            while (selectArmorID < 0 || selectArmorID > Armor.armors().length) {
                System.out.print("Geçersiz bir değer girdin. Tekrar dene:");
                selectArmorID = inp.nextInt();
            }
            if (selectArmorID!=0){ // 0- Çıkış seçilmezse

                Armor selectedArmor = Armor.getArmorObjeByID(selectArmorID);

                if (selectedArmor != null) {
                    if (selectedArmor.getaPrice() > this.getPlayer().getMoney()) {
                        System.out.println("Bakiyen yeterli değil...");
                    } else {
                        int balance = this.getPlayer().getMoney() - selectedArmor.getaPrice();
                        this.getPlayer().setMoney(balance);
                        System.out.println(selectedArmor.getaName() + " zırhını aldın.");
                        System.out.println("Kalan paran: " + this.getPlayer().getMoney());
                        this.getPlayer().getInventory().setArmor(selectedArmor);// Oyuncunun zırhı değiştirildi.
                    }
                }

            }


        }
    }

