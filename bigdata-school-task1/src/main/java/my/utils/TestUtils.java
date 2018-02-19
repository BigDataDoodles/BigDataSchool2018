package my.utils;

import my.pokemon.MyPokemon;
import org.apache.hadoop.classification.InterfaceAudience;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mrunit.types.Pair;

public class TestUtils {

    public static LinkedList< Pair<Text, MyPokemon> > readFromFileIntoMap(String path) throws IOException {

        LinkedList<Pair<Text, MyPokemon>> pokemons = new LinkedList<Pair<Text, MyPokemon>>();

        BufferedReader reader = new BufferedReader(new FileReader(
                path));

        String line = reader.readLine();

        while (line != null) {
            MyPokemon pokemon = new MyPokemon(line);
            Text type = new Text(pokemon.getType());
            pokemons.add(new Pair<Text, MyPokemon>(type, pokemon) );
            line = reader.readLine();
        }

        //закрываем мой ридер
        reader.close();

        //TODO: remove(null)?
        pokemons.remove(null);
        return pokemons;
    }
}
