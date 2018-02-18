package ru.vsu.bigdata_course.staging.stage2;


import my.pokemon.MyPokemon;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;
import java.util.LinkedList;

public class MStage2 extends Mapper<Text, MyPokemon, Text, MyPokemon>{
    @Override
    protected void map(Text key, MyPokemon value, Context context) throws IOException, InterruptedException {
        //for (MyPokemon v : value)
            context.write(new Text(value.getType()), value);
    }
}
