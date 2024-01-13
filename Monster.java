/*
 * Author:                 Sujan Rokad, 000882948
 *                         Jay Patel, 000881881 (Partner's Name and ID)
 * Authorship statement:   I, Sujan Rokad, 000882948, and my partner, Jay Patel, 000881881, certify that this material is our original work.
 *                         No other person's work has been used without due acknowledgment.
 * Purpose:                Define the abstract Monster class with attributes and behaviors common to all monsters.
 */

package Assignment_6;

import java.util.ArrayList;

public abstract class Monster {
    private String clanAffiliation; // The clan affiliation of the monster.
    private int ferocity; // The ferocity score of the monster.
    private int defense; // The defense score of the monster.
    private int magic; // The magic score of the monster.
    private int treasure; // The amount of treasure the monster has.
    private int health; // The health score of the monster.

    /**
     * Constructor for creating a Monster object with specified attribute values.
     *
     * @param clanAffiliation The clan affiliation of the monster.
     * @param ferocity        The ferocity score of the monster.
     * @param defense         The defense score of the monster.
     * @param magic           The magic score of the monster.
     * @param treasure        The amount of treasure the monster has.
     * @param health          The health score of the monster.
     */
    public Monster(String clanAffiliation, int ferocity, int defense, int magic, int treasure, int health) {
        this.clanAffiliation = clanAffiliation;
        this.ferocity = ferocity;
        this.defense = defense;
        this.magic = magic;
        this.treasure = treasure;
        this.health = health;
    }

    /**
     * Constructor for creating a Monster object with random attribute values.
     *
     * @param clanAffiliation The clan affiliation of the monster.
     */
    public Monster(String clanAffiliation) {
        this.clanAffiliation = clanAffiliation;
        this.ferocity = (int) (Math.random() * 21);
        this.defense = (int) (Math.random() * 21);
        this.magic = (int) (Math.random() * 21);
        this.treasure = 0;
        this.health = 10 + (int) (Math.random() * 11);
    }

    /**
     * Getter for the clan affiliation of the monster.
     *
     * @return The clan affiliation of the monster.
     */
    public String getClanAffiliation() {
        return clanAffiliation;
    }

    /**
     * Getter for the ferocity score of the monster.
     *
     * @return The ferocity score of the monster.
     */
    public int getFerocity() {
        return ferocity;
    }

    /**
     * Setter for the ferocity score of the monster.
     *
     * @param ferocity The ferocity score to set.
     */
    public void setFerocity(int ferocity) {
        if (ferocity >= 0 && ferocity <= 20) {
            this.ferocity = ferocity;
        } else {
            System.out.println("Ferocity must be between 0 & 20.");
        }
    }

    /**
     * Getter for the defense score of the monster.
     *
     * @return The defense score of the monster.
     */
    public int getDefense() {
        return defense;
    }

    /**
     * Setter for the defense score of the monster.
     *
     * @param defense The defense score to set.
     */
    public void setDefense(int defense) {
        if (defense < 0 || defense > 20) {
            System.out.println("Invalid defense value");
            return;
        }
        this.defense = defense;
    }

    /**
     * Getter for the magic score of the monster.
     *
     * @return The magic score of the monster.
     */
    public int getMagic() {
        return magic;
    }

    /**
     * Setter for the magic score of the monster.
     *
     * @param magic The magic score to set.
     */
    public void setMagic(int magic) {
        if (magic < 0 || magic > 20) {
            System.out.println("Invalid magic value");
            return;
        }
        this.magic = magic;
    }

    /**
     * Returns the value of the treasure.
     *
     * @return The treasure value.
     */
    public int getTreasure() {
        return treasure;
    }

    /**
     * Sets the treasure value.
     *
     * @param treasure The treasure value to set.
     */
    public void setTreasure(int treasure) {
        if (treasure < 0) {
            System.out.println("Invalid treasure value");
            return;
        }
        this.treasure = treasure;
    }

    /**
     * Returns the value of the health.
     *
     * @return The health value.
     */
    public int getHealth() {
        return health;
    }

    /**
     * Sets the health value.
     *
     * @param health The health value to set.
     */
    public void setHealth(int health) {
        if (health < 0) {
            System.out.println("Invalid health value");
            return;
        }
        this.health = health;
    }

    /**
     * Performs an attack on another monster, calculating damage based on attack and defense scores.
     *
     * @param other The target monster to be attacked.
     */
    public void attack(Monster other) {
        double attackScore = calculateAttackScore();
        double defenseScore = other.calculateDefenseScore();
        int damage = Math.max((int) Math.round((attackScore - defenseScore) / 2.0), 1);
        other.setHealth(other.getHealth() - damage);
        if (other.getHealth() <= 0) {
            other.setHealth(0);
            setTreasure(getTreasure() + other.getTreasure());
        }
    }

    /**
     * Calculates and returns the attack score of the monster.
     *
     * @return The calculated attack score.
     */
    public double calculateAttackScore() {
        return ferocity + magic;
    }

    /**
     * An abstract method that defines the rest behavior of the monster.
     */
    public abstract void rest();

    /**
     * An abstract method that gets the name of the monster.
     *
     * @return The name of the monster.
     */
    protected abstract String getName();

    /**
     * Calculates and returns the defense score of the monster.
     *
     * @return The calculated defense score.
     */
    public double calculateDefenseScore() {
        return defense + magic;
    }

    /**
     * Checks if the monster is defeated.
     *
     * @return True if the monster is defeated, false otherwise.
     */
    public boolean isDefeated() {
        return health <= 0;
    }

    /**
     * Returns a string representation of the monster.
     *
     * @return The string representation of the monster.
     */
    @Override
    public String toString() {
        String status = (health > 0) ? "alive" : "dead";
        return getClass().getSimpleName() + " from clan " + clanAffiliation + ", " +
                "ferocity=" + ferocity +
                ", defense=" + defense +
                ", magic=" + magic +
                ", treasure=" + treasure +
                ", health=" + health +
                ", status=" + status;
    }
}
