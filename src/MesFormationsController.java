import javafx.scene.control.Label;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import javafx.fxml.Initializable;

public class MesFormationsController extends MenuController implements Initializable
{
    @FXML
    private Pane formPane;
    @FXML
    private TextField nomForm=new TextField();
    private ArrayList<Label> liste=new ArrayList<Label>();


    public void retour(ActionEvent event) throws IOException {
        Parent ajout = (Parent)FXMLLoader.load(this.getClass().getResource("menu.fxml"));
        Scene scene = new Scene(ajout, 609.0D, 400.0D);
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(scene);
        window.show();
    }
    public void ajoutform(ActionEvent event) throws IOException {

        Label l=new Label();
        String s= nomForm.getText();



        if(s.equals("") ){
            System.out.println("ya pas de nom");
        }
        else
        {
            l = a.newLabel(liste, s);

            formPane.getChildren().add(l);
        }
    }

    public void initialize(URL url, ResourceBundle rb) {

       liste = new ArrayList<>();
        liste=a.arrayLabel2(getFormateur().getListeFormations());
        formPane.getChildren().clear();

        formPane.getChildren().addAll(liste);
    }
}
