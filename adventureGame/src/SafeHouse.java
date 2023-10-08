public class SafeHouse extends NormalLocation{

    public SafeHouse(Player player){
        super(player, "Güvenli Ev");
    }
    @Override
    public boolean onLocation(){
        if ((this.getPlayer().getInventory().isFood()==true) &&(this.getPlayer().getInventory().isFirewood()==true) && this.getPlayer().getInventory().isWater()==true && (this.getPlayer().getInventory().isRandomAward()==true)) {
            System.out.println("################################");
            System.out.println("Tebrikler oyunu kazandın!!! :)");
            this.getPlayer().setWin(true);
        }else {

            System.out.println("\nGüvenli Eve hoş geldiniz, Canınız yenileniyor...");
            this.getPlayer().setHealth(this.getPlayer().getOriginalHealth());
            //System.out.println("Can:"+this.getPlayer().getHealth());

        }
        return true;
    }


}
