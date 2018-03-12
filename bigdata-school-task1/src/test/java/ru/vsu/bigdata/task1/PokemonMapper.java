package ru.vsu.bigdata.task1;

import com.graphbuilder.curve.NURBSpline;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;

public class PokemonMapper extends Mapper<NullWritable, Pokemon, Text, Pokemon> {

    @Override
    public void map(NullWritable key, Pokemon value, Context context) throws IOException, InterruptedException {
        context.write(value.getType(), value);
    }
}
