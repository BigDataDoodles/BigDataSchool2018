package ru.vsu.bigdata_course;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mrunit.mapreduce.MapReduceDriver;
import org.apache.hadoop.mrunit.types.Pair;
import org.junit.Test;
import ru.vsu.bigdata_course.staging.stage1.MStage1;
import ru.vsu.bigdata_course.staging.stage1.RStage1;

import java.io.IOException;
import java.util.List;

public class Stage1Test {

    @Test
    public void test() throws IOException {
        MapReduceDriver<NullWritable, Text, Text, IntWritable, Text, IntWritable> driver =
                MapReduceDriver.newMapReduceDriver(new MStage1(), new RStage1());
        driver.addInput(NullWritable.get(), new Text("a a b b c a e"));
        List<Pair<Text, IntWritable>> res = driver.run();
        for (Pair<Text, IntWritable> pair : res)
            System.out.println(pair.getFirst() + "\t" + pair.getSecond());
    }

}
