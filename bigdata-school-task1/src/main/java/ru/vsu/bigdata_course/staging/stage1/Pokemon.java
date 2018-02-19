package ru.vsu.bigdata_course.staging.stage1;

import org.apache.hadoop.io.DoubleWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.io.WritableComparable;
import org.apache.poi.ss.usermodel.Row;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

public class Pokemon implements WritableComparable<Pokemon> {
    private Text number;
    private Text name;
    private Text type;
    private DoubleWritable hp;
    private DoubleWritable attack;
    private DoubleWritable defense;
    private DoubleWritable specialAttack;
    private DoubleWritable specialDefense;
    private DoubleWritable speed;

    public Text getNumber() { return number; }

    public Text getName() { return name; }

    public Text getType() { return type; }

    public DoubleWritable getHp() { return hp; }

    public DoubleWritable getAttack() { return attack; }

    public DoubleWritable getDefense() { return defense; }

    public DoubleWritable getSpecialAttack() { return specialAttack; }

    public DoubleWritable getSpecialDefense() { return specialDefense; }

    public DoubleWritable getSpeed() { return speed; }

    public void setNumber(Text number) { this.number = number; }

    public void setName(Text name) { this.name = name; }

    public void setType(Text type) { this.type = type; }

    public void setHp(DoubleWritable hp) { this.hp = hp; }

    public void setAttack(DoubleWritable attack) { this.attack = attack; }

    public void setDefense(DoubleWritable defense) { this.defense = defense; }

    public void setSpecialAttack(DoubleWritable specialAttack) { this.specialAttack = specialAttack; }

    public void setSpecialDefense(DoubleWritable specialDefense) { this.specialDefense = specialDefense; }

    public void setSpeed(DoubleWritable speed) { this.speed = speed; }

    public Pokemon() {
        this.number = new Text("");
        this.name = new Text("");
        this.type = new Text("");
        this.hp = new DoubleWritable(0);
        this.attack = new DoubleWritable(0);

        this.defense = new DoubleWritable(0);
        this.specialAttack = new DoubleWritable(0);
        this.specialDefense = new DoubleWritable(0);
        this.speed = new DoubleWritable(0);
    }

    public Pokemon(Row row) {
        try {
            //todo: use constants
            this.number = new Text(row.getCell(0).getStringCellValue());
            this.name = new Text(row.getCell(1).getStringCellValue());
            this.type = new Text(row.getCell(2).getStringCellValue());
            this.hp = new DoubleWritable(row.getCell(3).getNumericCellValue());
            this.attack = new DoubleWritable(row.getCell(4).getNumericCellValue());
            this.defense = new DoubleWritable(row.getCell(5).getNumericCellValue());
            this.specialAttack = new DoubleWritable(row.getCell(6).getNumericCellValue());
            this.specialDefense = new DoubleWritable(row.getCell(7).getNumericCellValue());
            this.speed = new DoubleWritable(row.getCell(8).getNumericCellValue()); } catch (IllegalStateException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String toString() {
        return String.format("Номер: %s\n Имя: %s\n Тип: %s\n Здоровье: %s\n Атака: %s\n Защита: %s\n Спец. атака: %s\n Спец. защита: %s\n Скорость: %s\n",
                this.number, this.name, this.type, this.hp, this.attack, this.defense, this.specialAttack, this.specialDefense, this.speed);
    }

    public void write(DataOutput dataOutput) throws IOException {
        this.number.write(dataOutput);
        this.name.write(dataOutput);
        this.type.write(dataOutput);
        this.hp.write(dataOutput);
        this.attack.write(dataOutput);
        this.defense.write(dataOutput);
        this.specialAttack.write(dataOutput);
        this.specialDefense.write(dataOutput);
        this.speed.write(dataOutput);
    }

    public void readFields(DataInput dataInput) throws IOException {
        this.number.readFields(dataInput);
        this.name.readFields(dataInput);
        this.type.readFields(dataInput);
        this.hp.readFields(dataInput);
        this.attack.readFields(dataInput);
        this.defense.readFields(dataInput);
        this.specialAttack.readFields(dataInput);
        this.specialDefense.readFields(dataInput);
        this.speed.readFields(dataInput);
    }

    public int compareTo(Pokemon o) {
        if (name.compareTo(o.name)==0)
        {
            return (name.compareTo(o.name));
        }
        else return (type.compareTo(o.type));
    }
}
