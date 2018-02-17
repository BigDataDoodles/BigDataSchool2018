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

        //первичное приближение
        tank.setCharactiristic(20,100,20,100,"example");
        feeble.setCharactiristic(20,100,20,100,"example");
        defender.setCharactiristic(20,100,20,100,"example");
        slowpoke.setCharactiristic(20,100,20,100,"example");

        for(Text str: values) {
            String[] characteristics = str.toString().split(",");

            //нахождение нужных нам коэффициентов скорости, атаки, и т.д.
            if (tank.getCharactiristics(0) < Double.parseDouble(characteristics[1])) {
                tank.setCharactitistic(0, Double.parseDouble(characteristics[1]));
                tank.setCharactiristic(characteristics[0]);
            }

            if (feeble.getCharactiristics(1) > Double.parseDouble(characteristics[2])) {
                feeble.setCharactitistic(1, Double.parseDouble(characteristics[2]));
                feeble.setCharactiristic(characteristics[0]);
            }

            if (defender.getCharactiristics(2) < Double.parseDouble(characteristics[5])) {
                defender.setCharactitistic(2, Double.parseDouble(characteristics[5]));
                defender.setCharactiristic(characteristics[0]);
            }

            if (slowpoke.getCharactiristics(3) < Double.parseDouble(characteristics[6])) {
                slowpoke.setCharactitistic(3, Double.parseDouble(characteristics[6]));
                slowpoke.setCharactiristic(characteristics[0]);
            }

        }
        String result = tank.getName()+ "," + feeble.getName()+ "," + defender.getName()+ "," + slowpoke.getName();
        context.write(key,new Text(result));
    }

}
