package ru.vsu.bigdata.task1.Help;

import org.apache.hadoop.io.*;
import org.apache.hadoop.mrunit.types.Pair;

import java.util.ArrayList;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.hadoop.io.NullWritable;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;

public class TestUtils {

    public static void write(List<Pair<Text,Text>> listOut){
        for (Pair<Text, Text> pair : listOut)
            System.out.println(pair.getFirst() + "\t" + pair.getSecond());
    }


    public static List<Pair<NullWritable,Text>> read(){
        List<Pair<NullWritable,Text>> result = new ArrayList<Pair<NullWritable, Text>>() {};
        String rowOfxlsx = "";
        //инициализация потоков
        InputStream inputStream = null;
        HSSFWorkbook workBook = null;
        try {
            inputStream = new FileInputStream("pokemon.xlsx");
            workBook = new HSSFWorkbook(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
        //разбираем первый лист входного файла на объектную модель
        Sheet sheet = workBook.getSheetAt(0);
        Iterator<Row> it = sheet.iterator();
        //проходим по всему листу
        while (it.hasNext()) {
            Row row = it.next();
            Iterator<Cell> cells = row.iterator();
            while (cells.hasNext()) {
                Cell cell = cells.next();
                int cellType = cell.getCellType();
                //перебираем возможные типы ячеек
                switch (cellType) {
                    case Cell.CELL_TYPE_STRING:
                        rowOfxlsx += cell.getStringCellValue() + ",";
                        break;
                    case Cell.CELL_TYPE_NUMERIC:
                        rowOfxlsx += cell.getNumericCellValue() + ",";
                        break;
                }
            }
            Pair<NullWritable,Text> pair = new Pair<NullWritable, Text>(NullWritable.get(),new Text(rowOfxlsx));
            result.add(pair);
        }

        return result;
    }
}
