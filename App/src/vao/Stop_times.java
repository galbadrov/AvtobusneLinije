package vao;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalTime;
import java.util.*;
import java.util.stream.Collectors;

public class Stop_times {
    private String trip_id;
    private Stop stop;
    private LocalTime arrival_time;
    private LocalTime departure_time;
    private static ArrayList<Stop_times> AllStops_times = new ArrayList();

    //konstruktor
    public Stop_times(String trip_id, Stop stop, LocalTime arrival_time, LocalTime departure_time){
        this.trip_id = trip_id;
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

    public String getTrip_id() {
        return trip_id;
    }

    public void setTrip_id(String trip_id) {
        this.trip_id = trip_id;
    }

    //branje
    public static void postajeSCasi() {
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
                    Stop_times Stop_wTime = new Stop_times(podatki[0], stop, arrival, departure);

                    //lahko bi zdruzil z ustvarjanjem
                    AllStops_times.add(Stop_wTime);
                }
            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    //toString
    @Override
    public String toString() {
        return "\nstop: " + stop + ", arrival_time: " + arrival_time + ", departure_time: " + departure_time ;
    }

    //metode
    public static void prihodiNaPostaji(int stopId, int steviloAvtobusov, int nacin) {
        LocalTime now = LocalTime.now();

        // 1. Filtriramo stop_times za to postajo in naslednji 2 uri
        List<Stop_times> prihodi = Stop_times.getAllStops_times().stream()
                .filter(st -> st.getStop().getStop_id() == stopId)
                .filter(st -> st.getArrival_time().isAfter(now) && st.getArrival_time().isBefore(now.plusHours(2)))
                .collect(Collectors.toList());

        //testni izpis
        //System.out.println("filtriran list: " + prihodi);

        // 2. Združimo po route_id da dobimo kljuc vrednost (linija in casi)
        Map<Integer, List<LocalTime>> poLinijah = new HashMap<>();



        //filtrirajmo pridobljen trip_id iz stop times iz razrednega atributa AllTrips
        for (Stop_times st : prihodi) {
            String tripId = st.getTrip_id();

            //preverimo ali v Trip obstaja ta trip id, ki je v Stop_times
            Optional<Trip> tripOpt = Trip.getAllTrips().stream().filter(trip -> trip.getTrip_id().equals(tripId)).findFirst();

            //ce imamo trip s tem id potem vzamimo route id tega tripa in dodajmo
            if (tripOpt.isPresent()) {
                int routeId = tripOpt.get().getRoute_id();
                //poLinijah.computeIfAbsent(routeId, k -> new ArrayList<>()).add(st.getArrival_time()); //nevem kako dobiti zdruziti kdaj pride avtobus na postajo in route--> ugotovil

                // Preverimo, ali ključ že obstaja v poLinijah
                if (poLinijah.containsKey(routeId)) {
                    // Če obstaja, preprosto dodamo čas prihoda v obstoječi seznam
                    //pogoj da samo toliko koliko je podano stevilo max avtobusov
                    if(poLinijah.get(routeId).size() < steviloAvtobusov) {
                        poLinijah.get(routeId).add(st.getArrival_time());
                    }else{
                        //ne dodamo vec avtobusa ker je zapolnjeno --> glede na stevilo ki smo ga podali
                    }
                } else {
                    // Če ključ ne obstaja, ustvarimo nov seznam, dodamo čas in ga shranimo v mapo
                    List<LocalTime> newTimes = new ArrayList<>();
                    newTimes.add(st.getArrival_time());
                    poLinijah.put(routeId, newTimes);
                }
            }
        }

        //dobimo ustrezni stop glede na podan id Postaje
        Stop stopIzpis = AllStops_times.stream().filter(st -> st.getStop().getStop_id() == stopId).findFirst().get().getStop();

        //pogoj za izpis:
        if(nacin == 1) {
            // 3. Izpišemo v obliki: 6: 12:10, 12:15, 12:40


            //izpis
            System.out.println("busTrips " + stopId + " relative \n" + stopIzpis.getStop_name());
            for (Map.Entry<Integer, List<LocalTime>> entry : poLinijah.entrySet()) {
                int routeId = entry.getKey();
                List<LocalTime> casi = entry.getValue().stream()
                        .sorted()
                        .collect(Collectors.toList());

                String ure = casi.stream()
                        .map(time -> time.toString()) // ali time.format(DateTimeFormatter.ofPattern("HH:mm"))
                        .collect(Collectors.joining(", "));

                //izpis za vsako linijo ce obstaja
                System.out.println(routeId + ": " + ure);
            }
        }else if(nacin == 2){
            //logika za

        }
    }
}
