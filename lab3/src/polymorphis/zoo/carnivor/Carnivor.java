package polymorphis.zoo.carnivor;
import polymorphis.zoo.animal;

public abstract class Carnivor extends animal {

    public Carnivor(String nume,int varsta)
    {
        super(nume,varsta);
        this.tipHrana = "carne";
    }
   @Override
   public void seHraneste()
   {
       System.out.println(this + " se hraneste cu " + this.tipHrana);
   }
}
