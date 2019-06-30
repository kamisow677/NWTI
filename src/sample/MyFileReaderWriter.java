package sample;

import javafx.scene.shape.Line;

import java.io.*;
import java.util.List;

import static sample.Constans.*;

public class MyFileReaderWriter {
    Vertice[][] vertices;
    String readFilePath;
    String saveFilePath;

    public MyFileReaderWriter(String readPath,String writePath) {
        this();
        this.readFilePath = readPath;
        this.saveFilePath = writePath;
    }

    public MyFileReaderWriter() {
        this.readFilePath = "data.txt";
        this.saveFilePath = "result.txt";

    }
    public void writePaths(List<Label> paths){
        FileWriter fileWriter = null;
        PrintWriter printWriter = null;
        try {
            fileWriter = new FileWriter(saveFilePath);
            printWriter = new PrintWriter(fileWriter);
            for (Label label : paths){
                String str = new String(label.getPath().listOfVertices.toString());
                printWriter.println(str);
            }
        } catch (IOException e) {
            e.printStackTrace();

        } finally {
            printWriter.close();
        }

    }

    public Vertice[][] read(){
        BufferedReader fileReader = null;

        try {
            fileReader = new BufferedReader(new FileReader(readFilePath));
            //Getting DIM
            String dim = fileReader.readLine();
            String[] split = dim.split(";");
            Constans.GRID_DIM =  Integer.parseInt(split[1]);
            this.vertices = new Vertice[GRID_DIM][GRID_DIM];
            Constans.VERTICES_NUM = GRID_DIM * GRID_DIM;

            dim = fileReader.readLine();
            split = dim.split(";");
            Constans.SOURCE =  Integer.parseInt(split[1]);

            dim = fileReader.readLine();
            split = dim.split(";");
            Constans.DESTINATION =  Integer.parseInt(split[1]);
            if (Constans.DESTINATION > Constans.VERTICES_NUM)
                throw new Exception("DESTINATION DOESNT EXISTS");

            if (Constans.SOURCE<0 || Constans.SOURCE>Constans.VERTICES_NUM)
                throw new Exception("BAD SOURCE NUMBER");

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
            System.out.println("There is no data.txt");
            e.printStackTrace();
        } catch (Exception e) {
            System.out.println(e.toString());
            System.out.println("OR NOT ENOUGH VERTICES !!!!");
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
