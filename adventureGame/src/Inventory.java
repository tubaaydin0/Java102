public class Inventory {// Envanter sınıfı.
    private Weapon weapon;
    private Armor armor;
    private boolean isFood=false;
    private boolean isFirewood=false;
    private boolean isWater=false;
    private boolean isRandomAward=false;
    public Inventory(){

        this.weapon=new Weapon("Yumruk",-1,0,0); // başlangıç Varsayılan envanter
        this.armor=new Armor("Paçavra",-1,0,0); // Başlangıç varsayılan zırh değerleri
    }

    public Weapon getWeapon() {
        return weapon;
    }

    public void setWeapon(Weapon weapon) {
        this.weapon = weapon;
    }
    public Armor getArmor(){
        return armor;
    }
    public  void setArmor(Armor armor){
        this.armor=armor;
    }

    public boolean isFood() {
        return isFood;
    }

    public void setFood(boolean food) {
        isFood = food;
    }

    public boolean isFirewood() {
        return isFirewood;
    }

    public void setFirewood(boolean firewood) {
        isFirewood = firewood;
    }

    public boolean isWater() {
        return isWater;
    }

    public void setWater(boolean water) {
        isWater = water;
    }

    public boolean isRandomAward() {
        return isRandomAward;
    }

    public void setRandomAward(boolean randomAward) {
        isRandomAward = randomAward;
    }
}
