package sample;


import static sample.Constans.GRID_DIM;
import static sample.Constans.VERTICES_NUM;

public class Grid {
    Vertice[][] vertices;
    Vertice[] verticesTable;
    MyFileReaderWriter f = new MyFileReaderWriter();
    public Grid()  {
          vertices = f.read();
          verticesTable = new Vertice[VERTICES_NUM];


        for (int i=0 ; i <GRID_DIM; i++){
            for (int j=0 ; j <GRID_DIM; j++){
                for (int k=-1 ; k < 2; k++) {
                    for (int l = -1; l < 2; l++) {
                        if ((i + k) > -1 && (i + k) < GRID_DIM  && (j + l) > -1 && (j + l) < GRID_DIM )
                            if (k==0 && l==0){

                            } else {
                                vertices[i][j].getPhi_a().add((i+k) * GRID_DIM + j + l);
                            }
                    }
                }
            }
        }

        for (int i=0 ; i <GRID_DIM; i++){
            for (int j=0 ; j <GRID_DIM; j++){
                verticesTable[i*GRID_DIM+j] = vertices[i][j];
            }
        }
    }
    public Vertice[] getVerticesTable(){
        return verticesTable;
    }

    public int[] getHTable(){
        int[] hTable = new int[Constans.VERTICES_NUM];
        int i = 0;
        for (Vertice ver : verticesTable){
            hTable[i] = ver.getH();
            i++;
        }
        return  hTable;
    }
    public int[] getGTable(int k){
        int[] gTable = new int[Constans.VERTICES_NUM];
        int i = 0;
        for (Vertice ver : verticesTable){
            gTable[i] = ver.getG(k);
            i++;
        }
        return  gTable;
    }



    public void setVerticesTable(Vertice[] verticesTable) {
        this.verticesTable = verticesTable;
    }
}
