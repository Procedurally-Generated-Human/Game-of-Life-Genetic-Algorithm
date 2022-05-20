import java.util.Arrays;

public class Main {

    public static void main(String[] args){
        Population pop = new Population(100);
        for(int i=0; i!=100; i++){
            System.out.println(pop.getFittest());
            pop = Algorithm.evolvePopulation(pop);
        }




    }

}
