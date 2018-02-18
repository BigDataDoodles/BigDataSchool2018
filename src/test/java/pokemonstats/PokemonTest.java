
package pokemonstats;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mrunit.mapreduce.MapReduceDriver;
import org.junit.Test;

import java.io.IOException;

//TODO Необходимо создать еще один вспомогательный класс TestUtils
//TODO В классе TestUtils реализовать статические методы: чтение/запись данных из/в файл
//TODO Метод чтения файла, должен возвращать List<Pair<KEY_IN, VALUE_IN>>. Далее эта коллекция передается в качестве аргумента в метод addAll() в экземпляр класса MapReduceDriver
//TODO Метод записи файла, в качестве параметра принимает коллекцию возвращаемую методом run() экземпляра класса MapReduceDriver
public class PokemonTest {


    @Test
    public void test() throws IOException {
        MapReduceDriver<LongWritable, Text, Text, Text, Text, Text> driver =
                MapReduceDriver.newMapReduceDriver(new PokeMapper(), new PokeReducer());
        System.out.print(driver.toString());
        driver.addAll(TestUtil.readExcel("/home/blackjack/dev/java/BigDataSchool2018/src/main/resources/pokemon" +
                ".xlsx"));
        //System.out.print();
        //List<Pair<Text, Text>> res = driver.run();

        //TestUtil.OutputResult(res);
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
