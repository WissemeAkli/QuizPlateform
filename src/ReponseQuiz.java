import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;

public class ReponseQuiz extends Quiz {




    /**************** LES ATTRIBUTS ***************/


    // les reponses questiions seront sous forme de tableaux
            /// reponse qcm = liste de reponses
            // reponses qo = un mot
            // remponse qcu = une phrase
    private ArrayList<ReponseQCM> repQcm;

    private ArrayList<String> repQo;
    private ArrayList<String> repQcu;

    public double getMoyenne() {
        return moyenne;
    }

    public void setMoyenne(double moyenne) {
        this.moyenne = moyenne;
    }

    private double moyenne;



    //******** CONSTRUCTEUR(S) **********************/
    public ReponseQuiz(){


        this.repQcm= new ArrayList<ReponseQCM>();
        this.repQcu= new ArrayList<String>();
        this.repQo = new ArrayList<String>();
        moyenne=0;
    }

    /*********************************************************/
    // METHODES

    public void repondreAuQuiz(Boolean c)
    {
        boolean stop = false;
        int var=0;

     // on doit afficher les questions une a une
        Iterator<QCM> it1= this.getQcm().iterator();

        Iterator<QCU> it2= this.getQcu().iterator();

        Iterator<QO> it3= this.getQo().iterator();


        QCU q2= new QCU();
        QO q3= new QO();
        QCM q1= new QCM();
        ReponseQCM rq=new ReponseQCM();

        ReponseQCM vide=new ReponseQCM();
        int aa;
        if( c)/// quiz deja entamé
        {


            while( !stop && it1.hasNext())
            {
                q1=it1.next();
                q1.afficherQst();
                aa=this.getQcm().indexOf(q1);
                if(repQcm.get(aa).getReponses().isEmpty())
                {
                    var=miniMenu();
                    switch (var)
                    {
                        case 1:

                            this.repQcm.add(q1.repondre());
                            break;
                        case 2:
                            this.repQcm.add(vide);
                            break;
                        case 3:
                            stop =true;
                            break;
                    }
                }
                else
                    {
                        repQcm.get(aa).afficherRep();
                        var=miniMenu2();
                        switch (var)
                        {
                            case 1:

                                this.repQcm.set(aa,q1.repondre());
                                break;
                            case 2:

                                break;
                            case 3:
                                stop =true;
                                break;
                        }
                    }
            }

            while( !stop && it2.hasNext())
            {
                q2=it2.next();
                q2.afficherQst();
                aa=this.getQcu().indexOf(q2);
                if(repQcu.get(aa).equals("VIDE"))
                {
                    var=miniMenu();
                    switch (var)
                    {
                        case 1:
                            this.repQcu.add(q2.repondre());
                            break;
                        case 2:
                            this.repQcu.add("VIDE");
                            break;
                        case 3:
                            stop =true;
                            break;
                    }
                }
                else
                    {
                        System.out.println("Votre reponse précédente est");
                        System.out.println(repQcu.get(aa));
                        var=miniMenu2();
                        switch (var)
                        {
                            case 1:
                                this.repQcu.set(aa,q2.repondre());
                                break;
                            case 2:

                                break;
                            case 3:
                                stop =true;
                                break;
                        }
                    }


            }

            while( !stop && it3.hasNext())
            {
                q3=it3.next();
                q3.afficherQst();
                aa=this.getQo().indexOf(q3);
                if(repQo.get(aa).equals("VIDE"))
                {
                    var=miniMenu();
                    switch (var)
                    {
                        case 1:
                            this.repQo.add(q3.repondre());
                            break;
                        case 2:
                            this.repQo.add("VIDE");
                            break;
                        case 3:
                            stop =true;
                            break;
                    }
                }
                else
                {
                    System.out.println("Votre reponse précédente est");
                    System.out.println(repQo.get(aa));
                    var=miniMenu2();
                    switch (var)
                    {
                        case 1:
                            this.repQo.set(aa,q3.repondre());
                            break;
                        case 2:
                            //this.repQo.set(aa,"VIDE");
                            break;
                        case 3:
                            stop =true;
                            break;
                    }
                }
            }
        }
        else
        {
            // nouveau quiz
            while( !stop && it1.hasNext())
            {
                q1=it1.next();
                q1.afficherQst();
                var=miniMenu();
                switch (var)
                {
                    case 1:
                        this.repQcm.add(q1.repondre());
                        break;
                    case 2:
                        this.repQcm.add(vide);
                        break;
                    case 3:
                        stop =true;
                        break;
                }

            }

            while( !stop && it2.hasNext())
            {
                q2=it2.next();
                q2.afficherQst();
                var=miniMenu();
                switch (var)
                {
                    case 1:
                        this.repQcu.add(q2.repondre());
                        break;
                    case 2:
                        this.repQcu.add("VIDE");
                        break;
                    case 3:
                        stop =true;
                        break;
                }

            }

            while( !stop && it3.hasNext())
            {
                q3=it3.next();
                q3.afficherQst();
                var=miniMenu();
                switch (var)
                {
                    case 1:
                        this.repQo.add(q3.repondre());
                        break;
                    case 2:
                        this.repQo.add("VIDE");
                        break;
                    case 3:
                        stop =true;
                        break;
                }

            }
        }
        System.out.println("Quiz terminé");
        evaluation();
    }

    private int miniMenu()
    {
        Scanner s=new Scanner(System.in);
        System.out.println("********************************************************");
        System.out.println("1. Répondre à la question\n2. Passer à la question suivante\n3" +
                ". Enregistrer les reponses et arreter le quiz");
        return s.nextInt();

    }
    private int miniMenu2()
    {
        Scanner s=new Scanner(System.in);
        System.out.println("********************************************************");
        System.out.println("1. Modifier la reponse\n2. Passer à la question suivante\n3" +
                ". Enregistrer les reponses et arreter le quiz");
        System.out.println("Choix : ");
        return s.nextInt();

    }

    public void evaluation()
    {
        double x=0;
        Iterator<QCM> itqcm = this.getQcm().iterator();
        Iterator<QCU> itqcu = this.getQcu().iterator();
        Iterator<QO> itqo = this.getQo().iterator();
        int a;
        a= this.getQcm().size()+ this.getQcu().size()+this.getQo().size();
        Iterator <ReponseQCM> itrepqcm = this.repQcm.iterator();
        Iterator<String> itrepqcu = this.repQcu.iterator();
        Iterator<String> itrepqo = this.repQo.iterator();

        while(itrepqcm.hasNext()&& itqcm.hasNext())
        {
            x+=itqcm.next().corrigerQcm(itrepqcm.next());
        }
        while(itrepqcu.hasNext())
        {
            x+=itqcu.next().corrigerQcu(itrepqcu.next());
        }
        while(itrepqo.hasNext())
        {
            x+=itqo.next().corrigerQo(itrepqo.next());
        }
        this.moyenne=x;
        System.out.println("la note du quiz = "+x+ "/"+a);


    }



}
