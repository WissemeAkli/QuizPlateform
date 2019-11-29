import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import jdk.nashorn.api.tree.TryTree;

import java.text.Format;
import java.text.Normalizer;
import java.time.LocalDate;
import java.util.*;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class MainTest extends Application{
    Stage window;

    @Override
    public void start(Stage primaryStage) throws Exception{
        window = primaryStage;

        Parent root = FXMLLoader.load(getClass().getResource("Welcome.fxml"));
        primaryStage.setTitle("ESI Quiz");
        primaryStage.setScene(new Scene(root, 609, 400));
        primaryStage.show(); }

    public static void main (String[] args)
    {
        launch(args);

       /* App a = new App();
        /******************************************************************************************

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


        //////////////////////////////////////////////////////////////////////////////////////////////////
        Scanner sc= new Scanner( System.in);
        Boolean menu= false;
        Boolean menu2= true;
        Boolean menu3=true;
        GroupeApprenants groupe = new GroupeApprenants();
        String nomdeformation;
        String identifiantApprenant;

        CompteFormateur formateur = new CompteFormateur();
        CompteApprenant apprenant = new CompteApprenant();

        System.out.println(25/2);
        int choix1;

        while ( menu)
        {
            System.out.println("**************************************************");
            System.out.println("****************** M E N U ***********************");
            System.out.println("1- Connexion Formateur");
            System.out.println("2- Connexion Apprenant");
            System.out.println("3- Inscription Formateur");
            System.out.println("4- Quitter");
            System.out.println("Choix :");


            int choix = sc.nextInt();
            switch ( choix) {
                case 1: // connexion formateur
                    try {
                        menu2=true;
                        formateur = a.connexionFormateur();
                    }
                    catch (OperationAnnuleeException e) { }
                    break;


                case 2:
                    try {
                        menu3=true;
                        apprenant = a.connexionApprenant();
                    } catch (OperationAnnuleeException e) {
                    }
                    break;


                case 3:/// Inscription
                    menu2=true;
                    formateur = a.creerCompteF(); // connexion directe a ce compte
                    break;


                case 4 :///
                    menu =false;
                    break;


            }
            if ( choix==1 || choix==3)
            {
                while (menu2)
                {

                    // apres la connexion
                    /// affichage du menu formateur
                    System.out.println("*********************************************************************");
                    System.out.println("*********************** MENU FORMATEUR ******************************");
                    System.out.println("1- Ajouter Formation");
                    System.out.println("2- Ajouter un quiz a une formation");
                    System.out.println("3- Ajouter un Apprenant à une Formation donnée ");
                    System.out.println("4- Supprimer un Apprenant d'une Formation donnée ");
                    System.out.println("5- Modifier les informations d'un apprenant d'une Formation donnée ");
                    System.out.println("6- Création d'une notion ");
                    System.out.println("7- Ajouter une question à une notion");
                    System.out.println("8- Supprimer des questions d'une notion");
                    System.out.println("9- Supprimer une notion");
                    System.out.println("10- Afficher Toutes les Notions ");
                    System.out.println("11- Afficher les Quiz d'une formation ");
                    System.out.println("12- Modifier un Quiz ");
                    System.out.println("13- Afficher la liste des apprenants d'une formation ");
                    System.out.println("14- Afficher la liste des apprenants d'une formation par classement");


                    System.out.println("15- Deconnexion (Retour au menu principal)");
                    System.out.println("Choix :");
                    ///// pour l'instant
                    choix1=sc.nextInt();
                    switch(choix1) {
                        case 1:// ajouter une formation
                            try{
                                a.ajouterFormation(formateur);
                            }
                            catch (AucuneNotionException e){}


                            break;
                        case 2: // ajouter un quiz à une formation
                            try{
                                 Quiz fqqq2=a.genererQuiz(formateur);


                            }
                            catch (AucuneNotionException e){}
                            catch (FormationNonExistanteException e2){}


                            break;


                        case 3:// ajouter un Apprenant a une formation donnée :
                            // on recupere le nom de la formaation
                            System.out.println("Nom de la formation :");
                            useless = sc.nextLine();
                            nomdeformation = sc.nextLine();

                            try {
                                format = a.rechercheFormation2(nomdeformation, formateur.getId());
                                /// si la formation existe : ( pas d'exception)
                                /// on va ajouter l'apprenant , ir : on va modifier le groupe d'apprenants qui est dans la formation
                                groupe = format.getGroupe();
                                apprenant = groupe.creerCompteApprenant(nomdeformation, formateur.getId());

                                try {
                                    a.addApprenant(apprenant);
                                    groupe.ajouterApprenant(apprenant);
                                    // on va ajouter cet apprenant a la liste des apprenants de l'application
                                } catch (CompteDejaExistant e) {
                                }

                            } catch (FormationNonExistanteException e) {
                            }


                            break;

                        case 4:// supprimer un apprenant d'une formation donnee
                            // on recupere le nom de la formaation
                            System.out.println("Nom de la formation :");
                            useless = sc.nextLine();
                            nomdeformation = sc.nextLine();
                            try {
                                format = a.rechercheFormation2(nomdeformation, formateur.getId());
                                /// si la formation existe : ( pas d'exception)
                                /// on va supprimer l'apprenant , ir : on va modifier le groupe d'apprenants qui est dans la formation
                                groupe = format.getGroupe();
                                try {
                                    identifiantApprenant = groupe.supprimerApprenant();
                                    // si le compte existe et qu'il a ete supprimé du groupe
                                    // on le supprime de l'appli aussi
                                    a.supprimerApprenant(identifiantApprenant);
                                } catch (CompteInexistantException e) {
                                }


                                /// on doit le supprimer de l'application aussi

                            } catch (FormationNonExistanteException e) {
                            }
                            break;


                        case 5:
                            /// mise a jour des infos d'un apprenant :
                            /// la modification se fait dans les groupes et dans l'application
                            // on recupere le nom de la formaation

                           a.miseAjourApprenant(formateur);
                            break;

                        case 6 : // creation d'une notion
                            // la liste de notions se trouve dans la classe application
                            try{
                                a.creerNotion();
                            }
                            catch ( NotionExistanteExeption e) {}
                            break;

                        case 7 :
                            // ajouter une question à une notion

                            try{
                                a.ajouterQuestionANotion();
                            }
                            catch ( NotionInExistanteException e) {}

                            break;
                        case 8 :
                            try{
                                a.supprimerQuestionsNotion();
                            }
                            catch ( NotionInExistanteException e) {}
                            break;
                        case 9:
                            // supprimer une notion
                            try{
                                a.supprNotion();
                            }
                            catch ( NotionInExistanteException e) {}
                            catch ( OperationAnnuleeException e) {}
                            break;
                        case 10 :
                            try{
                                a.afficherNotions();
                            }
                            catch (AucuneNotionException e){}

                            break;
                        case 11:
                            a.afficherQuizsFormation(formateur);
                            break;

                        case 12 :
                            a.modifierQuiz(formateur);
                            break;

                        case 13:
                            a.afficherApprenantsForm(formateur);
                            break;
                        case 14:
                            a.classApprenantsClass(formateur);
                            break;
                        case 15:
                            menu2 = false;
                            break;

                    }
                }
            }
            if (choix==2)
            {
               a.menuapprenant(apprenant);
            }
        }*/
    }
}
