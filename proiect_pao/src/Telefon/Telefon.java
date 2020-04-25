package Telefon;


import Telefon.Application.Agenda;
import Telefon.Application.Notite;
import Telefon.Application.Settings;
import Telefon.Application.WIFI;
import Telefon.Application.PlayList;
import Telefon.Application.Ceas;
import Telefon.Application.Mesagerie;

import java.util.Scanner;

public class Telefon {


    //public static void main(String[] args)
    Meniu Functii_Telefon[];
    public Telefon()
    {
        Functii_Telefon = new Meniu[7];
        Agenda my_Agenda = new Agenda(100);
        Ceas my_Clock = new Ceas(1,22,50);
        my_Clock.setTick(System.currentTimeMillis());
        Mesagerie my_Messages = new Mesagerie(100);
        Notite my_Notes = new Notite(100);
        PlayList my_Playlist = new PlayList(100);
        Settings my_Settings = new Settings("Samsung","A70","21.12.2018","Android 3.0","Ryzen",3.0f,"32GB","4GB",100);
        WIFI my_WIFI = new WIFI(100);
        my_Settings.AddUpdate("Android 3.5",false);
        Functii_Telefon[0] = my_Agenda;
        Functii_Telefon[1] = my_Clock;
        Functii_Telefon[2] = my_Messages;
        Functii_Telefon[3] = my_Notes;
        Functii_Telefon[4] = my_Playlist;
        Functii_Telefon[5] = my_Settings;
        Functii_Telefon[6] = my_WIFI;
    }
    public void StartPhone()
    {
        int iState = 0;

        while(true)
        {
            if(iState == 0) {
                System.out.println("-Meniul Telefonului-");
                System.out.println("1) Agenda");
                System.out.println("2) Ceas");
                System.out.println("3) Mesaje");
                System.out.println("4) Notite");
                System.out.println("5) Playlist");
                System.out.println("6) Setari");
                System.out.println("7) WI-FI");
                System.out.println("8) Inchide Telefonul");
                Scanner x = new Scanner(System.in);
                System.out.print("Alegeti o functie din cele prezentate mai sus : ");
                iState = x.nextInt();
                System.out.print("\n");
                while (iState < 1 || iState > 8) {
                    System.out.print("Numarul introdus este unul invalid, va rugam sa introduceti un numar intre 1 si 8 : ");
                    iState = x.nextInt();
                }
            }
            else
            {
                if(iState == 8)
                    return;
                while(!Functii_Telefon[iState - 1].ControlPanel());

                iState = 0;
            }

        }
        //int x = 59;
        //System.out.println(x % 60);
       // WIFI connec = new WIFI();
       // while(!connec.ControlPanel());
        //Settings mySettings = new Settings("Samsung","A70","21.12.2018","Android 3.0","Ryzen",3.0f,"32GB","4GB",100);
        //mySettings.AddUpdate("Android 3.0");
        //while(!mySettings.ControlPanel());
        //Ceas ceasu = new Ceas(04,8,38);
        //double stime = System.currentTimeMillis();
        //ceasu.setTick(stime);
        //while(!ceasu.ControlPanel());
        //double stime = System.currentTimeMillis();

        //Agenda agenda = new Agenda();
        //while(!agenda.ControlPanel());

        //Mesagerie mess = new Mesagerie();
        //while(!mess.ControlPanel());
        //Notite note = new Notite();
        //while(!note.ControlPanel());

        //PlayList plist = new PlayList(50);
        //while(!plist.ControlPanel());
    }

}
