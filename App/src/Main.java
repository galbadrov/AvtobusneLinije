import java.util.Scanner;
import vao.Stop;
import vao.Stop_times;
import vao.Trip;

public class Main {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        // vnasanje podatkov
        System.out.println("Vnesi id postaje:");
        int id_postaje = sc.nextInt();

        System.out.println("Vnesi stevilo naslednjih avtobusov:");
        int st_avtobusov = sc.nextInt();

        System.out.println("Vnesi nacin izpisa casa: \n 1 - relative \n 2 - absolute:");
        int nacin_zapisa = sc.nextInt();

        //testni izpis --> ustrezno branje
        //System.out.println("izpis vseh postaj: ");
        //System.out.println(Stop.preberiPostaje());
        Stop.preberiPostaje();

        //System.out.println("izpis postaj s casi prihodov: ");
        //System.out.print(Stop_times.postajeSCasi());
        Stop_times.postajeSCasi();

        System.out.println("--------------------------------------------");
        //System.out.println("izpis tripov: ");
        //System.out.print(Trip.VsiTripi());
        Trip.VsiTripi();

        //izpis logike
        // primer: postaja z id 9,
        //System.out.println("izpis prihodov na postajo: ");
        //Stop_times.prihodiNaPostaji(9, 2, 1);

        //dejanska uporaba iz vnosov:
        Stop_times.prihodiNaPostaji(id_postaje, st_avtobusov, nacin_zapisa);

    }
}