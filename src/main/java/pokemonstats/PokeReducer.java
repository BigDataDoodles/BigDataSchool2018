package pokemonstats;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;

public class PokeReducer extends Reducer<Text, Text, Text, Text>  {
    class ChosenPokemon{
        String name;
        int attr;
        ChosenPokemon(){
            name = "";
            attr = 0;
        }
        ChosenPokemon(String nm, int att) {
            name = nm;
            attr = att;
        }
        @Override
        public String toString(){
            return name;
        }
        public int greater(ChosenPokemon a){
            if(this.attr < a.attr ) {
                this.name = a.name;
                this.attr = a.attr;
            }
            return this.attr;
        }
        public int lesser(ChosenPokemon a){
            if(this.attr > a.attr ) {
                this.name = a.name;
                this.attr = a.attr;
            }
            return this.attr;
        }
    }

    @Override
    public void reduce(Text key, Iterable<Text> values, Context context)
            throws IOException, InterruptedException {

        ChosenPokemon tank = new ChosenPokemon();
        ChosenPokemon feeble = new ChosenPokemon();
        ChosenPokemon defender = new ChosenPokemon();
        ChosenPokemon slowpoke = new ChosenPokemon();

        for (Text value : values) {
            //values = hp, attack, defence, speed, name;
            String line = value.toString();
            String[] field = line.split(",");

            String name = field[4];

            tank.greater(new ChosenPokemon(name,Integer.parseInt(field[0])));
            feeble.lesser(new ChosenPokemon(name,Integer.parseInt(field[1])));
            defender.greater(new ChosenPokemon(name,Integer.parseInt(field[2])));
            slowpoke.lesser(new ChosenPokemon(name,Integer.parseInt(field[3])));
        }
        String val = tank.toString() + "," + feeble.toString()+ "," +defender.toString()+ ","+slowpoke.toString();
        context.write(key, new Text(val));
    }
}