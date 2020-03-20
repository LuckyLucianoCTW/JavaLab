package candy;

import java.util.Scanner;

public abstract class CandyBox
{
    private String flavor;
    private String origin;

    public CandyBox()
    {
        Scanner x = new Scanner(System.in);
        System.out.print("Flavor : ");

        flavor = x.nextLine();
        System.out.print("\n");

        System.out.print("Origin : ");
        origin = x.nextLine();

        System.out.print("\n");
    }
    public CandyBox(String flavor, String origin)
    {
        this.flavor = flavor;
        this.origin = origin;
    }

    public float getVolume()
    {
        return 0;
    }

    @Override
    public String toString()
    {
        return "The " + origin + " " + flavor;
    }

    @Override
    public boolean equals(Object obj)
    {
        if(obj instanceof  CandyBox)
        {
            //Daca au aceiasi origine + flavor + volum si sunt aceiasi clasa => sunt la fel
            CandyBox box = (CandyBox)obj;
            String a = box.toString();
            String b = this.toString();
            if(box.getClass() == this.getClass() && a.compareTo(b) == 0) {
                return true;
            }
            else {
                return false;
            }

        }
        else {
            return false;
        }
    }
}
