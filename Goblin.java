//My name: Sujan Rokad, 000882948
//My partner's name: Jay Patel, 000881881
//NUmber of total hours I worked: Around 17
//I think we did same effort in almost everything. (50%-50%)

package Assignment_6;

public class Goblin extends Monster {
   private Goblin enemy; //Variable enemy
    // constructor with all parameters
    public Goblin(String clanAffiliation, int ferocity, int defense, int magic, int treasure, int health, Goblin enemy) {
        super(clanAffiliation, ferocity, defense, magic, treasure, health); // call superclass constructor
        this.enemy = enemy;
    }

    public Goblin getEnemy() {
        return enemy;
    }

    public void setEnemy(Goblin enemy) {
        this.enemy = enemy;
    }

    // calculate the goblin's attack score, taking into account agility and rider
    public double calculateAttackScore() {
        double attackScore = getFerocity() + getMagic();
        return attackScore;
    }

    // attack another monster, reducing its health based on attack score and defense score
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

    public void rest() {
        // Calculate the amount of health lost during resting
        int lostHealth = Math.max((int) Math.round(getMaxHealth() * 0.1), 1); // lost health during resting is 10% of max health, at least 1

        // Restore lost health, but not more than max health
        setHealth(Math.min(getHealth() + lostHealth, getMaxHealth())); // restore lost health, but not more than max health

        // If this object is mounted and has a rider, let the rider rest too
    }

    @Override
    public String getName() {
        return getName();
    }

    private int getMaxHealth() {
        return getMaxHealth();
    }

    public double calculateDefenseScore() {
        return calculateDefenseScore();
    }

    public boolean isDefeated() {
        return isDefeated();
    }
}
