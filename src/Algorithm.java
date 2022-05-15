public class Algorithm {

    private static final double uniformRate = 0.5;
    private static final double mutationRate = 0.0100;
    private static final int tournamentSize = 7;
    private static final boolean elitism = true;

    public static Population evolvePopulation(Population pop){
        Population newPop = new Population(pop.size());
        Individual fittest = pop.getFittest();
        for(int i=0; i!=pop.size(); i++){
            Individual indiv1 = tournamentSelection(pop);
            Individual indiv2 = tournamentSelection(pop);
            Individual newIndiv = crossover(indiv1, indiv2);
            newPop.saveIndividual(newIndiv, i);
        }
        if (elitism){
            newPop.saveIndividual(fittest, 0);
        }
        return newPop;
    }

    private static Individual crossover(Individual indiv1, Individual indiv2){
        Individual newIndiv = new Individual();
        for (int i = 0; i < indiv1.getGenes().length; i++) {
            // Crossover
            if (Math.random() <= uniformRate) {
                newIndiv.setGene(indiv1.getGene(i), i);
            } else {
                newIndiv.setGene(indiv2.getGene(i), i);
            }
        }
        mutate(newIndiv);
        return newIndiv;
    }

    private static void mutate(Individual indiv){
        for (int i = 0; i < indiv.getGenes().length; i++) {
            if (Math.random() <= mutationRate) {
                // Create random gene
                byte gene = (byte) Math.round(Math.random());
                indiv.setGene(gene, i);
            }
        }
    }

    private static Individual tournamentSelection(Population pop){
        Population tournament = new Population(tournamentSize);
        for(int i=0; i!=tournamentSize; i++){
            int randomId = (int) (Math.random() * pop.size());
            tournament.saveIndividual(pop.getIndividual(randomId), i);
        }
        return tournament.getFittest();
    }
}
