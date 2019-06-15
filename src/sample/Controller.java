package sample;

public class Controller {
    Model model;
    public Controller(){
        model = new Model();
    }
    public void runModel(){
        model.runCalculations();
    }
}
