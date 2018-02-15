package ru.vsu.bigdata_course.staging.stage1;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;

public class MStage1 extends Mapper<NullWritable, Text, Text, IntWritable> {
    @Override
    protected void map(NullWritable key, Text value, Context context) throws IOException, InterruptedException {
        String[] row = value.toString().split(" ", -1);
        for (String v : row)
            context.write(new Text(v), new IntWritable(1));
    }
}
