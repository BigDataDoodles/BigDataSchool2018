package pokemonstats;

import org.apache.hadoop.io.DoubleWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.io.Writable;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

public class Pokemon implements Writable, Comparable<Pokemon> {
    private Text name;
    private Text type;
    private DoubleWritable power;
    private DoubleWritable hp;
    private DoubleWritable attack;
    private DoubleWritable defence;
    private DoubleWritable specialAttack;
    private DoubleWritable specialDefence;
    private DoubleWritable speed;

    public Pokemon(){
        name = new Text();
        type = new Text();
        power = new DoubleWritable();
        hp = new DoubleWritable();
        attack = new DoubleWritable();
        defence = new DoubleWritable();
        specialAttack = new DoubleWritable();
        specialDefence = new DoubleWritable();
        speed = new DoubleWritable();
    }
    public Pokemon(String csvRow) {
        String[] row = csvRow.split(",");
        type = new Text(row[2]);
        name = new Text(row[1]);
        power = new DoubleWritable(Double.parseDouble(row[3]));
        hp = new DoubleWritable(Double.parseDouble(row[4]));
        attack = new DoubleWritable(Double.parseDouble(row[5]));
        specialAttack = new DoubleWritable(Double.parseDouble(row[7]));
        defence = new DoubleWritable(Double.parseDouble(row[6]));
        specialDefence = new DoubleWritable(Double.parseDouble(row[8]));
        speed = new DoubleWritable(Double.parseDouble(row[9]));
    }
    @Override
    public String toString(){
        return "0"
                +","+name
                +","+type
                +","+power.get()
                +","+hp.get()
                +","+attack.get()
                +","+defence.get()
                +","+specialAttack.get()
                +","+specialDefence.get()
                +","+speed.get();
    }
    public void write(DataOutput dataOutput) throws IOException {
       name.write(dataOutput);
       type.write(dataOutput);
       hp.write(dataOutput);
       attack.write(dataOutput);
       specialAttack.write(dataOutput);
       defence.write(dataOutput);
       specialDefence.write(dataOutput);
       speed.write(dataOutput);
    }
    public void readFields(DataInput dataInput) throws IOException {
        name.readFields(dataInput);
        type.readFields(dataInput);
        hp.readFields(dataInput);
        attack.readFields(dataInput);
        specialAttack.readFields(dataInput);
        defence.readFields(dataInput);
        specialDefence.readFields(dataInput);
        speed.readFields(dataInput);
    }

    public String getName() {
        return String.valueOf(name);
    }

    public Text getType() {
        return type;
    }

    public DoubleWritable getPower() {
        return power;
    }

    public DoubleWritable getHp() {
        return hp;
    }

    public DoubleWritable getAttack() {
        return attack;
    }

    public DoubleWritable getDefence() {
        return defence;
    }

    public DoubleWritable getSpecialAttack() {
        return specialAttack;
    }

    public DoubleWritable getSpecialDefence() {
        return specialDefence;
    }

    public DoubleWritable getSpeed() {
        return speed;
    }
    public  DoubleWritable getAllDefence(){
        double tmp = getDefence().get() + getSpecialDefence().get();
        DoubleWritable returnValue = new DoubleWritable();
        returnValue.set(tmp);
        return returnValue;
    }
    public DoubleWritable getAllAttack(){
        double tmp = getAttack().get() + getSpecialAttack().get();
        DoubleWritable returnValue = new DoubleWritable();
        returnValue.set(tmp);
        return returnValue;
    }
    public int compareTo(Pokemon o) {//in case of equal quantities of e.g speed choose the pokemon with the name that
        // comes first alphabetically
        return this.name.compareTo(o.name);
    }
}
