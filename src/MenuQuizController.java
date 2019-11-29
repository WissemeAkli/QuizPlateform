import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class MenuQuizController extends MenuController implements Initializable {
    public void initialize(URL url, ResourceBundle rb) {
    }






    public void generer(ActionEvent event) throws IOException
    {

        FXMLLoader fxmlLoader = new FXMLLoader(this.getClass().getResource("GenererQuiz.fxml"));
        Parent ajout = (Parent)fxmlLoader.load();
        GenererQuizController controller= fxmlLoader.<GenererQuizController>getController();
        controller.setFormateur(getFormateur());
        //controller.reinitialisation();




        Scene scene = new Scene(ajout, 609.0D, 400.0D);
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(scene);
        window.show();
    }


    public void mesQuiz(ActionEvent event) throws IOException
    {

        a.afficherQuizsFormation(getFormateur());
        FXMLLoader fxmlLoader = new FXMLLoader(this.getClass().getResource("MenuQuiz.fxml"));
        Parent ajout = (Parent)fxmlLoader.load();


        Scene scene = new Scene(ajout, 609.0D, 400.0D);
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(scene);
        window.show();
    }

    public void modifQuiz(ActionEvent event) throws IOException
    {

        a.modifierQuiz(getFormateur());
        FXMLLoader fxmlLoader = new FXMLLoader(this.getClass().getResource("MenuQuiz.fxml"));
        Parent ajout = (Parent)fxmlLoader.load();


        Scene scene = new Scene(ajout, 609.0D, 400.0D);
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(scene);
        window.show();
    }



    public void retour(ActionEvent event) throws IOException {

        FXMLLoader fxmlLoader = new FXMLLoader(this.getClass().getResource("Menu.fxml"));
        Parent ajout = (Parent)fxmlLoader.load();


        Scene scene = new Scene(ajout, 609.0D, 400.0D);
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(scene);
        window.show();
    }
}
