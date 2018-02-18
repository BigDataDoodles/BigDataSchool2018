package ru.vsu.bigdata_course.staging.stage1;

import java.io.*;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mrunit.types.Pair;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;

public class MyTestUtils {

    public static List<Pair<NullWritable,Pokemon>> readExcel(String path) {
        FileInputStream file;
        HSSFWorkbook workbook;
        HSSFSheet sheet;
        try {
            file = new FileInputStream(new File(path));
            workbook = new HSSFWorkbook(file);
            sheet = workbook.getSheetAt(0);
        }
        catch(FileNotFoundException e) {
            System.out.println(e.getMessage());
            return null;
        }
        catch (IOException e) {
            System.out.println(e.getMessage());
            return null;
        }
        Pokemon pokemon;
        LinkedList<Pair<NullWritable,Pokemon>> listOfPokemon = new LinkedList<Pair<NullWritable,Pokemon>>();
        Iterator<Row> itr = sheet.iterator();
        Row row;
        //todo: use constants
        for(int i = 0; i < 1039;i++) {
            row = itr.next();
            pokemon = new Pokemon(row);
            listOfPokemon.add(new Pair<NullWritable,Pokemon>(NullWritable.get(), pokemon));
        }

        return listOfPokemon;
    }

    public static void OutputResult(List<Pair<Text, Text>> output) {
        File file = new File("bigdata-school-task1/src/test/resources/output.csv");
        try {
            FileOutputStream outputStream = new FileOutputStream(file);
            for (Pair<Text, Text> pair : output) {
                byte[] buffer = (pair.getFirst().toString() + "," + pair.getSecond().toString()).getBytes();

                outputStream.write(buffer, 0, buffer.length);
            }
        }catch (FileNotFoundException e){
            System.out.println(e.getMessage());
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

}
