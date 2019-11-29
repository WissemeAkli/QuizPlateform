import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;

public class Notion {



    //** ATTRIBUTS***************************************/
    private String nom;

    public int getNb() {
        return Nb;
    }

    public void setNb(int nb) {
        Nb = nb;
    }

    private int Nb;



    private ArrayList<QCM> listeQCM;
    private ArrayList<QO> listeQO;
    private ArrayList<QCU> listeQCU;

    //** CONSTRUCTEUR(S)***************************************/
    public Notion()
    {
        this.listeQCM= new ArrayList<QCM>();
        this.listeQCU= new ArrayList<QCU>();
        this.listeQO = new ArrayList<QO>();
    }
    public Notion(String nom)
    {
        this.listeQCM= new ArrayList<QCM>();
        this.listeQCU= new ArrayList<QCU>();
        this.listeQO = new ArrayList<QO>();
        this.nom= nom;
    }

    public Notion(String nom,ArrayList<QCM> a,ArrayList<QCU> b,ArrayList<QO> c)
    {
        this.listeQCM= a;
        this.listeQCU= b;
        this.listeQO = c;
        this.nom= nom;
    }


    //** Getters et setters********************************/
    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }
    public int getNbqst()
    {
        return (this.listeQCM.size()+this.listeQCU.size()+this.listeQO.size());
    }
    public int getnbqcm()
    { return listeQCM.size();}
    public int getnbqcu()
    { return listeQCU.size();}
    public int getnbqo()
    { return listeQO.size();}
    public ArrayList<QCM> getListeQCM() {
        return listeQCM;
    }

    public void setListeQCM(ArrayList<QCM> listeQCM) {
        this.listeQCM = listeQCM;
    }

    public ArrayList<QO> getListeQO() {
        return listeQO;
    }

    public void setListeQO(ArrayList<QO> listeQO) {
        this.listeQO = listeQO;
    }

    public ArrayList<QCU> getListeQCU() {
        return listeQCU;
    }

    public void setListeQCU(ArrayList<QCU> listeQCU) {
        this.listeQCU = listeQCU;
    }





    //** METHODES ***************************************/
    public void afficherQsts()
    {
        this.afficherQCMs();
        this.afficherQCUs();
        this.afficherQOs();
    }
    public void afficherQCMs()
    {
        System.out.println("La liste des questions de type QCM");
        System.out.println("----------------------------------");
        Iterator<QCM> it = listeQCM.iterator();
        int i=1;
        while(it.hasNext())
        {
            System.out.println("- QCM N°"+i);
            it.next().afficherQst();
            System.out.println("******");
            i++;
        }
    }
    public void afficherQOs()
    {
        System.out.println("La liste des questions de type QO");
        System.out.println("----------------------------------");
        Iterator<QO> it = listeQO.iterator();
        int i=1;
        while(it.hasNext())
        {
            System.out.println("- QO N°"+i);
            it.next().afficherQst();
            System.out.println("******");
            i++;
        }
    }
    public void afficherQCUs()
    {
        System.out.println("La liste des questions de type QCU");
        System.out.println("----------------------------------");
        Iterator<QCU> it = listeQCU.iterator();
        int i=1;
        while(it.hasNext())
        {
            System.out.println("- QCU N°"+i);

            it.next().afficherQst();
            System.out.println("******");
            i++;
        }
    }


    public void afficherNomNotion()
    {
        System.out.println("- "+this.nom);
    }

    public boolean equals(Notion n)
    {
        if (this.nom.equals(n.getNom()))
        {
            return true;
        }
        else {return  false;}
    }


    //////////////////////////////////////////////////////////////////////////////////
    public String ajouterEnonce()
    {
        Scanner sc = new Scanner(System.in);
        System.out.println("L'enoncé de la question");
        return sc.nextLine();
    }

    public ArrayList<String> ajouterPropositions()
    {
        Scanner sc = new Scanner(System.in);

        ArrayList<String> liste= new ArrayList<>();
        String var= " ";
        System.out.println("Veuillez taper les propostions une par une et terminer avec $");
        System.out.println("      (Vous devez entrer au moins une )");


        int i =0;
        while (!var.equals("$") || i==0 )
        {
            var=sc.nextLine();
            if ( !var.equals("$"))
            {
                liste.add(var);
                i++;
            }

        }
        return liste;
    }

    public ArrayList<String> ajouterPropositions2()
    {
        Scanner sc = new Scanner(System.in);

        ArrayList<String> liste= new ArrayList<>();
        String var= " ";
        System.out.println("Veuillez taper les propostions une par une et terminer avec $");


        int i =0;
        while (!var.equals("$")  )
        {
            var=sc.nextLine();
            if ( !var.equals("$"))
            {
                liste.add(var);
                i++;
            }

        }
        if(i==0)
        {
            System.out.println("Pas de propositions fausses ");
        }
        return liste;
    }


    //*********************************************************
    public void ajouterQCM()
    {
        String enonce = ajouterEnonce();
        Scanner sc = new Scanner(System.in);

        System.out.println("La liste des propostions justes");
        ArrayList<String> liste1=ajouterPropositions();
        System.out.println("Lea liste des fausses propostions  ");
        ArrayList<String> liste2 = new ArrayList<>();

            liste2=ajouterPropositions2();

        this.listeQCM.add( new QCM(enonce,liste1,liste2,this.nom));
    }

    public void ajouterQCU()
    {
        String enonce = ajouterEnonce();
        Scanner sc = new Scanner(System.in);
        System.out.println("La réponse juste : ");
        String juste = sc.nextLine();

        System.out.println("La liste des fausses propositions : ");
        ArrayList<String> liste1=ajouterPropositions();
        this.listeQCU.add( new QCU(enonce,juste,liste1,this.nom));

    }
    public void ajouterQO()
    {
        String enonce = ajouterEnonce();
        // les reponses
        System.out.println("La liste des mots qui representent la reponse : ");
        ArrayList<String> liste= ajouterPropositions();
        this.listeQO.add( new QO(enonce,liste,this.nom));
    }

    /**************************************************/
    public int rechercheQCM(QCM q)
    {
        int n=0, j=-1;
        Iterator<QCM> it= this.listeQCM.iterator();
        QCM qs;
        while( it.hasNext())
        {

            qs=it.next();
            if ( qs.equals(q))
            {
                j=n;
            }
            n++;
        }
        return j;

    }

    public void supprQCMs()
    {
        System.out.println("Suppression de QCMs :");
        System.out.println("---------------------");
        Scanner sc= new Scanner(System.in);
        ArrayList<QCM> qcmAsupprimer = new ArrayList<>();
        System.out.println("Veuillez taper un par un les index des questions a supprimer et terminer avec 9999");
        int i =0; // le nombre de questions supprimees
        int var =0;
        int inter=0;
        QCM question;
        while (var!=9999 )
        {
            var=sc.nextInt();
            if ( var !=9999 && var <this.listeQCM.size()+1)
            {
                qcmAsupprimer.add(this.listeQCM.get(var-1));
                inter=var;
                i++;
            }

        }
        Iterator<QCM> it= qcmAsupprimer.iterator();
        if ( i==0)
        {
            System.out.println("Vous n'allez supprimer aucune QCM ! ");
        }
        else
        {
            if ( i==1)
            {
                // on peut supprimer directement grace à l'index
                this.listeQCM.remove(inter-1);
            }
            else
            {
                while( it.hasNext())
                {
                   question=it.next();
                   // on doit trouver l'index de la question dans la liste
                    this.listeQCM.remove(rechercheQCM(question));
                }
            }
        }
    }



    /**************************************************/
    public int rechercheQCU(QCU q)
    {
        int n=0, j=-1;
        Iterator<QCU> it= this.listeQCU.iterator();
        QCU qs;
        while( it.hasNext())
        {

            qs=it.next();
            if ( qs.equals(q))
            {
                j=n;
            }
            n++;
        }
        return j;

    }

    public void supprQCUs()
    {
        System.out.println("Suppression de QCUs :");
        System.out.println("---------------------");
        Scanner sc= new Scanner(System.in);
        ArrayList<QCU> qcuAsupprimer = new ArrayList<>();
        System.out.println("Veuillez taper un par un les index des questions a supprimer et terminer avec 9999");
        int i =0; // le nombre de questions supprimees
        int var =0;
        int inter=0;
        QCU question;
        while (var!=9999 )
        {
            var=sc.nextInt();
            if ( var !=9999 && var <this.listeQCU.size()+1)
            {
                qcuAsupprimer.add(this.listeQCU.get(var-1));
                inter=var;
                i++;
            }

        }
        Iterator<QCU> it= qcuAsupprimer.iterator();
        if ( i==0)
        {
            System.out.println("Vous n'allez supprimer aucune QCU ! ");
        }
        else
        {
            if ( i==1)
            {
                // on peut supprimer directement grace à l'index
                this.listeQCU.remove(inter-1);
            }
            else
            {
                while( it.hasNext())
                {
                    question=it.next();
                    // on doit trouver l'index de la question dans la liste
                    this.listeQCU.remove(rechercheQCU(question));
                }
            }
        }
    }




    /**************************************************/
    public int rechercheQO(QO q)
    {
        int n=0, j=-1;
        Iterator<QO> it= this.listeQO.iterator();
        QO qs;
        while( it.hasNext())
        {

            qs=it.next();
            if ( qs.equals(q))
            {
                j=n;
            }
            n++;
        }
        return j;

    }

    public void supprQOs()
    {
        System.out.println("Suppression de Qos :");
        System.out.println("---------------------");
        Scanner sc= new Scanner(System.in);
        ArrayList<QO> qoAsupprimer = new ArrayList<>();
        System.out.println("Veuillez taper un par un les index des questions a supprimer et terminer avec 9999");
        int i =0; // le nombre de questions supprimees
        int var =0;
        int inter=0;
        QO question;
        while (var!=9999 )
        {
            var=sc.nextInt();
            if ( var !=9999 && var <this.listeQO.size()+1)
            {
                qoAsupprimer.add(this.listeQO.get(var-1));
                inter=var;
                i++;
            }

        }
        Iterator<QO> it= qoAsupprimer.iterator();
        if ( i==0)
        {
            System.out.println("Vous n'allez supprimer aucune QO ! ");
        }
        else
        {
            if ( i==1)
            {
                // on peut supprimer directement grace à l'index
                this.listeQO.remove(inter-1);
            }
            else
            {
                while( it.hasNext())
                {
                    question=it.next();
                    // on doit trouver l'index de la question dans la liste
                    this.listeQO.remove(rechercheQO(question));
                }
            }
        }
    }





    /************/
    /// POUR SELECTIONNER UNE QUESTION ALEATOIREMENT ( CREATION D UN QUIZ)
    public QCM genAleaQCM()
    {
        QCM question = new QCM();
        int indice= ((int) (Math.random()*10000))%this.listeQCM.size();
        Iterator<QCM> it = this.listeQCM.iterator();
        int i=0;
        while (it.hasNext())
        {
            if (i==indice)
            {
                question=it.next();
            }
        }
        return question;
    }

    public QCU genAleaQCU()
    {
        QCU question = new QCU();
        int indice= ((int) (Math.random()*10000))%this.listeQCU.size();
        Iterator<QCU> it = this.listeQCU.iterator();
        int i=0;
        while (it.hasNext())
        {
            if (i==indice)
            {
                question=it.next();
            }
        }
        return question;
    }

    public QO genAleaQO()
    {
        QO question = new QO();
        int indice= ((int) (Math.random()*10000))%this.listeQO.size();
        Iterator<QO> it = this.listeQO.iterator();
        int i=0;
        while (it.hasNext())
        {
            if (i==indice)
            {
                question=it.next();

            }
        }
        return question;
    }



    /*********************/
    public ArrayList<QCM> genAleaQCMs(int nb)
    {
        ArrayList<QCM> liste = new ArrayList<>();
        QCM q,q2;
        boolean existe ;
        int nombre=0;
        if (nb==this.listeQCM.size())
        {
            return this.listeQCM;
        }
        else
        {
            if (nb!=0)
            {
                while( nombre<nb)
                {
                    existe=true;
                    while(existe)
                    {
                        q=genAleaQCM();
                        // on verifie si q existe dans la liste
                        Iterator<QCM> it = liste.iterator();
                        existe=false;
                        while(it.hasNext() && !existe)
                        {
                            q2=it.next();
                            if(q.equals(q2))
                            {
                                existe=true;
                            }
                        }
                        if (!existe)
                        {
                            nombre++;
                            liste.add(q);
                        }
                    }

                }
            }

        }
        return liste;
    }

    /******************/
    public ArrayList<QCU> genAleaQCUs(int nb)
    {
        ArrayList<QCU> liste = new ArrayList<>();
        QCU q,q2;
        boolean existe ;
        int nombre=0;
        if (nb==this.listeQCU.size())
        {
            return this.listeQCU;
        }
        else
        {
            if( nb!=0)
            {
                while( nombre<nb)
                {
                    existe=true;
                    while(existe)
                    {
                        q=genAleaQCU();
                        // on verifie si q existe dans la liste
                        Iterator<QCU> it = liste.iterator();
                        existe=false;
                        while(it.hasNext() && !existe)
                        {
                            q2=it.next();
                            if(q.equals(q2))
                            {
                                existe=true;
                            }
                        }
                        if (!existe)
                        {
                            nombre++;
                            liste.add(q);
                        }
                    }

                }
            }

        }
        return liste;
    }

    /*************/
    public ArrayList<QO> genAleaQOs(int nb)
    {
        ArrayList<QO> liste = new ArrayList<>();
        QO q,q2;
        boolean existe ;
        int nombre=0;
        if (nb==this.listeQO.size())
        {
            return this.listeQO;
        }
        else
        {
            if (nb!=0)
            {
                while( nombre<nb)
                {
                    existe=true;
                    while(existe)
                    {
                        q=genAleaQO();
                        // on verifie si q existe dans la liste
                        Iterator<QO> it = liste.iterator();
                        existe=false;
                        while(it.hasNext() && !existe)
                        {
                            q2=it.next();
                            if(q.equals(q2))
                            {
                                existe=true;
                            }
                        }
                        if (!existe)
                        {
                            nombre++;
                            liste.add(q);
                        }
                    }
                }
            }
        }
        return liste;
    }






}
