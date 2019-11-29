import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import javax.sound.midi.SysexMessage;
import java.text.Format;
import java.text.Normalizer;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class App {

    //** ATTRIBUTS***************************************/
    private int n ;

    public int getN() {
        return n;
    }

    public void setN(int n) {
        this.n = n;
    }

    public ArrayList<Formation> getListeFormations() {
        return listeFormations;
    }

    public void setListeFormations(ArrayList<Formation> listeFormations) {
        this.listeFormations = listeFormations;
    }

    public ArrayList<CompteFormateur> getListeFormateurs() {
        return listeFormateurs;
    }

    public void setListeFormateurs(ArrayList<CompteFormateur> listeFormateurs) {
        this.listeFormateurs = listeFormateurs;
    }

    public void setListeApprenants(ArrayList<CompteApprenant> listeApprenants) {
        this.listeApprenants = listeApprenants;
    }

    public ArrayList<Notion> getListeNotions() {
        return listeNotions;
    }

    private ArrayList<Formation> listeFormations;
    private ArrayList<CompteFormateur> listeFormateurs;
    private ArrayList<CompteApprenant> listeApprenants;
    private ArrayList<Notion> listeNotions;


    //** CONSTRUCTEUR(S)**********************************/
    public App()
    {
        this.listeFormateurs= new ArrayList<CompteFormateur>();
        this.listeApprenants= new ArrayList<CompteApprenant>();
        this.listeFormations = new ArrayList<Formation>();
        this.listeNotions = new ArrayList<Notion>();
    }

    /** GETTERS SETTERS**/
    public void setListeNotions(ArrayList<Notion> listeNotions) {
        this.listeNotions = listeNotions;
    }
    public void addFormateur( CompteFormateur f)
    {
        this.listeFormateurs.add(f);
    }

    public ArrayList<CompteApprenant> getListeApprenants() {
        return listeApprenants;
    }

    public void addFormation ( Formation f)
    {
        this.listeFormations.add(f);
    }

    //** METHODES ***************************************/


    public CompteFormateur creerCompteF()
    {
        CompteFormateur f = new CompteFormateur();
        Scanner sc= new Scanner( System.in);
        System.out.println("Veuillez entrer les informations suivantes \n");

        System.out.println("Nom :");
        String nom = sc.nextLine();

        System.out.println("Prenom :");
        String prenom = sc.nextLine();
        System.out.println("Adresse :");
        String adresse = sc.nextLine();

        System.out.println("Date de naissance (AAAA-MM-JJ) :");

        String da = sc.nextLine();
        LocalDate d= LocalDate.parse(da);
        /*String str = sc.nextLine();
        Date date= new Date();
        if(str.matches("[0-9]{2}/[0-9]{2}/[0-9]{4}")){
            SimpleDateFormat d = new SimpleDateFormat("MM-dd-yyyy");
             date = d.parse();
        }
        else {
            System.out.println("Erreur format");
        }
        System.out.println(date);*/
        String id = new String();
        boolean idExiste= true;
        while (idExiste)
        {
            System.out.println("Identifiant :");
            id = sc.nextLine();
            idExiste=this.idFormExistant(id);
            if (idExiste)
            {
                System.out.println(" Id Existant ! Veuillez entrer un autre SVP !");
            }
        }
        System.out.println("Mot de passe :");
        String pwd = sc.nextLine();

        // creation du compte
        f= new CompteFormateur(id,pwd,nom,prenom,d ,adresse);
        // ajout à la liste des formateurs
        this.listeFormateurs.add(f);

        return f;
    }
    /***************/
    public boolean idFormExistant(String id)
    {
        boolean existe= false;
        CompteFormateur f ;

        Iterator<CompteFormateur> it = listeFormateurs.iterator();
        while (it.hasNext())
        {
            f=it.next();

            if ( f.getId().equals(id))
            {

                existe= true;
            }
        }
        return existe;
    }
    /******************/
    public boolean idAppExistant(String id)
    {
        boolean ex= false;
        CompteApprenant f ;
        Iterator<CompteApprenant> ite = listeApprenants.iterator();
        while (ite.hasNext())
        {
            f=ite.next();

            if ( f.getId().equals(id))
            {
                ex= true;
            }
        }
        return ex;
    }

    /****************/
    public CompteFormateur connexionFormateur() throws OperationAnnuleeException
    {
        boolean fail = true;
        boolean annul = false;
        String id,pwd;
        CompteFormateur f= new CompteFormateur();

        Scanner sc= new Scanner( System.in);
        while (fail)
        {
            try{
                System.out.println("Veuillez entrer les informations suivantes \n");

                System.out.println("Identifiant :");
                id = sc.nextLine();
                System.out.println("Mot de passe :");
                pwd = sc.nextLine();
                f= this.cnxForm(id,pwd);
                fail = false;
            }
            catch( CompteInexistantException e)
            {

            }
            catch (PwdErroneException e2)
            {

            }
        }
        if ( annul) throw new OperationAnnuleeException();

        return f;
    }
    /*****************/

    public CompteFormateur cnxForm(String id, String pwd) throws CompteInexistantException, PwdErroneException
    {
        boolean ex= false;

        CompteFormateur f = new CompteFormateur();
        CompteFormateur f2 = new CompteFormateur() ;
        String mdp = new String() ;
        Iterator<CompteFormateur> ite = listeFormateurs.iterator();
        while (ite.hasNext())
        {
            f=ite.next();

            if ( f.getId().equals(id))
            {
                ex= true;
                mdp=f.getPwd();
                f2=f;

            }
        }
        if (ex==true)
        {
            if(pwd.equals(mdp))
            {
                return f2;
            }
            else throw new PwdErroneException();
        }
        else throw new CompteInexistantException();
    }

    /*******************************************/

    public CompteApprenant connexionApprenant() throws OperationAnnuleeException
    {
        boolean fail = true;
        boolean annul = false;
        String id,pwd;
        CompteApprenant f= new CompteApprenant();

        Scanner sc= new Scanner( System.in);
        while (fail)
        {
            try{
                System.out.println("Veuillez entrer les informations suivantes \n");

                System.out.println("Identifiant :");
                id = sc.nextLine();
                System.out.println("Mot de passe :");
                System.out.println("Mot de passe :");
                pwd = sc.nextLine();
                f= this.cnxappr(id,pwd);
                fail = false;
            }
            catch( CompteInexistantException e)
            {

            }
            catch (PwdErroneException e2)
            {

            }
        }
        if ( annul) throw new OperationAnnuleeException();

        return f;
    }
    /*****************/

    public CompteApprenant cnxappr(String id, String pwd) throws CompteInexistantException, PwdErroneException
    {
        boolean ex= false;

        CompteApprenant f = new CompteApprenant();
        CompteApprenant f2 = new CompteApprenant() ;
        String mdp = new String();
        Iterator<CompteApprenant> ite = listeApprenants.iterator();
        while (ite.hasNext())
        {
            f=ite.next();

            if ( f.getId().equals(id))
            {
                ex= true;
                mdp=f.getPwd();
                f2=f;

            }
        }
        if (ex==true)
        {
            if(pwd.equals(mdp))
            {
                return f2;
            }
            else throw new PwdErroneException();
        }
        else throw new CompteInexistantException();
    }


    //********************************************************************************************/
    //***** POUR AJOUTER UNE NOUVELLE FORMATION ********************************/
    public void ajouterFormation( CompteFormateur formateur) throws AucuneNotionException
    {
        Formation f;
        ArrayList<Notion> not = new ArrayList<Notion>();
        int choix[] = new int [100];
        int i=0;
        int var=0;
        Scanner sc= new Scanner( System.in);
        System.out.println("Veuillez entrer les informations relatives à la formation :");
        System.out.println("Nom :");
        String nom = sc.nextLine();
        Boolean existe = true;
        while ( existe)
        {   try
        {
            rechercheFormation(nom);
            existe= false;
        }
        catch (NomDeFormationExistantException e)
        {
            System.out.println("Nom :");
            nom = sc.nextLine();
        }
        }
        System.out.println(" Descriptif :");
        String descriptif = sc.nextLine();


        /// conversion des dates ......


        boolean erreur =true;
        LocalDate datedebut=  LocalDate.now();
        LocalDate datefin= LocalDate.now();
        while (erreur)
        {
            System.out.println("Date d'ouverture (AAAA-MM-JJ):");
            String da = sc.nextLine();
            datedebut= LocalDate.parse(da);
            System.out.println("Date d'expiration (AAAA-MM-JJ):");
            String d22 = sc.nextLine();
            datefin= LocalDate.parse(d22);
            if(datedebut.isBefore(datefin))
            {
                erreur =false;
            }
            else
            {
                System.out.println("Erreur ! la date d'expiration doit etre apres la date d'ouverture ! \nVeuillez réessayer svp ");
            }
        }

        /// on devra selectionner la liste des notions
        /// on affichera alors la liste des notions ( liste not)
        System.out.println(" Veuillez taper les numeros des notions qu'abordera cette formation ( les taper un par un et terminer avec 9999)");
        System.out.println("                               (Selectionner au moins un) ");
        try
        {
            this.afficherNomsNotions();
            while (var!=9999 || i==0 )
            {
                var=sc.nextInt();
                if ( var !=9999 && var<=this.listeNotions.size())
                {
                    not.add(this.listeNotions.get(var-1));
                    this.listeNotions.get(var-1).afficherNomNotion();
                }
                i++;
            }

            //// il faut creer le groupe d'apprenants:
            System.out.println(" Entrez le nombre d'apprenants de cette formation ");
            int nb= sc.nextInt();
            GroupeApprenants groupe = new GroupeApprenants();
            CompteApprenant app= new CompteApprenant();
            // on doit ajouter les comptes apprenants dans application
            // ils seront tous dans groupe
            for (i=0; i<nb; i++)
            {
                app =groupe.creerCompteApprenant(nom,formateur.getId());
                try
                {
                    groupe.ajouterApprenant(app);
                    // on l'ajoute dans application // si il n'existe pas ( pas d'exception)
                    this.listeApprenants.add(app); //----> le contraire ! faut verifier da,s l'appli et pas dans le groupe
                }
                catch ( CompteDejaExistant e){}
            }
            /// normalement tous les comptes sont crees et e groupe est pret
            /// enfin on peut ajouter la formation
            f= new Formation(nom,descriptif,datedebut,datefin,not,groupe,formateur.getId());
            /// on l'ajoute chez le formateur
            formateur.ajouterFormation(f);
            /// on l'ajoute dans la liiste de formations deans Appliation
            this.listeFormations.add(f);
        }
        catch ( AucuneNotionException e)
        {
            // pas de ntions donc pas de formation !
            throw new AucuneNotionException();
        }





    }

    ///////////////////////////////////////
    public void afficherNotions() throws AucuneNotionException
    {
        Iterator<Notion> it= listeNotions.iterator();
        int i=0;
        Notion n;
        while (it.hasNext())
        {
            i++;
            System.out.println("NOTION N ° "+i);
            System.out.println("------------");
            n=it.next();
            n.afficherNomNotion();
            n.afficherQsts();

        }
        if ( i==0)
        { throw new AucuneNotionException();}

    }


    public void afficherNomsNotions() throws AucuneNotionException
    {
        Iterator<Notion> it= listeNotions.iterator();
        int i=0;
        Notion n;
        while (it.hasNext())
        {
            i++;
            System.out.println("NOTION N ° "+i);
            System.out.println("------------");
            n=it.next();
            n.afficherNomNotion();


        }
        if ( i==0)
        { throw new AucuneNotionException();}

    }



    public void rechercheFormation( String nom) throws NomDeFormationExistantException
    {

        Iterator<Formation> it = this.listeFormations.iterator();
        Formation f;
        while (it.hasNext())
        {
            f= it.next();
            if (f.getNom().equals(nom))
            {
                throw new NomDeFormationExistantException();
            }
        }

    }

    public Formation rechercheFormation2( String nom, String idFormateur) throws FormationNonExistanteException
    {
        Boolean t= false;
        Iterator<Formation> it = this.listeFormations.iterator();
        Formation f;
        Formation f1= new Formation();
        while (it.hasNext())
        {
            f= it.next();
            if (f.getNom().equals(nom) && (f.getIdFormateur()).equals(idFormateur))
            {
                t= true;
                f1=f;
            }
        }
        if ( !t)
        {
            throw new FormationNonExistanteException();
        }
        return f1;
    }


    public void addApprenant( CompteApprenant c) throws CompteDejaExistant
    {
        if (this.rechercheApprenant(c)){ throw new CompteDejaExistant();}
        else {this.listeApprenants.add(c);}

    }


    public boolean rechercheApprenant( CompteApprenant c)
    {
        Iterator<CompteApprenant> it = this.listeApprenants.iterator();
        boolean existe= false;
        CompteApprenant a;
        while ( it.hasNext())
        {
            a=it.next();
            if (a.equals(c))
            {
                existe=true;
            }
        }
        return existe;
    }



    public int rechercheIndiceApprenant( String c)
    {
        Iterator<CompteApprenant> it = this.listeApprenants.iterator();
        boolean existe= false;
        int indice =-2;
        int i=0;
        CompteApprenant a;
        while ( it.hasNext())
        {
            a=it.next();
            if (a.getId().equals(c))
            {
                existe=true;
                indice= i;
            }
            i++;
        }
        return indice;
    }

    public void supprimerApprenant (String apprenant)
    {
        int i=rechercheIndiceApprenant(apprenant);
        if (i!=-2)
        {
            this.listeApprenants.remove(i);
        }

    }





    public void modifierApprenant( CompteApprenant ancien, CompteApprenant nouveau) throws InformationsExistantesException
    {
        /// on verifie si les nouvelles infos de coincident pas avec un autre Compte
        int i =rechercheIndiceApprenant(ancien.getId());
        if ( i!=-2)
        {
            throw new InformationsExistantesException();
        }
        else
        {
            this.listeApprenants.remove(i);
            this.listeApprenants.add(nouveau);
        }
    }

    public int rechercheApprenant(String id)
    {
        Iterator<CompteApprenant> it= this.listeApprenants.iterator();
        int i=0;
        int indice=-2;
        CompteApprenant c;

        while(it.hasNext())
        {
            c= it.next();
            if ( c.getId().equals(id))
            {
                indice=i;
            }
        }
        return indice;
    }

    public boolean notionExiste(String notion)
    {
        boolean e= false;
        Iterator<Notion> it = this.listeNotions.iterator();
        Notion n;
        while ( it.hasNext()&& !e)
        {
            n=it.next();
            if (n.getNom().equals(notion))
            {
                e=true;
            }
        }
        return e;
    }

    public Notion rechercheNotion (String notion)
    {
        boolean e= false;
        Iterator<Notion> it = this.listeNotions.iterator();
        Notion n;
        Notion not = new Notion();
        while ( it.hasNext()&& !e)
        {
            n=it.next();
            if (n.getNom().equals(notion))
            {
                e=true;
                not=n;
            }
        }
        return not;
    }

    public void creerNotion() throws NotionExistanteExeption
    {
        String nom;
        int qcm=0,qo=0,qcu=0;
        System.out.println("Entrez le nom de cette notion : ");
        Scanner sc= new Scanner( System.in);
        nom= sc.nextLine();
        /// on doit verifier ce nom de ntion existe dans la liste
        if (notionExiste(nom))
        {
            throw new NotionExistanteExeption();
        }
        else
        {
            Notion notion = new Notion(nom);
            while(qcm+qcu+qo ==0)
            {
                // on continue en ajoutant les questions :
                System.out.println("Nous allons ajouter des questions a cette notion ( vous devez en ajouter au moins une)");
                System.out.println("Nombre de questions a choix multiple :");
                qcm= sc.nextInt();
                System.out.println("Nombre de questions a choix unique :");
                qcu= sc.nextInt();
                System.out.println("Nombre de questions ouvertes :");
                qo= sc.nextInt();
                if ( qcm+qcu+qo ==0)
                {
                    System.out.println("Vous devez ajouter au moins une question !");

                }
                else
                {
                    /// on ajoute les questions
                    if ( qcm>0)
                    {
                        System.out.println(" Les Questions à choix multiples : ");
                    }
                    while( qcm>0)
                    {
                        notion.ajouterQCM();
                        qcm--;
                    }

                    if ( qcu>0)
                    {
                        System.out.println(" Les Questions à choix unique : ");
                    }
                    while( qcu>0)
                    {
                        notion.ajouterQCU();
                        qcu--;
                    }


                    if ( qo>0)
                    {
                        System.out.println(" Les Questions ouvertes : ");
                    }
                    while( qo>0)
                    {
                        notion.ajouterQO();
                        qo--;
                    }

                    qcm=12;// juste pour arreter la boucle
                }

            }
            // on l'ajoute dans la liste des notions
            this.listeNotions.add(notion);

        }
    }

    public void ajouterQuestionANotion() throws NotionInExistanteException
    {
        System.out.println("Entrez le nom de la notion :");
        Scanner sc= new Scanner(System.in);
        String nom = sc.nextLine();
        Notion n;
        // recherche
        if ( notionExiste(nom))
        {
            n=rechercheNotion(nom);
            System.out.println("Voulez vous ajouter ..? \n 1- Une Question à choix multiples \n 2- Une Question a choix uniques \n 3- Une Question ouverte \n Votre choix : ..");
            int choix = sc.nextInt();
            switch ( choix)
            {
                case 1 ://qcm
                    n.ajouterQCM();
                    break;
                case 2:
                    n.ajouterQCU();
                    break;
                case 3 :
                    n.ajouterQO();
                    break;
            }
        }

        else
        {
            throw new NotionInExistanteException();
        }
    }





    public void supprimerQuestionsNotion() throws NotionInExistanteException
    {
        System.out.println("Entrez le nom de la notion :");
        Scanner sc= new Scanner(System.in);
        String nom = sc.nextLine();
        Notion n;

        // recherche
        if ( notionExiste(nom))
        {
            n=rechercheNotion(nom);
            // on affiche la liste des questions
            n.afficherQsts();
            /// on selectionne celles qu'on beut supprimer
            /// les QCM  a supprimer
            n.supprQCMs();
            n.supprQCUs();
            n.supprQOs();
        }

        else
        {
            throw new NotionInExistanteException();
        }
    }
    /***********************************************************/

    public void supprNotion() throws  NotionInExistanteException, OperationAnnuleeException
    {
        System.out.println("Entrez le nom de la notion :");
        Scanner sc= new Scanner(System.in);
        String nom = sc.nextLine();


        boolean e= false;
        Iterator<Notion> it = this.listeNotions.iterator();
        Notion not, v;
        while ( it.hasNext()&& !e)
        {
            not=it.next();
            if (not.getNom().equals(nom))
            {
                e=true;
                System.out.println("Etes vous sur de vouloir supprimer la notion : "+nom +" ?  ( taper 1 pour oui ou 2 pour non)");
                int choix = sc.nextInt();
                if(choix==1)
                {
                    it.remove();
                }
                else
                {
                    throw new OperationAnnuleeException();
                }
            }
        }
        if ( e==false)
        {
            throw new NotionInExistanteException();
        }
    }


    /**************************************************************************************************/
    /********CREATION DE QUIZ *********************************************************************/
    public Quiz genererQuiz(CompteFormateur f) throws FormationNonExistanteException, AucuneNotionException
    {
        System.out.println("****** AJOUT D'UN QUIZ : ******");
        System.out.println("-------------------------------");
        System.out.println("Entrez le nom de la formation :");
        Scanner sc= new Scanner(System.in);
        String nomForm = sc.nextLine();
        Quiz q = new Quiz();
        boolean existe=true;
        boolean erreur=true;
        try
        {
            Formation form=rechercheFormation2(nomForm,f.getId());
            // si la formation existe

            System.out.println("------------");
            System.out.println("Entrez le nom du Quiz :");
            String nomQuiz = new String();
            // il faut que le nom du quiz soit unique !
            // on verifie si il existe deja dans la formation
            while(existe)
            {
                try{
                    nomQuiz = sc.nextLine();
                    existe=form.quizExiste(nomQuiz);
                }
                catch (NomQuizExistantException e){}
            }
            LocalDate d=  LocalDate.now();
            LocalDate d2= LocalDate.now();
            while (erreur)
            {
                System.out.println("Date d'ouverture (AAAA-MM-JJ):");
                String da = sc.nextLine();
                d= LocalDate.parse(da);
                System.out.println("Date d'expiration (AAAA-MM-JJ):");
                String d22 = sc.nextLine();
                d2= LocalDate.parse(d22);
                if(d.isBefore(d2))
                {
                    erreur =false;
                }
                else
                {
                    System.out.println("Erreur ! la date d'expiration doit etre apres la date d'ouverture ! \nVeuillez réessayer svp ");
                }
            }
            /// nous allons selectionner les notions que le quiz couvre
            /// ces notions doivent etre tirées de la formation
            try
            {
                ArrayList<Notion> notions = form.selectNotions();
                // on peut creer le quiz
                q= new Quiz(nomQuiz,d,d2,notions);
                q.setFormation(nomForm);
                // le nombre de questions par notio:
                Iterator<Notion> it = notions.iterator();
                Notion not;
                int var =1;
                int nb=0;
                System.out.println("------------");
                System.out.println("*** Veuillez entrer le nombre de questions par notion : ");
                System.out.println("NB : si vous entrez un nombre trop grand, le nombre maximal de questions pour cette notion sera pris ");
                while(it.hasNext())
                {
                    not= it.next();
                    System.out.println("NOTION N ° "+var);
                    System.out.println("------------");
                    not.afficherNomNotion();
                    System.out.println("Nombre de Questions : ");
                    nb=sc.nextInt();
                    // on ajoute les questions
                    q.ajouterQst(nb,not);
                    var++;
                }
                form.addQuiz(q);
            }
            catch (AucuneNotionException e){
                throw new AucuneNotionException();
            }
        }
        catch ( FormationNonExistanteException e){
            throw new FormationNonExistanteException();
        }
        return q;
    }









    public void afficherQuizsFormation(CompteFormateur f)
    {
        Scanner sc= new Scanner(System.in);
        System.out.println("Nom formation : ");
        String nom = sc.nextLine();
        try
        {
            Formation form=f.rechercheFormation(nom);
            try
            {
                form.afficherQuizs();
            }
            catch(AucunQuizFormationException e){ }
        }
        catch ( FormationNonExistanteException e){ }
    }

    /********************************************************************************************/
    public void miseAjourApprenant(CompteFormateur formateur)
    {
        System.out.println("Nom de la formation :");
        Scanner sc = new Scanner( System.in);
        GroupeApprenants groupe;
        Formation format;
        CompteApprenant apprenant;
        String nomdeformation = sc.nextLine();
        try {
            format = this.rechercheFormation2(nomdeformation, formateur.getId());
            /// si la formation existe : ( pas d'exception)
            /// on va supprimer l'apprenant , ir : on va modifier le groupe d'apprenants qui est dans la formation
            groupe = format.getGroupe();
            try {

                System.out.println("Id de l'apprenant : ");
                String id = sc.nextLine();
                apprenant = groupe.modifierApprenant(id);
                /// apprenant contient les nouvelles infos
                // on verifie si il n'existe pas
                /// on modifie dans l'appli :
                CompteApprenant test = this.getListeApprenants().get(this.rechercheApprenant(id));
                try {
                    this.modifierApprenant(test, apprenant);
                    groupe.remplacerApprenant(test, apprenant);
                }
                catch (InformationsExistantesException e) { }

            } catch (CompteInexistantException e) {
            }
            /// on doit le supprimer de l'application aussi

        } catch (FormationNonExistanteException e) {
        }
    }


    /****************************************************/
    public void modifierQuiz(CompteFormateur f)
    {
        boolean menu=true;
        Scanner sc= new Scanner(System.in);
        System.out.println("Nom formation : ");
        String nom = sc.nextLine();
        try
        {
            Formation form=f.rechercheFormation(nom);
            try
            {


                form.modifierQuiz();


            }
            catch(AucunQuizFormationException e){ }
            catch(QuizDejaOuvertException e){}
        }
        catch ( FormationNonExistanteException e){ }

    }



    /*************************************************************************************/

    public void gestionCompte(CompteApprenant a)
    {
        Boolean menu = true;
        Scanner sc = new Scanner(System.in);
        while ( menu)
        {
            System.out.println("1.Modifier mot de passe");
            System.out.println("2.Afficher Informations");
            System.out.println("3.Modifier Adresse");
            System.out.println("4.Modifier Nom");
            System.out.println("5.Modifier Prenom");
            System.out.println("6.Retour au menu");
            System.out.println("Choix :");
            int choix = sc.nextInt();
            switch (choix)
            {
                case 1 :
                    try
                    {
                        a.modifierPWD();
                    }
                    catch ( Exception e){}

                    break;
                case 2:
                    a.afficherApprenant();
                    break;
                case 3:
                    try
                    {
                        a.modifierAdresse();
                    }
                    catch ( Exception e){}
                    break;
                case 4:
                    try
                    {
                        a.modifierNom();
                    }
                    catch ( Exception e){}

                    break;
                case 5 :
                    try
                    {
                        a.modifierPrenom();
                    }
                    catch ( Exception e){}

                    break;
                case 6 :
                    menu=false;
                    break;
            }

        }
    }







    /**********************************************************************/
    public void afficherQuizs(String formation,String formateur)
    {
        // on affiche les quiz ouverts et expirés de cette formation
        try
        {
            Formation form= rechercheFormation2(formation,formateur);
            form.afficherQuizs();
        }
        catch( Exception e){}
    }






    /****************************************************************************************************************/
    /************** REPONDRE A UN QUIZ ********************************/

    public void faireUnQuiz( CompteApprenant c)
    {
        Scanner sc = new Scanner(System.in);
        int var=0;
        Boolean a=false;
        Quiz q= new Quiz();
        ReponseQuiz rq= new ReponseQuiz();
        try
        {
            Formation form= rechercheFormation2(c.getFormation(),c.getFormateur());
            form.afficherQuizs();
            System.out.println("blabla");
            System.out.println("******************");
            System.out.println("Entrez le numéro du Quiz que vous voulez commencer/ continuer: ");
            var = sc.nextInt();
            System.out.println("******************");
            if( var>0 && var <=form.getListeQuiz().size())
            {
                q=form.getquiz(var-1);
                /// on doit verifier si il est déja entamé:
                //comment xd
                // on verifie dans la liste des  quiz entamés dans le compte apprenant
                try
                {
                    rq=c.rechercheQuiz(q.getNom());
                    a=true;
                }
                catch( QuizNonEntameException e)
                {
                    a=false;
                    // on doit creer un nouvel objet reponseQuiz à partir du quiz
                    rq.setNom(q.getNom());
                    rq.setDateEX(q.getDateEX());
                    rq.setDateO(q.getDateO());
                    rq.setNotions(q.getNotions());
                    rq.setQcm(q.getQcm());
                    rq.setQcu(q.getQcu());
                    rq.setQo(q.getQo());
                }
                /// now on peut remplir les reponses :
                rq.repondreAuQuiz(a);
                if(!a)
                {
                    c.ajouterQuizEntame(rq);
                }
            }
            else
            {
                System.out.println("ERREUR");
            }


        }
        catch( Exception e){}
    }





    public void afficherApprenantsForm(CompteFormateur f)
    {
        System.out.println("Entrez le nom de la formation :");
        Scanner sc= new Scanner(System.in);
        String nomForm = sc.nextLine();




        try
        {
            Formation form=rechercheFormation2(nomForm,f.getId());
            System.out.println("hhh");
            // si la formation existe
            form.afficherApprenants();
        }
        catch ( FormationNonExistanteException e){
        }
    }

    public void classApprenantsClass(CompteFormateur f)
    {
        System.out.println("Entrez le nom de la formation :");
        Scanner sc= new Scanner(System.in);
        String nomForm = sc.nextLine();




        try
        {
            Formation form=rechercheFormation2(nomForm,f.getId());
            System.out.println("hhh");
            // si la formation existe
            form.classApprenants();
        }
        catch ( FormationNonExistanteException e){
        }
    }







    public void menuapprenant( CompteApprenant apprenant)
    {
        boolean menu3=true;
        int choix1;
        Scanner sc= new Scanner(System.in);
        ///////////connexion etudiant !
        while(menu3)
        {
            System.out.println("*****************************************************");
            System.out.println("***************** MENU APPRENANT ********************");
            System.out.println("1- Gestion du compte");

            System.out.println("2- Liste des Quiz ouverts de la formation( Avec pourcentage de realisation)");
            System.out.println("3- Faire un Quiz");

            System.out.println("15- Quitter");
            System.out.println("Choix :");
            choix1=sc.nextInt();
            switch(choix1)
            {
                case 1:
                    gestionCompte(apprenant);
                    break;

                case 2:
                    afficherQuizs(apprenant.getFormation(), apprenant.getFormateur());
                    break;
                case 3:
                    faireUnQuiz(apprenant);
                    break;
                case 15:
                    menu3=false;
                    break;
            }
        }
    }





    /**********************************************************************************************************************************/
    /******         POUR L'INTERFACE ****************************************************************************************************/
    // R

    /**********************************************************************************************************/

    public ArrayList<String> arrayStringFormation(ArrayList<Formation> liste) // elle let les noms des formation dans une liste de string
    {
        ArrayList<String> listeForma=new ArrayList<String>() ;

        Iterator<Formation> it = liste.iterator();
        String f;
        while (it.hasNext())
        {
            f=it.next().getNom();
            listeForma.add(f);
            System.out.println(" formatio "+f);


        }
        return listeForma;

    }

    public ArrayList<CheckBox> arrayNotion(ArrayList<Notion> liste)// generer des checkboxs pour pouvoir les ajouter ensuite
    {   int i=0;
        ArrayList<CheckBox> check=new ArrayList<CheckBox>();
        Iterator<Notion> it = getListeNotions().iterator();
        String f;
        while (it.hasNext())
        {
            f=it.next().getNom();
            CheckBox ch2=new CheckBox(String.valueOf(i));

            ch2.setText(f);
            check.add(ch2);
            i++;

        }
        return check;
    }



   /* public ArrayList<String> notionSelect(ArrayList<CheckBox> notions)//elle permet de mettre dans une liste toutes les notions qui ont été séléctionné
    {
        ArrayList<String> notionSelected = new ArrayList<String>();


        Iterator<CheckBox> it = notions.iterator();
        String f;
        CheckBox ff;
        while (it.hasNext()) {
            ff = it.next();
            if (ff.isSelected()) {
                f = ff.getText();
                notionSelected.add(f);
                System.out.println(ff.getText());
                System.out.println(ff.isSelected());
            }

        }
        return notionSelected;
    }*/

    public ArrayList<TextField> arrayNumber(ArrayList<Notion> liste)// generer des checkboxs pour pouvoir les ajouter ensuite
    {   int i=0;
        ArrayList<TextField> textF=new ArrayList<TextField>();
        Iterator<Notion> it = getListeNotions().iterator();
        String f;
        Notion h;



        while (it.hasNext())
        {
            h=it.next();
            TextField tx;
            tx = new TextField();
            tx.setPromptText("n");

            tx.setMaxWidth(50.0);
            tx.setMaxHeight(0.0);
            textF.add(tx);
            i++;

        }
        return textF;
    }



    public ArrayList<Notion> notionSelect(ArrayList<CheckBox> notions,ArrayList<TextField> number)//elle permet de mettre dans une liste toutes les notions qui ont été séléctionné
    {
        ArrayList<Notion> notionSelected = new ArrayList<Notion>();
        Notion no;

        Iterator<CheckBox> it = notions.iterator();
        String f,nb;
        CheckBox ff;
        int k,j;
        int h;
        while (it.hasNext()) {
            ff = it.next();
            if (ff.isSelected()) {
                f = ff.getText();

                no=rechercheNotion(f);
                h=notions.indexOf(ff);

                k=Integer.valueOf(number.get(h).getText());
                no.setNb(k);
                notionSelected.add(no);
                System.out.println(ff.getText());
                System.out.println(ff.isSelected());
            }

        }
        return notionSelected;
    }

    public ArrayList<Notion> notionnotion(ArrayList<String> notions)//elle permet de mettre dans une liste toutes les notions qui ont été séléctionné
    {
        ArrayList<Notion> notionSelected = new ArrayList<Notion>();


        Iterator<String> it = notions.iterator();
        String f;

        while (it.hasNext())
        {

            f=it.next();
            notionSelected.add(rechercheNotion(f));

        }
        return notionSelected;
    }

    public ArrayList<Label> arrayLabel(ArrayList<Formation> liste)// generer des checkboxs pour pouvoir les ajouter ensuite
    {   int i=1;
        double j=0;
        ArrayList<Label> textF=new ArrayList<Label>();
        Iterator<Formation> it = getListeFormations().iterator();
        String f;
        while (it.hasNext())
        {

            Label l=new Label();

            l.setText("formation "+i+":   "+it.next().getNom());
            l.setLayoutY(j+20);
            textF.add(l);
            i++;
            j=j+20;
        }
        return textF;
    }
    public ArrayList<Label> quizLabel(Quiz qz)// generer des label pour le quiz
    {   int i=1;
        double j=0;
        ArrayList<Label> textF=new ArrayList<Label>();
        Iterator<QCM>  it = qz.getQcm().iterator();

        while (it.hasNext())
        { Label lll=new Label();
            QCM h=it.next();
            lll.setText("Enonce "+i+": "+h.getEnonce());

            lll.setLayoutY(j+20);

            textF.add(lll);
            i++;

            int k=1;
            Iterator<String>  it1 = h.getRep().iterator();

            while (it1.hasNext())
            {
                Label lm=new Label();
                lm.setText(k+" - "+ it1.next());
                //System.out.println(it1.next());
                k++;
                lm.setLayoutY(j+20);
                textF.add(lm);
            }
            j=j+20;

        }
        Iterator<QCU>  it2 = qz.getQcu().iterator();

        while (it2.hasNext())
        {
            QCU h3=it2.next();
            Label ll=new Label();
            ll.setText("Enonce "+i+": "+h3.getEnonce());
            ll.setLayoutY(j+20);
            ll.setLayoutX(80);
            textF.add(ll);

            i++;
            int k=1;
            Label l6=new Label();
            l6.setText("reponse: " +h3.getReponse());
            k++;
            l6.setLayoutY(j+20);
            textF.add(l6);
            j=j+20;


        }
        Iterator<QO>  it3 = qz.getQo().iterator();

        while (it3.hasNext())
        {   QO h4=it3.next();
            Label l5=new Label();
            l5.setText("Enonce "+i+": "+h4.getEnonce());
            l5.setLayoutX(80);
            l5.setLayoutY(j+20);
            textF.add(l5);

            i++;

            int k=1;
            Iterator<String>  it9 = h4.getMots().iterator();

            while (it9.hasNext())
            {
                Label l6=new Label();
                l6.setText(k+" - "+it9.next());
                k++;
                l6.setLayoutY(j+20);
                textF.add(l6);
            }
            j=j+20;

        }

        return textF;
    }

    public Label newLabel(ArrayList<Label> liste,String v)// ajouter une nouvelle formation
    {

        double j;

        Formation format4 = new Formation(v);
        listeFormations.add(format4);
        Label l=new Label();
        liste.add(l);
        l.setText("formation "+liste.size()+":   "+format4.getNom());
        j=liste.size()*20;
        l.setLayoutY(j);

        return l;
    }




    /*********************************************************************************************************************************/


    public Quiz genererQuizInterface(CompteFormateur f,String nomForm/* nom de la formation*/,String nomQuiz,LocalDate d/*date d'ouverture*/,LocalDate d2 /*date de fermeture*/,ArrayList<Notion> notions)
    {
        Quiz q = new Quiz();
        boolean existe=true;
        boolean erreur=true;
        try
        {
            Formation form=rechercheFormation2(nomForm,f.getId());
                // on peut creer le quiz
                q= new Quiz(nomQuiz,d,d2,notions);
                q.setFormation(nomForm);
                // le nombre de questions par notio:
                Iterator<Notion> it = notions.iterator();
                Notion not;
                int var =0;
                int nb=0;
                while(it.hasNext())
                {
                    not= it.next();
                    nb = not.getNb();
                    // on ajoute les questions
                    q.ajouterQst(nb,not);
                    var++;
                }
                form.addQuiz(q);
        }
        catch ( FormationNonExistanteException e){ }
        return q;
    }



    public Formation rechercheFormationInterface( String nom, String idFormateur)
    {
        Boolean t= false;
        Iterator<Formation> it = this.listeFormations.iterator();
        Formation f;
        Formation f1= new Formation();
        while (it.hasNext())
        {
            f= it.next();
            if (f.getNom().equals(nom) && (f.getIdFormateur()).equals(idFormateur))
            {
                t= true;
                f1=f;
            }
        }

        return f1;
    }


    public CompteFormateur cnxFormInterface(String id, String pwd) throws CompteInexistantInterfaceException, PwdErroneInterfaceException
    {
        boolean ex= false;

        CompteFormateur f = new CompteFormateur();
        CompteFormateur f2 = new CompteFormateur() ;
        String mdp = new String() ;
        Iterator<CompteFormateur> ite = listeFormateurs.iterator();
        while (ite.hasNext())
        {
            f=ite.next();

            if ( f.getId().equals(id))
            {
                ex= true;
                mdp=f.getPwd();
                f2=f;

            }
        }
        if (ex==true)
        {
            if(pwd.equals(mdp))
            {
                return f2;
            }
            else throw new PwdErroneInterfaceException();
        }
        else throw new CompteInexistantInterfaceException();
    }



    public ArrayList<Label> arrayLabel2(ArrayList<Formation> liste)// generer des checkboxs pour pouvoir les ajouter ensuite
    {   int i=1;
        double j=0;
        ArrayList<Label> textF=new ArrayList<Label>();
        Iterator<Formation> it = getListeFormations().iterator();
        String f;
        while (it.hasNext())
        {

            Label l=new Label();
            Formation fo=it.next();
            l.setText("formation "+i+":   "+fo.getNom());
            l.setLayoutY(j+20);
            textF.add(l);
            j=j+20;
            Label l2=new Label();
            l2.setText("Date de début : "+fo.getDatedeb()+         "       Date de fin: "+fo.getDatefin());
            l2.setLayoutY(j+20);
            textF.add(l2);
            j=j+20;
            Label l3=new Label();
            l3.setText("Descriptif : "+fo.getDescription());
            l3.setLayoutY(j+20);
            textF.add(l3);



            i++;
            j=j+20;
        }
        return textF;
    }



    public CompteApprenant cnxapprInterface(String id, String pwd) throws CompteInexistantInterfaceException, PwdErroneInterfaceException
    {
        boolean ex= false;

        CompteApprenant f = new CompteApprenant();
        CompteApprenant f2 = new CompteApprenant() ;
        String mdp = new String();
        Iterator<CompteApprenant> ite = listeApprenants.iterator();
        while (ite.hasNext())
        {
            f=ite.next();

            if ( f.getId().equals(id))
            {
                ex= true;
                mdp=f.getPwd();
                f2=f;

            }
        }
        if (ex==true)
        {
            if(pwd.equals(mdp))
            {
                return f2;
            }
            else throw new PwdErroneInterfaceException();
        }
        else throw new CompteInexistantInterfaceException();
    }






}















