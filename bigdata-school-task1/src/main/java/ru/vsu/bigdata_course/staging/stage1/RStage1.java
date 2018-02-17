package ru.vsu.bigdata_course.staging.stage1;

import org.apache.hadoop.io.DoubleWritable;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import org.apache.hadoop.mrunit.types.Pair;
import java.io.IOException;
import java.util.List;

public class RStage1 extends Reducer<Text, Pokemon, Text, Text> {

    @Override
    protected void reduce(Text key, Iterable<Pokemon> values, Context context) throws IOException, InterruptedException {
        DoubleWritable maxHP = new DoubleWritable(0);
        DoubleWritable minAttack = new DoubleWritable(Double.MAX_VALUE);
        DoubleWritable maxDeffense = new DoubleWritable(0);
        DoubleWritable minSpeed = new DoubleWritable(Double.MAX_VALUE);;
        for(Pokemon p: values) {
            if (p.hp.compareTo(maxHP) > 0) maxHP = p.hp;
            if (p.attack.compareTo(minAttack) < 0) minAttack = p.attack;
            if (p.defense.compareTo(maxDeffense) > 0) maxDeffense = p.defense;
            if (p.speed.compareTo(minSpeed) < 0) minSpeed = p.speed;
        }
        context.write(key,new Text("" + maxHP + "," + minAttack + "," + maxDeffense + "," + minSpeed + "\n") );
    }
}