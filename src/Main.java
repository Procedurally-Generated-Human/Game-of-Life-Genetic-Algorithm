import java.util.Arrays;

public class Main {

    public static void main(String[] args){
        Population pop = new Population(100);
        System.out.println(pop);
        System.out.println("**************");
        System.out.println(pop.getFittest());
        System.out.println(pop.getFittest().printGrid());
    }
}
