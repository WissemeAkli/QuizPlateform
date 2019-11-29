
import java.io.IOException;
import java.net.URL;
import java.text.Normalizer;
import java.time.LocalDate;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.fxml.Initializable;
import javafx.fxml.FXML;


public class AjouterApprenantController extends MenuController implements Initializable {

    @FXML
    private TextField nomText;
    @FXML
    private TextField prenomText;
    @FXML
    private TextField adrText;
    @FXML
    private ComboBox<String> formCombo;
    @FXML
    private DatePicker dateNText;




    public void ajouterApp (ActionEvent event) throws IOException {




        String nomm= nomText.getText();
        System.out.println(nomm);
        String prenom=prenomText.getText();
        System.out.println(prenom);
        String adr= adrText.getText();
        LocalDate date = dateNText.getValue();


        Formation format = new Formation();
        try {
            format = a.rechercheFormation2(formCombo.getValue(), getFormateur().getId());
            /// si la formation existe : ( pas d'exception)
            /// on va ajouter l'apprenant , ir : on va modifier le groupe d'apprenants qui est dans la formation
            GroupeApprenants groupe = format.getGroupe();
            CompteApprenant apprenant =  new CompteApprenant(nomText.getText(),prenomText.getText() ,dateNText.getValue(),adrText.getText(),formCombo.getValue(),getFormateur().getId());
            try {
                a.addApprenant(apprenant);
                groupe.ajouterApprenant(apprenant);
                showMessage("L'apprenant a été ajouté avec succès ! ");

                FXMLLoader fxmlLoader = new FXMLLoader(this.getClass().getResource("MenuApprenants.fxml"));
                Parent ajout = (Parent)fxmlLoader.load();


                Scene scene = new Scene(ajout, 609.0D, 400.0D);
                Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
                window.setScene(scene);
                window.show();
                // on va ajouter cet apprenant a la liste des apprenants de l'application
            } catch (CompteDejaExistant e) {
                showError(" Compte déja existant ! ");

            }

        } catch (FormationNonExistanteException e) {
        }




    }
    public void retour(ActionEvent event) throws IOException {
        Parent ajout = (Parent)FXMLLoader.load(this.getClass().getResource("menu.fxml"));
        Scene scene = new Scene(ajout, 609.0D, 400.0D);
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(scene);
        window.show();
    }

    public void initialize(URL url, ResourceBundle rb) {
        /**********************bedlii hna*/////////////////
        formCombo.setItems(FXCollections.observableArrayList(a.arrayStringFormation(a.getListeFormations())));
    }
}
