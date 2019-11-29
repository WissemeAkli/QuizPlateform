//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//



import java.io.IOException;
import java.net.URL;
import java.text.Normalizer;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;


public class GenererQuizController extends MenuController implements Initializable {



    @FXML
    private ComboBox<String> formation;

    @FXML
    private VBox notionVbox;
    @FXML
    private CheckBox ch = new CheckBox();
    @FXML
    private CheckBox ch1 = new CheckBox();
    @FXML
    private TextField nomQuiz;
    @FXML
    private DatePicker dateO;
    @FXML
    private DatePicker dateF;
    private ArrayList<CheckBox> check = new ArrayList();
    private ArrayList<TextField> check2 = new ArrayList();

    private ArrayList<Formation> listeForm;
    //private App a ;

    public GenererQuizController() {
    }

    public void retour(ActionEvent event) throws IOException
    {
        Parent ajout = (Parent)FXMLLoader.load(this.getClass().getResource("MenuQuiz.fxml"));
        Scene scene = new Scene(ajout, 609.0D, 400.0D);
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(scene);
        window.show();
    }

    @FXML
    public void valider(ActionEvent event) throws IOException
    {
        // apres la creation du quiz

        Boolean t= true;

        if(!verifierForm())
        {
            FXMLLoader fxmlLoader = new FXMLLoader(this.getClass().getResource("AfficherQuiz.fxml"));
            Parent ajout = (Parent)fxmlLoader.load();
             Quiz quiz= a.genererQuizInterface(getFormateur(),this.formation.getValue(),this.nomQuiz.getText(),dateO.getValue(),dateF.getValue(),this.a.notionSelect(this.check,this.check2));
            System.out.println(quiz.getNom());
            AfficherQuizController controller= fxmlLoader.<AfficherQuizController>getController();
            controller.setQuiz(quiz);
            controller.afficherQuiz();

            Scene scene = new Scene(ajout, 609.0D, 400.0D);
            Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
            window.setScene(scene);
            window.show();
        }



    }

    private boolean verifierForm()
    {
        /// on verifie si tous les champs ne sont pas vides :

        boolean erreur = true;

        boolean b = this.ch.isSelected();


        /*****************************/
        ArrayList<Notion> notionSelected = this.a.notionSelect(this.check,this.check2);
        String nomm = this.nomQuiz.getText();
        String np = (String)this.formation.getValue();
        LocalDate date = (LocalDate)this.dateO.getValue();
        LocalDate date2 = (LocalDate)this.dateF.getValue();

        if (!this.nomQuiz.getText().isEmpty() && !this.formation.getValue().isEmpty()&& !this.a.notionSelect(this.check,this.check2).isEmpty() && !this.dateO.getValue().equals(null) && !this.dateF.getValue().equals(null))
        {
            // pas de probleme tout est bien remplis
            /// on verifie les dates
            if ( date.isBefore(this.dateF.getValue()))
            {
                // on verifie si le nom deu quiz existe
                Formation f=a.rechercheFormationInterface(np,getFormateur().getId());

               if(! f.rechercheQuizInterface(nomm)) // si le nom n'existe pas deja
               {
                    // it's ok
                   if ( !(date.isAfter(f.getDatedeb())&& date2.isBefore(f.getDatefin())))
                   {
                       showError("La période d'ouverture du quiz doit etre comprise dans celle de la formation "+f.getNom()+" commencant le "+f.getDatedeb()+" et se terminant le "+f.getDatefin()+" !");
                   }
                   else
                   {
                       erreur = false;
                       Iterator<Notion> it = notionSelected.iterator();
                       while(it.hasNext())
                       {
                           System.out.println(it.next().getNom());
                       }
                   }

               }
               else
               {
                   showError("Nom de Quiz déja existant !");
               }

            }
            else
            {
                /// mettre en rouge
                /// faut verifier avec la date de la formation aussi
                showError("La date d'ouverture doit être avant la date de fermeture ! ");

            }
        }
        else
        {
            // show message "il faut tout remplir "
            showError("Tous les champs doivent être remplis !");

        }

        return erreur;

    }


    public void initialize(URL url, ResourceBundle rb) {
        System.out.println(" formateur "+ getFormateur().getId());

        this.formation.setItems(FXCollections.observableArrayList(this.a.arrayStringFormation(getFormateur().getListeFormations())));

        this.check = this.a.arrayNotion(this.a.getListeNotions());
        notionVbox.getChildren().removeAll();
        notionVbox.getChildren().addAll(check);

        check2=a.arrayNumber(a.getListeNotions());
        notionVbox.getChildren().addAll(check2);
    }

    public void reinitialisation ( )
    {
        this.formation.setItems(FXCollections.observableArrayList(this.a.arrayStringFormation(getFormateur().getListeFormations())));

        this.check = this.a.arrayNotion(this.a.getListeNotions());
        notionVbox.getChildren().removeAll();
        notionVbox.getChildren().addAll(check);

        check2=a.arrayNumber(a.getListeNotions());
        notionVbox.getChildren().addAll(check2);
    }
}
