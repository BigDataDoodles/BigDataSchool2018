package ru.vsu.bigdata_course.staging.stage1;

import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapred.JobConf;
import org.apache.hadoop.mapred.OutputCollector;
import org.apache.hadoop.mapred.Reporter;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;


public class MStage2 extends Mapper<NullWritable, Pokemon, Text, Pokemon> implements org.apache.hadoop.mapred.Mapper<NullWritable, Pokemon, Text, Pokemon> {

    @Override
    protected void map(NullWritable key, Pokemon pokemon, Context context) throws IOException, InterruptedException {
        context.write(new Text(pokemon.getType()), pokemon);
    }

    @Override
    public void map(NullWritable nullWritable, Pokemon pokemon, OutputCollector<Text, Pokemon> outputCollector, Reporter reporter) throws IOException {

    }

    @Override
    public void close() throws IOException {

    }

    @Override
    public void configure(JobConf jobConf) {

    }
}





