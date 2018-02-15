package ru.vsu.bigdata.task1;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mrunit.mapreduce.MapReduceDriver;
import org.apache.hadoop.mrunit.types.Pair;
import org.junit.Test;
import ru.vsu.bigdata_course.staging.stage1.CStage1;
import ru.vsu.bigdata_course.staging.stage1.MStage1;
import ru.vsu.bigdata_course.staging.stage1.RStage1;

import java.io.IOException;
import java.util.List;

//TODO Необходимо создать еще один вспомогательный класс TestUtils
//TODO В классе TestUtils реализовать статические методы: чтение/запись данных из/в файл
//TODO Метод чтения файла, должен возвращать List<Pair<KEY_IN, VALUE_IN>>. Далее эта коллекция передается в качестве аргумента в метод addAll() в экземпляр класса MapReduceDriver
//TODO Метод записи файла, в качестве параметра принимает коллекцию возвращаемую методом run() экземпляра класса MapReduceDriver
public class PokemonTest {

    @Test
    public void test() throws IOException {
        MapReduceDriver<NullWritable, Text, Text, IntWritable, Text, IntWritable> driver =
                MapReduceDriver.newMapReduceDriver(new MStage1(), new RStage1(), new CStage1());
        driver.addInput(NullWritable.get(), new Text("a a b b c a e"));
        List<Pair<Text, IntWritable>> res = driver.run();
        for (Pair<Text, IntWritable> pair : res)
            System.out.println(pair.getFirst() + "\t" + pair.getSecond());
    }

}
