package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.awt.*;


public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{

        final Rectangle rect = createRectangle(0,0,100,100,3);
        Line line = new Line(50, 50, 150, 50);
        line.setStroke(Color.BLUE);
        line.setStrokeWidth(5);
        final Rectangle rect2 = createRectangle(100,0,100,100,3);
        final Text text = new Text ( 0,50,"ZWYCIESTWO");

        final Text text2 = new Text (100,50,"ZWYCIESTWO");

//        stack.setPrefSize(300, 250);
//        stack2.setPrefSize(300, 250);

        //Creating a Grid Pane
//        GridPane gridPane = new GridPane();
//
//        //Setting the Grid alignment
//        gridPane.setAlignment(Pos.CENTER);
//
//        //Arranging all the nodes in the grid
//        gridPane.add(stack, 0, 0);
//        gridPane.add(stack2, 1, 0);


        //Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        Group root = new Group();
        root.getChildren().addAll(rect,rect2,text,text2,line);
        primaryStage.setTitle("Hello World");
       //root.getChildren().addAll(stack);
        primaryStage.setScene(new Scene(root, 800, 875));
        primaryStage.show();
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