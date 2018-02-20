package ru.vsu.bigdata_course.staging.stage1;

import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;


public class MStage2 extends Mapper<NullWritable, Pokemon, Text, Pokemon> {

    @Override
    protected void map(NullWritable key, Pokemon pokemon, Context context) throws IOException, InterruptedException {
        context.write(new Text(pokemon.getType()), pokemon);
    }

}





