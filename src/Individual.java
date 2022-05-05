import java.util.Arrays;

public class Individual {

    static final int defaultGeneLength = 100;
    private byte[] genes = new byte[defaultGeneLength];
    private int fitness = 0;

    public Individual(){
        for(int i=0; i!=defaultGeneLength; i++){
            byte gene = (byte)Math.round(Math.random());
            setGene(gene, i);
        }
    }

    public void setGene(byte gene, int index){
        genes[index] = gene;
    }
    public void setGenes(byte[] genes){
        this.genes = genes;
    }

    public int calculateFitness(){
        this.fitness = FitnessCalc.calculate(this);
        return this.fitness;
    }

    public byte[] getGenes(){
        return genes;
    }
    public byte getGene(int index){
        return genes[index];
    }
    public int getFitness() {
        return calculateFitness();
    }

    public String toString(){
        String s_genes = Arrays.toString(getGenes()).replace(",", "").replace("[", "").replace("]", "").replace(" ","");
        return "{fitness: " + getFitness() + ", genes: " + s_genes + "}";
    }

    public String printGrid(){
        StringBuilder grid = new StringBuilder();
        int x = (int) Math.sqrt(defaultGeneLength);
        for(int i=0; i!=x; i++){
            for(int j=0; j!=x; j++){
                grid.append(genes[i*x + j]);
                grid.append("  ");
            }
            grid.append("\n");
        }
        return grid.toString();
    }
}
