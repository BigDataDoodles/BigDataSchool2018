package ru.vsu.bigdata_course.staging.stage1;

import org.apache.poi.ss.usermodel.Row;

import java.io.Serializable;

public class Pokemon implements Serializable {
    public String number;
    public String name;
    public String type;
    public double hp;
    public double attack;
    public double defense;
    public double specialAttack;
    public double specialDeffense;
    public double speed;

    public Pokemon(Row row) {
        int i = 0;
        try {
            i = 0;
        this.number = row.getCell(0).getStringCellValue();
        ++i;
        this.name = row.getCell(1).getStringCellValue();
            ++i;
        this.type = row.getCell(2).getStringCellValue();
            ++i;
        this.hp = row.getCell(3).getNumericCellValue();
            ++i;
        this.attack = row.getCell(4).getNumericCellValue();
            ++i;
        this.defense = row.getCell(5).getNumericCellValue();
            ++i;
        this.specialAttack = row.getCell(6).getNumericCellValue();
            ++i;
        this.specialDeffense = row.getCell(7).getNumericCellValue();
            ++i;
        this.speed = row.getCell(8).getNumericCellValue(); } catch (IllegalStateException e) {
            System.out.println(row.getRowNum());
            System.out.println(i);
        }
    }

    @Override
    public String toString() {
        return "" + this.number + " " + this.name + " " + this.type + " " + this.hp + " " + this.attack + " "
                + this.defense + " " + this.specialAttack + " " + this.specialDeffense + " " + this.speed + "\n";
    }
}
