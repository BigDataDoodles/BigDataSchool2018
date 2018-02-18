
package pokemonstats;


import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mrunit.mapreduce.MapReduceDriver;
import org.apache.hadoop.mrunit.types.Pair;
import org.junit.Test;

import java.io.IOException;
import java.util.List;

//TODO Необходимо создать еще один вспомогательный класс TestUtils
//TODO В классе TestUtils реализовать статические методы: чтение/запись данных из/в файл
//TODO Метод чтения файла, должен возвращать List<Pair<KEY_IN, VALUE_IN>>. Далее эта коллекция передается в качестве аргумента в метод addAll() в экземпляр класса MapReduceDriver
//TODO Метод записи файла, в качестве параметра принимает коллекцию возвращаемую методом run() экземпляра класса MapReduceDriver
public class PokemonTest {
    @Test
    public void test() throws IOException {
        MapReduceDriver<LongWritable, Text, Text, Text, Text, Text> driver =
                new MapReduceDriver(new PokeMapper(), new PokeReducer());

        driver.addAll(TestUtil.readExcel("src/main/resources/pokemon.xlsx"));
        List<Pair<Text, Text>> res = driver.run();
        TestUtil.OutputResult(res);
    }

}
class Main {
    public static void main(String[] args) {
        PokemonTest test = new PokemonTest();
        try {
            test.test();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (IllegalStateException e) {
            e.printStackTrace();
        }
    }
}
