
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;


public class CnxFormateurController  extends Controller implements Initializable {
    @FXML
    private TextField nom;
    @FXML
    private TextField mdps;
    private Label label;
    //private App a;

    public CnxFormateurController() {
    }

    public void inscription(ActionEvent event) throws IOException, CompteInexistantException, PwdErroneException, OperationAnnuleeException {
        System.out.println("mis a jour");
        String nomm = this.nom.getText();
        System.out.println(nomm);
        String mdpss = this.mdps.getText();
        System.out.println(mdpss);

        try {
            CompteFormateur formateur= this.a.cnxFormInterface(nomm, mdpss);
            showMessage("Authentification reussie");

            FXMLLoader fxmlLoader = new FXMLLoader(this.getClass().getResource("menu.fxml"));
            Parent ajout = (Parent)fxmlLoader.load();


            MenuController controller= fxmlLoader.<MenuController>getController();
            controller.setFormateur(formateur);



            Scene scene = new Scene(ajout, 609.0D, 400.0D);
            this.label = new Label("Rien pour le moment");
            Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
            window.setScene(scene);
            window.show();

            /// on va faire passer  l'application au controller de la page suivante ( cnxFormateur)
          //  MenuController controller= fxmlLoader.<MenuController>getController();
           // controller.setApp(a);
        } catch (CompteInexistantInterfaceException var7) { showError("Compte Inexistant !");
        } catch (PwdErroneInterfaceException var8) {
            showError("Mot de passe erroné !");
        }

    }




    public void quitter(ActionEvent event) throws IOException {
        Parent ajout = (Parent)FXMLLoader.load(this.getClass().getResource("FirstPage.fxml"));
        Scene scene = new Scene(ajout, 609.0D, 400.0D);
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(scene);
        window.show();
    }

    public void initialize(URL url, ResourceBundle rb) {

    }



}
