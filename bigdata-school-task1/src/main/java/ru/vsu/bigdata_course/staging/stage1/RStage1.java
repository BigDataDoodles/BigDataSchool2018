package ru.vsu.bigdata_course.staging.stage1;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import org.apache.hadoop.mrunit.types.Pair;
import java.io.IOException;
import java.util.List;

public class RStage1 extends Reducer<Text, Pokemon, Text, Text> {

    @Override
    protected void reduce(Text key, Iterable<Pokemon> values, Context context) throws IOException, InterruptedException {
        double maxHP = 0;
        double minAttack = Integer.MAX_VALUE;
        double maxDeffense = 0;
        double minSpeed = Integer.MIN_VALUE;
        for(Pokemon p: values) {
            if (p.hp > maxHP) maxHP = p.hp;
            if (p.attack < minAttack) minAttack = p.attack;
            if (p.defense > maxDeffense) maxDeffense = p.defense;
            if (p.speed < minSpeed) minSpeed = p.speed;
        }
        context.write(key,new Text("" + maxHP + "," + minAttack + "," + maxDeffense + "," + minSpeed) );
    }
}