public class FitnessCalc {


    static int x = (int) Math.sqrt(Individual.defaultGeneLength);

    static byte[][] onetotwod(byte[] oned){
        int index = 0;
        byte[][] twod = new byte[x][x];
        for(int i=0; i!=x; i++){
            for(int j=0; j!=x; j++){
                twod[i][j] = oned[index];
                index += 1;
            }
        }
        return twod;
    }
    static byte[] twotooned(byte[][] twod){
        int index = 0;
        byte[] oned = new byte[x*x];
        for(int i=0; i!=x; i++){
            for(int j=0; j!=x; j++){
                oned[index] = twod[i][j];
                index += 1;
            }
        }
        return oned;
    }

    static void evolve(Individual indiv){
        byte[][] genes = onetotwod(indiv.getGenes());


        byte[][] future = new byte[x][x];
        // Loop through every cell
        for (int l = 0; l != x; l++)
        {
            for (int m = 0; m != x; m++)
            {
                // finding no Of Neighbours that are alive
                int aliveNeighbours = 0;
                for (int i = -1; i <= 1; i++)
                    for (int j = -1; j <= 1; j++)
                        if ((l+i>=0 && l+i<x) && (m+j>=0 && m+j<x))
                            aliveNeighbours += genes[l + i][m + j];

                // The cell needs to be subtracted from
                // its neighbours as it was counted before
                aliveNeighbours -= genes[l][m];

                // Implementing the Rules of Life

                // Cell is lonely and dies
                if ((genes[l][m] == 1) && (aliveNeighbours < 2))
                    future[l][m] = 0;

                    // Cell dies due to over population
                else if ((genes[l][m] == 1) && (aliveNeighbours > 3))
                    future[l][m] = 0;

                    // A new cell is born
                else if ((genes[l][m] == 0) && (aliveNeighbours == 3))
                    future[l][m] = 1;

                    // Remains the same
                else
                    future[l][m] = genes[l][m];
            }
        }
        byte[] newGenes = twotooned(future);
        indiv.setGenes(newGenes);

    }

    static int calculate(Individual ind){
        Individual indiv = new Individual();
        indiv.setGenes(ind.getGenes());
        for(int i=0; i!=100; i++){
            evolve(indiv);
        }
        int grade = 0;
        for(int i=0; i!=Individual.defaultGeneLength; i++){
            if(indiv.getGene(i) == 1){
                grade += 1;
            }
        }
        return grade;
    }
}
