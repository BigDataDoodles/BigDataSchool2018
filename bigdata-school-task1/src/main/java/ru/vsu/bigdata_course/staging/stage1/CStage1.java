package ru.vsu.bigdata_course.staging.stage1;

import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;
import java.util.LinkedList;

public class CStage1 extends Reducer<Text, Pokemon, Text, Iterable<Pokemon>> {

    @Override
    public void reduce(Text key, Iterable<Pokemon> values, Context context)
    {
        LinkedList<Pokemon> pokemons = new LinkedList<Pokemon>();

        for(Pokemon p: values) {
            pokemons.add(p);
        }
        try {
            context.write(key, pokemons);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}