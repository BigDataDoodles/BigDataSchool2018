package ru.vsu.bigdata_course.staging.stage1;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;
import java.util.List;

public class RStage1 extends Reducer<Text, Iterable<Pokemon>, Text, Text> {

    @Override
    protected void reduce(Text key, Iterable<Pokemon> values, Context context) throws IOException, InterruptedException {
        Pair<Integer, String>[] leaders = new Pair<Integer, String>[4];
        while (values.iterator().hasNext())
        {
            pokemons.add(values.iterator().next());
        }
        context.write(key, new IntWritable(sum));
    }
}