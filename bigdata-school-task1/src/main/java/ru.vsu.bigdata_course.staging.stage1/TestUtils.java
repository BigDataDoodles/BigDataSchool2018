package ru.vsu.bigdata_course.staging.stage1;

import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mrunit.types.Pair;

import java.io.*;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class TestUtils {

    //TODO: Почему ты выбрал такую структыру, и почему их 4??
    private static Map<String, Pokemon> tank = new HashMap<>();
    private static Map<String, Pokemon> feeble = new HashMap<>();
    private static Map<String, Pokemon> defender = new HashMap<>();
    private static Map<String, Pokemon> slowpoke = new HashMap<>();


    static Pokemon getTank(String key) {
        return tank.get(key);
    }

    static Pokemon getFeeble(String key) {
        return feeble.get(key);
    }

    static Pokemon getDefender(String key) {
        return defender.get(key);
    }

    static Pokemon getSlowpoke(String key) {
        return slowpoke.get(key);
    }


    public static void writeInFile(List<Pair<Text, Text>> pokemons, String path) throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter(path));
        for (Pair<Text, Text> pokemon : pokemons) {

            {
                writer.write(pokemon.toString());
            }
            writer.close();
        }
    }

    public static List<Pair<NullWritable, Pokemon>> readFromFile(String path) throws IOException {

        List<Pair<NullWritable, Pokemon>> pokemons = new LinkedList<>();
        BufferedReader reader = new BufferedReader(new FileReader(
                path));
        String line;
        while ((line = reader.readLine()) != null) {
            String[] row = line.split(";");
            Pokemon pokemon = new Pokemon(row);
            pokemons.add(new Pair<>(NullWritable.get(), pokemon));
        }
        reader.close();
        return pokemons;
    }

    static void findRightPokemons(Iterable<List<Pokemon>> values, double maxHp, double minAttack, double maxDefense, double minSpeed, List<Pokemon> outPokemons) {
        for (Pokemon pokemon : values.iterator().next()
                ) {
            if (minAttack == 0) minAttack = pokemon.getAttack();
            if (minSpeed == 0) minSpeed = pokemon.getSpeed();
            if (maxHp < pokemon.getHp()) {
                maxHp = pokemon.getHp();
                outPokemons.add(pokemon);
            }
            if (minAttack < pokemon.getAttack()) {
                minAttack = pokemon.getAttack();
                outPokemons.add(pokemon);
            }
            if (maxDefense > pokemon.getDefence()) {
                maxDefense = pokemon.getDefence();
                outPokemons.add(pokemon);
            }
            if (minSpeed < pokemon.getSpeed()) {
                minSpeed = pokemon.getSpeed();
                outPokemons.add(pokemon);
            }

        }

    }

    static void checkTank(String type, Pokemon pokemon) {
        if (tank.get(type).getHp() < pokemon.getHp()) {
            tank.put(type, pokemon);
        }
    }

    static void checkFeeble(String type, Pokemon pokemon) {
        if (feeble.get(type).getAttack() < pokemon.getAttack()) {
            feeble.put(type, pokemon);
        }
    }

    static void checkDefender(String type, Pokemon pokemon) {
        if (defender.get(type).getDefence() < pokemon.getDefence()) {
            defender.put(type, pokemon);
        }
    }

    static void checkSlowpoke(String type, Pokemon pokemon) {
        if (slowpoke.get(type).getSpeed() < pokemon.getSpeed()) {
            slowpoke.put(type, pokemon);
        }
    }
}




