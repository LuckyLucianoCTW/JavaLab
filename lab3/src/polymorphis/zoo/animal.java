package polymorphis.zoo;

public abstract class animal
{
    private int nrIdentificare;
    private String nume;
    private int varsta;
    protected String tipHrana;
    protected String sunetSpecific;

    public animal(String nume, int varsta)
    {
        nrIdentificare = hashCode();
        this.varsta = varsta;
        this.nume = nume;

    }

    public abstract void seHraneste();

    public abstract void scoateSunet();


    public void afiseazaDetalii(){
        System.out.println("Acesta este " + this.toString());
    }
    public String getNume()
    {
        return this.nume;
    }
    @Override
    public String toString()
    {
        return "Animal din categoria " + this.getClass().getSuperclass().getSimpleName() +
                ", si este un/o  " +
                this.getClass().getSimpleName() +
                 " numele ei/ele este  " + nume +
                " are o varsta de   " + varsta +
                " iar el/ea un/o    " + tipHrana;
    }

}
