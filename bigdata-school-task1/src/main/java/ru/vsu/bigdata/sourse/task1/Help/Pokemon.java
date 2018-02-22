package ru.vsu.bigdata.sourse.task1.Help;
import org.apache.hadoop.io.DoubleWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.io.Writable;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

public class Pokemon implements Writable {

    private DoubleWritable HP;
    private DoubleWritable attack;
    private DoubleWritable def;
    private DoubleWritable speed;
    private Text name;
    private Text type;


    public DoubleWritable getHP() {
        return HP;
    }

    public void setHP(DoubleWritable HP) {
        this.HP = HP;
    }

    public DoubleWritable getAttack() {
        return attack;
    }

    public void setAttack(DoubleWritable attack) {
        this.attack = attack;
    }

    public DoubleWritable getDef() {
        return def;
    }

    public void setDef(DoubleWritable def) {
        this.def = def;
    }

    public DoubleWritable getSpeed() {
        return speed;
    }

    public void setSpeed(DoubleWritable speed) {
        this.speed = speed;
    }

    public Text getName() {
        return name;
    }

    public void setName(Text name) {
        this.name = name;
    }

    public Text getType() {
        return type;
    }

    public void setType(Text type) {
        this.type = type;
    }

    public void write(DataOutput out) throws IOException {
        this.name.write(out);
        this.speed.write(out);
        this.HP.write(out);
        this.def.write(out);
        this.attack.write(out);
        this.type.write(out);
    }

    public void readFields(DataInput in) throws IOException {
        this.name.readFields(in);
        this.attack.readFields(in);
        this.def.readFields(in);
        this.HP.readFields(in);
        this.speed.readFields(in);
        this.type.readFields(in);
    }

    public Pokemon(DoubleWritable attack, DoubleWritable def, DoubleWritable HP, DoubleWritable speed,
                   Text name, Text type){
        this.attack = attack;
        this.def = def;
        this.HP = HP;
        this.speed = speed;
        this.name = name;
        this.type = type;
    }

    @Override
    public String toString() {
        return "Pokemon{" +
                "HP=" + HP +
                ", attack=" + attack +
                ", def=" + def +
                ", speed=" + speed +
                ", name=" + name +
                ", type=" + type +
                '}';
    }

    public Pokemon(){
        //TODO: Double.MAX_VALUE вместо 100
        this.attack = new DoubleWritable(100);//нам надо найти минимум, поэтому выставим большое число
        this.def = new DoubleWritable(0);
        this.HP = new DoubleWritable(0);
        this.speed = new DoubleWritable(100);//нам надо найти минимум, поэтому выставим большое число
        this.name = new Text("Example name");
        this.type = new Text("Example type");
    }
}