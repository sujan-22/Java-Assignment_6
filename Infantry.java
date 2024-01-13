/*
 * Author:                 Sujan Rokad, 000882948
 *                         Jay Patel, 000881881 (Partner's Name and ID)
 * Authorship statement:   I, Sujan Rokad, 000882948, and my partner, Jay Patel, 000881881, certify that this material is our original work.
 *                         No other person's work has been used without due acknowledgment.
 * Purpose:                Define the Infantry class, a subclass of Monster, with additional functionality for attack, rest, and more.
 */

package Assignment_6;

class Infantry extends Monster {

    private Orc warlord; // A reference to the Infantry's Warlord

    /**
     * Constructor for the Infantry class with specified parameters.
     *
     * @param clanAffiliation The clan affiliation of the infantry.
     * @param ferocity        The ferocity score of the infantry.
     * @param defense         The defense score of the infantry.
     * @param magic           The magic score of the infantry.
     * @param treasure        The treasure score of the infantry.
     * @param health          The health score of the infantry.
     * @param warlord         The warlord of the infantry.
     */
    public Infantry(String clanAffiliation, int ferocity, int defense, int magic, int treasure, int health, Warlord warlord) {
        super(clanAffiliation, ferocity, defense, magic, treasure, health); // Calls superclass constructor with specified parameters
        this.warlord = warlord; // Calls superclass constructor with specified parameters
        // Adds the Infantry to the Warlord's list of infantry if the Warlord is not null
        if (warlord != null) {
            warlord.addInfantry(this);
        }
    }

    /**
     * Constructor for the Infantry class with default random values.
     *
     * @param clanAffiliation The clan affiliation of the infantry.
     */
    public Infantry(String clanAffiliation) {
        this(clanAffiliation, (int) (Math.random() * 11), (int) (Math.random() * 11), (int) (Math.random() * 11), 0, 5, null);
    }

    /**
     * Get the warlord of the infantry.
     *
     * @return The warlord of the infantry.
     */
    public Orc getWarlord() {
        return warlord;
    }

    /**
     * Set the warlord of the infantry.
     *
     * @param warlord The warlord of the infantry.
     */
    public void setWarlord(Orc warlord) {
        this.warlord = warlord;
    }

    /**
     * Calculate the Infantry's attack score, taking into account its ferocity, magic, and its Warlord's leadership rating.
     *
     * @return The calculated attack score.
     */
    @Override
    public double calculateAttackScore() {
        return (getFerocity() + getMagic()) * (1.0 + (warlord != null ? warlord.getLeadershipRating() / 10.0 : 0));
    }

    /**
     * Attack another monster, reducing its health based on attack score and defense score.
     *
     * @param other The target monster to be attacked.
     */
    @Override
    public void attack(Monster other) {
        double attackScore = calculateAttackScore();
        double defenseScore = other.getDefense() + other.getMagic();
        if (attackScore > defenseScore) {
            other.setHealth(other.getHealth() - (int) Math.round(attackScore - defenseScore));
            if (other.getHealth() < 0) {
                other.setHealth(0);
                setTreasure(getTreasure() + other.getTreasure());
            }
        }
    }

    /**
     * Rest action for the infantry, increasing health by 1, up to its maximum health of 5.
     */
    @Override
    public void rest() {
        setHealth(Math.min(getHealth() + 1, getMaxHealth()));
    }

    /**
     * Get the maximum health of the infantry.
     *
     * @return The maximum health of the infantry.
     */
    private int getMaxHealth() {
        return 5;
    }

    /**
     * Get the name of the infantry.
     *
     * @return The name of the infantry.
     */
    @Override
    protected String getName() {
        return "Infantry";
    }

    /**
     * Calculate the defense score of the infantry.
     *
     * @return The calculated defense score.
     */
    public double calculateDefenseScore() {
        return (getDefense() + getMagic()) * (1.0 + (warlord != null ? warlord.getLeadershipRating() / 10.0 : 0));
    }

    /**
     * Check if the infantry is defeated.
     *
     * @return True if the infantry is defeated, false otherwise.
     */
    public boolean isDefeated() {
        return getHealth() == 0;
    }

    /**
     * String representation for the infantry.
     *
     * @return A string representation of the infantry.
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder(super.toString());
        sb.append("\nWarlord: ").append(warlord != null ? warlord.getClanAffiliation() : "None");
        return sb.toString();
    }
}
