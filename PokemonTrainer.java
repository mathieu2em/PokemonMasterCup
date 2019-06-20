import java.util.ArrayList;

public class PokemonTrainer {

    private String name;
    private int win;
    private ArrayList<Pokemon> team;

    // The constructor
    public PokemonTrainer(String name, ArrayList<Pokemon> team) {
	this.name = name;
	this.team = team;
    }

    @Override
    public String toString() {
	return "Trainer: " + name + ", Wins: "
		+ win + ", team:[\n" + teamToString(team) +
		"]\n";
    }

    public String teamToString(ArrayList<Pokemon> arrayList){
	String pokeList = "";
	for(int i=0; i<arrayList.size(); i++){
	    pokeList += arrayList.get(i).toString() + ((i == arrayList.size()-1)?"":",\n");
	}
	return pokeList;
    }

    public String getName() {
	return name;
    }

    public int getWin() {
	return win;
    }

    public void setWin(int win) {
	this.win = win;
    }

    public ArrayList<Pokemon> getTeam() {
	return team;
    }

    public void setTeam(ArrayList<Pokemon> team) {
	this.team = team;
    }
}
