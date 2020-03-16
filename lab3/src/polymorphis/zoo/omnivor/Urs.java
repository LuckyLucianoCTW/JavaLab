package polymorphis.zoo.omnivor;

public class Urs extends omnivor {
    public Urs(String nume,int varsta)
    {
        super(nume,varsta);
        this.sunetSpecific = "moramaie";
    }
    @Override
    public void scoateSunet()
    {
        System.out.println("ursul " + this.sunetSpecific);
    }
}
