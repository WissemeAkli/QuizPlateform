

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class FirstPageControlller extends  Controller implements Initializable
{
    //private App a;



    public void initialize(URL url, ResourceBundle rb) {



    }


    /******************************/

    public FirstPageControlller()
    {

    }
    /******************************************************************************/




    public void CnxForm(ActionEvent event) throws IOException
    {
        FXMLLoader fxmlLoader = new FXMLLoader(this.getClass().getResource("CnxFormateur.fxml"));
        Parent ajout = (Parent)fxmlLoader.load();
        Scene scene = new Scene(ajout, 609.0D, 400.0D);
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(scene);
        window.show();

        /// on va faire passer  l'application au controller de la page suivante ( cnxFormateur)
       // CnxFormateurController controller= fxmlLoader.<CnxFormateurController>getController();
       // controller.setApp(getA());
    }

    public void CnxApprenant(ActionEvent event) throws IOException
    {

            FXMLLoader fxmlLoader = new FXMLLoader(this.getClass().getResource("CnxApprenant.fxml"));
            Parent ajout = (Parent)fxmlLoader.load();
            Scene scene = new Scene(ajout, 609.0D, 400.0D);
            Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
            window.setScene(scene);
            window.show();
    }


    public void InsFormateur (ActionEvent event) throws IOException
    {
            CompteFormateur f = a.creerCompteF();

            /// apres l'inscription

            FXMLLoader fxmlLoader = new FXMLLoader(this.getClass().getResource("Menu.fxml"));
            Parent ajout = (Parent)fxmlLoader.load();
            Scene scene = new Scene(ajout, 609.0D, 400.0D);
            Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
            window.setScene(scene);
            window.show();

            MenuController controller= fxmlLoader.<MenuController>getController();
            controller.setFormateur(f);

    }
}
