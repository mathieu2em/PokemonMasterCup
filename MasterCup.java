import java.io.*;
import java.util.ArrayList;
import java.util.Random;

public class MasterCup {

    private Random rand = new Random(10);
    private ArrayList<PokemonTrainer> trainerList;

    public static void main(String[] args) throws IOException {
	MasterCup m = new MasterCup();
    }

    // The constructor
    public MasterCup() throws IOException {

	trainerList = new ArrayList<>();
	PokemonZoo zoo = new PokemonZoo();
	createTrainers(10, zoo.getPokeList());
	startMatch();
	writeScores();
    }

    // The range should include the last element
    private int getRandomInt(int range) {

	return rand.nextInt(range);

    }

    public void createTrainers(int n, ArrayList<Pokemon> pokemons) throws IOException {

	for(int i=0; i<n; i++){
	    trainerList.add(new PokemonTrainer(getRandomName(getRandomInt(5870)),getRandomPokemons(pokemons) ));
	}

	printTrainerList();
    }

    private static String getRandomName(int n) throws IOException {

	FileReader fileReader = new FileReader("name.txt");
	BufferedReader bufferedReader = new BufferedReader(fileReader);

	for (int i = 0; i<n-1; i++){
	    bufferedReader.readLine();
	}

	return bufferedReader.readLine().split(" ")[0];

    }

    private  ArrayList<Pokemon> getRandomPokemons(ArrayList<Pokemon> pokemonList){

	ArrayList<Pokemon> pokeList = new ArrayList<>();
	for(int i=0; i<5; i++) {
	    pokeList.add(pokemonList.get(getRandomInt(pokemonList.size())));
	}
	return pokeList;
    }

    // returns a string containing the trainerlist formatted as asked
    private String printTrainerList(){

	String trainers = "";
	for(int i=0; i < trainerList.size(); i++ ){
	    PokemonTrainer trainer = trainerList.get(i);
	    trainers += trainer.toString() + "\n";
	}
	return trainers;
    }

    // heals all the pokemons
    public void healAll(PokemonTrainer trainer){
	ArrayList<Pokemon> team = trainer.getTeam();
	// set the current heal to max heal to each pokemon
	for(int i=0; i<team.size(); i++){
	    Pokemon pokemon = team.get(i);
	    pokemon.setCurrentHealth(pokemon.getMaxHealth());
	}
    }

    // should simulate a pokemon battle
    // it is made randomly.
    public void pvp(PokemonTrainer trainer1, PokemonTrainer trainer2){

	System.out.println(trainer1.getName()+ " attack "+trainer2.getName());
	while(true){
	    if(p1AttackP2(trainer1,trainer2)) return;
	    if(p1AttackP2(trainer2,trainer1)) return;
	}

    }

    // this method make the attack between two pokemons and verify if the player still has pokemons alive aftermath
    private boolean p1AttackP2(PokemonTrainer p1, PokemonTrainer p2){

	getRandomPokemonAlive(p1).attack(getRandomPokemonAlive(p2));

	if(!stillFighting(p2)){
	    p1.setWin(p1.getWin()+1);
	    System.out.println(p1.getName()+ " win!");
	    healAll(p1);
	    healAll(p2);
	    return true;
	}

	return false;
    }

    public void startMatch(){

	for(int i=0; i < trainerList.size(); i++){

	    PokemonTrainer pokemonTrainer = trainerList.get(i);

	    for(int j=0; j < trainerList.size(); j++){

		if(i == j)continue;
		pvp(pokemonTrainer, trainerList.get(j));

	    }
	}
    }

    public void writeScores() throws IOException {

	File file = new File("scores.txt");
	FileWriter fr = null;
	try {
	    fr = new FileWriter(file);
	    fr.write(printTrainerList());
	} catch (IOException e) {
	    e.printStackTrace();
	}finally{
	    //close resources
	    try {
		fr.close();
	    } catch (IOException e) {
		e.printStackTrace();
	    }
	}
    }

    private Pokemon getRandomPokemonAlive(PokemonTrainer pokemonTrainer){
	Pokemon pokemon = null;

	while(isDead(pokemon)) {
	    pokemon = pokemonTrainer.getTeam().get(getRandomInt(pokemonTrainer.getTeam().size()));
	}
	return pokemon;
    }

    private boolean isDead(Pokemon pokemon){

	if(pokemon == null) return true;
	return pokemon.getCurrentHealth() <= 0;
    }

    // verify if the player still has pokemons alive
    private boolean stillFighting(PokemonTrainer pokemonTrainer){
	ArrayList<Pokemon> team = pokemonTrainer.getTeam();
	for (Pokemon pokemon : team) {
	    if (!isDead(pokemon)) return true;
	}
	return false;
    }
}
