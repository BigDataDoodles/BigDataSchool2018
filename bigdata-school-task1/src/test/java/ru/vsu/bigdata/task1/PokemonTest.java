package ru.vsu.bigdata.task1;

import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mrunit.mapreduce.MapReduceDriver;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.junit.Test;

import java.io.IOException;

//TODO Необходимо создать еще один вспомогательный класс TestUtils
//TODO В классе TestUtils реализовать статические методы: чтение/запись данных из/в файл
//TODO Метод чтения файла, должен возвращать List<Pair<KEY_IN, VALUE_IN>>. Далее эта коллекция передается в качестве аргумента в метод addAll() в экземпляр класса MapReduceDriver
//TODO Метод записи файла, в качестве параметра принимает коллекцию возвращаемую методом run() экземпляра класса MapReduceDriver
public class PokemonTest {

    @Test
    public void test() throws IOException, InvalidFormatException {
        MapReduceDriver<NullWritable,Pokemon,Text,Pokemon,Text,Text>  mapReduceDriver =
                new MapReduceDriver(new PokemonMapper(),new PokemonReducer());
        mapReduceDriver.addAll(FileUtils.readXlsx());
        FileUtils.writeCsv(mapReduceDriver.run());
    }

}
