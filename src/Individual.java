import java.util.Arrays;

public class Individual {

    static final int defaultGeneLength = 400;
    static final int realGeneLength = 100;
    static final int x = (int) Math.sqrt(defaultGeneLength);
    private byte[] genes = new byte[defaultGeneLength];
    static final int paddingSize = 5;
    private int fitness = 0;

    public Individual(){
        for(int i=0; i!=realGeneLength; i++){
            byte gene = (byte)Math.round(Math.random());
            setGene(gene, i);
        }
        byte[][] padded_genes_2d = new byte[x+(2*paddingSize)][x+(2*paddingSize)];
        byte[][] genes_2d = FitnessCalc.oney(genes);
        for (byte[] bytes : padded_genes_2d) {
            Arrays.fill(bytes, (byte) 0);
        }
        int k = 0;
        for(int i=paddingSize; i!=paddingSize + 10; i++){
            int p =0;
            for(int j=paddingSize; j!=Math.sqrt(defaultGeneLength)-paddingSize; j++){
                padded_genes_2d[i][j] = genes_2d[k][p];
                p++;
            }
            k++;
        }
        this.genes = FitnessCalc.twotooned(padded_genes_2d);

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
                if(genes[i*x + j]==1){
                    grid.append("#");
                }
                else{
                    grid.append(" ");
                }
                grid.append(" ");
            }
            grid.append("\n");
        }
        return grid.toString();
    }

    public void evolve(int n){
        for(int i=0; i!=n+1;){
            printGrid();
        }
    }
}
