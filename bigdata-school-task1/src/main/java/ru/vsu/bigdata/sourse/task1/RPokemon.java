package ru.vsu.bigdata.sourse.task1;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;
import ru.vsu.bigdata.sourse.task1.Help.Pokemon;

import java.io.IOException;


public class RPokemon extends Reducer<Text,Pokemon,Text,Text>{

    @Override
    protected void reduce(Text key, Iterable<Pokemon> values, Context context) throws IOException, InterruptedException {
        Pokemon tank = new Pokemon();
        Pokemon feeble = new Pokemon();
        Pokemon defender = new Pokemon();
        Pokemon slowPoke = new Pokemon();
        for(Pokemon it: values){

            if(Double.parseDouble(tank.getHP().toString()) < Double.parseDouble(it.getHP().toString())){
                tank.setHP(it.getHP());
                tank.setName(it.getName());
            }

            if(Double.parseDouble(feeble.getAttack().toString()) > Double.parseDouble(it.getAttack().toString())){
                feeble.setAttack(it.getAttack());
                feeble.setName(it.getName());
            }

            if(Double.parseDouble(defender.getDef().toString()) < Double.parseDouble(it.getDef().toString())){
                defender.setName(it.getName());
                defender.setDef(it.getDef());
            }

            if(Double.parseDouble(slowPoke.getSpeed().toString()) > Double.parseDouble(it.getSpeed().toString())){
                slowPoke.setName(it.getName());
                slowPoke.setSpeed(it.getSpeed());
            }
        }
        String res = tank.getName()+ "," + feeble.getName()+ "," + defender.getName()+ "," + slowPoke.getName();
        context.write(key,new Text(res));
    }
}
