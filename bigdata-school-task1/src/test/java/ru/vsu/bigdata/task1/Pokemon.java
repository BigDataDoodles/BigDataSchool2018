package ru.vsu.bigdata.task1;

import org.apache.hadoop.io.DoubleWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.io.Writable;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;
import java.util.Comparator;

public class Pokemon implements Writable {

    private Text number;
    private Text name;
    private Text type;

    private DoubleWritable hp;
    private DoubleWritable attack;
    private DoubleWritable defence;
    private DoubleWritable specialAttack;
    private DoubleWritable specialDefence;
    private DoubleWritable speed;


    /*Filler pokemon
      - max attack
      - max speed
      - min hp
      - min defence
    */
    public Pokemon() {
        this.number = new Text("Filler number");
        this.name = new Text("Filler name");
        this.type = new Text("Filler type");

        this.hp = new DoubleWritable(Double.MIN_VALUE);
        this.attack = new DoubleWritable(Double.MAX_VALUE);
        this.defence = new DoubleWritable(Double.MIN_VALUE);
        this.speed = new DoubleWritable(Double.MAX_VALUE);

        this.specialAttack = new DoubleWritable(Double.MAX_VALUE);
        this.specialDefence = new DoubleWritable(Double.MIN_VALUE);
    }

    public Pokemon(Text number, Text name, Text type, DoubleWritable hp, DoubleWritable attack, DoubleWritable defence, DoubleWritable specialAttack, DoubleWritable specialDefence, DoubleWritable speed) {
        this.number = number;
        this.name = name;
        this.type = type;
        this.hp = hp;
        this.attack = attack;
        this.defence = defence;
        this.specialAttack = specialAttack;
        this.specialDefence = specialDefence;
        this.speed = speed;
    }

    //copy constructor
    public Pokemon(Pokemon pokemon) {
        this.number = new Text(pokemon.getNumber());
        this.name = new Text(pokemon.getName());
        this.type = new Text(pokemon.getType());
        this.hp = new DoubleWritable(pokemon.getHp().get());
        this.attack = new DoubleWritable(pokemon.getAttack().get());
        this.defence = new DoubleWritable(pokemon.getDefence().get());
        this.specialAttack = new DoubleWritable(pokemon.getSpecialAttack().get());
        this.specialDefence = new DoubleWritable(pokemon.getSpecialDefence().get());
        this.speed = new DoubleWritable(pokemon.getSpeed().get());
    }

    public Text getNumber() {
        return number;
    }

    public void setNumber(Text number) {
        this.number = number;
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

    public DoubleWritable getHp() {
        return hp;
    }

    public void setHp(DoubleWritable hp) {
        this.hp = hp;
    }

    public DoubleWritable getAttack() {
        return attack;
    }

    public void setAttack(DoubleWritable attack) {
        this.attack = attack;
    }

    public DoubleWritable getDefence() {
        return defence;
    }

    public void setDefence(DoubleWritable defence) {
        this.defence = defence;
    }

    public DoubleWritable getSpecialAttack() {
        return specialAttack;
    }

    public void setSpecialAttack(DoubleWritable specialAttack) {
        this.specialAttack = specialAttack;
    }

    public DoubleWritable getSpecialDefence() {
        return specialDefence;
    }

    public void setSpecialDefence(DoubleWritable specialDefence) {
        this.specialDefence = specialDefence;
    }

    public DoubleWritable getSpeed() {
        return speed;
    }

    public void setSpeed(DoubleWritable speed) {
        this.speed = speed;
    }


    public void write(DataOutput dataOutput) throws IOException {
        number.write(dataOutput);
        name.write(dataOutput);
        type.write(dataOutput);
        hp.write(dataOutput);
        attack.write(dataOutput);
        defence.write(dataOutput);
        specialAttack.write(dataOutput);
        specialDefence.write(dataOutput);
        speed.write(dataOutput);
    }

    public void readFields(DataInput dataInput) throws IOException {
        number.readFields(dataInput);
        name.readFields(dataInput);
        type.readFields(dataInput);
        hp.readFields(dataInput);
        attack.readFields(dataInput);
        defence.readFields(dataInput);
        specialAttack.readFields(dataInput);
        specialDefence.readFields(dataInput);
        speed.readFields(dataInput);
    }

    public static Comparator<Pokemon> AttackComparator = new Comparator<Pokemon>() {
        @Override
        public int compare(Pokemon o1, Pokemon o2) {

            return (int) (o2.getAttack().get() - o1.getAttack().get());
        }
    };

    public static Comparator<Pokemon> DefenceComparator = new Comparator<Pokemon>() {
        @Override
        public int compare(Pokemon o1, Pokemon o2) {

            return (int) (o2.getDefence().get() - o1.getDefence().get());
        }
    };

    public static Comparator<Pokemon> HPComparator = new Comparator<Pokemon>() {
        @Override
        public int compare(Pokemon o1, Pokemon o2) {

            return (int) (o2.getHp().get() - o1.getHp().get());
        }
    };

    public static Comparator<Pokemon> SpeedComparator = new Comparator<Pokemon>() {
        @Override
        public int compare(Pokemon o1, Pokemon o2) {

            return (int) (o2.getSpeed().get() - o1.getSpeed().get());
        }
    };
}
