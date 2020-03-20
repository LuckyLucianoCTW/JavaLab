package candy;

import candy.CandyBox;

import java.util.Scanner;


public class Milka extends  CandyBox{
    private float inaltime;
    private float raza;

    public Milka() {
        super();
        Scanner x = new Scanner(System.in);
        System.out.print("Inaltimea : ");
        this.inaltime = x.nextFloat();

        System.out.print("\n");
        System.out.print("Raza : ");
        this.raza = x.nextFloat();
        System.out.print("\n");

    }

    public Milka(String flavor, String origin, float inaltime, float raza) {
        super(flavor, origin);
        this.inaltime = inaltime;
        this.raza = raza;
    }

    @Override
    public float getVolume()
    {
        return (3.1415926f * inaltime * raza);
    }

    @Override
    public String toString()
    {
        return super.toString() + " has volume " + getVolume();
    }

    public void printMilkaDim()
    {
        System.out.println("Inaltimea cutiei : " + this.inaltime + " iar raza : " + this.raza);
    }
    //cilindru
}
