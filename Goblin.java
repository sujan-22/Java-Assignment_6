/*
 * Author:                 Sujan Rokad, 000882948
 *                         Jay Patel, 000881881 (Partner's Name and ID)
 * Authorship statement:   I, Sujan Rokad, 000882948, and my partner, Jay Patel, 000881881, certify that this material is our original work.
 *                         No other person's work has been used without due acknowledgment.
 * Purpose:                Define the Goblin class, a subclass of Monster, with additional functionality for attacking, resting, and more.
 */

package Assignment_6;

public class Goblin extends Monster {
    private Goblin enemy; // Variable enemy

    /**
     * Constructor for the Goblin class with all parameters.
     *
     * @param clanAffiliation The clan affiliation of the goblin.
     * @param ferocity        The ferocity score of the goblin.
     * @param defense         The defense score of the goblin.
     * @param magic           The magic score of the goblin.
     * @param treasure        The treasure score of the goblin.
     * @param health          The health score of the goblin.
     * @param enemy           The enemy goblin.
     */
    public Goblin(String clanAffiliation, int ferocity, int defense, int magic, int treasure, int health, Goblin enemy) {
        super(clanAffiliation, ferocity, defense, magic, treasure, health); // Call superclass constructor
        this.enemy = enemy;
    }

    /**
     * Get the enemy goblin.
     *
     * @return The enemy goblin.
     */
    public Goblin getEnemy() {
        return enemy;
    }

    /**
     * Set the enemy goblin.
     *
     * @param enemy The enemy goblin.
     */
    public void setEnemy(Goblin enemy) {
        this.enemy = enemy;
    }

    /**
     * Calculate the goblin's attack score, taking into account ferocity and magic.
     *
     * @return The calculated attack score.
     */
    public double calculateAttackScore() {
        return getFerocity() + getMagic();
    }

    /**
     * Attack another monster, reducing its health based on attack score and defense score.
     *
     * @param other The target monster to be attacked.
     */
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
     * Rest action for the goblin, restoring lost health.
     */
    public void rest() {
        int lostHealth = Math.max((int) Math.round(getMaxHealth() * 0.1), 1); // Lost health during resting is 10% of max health, at least 1
        setHealth(Math.min(getHealth() + lostHealth, getMaxHealth())); // Restore lost health, but not more than max health
    }

    /**
     * Get the name of the goblin.
     *
     * @return The name of the goblin.
     */
    @Override
    public String getName() {
        return getName();
    }

    /**
     * Get the maximum health of the goblin.
     *
     * @return The maximum health of the goblin.
     */
    private int getMaxHealth() {
        return getMaxHealth();
    }

    /**
     * Calculate the goblin's defense score.
     *
     * @return The calculated defense score.
     */
    public double calculateDefenseScore() {
        return calculateDefenseScore();
    }

    /**
     * Check if the goblin is defeated.
     *
     * @return True if the goblin is defeated, false otherwise.
     */
    public boolean isDefeated() {
        return isDefeated();
    }
}
