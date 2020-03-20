package candy;

import candy.Heidi;

public class Main {
    static public void main(String [] args)
    {
       // Lindt a = new Lindt();
       // Lindt b = new Lindt();
       // Lindt c = new Lindt();
       // System.out.println(a);
       // System.out.println(b);
       // System.out.println(a.equals(b));
       // System.out.println(a.equals(c));
        CandyBag a = new CandyBag();
        Lindt a1 = new Lindt("Acru","Germania",10,10,10);
        Heidi a2 = new Heidi("Acru","Germania",10);
        Milka a3 = new Milka("Acru","Germania",20,10);
        Lindt a4 = new Lindt("Acru","Germania",10,10,10);
        Lindt a5 = new Lindt("Sarat","Germania",10,10,10);
        Lindt a6 = new Lindt("Acru","Germania",10,10,10);
        a1.printLindtDim();
        a2.printHeidiDim();
        a3.printMilkaDim();
        a.AdaugaBomboana(a1);
        a.AdaugaBomboana(a2);
        a.AdaugaBomboana(a3);
        a.AdaugaBomboana(a4);
        a.AdaugaBomboana(a5);
        a.AdaugaBomboana(a6);
        a.TestLindt();
    }
}
