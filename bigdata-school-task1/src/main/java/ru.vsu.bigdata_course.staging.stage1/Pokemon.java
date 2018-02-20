package ru.vsu.bigdata_course.staging.stage1;

import java.util.Objects;

public class Pokemon   {

    private String number;
    private String name;
    private double hp;
    private String type;
    private double attack;
    private double defence;
    private double specialAttack;
    private double specialDefense;
    private double speed;

    private final int POSITION_FOR_NUMBER = 0;
    private final int POSITION_FOR_NAME = 1;
    private final int POSITION_FOR_TYPE = 2;
    private final int POSITION_FOR_HP = 3;
    private final int POSITION_FOR_ATTACK = 4;
    private final int POSITION_FOR_DEFENSE = 5;
    private final int POSITION_FOR_SPECIAL_ATTACK = 6;
    private final int POSITION_FOR_SPECIAL_DEFENSE = 7;
    private final int POSITION_FOR_SPEED = 8;


    Pokemon(String[] row) {
        this.setNumber(row[POSITION_FOR_NUMBER]);
        this.setName(row[POSITION_FOR_NAME]);
        this.setType(row[POSITION_FOR_TYPE]);
        this.setHp(Integer.parseInt(row[POSITION_FOR_HP]));
        this.setAttack(Integer.parseInt(row[POSITION_FOR_ATTACK]));
        this.setDefence(Integer.parseInt(row[POSITION_FOR_DEFENSE]));
        this.setSpecialAttack(Integer.parseInt(row[POSITION_FOR_SPECIAL_ATTACK]));
        this.setSpecialDefense(Integer.parseInt(row[POSITION_FOR_SPECIAL_DEFENSE]));
        this.setSpeed(Integer.parseInt(row[POSITION_FOR_SPEED]));
    }

    String getNumber() { return number;}

    private void setNumber(String number) {
        this.number = number;
    }

    public String getName() { return name;}

    private void setName(String name) {
        this.name = name;
    }


    double getHp() {
        return hp;
    }

    private void setHp(double hp) {
        this.hp = hp;
    }

    double getAttack() {
        return attack;
    }

    String getType() {
        return type;
    }

    private void setType(String type) {
        this.type = type;
    }

    private void setAttack(double attack) {
        this.attack = attack;
    }

    double getDefence() {
        return defence;
    }

    private void setDefence(double defence) {
        this.defence = defence;
    }

    public double getSpecialAttack() {
        return specialAttack;
    }

    private void setSpecialAttack(double specialAttack) {
        this.specialAttack = specialAttack;
    }

    public double getSpecialDefense() {
        return specialDefense;
    }

    private void setSpecialDefense(double specialDefense) {
        this.specialDefense = specialDefense;
    }

    double getSpeed() {
        return speed;
    }

    private void setSpeed(double speed) {
        this.speed = speed;
    }

    @Override
    public String toString() {
        return "Pokemon: " + name + " number: " + number;

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Pokemon pokemon = (Pokemon) o;
        return Double.compare(pokemon.hp, hp) == 0 &&
                Double.compare(pokemon.attack, attack) == 0 &&
                Double.compare(pokemon.defence, defence) == 0 &&
                Double.compare(pokemon.specialAttack, specialAttack) == 0 &&
                Double.compare(pokemon.specialDefense, specialDefense) == 0 &&
                Double.compare(pokemon.speed, speed) == 0 &&
                Objects.equals(number, pokemon.number) &&
                Objects.equals(name, pokemon.name) &&
                Objects.equals(type, pokemon.type);
    }

    @Override
    public int hashCode() {
        return Objects.hash(number, name, hp, type, attack, defence, specialAttack, specialDefense, speed);
    }


}
