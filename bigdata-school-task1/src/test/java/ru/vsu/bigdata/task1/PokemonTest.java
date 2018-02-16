package ru.vsu.bigdata.task1;

import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mrunit.mapreduce.MapReduceDriver;
import org.apache.hadoop.mrunit.types.Pair;
import org.junit.Test;
import ru.vsu.bigdata_course.staging.stage1.*;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

//TODO Необходимо создать еще один вспомогательный класс TestUtils
//TODO В классе TestUtils реализовать статические методы: чтение/запись данных из/в файл
//TODO Метод чтения файла, должен возвращать List<Pair<KEY_IN, VALUE_IN>>. Далее эта коллекция передается в качестве аргумента в метод addAll() в экземпляр класса MapReduceDriver
//TODO Метод записи файла, в качестве параметра принимает коллекцию возвращаемую методом run() экземпляра класса MapReduceDriver
public class PokemonTest {


    @Test
    public void test() throws IOException {
        MapReduceDriver<NullWritable, Pokemon, Text, Pokemon, Text, Text> driver =
                MapReduceDriver.newMapReduceDriver(new MStage1(), new RStage1());
        driver.addAll(MyTestUtils.readExcel("bigdata-school-task1/src/test/resources/pokemon.xls"));
        List<Pair<Text, Text>> res = driver.run();

        MyTestUtils.OutputResult(res);
    }

}
class Main {
    public static void main(String[] args) {
        PokemonTest test = new PokemonTest();
        try {
            test.test();
        } catch(IOException e) {
            e.printStackTrace();
        }
    }
}