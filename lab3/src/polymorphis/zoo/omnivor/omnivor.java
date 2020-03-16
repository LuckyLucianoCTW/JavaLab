package polymorphis.zoo.omnivor;
import polymorphis.zoo.animal;

public abstract class omnivor extends animal {

    public omnivor(String nume,int varsta)
    {
        super(nume,varsta);
        this.tipHrana = "carne si vegetatie";
    }
    @Override
    public void seHraneste()
    {
        System.out.println(this + " se hraneste cu " + this.tipHrana);
    }
}
