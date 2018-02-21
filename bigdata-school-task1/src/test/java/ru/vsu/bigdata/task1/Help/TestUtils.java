package ru.vsu.bigdata.task1.Help;


import au.com.bytecode.opencsv.CSVWriter;
import org.apache.hadoop.io.*;
import org.apache.hadoop.mrunit.types.Pair;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.*;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import ru.vsu.bigdata.sourse.task1.Help.Pokemon;


import java.util.Iterator;

public class TestUtils {

    /**
     * Запись в csv файл
     * @param listOut
     */
    public static void write(List<Pair<Text,Text>> listOut) throws IOException {

        List<String[]> result = new ArrayList<String[]>();
        int i = 0;
        for (Pair<Text, Text> pair : listOut) {
            String buf = pair.getFirst().toString() + "," + pair.getSecond();
            result.add(buf.split(","));
            i++;
        }

        String csv = "./src/test/resources/pokemon.csv";
        String[] head = new String[5];
        head[0] = "Type";
        head[1] = "Tank";
        head[2] = "Feeble";
        head[3] = "Defender";
        head[4] = "Slowpoke";
        CSVWriter writer = new CSVWriter(new FileWriter(csv));
        writer.writeNext(head);
        writer.writeAll(result);
        writer.close();
    }

    /**
     * чтение excel файла
     * @return
     * @throws IOException
     */
    public static List<Pair<NullWritable,Pokemon>> read() throws IOException {
        List<Pair<NullWritable,Pokemon>> result = new ArrayList<Pair<NullWritable, Pokemon>>() {};
        Pair<NullWritable,Pokemon> pair;

        String stringFromExls = "";
        String excelFilePath = "./src/test/resources/pokemon.xlsx";

        // переменная для работы с новым типом excel файлов
        XSSFWorkbook workbook = null;
        InputStream inputStream;
        //инициализация и подключение ресурс файла
        try{
            inputStream = new FileInputStream(excelFilePath);
            workbook = new XSSFWorkbook(inputStream);
        } catch (IOException e){
            e.printStackTrace();
        }

        //получение первого листа и создание итератора для прохода по строкам файла
        Sheet sheet = workbook.getSheetAt(0);
        Iterator<Row> it = sheet.rowIterator();

        //проход по строкам
        while (it.hasNext()){
            Row row = it.next();
            Iterator<Cell> cells = row.cellIterator();

            //проход по отдельной строке
            while (cells.hasNext()){
                Cell cell = cells.next();
                int cellType = cell.getCellType();
                switch (cellType){
                    case 0: stringFromExls += cell.getNumericCellValue() + ",";
                            break;
                    case 1: stringFromExls += cell.getStringCellValue() + ",";
                            break;

                }
            }
            String[] buf = stringFromExls.split(",");
            //проверка на валидность
            if(isValid(buf)){
                Pokemon poke = new Pokemon(new DoubleWritable(Double.parseDouble(buf[5])),
                        new DoubleWritable(Double.parseDouble(buf[8])),new DoubleWritable(Double.parseDouble(buf[4])),
                        new DoubleWritable(Double.parseDouble(buf[9])), new Text(buf[1]),new Text(buf[2]));

                pair = new Pair<NullWritable, Pokemon>(NullWritable.get(),poke);
                stringFromExls = "";
                result.add(pair);
            }else{
                stringFromExls = "";
            }
        }
        return result;
    }

    /**
     * Проверка на валидность
     * @param str
     * @return
     */
    private static boolean isValid(String[] str){
        if(str.length < 8) {
            return false;
        }
        return true;
    }

}


