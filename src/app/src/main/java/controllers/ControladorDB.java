package controllers;
import android.os.health.SystemHealthManager;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.sql.Statement;

public class ControladorDB extends Thread{
    private String url;
    private String usuario;
    private String contra;
    private Connection con;

    public ControladorDB() {
        this.con = null;
        this.usuario = "godzilla";
        this.contra = "teachu1234";
        this.url = "jdbc:mysql://teachu.cgcnxzpwojtb.us-east-2.rds.amazonaws.com:3306/teachU?user="+usuario+"&password="+contra;

    }

    public void run(){

    }

   /* public Connection conexion(){
        Connection con = DriverManager

    }*/

}
