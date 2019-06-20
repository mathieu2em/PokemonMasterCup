import java.io.*;
import java.util.ArrayList;
import java.util.Random;

public class PokemonZoo {
    private Random rand = new Random(10);

    private ArrayList<SkillMove> movesList;
    private ArrayList<Pokemon> pokemonList;

    public PokemonZoo() {
        movesList = new ArrayList<>();
        pokemonList = new ArrayList<>();
        loadMoves();
        loadPokemon();
    }

    // The range should include the last element
    private int getRandomInt(int range) {
        return rand.nextInt(range);
    }

    public void loadMoves(){
        try {
            FileReader fileReader = new FileReader("skillMove.txt");
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            bufferedReader.readLine();

            while(true){
                try {
                    String[] skills = bufferedReader.readLine().split(" ");
                    SkillMove move = new SkillMove(skills[0],skills[1],
                            Double.parseDouble(skills[2]),
                            Double.parseDouble(skills[3]));
                    movesList.add(move);
                } catch (NullPointerException e){
                    break;
                }
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {

        }
    }

    public void loadPokemon(){
        FileReader fileReader = null;
        try {
            fileReader = new FileReader("pokemons.txt");
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            bufferedReader.readLine();
            while(true){
                try {
                    String[] pokemon = bufferedReader.readLine().split(" ");
                    Pokemon pokemon1 =
                            new Pokemon(
                                    pokemon[0],
                                    Double.parseDouble(pokemon[1]),
                                    pokemon[2],
                                    genMove(Integer.parseInt(pokemon[3])));
                    pokemonList.add(pokemon1);
                } catch (NullPointerException e){
                    break;
                }

            }

        } catch (FileNotFoundException e ) {
            e.printStackTrace();
        } catch (IOException e) {

        }

    }

    public ArrayList<SkillMove> genMove(int n){

        ArrayList<SkillMove> moveList = new ArrayList<>();

        for(int i=0; i<n; i++){
            moveList.add(movesList.get(getRandomInt(movesList.size())));
        }
        return moveList;
    }

    public ArrayList<Pokemon> getPokeList(){
        return pokemonList;
    }


}

