package controllers;
import android.os.health.SystemHealthManager;
import android.text.TextUtils;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

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

}
