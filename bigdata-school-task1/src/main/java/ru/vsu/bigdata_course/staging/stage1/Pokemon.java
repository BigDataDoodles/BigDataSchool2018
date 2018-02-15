package ru.vsu.bigdata_course.staging.stage1;

import org.apache.poi.ss.usermodel.Row;

public class Pokemon {
    public int number;
    public String name;
    public String type;
    public int hp;
    public int attack;
    public int defense;
    public int specialAttack;
    public int specialDeffense;
    public int speed;

    public Pokemon(Row row) {
        this.number = Integer.parseInt(row.getCell(0).toString());
        this.name = row.getCell(1).toString();
        this.type = row.getCell(2).toString();
        this.hp = Integer.parseInt(row.getCell(3).toString());
        this.attack = Integer.parseInt(row.getCell(4).toString());
        this.defense = Integer.parseInt(row.getCell(5).toString());
        this.specialAttack = Integer.parseInt(row.getCell(6).toString());
        this.specialDeffense = Integer.parseInt(row.getCell(7).toString());
        this.speed = Integer.parseInt(row.getCell(8).toString());
    }
}
