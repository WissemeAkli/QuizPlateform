

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


public class WelcomeController extends  Controller implements Initializable
{
    //private App a;



    public void initialize(URL url, ResourceBundle rb) {

        App a = new App();
        /******************************************************************************************/

        /// ajout des notions
        ArrayList<String> rc1= new ArrayList<>();
        ArrayList<String> rf1= new ArrayList<>();
        rc1.add("oui");
        rc1.add("elle est ronde");
        rf1.add("non");
        rf1.add("NO");
        QCM q1= new QCM("La terre est ronde?",rc1,rf1,"Philo");

        ArrayList<String> rc2= new ArrayList<>();
        ArrayList<String> rf2= new ArrayList<>();
        rf2.add("oui");
        rf2.add("elle est plate");
        rc2.add("non");
        rc2.add("NO");
        QCM q2= new QCM("La terre est plate?",rc2,rf2,"Philo");

        ArrayList<String> mots1= new ArrayList<>();
        mots1.add("jcp");
        mots1.add("donno");

        QO q3= new QO("pourquoi le ciel est bleu?",mots1,"Philo");

        ArrayList<QCM> qq1 = new ArrayList<>();
        qq1.add(q1);
        qq1.add(q2);

        ArrayList<QCU> qq2 = new ArrayList<>();
        ArrayList<QO> qq3 = new ArrayList<>();
        qq3.add(q3);

        Notion n1= new Notion("Philo",qq1,qq2,qq3); // 2qcm et 1 qo

        //////////////////////////

        ArrayList<String> rc4= new ArrayList<>();
        ArrayList<String> rf4= new ArrayList<>();
        rc4.add("2");
        rc4.add("4/2");
        rf4.add("9999");
        rf4.add("0");
        QCM q4= new QCM("1+1=?",rc4,rf4,"Maths");


        ArrayList<String> mots5= new ArrayList<>();
        mots5.add("1");
        mots5.add("un");
        mots5.add("wahidoun");

        QO q5= new QO(" 1 ! = ?",mots5,"Maths");

        ArrayList<String> rc6= new ArrayList<>();

        rc6.add("2");
        rc6.add("4/2");

        QCU q6= new QCU("sin 90 = ?","1",rc6,"Maths");
        ArrayList<QCM> qq4 = new ArrayList<>();
        ArrayList<QCU> qq5 = new ArrayList<>();
        ArrayList<QO> qq6 = new ArrayList<>();

        qq4.add(q4);
        qq6.add(q5);
        qq5.add(q6);



        Notion n2= new Notion("Maths",qq4,qq5,qq6);

        ArrayList<Notion> notions = new ArrayList<Notion>();

        notions.add(n1);
        notions.add(n2);

        a.setListeNotions(notions);
        /// ajout d'un compte formateur
        String dd="1999-10-03";
        LocalDate date= LocalDate.parse(dd);
        String d1="2018-07-02";
        LocalDate date1= LocalDate.parse(d1);
        String d2="2022-07-02";
        LocalDate date2= LocalDate.parse(d2);
        String d3="2003-04-28";
        LocalDate date3= LocalDate.parse(d3);
        //******************** ajout d'une formation juste dans application
        //******************* le groupe d'apprenants :

        GroupeApprenants g = new GroupeApprenants();
        ArrayList<CompteApprenant> apprenants = new ArrayList<>();
        CompteApprenant c1= new CompteApprenant("akli","wisseme",date,"Benak","ESI","wiwi");
        CompteApprenant c2= new CompteApprenant("akli","rayane",date3,"Benak","ESI","wiwi");
        apprenants.add(c1);
        apprenants.add(c2);
        try
        {
            g.ajouterApprenant(c1);
            g.ajouterApprenant(c2);

        }
        catch (Exception e) {}

        try
        {
            a.addApprenant(c1);
            a.addApprenant(c2);
        }
        catch ( Exception e)
        {}



        Formation format = new Formation("ESI","Formation d'ingeniorat sur 5 ans",date1,date2,notions,g,"wiwi");

        a.addFormation(format);
        String useless; // but useful xd



        CompteFormateur form = new CompteFormateur("wiwi","123","akli", "wisseme", date, "benak");
        form.ajouterFormation(format);
        a.addFormateur(form);

        ///*********************** Ajout de quizs
        String d4="2022-09-02";
        LocalDate date4= LocalDate.parse(d4);
        String d5="2019-04-02";
        LocalDate date5= LocalDate.parse(d5);

        String d6="2019-09-02";
        LocalDate date6= LocalDate.parse(d6);
        Quiz q = new Quiz("QUIZ1",date4,date5,notions,qq1,qq3,qq2);
        Quiz qqq2 = new Quiz("QUIZ2",date4,date6,notions,qq1,qq3,qq2);
        format.addQuiz(q);
        format.addQuiz(qqq2);

        setApp(a);

    }


    /******************************/


    /******************************************************************************/






    public void Acceder(ActionEvent event) throws IOException
    {

        FXMLLoader fxmlLoader = new FXMLLoader(this.getClass().getResource("FirstPage.fxml"));
        Parent ajout = (Parent)fxmlLoader.load();
        Scene scene = new Scene(ajout, 609.0D, 400.0D);
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(scene);
        window.show();
    }



}
