package ru.vsu.bigdata_course.staging.stage1;

import org.apache.hadoop.io.DoubleWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;

public class RStage1 extends Reducer<Text, Pokemon, Text, Text> {

    @Override
    protected void reduce(Text key, Iterable<Pokemon> values, Context context) throws IOException, InterruptedException {
        DoubleWritable maxHP = new DoubleWritable(0);
        DoubleWritable minAttack = new DoubleWritable(Double.MAX_VALUE);
        DoubleWritable maxDeffense = new DoubleWritable(0);
        DoubleWritable minSpeed = new DoubleWritable(Double.MAX_VALUE);;
        for(Pokemon p: values) {
            if (p.getHp().compareTo(maxHP) > 0) maxHP = p.getHp();
            if (p.getAttack().compareTo(minAttack) < 0) minAttack = p.getAttack();
            if (p.getDefense().compareTo(maxDeffense) > 0) maxDeffense = p.getDefense();
            if (p.getSpeed().compareTo(minSpeed) < 0) minSpeed = p.getSpeed();
        }
        context.write(key,new Text(String.format("%s,%s,%s,%s",maxHP, minAttack, maxDeffense, minSpeed)));
    }
}