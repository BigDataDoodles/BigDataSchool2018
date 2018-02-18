package pokemonstats;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mrunit.types.Pair;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.*;
import java.util.LinkedList;
import java.util.List;

public class TestUtil {
    /*private void readFile(String excelFileName) throws FileNotFoundException, IOException {
        XSSFWorkbook workbook = new XSSFWorkbook(new FileInputStream(excelFileName));
        if (workbook.getNumberOfSheets() > 1){
            System.out.println("Please make sure there is only one sheet in the excel workbook.");
        }
        XSSFSheet sheet = workbook.getSheetAt(0);
        int numOfPhysRows = sheet.getPhysicalNumberOfRows();
        XSSFRow row;
        XSSFCell num;
        for(int y = 1;y < numOfPhysRows;y++){    //start at the 2nd row since 1st should be category names
            row = sheet.getRow(y);
            poNum = row.getCell(1);
            item = new Item(Integer.parseInt(poNum.getStringCellValue());
            itemList.add(item);
            y++;
        }
    }

    private int poiConvertFromStringtoInt(XSSFCell cell){
        int x = Integer.parseInt(Double.toString(cell.getNumericCellValue()));
        return x;
    }*/
    public static List<Pair<LongWritable,Text>> readExcel(String path) {
        LinkedList<Pair<LongWritable,Text>> pokemonRow = new LinkedList<Pair<LongWritable,Text>>();
        /*FileInputStream file;
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

        Iterator<Row> itr = sheet.iterator();
        Row row;
        for(int i = 0; i < sheet.getPhysicalNumberOfRows();i++) {
            row = itr.next();
            //System.out.print(row.getCell(0).getStringCellValue()+", ");
            //System.out.print(row.getCell(1).getStringCellValue()+", ");
            System.out.print(row.getCell(2).getStringCellValue()+", ");
            System.out.print(row.getCell(3).getNumericCellValue()+", ");
            System.out.print(row.getCell(4).getNumericCellValue()+", ");
            System.out.print(row.getCell(5).getNumericCellValue()+", ");
            System.out.print(row.getCell(6).getNumericCellValue()+", ");
            System.out.print(row.getCell(7).getNumericCellValue()+", ");
            System.out.print(row.getCell(8).getNumericCellValue()+", ");
            System.out.println();


            String rowData = "";
            //rowData += ","+String.valueOf(row.getCell(1).getStringCellValue());
            //rowData += ","+String.valueOf(row.getCell(2).getStringCellValue());
            rowData += ","+String.valueOf(row.getCell(3).getNumericCellValue());
            rowData += ","+String.valueOf(row.getCell(4).getNumericCellValue());
            rowData += ","+String.valueOf(row.getCell(5).getNumericCellValue());
            rowData += ","+String.valueOf(row.getCell(6).getNumericCellValue());
            rowData += ","+String.valueOf(row.getCell(7).getNumericCellValue());
            rowData += ","+String.valueOf(row.getCell(8).getNumericCellValue());
            rowData += ","+String.valueOf(row.getCell(8).getNumericCellValue());
            pokemonRow.add(new Pair<LongWritable,Text>(new LongWritable(), new Text(rowData)));
        }
        */
        try  {
            FileInputStream file = new FileInputStream(new File(path));
            Workbook wb = new XSSFWorkbook(file);
            Sheet sheet = wb.getSheetAt(0);
            System.out.println(wb.getSheetName(0));
            for (Row row : sheet) {
                for (Cell cell : row) {
                    System.out.print(cell+", ");
                }
            }

        }catch(FileNotFoundException e) {
            System.out.println(e.getMessage());
            return null;
        }
        catch (IOException e) {
            System.out.println(e.getMessage());
            return null;
        }
        return pokemonRow;
    }

    public static void OutputResult(List<Pair<Text, Text>> output) {
        File file = new File("/home/blackjack/dev/java/BigDataSchool2018/src/main/resources/output.csv");
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
