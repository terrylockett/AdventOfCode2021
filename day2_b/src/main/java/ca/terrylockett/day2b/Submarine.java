package ca.terrylockett.day2b;

public class Submarine {

    private int forward = 0;
    private int aim = 0;
    private int depth = 0;


    public void move(int distanceForward) {
        this.forward += distanceForward;
        this.depth -= (this.aim * distanceForward);
    }

    public void modifyAim(int amount){
        this.aim += amount;
    }

    public int getTotal() {
        return (this.forward * this.depth);
    }
}
