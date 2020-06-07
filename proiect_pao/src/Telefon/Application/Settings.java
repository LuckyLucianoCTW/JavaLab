package Telefon.Application;

import Telefon.DataBase.mySQL;
import Telefon.Files.CSV;
import Telefon.Meniu;
import Telefon.Render.Render;

import java.awt.*;
import java.awt.desktop.ScreenSleepEvent;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;
import java.util.Set;
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
    String currentListBox[];
    boolean isSelected[];
    mySQL<Settings> ReadFromDB;

    public Settings(String Denumire, String Versiune, String ReleaseDate, String OS, String Procesor, float GHZ, String Memorie_Interna, String Memorie_RAM, int Max_Updates, Render rend) throws SQLException {
        super(Max_Updates,rend);
        isSelected = new boolean[100];
        currentListBox = new String[10 + Max_Updates];
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
        for(int i = 0 ; i < 10 + Max_Updates; i++)
            currentListBox[i] = "";
        ReadFromFile = new CSV<Settings>(this,"src/Telefon/Application/Settings.csv");
        ReadFromDB = new mySQL<Settings>(this);
    }

    public boolean OverClockOurCPU(float new_GHZ)
    {
        //Scanner x = new Scanner(System.in);
        //System.out.print("Introduceti noua Frecventa : ");
        //float new_GHZ = x.nextFloat();
        //System.out.print("\n");
        if(new_GHZ < min_GHZ || new_GHZ > min_GHZ + 2.5f)
        {
           // System.out.print("Aceasta valoare este una prea mica sau prea mare, va rugam sa introduceti o valoare peste minimul de : " + min_GHZ + " si maximul de " + min_GHZ + 2.5f + "\n");
           // System.out.print("Introduceti noua Frecventa : ");
            return  false;
        }
        if(new_GHZ > GHZ)
            System.out.println("Frecventa procesorului a fost crescuta de la " + GHZ + " la " + new_GHZ+ "\n");
        else
            System.out.println("Frecventa procesorului a fost scazuta de la " + GHZ + " la " + new_GHZ + "\n");

        GHZ = new_GHZ;
        return true;
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
    public void AddUpdate(String UpdateName, boolean fromFile) throws SQLException {
        boolean exists = false;
        for(int i = 0 ; i < iSelected_index ; i++) {
            if(Updates[i].equals(UpdateName))
                exists = true;
        }
        if(!exists)
        {
        Updates[iSelected_index] = UpdateName;
        iSelected_index++;
        if(!fromFile) {
            ReadFromFile.WriteFile(this, 1);
            ReadFromDB.InsertIntoTable(this, 1);
        }
        }
    }
    public void UpdateMyPhone()
    {

        if(iSelected_index == 0) {
        //System.out.print("Telefonul nu are niciun update disponibil \n");
        return;
        }
        OS = Updates[0];
        for(int i = 0 ; i < iSelected_index - 1; i++)
            Updates[i] = Updates[i + 1];
        iSelected_index--;
        String toShow = "|";
            Renderer.Label[0].setVisible(false);
            Renderer.Label[1].setVisible(false);
            Renderer.Label[2].setVisible(false);
            Renderer.Label(0,"Telefonul se updateaza... ",5, 250, 150, 20, true, Color.white, Color.black);
            Renderer.Label(1,toShow,5, 275, 150, 20, true, Color.white, Color.black);
            //System.out.print("|");
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
                    //System.out.print("=");
                    toShow+= "=";
                    Renderer.Label(1,toShow,5, 275, 150, 20, true, Color.white, Color.black);
                }
                if(x == 100) {
                    toShow += "| ";
                    Renderer.Label(1,toShow,5, 275, 150, 20, true, Color.white, Color.black);
                }

            }
        Renderer.Label(2,"Updateul a fost finalizat!",5, 300, 150, 20, true, Color.white, Color.black);

    }


    @Override
    public String toString() {
        currentListBox[0] = "Telefon : " + Denumire;
        currentListBox[1] = "Versiunea : " + Versiune;
        currentListBox[2] = "Lansat in data de : " + ReleaseDate;
        currentListBox[3] = "Sistemul de Operare : " + OS;
        currentListBox[4] = "Procesor : " + Procesor + "  avand o frecventa de : " + GHZ + "GHZ";
        currentListBox[5] = "Memorie Interna : " + Memorie_Interna;
        currentListBox[6] = "Memorie Ram : " + Memorie_RAM;
        for(int i = 0 ; i < iSelected_index; i++)
            currentListBox[7 + i] = "Update : " + Updates[i];
        return "Telefon : " + Denumire + "\n" +
               "Versiunea : " + Versiune + "\n" +
               "Lansat in data de : " + ReleaseDate + "\n" +
               "Sistemul de Operare : " + OS + "\n" +
               "Procesor : " + Procesor + "  avand o frecventa de : " + GHZ + "GHZ" +"\n" +
               "Memorie Interna : " + Memorie_Interna + "\n" +
               "Memorie Ram : " + Memorie_RAM + "\n";
    }
    public void UpdateListBox()
    {
        toString();
    }
    public boolean ControlPanel()
    {
        /*
        TODO : Reset To Factory Settings
         */
        iState = 0;
        Renderer.ResetEverything();
        Renderer.setTitle("-Settings-");
        //Renderer.ListBox(0,text_list,-1,50,350,150,true,Color.black);
        //String textLabel = "<html><br>Testy<br>></html>";
        //Renderer.Button(0,"Press Me",0,0,50,20);
        //System.out.println("-Agenda-");
        Renderer.ListBox(7 +iSelected_index, this.currentListBox, -1, -1, 400, 150, true, Color.black);
        Renderer.Button(0, "Despre Telefon", 5, 200, 150, 20);
        Renderer.Button(1, "OverClock CPU", 5 + Renderer.Button[0].getWidth(), 200, 225, 20);
        Renderer.Button(2, "Update OS", 5, 225, 225, 20);
        Renderer.Button(3, "Back to Menu", 5 + Renderer.Button[2].getWidth(), 225, 150, 20);
        Renderer.Button[0].addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                UpdateListBox();
                Renderer.ListBox(7 + iSelected_index, currentListBox, -1, -1, 400, 150, true, Color.black);
            }
        });
        Renderer.Button[1].addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!isSelected[0])
                {
                    Renderer.InputText(0, "GHZ", 5, 250, 150, 20, true, Color.white, Color.black);
                    isSelected[0] = true;
                } else {
                    float ghz = Float.parseFloat(Renderer.textField[0].getText());
                    if(OverClockOurCPU(ghz)){
                    Renderer.textField[0].setVisible(false);
                    isSelected[0] = false;
                    }
                }
                //Renderer.ListBox(0,currentListBox,-1,-1,400,150,true,Color.black);
            }
        });
        Renderer.Button[2].addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                UpdateMyPhone();
                //Renderer.ListBox(0,currentListBox,-1,-1,400,150,true,Color.black);
            }
        });
        Renderer.Button[3].addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                iState = 4;
            }
        });
        while (true) {
            try{Thread.sleep(250);}
            catch(InterruptedException e)
            {
                System.out.println(e);
            }
            if (iState == 4) {
                this.ReadFromFile.AuditSystem("Inapoi in Meniu");
                return true;
            }
        }
        /*
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
            this.ReadFromFile.AuditSystem("Afisare detalii telefon");
            System.out.println(this.toString());
        }
        else if(iState == 2)
        {
            this.ReadFromFile.AuditSystem("CPU Overclock");
            OverClockOurCPU();
        }
        else if(iState == 3)
        {
            this.ReadFromFile.AuditSystem("Update la sistem");
            UpdateMyPhone();
        }
        else if(iState == 4)
        {
            this.ReadFromFile.AuditSystem("Inapoi in Meniu");
            return true;
        }

        return false;*/
    }

}
