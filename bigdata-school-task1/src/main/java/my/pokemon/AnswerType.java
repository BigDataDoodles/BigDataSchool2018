package my.pokemon;

public class AnswerType {

    private String number;
    private String name;
    private String type;
    private int hp = 0;
    private int attack = 0;
    private int defense = 0;
    private int specialAttack = 0;
    private int specialDefense = 0;
    private int speed = 0;

    public String getNumber() { return this.number; }
    public String getName() { return this.name; }
    public String getType() { return this.type; }
    public int getHp() { return this.hp; }
    public int getAttack() { return this.attack; }
    public int getDefense() { return this.defense; }
    public int getSpecialAttack() { return this.specialAttack; }
    public int getSpecialDefense() { return this.specialDefense; }
    public int getSpeed() { return this.speed; }

    AnswerType ( String str) throws NumberFormatException
    {
        String[] mas = str.split(",");
        this.number = mas[1];
        this.name = mas[2];
        this.type = mas[3];
        try {
            this.hp = Integer.parseInt(mas[4]);
        } catch( NumberFormatException e )
        {
            hp = 0;
        }

        try {
            this.attack = Integer.parseInt(mas[5]);
        } catch( NumberFormatException e )
        {
            attack = 0;
        }

        try {
            this.defense = Integer.parseInt(mas[6]);
        } catch( NumberFormatException e )
        {
            defense = 0;
        }

        try {
            this.specialAttack = Integer.parseInt(mas[7]);
        } catch( NumberFormatException e )
        {
            specialAttack = 0;
        }

        try {
            this.specialDefense = Integer.parseInt(mas[8]);
        } catch( NumberFormatException e )
        {
            specialDefense = 0;
        }

        try {
            this.speed = Integer.parseInt(mas[9]);
        } catch( NumberFormatException e )
        {
            speed = 0;
        }
    }
}
