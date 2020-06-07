package Telefon.Application;

import Telefon.DataBase.mySQL;
import Telefon.Files.CSV;
import Telefon.Meniu;
import Telefon.Render.Render;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;
import java.util.Vector;

public class Notite extends Meniu
{

    boolean isSelected[];
    String notite[];
    String myListBox[];
    CSV<Notite> ReadFromFile;
    mySQL<Notite> ReadFromDB;
    public Notite(Render rend) throws SQLException {
        super(rend);
        System.out.print("Numarul maxim de notite : ");
        Scanner x = new Scanner(System.in);
        int maxNotite = x.nextInt();
        notite = new String[maxNotite];
        myListBox = new String[maxNotite];
        for(int i = 0 ; i < maxNotite; i++)
            myListBox[i] = "";
        isSelected = new boolean[maxNotite];
        this.SetMaxIndex(maxNotite);
        ReadFromFile = new CSV<Notite>(this,"src/Telefon/Application/Notite.csv");
        Notite a = ReadFromFile.ReadFile();
        ReadFromDB = new mySQL<Notite>(this);

    }

    public Notite(int x,Render rend) throws SQLException {
        super(x,rend);
        notite = new String[x];
        myListBox = new String[x];
        for(int i = 0 ; i < x; i++)
            myListBox[i] = "";
        isSelected = new boolean[x];
        this.SetMaxIndex(x);
        ReadFromFile = new CSV<Notite>(this,"src/Telefon/Application/Notite.csv");
        ReadFromDB = new mySQL<Notite>(this);
    }

    public void AddNotite(String Text, boolean fromFile) throws SQLException {
        if(iSelected_index < iMax_Index)
        {
            notite[iSelected_index] = Text;
            iSelected_index++;
            if(!fromFile)
            {
                ReadFromFile.WriteFile(this,1);
                ReadFromDB.InsertIntoTable(this,1);
                System.out.println("Notita adaugata cu succes!");
            }
        }
        else {
            if (!fromFile)
                System.out.println("Ai atins numarul maxim de notite");
        }
    }
    public List GetList()
    {
        List<String> a = new Vector();
        for(int i = 0 ; i < iSelected_index; i++)
        {
            a.add(notite[i]);
        }
        return a;
    }
    public boolean RemoveNotite(int x) throws SQLException {
        x -= 1;
        if(x >= 0 && x < iSelected_index) {
            for (int i = x; i < iSelected_index - 1; i++) {
                notite[i] = notite[i + 1];
            }
            iSelected_index--;
            ReadFromDB.InsertIntoTable(this,1);
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
            myListBox[i] = notite[i];
            my_notes += (1 + i) + ") " +  notite[i] + "\n";
        }
        return my_notes;
    }
    public void UpdateString()
    {
        for(int i = 0 ; i < iSelected_index; i++)
            myListBox[i] = notite[i];
    }
    public boolean ControlPanel()
    {
        iState = 0;
        Renderer.ResetEverything();
        Renderer.setTitle("-Notite-");
        Renderer.ListBox(iSelected_index, myListBox, -1, -1, 400, 150, true, Color.black);
        Renderer.Button(0, "Afiseaza Notite", 5, 200, 150, 20);
        Renderer.Button(1, "Adauga o notita", 5 + Renderer.Button[0].getWidth(), 200, 225, 20);
        Renderer.Button(2, "Sterge o notita", 5, 225, 225, 20);
        Renderer.Button(3, "Back to Menu", 5 + Renderer.Button[2].getWidth(), 225, 150, 20);
        Renderer.Button[0].addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ReadFromFile.AuditSystem("Vizualizare Notite");
                System.out.println("Ok");
                UpdateString();
                Renderer.ListBox(iSelected_index, myListBox, -1, -1, 400, 150, true, Color.black);
            }
        });
        Renderer.Button[1].addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!isSelected[0])
                {
                    Renderer.InputText(0, "", 5, 250, 150, 20, true, Color.white, Color.black);
                    isSelected[0] = true;
                } else {
                    Renderer.textField[0].setVisible(false);
                    String notita = Renderer.textField[0].getText();
                    try {
                        AddNotite(notita,false);
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
            public void actionPerformed(ActionEvent e)
            {
                if (!isSelected[1]) {
                    Renderer.InputText(2, "ID-ul notitei", 5, 250, 150, 20, true, Color.white, Color.black);
                    isSelected[1] = true;
                } else {

                    String numele = Renderer.textField[2].getText();
                    int ID = Integer.parseInt(numele);
                    try {
                        if(RemoveNotite(ID))
                        {
                            isSelected[1] = false;
                            Renderer.textField[2].setVisible(false);
                        }
                    } catch (SQLException throwables) {
                        throwables.printStackTrace();
                    }
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
        }
        /*
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
        if(iState == 1) {
            this.ReadFromFile.AuditSystem("Vizualizare Notite");
            System.out.println(toString());
        }
        else if(iState == 2)
        {
            String text;
            System.out.print("Text : ");
            text = x.nextLine();
            AddNotite(text,false);
            this.ReadFromFile.AuditSystem("Adaugarea unei Notite");
        }
        else if(iState == 3)
        {

            if(iSelected_index == 0)
            {
                System.out.println("Nu aveti nicio notita!");
                return false;
            }
            int ID;
            System.out.print("Introduceti un numar[inclusiv] intre 1 si " + iSelected_index + " : ");
            ID = x.nextInt();
            this.ReadFromFile.AuditSystem("Stergerea unei notite");
            if(RemoveNotite(ID))
                System.out.println("Notita a fost stearsa cu succes!");
            else
                System.out.println("Numarul introdus a fost invalid!");
        }
        else if(iState == 4) {
            this.ReadFromFile.AuditSystem("Inapoi in Meniu");
            return true;
        }
        return false;*/
    }
}
