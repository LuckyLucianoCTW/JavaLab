package polymorphis.zoo.omnivor;

public class Caine extends omnivor{

    public Caine(String nume,int varsta)
    {
        super(nume,varsta);
        this.sunetSpecific = "latra";
    }
    @Override
    public void scoateSunet()
    {
        System.out.println("cainele " + this.sunetSpecific);
    }
}
