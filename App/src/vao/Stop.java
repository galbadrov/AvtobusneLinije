package vao;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.List;

public class stops {
    private int stop_id;
    private String stop_name;
    static List AllStops;

    //kontruktor
    public stops(int stop_id, String stop_name){
        this.stop_id = stop_id;
        this.stop_name = stop_name;
    }


    //branje postaj
    BufferedReader br;
    {
        try {
            br = new BufferedReader(new FileReader("../data/stops.txt"));
            String vrstica;
            while((vrstica = br.readLine()) != null){
                String[] podatki = vrstica.split(",");

                stops postaja = stops(podatki[]);
                AllStops.add(postaja);
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    //metode
    public zacasno shranjevanje
}
