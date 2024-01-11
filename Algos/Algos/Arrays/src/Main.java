import java.util.Arrays;

public class Main {

    public static void main(String[] args) {

        String[] teams = {"Man City", "Man United", "Arsenal", "Chelsea", "Tottenham", "Real Madrid", "Barcelona"};
        System.out.println(Arrays.toString(teams));

        teams[2] = "Team Ndogo";
        System.out.println("Hello world!");
        System.out.println(teams.length);
        System.out.println(Arrays.toString(teams));
        System.out.println(teams[0]);

        // loop through an array
        for (String i : teams){
            System.out.println(i);
        }
    }
}

