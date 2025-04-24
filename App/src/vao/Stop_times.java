package vao;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Comparator;
import java.util.stream.Collectors;

public class Stop_times {
    private Stop stop;
    private LocalTime arrival_time;
    private LocalTime departure_time;
    private static ArrayList<Stop_times> AllStops_times = new ArrayList();

    //konstruktor
    public Stop_times(Stop stop, LocalTime arrival_time, LocalTime departure_time){
        this.stop = stop;
        this.arrival_time = arrival_time;
        this.departure_time = departure_time;

    }

    //getterji/setterji


    public Stop getStop() {
        return stop;
    }

    public void setStop(Stop stop) {
        this.stop = stop;
    }

    public LocalTime getArrival_time() {
        return arrival_time;
    }

    public void setArrival_time(LocalTime arrival_time) {
        this.arrival_time = arrival_time;
    }

    public LocalTime getDeparture_time() {
        return departure_time;
    }

    public void setDeparture_time(LocalTime departure_time) {
        this.departure_time = departure_time;
    }

    public static ArrayList<Stop_times> getAllStops_times() {
        return AllStops_times;
    }

    public static void setAllStops_times(ArrayList<Stop_times> allStops_times) {
        AllStops_times = allStops_times;
    }

    //branje
    public static ArrayList postajeSCasi() {
        BufferedReader br;
        {
            try {
                br = new BufferedReader(new FileReader("../data/stop_times.txt"));
                String vrstica;

                //preskoci prvo vrstico
                br.readLine();

                while ((vrstica = br.readLine()) != null) {
                    String[] podatki = br.readLine().split(",");

                    //dobim postajo iz arrayLista postaj da ji dodelim cas
                    Stop stop = Stop.getStopById(Integer.parseInt(podatki[3]));

                    //pretvorba string v localtime
                    LocalTime arrival = LocalTime.parse(podatki[1]);
                    LocalTime departure = LocalTime.parse(podatki[2]);

                    //ustvarim objekt postaje s casom prihoda
                    Stop_times Stop_wTime = new Stop_times(stop, arrival, departure);

                    //lahko bi zdruzil z ustvarjanjem
                    AllStops_times.add(Stop_wTime);
                }
            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        return AllStops_times;
    }

    //toString
    @Override
    public String toString() {
        return "\nstop: " + stop + ", arrival_time: " + arrival_time + ", departure_time: " + departure_time ;
    }

    //metode
    public List<Stop_times> sortiranjePoPrihodu(){
        return AllStops_times.stream().sorted(Comparator.comparing(Stop_times::getArrival_time)).collect(Collectors.toList());
    }

    //izpis postaj

}
