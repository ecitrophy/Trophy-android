package com.edu.eci.ieti.trophy;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.ProtocolException;
import java.net.URL;

/**
 *
 * @author Jonathan Prieto
 */
public class HttpConnection {

    private static final String USER_AGENT = "Mozilla/5.0";

    public static String getUrlData(String urlParam) throws IOException {

        URL objUrl = new URL(urlParam);
        HttpURLConnection con = (HttpURLConnection) objUrl.openConnection();
        con.setRequestMethod("GET");
        con.setRequestProperty("User-Agent", USER_AGENT);

        //The following invocation perform the connection implicitly before getting the code
        int responseCode = con.getResponseCode();
        //System.out.println("GET Response Code :: " + responseCode);

        if ((responseCode == HttpURLConnection.HTTP_ACCEPTED) || (responseCode == HttpURLConnection.HTTP_OK)) { // success
            BufferedReader in = new BufferedReader(new InputStreamReader(
                    con.getInputStream()));
            String inputLine;
            StringBuffer response = new StringBuffer();

            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();

            // print result
            return response.toString();
        } else {
            return "Error en los parametros de la peticion";
        }
        //System.out.println("GET DONE");
    }


}
