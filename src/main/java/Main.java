import view.ViewAbstract;
import view.Views;

public class Main {


    public static void main(String [] args) {

        ViewAbstract viewAbstract = Views.getView();
        viewAbstract.start();
    }
}
