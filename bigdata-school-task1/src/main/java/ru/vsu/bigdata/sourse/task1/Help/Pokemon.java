package ru.vsu.bigdata.sourse.task1.Help;

public class Pokemon {

    private int HP;
    private int attack;
    private int def;
    private int speed;
    private String name;

    /**
     *
     * @param i
     * @return Hp если i = 0; attak если i = 1; defend если i = 2; speed если i = 3;
     */
    public int getCharactiristics(int i){
        switch (i){
            case 0: return HP;
            case 1: return attack;
            case 2: return def;
            case 3: return speed;
        }
        return -1;
    }

    public String getName(){
        return name;
    }

    public void setCharactiristic(int HP,int attack,int def,int speed,String name){
        this.HP = HP;
        this.attack = attack;
        this.def = def;
        this.speed = speed;
        this.name = name;
    }

    public void setCharactitistic(int key, int value){
        switch (key){
            case 0: {HP = value; break;}
            case 1: {attack = value; break;}
            case 2: {def = value; break;}
            case 3: {speed = value; break;}
        }
    }

    public void setCharactiristic(String name){
        this.name = name;
    }


}
