//This class is a subclass of Person and represents a player in the game. 
// It has an additional attribute for money, which is initialized to 1000. 
// The class provides methods to get and set the money, as well as a toString 
// method to display the player's information.
public class Players extends Person {
    
    private int money;

    public Players(String firstName, String lastName) {
        super(firstName, lastName);
        money = 1000;
    }
    public Players(String firstName) {
        super(firstName);
        money = 1000;
    }
    public int getMoney() {
        return money;
    }
    public void setMoney(int money) {
        this.money = money;
    }
    @Override
    public String toString() {
        return "Player: " + getNameFirst() + " " + 
        getNameLast() + " has $" + money;
    }
}