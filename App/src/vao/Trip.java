package vao;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;

public class Trip {
    private String trip_id;
    private int route_id;
    private static ArrayList<Trip> AllTrips = new ArrayList<>();

    //konstruktor
    public Trip(String trip_id, int route_id) {
        this.trip_id = trip_id;
        this.route_id = route_id;
    }

    public String getTrip_id() {
        return trip_id;
    }

    public void setTrip_id(String trip_id) {
        this.trip_id = trip_id;
    }

    public int getRoute_id() {
        return route_id;
    }

    public void setRoute_id(int route_id) {
        this.route_id = route_id;
    }

    public static ArrayList<Trip> getAllTrips() {
        return AllTrips;
    }

    public static void setAllTrips(ArrayList<Trip> allTrips) {
        AllTrips = allTrips;
    }

    //branje podatkov
    public static int VsiTripi() {
        BufferedReader br;

            try {
                br = new BufferedReader(new FileReader("../data/trips.txt"));
                String vrstica;

                //preskoci prvo vrstico
                br.readLine();

                while ((vrstica = br.readLine()) != null) {

                    String[] podatki = vrstica.split(",");

                    int podatkiInt_routeId = Integer.parseInt(podatki[0]);

                    AllTrips.add(new Trip(podatki[2], podatkiInt_routeId));
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

        return Trip.AllTrips.size();
    }

    @Override
    public String toString() {
        return "trip_id: " + trip_id + ", route_id: " + route_id;
    }
}
