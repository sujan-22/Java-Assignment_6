//My name: Sujan Rokad, 000882948
//My partner's name: Jay Patel, 000881881
//NUmber of total hours I worked: Around 17
//I think we did same effort in almost everything. (50%-50%)

package Assignment_6;

abstract class Manticore extends Monster {

    private int tailVenom;

    // Constructor with parameters to initialize the instance variables of the Manticore object
    public Manticore(String clanAffiliation, int ferocity, int defense, int magic, int treasure, int health,
                     int tailVenom) {
        super(clanAffiliation, ferocity, defense, magic, treasure, health); // Calls the constructor of the parent class to set common instance variables
        setTailVenom(tailVenom); //Sets the tail venom instance variable of the Manticore object
    }

    // Default constructor that sets the instance variables of the Manticore object randomly
    public Manticore(String clanAffiliation) {
        this(clanAffiliation, (int)(Math.random()*21), (int)(Math.random()*21), (int)(Math.random()*21), 0, 15,
                (int)(Math.random()*11));
    }

    // Getter method to get the tail venom instance variable of the Manticore object.
    public int getTailVenom() {
        return tailVenom;
    }

    // Setter method to set the tail venom instance variable of the Manticore object within a certain range
    public void setTailVenom(int tailVenom) {
        this.tailVenom = Math.max(Math.min(tailVenom, 10), 1);
    }

    // Calculates and returns the attack score of the Manticore object.
    public double calculateAttackScore() {
        return getFerocity() + getMagic() + tailVenom;
    }

    // Overrides the attack method of the parent class to include the tail venom attack
    public void attack(Monster other) {
        double attackScore = calculateAttackScore();
        double defenseScore = other.getDefense() + other.getMagic();
        if (attackScore > defenseScore) {
            other.setHealth(other.getHealth() - (int) Math.round((attackScore - defenseScore) / 2.0));
            if (other.getHealth() < 0) {
                other.setHealth(0);
                setTreasure(getTreasure() + other.getTreasure());
            }
        }
    }

   // An abstract method that needs to be implemented by the child class to define the heal behavior of the Manticore
   // object
    public abstract void heal();
}