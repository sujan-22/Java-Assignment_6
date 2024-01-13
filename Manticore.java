/*
 * Author:                 Sujan Rokad, 000882948
 *                         Jay Patel, 000881881 (Partner's Name and ID)
 * Authorship statement:   I, Sujan Rokad, 000882948, and my partner, Jay Patel, 000881881, certify that this material is our original work.
 *                         No other person's work has been used without due acknowledgment.
 * Purpose:                Define the abstract Manticore class, a subclass of Monster, with additional functionality for tail venom and healing.
 */

package Assignment_6;

abstract class Manticore extends Monster {

    private int tailVenom;

    /**
     * Constructor for the Manticore class with specified parameters.
     *
     * @param clanAffiliation The clan affiliation of the manticore.
     * @param ferocity        The ferocity score of the manticore.
     * @param defense         The defense score of the manticore.
     * @param magic           The magic score of the manticore.
     * @param treasure        The treasure score of the manticore.
     * @param health          The health score of the manticore.
     * @param tailVenom       The tail venom score of the manticore.
     */
    public Manticore(String clanAffiliation, int ferocity, int defense, int magic, int treasure, int health,
                     int tailVenom) {
        super(clanAffiliation, ferocity, defense, magic, treasure, health); // Calls the constructor of the parent class to set common instance variables
        setTailVenom(tailVenom); // Sets the tail venom instance variable of the Manticore object
    }

    /**
     * Default constructor for the Manticore class that sets the instance variables of the manticore randomly.
     *
     * @param clanAffiliation The clan affiliation of the manticore.
     */
    public Manticore(String clanAffiliation) {
        this(clanAffiliation, (int) (Math.random() * 21), (int) (Math.random() * 21), (int) (Math.random() * 21), 0, 15,
                (int) (Math.random() * 11));
    }

    /**
     * Get the tail venom instance variable of the manticore.
     *
     * @return The tail venom score of the manticore.
     */
    public int getTailVenom() {
        return tailVenom;
    }

    /**
     * Set the tail venom instance variable of the manticore within a certain range.
     *
     * @param tailVenom The tail venom score of the manticore.
     */
    public void setTailVenom(int tailVenom) {
        this.tailVenom = Math.max(Math.min(tailVenom, 10), 1);
    }

    /**
     * Calculate and return the attack score of the manticore.
     *
     * @return The calculated attack score.
     */
    public double calculateAttackScore() {
        return getFerocity() + getMagic() + tailVenom;
    }

    /**
     * Override the attack method of the parent class to include the tail venom attack.
     *
     * @param other The target monster to be attacked.
     */
    @Override
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

    /**
     * An abstract method that needs to be implemented by the child class to define the healing behavior of the manticore.
     */
    public abstract void heal();
}
