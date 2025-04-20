package vao;

import java.io.*;
import java.util.ArrayList;

public class Stop {
    private int stop_id;
    private String stop_name;
    private static ArrayList<Stop> AllStops = new ArrayList();

    //kontruktor
    public Stop(int stop_id, String stop_name){
        this.stop_id = stop_id;
        this.stop_name = stop_name;
    }

    //getters/setters
    public int getStop_id() {
        return stop_id;
    }

    public void setStop_id(int stop_id) {
        this.stop_id = stop_id;
    }

    public String getStop_name() {
        return stop_name;
    }

    public void setStop_name(String stop_name) {
        this.stop_name = stop_name;
    }

    public static ArrayList getAllStops() {
        return AllStops;
    }

    public static void setAllStops(ArrayList allStops) {
        AllStops = allStops;
    }

    //branje postaj
    public static ArrayList preberiPostaje() {
        BufferedReader br;

        {
            try {
                System.out.println("1. Tu");

                br = new BufferedReader(new FileReader("../data/stops.txt"));
                String vrstica;

                //preskocimo prvo vrstico ker nima vrednosti
                br.readLine();

                while ((vrstica = br.readLine()) != null) {
                    String[] podatki = vrstica.split(",");

                    Stop postaja = new Stop(Integer.parseInt(podatki[0]), podatki[2]);
                    AllStops.add(postaja);
                }

                System.out.println("2. Konec");
            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        return AllStops;
    }

    //toString
    @Override
    public String toString() {
        return "stop_id= " + stop_id + ", stop_name='" + stop_name;
    }

    //metode
    public static Stop getStopById(int id){
        return AllStops.stream().filter(stop -> stop.stop_id == id).findFirst().get();
    }
}
