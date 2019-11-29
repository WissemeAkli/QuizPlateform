import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;

public class QCU extends Question {


    //** ATTRIBUTS***************************************/
    private String reponse;
    private ArrayList<String> reponsesErr;
    private ArrayList<String> rep;

    //** CONSTRUCTEUR(S)***************************************/
    public QCU()
    {
        super();
    }
    public QCU(String enonce,String reponse, ArrayList<String> re,String notion)
    {
        super(enonce,notion);
        this.reponse=reponse;
        this.reponsesErr=re;
        rep=new ArrayList<String>();

    }

    /*** Getters setter ****/
    public String getReponse() {
        return reponse;
    }

    public void setReponse(String reponse) {
        this.reponse = reponse;
    }

    //** METHODES ***************************************/
    public void afficherQst()
    {
        rep=new ArrayList<>();
        super.afficherQst();
        System.out.println("Veuillez taper l'une des réponses suivantes :");
        String s;
        int i=0;
        Iterator<String > it = reponsesErr.iterator();
        Iterator<String> itrep=rep.iterator();
        int indice= (int) (Math.random()*100000) %reponsesErr.size();


        while (it.hasNext())
        {
            if (i==indice)
            {
                s = it.next();
                rep.add(s);
                //System.out.println(i+1+"- "+this.reponse);
                //i++;
            }
            s = it.next();
           // System.out.println(i+1+"- "+s);
            //i++;
            rep.add(s);
        }
        itrep = rep.iterator();
        i=0;
        while(itrep.hasNext())
        {
            i++;

            System.out.println(i+"- "+itrep.next());
        }

    }


    /***********/
    public void ajouterRepErr(String m)
    {
        this.reponsesErr.add(m);
    }

    public void supprimerRepErr(String m)
    {
        this.reponsesErr.remove(m);
    }
    /*********/
    public void afficherRepCorr()
    {
        System.out.println(" La reponse correcte est : "+this.reponse);
    }

    public  boolean equals ( QCU q)
    {
        if ( this.getEnonce().equals(q.getEnonce()))
        {
            return  true;
        }
        else
        { return false;}
    }

    public String repondre()
    {
        System.out.println("veuillez choisir une reponse 'un numero'");
        int a=0;
        String repApp;
        Scanner sc = new Scanner(System.in);
        a=sc.nextInt();
        repApp=rep.get(a-1);
        return repApp;

    }

    public double corrigerQcu(String reponseEtudiant)
    {
        double a=0.0f;
        if(reponseEtudiant.equals(this.reponse))  a=1.0;
          return a;

    }



}
