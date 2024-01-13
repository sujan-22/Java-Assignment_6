/*
 * Author:                 Sujan Rokad, 000882948
 *                         Jay Patel, 000881881 (Partner's Name and ID)
 * Authorship statement:   I, Sujan Rokad, 000882948, and my partner, Jay Patel, 000881881, certify that this material is our original work.
 *                         No other person's work has been used without due acknowledgment.
 * Purpose:                Define an abstract class representing Warlords in the game, extending the Orc class.
 */

abstract class Warlord extends Orc {

    /**
     * Constructor for creating a Warlord with specified attributes.
     *
     * @param clanAffiliation The clan affiliation of the Warlord.
     * @param ferocity        The ferocity score of the Warlord.
     * @param defense         The defense score of the Warlord.
     * @param magic           The magic score of the Warlord.
     * @param treasure        The amount of treasure the Warlord has.
     * @param health          The health score of the Warlord.
     * @param leadershipRating The leadership rating of the Warlord (1-5).
     * @param infantryList    The list of Infantry units under the Warlord.
     */
    public Warlord(String clanAffiliation, int ferocity, int defense, int magic, int treasure, int health,
                   int leadershipRating, ArrayList<Infantry> infantryList) {
        super(clanAffiliation, ferocity, defense, magic, treasure, health, true, leadershipRating, new ArrayList<>());
    }

    /**
     * Constructor for creating a Warlord with random attribute values.
     *
     * @param clanAffiliation The clan affiliation of the Warlord.
     */
    public Warlord(String clanAffiliation) {
        super(clanAffiliation);
        setWarlord(true);
        setLeadershipRating((int)(Math.random() * 5) + 1);
    }
}
