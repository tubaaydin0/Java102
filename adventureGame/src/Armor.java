public class Armor { // Zırh Sınıfı
    private String aName;
    private int aId;
    private int aBlock;// Zırh engelleme
    private  int aPrice;//Zırh fiyat
    public Armor(String aName,int aId,int aBlock,int aPrice){
        this.aName=aName;
        this.aId=aId;
        this.aBlock=aBlock;
        this.aPrice=aPrice;
    }
    public static Armor[] armors(){
        Armor[] armorList=new Armor[3];
        armorList[0]=new Armor("Hafif",1,1,15);
        armorList[1]=new Armor("Orta",2,3,25);
        armorList[2]=new Armor("Ağır",3,5,40);
        return armorList;
    }
    public static Armor getArmorObjeByID(int id){
        for (Armor a: Armor.armors()){
            if(a.getaId()==id){
                return a;
            }
        }
        return null;
    }
    public String getaName() {
        return aName;
    }

    public void setaName(String aName) {
        this.aName = aName;
    }

    public int getaId() {
        return aId;
    }

    public void setaId(int aId) {
        this.aId = aId;
    }

    public int getaBlock() {
        return aBlock;
    }

    public void setaBlock(int aBlock) {
        this.aBlock = aBlock;
    }

    public int getaPrice() {
        return aPrice;
    }

    public void setaPrice(int aPrice) {
        this.aPrice = aPrice;
    }
}
