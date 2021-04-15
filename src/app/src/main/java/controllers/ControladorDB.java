package controllers;
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

    public ControladorDB() throws SQLException, ClassNotFoundException {
        this.con = null;
        this.usuario = "godzilla";
        this.contra = "teachu1234";
        this.url = "jdbc:mysql://teachu.cgcnxzpwojtb.us-east-2.rds.amazonaws.com:3306/teachU?user="+usuario+"&password="+contra;

    }

    public void run(){
        try{
            //con = DriverManager.getConnection( (url+"?user="+usuario+"&password="+contra + "?useUnicode=true&characterEncoding=UTF-8&zeroDateTimeBehavior=CONVERT_TO_NULL&serverTimezone=GMT") );
            con = DriverManager.getConnection(url);
        }
        catch (SQLException e){
            e.printStackTrace();
            throw new Error("Error", e);
        }
    }


}
