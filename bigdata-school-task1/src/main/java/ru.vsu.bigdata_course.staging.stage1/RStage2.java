package ru.vsu.bigdata_course.staging.stage1;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapred.JobConf;
import org.apache.hadoop.mapred.OutputCollector;
import org.apache.hadoop.mapred.Reporter;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;
import java.util.Iterator;

public class RStage2 extends Reducer<Text, Pokemon, Text, Text> implements org.apache.hadoop.mapred.Reducer<Text, Pokemon, Text, Text> {

    @Override
    protected void reduce(Text key, Iterable<Pokemon> pokemons, Context context) throws IOException, InterruptedException {

        //Map<String, Pokemon> outPokemons = new HashMap<>();
        for (Pokemon pokemon : pokemons) {
            TestUtils.checkTank(pokemon.getType(), pokemon);
            TestUtils.checkFeeble(pokemon.getType(), pokemon);
            TestUtils.checkDefender(pokemon.getType(), pokemon);
            TestUtils.checkSlowpoke(pokemon.getType(), pokemon);

        }
        Text result = new Text("Key: " + key + ", tank: " + TestUtils.getTank(key.toString()) + ", feeble: " + TestUtils.getFeeble(key.toString()) + ", defender: " + TestUtils.getDefender(key.toString()) + ", slowpoke:" + TestUtils.getSlowpoke(key.toString()));
        context.write(key, result);

    }

    @Override
    public void reduce(Text text, Iterator<Pokemon> iterator, OutputCollector<Text, Text> outputCollector, Reporter reporter) throws IOException {

    }

    @Override
    public void close() throws IOException {

    }

    @Override
    public void configure(JobConf jobConf) {

    }
}


