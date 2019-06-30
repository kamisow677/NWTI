package sample;

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.util.List;

import static sample.Constans.GRID_DIM;

public class View {

    public void show(Stage primaryStage, Grid gri, List<Label> pathsList){
        Grid grid = gri;
        List<Label> paths = pathsList;

        //ELEMENTS CREATION
        Line line = new Line(50, 50, 150, 50);
        line.setStroke(Color.BLUE);
        line.setStrokeWidth(5);

        //FILLING WINDOW
        Group root = new Group();

        root = drawGrid(grid,root,paths);
        primaryStage.setTitle("Pareto optimal shortest paths for unarmed vehicle");
        primaryStage.setScene(new Scene(root, 800, 875));
        primaryStage.show();
    }

    public void printTable(String description, int[] table){
        System.out.print(description+ " ");
        for (int t : table){
            System.out.print(" "+t);
        }
        System.out.println();

    }

    public static Group drawGrid(Grid grid, Group root, List<Label> pList){
        int i = 0;
        int j = 0;
        Rectangle rectangle;
        Text text;
        for (Vertice[] verticeRow : grid.vertices){
            for (Vertice vertice : verticeRow){
                rectangle = createRectangle(100*i,100*j,100,100,3);
                String string = new String(vertice.c(0)+" "+vertice.c(1)+" "+vertice.c(2));
                text = new Text ( 100*i +30,100*j + 50,string);
                root.getChildren().addAll(rectangle,text);
                j++;
            }
            i++;
            j=0;
        }
        Line line;
        for (Label p : pList){
            for (int k = 0; k <  p.getPath().listOfVertices.size()-1; k++ ){
                int a = p.getPath().listOfVertices.get(k)/GRID_DIM;
                int b = p.getPath().listOfVertices.get(k)%GRID_DIM;
                int c = p.getPath().listOfVertices.get(k+1)/GRID_DIM;
                int d = p.getPath().listOfVertices.get(k+1)%GRID_DIM;
                line = new Line(100*a + 50, 100*b +50, 100*c + 50, 100*d +50);
                root.getChildren().addAll(line);
            }
        }
        return root;
    }

    public static Rectangle createRectangle(double x, double y, double width, double height, double w) {
        final Rectangle rect = new Rectangle(x,y,width,height);
        rect.setStroke(Color.BLUE);
        rect.setStrokeWidth(w);
        rect.setFill(Color.TRANSPARENT);
        return rect;
    }
}
