package candy;

import candy.CandyBox;

import java.util.Scanner;

public class Lindt extends  CandyBox
{
    private float lungime;
    private float latime;
    private float inaltime;

    public Lindt()
    {
        super();
        Scanner x = new Scanner(System.in);
        System.out.print("Lungime : ");
        this.lungime = x.nextFloat();

        System.out.print("\n");
        System.out.print("Latime : ");
        this.latime = x.nextFloat();

        System.out.print("\n");

        System.out.print("Inaltime : ");
        this.inaltime = x.nextFloat();
        System.out.print("\n");

    }

    public Lindt(String flavor, String origin, float lungime, float latime, float inaltime)
    {
        super(flavor, origin);
        this.lungime = lungime;
        this.latime = latime;
        this.inaltime = inaltime;
    }

    @Override
    public float getVolume()
    {
        return (lungime * latime * inaltime);
    }


    @Override
    public String toString()
    {
        return super.toString() + " has volume " + getVolume();
    }

    public void printLindtDim()
    {
        System.out.println("Inaltimea cutiei : " + this.inaltime + " latimea : " + this.latime + " lungimea :  " + this.lungime);
    }
    //paralelipiped dreptunghic
}
