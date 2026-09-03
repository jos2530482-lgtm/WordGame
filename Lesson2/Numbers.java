public class Numbers {
    private int randomNum;
    
    public int getRandomNum() {
        return randomNum;
    }
    public void setRandomNum(int randomNum) {
        this.randomNum = randomNum;
    }
    public void generateNumber() {
        this.randomNum = (int) (Math.random() * 101);
    }
    
    public boolean compareNumber(int guess) {
    if (guess == randomNum) {
        System.out.println("Congratulations, you guessed the number!");
        return true;
    } else if (guess > randomNum) {
        System.out.println("I'm sorry. That guess was too high.");
        return false;
    } else {
        System.out.println("I'm sorry. That guess was too low.");
        return false;
    }
}
}