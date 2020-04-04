package Telefon.Application;

import Telefon.Meniu;

import java.util.Arrays;
import java.util.Scanner;

public class Ceas extends Meniu {
    int ceas[];
    int Alarm[];
    boolean ActivateAlarm;
    double tickcount;

    public void setTick(double tick)
    {
        this.tickcount = tick;
    }
    public boolean IsAvailable(int ziua,int ora,int minut,int secunda)
    {
        if(ziua < 0)
            return false;
        if(ora < 0)
            return false;
        if(minut < 0)
            return false;
        if(minut < 0)
            return false;
        return true;
    }

    public void ConvertCeas()
    {
        int rest = 0;
        if(this.ceas[0] > 59)
        {
            rest = (this.ceas[0] - (this.ceas[0] % 60)) / 60;
            this.ceas[0] %= 60;
        }

        this.ceas[1] += rest;
        rest = 0;
        if(this.ceas[1] > 59)
        {
            rest = (this.ceas[1] - (this.ceas[1] % 60)) / 60;
            this.ceas[1] %= 60;
        }
        this.ceas[2] += rest;
        rest = 0;
        if(this.ceas[2] > 23)
        {
            rest = (this.ceas[2] - (this.ceas[2] % 24)) / 24;
            this.ceas[2] %= 24;
        }
        this.ceas[3] += rest;
        rest = 0;
    }
    public void ConvertAlarm()
    {
        int rest = 0;
        if(this.Alarm[0] > 59)
        {
            rest = (this.Alarm[0] - (this.Alarm[0] % 60)) / 60;
            this.Alarm[0] %= 60;
        }

        this.Alarm[1] += rest;
        rest = 0;
        if(this.Alarm[1] > 59)
        {
            rest = (this.Alarm[1] - (this.Alarm[1] % 60)) / 60;
            this.Alarm[1] %= 60;
        }
        this.Alarm[2] += rest;
        rest = 0;
        if(this.Alarm[2] > 23)
        {
            rest = (this.Alarm[2] - (this.Alarm[2] % 24)) / 24;
            this.Alarm[2] %= 24;
        }
        this.Alarm[3] += rest;
    }
    public Ceas(int zi,int ora,int minut)
    {
        if(IsAvailable(zi,ora,minut,0)) {

            this.ceas = new int[4];
            this.Alarm = new int[4];
            this.ceas[3] = zi;
            this.ceas[2] = ora;
            this.ceas[1] = minut;
            this.ceas[0] = 0;
            ConvertCeas();
        }
        else  throw new RuntimeException("Orele introduse sunt invalide\n");
    }

    public void Alarm(int zi,int ora,int minut,int secunda)
    {
        this.Alarm[3] = zi;
        this.Alarm[2] = ora;
        this.Alarm[1] = minut;
        this.Alarm[0] = secunda;
        ActivateAlarm = true;
        ConvertAlarm();
    }
    public void RemoveAlarm()
    {
        if(ActivateAlarm)
        {

            System.out.println("Alarma a fost stearsa cu succes!");
            ActivateAlarm = false;
        }
        else
            System.out.println("Nu aveti o alarma setata!");
    }
    public void ActivateOurAlarm()
    {
        String ora;
        String minutu;
        String Secunda;
        if(this.ceas[0] < 10)
            Secunda = "0" + this.ceas[0];
        else
            Secunda = "" + this.ceas[0];
        if(this.ceas[1] < 10)
            minutu = "0" + this.ceas[1];
        else
            minutu = "" + this.ceas[1];
        if(this.ceas[2] < 10)
            ora = "0" + this.ceas[2];
        else
            ora = "" + this.ceas[2];
        String ceas = ora + ":" + minutu + ":" + Secunda + "\n";
        for(int i = 0 ; i < 10 ; i++)
        {
            try{Thread.sleep(200);}
            catch(InterruptedException e)
            {
                System.out.println(e);
            }
            System.out.print("ALARMA D-VOASTRA DE LA ORA : " + ceas + "\n");
        }
        UpdateOurClock();
    }
    public void UpdateOurClock()
    {
        /*
        Avem nevoie de multi-threading + concurenta pentru a putea realiza o Alarma ce se poate activa in timp real,
        Sa speram ca pana la ultima etapa a proiectului pot updata aceasta functie pentru a folosi paralelismul de functii...
        Pana atunci, ramane un conceput dragut.
         */
        if(ActivateAlarm && (this.Alarm[0] == this.ceas[0] && this.Alarm[1] == this.ceas[1] && this.ceas[2] == this.Alarm[2] && this.ceas[3]  == this.Alarm[3]))
            this.ActivateOurAlarm();

        this.ceas[0] += 1;
        if(this.ceas[0] == 60)
        {
            this.ceas[0] = 0;
            this.ceas[1] += 1;
            if(this.ceas[1] == 60)
            {
                this.ceas[1] = 0;
                this.ceas[2] += 1;
                if(this.ceas[2] == 24)
                {
                    this.ceas[2] = 0;
                    this.ceas[3] += 1;
                }
            }
        }
    }

    @Override
    public String toString() {
        ceas[0] += ((System.currentTimeMillis() - tickcount) / 1000);
        this.UpdateOurClock();
        tickcount = System.currentTimeMillis();
        String ora;
        String minutu;
        String Secunda;
        if(this.ceas[0] < 10)
            Secunda = "0" + this.ceas[0];
        else
            Secunda = "" + this.ceas[0];
        if(this.ceas[1] < 10)
            minutu = "0" + this.ceas[1];
        else
            minutu = "" + this.ceas[1];
        if(this.ceas[2] < 10)
            ora = "0" + this.ceas[2];
        else
            ora = "" + this.ceas[2];
        String ceas = "Ziua : " + this.ceas[3] + " --- " + ora + ":" + minutu + ":" + Secunda + "\n";
        if(ActivateAlarm)
        {
            ceas += "Aveti o alarma activata!\n";
            if(this.Alarm[0] < 10)
                Secunda = "0" + this.Alarm[0];
            else
                Secunda = "" + this.Alarm[0];
            if(this.Alarm[1] < 10)
                minutu = "0" + this.Alarm[1];
            else
                minutu = "" + this.Alarm[1];
            if(this.Alarm[2] < 10)
                ora = "0" + this.Alarm[2];
            else
                ora = "" + this.Alarm[2];
            ceas += "Ziua : " + this.Alarm[3] + " --- " + ora + ":" + minutu + ":" + Secunda + "\n";
        }
        else
            ceas+= "Nu aveti o alarma setata!\n";
        return ceas;
    }

    public boolean ControlPanel()
    {
        tickcount =  System.currentTimeMillis();
        System.out.println("-Ceas-");
        System.out.println("1) Afisati ora si Alarma");
        System.out.println("2) Setati Alarma");
        System.out.println("3) Setati ora si ziua");
        System.out.println("4) Stergeti Alarma!");
        System.out.println("5) Back to Menu");
        Scanner x = new Scanner(System.in);
        System.out.print("Alegeti o functie din cele prezentate mai sus : ");
        this.iState = x.nextInt();
        System.out.print("\n");
        while(iState < 1 || iState > 5)
        {
            System.out.print("Numarul introdus este unul invalid, va rugam sa introduceti un numar intre 1 si 5 : ");
            this.iState = x.nextInt();
        }
        x.nextLine();
        if(iState == 1)
        {
            System.out.println(toString());
        }
        else if(iState == 2)
        {
            int new_alarm[] = new int[4];
            System.out.print("Ziua : ");
            new_alarm[3] = x.nextInt();
            System.out.print("Ora : ");
            new_alarm[2] = x.nextInt();
            System.out.print("Minut : ");
            new_alarm[1] = x.nextInt();
            System.out.print("Secunda : ");
            new_alarm[0] = x.nextInt();
            if(IsAvailable(new_alarm[3],new_alarm[2],new_alarm[1],new_alarm[0]))
            {
                Alarm(new_alarm[3],new_alarm[2],new_alarm[1],new_alarm[0]);
                ConvertAlarm();
                System.out.println("Alarma a fost setata cu succes!");
            }
            else
                System.out.println("Alarma introdusa este invalida!");
        }
        else if(iState == 3)
        {
            int new_ceas[] = new int[4];
            System.out.print("Ziua : ");
            new_ceas[3] = x.nextInt();
            System.out.print("Ora : ");
            new_ceas[2] = x.nextInt();
            System.out.print("Minut : ");
            new_ceas[1] = x.nextInt();
            System.out.print("Secunda : ");
            new_ceas[0] = x.nextInt();
            if(IsAvailable(new_ceas[3],new_ceas[2],new_ceas[1],new_ceas[0]))
            {
                ceas[0] = new_ceas[0];
                ceas[1] = new_ceas[1];
                ceas[2] = new_ceas[2];
                ceas[3] = new_ceas[3];
                ConvertCeas();
                System.out.println("Orele ceasului au fost modificate!");
            }
            else
                System.out.println("Valorile introduse sunt invalide.");
        }
        else if(iState == 4)
        {
            RemoveAlarm();
        }
        else if(iState == 5)
        {
            return true;
        }
        return false;
    }

}
