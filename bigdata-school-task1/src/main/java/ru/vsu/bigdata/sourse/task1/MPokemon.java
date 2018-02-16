package ru.vsu.bigdata.sourse.task1;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;

public class MPokemon extends Mapper<NullWritable,Text,Text,Text>{

    @Override
    protected void map(NullWritable key, Text value, Context context) throws IOException, InterruptedException {
        String[] row = value.toString().split(",");
        String first = "", second = "";
        for(int i = 0;i<row.length;i++){
            if(i == 1){
                first = row[i];
            }else {
                if (i == row.length-1)
                    second += row[i];
                second += row[i] + ",";
            }
        }
        context.write(new Text(first),new Text(second));
    }
}
