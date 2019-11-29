

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;


public class AfficherQuizController extends MenuController implements Initializable {

    public Quiz getQuiz() {
        return quiz;
    }

    public void setQuiz(Quiz quiz) {
        this.quiz = quiz;
    }

    private Quiz quiz;
    /*******************************/

    @FXML
    private VBox questionVbox;
    @FXML
    private Label nomQ;
    @FXML
    private Label nomF;
    @FXML
    private Label dateO;
    @FXML
    private Label dateF;
    private ArrayList<Label> liste = new ArrayList();
  //  Application a = new Application();
    private ArrayList<CheckBox> check = new ArrayList();
    private ArrayList<TextField> check2 = new ArrayList();

    public AfficherQuizController() {
    }

    public void retour(ActionEvent event) throws IOException {
        Parent ajout = (Parent)FXMLLoader.load(this.getClass().getResource("menuQuiz.fxml"));
        Scene scene = new Scene(ajout, 609.0D, 400.0D);
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(scene);
        window.show();
    }

    @FXML
    public void initialize(URL url, ResourceBundle rb) {
        /*
        String d4 = "2022-09-02";
        LocalDate date4 = LocalDate.parse(d4);
        String d5 = "2019-04-02";
        LocalDate date5 = LocalDate.parse(d5);
        String d6 = "2019-09-02";
        LocalDate date6 = LocalDate.parse(d6);
        this.nomQ.setText("quiz");
        this.nomF.setText("form");
        this.dateF.setText("dateF");
        this.dateO.setText("dateO");
        ArrayList<String> rc4 = new ArrayList();
        ArrayList<String> rf4 = new ArrayList();
        rc4.add("2");
        rc4.add("4/2");
        rf4.add("9999");
        rf4.add("0");
        QCM q4 = new QCM("1+1=?", rc4, rf4, "Maths");
        ArrayList<String> mots5 = new ArrayList();
        mots5.add("1");
        mots5.add("un");
        mots5.add("wahidoun");
        QO q5 = new QO(" 1 ! = ?", mots5, "Maths");
        ArrayList<String> rc6 = new ArrayList();
        rc6.add("2");
        rc6.add("4/2");
        QCU q6 = new QCU("sin 90 = ?", "1", rc6, "Maths");
        ArrayList<QCM> qq4 = new ArrayList();
        ArrayList<QCU> qq5 = new ArrayList();
        ArrayList<QO> qq6 = new ArrayList();
        qq4.add(q4);
        qq6.add(q5);
        qq5.add(q6);
        ArrayList<Notion> notions = new ArrayList();
        Notion n1 = new Notion("Maths");
        Notion n2 = new Notion("Philo");
        Notion n3 = new Notion("Arabe");
        notions.add(n1);
        notions.add(n2);
        notions.add(n3);
        Quiz q = new Quiz("QUIZ1", date4, date5, notions, qq4, qq6, qq5);*/

    }

/*
    private Label nomQ;
    @FXML
    private Label nomF;
    @FXML
    private Label dateO;
    @FXML
    private Label dateF;*/

    public void afficherQuiz()
    {
        this.nomQ.setText(quiz.getNom());
        this.nomF.setText(quiz.getFormation());
        this.dateO.setText(quiz.getDateO().toString());
        this.dateF.setText(quiz.getDateEX().toString());
        this.liste = this.a.quizLabel(quiz);
        this.questionVbox.getChildren().addAll(this.liste);
    }
}
