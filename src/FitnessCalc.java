public class FitnessCalc {

    Individual indiv;

    public Individual getIndiv() {
        return indiv;
    }

    public void setIndiv(Individual indiv) {
        this.indiv = indiv;
    }

    public byte[][] onetotwod(byte[] oned){
        int index = 0;
        byte[][] twod = new byte[10][10];
        for(int i=0; i!=10; i++){
            for(int j=0; j!=10; j++){
                twod[i][j] = oned[index];
                index += 1;
            }
        }
        return twod;
    }

    public byte[] twotooned(byte[][] twod){
        int index = 0;
        byte[] oned = new byte[100];
        for(int i=0; i!=10; i++){
            for(int j=0; j!=10; j++){
                oned[index] = twod[i][j];
                index += 1;
            }
        }
        return oned;
    }

    public void evolve(){
        byte[][] genes = onetotwod(indiv.getGenes());

        int x = 10;
        int y = 10;

        byte[][] future = new byte[x][y];
        // Loop through every cell
        for (int l = 0; l != x; l++)
        {
            for (int m = 0; m != y; m++)
            {
                // finding no Of Neighbours that are alive
                int aliveNeighbours = 0;
                for (int i = -1; i <= 1; i++)
                    for (int j = -1; j <= 1; j++)
                        if ((l+i>=0 && l+i<x) && (m+j>=0 && m+j<y))
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

    public int calculate(){
        for(int i=0; i!=100; i++){
            evolve();
        }
        int grade = 0;
        for(int i=0; i!=100; i++){
            if(indiv.getGene(i) == 1){
                grade += 1;
            }
        }
        return grade;
    }
}
