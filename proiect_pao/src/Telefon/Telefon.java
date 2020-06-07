package Telefon;


import Telefon.Application.Agenda;
import Telefon.Application.Notite;
import Telefon.Application.Settings;
import Telefon.Application.WIFI;
import Telefon.Application.PlayList;
import Telefon.Application.Ceas;
import Telefon.Application.Mesagerie;
import Telefon.Render.Render;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.sql.SQLException;
import java.util.Scanner;

public class Telefon
{


    //public static void main(String[] args)
    Render rend;
    Meniu Functii_Telefon[];

    public  int iState = -2;
    public Telefon() throws SQLException {
        rend = new Render("SAMSUNG");
        Functii_Telefon = new Meniu[7];
        Agenda my_Agenda = new Agenda(100,rend);
        Ceas my_Clock = new Ceas(1,22,50,rend);
        my_Clock.setTick(System.currentTimeMillis());
        Mesagerie my_Messages = new Mesagerie(100,rend);
        Notite my_Notes = new Notite(100,rend);
        PlayList my_Playlist = new PlayList(100,rend);
        Settings my_Settings = new Settings("Samsung","A70","21.12.2018","Android 3.0","Ryzen",3.0f,"32GB","4GB",100,rend);
        WIFI my_WIFI = new WIFI(100,rend);
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

        while(true)
        {
            if(iState == -2)
            {
                iState = -1;
                rend.ResetEverything();
                rend.setTitle("-Meniu-");
                rend.Button(0, "Agenda", 60, 30, 100, 20);
                rend.Button(1, "Ceas", rend.Button[0].getWidth() + 60, 30, 100, 20);
                rend.Button(2, "Notite", 60 + (rend.Button[1].getWidth() + rend.Button[0].getWidth()), 30, 100, 20);
                rend.Button(3, "PlayList", 60, 50, 100, 20);
                rend.Button(4, "Setari", rend.Button[3].getWidth() + 60 + rend.Button[2].getWidth(), 50, 100, 20);
                //rend.Button(5, "WI-FI", 60 + (rend.Button[4].getWidth() + rend.Button[3].getWidth()), 50, 100, 20);
                rend.Button(6, "Inchide Telefonul", 60, 70, 300, 20);
                rend.Button[0].addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        iState = 0;
                    }
                });
                rend.Button[1].addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        iState = 1;
                    }
                });
                rend.Button[2].addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        iState = 3;
                    }
                });
                rend.Button[3].addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        iState = 4;
                    }
                });
                rend.Button[4].addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        iState = 5;
                    }
                });
                rend.Button[6].addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        iState = 9;
                    }
                });
                continue;
            }
            try{Thread.sleep(250);}
            catch(InterruptedException e)
            {
                System.out.println(e);
            }
            if(iState == 9) {
                rend.ShutDown();
                return;
            }
            if (iState >= 0 && iState <= 6)
            {
                Functii_Telefon[iState].ControlPanel();
                iState = -2;
                continue;
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