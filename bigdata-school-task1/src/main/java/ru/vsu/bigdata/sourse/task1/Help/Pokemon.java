package ru.vsu.bigdata.sourse.task1.Help;

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
    public void setCharactiristic(double HP,double attack,double def,double speed,String name){
        this.HP = HP;
        this.attack = attack;
        this.def = def;
        this.speed = speed;
        this.name = name;
    }

    /**
     * перегрузка функции задания характеристик
     * @param key ключ по которому мы выставляем характеристики
     * @param value значение
     */
    public void setCharactitistic(int key, double value){
        switch (key){
            case 0: {HP = value; break;}
            case 1: {attack = value; break;}
            case 2: {def = value; break;}
            case 3: {speed = value; break;}
        }
    }

    /**
     * Перегрузка функции задачи характиристик. Задание имени
     * @param name
     */
    public void setCharactiristic(String name){
        this.name = name;
    }


}
