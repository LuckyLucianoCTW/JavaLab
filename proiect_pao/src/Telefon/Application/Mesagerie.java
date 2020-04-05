package Telefon.Application;

import Telefon.Meniu;

import java.util.Arrays;
import java.util.Scanner;

public class Mesagerie extends Meniu
{
    String Messages[][];
    int lenght[];
    public Mesagerie()
    {
        System.out.print("Numarul maxim de persoane/mesaje : ");
        Scanner x = new Scanner(System.in);
        int maxMessages = x.nextInt();
        this.SetMaxIndex(maxMessages);
        lenght = new int[maxMessages];
        Messages = new String[maxMessages][maxMessages + 1];
    }

    public Mesagerie(int iMax)
    {
        super(iMax);
        this.SetMaxIndex(iMax);
        lenght = new int[iMax];
        Messages = new String[iMax][iMax + 1];
    }

    public boolean SendMessage(String mesaj,String nume)
    {

     if(iSelected_index == this.iMax_Index)
     {
         System.out.println("Numarul maxim de persoane a fost atins!");
         return false;
     }

     for(int i = 0 ; i < iSelected_index; i++) {

        if (Messages[i][0].equals(nume))
        {
            if(lenght[i] == this.iMax_Index) {
                System.out.println("Numarul maxim de mesaje a fost atins!");
                return false;
            }
            Messages[i][lenght[i]] = mesaj;
            lenght[i]++;
            return true;
        }
    }
        Messages[iSelected_index][0] = nume;
        Messages[iSelected_index][1] = mesaj;
        lenght[iSelected_index]+= 2;
        iSelected_index++;
        return true;
    }

    public boolean DeleteMessagesWithSomeone(String nume)
    {
        for(int i = 0 ; i < iSelected_index; i++)
        {
            if(Messages[i][0].equals(nume))
            {
                if(i != iSelected_index - 1)
                {
                    int exI = lenght[i];
                    for (int j = 0; j < lenght[iSelected_index - 1]; j++) {
                        Messages[i][j] = Messages[iSelected_index - 1][j];
                    }
                    if(exI > lenght[iSelected_index - 1])
                    {
                        for(int j = lenght[iSelected_index - 1]; j < exI; j++)
                            Messages[i][j] = null;
                    }
                    lenght[i] = lenght[iSelected_index - 1];
                }
                iSelected_index--;
                return true;
            }
        }
        return false;
    }

    @Override
    public String toString()
    {
        String returningPoint = "";
        if(iSelected_index == 0)
         returningPoint = "Nu aveti niciun Mesaj";
        else
        {
            for(int i = 0 ; i < iSelected_index; i++)
            {
                returningPoint += "Nume : " + Messages[i][0] + "\n";
                for(int j = 1 ; j < lenght[i]; j++)
                {
                    returningPoint += j + ") : " + Messages[i][j] + "\n";
                }
            }
        }
        return returningPoint;
    }

    public boolean ControlPanel()
    {
        System.out.println("-Notite-");
        System.out.println("1) Vizualizeaza Mesajele");
        System.out.println("2) Trimiteti un Mesaj");
        System.out.println("3) Sterge mesajele cu o persoana");
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
         System.out.println(toString());
        }
        else if(iState == 2)
        {
            String my_Name;
            String my_Message;
            System.out.print("Numele persoanei : ");
            my_Name = x.nextLine();
            System.out.print("Mesajul : ");
            my_Message = x.nextLine();

            if(SendMessage(my_Message,my_Name))
                System.out.println("Mesajul a fost trimis cu succes!");
            else
                System.out.println("Mesajul nu a fost trimis!");
        }
        else if(iState == 3)
        {

            if(iSelected_index == 0)
            {
                System.out.println("Nu aveti niciun mesaj.");
                return false;
            }
            String my_Name;
            System.out.print("Numele persoanei : ");
            my_Name = x.nextLine();

            if(DeleteMessagesWithSomeone(my_Name))
                System.out.println("Mesajele au fost sterse cu succes!");
            else
                System.out.println("Mesajele nu au putut fi sterse!");
        }
        else if(iState == 4)
        {
            return true;
        }
        return false;
    }

}
