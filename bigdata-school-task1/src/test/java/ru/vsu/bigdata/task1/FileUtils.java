package ru.vsu.bigdata.task1;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import org.apache.hadoop.io.DoubleWritable;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mrunit.types.Pair;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

public class FileUtils {

    private static final String POKEMON_XLSX_FILEPATH = "pokemon.xlsx";
    private static final String POKEMON_CSV_FILEPATH = "pokemonChart.csv";
    private static final int NUMBER_OF_STRING_FIELDS = 3;
    private static final int NUMBER_OF_NUMERIC_FIELDS = 7;

    public static List<Pair<NullWritable, Pokemon>> readXlsx() throws IOException, InvalidFormatException {

        List<Pair<NullWritable, Pokemon>> pokemons = new ArrayList<Pair<NullWritable, Pokemon>>();
        XSSFWorkbook wb = new XSSFWorkbook(POKEMON_XLSX_FILEPATH);

        Sheet sheet = wb.getSheetAt(0);
        Iterator<Row> rowIterator = sheet.rowIterator();
        while (rowIterator.hasNext()) {
            Row row = rowIterator.next();
            List<String> stringValues = new ArrayList<String>();
            List<Double> doubleValues = new ArrayList<Double>();

            Iterator<Cell> cellIterator = row.cellIterator();
            while (cellIterator.hasNext()) {
                Cell cell = cellIterator.next();
                CellType cellType = cell.getCellTypeEnum();
                switch (cellType) {
                    case STRING:
                        stringValues.add(cell.getStringCellValue());
                        break;
                    case NUMERIC:
                        doubleValues.add(cell.getNumericCellValue());
                        break;
                }
            }
            if (stringValues.size() == NUMBER_OF_STRING_FIELDS && doubleValues.size() == NUMBER_OF_NUMERIC_FIELDS) {
                Pokemon pokemon = new Pokemon(new Text(stringValues.get(0)), new Text(stringValues.get(1)),
                        new Text(stringValues.get(2)), new DoubleWritable(doubleValues.get(1)), new DoubleWritable(doubleValues.get(2)),
                        new DoubleWritable(doubleValues.get(3)), new DoubleWritable(doubleValues.get(4)), new DoubleWritable(doubleValues.get(5)),
                        new DoubleWritable(doubleValues.get(6)));
                pokemons.add(new Pair<NullWritable, Pokemon>(NullWritable.get(), pokemon));

            }
            else if (stringValues.size() == NUMBER_OF_STRING_FIELDS && doubleValues.size() == NUMBER_OF_NUMERIC_FIELDS -1) {
                Pokemon pokemon = new Pokemon(new Text(stringValues.get(0)), new Text(stringValues.get(1)),
                        new Text(stringValues.get(2)), new DoubleWritable(doubleValues.get(0)), new DoubleWritable(doubleValues.get(1)),
                        new DoubleWritable(doubleValues.get(2)), new DoubleWritable(doubleValues.get(3)), new DoubleWritable(doubleValues.get(4)),
                        new DoubleWritable(doubleValues.get(5)));
                pokemons.add(new Pair<NullWritable, Pokemon>(NullWritable.get(), pokemon));

            }

        }
        return pokemons;
    }

    public static void writeCsv(List<Pair<Text, Text>> input) throws IOException {

        BufferedWriter writer = Files.newBufferedWriter(Paths.get(POKEMON_CSV_FILEPATH));
        CSVPrinter csvPrinter = new CSVPrinter(writer, CSVFormat.DEFAULT.withHeader("Type", "Tank", "Feeble",
                "Defender", "Slowpoke"));
        ArrayList<String> line;
        for (Pair<Text, Text> pair : input) {
            line = new ArrayList<>();
            line.add(pair.getFirst().toString());
            String[] res = pair.getSecond().toString().split(",");
            line.addAll(Arrays.asList(res));
            csvPrinter.printRecord(line);
        }
        csvPrinter.flush();
        writer.close();
    }
}