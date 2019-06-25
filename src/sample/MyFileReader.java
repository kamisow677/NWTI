package sample;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import static sample.Constans.*;

public class MyFileReader {
    Vertice[][] vertices;
    String filePath;

    public MyFileReader(String filePath) {
        this();
        this.filePath = filePath;
    }

    public MyFileReader() {
        this.vertices = new Vertice[GRID_DIM][GRID_DIM];
        this.filePath = "data.txt";

    }

    public Vertice[][] read(){
        BufferedReader fileReader = null;

        try {
            fileReader = new BufferedReader(new FileReader(filePath));
            //Getting DIM
            String dim = fileReader.readLine();
            String[] split = dim.split(";");
            Constans.GRID_DIM =  Integer.parseInt(split[1]);

            dim = fileReader.readLine();
            split = dim.split(";");
            Constans.SOURCE =  Integer.parseInt(split[1]);

            dim = fileReader.readLine();
            split = dim.split(";");
            Constans.DESTINATION =  Integer.parseInt(split[1]);

            for (int i=0 ; i <GRID_DIM; i++){
                for (int j=0 ; j <GRID_DIM; j++){
                    String line = fileReader.readLine();
                    if (line.length()==0){
                        throw new Exception("Not enough vertices");
                    }
                    split = line.split(";");
                    vertices[i][j] = new Vertice(i * GRID_DIM + j, Integer.parseInt(split[TT]), Integer.parseInt(split[RL]) ,Integer.parseInt(split[JL]));
                }
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (fileReader != null) {
                    fileReader.close();
                }
            } catch (Exception ex){

            }
        }
        return vertices;
    }
}
