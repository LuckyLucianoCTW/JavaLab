package candy;


import candy.CandyBox;

import java.util.Scanner;

public class Heidi extends CandyBox {

    private float latura;

    public Heidi() {
        super();
        Scanner x = new Scanner(System.in);
        System.out.print("Latura  : ");
        this.latura = x.nextFloat();
        System.out.print("\n");


    }

    public Heidi(String flavor, String origin, float latura) {
        super(flavor, origin);
        this.latura = latura;

    }

    @Override
    public float getVolume()
    {
        return (latura * latura * latura);
    }

    @Override
    public String toString()
    {
        return super.toString() + " has volume " + getVolume();
    }

    public void printHeidiDim()
    {
        System.out.println("Latura cutiei : " + this.latura);
    }

//cub
}
