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

        String csv = "C:\\Users\\Pavel\\IdeaProjects\\BigDataSchool2018\\bigdata-school-task1\\src\\test\\resources\\data.csv";
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
    public static List<Pair<NullWritable,Text>> read() throws IOException {
        List<Pair<NullWritable,Text>> result = new ArrayList<Pair<NullWritable, Text>>() {};
        Pair<NullWritable,Text> pair;
        String stringFromExls = "";
        String excelFilePath = "C:\\Users\\Pavel\\IdeaProjects\\BigDataSchool2018\\bigdata-school-task1\\src\\test\\resources\\pokemon.xlsx";
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

            //проверка на валидность и удаление номера и побочного эллемента
            if(isValid(stringFromExls)){
                stringFromExls = removeAt(3,stringFromExls);
                stringFromExls = removeAt(0,stringFromExls);
                pair = new Pair<NullWritable, Text>(NullWritable.get(),new Text(stringFromExls));
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
    private static boolean isValid(String str){
        String[] list = str.split(",");
        if(list.length < 8) {
            return false;
        }
        return true;
    }

    /** удаление из массива строк элемента по ключу
     * @param key
     * @param string
     * @return
     */
    private static String removeAt(int key, String string){
        String[] list = string.split(",");
        String result = "";
        System.arraycopy(list,key+1,list,key,list.length-1-key);
        for (int i = 0; i < list.length-1;i++) {
            result += list[i];
            if(i != list.length-2)
            result += ",";
        }
        return result;
    }

}


