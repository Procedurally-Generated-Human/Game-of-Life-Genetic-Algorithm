public class Main {

    public static void main(String[] args){
        Population pop = new Population(100);
        System.out.println(pop);
        FitnessCalc f = new FitnessCalc();
        for(int i=0; i!=100; i++){
            f.setIndiv(pop.getIndividual(i));
            int grade = f.calculate();
            System.out.println(grade);
        }
    }
}
