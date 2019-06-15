package sample;


import java.lang.reflect.Array;
import java.util.ArrayList;

import static sample.Constans.GRID_DIM;
import static sample.Constans.VERTICES_NUM;

public class Grid {
    Vertice[][] vertices = new Vertice[GRID_DIM][GRID_DIM];
    Vertice[] verticesTable = new Vertice[VERTICES_NUM];
    public Grid(){
        vertices[0][0] = new Vertice(0, 1, 1 ,4);
        vertices[0][1] = new Vertice(1, 1, 4 ,2);
        vertices[0][2] = new Vertice(2, 4, 2 ,7);
        vertices[0][3] = new Vertice(3, 6, 1 ,3);

        vertices[1][0] = new Vertice(4, 4, 1 ,3);
        vertices[1][1] = new Vertice(5, 4, 5 ,7);
        vertices[1][2] = new Vertice(6, 2, 2 ,3);
        vertices[1][3] = new Vertice(7, 2, 2 ,1);

        vertices[2][0] = new Vertice(8, 2, 3 ,4);
        vertices[2][1] = new Vertice(9, 1, 3 ,2);
        vertices[2][2] = new Vertice(10, 4, 5 ,2);
        vertices[2][3] = new Vertice(11, 1, 3 ,4);

        vertices[3][0] = new Vertice(12, 3, 6,2);
        vertices[3][1] = new Vertice(13, 2, 1 ,1);
        vertices[3][2] = new Vertice(14, 3, 4 ,3);
        vertices[3][3] = new Vertice(15, 1, 4 ,2);

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

    public void setVerticesTable(Vertice[] verticesTable) {
        this.verticesTable = verticesTable;
    }
}
