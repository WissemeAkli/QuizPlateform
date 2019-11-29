import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.text.Normalizer;
import java.util.ResourceBundle;
import java.util.Scanner;

public class MenuApprenantsController extends MenuController implements Initializable {
    public void initialize(URL url, ResourceBundle rb) {
    }

    public void retour(ActionEvent event) throws IOException {

        FXMLLoader fxmlLoader = new FXMLLoader(this.getClass().getResource("Menu.fxml"));
        Parent ajout = (Parent)fxmlLoader.load();



        Scene scene = new Scene(ajout, 609.0D, 400.0D);
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(scene);
        window.show();
    }

    public void AjouterApprenants(ActionEvent event) throws IOException {
        if ( getFormateur().getListeFormations().isEmpty())
        {
            showError("Vous devez avoir des formations pour enregistrer des apprenants!");
        }
        else
        {
            FXMLLoader fxmlLoader = new FXMLLoader(this.getClass().getResource("AjouterApprenant.fxml"));
            Parent ajout = (Parent)fxmlLoader.load();


            Scene scene = new Scene(ajout, 609.0D, 400.0D);
            Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
            window.setScene(scene);
            window.show();
        }

    }




    public void classerAppr(ActionEvent event) throws IOException {
        if ( getFormateur().getListeFormations().isEmpty())
        {
            showError("Vous devez avoir des formations pour enregistrer des apprenants!");
        }
        else
        {
            a.classApprenantsClass(getFormateur());
            FXMLLoader fxmlLoader = new FXMLLoader(this.getClass().getResource("MenuApprenants.fxml"));
            Parent ajout = (Parent)fxmlLoader.load();


            Scene scene = new Scene(ajout, 609.0D, 400.0D);
            Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
            window.setScene(scene);
            window.show();
        }

    }

    public void supprAppr(ActionEvent event) throws IOException {
        if ( getFormateur().getListeFormations().isEmpty())
        {
            showError("Vous devez avoir des formations pour enregistrer des apprenants!");
        }
        else
        {

            // on recupere le nom de la formaation
            System.out.println("Nom de la formation :");
            Scanner sc = new Scanner(System.in);

            String nomdeformation = sc.nextLine();
            try {
                Formation format = a.rechercheFormation2(nomdeformation, getFormateur().getId());
                /// si la formation existe : ( pas d'exception)
                /// on va supprimer l'apprenant , ir : on va modifier le groupe d'apprenants qui est dans la formation
                GroupeApprenants groupe = format.getGroupe();
                try {
                  String   identifiantApprenant = groupe.supprimerApprenant();
                    // si le compte existe et qu'il a ete supprimé du groupe
                    // on le supprime de l'appli aussi
                    a.supprimerApprenant(identifiantApprenant);
                } catch (CompteInexistantException e) {
                }


                /// on doit le supprimer de l'application aussi

            } catch (FormationNonExistanteException e) {
            }



            a.classApprenantsClass(getFormateur());
            FXMLLoader fxmlLoader = new FXMLLoader(this.getClass().getResource("MenuApprenants.fxml"));
            Parent ajout = (Parent)fxmlLoader.load();


            Scene scene = new Scene(ajout, 609.0D, 400.0D);
            Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
            window.setScene(scene);
            window.show();
        }

    }




    public void modifAppr(ActionEvent event) throws IOException {
        if ( getFormateur().getListeFormations().isEmpty())
        {
            showError("Vous devez avoir des formations pour enregistrer des apprenants!");
        }
        else
        {
            a.miseAjourApprenant(getFormateur());
            a.classApprenantsClass(getFormateur());
            FXMLLoader fxmlLoader = new FXMLLoader(this.getClass().getResource("MenuApprenants.fxml"));
            Parent ajout = (Parent)fxmlLoader.load();


            Scene scene = new Scene(ajout, 609.0D, 400.0D);
            Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
            window.setScene(scene);
            window.show();
        }

    }


}
