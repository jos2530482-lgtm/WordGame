public class Players extends Person {
    
    private int money = 1000;
    
    public Players(int money) {
        this.money = money;
    }
    
    public int getMoney() {
        return money;
    }
    
    public void setMoney(int money) {
        this.money = money;
    }
}