package sample;

import javafx.stage.Stage;

import java.util.List;

public class Controller {
    Model model;
    public Controller(){
        model = new Model();
    }
    public void runModel(){
        model.runCalculations();
    }
    public Grid getGrid(){
        return model.grid;
    }
    public List<Label> getPaths(){
        return model.P;
    }
    public void showView(Stage primaryStage){
        Grid grid = getGrid();
        List<Label> paths = getPaths();
        View view = new View();
        view.show(primaryStage,grid,paths);
    }
}
