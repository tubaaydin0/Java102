public class Weapon {// Silah türlerini tutan sınıf
    private String wName; // isim
    private int wId; //id
    private int wDamage; // hasar
    private int wPrice; //fiyat

    public Weapon(String wName,int wId, int wDamage,int wPrice){
        this.wName=wName;
        this.wId=wId;
        this.wDamage=wDamage;
        this.wPrice=wPrice;
    }
    public static Weapon[] weapons(){
        Weapon[] weaponList= new Weapon[3]; //3 elemanlı Weapon sınıfına ait weaponList isimli bir dizi oluşturduk.
        //Bu dizinin içine sırasıyla Weapon sınıfına ait nesneler üreterek bu nesnelerin değerlerini taşıyan elemanları oluşturduk.
        weaponList[0]=new Weapon("Kılıç",1,2,15);
        weaponList[1]=new Weapon("Tabanca",2,3,35);
        weaponList[2]=new Weapon("Tüfek",3,7,45);
        return weaponList;
    }
    //Dışarıya id numarasına göre nesne döndüren bir metot oluşturduk.
    // Kullanıcının seçeceği id ile weaponList dizisinin içindeki hangi id  eşleşiyorsa,
    // o id ye ait nesneyi seçmiş oluyoruz.
    public static Weapon getWeaponObjeById(int id){
        for (Weapon w: Weapon.weapons()){
            if (w.getwId()==id){ // weaponListteki id ile kullanıcının seçtiği id eşitse dizideki seçili id ye ait nesnenin tüm özelliklerini getir(wId,wName,wDamage,wPrice)
                return w;
            }
        }
        return null; // Eşit değilse boş değer döndürür.

    }
    public String getwName(){
        return this.wName;
    }
    public void setwName(String wName){
        this.wName=wName;
    }
    public int getwId(){
        return this.wId;
    }
    public void setwId(int wId){
        this.wId=wId;
    }

    public int getwDamage() {
        return wDamage;
    }

    public void setwDamage(int wDamage) {
        this.wDamage = wDamage;
    }

    public int getwPrice() {
        return wPrice;
    }

    public void setwPrice(int wPrice) {
        this.wPrice = wPrice;
    }
}
