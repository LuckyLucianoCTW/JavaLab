package Telefon.DataBase;
import Telefon.Application.Agenda;
import Telefon.Application.Notite;
import Telefon.Application.PlayList;
import Telefon.Application.Settings;
import Telefon.Meniu;

import java.sql.*;
import java.util.List;

public class mySQL<T> {
    static Connection connection = null;
    static String url = "jdbc:mysql://localhost:3306/pao_project";
    static String username = "root";
    static String password = "asdqwe123";
    T template;
    public mySQL(T Mytemplate) throws SQLException {
        template = Mytemplate;
        connection = DriverManager.getConnection(url,username,password);
        ReadTable();
    }
    public boolean ReadTable() throws SQLException {
        Statement stat = connection.createStatement();

        String sql = "select * from ";
        if(template instanceof Agenda)
        {
            sql += "agenda";
        }
        else if(template instanceof Notite)
        {
            sql += "notite";
        }
        else if(template instanceof Settings)
        {
            sql += "settings";
        }
        else if(template instanceof PlayList)
        {
            sql += "playlist";
        }
        ResultSet rs = stat.executeQuery(sql);
        while(rs.next())
        {
            if(template instanceof Agenda)
            {
                ((Agenda)template).AddToMyAgenda(rs.getString("Nume"),rs.getString("Numar"),true);
            }
            else if(template instanceof Notite)
            {
                ((Notite)template).AddNotite(rs.getString("Nume"),true);
            }
            else if(template instanceof Settings)
            {
                ((Settings)template).AddUpdate(rs.getString("Update"),true);
            }
            else if(template instanceof PlayList)
            {
                ((PlayList)template).AddSong(rs.getString("Nume"),rs.getString("Titlu"),rs.getString("Release"),true);
            }
        }
        return true;
    }
    public boolean InsertIntoTable(T myTemp, int separator) throws SQLException {
        Statement stat = connection.createStatement();
        String sql = "delete from ";
        String template_name = "";
        if(template instanceof Agenda)
        {
            template_name = "agenda";
        }
        else if(template instanceof Notite)
        {
            template_name = "notite";
        }
        else if(template instanceof Settings)
        {
            template_name = "settings";
        }
        else if(template instanceof PlayList)
        {
            template_name = "playlist";
        }
        stat.executeUpdate(sql + template_name);
        Meniu a = (Meniu)myTemp;
        sql = "insert into " + template_name + " values(";
        List myList = a.GetList();

        for(int i = 0 ; i < myList.size() ; i+= separator)
        {
            String List = "";
            for(int j = 0 ; j < separator; j++) {
                List += "'"+myList.get(i + j).toString()+"'";
                if(j != separator - 1)
                    List +=(",");
            }

            stat.executeUpdate(sql+List+")");
        }
        //INSERT INTO AGENDA VALUES("SIKUTA","07646346");
        return true;
    }
}
