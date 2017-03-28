package utils;

import com.android.volley.toolbox.RequestFuture;
import com.android.volley.toolbox.StringRequest;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONException;

import java.util.List;
import java.util.concurrent.ExecutionException;

import model.QuoteBean;

/**
 * Created by 89003337 on 2017/3/23.
 */

public class Request {

    public static QuoteBean stringRequest(String url, int method,String tag) throws ExecutionException, InterruptedException, JSONException {
        RequestFuture<String> requestFuture = RequestFuture.newFuture();
        com.android.volley.Request req=new StringRequest(method,url, requestFuture,requestFuture);
        req.setTag(tag);
        RequestManager.getRequestQueue().add(req);
        Gson gson = new Gson();
        String result=requestFuture.get();

        System.out.println("request result:"+result);
        List<QuoteBean> list = gson.fromJson(result, new TypeToken<List<QuoteBean>>() {}.getType());
        if(list.size()>0)
            return list.get(0);
        else
            return null;
    }

}
