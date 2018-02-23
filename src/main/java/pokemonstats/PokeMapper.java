package pokemonstats;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

public class PokeMapper extends Mapper<Text, Text, Text, Pokemon> {
    @Override
    public void map(Text key, Text excelRow, Context context){
        try{
            Pokemon pokemon = new Pokemon(excelRow.toString());
            context.write(pokemon.getType(), pokemon);
        }catch (Exception e){
            e.printStackTrace();
        }

    }
}
