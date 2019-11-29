import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;

public class CompteApprenant extends Compte implements Comparable<CompteApprenant> {

    /** ATTRIBUTS*************************/
    private double moyenne=0;

    public String getFormation() {
        return formation;
    }

    public void setFormation(String formation) {
        this.formation = formation;
    }

    private String formation;

    public String getFormateur() {
        return formateur;
    }

    public void setFormateur(String formateur) {
        this.formateur = formateur;
    }

    private String formateur;
    private ArrayList<ReponseQuiz> quizEntames;


    /** Constructeur(s)*******/
    public CompteApprenant()
    {
        super(); quizEntames= new ArrayList<>();
    }

    public CompteApprenant(double moy)
    {
        moyenne=moy; quizEntames= new ArrayList<>();
    }
    public CompteApprenant(String nom, String prenom, LocalDate dateN, String adresse,String formation,String formateur)
    {

        super(nom,prenom,dateN,adresse);
        String id =this.genID();
        this.setId(id);
        System.out.println(" ID :"+id);
        String pwd= this.genMDP();
        this.setPwd(pwd);
        System.out.println(" PWD:"+pwd);
        this.formation=formation;
        this.formateur=formateur;
        this.moyenne=0;
        quizEntames= new ArrayList<>();
    }



    /** GETTERS SETTERS************************************/

    public double getMoyenne() {
        return moyenne;
    }

    public void setMoyenne(double moyenne) {
        this.moyenne = moyenne;
    }
    /**Méthodes*/
     public void afficherApprenant()
     {
         super.afficherInfos();
         int nb=0;double moy=0;
         Iterator<ReponseQuiz> it= this.quizEntames.iterator();
         while( it.hasNext())
         {
             nb++;
             moy+=it.next().getMoyenne();

         }
         if (nb==0)
         {
             nb=1;
         }

         this.moyenne=(double) moy/nb;
         System.out.println("Moyenne : "+this.moyenne);
     }


    public int compareTo(CompteApprenant autreAppr)
    {

        int moy=0;
        if (this.moyenne > autreAppr.moyenne)  moy=1;
        if (this.moyenne < autreAppr.moyenne) moy= -1;
        if (this.moyenne == autreAppr.moyenne) moy= 0;
        return moy ;
    }

     public Boolean equals(CompteApprenant a)
     {
         System.out.println("   "+this.getNom()+"    "+a.getNom()+"     "+this.getPrenom()+"    "+a.getPrenom());
         if ( this.getNom().equals(a.getNom())&& this.getPrenom().equals(a.getPrenom()))
         {
             return true;
         }
         else
         {
             return false;
         }

     }

     public String genID()
     {
        String test=this.getNom().substring(0,1);
        String id= new String();
        id = test+this.getPrenom();
        System.out.println(" ID :"+id);
        return id;
     }

     public String genMDP()
     {
        String test = this.getDateNaissance().toString();
        String pwd= this.getNom()+test;
         System.out.println(" PWD :"+pwd);
        return pwd;
     }



     public ReponseQuiz rechercheQuiz( String nom) throws QuizNonEntameException
     {
         Iterator<ReponseQuiz> it = this.quizEntames.iterator();
         boolean trouv=false;
         ReponseQuiz q1,q2= new ReponseQuiz();
         while( it.hasNext()&& !trouv)
         {
             q1=it.next();
             if ( q1.getNom().equals(nom))
             {
                 trouv=true;
                 q2=q1;
             }
         }
         if(trouv) {return q2;}
         else
         {
             throw new QuizNonEntameException();
         }
     }



     public void ajouterQuizEntame(ReponseQuiz q)
     {
         this.quizEntames.add(q);
     }

}
