package fileget;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

public class API {
    public static void main(String[] args) throws IOException {
        API api = new API();
        api.file();
    }

    private void file() throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        URL url = new URL("https://reqres.in/api/unknown");
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        InputStreamReader reader = new InputStreamReader(connection.getInputStream());
        BufferedReader bufferedReader = new BufferedReader(reader);
        String value = bufferedReader.readLine();
        Root root = mapper.readValue(value, Root.class);
        System.out.println("Page: " + root.page + "\n Total Page: " + root.total_pages + "\n Per Page: " + root.per_page);
        for (Datum datum : root.data) {
            System.out.println("Id : " + datum.id + "\n Name : " + datum.name + "\n Year :" + datum.year + "\n Color :" + datum.color);
        }
        System.out.println(root.support.url);
        System.out.println(root.support.text);
    }
}

class Datum {
    public int id;
    public String name;
    public int year;
    public String color;
    public String pantone_value;
}

class Root {
    public int page;
    public int per_page;
    public int total;
    public int total_pages;
    public ArrayList<Datum> data;
    public Support support;
}

class Support {
    public String url;
    public String text;
}