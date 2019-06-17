package sample;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.util.List;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{

        Model model = new Model();
        model.runCalculations();

        //ELEMENTS CREATION
        final Rectangle rect = createRectangle(0,0,100,100,3);
        final Rectangle rect2 = createRectangle(100,0,100,100,3);
        final Text text = new Text ( 0,50,"ZWYCIESTWO");
        final Text text2 = new Text (100,50,"ZWYCIESTWO");
        Line line = new Line(50, 50, 150, 50);
        line.setStroke(Color.BLUE);
        line.setStrokeWidth(5);

        //FILLING WINDOW
        //Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        Group root = new Group();
        //root.getChildren().addAll(rect,rect2,text,text2,line);

        root = drawGrid(model.grid,root,model.P);
        primaryStage.setTitle("Hello World");
       //root.getChildren().addAll(stack);
        primaryStage.setScene(new Scene(root, 800, 875));
        primaryStage.show();
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
                int a = p.getPath().listOfVertices.get(k)/4;
                int b = p.getPath().listOfVertices.get(k)%4;
                int c = p.getPath().listOfVertices.get(k+1)/4;
                int d = p.getPath().listOfVertices.get(k+1)%4;
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

    public static void main(String[] args) {
        launch(args);
    }
}
