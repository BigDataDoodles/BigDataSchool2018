package ru.vsu.bigdata.task1;
// Это не рабочая версия
import my.pokemon.MyPokemon;
import my.utils.TestUtils;
import org.junit.Test;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mrunit.mapreduce.MapReduceDriver;
import org.apache.hadoop.mrunit.types.Pair;
import org.junit.Test;
import ru.vsu.bigdata_course.staging.stage2.MStage2;
import ru.vsu.bigdata_course.staging.stage2.RStage2;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;


//TODO Необходимо создать еще один вспомогательный класс TestUtils
//TODO В классе TestUtils реализовать статические методы: чтение/запись данных из/в файл
//TODO Метод чтения файла, должен возвращать List<Pair<KEY_IN, VALUE_IN>>. Далее эта коллекция передается в качестве аргумента в метод addAll() в экземпляр класса MapReduceDriver
//TODO Метод записи файла, в качестве параметра принимает коллекцию возвращаемую методом run() экземпляра класса MapReduceDriver
public class PokemonTest {

    //TODO: absolute path is bad!
    private static String path = "L:/BigData/BigDataSchool2018/bigdata-school-task1/src/test/resources/pokemon.csv";
    @Test
    public void test() {

        LinkedList<Pair<Text, MyPokemon>> list = new LinkedList<Pair<Text, MyPokemon>>();
        try
        {
            list = TestUtils.readFromFileIntoMap(path);
        } catch ( IOException e)
        {
            e.printStackTrace();
        }
        MapReduceDriver<Text, MyPokemon, Text, MyPokemon, Text, Text> driver =
                MapReduceDriver.newMapReduceDriver(new MStage2(), new RStage2());
        driver.addAll( list );



//        List<Pair<Text, Text>> res = new LinkedList<Pair<Text, Text>>();
//
//        try {
//            res = driver.run();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        for (Pair<Text, Text> pair : res)
//            System.out.println(pair.getFirst() + "\t" + pair.getSecond());
//
     }

}
