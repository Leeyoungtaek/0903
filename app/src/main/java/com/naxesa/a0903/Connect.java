package com.naxesa.a0903;

import android.util.Log;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Iterator;

/**
 * Created by Lee young teak on 2016-10-04.
 */

public class Connect {
    private static final String url = null;
    public static synchronized JSONObject postData(JSONObject request){
        JSONObject result = null;
        try
        {
            URL urlLink = new URL(url);
            HttpURLConnection con = (HttpURLConnection) urlLink.openConnection();
            con.setRequestMethod("POST");
            con.setRequestProperty("Accept-Language", "ko-kr,ko;q=0.8,en-us;q=0.5,en;q=0.3");

            Iterator<String> keys = request.keys();
            String urlParameters = "Command="+request.get("Command");
            while(keys.hasNext()){
                String key = keys.next();
                StringBuilder builder = new StringBuilder();
                builder.append("&");
                builder.append(key);
                builder.append("=");
                builder.append(request.get(key));
                urlParameters +=builder.toString();
            }

            con.setDoOutput(true);
            DataOutputStream wr = new DataOutputStream(con.getOutputStream());
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(wr, "UTF-8"));
            writer.write(urlParameters);
            writer.flush();
            writer.close();
            wr.close();

            int responseCode = con.getResponseCode();
            Log.d("Response Code", responseCode + "");

            BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream(),"UTF-8"));
            StringBuffer response = new StringBuffer();
            String inputLine;

            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();
            result = new JSONObject(response.toString());
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return result;
    }
}
