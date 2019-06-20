import java.util.ArrayList;
import java.util.Random;

public class Pokemon {

    private Random rand = new Random(10);

    private String name;
    private double maxHealth;
    private double currentHealth;
    private String type;
    private ArrayList<SkillMove> moves;

    // The constructor
    public Pokemon(String name, double maxHealth, String type, ArrayList<SkillMove> moves) {
        this.name = name;
        this.maxHealth = maxHealth;
        currentHealth = maxHealth;
        this.moves = moves;
        this.type = type;
    }

    // The range should include the last element
    private int getRandomInt(int range) {
        return rand.nextInt(range);
    }

    // you should use this method to see if the skill move is missed.
    private boolean isMoveMissed(SkillMove m) {
        double d = rand.nextDouble();
        if (d > m.getMissRate()) {
            return false;
        }
        return true;
    }


    public void attack(Pokemon pokemon){

        SkillMove selectedAttack = selectRandomMove();

        if ( !isMoveMissed(selectedAttack) ){
            pokemon.setCurrentHealth( pokemon.getCurrentHealth() - selectedAttack.getDmg() );
        }

    }

    private SkillMove selectRandomMove(){
        return moves.get(getRandomInt(moves.size()));
    }

    public String toString(){
        return name + ", Moves:" + moves.toString(); 
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getMaxHealth() {
        return maxHealth;
    }

    public void setMaxHealth(double maxHealth) {
        this.maxHealth = maxHealth;
    }

    public double getCurrentHealth() {
        return currentHealth;
    }

    public void setCurrentHealth(double currentHealth) {
        this.currentHealth = currentHealth;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public ArrayList<SkillMove> getMoves() {
        return moves;
    }

    public void setMoves(ArrayList<SkillMove> moves) {
        this.moves = moves;
    }
}
