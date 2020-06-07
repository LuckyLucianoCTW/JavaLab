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

public class PlayList extends Meniu
{
    String Song[];
    String Artist[];
    String ReleaseDate[];
    CSV<PlayList>ReadFromFile;
    String myListBox[];
    boolean isSelected[];
    mySQL<PlayList> ReadFromDB;
    public List GetList()
    {
        List<String> a = new Vector();
        for(int i = 0 ; i < iSelected_index; i++)
        {
            a.add(Song[i]);
            a.add(Artist[i]);
            a.add(ReleaseDate[i]);
        }
        return a;
    }
    public PlayList(Render rend) throws SQLException {
        super(rend);
        System.out.print("Numarul maxim de melodii : ");
        Scanner x = new Scanner(System.in);
        int iMax = x.nextInt();
        this.SetMaxIndex(iMax);
        Song = new String[iMax];
        Artist = new String[iMax];
        myListBox = new String[iMax];
        isSelected = new boolean[iMax];
        for (int i = 0 ; i < iMax; i++)
            myListBox[i] = "";
        ReleaseDate = new String[iMax];
        ReadFromFile = new CSV<PlayList>(this,"src/Telefon/Application/PlayList.csv");
        ReadFromDB = new mySQL<PlayList>(this);
    }
    public PlayList(int x,Render rend) throws SQLException {
        super(x,rend);

        this.SetMaxIndex(x);
        myListBox = new String[x];
        isSelected = new boolean[x];
        Song = new String[x];
        Artist = new String[x];
        for (int i = 0 ; i < x; i++)
            myListBox[i] = "";
        ReleaseDate = new String[x];
        ReadFromFile = new CSV<PlayList>(this,"src/Telefon/Application/PlayList.csv");
        ReadFromDB = new mySQL<PlayList>(this);
    }

    public boolean AddSong(String Song, String Artist, String ReleaseDate, boolean fromFile) throws SQLException {
        if(iSelected_index  == iMax_Index)
            return false;
        for(int i = 0 ; i < iSelected_index; i++)
            if(Song.equals(this.Song[i]) && Artist.equals(this.Artist[i]) && ReleaseDate.equals(this.ReleaseDate[i]))
                return false;
        this.Song[iSelected_index] = Song;
        this.Artist[iSelected_index] = Artist;
        this.ReleaseDate[iSelected_index] = ReleaseDate;
        iSelected_index++;
        if(!fromFile) {
            ReadFromFile.WriteFile(this, 3);
            ReadFromDB.InsertIntoTable(this, 3);
        }
        return true;
    }

    public boolean RemoveSong(int x) throws SQLException {
        if(x >= 0 && x < iSelected_index)
        {
            for (int i = x; i < iSelected_index - 1; i++)
            {
                Song[i] = Song[i + 1];
                Artist[i] = Artist[i + 1];
                ReleaseDate[i] = ReleaseDate[i + 1];
            }
            iSelected_index--;
            ReadFromDB.InsertIntoTable(this, 3);
            return true;
        }
        return false;
    }

    @Override
    public String toString() {
      String my_Playlist = "--Aveti " + iSelected_index + " melodii in playlist!--\n";
      for(int i = 0 ; i < iSelected_index; i++)
      {
          my_Playlist += (i+1) + ") " + Song[i] + " - " + Artist[i] + " - " + ReleaseDate[i] + "\n";
      }
      return my_Playlist;
    }

    public boolean ControlPanel()
    {
        iState = 0;
        Renderer.ResetEverything();
        Renderer.setTitle("-PlayList-");
        Renderer.ListBox(iSelected_index, myListBox, -1, -1, 400, 150, true, Color.black);
        Renderer.Button(0, "Afiseaza Playlistul", 5, 200, 150, 20);
        Renderer.Button(1, "Adauga o melodie", 5 + Renderer.Button[0].getWidth(), 200, 225, 20);
        Renderer.Button(2, "Sterge o melodie", 5, 225, 225, 20);
        Renderer.Button(3, "Back to Menu", 5 + Renderer.Button[2].getWidth(), 225, 150, 20);
        Renderer.Button[0].addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ReadFromFile.AuditSystem("Vizualizare Playlist");
                for(int i = 0 ; i < iSelected_index; i++)
                    myListBox[i] = Song[i] + " - " + Artist[i] + " - " + ReleaseDate[i];
                Renderer.ListBox(iSelected_index, myListBox, -1, -1, 400, 150, true, Color.black);
            }
        });
        Renderer.Button[1].addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!isSelected[0])
                {
                    Renderer.InputText(0, "Numele Melodiei", 5, 250, 150, 20, true, Color.white, Color.black);
                    Renderer.InputText(1, "Aristul", 5, 275, 150, 20, true, Color.white, Color.black);
                    Renderer.InputText(2, "Data Lansarii", 5, 300, 150, 20, true, Color.white, Color.black);
                    isSelected[0] = true;
                } else {

                    String My_Song = Renderer.textField[0].getText();
                    String My_Artist = Renderer.textField[1].getText();
                    String Release_Date = Renderer.textField[2].getText();
                    try {
                        if(AddSong(My_Song,My_Artist,Release_Date,false))
                        {
                            Renderer.textField[0].setVisible(false);
                            Renderer.textField[1].setVisible(false);
                            Renderer.textField[2].setVisible(false);
                            isSelected[0] = false;
                        }
                    } catch (SQLException throwables) {
                        throwables.printStackTrace();
                    }
                }
                //Renderer.ListBox(0,currentListBox,-1,-1,400,150,true,Color.black);
            }
        });
        Renderer.Button[2].addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                if (!isSelected[1]) {
                    Renderer.InputText(2, "ID-ul Melodiei", 5, 250, 150, 20, true, Color.white, Color.black);
                    isSelected[1] = true;
                } else {

                    String numele = Renderer.textField[2].getText();
                    int ID = Integer.parseInt(numele) - 1;
                    try {
                        if(RemoveSong(ID))
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
        System.out.println("-PlayList-");
        System.out.println("1) Afiseaza PlayListul");
        System.out.println("2) Adauga o melodie");
        System.out.println("3) Sterge o melodie");
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
        if(this.iState == 1)
        {
            this.ReadFromFile.AuditSystem("Afisare PlayList");
            System.out.println(this.toString());
        }
        else if(this.iState == 2)
        {
            String My_Song;
            String My_Artist;
            String Release_Date;
            System.out.print("Numele Melodiei : ");
            My_Song = x.nextLine();
            System.out.print("Numele Artistului : ");
            My_Artist = x.nextLine();
            System.out.print("Data Lansarii Melodiei : ");
            Release_Date = x.nextLine();
            this.ReadFromFile.AuditSystem("Adaugarea unei melodii in PlayList");
            if(AddSong(My_Song,My_Artist,Release_Date,false))
                System.out.println("Melodia a fost adaugata in PlayList");
            else
                System.out.println("Melodia exista deja in playlist sau nu mai este loc.");
        }
        else if(this.iState == 3)
        {

            if(iSelected_index == 0)
            {
                System.out.println("Nu aveti nicio melodie in playlist!");
                return false;
            }
            int my_Song;
            System.out.println(this.toString());
            System.out.print("Alegeti un numar : ");
            my_Song = x.nextInt();
            my_Song--;
            this.ReadFromFile.AuditSystem("Stergerea din PlayList");
            if(RemoveSong(my_Song))
                System.out.println("Melodia a fost stearsa din PlayList");
            else
                System.out.println("Numarul introdus este invalid!");
        }
        else if(this.iState == 4)
        {
            this.ReadFromFile.AuditSystem("Inapoi in Meniu");
            return true;
        }
        return false;*/
    }
}
