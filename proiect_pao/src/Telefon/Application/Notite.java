package Telefon.Application;

import Telefon.Meniu;

import java.util.Scanner;

public class Notite extends Meniu
{

    String notite[];
    public Notite()
    {
        System.out.print("Numarul maxim de notite : ");
        Scanner x = new Scanner(System.in);
        int maxNotite = x.nextInt();
        notite = new String[maxNotite];
        this.SetMaxIndex(maxNotite);
    }

    public Notite(int x)
    {
        super(x);
        notite = new String[x];
        this.SetMaxIndex(x);
    }

    public void AddNotite(String Text)
    {
        if(iSelected_index < iMax_Index)
        {

            System.out.println("Notita adaugata cu succes!");
            notite[iSelected_index] = Text;
            iSelected_index++;
        }
        else
            System.out.println("Ai atins numarul maxim de notite");
    }

    public boolean RemoveNotite(int x)
    {
        x -= 1;
        if(x >= 0 && x < iSelected_index) {
            for (int i = x; i < iSelected_index - 1; i++) {
                notite[i] = notite[i + 1];
            }
            iSelected_index--;
            return true;
        }
        return false;
    }


    @Override
    public String toString() {
        String my_notes = "";
        my_notes += "Ai " + iSelected_index + " de notite\n";
        for(int i = 0 ; i < iSelected_index; i++)
        {
            my_notes += (1 + i) + ") " +  notite[i] + "\n";
        }
        return my_notes;
    }

    public boolean ControlPanel()
    {
        System.out.println("-Notite-");
        System.out.println("1) Afiseaza Notite");
        System.out.println("2) Adauga o notita");
        System.out.println("3) Sterge o notita");
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
            System.out.println(toString());
        else if(iState == 2)
        {
            String text;
            System.out.print("Text : ");
            text = x.nextLine();
            AddNotite(text);
        }
        else if(iState == 3)
        {
            int ID;
            System.out.print("Introduceti un numar[inclusiv] intre 1 si " + iSelected_index + " : ");
            ID = x.nextInt();
            if(RemoveNotite(ID))
                System.out.println("Notita a fost stearsa cu succes!");
            else
                System.out.println("Numarul introdus a fost invalid!");
        }
        else if(iState == 4)
            return true;
        return false;
    }
}
