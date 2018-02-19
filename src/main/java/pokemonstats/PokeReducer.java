package pokemonstats;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;

public class PokeReducer extends Reducer<Text, Text, Text, Text>  {
    //TODO: Why is the ChosenPokemon inner class? It should be just class
    class ChosenPokemon{
        String name;
        double attr;
        ChosenPokemon(){
            name = "n/a";
            attr = 0;
        }
        ChosenPokemon(String nm, double att) {
            name = nm;
            attr = att;
        }
        @Override
        public String toString(){
            return name;
        }
        public double greater(ChosenPokemon a){
            if(this.attr < a.attr || this.name.equals("n/a")) {
                this.name = a.name;
                this.attr = a.attr;
            }
            return this.attr;
        }
        public double lesser(ChosenPokemon a){
            if(this.attr > a.attr || this.name.equals("n/a")) {
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

            tank.greater(new ChosenPokemon(name,Double.parseDouble(field[0])));
            feeble.lesser(new ChosenPokemon(name,Double.parseDouble(field[1])));
            defender.greater(new ChosenPokemon(name,Double.parseDouble(field[2])));
            slowpoke.lesser(new ChosenPokemon(name,Double.parseDouble(field[3])));
        }
        String val = tank.toString() + "," + feeble.toString()+ ","+defender.toString()+ ","+slowpoke.toString();
        context.write(key, new Text(val));
    }
}