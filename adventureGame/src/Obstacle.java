public class Obstacle {//Canavar Sınıfı
    private int oId;
    private String oName;
    private int oDamage;
    private int oHealth;
    private int moneyAward; // Canavar öldürünce kazanılan para
    private int originalHealth; // Başlangıçtaki canı

    public Obstacle(int oId, String oName, int oDamage, int oHealth, int moneyAward) {
        this.oId = oId;
        this.oName = oName;
        this.oDamage = oDamage;
        this.oHealth = oHealth;
        this.moneyAward = moneyAward;
        this.originalHealth = oHealth;
    }

    public int getoId() {
        return this.oId;
    }

    public void setoId(int oId) {
        this.oId = oId;
    }

    public String getoName() {
        return oName;
    }

    public void setoName(String oName) {
        this.oName = oName;
    }

    public int getoDamage() {
        return oDamage;
    }

    public void setoDamage(int oDamage) {
        this.oDamage = oDamage;
    }

    public int getoHealth() {

        return oHealth;
    }
    public void setoHealth(int oHealth) {
        if (oHealth < 0) { // Canavarın canı 0 ın altına düşerse onu 0 a eşitliyoruz.
            oHealth = 0;
        }
        this.oHealth = oHealth;
        }


        public int getMoneyAward() {
        return moneyAward;
    }

    public void setMoneyAward(int moneyAward) {
        this.moneyAward = moneyAward;
    }


        public int getOriginalHealth() {
            return originalHealth;
        }

        public void setOriginalHealth(int originalHealth){
            this.originalHealth = originalHealth;
        }
    }

