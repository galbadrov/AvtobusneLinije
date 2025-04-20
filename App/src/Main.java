import java.util.Scanner;
import vao.Stop;
import vao.Stop_times;

public class Main {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        // vnasanje podatkov
        System.out.println("Vnesi id postaje:");
        int id_postaje = sc.nextInt();

        System.out.println("Vnesi stevilo naslednjih avtobusov:");
        int st_avtobusov = sc.nextInt();

        System.out.println("Vnesi nacin izpisa casa (relative - absolute):");
        String nacin_zapisa = sc.next();

        //testni izpis
        System.out.println("izpis vseh postaj: ");
        System.out.println(Stop.preberiPostaje());

        System.out.println("izpis postaj s casi prihodov: ");
        System.out.print(Stop_times.postajeSCasi());

        //izpis logike

    }
}