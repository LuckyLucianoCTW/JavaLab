package Telefon.Application;

import Telefon.Files.CSV;
import Telefon.Meniu;

import java.awt.desktop.ScreenSleepEvent;
import java.util.List;
import java.util.Scanner;
import java.util.Vector;


public class Settings extends Meniu
{

    String Denumire;
    String Versiune;
    String ReleaseDate;
    String OS;
    String Procesor;
    float GHZ;
    float min_GHZ;
    String Memorie_Interna;
    String Memorie_RAM;
    String Updates[];
    CSV<Settings>ReadFromFile;

    public Settings(String Denumire, String Versiune, String ReleaseDate, String OS, String Procesor, float GHZ, String Memorie_Interna,String Memorie_RAM, int Max_Updates)
    {
        super(Max_Updates);
        this.Denumire = Denumire;
        this.Versiune = Versiune;
        this.ReleaseDate = ReleaseDate;
        this.OS = OS;
        this.Procesor = Procesor;
        this.GHZ = GHZ;
        min_GHZ = GHZ;
        this.Memorie_Interna = Memorie_Interna;
        this.Memorie_RAM = Memorie_RAM;
        Updates = new String[Max_Updates];
        ReadFromFile = new CSV<Settings>(this,"src/Telefon/Application/Settings.csv");
    }

    public void OverClockOurCPU()
    {
        Scanner x = new Scanner(System.in);
        System.out.print("Introduceti noua Frecventa : ");
        float new_GHZ = x.nextFloat();
        System.out.print("\n");
        while(new_GHZ < min_GHZ || new_GHZ > min_GHZ + 2.5f)
        {
            System.out.print("Aceasta valoare este una prea mica sau prea mare, va rugam sa introduceti o valoare peste minimul de : " + min_GHZ + " si maximul de " + min_GHZ + 2.5f + "\n");
            System.out.print("Introduceti noua Frecventa : ");
            new_GHZ = x.nextFloat();
        }
        if(new_GHZ > GHZ)
            System.out.println("Frecventa procesorului a fost crescuta de la " + GHZ + " la " + new_GHZ+ "\n");
        else
            System.out.println("Frecventa procesorului a fost scazuta de la " + GHZ + " la " + new_GHZ + "\n");

        GHZ = new_GHZ;
    }

    public List GetList()
    {
        List<String> a = new Vector();
        for(int i = 0 ; i < iSelected_index; i++)
        {
            a.add(Updates[i]);
        }
        return a;
    }
    public void AddUpdate(String UpdateName, boolean fromFile)
    {
        boolean exists = false;
        for(int i = 0 ; i < iSelected_index ; i++) {
            if(Updates[i].equals(UpdateName))
                exists = true;
        }
        if(!exists)
        {
        Updates[iSelected_index] = UpdateName;
        iSelected_index++;
        if(!fromFile)
            ReadFromFile.WriteFile(this,1);
        }
    }
    public void UpdateMyPhone()
    {

        if(iSelected_index == 0) {
        System.out.print("Telefonul nu are niciun update disponibil \n");
        return;
        }
        OS = Updates[0];
        for(int i = 0 ; i < iSelected_index - 1; i++)
            Updates[i] = Updates[i + 1];
        iSelected_index--;
            System.out.print("Telefonul se updateaza... \n");
            System.out.print("|");
            int x = 0;

            while (x != 100)
            {
                x++;

                try{Thread.sleep(50);}
                catch(InterruptedException e)
                {
                    System.out.println(e);
                }
                if (x % 10 == 0) {
                    System.out.print("=");
                }
                if(x == 100) {
                    System.out.print("|");
                    System.out.print("\n");
                    System.out.print("Telefonul a fost updatat cu succes \n\n");
                }

            }

    }


    @Override
    public String toString() {

       return "Telefon : " + Denumire + "\n" +
               "Versiunea : " + Versiune + "\n" +
               "Lansat in data de : " + ReleaseDate + "\n" +
               "Sistemul de Operare : " + OS + "\n" +
               "Procesor : " + Procesor + "  avand o frecventa de : " + GHZ + "GHZ" +"\n" +
               "Memorie Interna : " + Memorie_Interna + "\n" +
               "Memorie Ram : " + Memorie_RAM + "\n";
    }

    public boolean ControlPanel()
    {
        /*
        TODO : Reset To Factory Settings
         */
        System.out.println("-Settings-");
        System.out.println("1) Despre Telefon");
        System.out.println("2) OverClock CPU");
        System.out.println("3) Updateaza-ti Telefonul");
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
            System.out.println(this.toString());
        }
        else if(iState == 2)
        {
            OverClockOurCPU();
        }
        else if(iState == 3)
        {
            UpdateMyPhone();
        }
        else if(iState == 4)
        {
            return true;
        }

        return false;
    }

}
