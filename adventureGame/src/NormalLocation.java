public abstract class NormalLocation extends Location {
    public NormalLocation(Player player,String locName){// oyuncu dahil ediliyor.
        super(player,locName);
    }
    @Override
    public boolean onLocation(){
        return true;
    }


}
