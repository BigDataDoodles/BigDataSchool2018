package ru.vsu.bigdata.task1;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import org.apache.commons.collections.IteratorUtils;

public class PokemonReducer extends Reducer<Text, Pokemon, Text, Text> {

    private Text result = new Text();
    private static final String DELIM = ",";

    @Override
    public void reduce(Text key, Iterable<Pokemon> values, Context context) throws IOException, InterruptedException {
        Pokemon tank = new Pokemon();
        Pokemon feeble = new Pokemon();
        Pokemon defender = new Pokemon();
        Pokemon slowpoke = new Pokemon();


        for(Pokemon pokemon: values)
        {
            if(pokemon.getHp().compareTo(tank.getHp())>0)
            {
                tank = new Pokemon(pokemon);
            }

            if(pokemon.getDefence().compareTo(defender.getDefence())>0)
            {
                defender = new Pokemon(pokemon);
            }

            if(pokemon.getAttack().compareTo(feeble.getAttack())<0)
            {
                feeble = new Pokemon(pokemon);
            }

            if(pokemon.getSpeed().compareTo(slowpoke.getSpeed())<0)
            {
                slowpoke = new Pokemon(pokemon);
            }
        }
//        ArrayList<Pokemon> pokemons = new ArrayList<>();
//        int size = pokemons.size();
//
//        // min speed - take last element
//        pokemons.sort(Pokemon.SpeedComparator);
//        slowpoke = pokemons.get(size - 1);
//
//        // min attack - take last element
//        pokemons.sort(Pokemon.AttackComparator);
//        feeble = pokemons.get(size - 1);
//
//        // max hp - take first element
//        pokemons.sort(Pokemon.HPComparator);
//        tank = pokemons.get(0);
//
//        // max defence - take first element
//        pokemons.sort(Pokemon.DefenceComparator);
//        defender = pokemons.get(0);

        result = new Text(tank.getName() + DELIM + feeble.getName() + DELIM +
                defender.getName()+ DELIM + slowpoke.getName());

        context.write(key, result);
    }
}
