package co.app.mercaditodesofi.controller;

import android.os.AsyncTask;

import org.apache.hc.core5.http.NameValuePair;
import org.apache.hc.core5.net.URLEncodedUtils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import javax.net.ssl.HttpsURLConnection;

import co.app.mercaditodesofi.data.LoginRepository;
import co.app.mercaditodesofi.data.model.LoggedInUser;

public class RequestController {

    //Public usable methods
    public static String makeRequest(final String url, String type, HashMap<String, String> params) throws IOException {

        LoggedInUser user = LoginRepository.getInstance(null).getUser();

        if(user != null) {
            params.put("u32", user.getDisplayName());
            params.put("cpno", user.getPassword());
        }

        AsyncTask<Void, Void, String> task = new AsyncTask<Void, Void, String>() {

            @Override
            protected String doInBackground(Void[] objects) {

                try {
                    String paramsAsString, host_url;

                   host_url = url;

                    List<NameValuePair> paramsInList = new LinkedList<>();

                    for (Map.Entry<String, String> entry : params.entrySet()) {
                        final String key = entry.getKey(),
                                value = entry.getValue();

                        NameValuePair pair = new NameValuePair() {
                            @Override
                            public String getName() {
                                return key;
                            }

                            @Override
                            public String getValue() {
                                return value;
                            }
                        };

                        paramsInList.add(pair);
                    }

                    paramsAsString = URLEncodedUtils.format(paramsInList, Charset.forName("UTF-8"));

                    if (type == "GET")
                        host_url += "?" + paramsAsString;

                    URL host = new URL(host_url);

                    HttpsURLConnection http = (HttpsURLConnection) host.openConnection();
                    http.setRequestMethod(type);

                    if (type == "POST") {
                        http.setDoOutput(true);
                        OutputStream ostream = http.getOutputStream();
                       ostream.write(paramsAsString.getBytes());
                        ostream.flush();
                        ostream.close();
                    }

                    int responseCode = http.getResponseCode();

                    if (responseCode == HttpsURLConnection.HTTP_OK) {
                        BufferedReader in = new BufferedReader(new InputStreamReader(http.getInputStream()));

                        String line;
                        StringBuffer response = new StringBuffer();

                        while ((line = in.readLine()) != null) {
                            response.append(line);
                        }
                        in.close();

                        return response.toString();
                    }

                } catch(Exception ex) {
                    MessageController.showError(ex.getMessage());
                }

                return null;
            }
        };

        task.execute();

        String result = "";

        try {

            result = task.get(5000, TimeUnit.MILLISECONDS);

        } catch(Exception ex) {
            MessageController.showError(ex.getMessage());
        }

        return result;
    }


}
