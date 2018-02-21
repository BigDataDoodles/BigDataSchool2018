package ru.vsu.bigdata.task1;

import org.junit.Test;
import ru.vsu.bigdata.sourse.task1.Help.Pokemon;
import ru.vsu.bigdata.sourse.task1.MPokemon;
import ru.vsu.bigdata.sourse.task1.RPokemon;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import java.io.IOException;
import org.apache.hadoop.mrunit.mapreduce.MapReduceDriver;
import ru.vsu.bigdata.task1.Help.TestUtils;

//TODO Необходимо создать еще один вспомогательный класс TestUtils
//TODO В классе TestUtils реализовать статические методы: чтение/запись данных из/в файл
//TODO Метод чтения файла, должен возвращать List<Pair<KEY_IN, VALUE_IN>>.
//TODO Далее эта коллекция передается в качестве аргумента в метод addAll() в экземпляр класса MapReduceDriver
//TODO Метод записи файла, в качестве параметра принимает коллекцию возвращаемую методом run() экземпляра класса MapReduceDriver
public class PokemonTest extends TestUtils {


    @Test
    public void test() throws IOException{
        MapReduceDriver<NullWritable, Pokemon, Text, Pokemon, Text, Text> driver =
                MapReduceDriver.newMapReduceDriver( new MPokemon(), new RPokemon());
        driver.addAll(read());
        write(driver.run());
    }

}
