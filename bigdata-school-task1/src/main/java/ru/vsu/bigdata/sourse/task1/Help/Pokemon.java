package ru.vsu.bigdata.sourse.task1.Help;
<<<<<<< HEAD

import org.apache.hadoop.io.DoubleWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.io.Writable;
import org.apache.hadoop.io.WritableComparable;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

public class Pokemon implements Writable{

    public DoubleWritable getHP() {
        return HP;
    }

    public void setHP(DoubleWritable HP) {
=======
//Todo: почитай про инкапсуляцию, геттеры и сеттеры в джаве и перепиши этот класс
public class Pokemon {

    private double HP;
    private double attack;
    private double def;
    private double speed;
    private String name;

    /**
     * Получение характеристик покемона
     * @param i
     * @return Hp если i = 0; attak если i = 1; defend если i = 2; speed если i = 3;
     */
    public double getCharactiristics(int i){
        switch (i){
            case 0: return HP;
            case 1: return attack;
            case 2: return def;
            case 3: return speed;
        }
        return -1;
    }

    /**
     * Получение имени покемона
     * @return
     */
    public String getName(){
        return name;
    }

    /**
     * Задание статистик
     * @param HP health
     * @param attack attack
     * @param def defence
     * @param speed speed
     * @param name name
     */
    //Todo: похоже на нормальный конструктор, почему метод?
    public void setCharactiristic(double HP,double attack,double def,double speed,String name){
>>>>>>> 6d1776e5337c338ee0c28946a37f43678994a098
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

    private DoubleWritable HP;
    private DoubleWritable attack;
    private DoubleWritable def;
    private DoubleWritable speed;
    private Text name;
    private Text type;

    public Text getType() {
        return type;
    }

    public void setType(Text type) {
        this.type = type;
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

    public Pokemon(){
        this.attack = new DoubleWritable(100);//нам надо найти минимум, поэтому выставим большое число
        this.def = new DoubleWritable(0);
        this.HP = new DoubleWritable(0);
        this.speed = new DoubleWritable(100);//нам надо найти минимум, поэтому выставим большое число
        this.name = new Text("Example name");
        this.type = new Text("Example type");
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
}
