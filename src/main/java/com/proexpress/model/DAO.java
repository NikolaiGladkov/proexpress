package com.proexpress.model;

import com.proexpress.controller.MainController;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DAO {
    private MainController mainController;
    public DAO(MainController mainController){this.mainController=mainController;}
    // JDBC driver name and database URL
    static final String JDBC_DRIVER = "org.h2.Driver";
    //static final String DB_URL = "jdbc:h2:~/proexpress";
    static final String DB_URL = "jdbc:h2:C:/Users/Nikolai/IdeaProjects/proexpress\\src\\main\\resources/db/proexpress";
    //  Database credentials
    static final String USER="sa";
    static final String PASS="sa";
    String sql;
    Connection conn=null;
    Statement stmt=null;




public void connectDb(String user, String password){

}

public void createTable(String name){
    String sql;
    try{
        Class.forName( JDBC_DRIVER );
        conn=DriverManager.getConnection( DB_URL,USER,PASS );
        stmt=conn.createStatement();
        sql="CREATE TABLE"+ " `"+name+"` "+"(`ID` INT NOT NULL AUTO_INCREMENT PRIMARY KEY, `plot` TEXT,`street` TEXT,`house` TEXT,`housing` TEXT,`floor` TEXT,`door` TEXT,`apartment` TEXT,`other` TEXT )";
        stmt.executeUpdate( sql );
    }

    catch (Exception e){
        System.out.println( e );
    }
}

public void createRow(String plot, String street, String house, String housing, String floor, String door, String apartment, String other)
{
    String sql;
    try{
        Class.forName( JDBC_DRIVER );
        conn=DriverManager.getConnection( DB_URL,USER,PASS );
        stmt=conn.createStatement();
        sql="INSERT INTO SOV (plot, street, house,housing,floor,door,apartment,other) VALUES" + " ("+"'"+plot+"'"+","+"'"+street+"'"+","+"'"+house+"'"+","+ "'"+housing+"'"+","+ "'"+floor+"'"+","+ "'"+door+"'"+","+ "'"+apartment+"'"+"," +"'"+other+"')";
        stmt.executeUpdate( sql );
    }

    catch (Exception e){
        System.out.println( e );
    }
}

}
