package Telefon.Application;

import Telefon.Meniu;
import Telefon.Files.CSV;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.Vector;

public class Agenda extends Meniu
{
    String nume[];
    String number[];
    CSV<Agenda>ReadFromFile;
    public Agenda()
    {
        System.out.print("Nr max de persoane : ");
        Scanner x = new Scanner(System.in);
        int iMax = x.nextInt();
        this.SetMaxIndex(iMax);
        nume = new String[iMax];
        number = new String[iMax];
        ReadFromFile = new CSV<Agenda>(this,"src/Telefon/Application/Agenda.csv");
    }
    public Agenda(int x)
    {

        super(x);
        nume = new String[x];
        number = new String[x];
        ReadFromFile = new CSV<Agenda>(this,"src/Telefon/Application/Agenda.csv");
    }
    public List GetList()
    {
     List<String> a = new Vector();
     for(int i = 0 ; i < iSelected_index; i++)
     {
         a.add(nume[i]);
         a.add(number[i]);
     }
     return a;
    }
    public void AddToMyAgenda(String name, String number, boolean IsFromFile)
    {
        if(iSelected_index == this.iMax_Index) {
            if (!IsFromFile)
                System.out.println("Nu mai puteti adauga in agenda!");
        }
        else
            {
                for(int i = 0 ; i < iSelected_index; i++) {
                    if (this.number[i].equals(number) && this.nume[i].equals(name)) {
                        if(!IsFromFile)
                         System.out.println("Acest numar exista deja in agenda dumneavoastra!");
                        return;
                    }
                }
            this.nume[iSelected_index] = name;
            this.number[iSelected_index] = number;
            iSelected_index++;
            if(!IsFromFile)
            {
                ReadFromFile.WriteFile(this,2);
                System.out.print("Numarul a fost adaugat cu succes!\n");
            }
        }
    }
    protected boolean RemoveFromMyAgenda(String name)
    {
        for(int i = 0; i < iSelected_index; i++)
        {
            if (nume[i].equals(name))
            {
                for (int j = i; j < iSelected_index - 1; j++) {
                    nume[j] = nume[j + 1];
                    number[j] = number[j + 1];
                }
                iSelected_index--;
                return true;
            }
        }
        return false;
    }
    @Override
    public String toString() {
        /*return "Agenda are" +
                index + " Contacte"  + "\n" +
                " nume = " + Arrays.toString(nume) + "\n" +
                " numar = " + Arrays.toString(number);*/
        String my_Agenda = "-------Agenda are " + iSelected_index +  " contacte -------\n";
        for(int i = 0 ; i < iSelected_index; i++)
        {
            my_Agenda += "Nume : " + nume[i] + "\n" + "Numar : " + number[i] + " \n\n";
        }
        return my_Agenda;
    }

    public boolean ControlPanel()
    {
        System.out.println("-Agenda-");
        System.out.println("1) Vizualizati Agenda");
        System.out.println("2) Adaugati o persoana in Agenda");
        System.out.println("3) Stergeti o persoana din Agenda");
        System.out.println("4) Back to Menu");
        Scanner x = new Scanner(System.in);
        System.out.print("Alegeti o functie din cele prezentate mai sus : ");
        this.iState = x.nextInt();
        System.out.print("\n");
        while(iState < 1 || iState > 4)
        {
            System.out.print("Numarul introdus este unul invalid, va rugam sa introduceti un numar intre 1 si 4 : ");
            this.iState = x.nextInt();
        }
        x.nextLine();
        if(iState == 1)
        {
            this.ReadFromFile.AuditSystem("Vizualizare Agenda");
            System.out.println(toString());
        }
        else if(iState == 2)
        {
            System.out.print("Nume : ");
            String numele = x.nextLine();
            System.out.print("Numarul de telefon : ");
            String numar = x.nextLine();
            this.ReadFromFile.AuditSystem("Adaugare Agenda");
            AddToMyAgenda(numele,numar,false);
        }
        else if(iState == 3)
        {

            if(iSelected_index == 0)
            {
                System.out.println("Nu aveti nicio persoana in agenda!");
                return false;
            }
            System.out.print("Nume : ");
            String numele = x.nextLine();
            this.ReadFromFile.AuditSystem("Stergere din Agenda");
            if(RemoveFromMyAgenda(numele))
                System.out.println("Persoana a fost stearsa din Agenda!");
            else
                System.out.println("Persoana introdusa nu exista");
        }
        else if(iState == 4)
        {
            this.ReadFromFile.AuditSystem("Inapoi in Meniu");
            return true;
        }
        return false;
    }
}
