package ru.vsu.bigdata.sourse.task1;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;
import ru.vsu.bigdata.sourse.task1.Help.Pokemon;

import java.io.IOException;
import java.util.Iterator;

public class RPokemon extends Reducer<Text,Text,Text,Text>{

    @Override
    protected void reduce(Text key, Iterable<Text> values, Context context) throws IOException, InterruptedException {
        Pokemon tank = new Pokemon();
        Pokemon feeble = new Pokemon();
        Pokemon defender = new Pokemon();
        Pokemon slowpoke = new Pokemon();
        Iterator<Text> iterator = values.iterator();
        while (iterator.hasNext()){
            String[] characteristics = iterator.next().toString().split(",");
            //первичное приближение
            tank.setCharactiristic(Integer.parseInt(characteristics[1]),Integer.parseInt(characteristics[2]),
                    Integer.parseInt(characteristics[5]),Integer.parseInt(characteristics[6]),characteristics[0]);
            feeble.setCharactiristic(Integer.parseInt(characteristics[1]),Integer.parseInt(characteristics[2]),
                    Integer.parseInt(characteristics[5]),Integer.parseInt(characteristics[6]),characteristics[0]);
            defender.setCharactiristic(Integer.parseInt(characteristics[1]),Integer.parseInt(characteristics[2]),
                    Integer.parseInt(characteristics[5]),Integer.parseInt(characteristics[6]),characteristics[0]);
            slowpoke.setCharactiristic(Integer.parseInt(characteristics[1]),Integer.parseInt(characteristics[2]),
                    Integer.parseInt(characteristics[5]),Integer.parseInt(characteristics[6]),characteristics[0]);
            //нахождение нужных нам коэффициентов скорости, атаки, и т.д.
            if(tank.getCharactiristics(0) < Integer.parseInt(characteristics[1])){
                tank.setCharactitistic(0,Integer.parseInt(characteristics[1]));
                tank.setCharactiristic(characteristics[0]);
            }

            if(feeble.getCharactiristics(1) > Integer.parseInt(characteristics[2])){
                feeble.setCharactitistic(1,Integer.parseInt(characteristics[2]));
                feeble.setCharactiristic(characteristics[0]);
            }

            if(defender.getCharactiristics(2) < Integer.parseInt(characteristics[5])){
                defender.setCharactitistic(2,Integer.parseInt(characteristics[5]));
                defender.setCharactiristic(characteristics[0]);
            }

            if(slowpoke.getCharactiristics(3) < Integer.parseInt(characteristics[6])){
                slowpoke.setCharactitistic(3,Integer.parseInt(characteristics[6]));
                slowpoke.setCharactiristic(characteristics[0]);
            }

        }
        String result = tank.getName()+ "," + feeble.getName()+ "," + defender.getName()+ "," + slowpoke.getName();
        context.write(key,new Text(result));
    }

}
