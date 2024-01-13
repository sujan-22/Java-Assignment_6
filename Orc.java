/*
 * Author:                 Sujan Rokad, 000882948
 *                         Jay Patel, 000881881 (Partner's Name and ID)
 * Authorship statement:   I, Sujan Rokad, 000882948, and my partner, Jay Patel, 000881881, certify that this material is our original work.
 *                         No other person's work has been used without due acknowledgment.
 * Purpose:                Define an abstract class representing Orcs in the game, with attributes and behaviors specific to Orcs.
 */

package Assignment_6;

import java.util.ArrayList;

// An abstract class that extends the Monster class and represents an Orc in the game
public abstract class Orc extends Monster {

    private boolean isWarlord; // indicates whether the orc is a warlord or not
    private int leadershipRating; // the leadership rating of the warlord (0-5)
    private ArrayList<Infantry> infantryList; // a list of infantry units that belong to the warlord

    // Constructor for creating an orc with specified attributes
    public Orc(String clanAffiliation, int ferocity, int defense, int magic, int treasure, int health,
               boolean isWarlord, int leadershipRating, ArrayList<Infantry> infantryList) {
        super(clanAffiliation, ferocity, defense, magic, treasure, health);
        this.isWarlord = isWarlord;

        // If the orc is a warlord
        if (isWarlord) {
            this.leadershipRating = Math.max(Math.min(leadershipRating, 5), 1); // limit leadership rating to 1-5

            // If infantryList is not provided, create an empty list
            if (infantryList == null) {
                this.infantryList = new ArrayList<>();
            } else {
                this.infantryList = infantryList;
                for (Infantry i : infantryList) {
                    i.setWarlord(this);
                }
            }
        } else { // If the orc is not a warlord
            this.leadershipRating = 0; // set leadership rating to 0
            this.infantryList = new ArrayList<>(); // create an empty infantry list
        }
    }

    // Constructor for Orc class that initializes instance variables with random values and a clan affiliation.
    // It also creates a new ArrayList for the Orc's infantry list.
    public Orc(String clanAffiliation) {
        this(clanAffiliation, (int) (Math.random() * 21), (int) (Math.random() * 21),
                (int) (Math.random() * 21), 0, 10, false, 0, new ArrayList<>());
    }

    // Getter method for isWarlord instance variable.
    public boolean isWarlord() {
        return isWarlord;
    }

    // Setter method for isWarlord instance variable that sets the variable to the given boolean value.
    // If the Orc is not a Warlord, then the leadershipRating is set to 0 and infantryList is cleared.
    public void setWarlord(boolean isWarlord) {
        this.isWarlord = isWarlord;
        if (!isWarlord) {
            leadershipRating = 0;
            infantryList = new ArrayList<>();
        }
    }

    // Getter method for leadershipRating instance variable.
    public int getLeadershipRating() {
        return leadershipRating;
    }

    // Setter method for leadershipRating instance variable that sets the variable to the given integer value.
    // If the Orc is not a Warlord, then the method returns without changing the value of leadershipRating.
    // Otherwise, the value of leadershipRating is limited to be between 1 and 5.
    public void setLeadershipRating(int leadershipRating) {
        if (!isWarlord) {
            return;
        }
        this.leadershipRating = Math.max(Math.min(leadershipRating, 5), 1);
    }

    // Getter method for infantryList instance variable.
    public ArrayList<Infantry> getInfantryList() {
        return infantryList;
    }

    // Setter method for infantryList instance variable that sets the variable to the given ArrayList of Infantry objects.
    // If the Orc is not a Warlord, then the method returns without changing the value of infantryList.
    // Otherwise, the value of infantryList is set to the given ArrayList, and each Infantry object in the list has its Warlord set to this Orc.
    public void setInfantryList(ArrayList<Infantry> infantryList) {
        if (!isWarlord) {
            return;
        }
        this.infantryList = infantryList;
        for (Infantry i : infantryList) {
            i.setWarlord(this);
        }
    }

    // Method that adds an Infantry object to the Orc's infantryList.
    // If the Orc is not a Warlord, then the method returns without adding the Infantry object.
    // Otherwise, the Warlord of the Infantry object is set to this Orc, and the Infantry object is added to the infantryList.
    public void addInfantry(Infantry infantry) {
        if (!isWarlord) {
            return;
        }
        infantry.setWarlord(this);
        if (infantryList == null) {
            infantryList = new ArrayList<>();
        }
        infantryList.add(infantry);
    }

    // Method that removes an Infantry object from the Orc's infantryList.
    // If the Orc is not a Warlord, then the method returns without removing the Infantry object.
    // Otherwise, the Warlord of the Infantry object is set to null, and the Infantry object is removed from the infantryList.
    public void removeInfantry(Infantry infantry) {
        if (!isWarlord) {
            return;
        }
        infantry.setWarlord(null);
        infantryList.remove(infantry);
        infantry.setWarlord(null);
    }

    // Calculate the total attack score of the Orc, considering its own attack score, leadership rating, and the attack scores of its infantry.
    public double calculateAttackScore() {
        double attackScore = 0.0;

        // Iterate over the infantryList and calculate their individual attack scores,
        // then add them up to get the total attack score.
        for (Infantry i : infantryList) {
            attackScore += i.calculateAttackScore();
        }

        // If the current object is a warlord, multiply the attack score by the warlord's
        // leadership rating divided by 5.
        if (isWarlord) {
            attackScore *= leadershipRating / 5.0;
        }

        // Add the ferocity and magic attributes to the attack score, and return it.
        attackScore += getFerocity() + getMagic();
        return attackScore;
    }

    // Perform an attack on another monster, considering the total attack score and the target Monster's defense score.
    public void attack(Monster other) {
        // Calculate the attack score of the current object.
        double attackScore = calculateAttackScore();

        // Calculate the defense score of the target monster.
        double defenseScore = other.calculateDefenseScore();

        // If the attack score is greater than the defense score, subtract the difference
        // from the target's health, but only half of it.
        if (attackScore > defenseScore) {
            other.setHealth(other.getHealth() - (int) Math.round((attackScore - defenseScore) / 2.0));

            // If the target's health goes below 0, set it to 0 and add the target's treasure
            // to the current object's treasure.
            if (other.getHealth() < 0) {
                other.setHealth(0);
                setTreasure(getTreasure() + other.getTreasure());
            }
        }
    }

    // Perform rest action, considering the health restoration for both the Orc and its infantry.
    public void rest() {
        // Calculate the amount of health lost during resting as 10% of max health, but
        // not less than 1.
        int lostHealth = Math.max((int) Math.round(getMaxHealth() * 0.1), 1);

        // Restore the lost health, but not more than the max health.
        setHealth(Math.min(getHealth() + lostHealth, getMaxHealth()));

        // Call the rest method on each infantry in the infantryList.
        for (Infantry i : infantryList) {
            i.rest();
        }
    }

    // Getter method for the max health of the Orc.
    public int getMaxHealth() {
        // This method is not implemented in this class, so it will return 0 by default.
        return 0;
    }

    // String representation of the Orc, including warlord-specific information and details about infantry.
    @Override
    public String toString() {
        // Append the basic object information to a StringBuilder.
        StringBuilder sb = new StringBuilder(super.toString());

        // Append the warlord-specific information to the StringBuilder.
        sb.append("\nLeadership rating: ").append(leadershipRating);
        sb.append("\nWarlord: ").append(isWarlord ? "Yes" : "No");

        // Append the infantry list to the StringBuilder, or indicate that there is no infantry.
        sb.append("\nInfantry: ");
        if (infantryList.isEmpty()) {
            sb.append("None");
        } else {
            for (Infantry i : infantryList) {
                sb.append("\n- ").append(i.getName());
            }
        }

        // Return the StringBuilder as a string.
        return sb.toString();
    }

    // Calculate the total defense score of the Orc, considering its own defense score, magic, and the defense scores of its infantry.
    public double calculateDefenseScore() {
        // Calculate the defense score by adding the defense and magic scores of the character, and those of their infantry
        double defenseScore = getDefense() + getMagic();
        for (Infantry i : infantryList) {
            defenseScore += i.calculateDefenseScore();
        }

        // If the character is a warlord, modify the defense score based on their leadership rating
        if (isWarlord) {
            defenseScore *= (6 - leadershipRating) / 5.0;
        }

        // Return the final defense score
        return defenseScore;
    }

    // Check if the Orc is defeated.
    public boolean isDefeated() {
        // If the character's health is less than or equal to 0, they are defeated
        if (getHealth() <= 0) {
            return true;
        }

        // If any of the character's infantry are not defeated, the character is not defeated
        for (Infantry i : infantryList) {
            if (!i.isDefeated()) {
                return false;
            }
        }

        // If all of the character's infantry are defeated, the character is defeated
        return true;
    }
}
