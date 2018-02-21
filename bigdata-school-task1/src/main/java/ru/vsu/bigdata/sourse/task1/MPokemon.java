package ru.vsu.bigdata.sourse.task1;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;
import ru.vsu.bigdata.sourse.task1.Help.Pokemon;

import java.io.IOException;

public class MPokemon extends Mapper<NullWritable,Pokemon,Text,Pokemon>{

    @Override
    protected void map(NullWritable key, Pokemon value, Context context) throws IOException, InterruptedException {
        context.write(value.getType(),value);
    }
}
