package pokemonstats;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;

public class PokeMapper extends Mapper<LongWritable, Text, Text, Text> {
    public void map(LongWritable key, Text pokemon, Context context) throws IOException {
        String[] row = pokemon.toString().split(",");
        try{
            /*for(String val : row){
                System.out.print(val + ", ");
            }
            System.out.println();*/
            //0 #, 1 name, 2 type, 3 hp, 4 attack, 5 defence, 6 special attack, 7 special defence, 8 speed
            String tipa = row[2];
            String name = row[1];
            double hp = Double.parseDouble(row[3]);
            double attack = Double.parseDouble(row[4]) + Double.parseDouble(row[6]);
            double defence = Double.parseDouble(row[5]) + Double.parseDouble(row[7]);
            double speed = Double.parseDouble(row[8]);
            String val = String.valueOf(hp) + "," + String.valueOf(attack)+ "," +String.valueOf(defence)+ ","
                    +String.valueOf(speed)+ "," + name;

            context.write(new Text(tipa), new Text(val));
        }catch (Exception e){
            e.printStackTrace();
        }

    }
}
