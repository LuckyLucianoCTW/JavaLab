package polymorphis.zoo.ierbivor;

import polymorphis.zoo.animal;

public abstract class ierbivor extends animal {
    public ierbivor(String nume,int varsta)
    {
        super(nume,varsta);
        this.tipHrana = "vegetatie";
    }
    @Override
    public void seHraneste()
    {
        System.out.println(this + " se hraneste cu " + this.tipHrana);
    }
}
