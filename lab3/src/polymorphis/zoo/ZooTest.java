package polymorphis.zoo;
import polymorphis.zoo.carnivor.Leu;
import polymorphis.zoo.carnivor.Pisica;
import polymorphis.zoo.ierbivor.Cal;
import polymorphis.zoo.ierbivor.elefant;
import polymorphis.zoo.omnivor.Caine;
import polymorphis.zoo.omnivor.Urs;

import java.util.Scanner;

public class ZooTest {
    public static void main(String[] args)
    {
        Integer nrAnimaleZoo = Integer.valueOf(args[0]);
        System.out.println(nrAnimaleZoo);

        /*Scanner scanner = new Scanner(System.in);
        System.out.println("Precizati nr max de animale ce pot fi gazduite la zoo : ");
        int nrAnimaleZoo = scanner.nextInt();
        scanner.close();*/
        Zoo zooBucuresti = new Zoo(nrAnimaleZoo);
        adaugaAnimalLaZoo(zooBucuresti);
        for(int i = 0; (i < zooBucuresti.animaleZoo.length) && (zooBucuresti.animaleZoo[i] != null); i++) { {
            animal Animal = zooBucuresti.animaleZoo[i];
            Animal.afiseazaDetalii();
            Animal.seHraneste();
            Animal.scoateSunet();
        }
        }

    }

    public static void adaugaAnimalLaZoo(Zoo zoo)
    {

        Leu leu = new Leu("Simba",7);
        zoo.adaugaAnimal(leu);
        elefant Elefant = new elefant("eli",10);
        zoo.adaugaAnimal(Elefant);
        Urs urs = new Urs("Fram",4);
        zoo.adaugaAnimal(urs);
        Pisica pisica = new Pisica("Tom",2);
        zoo.adaugaAnimal(pisica);
        Caine caine = new Caine("Toto",3);
        zoo.adaugaAnimal(caine);
        Cal cal = new Cal("Thunder",3);
        zoo.adaugaAnimal(cal);
    }
}
