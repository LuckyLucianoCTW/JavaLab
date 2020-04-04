package Telefon.Application;

import Telefon.Meniu;

import java.util.Scanner;

public class PlayList extends Meniu
{
    String Song[];
    String Artist[];
    String ReleaseDate[];
    public PlayList()
    {
        System.out.print("Numarul maxim de melodii : ");
        Scanner x = new Scanner(System.in);
        int iMax = x.nextInt();
        this.SetMaxIndex(iMax);
        Song = new String[iMax];
        Artist = new String[iMax];
        ReleaseDate = new String[iMax];
    }
    public PlayList(int x)
    {
        super(x);

        this.SetMaxIndex(x);
        Song = new String[x];
        Artist = new String[x];
        ReleaseDate = new String[x];
    }

    public boolean AddSong(String Song, String Artist, String ReleaseDate)
    {
        for(int i = 0 ; i < iSelected_index; i++)
            if(Song.equals(this.Song[i]) && Artist.equals(this.Artist[i]) && ReleaseDate.equals(this.ReleaseDate[i]))
                return false;
        this.Song[iSelected_index] = Song;
        this.Artist[iSelected_index] = Artist;
        this.ReleaseDate[iSelected_index] = ReleaseDate;
        iSelected_index++;
        return true;
    }

    public boolean RemoveSong(int x)
    {
        if(x >= 0 && x < iSelected_index)
        {
            for (int i = x; i < iSelected_index - 1; i++)
            {
                Song[i] = Song[i + 1];
                Artist[i] = Artist[i + 1];
                ReleaseDate[i] = ReleaseDate[i + 1];
            }
            iSelected_index--;
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
            if(AddSong(My_Song,My_Artist,Release_Date))
                System.out.println("Melodia a fost adaugata in PlayList");
            else
                System.out.println("Melodia exista deja in playlist");
        }
        else if(this.iState == 3)
        {

            int my_Song;
            System.out.println(this.toString());
            System.out.print("Alegeti un numar : ");
            my_Song = x.nextInt();
            my_Song--;
            if(RemoveSong(my_Song))
                System.out.println("Melodia a fost stearsa din PlayList");
            else
                System.out.println("Numarul introdus este invalid!");
        }
        else if(this.iState == 4)
        {
            return true;
        }
        return false;
    }
}
