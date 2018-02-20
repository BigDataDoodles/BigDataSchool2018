package ru.vsu.bigdata.task1;

import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mrunit.MapReduceDriver;
import org.apache.hadoop.mrunit.types.Pair;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.junit.Test;
import ru.vsu.bigdata_course.staging.stage1.*;

import java.io.IOException;
import java.util.List;

//TODO Необходимо создать еще один вспомогательный класс TestUtils
//TODO В классе TestUtils реализовать статические методы: чтение/запись данных из/в файл
//TODO Метод чтения файла, должен возвращать List<Pair<KEY_IN, VALUE_IN>>. Далее эта коллекция передается в качестве аргумента в метод addAll() в экземпляр класса MapReduceDriver
//TODO Метод записи файла, в качестве параметра принимает коллекцию возвращаемую методом run() экземпляра класса MapReduceDriver
public class PokemonTest {

    @Test
    public void test() throws IOException, InvalidFormatException {
        //String inPath = "D:\\Projects2\\BigDataSchool2018\\bigdata-school-task1\\src\\test\\resources\\pokemon.csv";
        String inPath = "./src/test/resources/pokemon.csv";
        String outPath = "./src/test/resources/out.csv";
        List<Pair<NullWritable, Pokemon>> pokemons = TestUtils.readFromFile(inPath);
        MStage2 mStage2 = new MStage2();
        RStage2 rStage2 = new RStage2();
        MapReduceDriver<NullWritable, Pokemon, Text, Pokemon, Text, Text> driver =
                MapReduceDriver.newMapReduceDriver();
        driver.setMapper(mStage2);
        driver.setReducer(rStage2);
        driver.addAll(pokemons);
        TestUtils.writeInFile((driver.run()), outPath);

    }
}


