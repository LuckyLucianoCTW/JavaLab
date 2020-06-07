package Telefon.Files;

import Telefon.Application.*;
import Telefon.Meniu;

import java.io.*;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.Date;

public class CSV<T> implements FileInterface<T>
{
    private String Path;
    private T template;
    public CSV(T myTemplate,String value)
    {
        template = myTemplate;
        this.Path = value;
        //ReadFile();
    }
    public T ReadFile()
    {
        return null;
    /*
        String TextInFile = "";


            BufferedReader br = null;
            try
            {
                br = new BufferedReader(new FileReader(this.Path));
                while ((TextInFile = br.readLine()) != null)
                {
                    String [] text = TextInFile.split(";");
                    if(template instanceof Agenda)
                        ((Agenda)template).AddToMyAgenda(text[0],text[1],true);
                    else if (template instanceof Notite)
                        ((Notite)template).AddNotite(text[0],true);
                    else if (template instanceof PlayList)
                        ((PlayList)template).AddSong(text[0],text[1],text[2],true);
                    else if (template instanceof Settings)
                        ((Settings)template).AddUpdate(text[0],true);
                    else if (template instanceof WIFI)
                        ((WIFI)template).AddNewWIFIConnection(text[0],text[1],text[2],Integer.parseInt(text[3]),true);
                }
                br.close();
            }
            catch (FileNotFoundException e)
            {
                e.printStackTrace();
            } catch (IOException e)
            {
                e.printStackTrace();
            }

            return template;
        */
    }

    public boolean WriteFile(T x,int separator)
    {
        /*
        if(x instanceof Agenda || x instanceof Mesagerie || x instanceof Notite || x instanceof PlayList || x instanceof Settings || x instanceof WIFI)
        {
            Meniu a = (Meniu)x;

            File myFile = new File(this.Path);
            try
            {
                FileWriter outputfile = new FileWriter(myFile);
                List b = a.GetList();
                for(int i = 0 ; i < b.size() ; i+= separator)
                {
                    for(int j = 0 ; j < separator; j++) {
                        outputfile.write(b.get(i + j).toString());
                        if(j != separator - 1)
                            outputfile.write(";");
                    }
                    outputfile.write(System.lineSeparator());
                }
                outputfile.close();
                return true;
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
        }*/
        return false;
    }
    public void AuditSystem(String Function)
    {
        List<String>myStringAudit = new Vector<String>();
        BufferedReader br = null;
        String TextInFile = "";
        try {
            br = new BufferedReader(new FileReader("src/Telefon/Files/auditLog.csv"));
            while ((TextInFile = br.readLine()) != null)
                myStringAudit.add(TextInFile);
            br.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        File myFile = new File("src/Telefon/Files/auditLog.csv");
        try
        {
            FileWriter outputfile = new FileWriter(myFile);
            for(int i = 0 ; i < myStringAudit.size(); i++)
            {
                outputfile.write(myStringAudit.get(i));
                outputfile.write(System.lineSeparator());
            }
            Timestamp timestamp = new Timestamp(System.currentTimeMillis());
            outputfile.write(Function + " at " + timestamp);
            outputfile.write(System.lineSeparator());
            outputfile.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
