package ru.vsu.bigdata_course.staging.stage1;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Iterator;
import java.util.LinkedList;

import javafx.util.Pair;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

public class TestUtils {

    public static LinkedList<Pair<String,Pokemon>> readExcel(String path) {
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
        LinkedList<Pair<String,Pokemon>> listOfPokemon = new LinkedList<Pair<String,Pokemon>>();
        Iterator<Row> itr = sheet.iterator();
        Row row = sheet.getRow(0);
        pokemon = new Pokemon(row);
        listOfPokemon.add(new Pair<String,Pokemon>(pokemon.type, pokemon));
        while(itr.hasNext()) {
            row = itr.next();
            pokemon = new Pokemon(row);
            listOfPokemon.add(new Pair<String,Pokemon>(pokemon.type, pokemon));
        }

        return listOfPokemon;
    }

    public static void OutputResult(String path) {

    }

}
