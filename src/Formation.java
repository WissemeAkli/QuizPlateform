import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Scanner;

public class Formation {

    public ArrayList<Quiz> getListeQuiz() {
        return listeQuiz;
    }

    public void setListeQuiz(ArrayList<Quiz> listeQuiz) {
        this.listeQuiz = listeQuiz;
    }

    //** ATTRIBUTS***************************************/
    private ArrayList<Quiz> listeQuiz;
    private ArrayList<Notion> listeNotions;


    private GroupeApprenants groupe;



    private String nom;// nom de la formation
    private String description;

    public ArrayList<Notion> getListeNotions() {
        return listeNotions;
    }

    public void setListeNotions(ArrayList<Notion> listeNotions) {
        this.listeNotions = listeNotions;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDate getDatedeb() {
        return datedeb;
    }

    public void setDatedeb(LocalDate datedeb) {
        this.datedeb = datedeb;
    }

    public LocalDate getDatefin() {
        return datefin;
    }

    public void setDatefin(LocalDate datefin) {
        this.datefin = datefin;
    }

    private LocalDate datedeb;
    private LocalDate datefin;


    private String idFormateur;



    //** CONSTRUCTEUR(S)***************************************/

    public Formation(){
        this.listeQuiz = new ArrayList<Quiz>();
        this.listeNotions = new ArrayList<Notion>();

    }
    public Formation(String nom, String des,LocalDate dd, LocalDate df,  ArrayList<Notion> n,GroupeApprenants a , String nomformateur)
    {
        this.nom=nom;
        this.datedeb=dd;
        this.datefin=df;
        this.description=des;
        this.listeQuiz = new ArrayList<Quiz>();
        this.listeNotions=n;
        this.groupe=a;
        this.idFormateur=nomformateur;
    }

    public Formation(String nom )
    {
        this.nom=nom;
        this.listeQuiz = new ArrayList<Quiz>();
        this.listeNotions = new ArrayList<Notion>();
    }


    //** METHODES ***************************************/

    public String getNom() {
        return nom;
    }

    public GroupeApprenants getGroupe() {
        return groupe;
    }

    public void setGroupe(GroupeApprenants groupe) {
        this.groupe = groupe;
    }

    public String getIdFormateur() {
        return idFormateur;
    }

    public void setIdFormateur(String idFormateur) {
        this.idFormateur = idFormateur;
    }

    /*****************************/

    public boolean quizExiste(String nom) throws NomQuizExistantException
    {
        Iterator<Quiz> it = this.listeQuiz.iterator();
        boolean existe=false;
        Quiz q;
        while(it.hasNext()&& !existe)
        {
            q=it.next();
            if (q.getNom().equals(nom))
            {
                existe = true;
            }
        }
        if ( existe)
        {
            throw new NomQuizExistantException();
        }
        return existe;
    }
    //***************************************************************************
    public void afficherNotions() throws AucuneNotionException
    {
        Iterator<Notion> it= listeNotions.iterator();
        int i=0;
        Notion n;
        while (it.hasNext())
        {
            i++;
            System.out.print("NOTION N ° "+i);
            System.out.print("------------");
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
            System.out.print("NOTION N ° "+i);
            System.out.print("------------");
            n=it.next();
            n.afficherNomNotion();


        }
        if ( i==0)
        { throw new AucuneNotionException();}

    }
    /*********************************************************************/
    public ArrayList<Notion> selectNotions() throws AucuneNotionException
    {
        ArrayList<Notion> liste = new ArrayList<>();
        int var=0;
        int i=0;
        Scanner sc = new Scanner(System.in);
        /// on devra selectionner la liste des notions
        /// on affichera alors la liste des notions ( liste not)
        System.out.println("*** Selection des Notions :  ");
        System.out.println(" Veuillez taper les numeros des notions qu'abordera ce Quiz un par un et terminer avec 9999");
        System.out.println("                               (Selectionner au moins une) ");
        try
        {
            this.afficherNomsNotions();
            while (var!=9999 || i==0 )
            {
                var=sc.nextInt();
                if ( var !=9999 || var<this.listeNotions.size())
                {
                    liste.add(this.listeNotions.get(var-1));
                    this.listeNotions.get(var-1).afficherNomNotion();
                }
                i++;
            }
        }
        catch ( AucuneNotionException e)
        {
            // pas de ntions donc pas de formation !
            throw new AucuneNotionException();
        }
        return liste;
    }
    /****************************************************************************/

    public void afficherQuizs() throws AucunQuizFormationException
    {
        if ( listeQuiz.size()>0)
        {
            System.out.println("---------------------------");
            System.out.println("Formation : "+this.nom);
            System.out.println("---------------------------");
            System.out.println("Liste des Quiz :");
            System.out.println("---------------------------");
            Iterator<Quiz> it = this.listeQuiz.iterator();
            int i=1;
            Quiz q;
            while ( it.hasNext())
            {
                System.out.println("QUIZ N"+i);
                i++;
                q=it.next();
                q.afficherQuiz();
                System.out.println("---------------------------");
            }
        }
        else
        {
            throw  new AucunQuizFormationException();
        }
    }


    public void modifierQuiz()throws AucunQuizFormationException, QuizDejaOuvertException
    {
        Scanner sc = new Scanner(System.in);
        String nom;
        Quiz q;
        int choix ;
        boolean menu= true;

        if ( listeQuiz.size()>0)
        {
            System.out.println("Nom du Quiz à modifier :");
            nom=sc.next();
            try{
                q= rechercheQuiz(nom);
                // on doit verifier si le quiz n'est pas ouvert !
                if ( q.getDateO().isBefore(LocalDate.now()))
                {

                    throw new QuizDejaOuvertException();
                }
                else
                {
                    while ( menu)
                    {
                        /// on affiche le menu des modifs:
                        System.out.println("*******************************************************************");
                        System.out.println("***************** MENU modification d'un Quiz  ********************");
                        System.out.println("1- Ajouter Une question aléatoire");
                        System.out.println("2- Supprimer une question");
                        System.out.println("3- Ajouter Une question  ");
                        System.out.println("4- Remplacer Une question de la même notion  ");
                        System.out.println("5- Retour au menu formateur ");
                        System.out.println("Choix : ");
                        choix=sc.nextInt();
                        switch(choix)
                        {
                            case 1 : // ajout d'une question aleatoire..
                                try
                                {
                                    q.ajouterQstalea();
                                }
                                catch (AjoutImpossibleException e){}
                                break;
                            case 2 :
                                try {
                                    q.supprQst();
                                }
                                catch(OperationAnnuleeException e){}
                                break;
                            case 3 :
                                try
                                {
                                    q.ajouterQst();
                                }
                                catch (AjoutImpossibleException e){}
                                break;
                            case 4 :
                                try
                                {
                                    q.remplacerQst();
                                }
                                catch (Exception e){}

                                break;
                            case 5 :
                                menu=false;
                                break;
                        }
                    }
                }
            }
            catch (QuizInexistantException e){}
        }
        else
        {
            throw  new AucunQuizFormationException();
        }
    }

    public Quiz rechercheQuiz(String nom) throws QuizInexistantException
    {
        Iterator<Quiz> it=this.listeQuiz.iterator();
        Quiz q,q2= new Quiz();
        boolean existe=false;
        while(it.hasNext()&& !existe)
        {
            q=it.next();
            if( q.getNom().equals(nom))
            {
                q2=q;
                existe=true;
            }
        }
        if (!existe)
        {
            throw new QuizInexistantException();
        }
        return q2;
    }
    public void addQuiz(Quiz q)
    {
        listeQuiz.add(q);
    }


    public Quiz getquiz( int nb)
    {
        return this.listeQuiz.get(nb);
    }





    public void afficherApprenants()
    {
        this.groupe.afficherApprenants();
    }

    public void classApprenants()
    {
        this.groupe.classementApprenant();
    }







    /**************************/
    public Boolean rechercheQuizInterface(String nom)
    {
        Iterator<Quiz> it=this.listeQuiz.iterator();
        Quiz q,q2= new Quiz();
        boolean existe=false;
        while(it.hasNext()&& !existe)
        {
            q=it.next();
            if( q.getNom().equals(nom))
            {
                q2=q;
                existe=true;
            }
        }

        return existe;
    }

}
