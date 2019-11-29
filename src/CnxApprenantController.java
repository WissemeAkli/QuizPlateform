
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


public class CnxApprenantController  extends Controller implements Initializable {
    @FXML
    private TextField nom;
    @FXML
    private TextField mdps;
    private Label label;
    //private App a;

    public CnxApprenantController() {
    }




    public void quitter(ActionEvent event) throws IOException {
        Parent ajout = (Parent)FXMLLoader.load(this.getClass().getResource("FirstPage.fxml"));
        Scene scene = new Scene(ajout, 609.0D, 400.0D);
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(scene);
        window.show();
    }

    public void cnx(ActionEvent event) throws IOException {

        System.out.println("mis a jour");
        String nomm = this.nom.getText();
        System.out.println(nomm);
        String mdpss = this.mdps.getText();
        System.out.println(mdpss);



            try{

                CompteApprenant f= a.cnxapprInterface(this.nom.getText(),this.mdps.getText());
                a.menuapprenant(f);
                showMessage("Authentification reussie");

            }
            catch( CompteInexistantInterfaceException e)
            {
                showError("Compte Inexistant ! ");

            }
            catch (PwdErroneInterfaceException e2)
            {
                showError("Mot de passe erroné ! ");
            }

        Parent ajout = (Parent)FXMLLoader.load(this.getClass().getResource("FirstPage.fxml"));
        Scene scene = new Scene(ajout, 609.0D, 400.0D);
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(scene);
        window.show();


    }




    public void initialize(URL url, ResourceBundle rb) {

    }



}
