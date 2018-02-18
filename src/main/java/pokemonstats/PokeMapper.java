package pokemonstats;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;

public class PokeMapper extends Mapper<LongWritable, Text, Text, Text> {
    public void map(IntWritable key, Text pokemon, Context context) throws IOException {
        String[] row = pokemon.toString().split(",");
        try{
            //0 #, 1 name, 2 type, 3 hp, 4 attack, 5 defence, 6 special attack, 7 special defence, 8 speed
            String tipa = row[2];
            String name = row[1];
            int hp = Integer.parseInt(row[3]);
            int attack = Integer.parseInt(row[4]) + Integer.parseInt(row[6]);
            int defence = Integer.parseInt(row[5]) + Integer.parseInt(row[7]);
            int speed = Integer.parseInt(row[8]);
            String val = String.valueOf(hp) + "," + String.valueOf(attack)+ "," +String.valueOf(defence)+ ","
                    +String.valueOf(speed)+ "," + name;

            context.write(new Text(tipa), new Text(val));
        }catch (Exception e){
            e.printStackTrace();
        }

    }
}
