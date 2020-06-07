package Telefon.Application;

import Telefon.Meniu;
import Telefon.Files.CSV;
import Telefon.Render.Render;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.Vector;
import Telefon.DataBase.mySQL;
public class Agenda extends Meniu
{
    boolean isSelected[];
    String currentListBox[];
    String nume[];
    String number[];
    CSV<Agenda>ReadFromFile;
    mySQL<Agenda>ReadFromDB;
    public Agenda(Render rend) throws SQLException {
        super(rend);
        System.out.print("Nr max de persoane : ");
        Scanner x = new Scanner(System.in);
        int iMax = x.nextInt();
        this.SetMaxIndex(iMax);
        isSelected = new boolean[iMax];
        currentListBox = new String[iMax];
        for(int i = 0 ; i < iMax ; i++)
            currentListBox[iMax] = "";
        nume = new String[iMax];
        number = new String[iMax];
        ReadFromDB = new mySQL<Agenda>(this);
        ReadFromFile = new CSV<Agenda>(this,"src/Telefon/Application/Agenda.csv");

    }
    public Agenda(int x,Render rend) throws SQLException {
        super(x,rend);
        currentListBox = new String[x];

        isSelected = new boolean[x];
        for(int i = 0 ; i < x ; i++)
            currentListBox[i] = "";
        nume = new String[x];
        number = new String[x];
        ReadFromDB = new mySQL<Agenda>(this);

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
    public void AddToMyAgenda(String name, String number, boolean IsFromFile) throws SQLException {
        if(iSelected_index == this.iMax_Index) {
            return;
           // if (!IsFromFile)
                //System.out.println("Nu mai puteti adauga in agenda!");
        }
        else
            {
                for(int i = 0 ; i < iSelected_index; i++) {
                    if (this.number[i].equals(number) && this.nume[i].equals(name)) {
                        //if(!IsFromFile)
                         //System.out.println("Acest numar exista deja in agenda dumneavoastra!");
                        return;
                    }
                }
            this.nume[iSelected_index] = name;
            this.number[iSelected_index] = number;
            iSelected_index++;
            if(!IsFromFile)
            {
                ReadFromDB.InsertIntoTable(this,2);
                //ReadFromFile.WriteFile(this,2);
                //System.out.print("Numarul a fost adaugat cu succes!\n");
            }
        }
    }
    protected boolean RemoveFromMyAgenda(String name) throws SQLException {
        for(int i = 0; i < iSelected_index; i++)
        {
            if (nume[i].equals(name))
            {
                for (int j = i; j < iSelected_index - 1; j++) {
                    nume[j] = nume[j + 1];
                    number[j] = number[j + 1];
                }
                iSelected_index--;
                ReadFromDB.InsertIntoTable(this,2);
                return true;
            }
        }
        return false;
    }
    @Override
    public String toString() {
        String my_Agenda = "-------Agenda are " + iSelected_index +  " contacte -------\n";
        for(int i = 0 ; i < iSelected_index; i++)
        {
            this.currentListBox[i] = "Nume : " + nume[i] + " ----> " + "Numar : " + number[i];
            my_Agenda +=  "Nume : " + nume[i] + "\n" + "Numar : " + number[i] + " \n";
        }
        return my_Agenda;
    }
    public void UpdateToString()
    {
        toString();
    }
    public boolean ControlPanel() {

        iState = 0;
        Renderer.ResetEverything();
        Renderer.setTitle("-Agenda-");
        //Renderer.ListBox(0,text_list,-1,50,350,150,true,Color.black);
        //String textLabel = "<html><br>Testy<br>></html>";

        //Renderer.Button(0,"Press Me",0,0,50,20);
        //System.out.println("-Agenda-");
        Renderer.ListBox(iSelected_index, this.currentListBox, -1, -1, 400, 150, true, Color.black);
        Renderer.Button(0, "Vizualizati Agenda", 5, 200, 150, 20);
        Renderer.Button(1, "Adaugati o persoana in Agenda", 5 + Renderer.Button[0].getWidth(), 200, 225, 20);
        Renderer.Button(2, "Stergeti o persoana din Agenda", 5, 225, 225, 20);
        Renderer.Button(3, "Back to Menu", 5 + Renderer.Button[2].getWidth(), 225, 150, 20);
        Renderer.Button[0].addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                UpdateToString();
                Renderer.ListBox(iSelected_index, currentListBox, -1, -1, 400, 150, true, Color.black);
            }
        });
        Renderer.Button[1].addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!isSelected[0])
                {
                    Renderer.InputText(0, "Numele Persoanei", 5, 250, 150, 20, true, Color.white, Color.black);
                    Renderer.InputText(1, "Numarul Persoanei", 5, 275, 150, 20, true, Color.white, Color.black);
                    isSelected[0] = true;
                } else {
                    Renderer.textField[0].setVisible(false);
                    Renderer.textField[1].setVisible(false);
                    String numele = Renderer.textField[0].getText();
                    String numar = Renderer.textField[1].getText();
                    try {
                        AddToMyAgenda(numele, numar, false);
                    } catch (SQLException throwables) {
                        throwables.printStackTrace();
                    }
                    isSelected[0] = false;
                }
                //Renderer.ListBox(0,currentListBox,-1,-1,400,150,true,Color.black);
            }
        });
        Renderer.Button[2].addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!isSelected[1]) {
                    Renderer.InputText(2, "Numele Persoanei", 5, 250, 150, 20, true, Color.white, Color.black);
                    isSelected[1] = true;
                } else {
                    Renderer.textField[2].setVisible(false);
                    String numele = Renderer.textField[2].getText();

                    boolean isRemoved = false;
                    try {
                        isRemoved = RemoveFromMyAgenda(numele);
                    } catch (SQLException throwables) {
                        throwables.printStackTrace();
                    }

                    isSelected[1] = false;
                }
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
        }/*
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
    }*/
    }
}
