package ru.vsu.bigdata.task1;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class PokemonReducer extends Reducer<Text, Pokemon, Text, Text> {

    private Text result = new Text();

    @Override
    public void reduce(Text key, Iterable<Pokemon> values, Context context) throws IOException, InterruptedException {
        Pokemon tank = new Pokemon();
        Pokemon feeble = new Pokemon();
        Pokemon defender = new Pokemon();
        Pokemon slowpoke = new Pokemon();

        List<Pokemon> pokemons = new ArrayList<>();
        for (Pokemon p : values) {
            pokemons.add(p);
        }
        int size = pokemons.size();

        // min speed - take last element
        Collections.sort(pokemons, Pokemon.SpeedComparator);
        slowpoke = pokemons.get(size - 1);

        // min attack - take last element
        Collections.sort(pokemons, Pokemon.AttackComparator);
        feeble = pokemons.get(size - 1);

        // max hp - take first element
        Collections.sort(pokemons, Pokemon.HPComparator);
        tank = pokemons.get(0);

        // max defence - take first element
        Collections.sort(pokemons, Pokemon.DefenceComparator);
        defender = pokemons.get(0);

        result = new Text(tank.getName() + " " + feeble.getName() + " " +
                defender.getName() + slowpoke.getName());

        context.write(key, result);
    }
}
