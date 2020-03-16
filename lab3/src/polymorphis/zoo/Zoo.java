package polymorphis.zoo;

public class Zoo {

    private final int nrMaxAnimale;
    animal[] animaleZoo;
    private int indexCurent;
    public Zoo(int nrMaxAnimale)
    {
        if(nrMaxAnimale > 0) {
            this.nrMaxAnimale = nrMaxAnimale;
            this.animaleZoo = new animal[nrMaxAnimale];
        }
        else
        {
            throw new RuntimeException("Nu ati introdus un numar intreg pozitiv");
        }
    }

    public void adaugaAnimal(animal Animal)
    {
        if(indexCurent < animaleZoo.length)
        {
            animaleZoo[indexCurent] = Animal;
            System.out.println("Adaugat animal " + Animal.getClass().getSimpleName() + " la pozitia " + indexCurent++);
        }
    }
}
