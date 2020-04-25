package Telefon.Application;

import Telefon.Files.CSV;
import Telefon.Meniu;

import java.util.List;
import java.util.Scanner;
import java.util.Vector;

public class WIFI extends Meniu
{

    String Nume_Retea[];
    int Latency[];
    String Encryp_Method[];
    String Parola[];



    String cur_Nume_Retea;
    int cur_Latency;
    String cur_Encryp_Method;
    String cur_Parola;
    CSV<WIFI>ReadFromFile;
    public List GetList()
    {
        List a = new Vector();
        for(int i = 0 ; i < iSelected_index; i++)
        {

            a.add(Nume_Retea[i]);
            a.add(Parola[i]);
            a.add(Encryp_Method[i]);
            a.add(Latency[i]);
        }
        return a;
    }
    public WIFI()
    {
        Scanner x = new Scanner(System.in);
        System.out.print("Numarul maxim de conexiuni : ");
        int iMax = x.nextInt();
        System.out.print("\n");
        this.SetMaxIndex(iMax);
        Nume_Retea = new String[iMax];
        Latency = new int[iMax];
        Encryp_Method = new String[iMax];
        Parola = new String[iMax];
        cur_Nume_Retea = null;
        cur_Latency = 0;
        ReadFromFile = new CSV<WIFI>(this,"src/Telefon/Application/WIFI.csv");

    }

    public WIFI(int iMax)
    {
        super(iMax);
        Nume_Retea = new String[iMax];
        Latency = new int[iMax];
        Encryp_Method = new String[iMax];
        Parola = new String[iMax];
        cur_Nume_Retea = null;
        cur_Latency = 0;
        ReadFromFile = new CSV<WIFI>(this,"src/Telefon/Application/WIFI.csv");
    }



    void SortArray()
    {

        String sort_Retea;
        int sort_Latency;
        String sort_Encryp_Method;
        String sort_Parola;
        for(int i = 0 ; i < iSelected_index; i++)
            for(int j = i + 1; j < iSelected_index; j++)
            {
                if(Latency[i] < Latency[j])
                {
                    sort_Retea = Nume_Retea[i];
                    sort_Latency = Latency[i];
                    sort_Encryp_Method = Encryp_Method[i];
                    sort_Parola = Parola[i];

                    Latency[i] = Latency[j];
                    Nume_Retea[i] = Nume_Retea[j];
                    Encryp_Method[i] = Encryp_Method[j];
                    Parola[i] = Parola[j];
                    Latency[j] = sort_Latency;
                    Nume_Retea[j] = sort_Retea;
                    Encryp_Method[j] = sort_Encryp_Method;
                    Parola[j] = sort_Parola;
                }
            }
    }

    public void AddNewWIFIConnection(String nume, String parola, String Enc_Method, int Latency, boolean FromFile)
    {
        if(Latency > 5 || Latency < 1)
        {
            if(!FromFile)
            System.out.println("Latenta nu se incadreaza intre minimul de 1 si maximul 5");
        return;
        }
        for(int i = 0 ; i < iSelected_index; i++)
        {
            if(nume == Nume_Retea[i])
            {
                if(!FromFile)
                    System.out.println("Conexiunea exista deja");
                return;
            }
        }
        if(iSelected_index < iMax_Index)
        {
        Nume_Retea[iSelected_index] = nume;
        Parola[iSelected_index] = parola;
        Encryp_Method[iSelected_index] = Enc_Method;
        this.Latency[iSelected_index] = Latency;
        iSelected_index++;
        SortArray();
        if(!FromFile)
            ReadFromFile.WriteFile(this,4);
        }
        else {
            if(!FromFile)
              System.out.println("Numarul maxim de retele a fost atins!");
        }
    }

    public boolean RemoveWIFIConnection(int x)
    {

        if(x >= 0 && x < iSelected_index) {

            if(Nume_Retea[x].equals(cur_Nume_Retea) && Parola[x].equals(cur_Parola) && cur_Latency == Latency[x] && cur_Encryp_Method.equals(Encryp_Method[x]))
                this.ClearCurrentWIFIConnection();


            for (int i = x; i < iSelected_index - 1; i++) {
                Nume_Retea[i] = Nume_Retea[i + 1];
                Parola[i] = Parola[i + 1];
                Encryp_Method[i] = Encryp_Method[i + 1];
                Latency[i] = Latency[i + 1];
            }
            iSelected_index--;
            return true;
        }
        return false;
    }

    public boolean ConnectToWIFI(int x)
    {
        if(x < iSelected_index && x >= 0)
        {

            cur_Nume_Retea = Nume_Retea[x];
            cur_Parola = Parola[x];
            cur_Encryp_Method = Encryp_Method[x];
            cur_Latency = Latency[x];
            return true;
        }
        return false;
    }

    public boolean ClearCurrentWIFIConnection()
    {
        if(cur_Nume_Retea != null)
        {
            cur_Nume_Retea = null;
            cur_Parola = null;
            cur_Encryp_Method = null;
            cur_Latency = 0;
            return true;
        }
        return false;
    }


    @Override
    public String toString()
    {

        String my_String = "Aveti " + iSelected_index + " retele WI-FI valide \n";
        for(int i = 0 ; i < iSelected_index; i++)
        {
            my_String += (i + 1) + ") Nume : " + Nume_Retea[i] + " Parola : ";
            for(int j = 0 ; j < Parola[i].length(); j++)
            {
                my_String += "*";
            }
            my_String += " Codat cu : " + Encryp_Method[i] + " Latency : ";
            for(int j = 0 ; j < Latency[i]; j++)
            {
                my_String += "|";
            }
            my_String += "\n";
        }
        if(cur_Nume_Retea != null)
        {
            my_String += "\n";
            my_String += "Sunteti conectat la Reteaua : \n";
            my_String +=  "Nume : " + cur_Nume_Retea + " Parola : ";
            for(int j = 0 ; j < cur_Parola.length(); j++)
            {
                my_String += "*";
            }
            my_String += " Codat cu : " + cur_Encryp_Method + " Latency : ";
            for(int j = 0 ; j < cur_Latency; j++)
            {
                my_String += "|";
            }
            my_String += "\n";
        }
        return my_String;
    }


    public boolean ControlPanel()
    {

        System.out.println("-WIFI-");
        System.out.println("1) Adauga o noua retea WI-FI");
        System.out.println("2) Conecteaza-te la o retea WI-FI");
        System.out.println("3) Arata retelele WI-FI");
        System.out.println("4) Deconecteaza-te de la reteaua WI-FI");
        System.out.println("5) Sterge o retea WI-FI");
        System.out.println("6) Back to Menu");
        Scanner x = new Scanner(System.in);
        System.out.print("Alegeti o functie din cele prezentate mai sus : ");
        this.iState = x.nextInt();
        System.out.print("\n");
        while(iState < 1 || iState > 6)
        {
            System.out.print("Numarul introdus este unul invalid, va rugam sa introduceti un numar intre 1 si 6 : ");
            this.iState = x.nextInt();
        }
        if(iState == 1)
        {
            Scanner xz = new Scanner(System.in);
            String nume_retea = null;
            String Parola_retea = null;
            String Encrypted_retea = null;
            int Latency_retea = 0;
            System.out.print("Numele Retelei : ");
            nume_retea = xz.nextLine();

            System.out.print("Parola Retelei : ");
            Parola_retea = xz.nextLine();

            System.out.print("Metoda de encryptare a Retelei : ");
            Encrypted_retea = xz.nextLine();
            System.out.print("Latenta Retelei : ");
            Latency_retea = xz.nextInt();
            this.ReadFromFile.AuditSystem("Adaugare Retea WIFI");
            AddNewWIFIConnection(nume_retea,Parola_retea,Encrypted_retea,Latency_retea,false);
        }
        else if(iState == 2)
        {
            Scanner xz = new Scanner(System.in);
            int reteaua = 0;
            System.out.println(this.toString());
            System.out.print("Reteaua la care sa va conectati : ");
            reteaua = xz.nextInt();
            reteaua--;
            if(reteaua >= 0 && reteaua < iSelected_index) {
                this.ReadFromFile.AuditSystem("Conectare la o retea WIFI");
                if (ConnectToWIFI(reteaua))
                    System.out.println("V-ati conectat cu succes!");
                else
                    System.out.println("Conectarea a esuat!");
            }
        }
        else if(iState == 3)
        {
            this.ReadFromFile.AuditSystem("Afisare retele WIFI");
            System.out.println(this.toString());
        }
        else if(iState == 4)
        {
            this.ReadFromFile.AuditSystem("Deconectarea dintr-o retea WIFI");
            if(this.ClearCurrentWIFIConnection() == true)
                System.out.println("Ati fost deconectat cu succes!");
            else
                System.out.println("Nu sunteti conectat la nicio retea");
        }
        else if(iState == 5)
        {
            if(iSelected_index == 0)
            {
                System.out.println("Nu exista retele de sters.");
                return false;
            }
            Scanner xz = new Scanner(System.in);
            int reteaua = 0;
            System.out.println(this.toString());
            System.out.print("Reteaua pe care doriti sa o stergeti : ");
            reteaua = xz.nextInt();
            reteaua--;
            if(reteaua >= 0 && reteaua < iSelected_index)
            {
                this.ReadFromFile.AuditSystem("Stergere retea WIFI");
                if (this.RemoveWIFIConnection(reteaua) == true)
                    System.out.println("Reteaua selectata a fost stearsa cu succes!");
                else
                    System.out.println("Reteaua nu exista");

            }
        }
        else if(iState == 6)
        {
            this.ReadFromFile.AuditSystem("Inapoi in Meniu");
            return true;
        }
        return false;
    }
}
