import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;

public class QO  extends Question {



    //** ATTRIBUTS***************************************/
    private ArrayList<String> mots;

    //** CONSTRUCTEUR(S)***************************************/
    public QO()
    {
        super();
    }
    public QO(String enonce,ArrayList<String> words,String notion)
    {
        super(enonce,notion);
        mots= new ArrayList<String>();
        this.mots=words;
    }

    /********************************************/
    public ArrayList<String> getMots() {
        return mots;
    }

    public void setMots(ArrayList<String> mots) {
        this.mots = mots;
    }




    //** METHODES ***************************************/
    public void afficherQst()
    {
        super.afficherQst();
        System.out.println(" Veuillez repondre par un seul mot: ");
    }
    public void ajouterMot(String m)
    {
        this.mots.add(m);
    }

    public void supprimerMot(String m)
    {
        this.mots.remove(m);
    }
    public void afficherRepCorr()
    {
        System.out.println(" La reponse correcte est l'un de ces mots : ");
        Iterator<String> it=  mots.iterator();

        while ( it.hasNext())
        {
            System.out.print("- "+it.next());
        }
    }


    public  boolean equals ( QO q)
    {
        if ( this.getEnonce().equals(q.getEnonce()))
        {
            return  true;
        }
        else
        { return false;}
    }

    public String repondre()
    { String a;
        Scanner sc = new Scanner(System.in);
        a=sc.nextLine();
        return a;
    }


    public double corrigerQo(String reponseEtudiant)
    {
        double a=0.0f;

        if( this.mots.contains(reponseEtudiant))a=1.0;
        System.out.println("note qo="+a);
        return a;

    }



}
