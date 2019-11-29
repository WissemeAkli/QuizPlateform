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

public class MenuNotionsController extends MenuController implements Initializable {
    public void initialize(URL url, ResourceBundle rb) {
    }

    public void creerNotion(ActionEvent event) throws IOException {

        try{
            a.creerNotion();
        }
        catch ( NotionExistanteExeption e) {}

        FXMLLoader fxmlLoader = new FXMLLoader(this.getClass().getResource("MenuNotions.fxml"));
        Parent ajout = (Parent)fxmlLoader.load();


        Scene scene = new Scene(ajout, 609.0D, 400.0D);
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(scene);
        window.show();
    }

    public void ajouterQst(ActionEvent event) throws IOException {
        try{
            a.ajouterQuestionANotion();
        }
        catch ( NotionInExistanteException e) {}


        FXMLLoader fxmlLoader = new FXMLLoader(this.getClass().getResource("MenuNotions.fxml"));
        Parent ajout = (Parent)fxmlLoader.load();


        Scene scene = new Scene(ajout, 609.0D, 400.0D);
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(scene);
        window.show();
    }

    public void supprQst(ActionEvent event) throws IOException {
        try{
            a.supprimerQuestionsNotion();
        }
        catch ( NotionInExistanteException e) {}

        FXMLLoader fxmlLoader = new FXMLLoader(this.getClass().getResource("MenuNotions.fxml"));
        Parent ajout = (Parent)fxmlLoader.load();


        Scene scene = new Scene(ajout, 609.0D, 400.0D);
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(scene);
        window.show();
    }




    public void supprNot(ActionEvent event) throws IOException {
        try{
            a.supprNotion();
        }
        catch ( NotionInExistanteException e) {}
        catch ( OperationAnnuleeException e) {}

        FXMLLoader fxmlLoader = new FXMLLoader(this.getClass().getResource("MenuNotions.fxml"));
        Parent ajout = (Parent)fxmlLoader.load();


        Scene scene = new Scene(ajout, 609.0D, 400.0D);
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(scene);
        window.show();
    }




    public void afficherNot(ActionEvent event) throws IOException {
        try{
            a.afficherNotions();
        }
        catch (AucuneNotionException e){}

        FXMLLoader fxmlLoader = new FXMLLoader(this.getClass().getResource("MenuNotions.fxml"));
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
