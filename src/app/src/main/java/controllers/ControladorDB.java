package controllers;
import android.os.health.SystemHealthManager;
import android.text.TextUtils;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

import java.io.IOException;
import java.io.PrintStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

public class ControladorDB extends Thread{
    public static final String TAG = ControladorDB.class.getSimpleName();
    private RequestQueue rqq;


    public ControladorDB() {

    }

    public RequestQueue getRqq() {
        if (rqq == null) {
            return null;
        }

        return rqq;
    }

    public <T> void agregarARqq(Request<T> req, String tag) {
        req.setTag(TextUtils.isEmpty(tag) ? TAG : tag);
        getRqq().add(req);
    }

    public void pasarAphp(String nombre, String apellido, String correo, String contrasena, String nusuario, String id_usuario){
        URL url = new URL("http://192.168.0.5/webservices/registro.php");
        try {
            URL url = new URL("http://192.168.0.5/webservices/registro.php");
            URLConnection con = url.openConnection();
            con.setDoOutput(true);
            PrintStream ps = new PrintStream(con.getOutputStream());
            ps.print("nombre="+nombre);
            ps.print("&apellido="+apellido);
            ps.print("&correo="+correo);
            ps.print("&contrasena="+contrasena);
            ps.print("&nusuario="+nusuario);
            ps.print("&id_usuario="+id_usuario);
            con.getInputStream();
            ps.close();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
