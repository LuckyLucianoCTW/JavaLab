package candy;

import java.util.Arrays;
import java.util.Scanner;

public class CandyBag {

    private int nrMaxCandy;
    private CandyBox [] box;
    private int index_CandyBox;
    public CandyBag()
    {
    Scanner x = new Scanner(System.in);
    System.out.print("Numaru de cutii : ");
    nrMaxCandy =  x.nextInt();
    System.out.print("\n");
    index_CandyBox = 0;
    if(nrMaxCandy > 0) {
        box = new CandyBox[nrMaxCandy];
    }
    }

    public CandyBag(int maxCandy, CandyBox[] box)
    {
        this.box = Arrays.copyOf(box,box.length);
        index_CandyBox = box.length;
        nrMaxCandy = maxCandy;
    }

    public boolean AdaugaBomboana(CandyBox box)
    {
        if(index_CandyBox < nrMaxCandy)
        {
            this.box[index_CandyBox] = box;
            index_CandyBox++;
            return true;
        }
        else
        {
            return false;
        }
    }

    public void TestLindt()
    {
        for(int i = 0 ; i < index_CandyBox - 1; i++)
        {
            for(int j = i + 1 ; j < index_CandyBox; j++) {
                if(box[i] instanceof  Lindt && box[j] instanceof  Lindt)
                {
                    System.out.println("Cutia Lindt cu index : " + i + " si cutia cu index : " + j + " sunt " + box[i].equals(box[j]));
                }

            }
        }
    }
}
