package ru.vsu.bigdata_course.staging.stage1;

import org.apache.hadoop.io.DoubleWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.io.WritableComparable;
import org.apache.poi.ss.usermodel.Row;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

public class Pokemon implements WritableComparable<Pokemon> {
    public Text number;
    public Text name;
    public Text type;
    public DoubleWritable hp;
    public DoubleWritable attack;
    public DoubleWritable defense;
    public DoubleWritable specialAttack;
    public DoubleWritable specialDeffense;
    public DoubleWritable speed;

    public Pokemon() {
        this.number = new Text("");
        this.name = new Text("");
        this.type = new Text("");
        this.hp = new DoubleWritable(0);
        this.attack = new DoubleWritable(0);;
        this.defense = new DoubleWritable(0);;
        this.specialAttack = new DoubleWritable(0);;
        this.specialDeffense = new DoubleWritable(0);;
        this.speed = new DoubleWritable(0);;
    }

    public Pokemon(Row row) {
        try {
            this.number = new Text(row.getCell(0).getStringCellValue());
            this.name = new Text(row.getCell(1).getStringCellValue());
            this.type = new Text(row.getCell(2).getStringCellValue());
            this.hp = new DoubleWritable(row.getCell(3).getNumericCellValue());
            this.attack = new DoubleWritable(row.getCell(4).getNumericCellValue());
            this.defense = new DoubleWritable(row.getCell(5).getNumericCellValue());
            this.specialAttack = new DoubleWritable(row.getCell(6).getNumericCellValue());
            this.specialDeffense = new DoubleWritable(row.getCell(7).getNumericCellValue());
            this.speed = new DoubleWritable(row.getCell(8).getNumericCellValue()); } catch (IllegalStateException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String toString() {
        return this.number + " " + this.name + " " + this.type + " " + this.hp + " " + this.attack + " "
                + this.defense + " " + this.specialAttack + " " + this.specialDeffense + " " + this.speed + "\n";
    }

    public void write(DataOutput dataOutput) throws IOException {
        this.number.write(dataOutput);
        this.name.write(dataOutput);
        this.type.write(dataOutput);
        this.hp.write(dataOutput);
        this.attack.write(dataOutput);
        this.defense.write(dataOutput);
        this.specialAttack.write(dataOutput);
        this.specialDeffense.write(dataOutput);
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
        this.specialDeffense.readFields(dataInput);
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
