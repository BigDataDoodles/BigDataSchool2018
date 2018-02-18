package ru.vsu.bigdata_course.staging.stage2;

import javafx.util.Pair;
import my.pokemon.MyPokemon;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;
import java.util.LinkedList;

public class RStage2 extends Reducer<Text, MyPokemon, Text, Text>{
    @Override
    protected void reduce(Text key, Iterable<MyPokemon> values, Context context) throws IOException, InterruptedException {
        // максимальное хп
        int maxHp = 0;
        // покемон с паксимоальным хп
        MyPokemon maxP = null;

        for ( MyPokemon p: values
             ) {
            if (maxHp < p.getHp())
            {
                maxHp = p.getHp();
                maxP = p;
            }
        }

        context.write(key, new Text(maxP.getName()));
    }
}
