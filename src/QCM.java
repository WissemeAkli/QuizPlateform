import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Scanner;

public class QCM extends Question {

    public ArrayList<String> getReponsesCorr() {

        return reponsesCorr;
    }

    public void setReponsesCorr(ArrayList<String> reponsesCorr) {
        this.reponsesCorr = reponsesCorr;
    }

    //** ATTRIBUTS***************************************/
    private ArrayList<String> reponsesCorr;



    private ArrayList<String> reponsesErr;

    private ArrayList<String> rep;

    //** CONSTRUCTEUR(S)***************************************/
    public QCM() {
        super();
    }

    public QCM(String enonce, ArrayList<String> rc, ArrayList<String> re, String notion) {
        super(enonce, notion);
        this.reponsesCorr = rc;
        this.reponsesErr = re;
        rep=new ArrayList<String>();

    }

    /***************/
    public ArrayList<String> getReponsesErr() {
        return reponsesErr;
    }

    public void setReponsesErr(ArrayList<String> reponsesErr) {
        this.reponsesErr = reponsesErr;
    }

    public ArrayList<String> getRep()
    {
        rep.addAll(reponsesCorr);
        rep.addAll(reponsesErr);
        return rep;
    }

    public void setRep(ArrayList<String> rep) {
        this.rep = rep;
    }


    //** METHODES ***************************************/
    public void afficherQst() {
        rep=new ArrayList<>();
        super.afficherQst();
        System.out.println("Veuillez choisir les réponses justes :");
        String s = new String();
        int i = 0;
        Iterator<String> it = reponsesErr.iterator();
        Iterator<String> itjuste = reponsesCorr.iterator();
        Iterator<String> itrep = rep.iterator();
        int indice = 1;


        while (it.hasNext() || itjuste.hasNext()) {
            indice = (int) (1 + Math.random() * 100000) % 3;
            if (it.hasNext() && indice == 1) {
                s = it.next();
                rep.add(s);
                //System.out.println(i+1+"- "+s);
            } else {
                if (itjuste.hasNext()) {
                    s = itjuste.next();
                    rep.add(s);
                    //System.out.println(i+1+"- "+s);
                }
            }


        }
        i=0;
        itrep = rep.iterator();
        while (itrep.hasNext()) {
            i++;

            System.out.println(i + "- " + itrep.next());
        }
    }

    /************/
    public void afficherRepCorr() {
        System.out.println(" Les réponses correctes sont : ");
        Iterator<String> it = reponsesCorr.iterator();

        while (it.hasNext()) {
            System.out.println("- " + it.next());
        }
    }

    /*************/

    public boolean equals(QCM q) {
        if (this.getEnonce().equals(q.getEnonce())) {
            return true;
        } else {
            return false;
        }
    }


    public ReponseQCM repondre() {
        boolean stop = false;
        int a;
        Scanner sc = new Scanner(System.in);
        ReponseQCM repApp = new ReponseQCM();


        while (!stop)
        {

            System.out.println("Veuillez taper l'index de la reponse ou '999' pour valider les reponses");
            a = sc.nextInt();

            if (a==999)
            {
                stop = true;
            }
            else
            {
                if (a>rep.size())
                {
                    System.out.println("veuillez saisir un numero entre 1 et "+rep.size());
                }
                else
                {
                    repApp.ajtReponse(rep.get(a-1));
                }

            }

        }

        return repApp;
        }








    public double corrigerQcm(ReponseQCM reponseEtudiant) {
        Iterator<String> it = reponseEtudiant.getReponses().iterator();
        double a = 0.0;
        while (it.hasNext()) {

            if( this.reponsesCorr.contains(it.next()))
            {a= a+((double) 1/reponsesCorr.size());}
            else {
                if(a>0) a=a- (double) 1/reponsesCorr.size();
            }
        }
        System.out.println("note qcm="+a);
        return a;

    }
}
