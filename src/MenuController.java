//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//



import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class MenuController extends Controller implements Initializable {




    private static CompteFormateur formateur;  // l'id du formateur su'on va recuperer de la page de connexion
    // il est statique pour qu'on puisse l'utiliser dans les autres classes dérivées
    /**************************************************/

    public static CompteFormateur getFormateur() {
        return formateur;
    }

    public  void setFormateur(CompteFormateur formateur) {
        MenuController.formateur = formateur;
    }

/***************************************************/

    public MenuController() {

    }

    public void generer(ActionEvent event) throws IOException /// menu quiz
    {

        // on verifie si il a des formations
        if ( formateur.getListeFormations().isEmpty())
        {
            showError("Vous devez d'abord ajouter une formation ! ");
        }
        else
        {
            FXMLLoader fxmlLoader = new FXMLLoader(this.getClass().getResource("MenuQuiz.fxml"));
            Parent ajout = (Parent)fxmlLoader.load();


            Scene scene = new Scene(ajout, 609.0D, 400.0D);
            Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
            window.setScene(scene);
            window.show();
            System.out.println("id formateur : "+formateur.getId());


        }

    }

    public void quitter(ActionEvent event) throws IOException {
        Parent ajout = (Parent)FXMLLoader.load(this.getClass().getResource("CnxFormateur.fxml"));
        Scene scene = new Scene(ajout, 609.0D, 400.0D);
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(scene);
        window.show();
    }

    public void menuNotion(ActionEvent event) throws IOException {

        FXMLLoader fxmlLoader = new FXMLLoader(this.getClass().getResource("MenuNotions.fxml"));
        Parent ajout = (Parent)fxmlLoader.load();


        Scene scene = new Scene(ajout, 609.0D, 400.0D);
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(scene);
        window.show();


    }


    public void menuFormation(ActionEvent event) throws IOException {

        FXMLLoader fxmlLoader = new FXMLLoader(this.getClass().getResource("MenuFormation.fxml"));
        Parent ajout = (Parent)fxmlLoader.load();


        Scene scene = new Scene(ajout, 609.0D, 400.0D);
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(scene);
        window.show();

        MenuFormationController controller= fxmlLoader.<MenuFormationController>getController();
        controller.setFormateur(formateur);
    }




    public void menuApprenants(ActionEvent event) throws IOException {

        FXMLLoader fxmlLoader = new FXMLLoader(this.getClass().getResource("MenuApprenants.fxml"));
        Parent ajout = (Parent)fxmlLoader.load();


        Scene scene = new Scene(ajout, 609.0D, 400.0D);
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(scene);
        window.show();
        MenuApprenantsController controller= fxmlLoader.<MenuApprenantsController>getController();
        controller.setFormateur(formateur);
    }

    public void initialize(URL url, ResourceBundle rb) {
    }
}
