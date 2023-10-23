//My name: Sujan Rokad, 000882948
//My partner's name: Jay Patel, 000881881
//NUmber of total hours I worked: Around 17
//I think we did same effort in almost everything. (50%-50%)

package Assignment_6;

import java.util.ArrayList;

abstract class Warlord extends Orc {

    // Constructor with all parameters
    public Warlord(String clanAffiliation, int ferocity, int defense, int magic, int treasure, int health,
                   int leadershipRating, ArrayList<Infantry> infantryList) {
        super(clanAffiliation, ferocity, defense, magic, treasure, health, true, leadershipRating, new ArrayList<>());
    }

    // It also generates a random leadership rating for the Warlord, between 1 and 5 (inclusive)
    public Warlord(String clanAffiliation) {        super(clanAffiliation);
        setWarlord(true);
        setLeadershipRating((int)(Math.random()*5)+1);
    }
}

