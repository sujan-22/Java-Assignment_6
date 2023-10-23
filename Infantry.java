//My name: Sujan Rokad, 000882948
//My partner's name: Jay Patel, 000881881
//NUmber of total hours I worked: Around 17
//I think we did same effort in almost everything. (50%-50%)

package Assignment_6;

class Infantry extends Monster {

    private Orc warlord; // A reference to the Infantry's Warlord

    // Constructor with specified parameters
    public Infantry(String clanAffiliation, int ferocity, int defense, int magic, int treasure, int health, Warlord warlord) {
        super(clanAffiliation, ferocity, defense, magic, treasure, health); // Calls superclass constructor with specified parameters
        this.warlord = warlord; // Calls superclass constructor with specified parameters
        // Adds the Infantry to the Warlord's list of infantry if the Warlord is not null
        if (warlord != null){
            warlord.addInfantry(this);
        }
    }

    // Constructor with default random values
    public Infantry(String clanAffiliation) {
        this(clanAffiliation, (int)(Math.random()*11), (int)(Math.random()*11), (int)(Math.random()*11), 0, 5,null);
    }

    // Getter method for the Infantry's warlord
    public Orc getWarlord() {
        return warlord;
    }

    // Setter method for the Infantry's warlord
    public void setWarlord(Orc warlord) {
        this.warlord = warlord;
    }

    // Calculates the Infantry's attack score, taking into account its ferocity, magic, and its Warlord's leadership rating
    @Override
    public double calculateAttackScore() {
        return (getFerocity() + getMagic()) * (1.0 + (warlord != null ? warlord.getLeadershipRating() / 10.0 : 0));
    }

    // Overrides the super class's attack method to factor in the Infantry's attack score and the target Monster's
    // defense score
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

    // Overrides the superclass's rest method to increase the Infantry's health by 1, up to its maximum health of 5
    @Override
    public void rest() {
        setHealth(Math.min(getHealth() + 1, getMaxHealth()));
    }

    // Returns the Infantry's maximum health of 5
    private int getMaxHealth() {
        return 5;
    }

    // Returns the Infantry's name of "Infantry"
    @Override
    protected String getName() {
        return "Infantry";
    }

    // Calculates the defense score and returns score to the monster
    public double calculateDefenseScore() {

        return (getDefense() + getMagic()) * (1.0 + (warlord != null ? warlord.getLeadershipRating() / 10.0 : 0));
    }

    // Boolean value that checks if monster is defeated or not
    public boolean isDefeated() {

        return getHealth() == 0;
    }

    // String representation for Infantry
    public String toString() {
        StringBuilder sb = new StringBuilder(super.toString());
        sb.append("\nWarlord: ").append(warlord != null ? warlord.getClanAffiliation() : "None");
        return sb.toString();
    }
}

