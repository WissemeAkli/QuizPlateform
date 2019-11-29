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

public class MenuFormationController  extends MenuController implements Initializable {

    public void initialize(URL url, ResourceBundle rb) {
    }

    public void retour(ActionEvent event) throws IOException {

        FXMLLoader fxmlLoader = new FXMLLoader(this.getClass().getResource("MenuFormation.fxml"));
        Parent ajout = (Parent)fxmlLoader.load();


        Scene scene = new Scene(ajout, 609.0D, 400.0D);
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(scene);
        window.show();
    }

    public void Mesformations(ActionEvent event) throws IOException
    {
        if ( getFormateur().getListeFormations().isEmpty())
        {
            showError("Vous n'avez aucune formation !");
        }
        else
        {
            FXMLLoader fxmlLoader = new FXMLLoader(this.getClass().getResource("MesFormations.fxml"));
            Parent ajout = (Parent)fxmlLoader.load();


            Scene scene = new Scene(ajout, 609.0D, 400.0D);
            Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
            window.setScene(scene);
            window.show();
        }

    }

    public void Modifformation(ActionEvent event) throws IOException
    {
        if ( getFormateur().getListeFormations().isEmpty())
        {
            showError("Vous n'avez aucune formation !");
        }

    }

    public void Ajouterformation(ActionEvent event) throws IOException
    {

            try
            {
                a.ajouterFormation(getFormateur());
            }
            catch (Exception e){}

            FXMLLoader fxmlLoader = new FXMLLoader(this.getClass().getResource("MenuFormation.fxml"));
            Parent ajout = (Parent)fxmlLoader.load();


            Scene scene = new Scene(ajout, 609.0D, 400.0D);
            Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
            window.setScene(scene);
            window.show();


    }


}
