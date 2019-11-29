import java.lang.reflect.Array;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;

public class Quiz {

    //**************** LES ATTRIBUTS ***************/

    private String nom;
    private LocalDate dateO; // date d'ouverture du quiz
    private LocalDate dateEX;// date de fermeture du quiz ( expiration)
    private ArrayList<Notion> notions ; // liste des notions qu'il couvre
    // les questions
    private ArrayList<QCM> qcm;
    private ArrayList<QO> qo;
    private ArrayList<QCU> qcu;

    public String getFormation() {
        return formation;
    }

    public void setFormation(String formation) {
        this.formation = formation;
    }

    private String formation;


    //******** CONSTRUCTEUR(S) **********************/
    public Quiz(){
        this.notions= new ArrayList<>();
        this.qcm= new ArrayList<>();
        this.qo= new ArrayList<>();
        this.qcu = new ArrayList<>();
    }
    public Quiz(String name, LocalDate dateex, LocalDate dateo, ArrayList<Notion> not, ArrayList<QCM> qcm, ArrayList<QO > qo, ArrayList<QCU> qcu)
    {
        this.nom=name;
        this.dateEX=dateex;
        this.dateO=dateo;
        this.qcm=qcm;
        this.qo=qo;
        this.qcu=qcu;
        this.notions=not;
    }

    public Quiz(String name, LocalDate dateex, LocalDate dateo, ArrayList<Notion> not)
    {
        this.nom=name;
        this.dateEX=dateex;
        this.dateO=dateo;

        this.notions=not;
        this.qcm= new ArrayList<>();
        this.qo= new ArrayList<>();
        this.qcu = new ArrayList<>();
    }





    ///******* Getters et setters  ************************************

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public LocalDate getDateO() {
        return dateO;
    }

    public void setDateO(LocalDate dateO) {
        this.dateO = dateO;
    }

    public LocalDate getDateEX() {
        return dateEX;
    }

    public void setDateEX(LocalDate dateEX) {
        this.dateEX = dateEX;
    }

    public ArrayList<Notion> getNotions() {
        return notions;
    }

    public void setNotions(ArrayList<Notion> notions) {
        this.notions = notions;
    }

    public ArrayList<QCM> getQcm() {
        return qcm;
    }

    public void setQcm(ArrayList<QCM> qcm) {
        this.qcm = qcm;
    }

    public ArrayList<QO> getQo() {
        return qo;
    }

    public void setQo(ArrayList<QO> qo) {
        this.qo = qo;
    }

    public ArrayList<QCU> getQcu() {
        return qcu;
    }

    public void setQcu(ArrayList<QCU> qcu) {
        this.qcu = qcu;
    }


    //****** METHODES ****************************/


    public void ajouterQst( int nb, Notion n)
    {
        int nombre= nb;
        // on verifie d'abord si nb n'est pas plus grand que le max de questions de la notion
        if ( nombre> n.getNbqst())
        {
            nombre=n.getNbqst();
            System.out.println(" grand , on a pris"+n.getNbqst());
        }
        /// on va répartir ce nombre sur le type de questions aleatoirement
        boolean erreur1= true, erreur2=true, erreur3=true;
        int nbqcm=0,nbqo=0,nbqcu=0;
        // nombre c le nb de qst:
        int nb1=n.getnbqcm(),nb2=n.getnbqcu(), nb3=n.getnbqo();
        int choix=0;
        while (nombre>0)
        {
            choix =(int)(Math.random()*100000000) % 3;
            if (choix==2)
            {
                if (nb1>0)
                {
                    nbqcm++;
                    nb1--;
                    nombre--;
                }
                else
                {
                    if (nb2>0)
                    {
                        nbqcu++;
                        nb2--;
                        nombre--;
                    }
                    else
                    {
                        if (nb3>0)
                        {
                            nbqo++;
                            nb3--;
                            nombre--;
                        }
                    }
                }
            }
            else
            {
                if (choix==1)
                {
                    if (nb2>0)
                    {
                        nbqcu++;
                        nb2--;
                        nombre--;
                    }
                    else
                    {
                        if (nb3>0)
                        {
                            nbqo++;
                            nb3--;
                            nombre--;
                        }
                        else
                        {
                            if (nb1>0)
                            {
                                nbqcm++;
                                nb1--;
                                nombre--;
                            }
                        }
                    }
                }
                else // choix =0
                {
                    if (nb3>0)
                    {
                        nbqo++;
                        nb3--;
                        nombre--;
                    }
                    else
                    {
                        if (nb1>0)
                        {
                            nbqcm++;
                            nb1--;
                            nombre--;
                        }
                        else
                        {
                            if (nb2>0)
                            {
                                nbqcu++;
                                nb2--;
                                nombre--;
                            }
                        }

                    }
                }
            }
        }
        ArrayList<QCM> qcm = n.genAleaQCMs(nbqcm);
        ArrayList<QCU> qcu = n.genAleaQCUs(nbqcu);
        ArrayList<QO> qo = n.genAleaQOs(nbqo);
        // on ajoute les questions aux listes
        Iterator<QCM> it=qcm.iterator();
        while( it.hasNext())
        {
            this.qcm.add(it.next());
        }
        Iterator<QCU> it2=qcu.iterator();
        while( it2.hasNext())
        {
            this.qcu.add(it2.next());
        }
        Iterator<QO> it3=qo.iterator();
        while( it3.hasNext())
        {
            this.qo.add(it3.next());
        }
    }
/*****************************************************************/

    public void afficherNomsNotions()


    {
        Iterator<Notion> it= notions.iterator();
        int i=0;
        Notion n;
        while (it.hasNext())
        {
            i++;
            System.out.println("Notion N ° "+i);
            System.out.println("------------");
            n=it.next();
            n.afficherNomNotion();
        }
    }
    public void afficherQsts()
    {
        this.afficherQCMs();
        this.afficherQCUs();
        this.afficherQOs();
    }
    public void afficherQCMs()
    {
        if (qcm.size()>0)
        {
            System.out.println("La liste des questions de type QCM");

            Iterator<QCM> it = qcm.iterator();
            int i=1;
            while(it.hasNext())
            {
                System.out.println("- QCM N°"+i);
                it.next().afficherQst();
                System.out.println("******");
                i++;
            }
        }

    }
    public void afficherQOs()
    {
        if (qo.size()>0)
        {
            System.out.println("La liste des questions de type QO");

            Iterator<QO> it = qo.iterator();
            int i=1;
            while(it.hasNext())
            {
                System.out.println("- QO N°"+i);
                it.next().afficherQst();
                System.out.println("******");
                i++;
            }
        }

    }
    public void afficherQCUs()
    {
        if (qcu.size()>0)
        {
            System.out.println("La liste des questions de type QCU");

            Iterator<QCU> it = qcu.iterator();
            int i=1;
            while(it.hasNext())
            {
                System.out.println("- QCU N°"+i);

                it.next().afficherQst();
                System.out.println("******");
                i++;
            }
        }

    }


    public void afficherQuiz()// affichage seulement
    {
        System.out.println("*** AFFICHAGE D'UN QUIZ ***");
        System.out.println("***-------------------- ***");
        System.out.println("Nom :"+this.nom);
        System.out.println("Date d'ouverture :"+this.dateO);
        System.out.println("Date de fermeture :"+this.dateEX);
        System.out.println("Les Notions que couvre le quiz:"+this.nom);
        afficherNomsNotions();
        System.out.println("Les Questions :");
        afficherQsts();
    }

/*****************************************************************************/
    public void ajouterQstalea() throws AjoutImpossibleException
    {
        Scanner sc= new Scanner(System.in);
        System.out.println("*** Ajout d'une Question ***");
        System.out.println("***----------------------***");
        System.out.println("Selection de la notion :");
        System.out.println("------------------");
        this.afficherNomsNotions();
        System.out.println("Nom de la notion :");
        String nom= sc.next();
        // on recupere la notion
        try{
            Notion n=getNotion(nom);
            if (nbQstNotion(nom)==n.getNbqst())
            {
                throw new AjoutImpossibleException();
            }
            else
            {
                int nb= (int)(Math.random()*100000000) % 3;
                if (nbQcmNotion(nom)<n.getnbqcm())
                {
                    // on ajoute une qcm
                    plusQcm(n);
                }
                else
                {
                    if( nbQoNotion(nom)<n.getnbqo())
                    {
                        // on ajoute une qo
                        plusQo(n);
                    }
                    else
                    {
                        // on ajoute un qcu
                        plusQcu(n);
                    }
                }
            }
        }
        catch(NotionInExistanteException e){}
    }




    public void ajouterQst() throws AjoutImpossibleException
    {
        Scanner sc= new Scanner(System.in);
        System.out.println("*** Ajout d'une Question ***");
        System.out.println("***----------------------***");
        System.out.println("Selection de la notion :");
        System.out.println("------------------");
        this.afficherNomsNotions();
        System.out.println("Nom de la notion :");
        String nom= sc.next();
        // on recupere la notion
        try{
            Notion n=getNotion(nom);
            if (nbQstNotion(nom)==n.getNbqst())
            {
                throw new AjoutImpossibleException();
            }
            else
            {
                // on va afficher les questions restantes et en choisir une
                int index =afficherQstRestantes(n);
                if ( index<=n.getnbqcm())
                {
                    this.qcm.add(n.getListeQCM().get(index-1));
                }
                else
                {
                    index-=n.getnbqcm();
                    if( index<=n.getnbqcu())
                    {
                        this.qcu.add(n.getListeQCU().get(index-1));
                    }
                    else
                    {
                        index-=n.getnbqcu();
                        this.qo.add(n.getListeQO().get(index-1));
                    }
                }
            }
        }
        catch(NotionInExistanteException e){}
    }




    public int nbQstNotion(String n)// calcule le nombre de questions par notion
    {
        return nbQcmNotion(n)+nbQoNotion(n)+nbQcuNotion(n);
    }

    public int nbQcmNotion(String n)
    {
        int i=0;
        Iterator<QCM> it = this.qcm.iterator();
        while(it.hasNext())
        {
            if ( it.next().getNotion().equals(n))
            {
                i++;
            }
        }
        return i;
    }

    public int nbQcuNotion(String n)
    {
        int i=0;
        Iterator<QCU> it = this.qcu.iterator();
        while(it.hasNext())
        {
            if ( it.next().getNotion().equals(n))
            {
                i++;
            }
        }
        return i;
    }
    public int nbQoNotion(String n)
    {
        int i=0;
        Iterator<QO> it = this.qo.iterator();
        while(it.hasNext())
        {
            if ( it.next().getNotion().equals(n))
            {
                i++;
            }
        }
        return i;
    }

    public Notion getNotion(String n) throws NotionInExistanteException
    {
        Iterator<Notion> it = notions.iterator();
        boolean existe=false;
        Notion not= new Notion(),n2;
        while( it.hasNext()&& !existe)
        {
            n2=it.next();
            if(n2.getNom().equals(n))
            {
                not=n2;
                existe=true;
            }
        }
        if(!existe)
        {
            throw new NotionInExistanteException();
        }
        return not;
    }

    /*************************************************************************/
    ////////////MODIFICATION D UN QUIZ **********************************/
    public void plusQcm(Notion n)
    {
        // le principe est simple :
        //on parcours la liste des qcm de cette notion, et si on tombe sur un qui n'est pas dans le quiz on l'ajoute
        ArrayList<QCM> liste = n.getListeQCM();
        Iterator<QCM> it = liste.iterator();
        QCM q = new QCM();
        boolean stop=false;
        while( it.hasNext())
        {
            q=it.next();
            if ( !qcmExiste(q))
            {
                stop=true;
                this.qcm.add(q);
            }
        }
    }
    public boolean qcmExiste(QCM q)
    {
        Iterator<QCM> it = qcm.iterator();
        boolean stop=false;
        while( it.hasNext()&& !stop)
        {
            if ( it.next().equals(q))
            {
                stop=true;
            }
        }
        return stop;

    }


    //**********************************/
    public void plusQcu(Notion n)
    {
        // le principe est simple :
        //on parcours la liste des qcm de cette notion, et si on tombe sur un qui n'est pas dans le quiz on l'ajoute
        ArrayList<QCU> liste = n.getListeQCU();
        Iterator<QCU> it = liste.iterator();
        QCU q = new QCU();
        boolean stop=false;
        while( it.hasNext())
        {
            q=it.next();
            if ( !qcuExiste(q))
            {
                stop=true;
                this.qcu.add(q);
            }
        }
    }
    public boolean qcuExiste(QCU q)
    {
        Iterator<QCU> it = qcu.iterator();
        boolean stop=false;
        while( it.hasNext()&& !stop)
        {
            if ( it.next().equals(q))
            {
                stop=true;
            }
        }
        return stop;

    }

    //**********************************/
    public void plusQo(Notion n)
    {
        // le principe est simple :
        //on parcours la liste des qcm de cette notion, et si on tombe sur un qui n'est pas dans le quiz on l'ajoute
        ArrayList<QO> liste = n.getListeQO();
        Iterator<QO> it = liste.iterator();
        QO q = new QO();
        boolean stop=false;
        while( it.hasNext())
        {
            q=it.next();
            if ( !qoExiste(q))
            {
                stop=true;
                this.qo.add(q);
            }
        }
    }
    public boolean qoExiste(QO q)
    {
        Iterator<QO> it = qo.iterator();
        boolean stop=false;
        while( it.hasNext()&& !stop)
        {
            if ( it.next().equals(q))
            {
                stop=true;
            }
        }
        return stop;

    }



    /***********************************************************/
    public void supprQst() throws OperationAnnuleeException
    {
        Scanner sc= new Scanner(System.in);
        System.out.println("*** Suppression d'une Question ***");
        System.out.println("***----------------------***");
        /// affichage de toutes les qst :
        afficherQuestions();
        System.out.println("Index de la question à supprimer :");
        int supr= sc.nextInt();
        System.out.println("Etes vous surs de vouloir supprimer cette question? ( 1 pour oui et 2 pour non)");
        int choix= sc.nextInt();
        if (choix==1)
        {
            if(supr<=qcm.size())
            {
                qcm.remove(supr-1);
            }
            else
            {
                if ( supr<=qcm.size()+qcu.size())
                {
                    qcu.remove(supr-1-qcm.size());
                }
                else
                {
                    qo.remove(supr-1-qcm.size()-qcu.size());
                }
            }
        }
        else
        {
            throw  new OperationAnnuleeException();
        }
    }
/**********************************************/
    public void afficherQuestions()
    {
        int i=1;
        Iterator<QCM> it1=qcm.iterator();
        Iterator<QCU> it2=qcu.iterator();
        Iterator<QO> it3= qo.iterator();
        while(it1.hasNext())
        {
            System.out.println("******Question "+i+": ");
            it1.next().afficherQst();
            i++;
        }
        while(it2.hasNext())
        {
            System.out.println("******Question "+i+": ");
            it2.next().afficherQst();
            i++;
        }
        while(it3.hasNext())
        {
            System.out.println("******Question "+i+": ");
            it3.next().afficherQst();
            i++;
        }
    }

    public int afficherQstRestantes(Notion n) // nous donne l'indice de la question selectionnee
    {
        Iterator<QCM> it1= n.getListeQCM().iterator();
        Iterator<QCU> it2=n.getListeQCU().iterator();
        Iterator<QO> it3=n.getListeQO().iterator();
        Scanner sc= new Scanner(System.in);
        ArrayList<QCM> liste1 = new ArrayList<>();
        ArrayList<QCU> liste2 = new ArrayList<>();
        ArrayList<QO> liste3 = new ArrayList<>();
        QCM q1;QCU q2; QO q3;
        int i=1;
        while(it1.hasNext())
        {

            q1=it1.next();
            if (!InQuiz(q1))
            {
                System.out.println("*** Question N"+i);
                q1.afficherQst();
                liste1.add(q1);
                i++;
            }
        }
        int j=i;
        while(it2.hasNext())
        {
            q2=it2.next();
            if (!InQuiz(q2))
            {
                System.out.println("*** Question N"+i);
                q2.afficherQst();
                liste2.add(q2);
                i++;
            }
        }
        int k=i-j;
        while(it3.hasNext())
        {
            q3=it3.next();
            if (!InQuiz(q3))
            {
                System.out.println("*** Question N"+i);
                q3.afficherQst();
                liste3.add(q3);
                i++;
            }
        }
        int w=i-j-k;
        System.out.println("Entrez l'indice de la question choisie:");
        int choix = sc.nextInt();
        int index=0;
        /// le type de l question
        if ( i<=j)
        {
            index=indexQst(liste1.get(i-1),n);
        }
        else
        {
            i=i-j;
            if (i<=k)
            {
                index=indexQst(liste2.get(i-1),n)+n.getnbqcm();
            }
            else
            {
                i=i-k;
                if ( i<=w)
                {
                    index=indexQst(liste3.get(i-1),n)+n.getnbqcm()+n.getnbqcu();
                }
            }
        }
        return index;

    }

    public boolean InQuiz (QCM q)
    {
        Iterator<QCM> it = this.qcm.iterator();
        boolean existe=false;
        while( it.hasNext()&& !existe)
        {
            if ( it.next().equals(q))
            {
                existe=true;
            }
        }

        return existe;
    }
    public boolean InQuiz (QCU q)
    {
        Iterator<QCU> it = this.qcu.iterator();
        boolean existe=false;
        while( it.hasNext()&& !existe)
        {
            if ( it.next().equals(q))
            {
                existe=true;
            }
        }

        return existe;
    }
    public boolean InQuiz (QO q)
    {
        Iterator<QO> it = this.qo.iterator();
        boolean existe=false;
        while( it.hasNext()&& !existe)
        {
            if ( it.next().equals(q))
            {
                existe=true;
            }
        }
        return existe;
    }

    /***********************************/
    public int indexQst(QCM q,Notion n)
    {
        Iterator<QCM> it = n.getListeQCM().iterator();
        int i=0,index=0;
        boolean existe=false;
        while( it.hasNext()&& !existe)
        {
            if ( it.next().equals(q))
            {
                existe=true;
                index=i;
            }
            i++;
        }
        return  index;
    }

    public int indexQst(QCU q,Notion n)
    {
        Iterator<QCU> it = n.getListeQCU().iterator();
        int i=0,index=0;
        boolean existe=false;
        while( it.hasNext()&& !existe)
        {
            if ( it.next().equals(q))
            {
                existe=true;
                index=i;
            }
            i++;
        }
        return  index;
    }

    public int indexQst(QO q,Notion n)
    {
        Iterator<QO> it = n.getListeQO().iterator();
        int i=0,index=0;
        boolean existe=false;
        while( it.hasNext()&& !existe)
        {
            if ( it.next().equals(q))
            {
                existe=true;
                index=i;
            }
            i++;
        }
        return  index;
    }


    /****************************************/
    /******* REMPLACER UNE QUESTION ******/
    public void remplacerQst() throws RemplacementImpossibleException,OperationAnnuleeException
    {
        Scanner sc= new Scanner(System.in);
        System.out.println("*** Remplacement d'une Question ***");
        System.out.println("***----------------------***");
        /// affichage de toutes les qst :
        afficherQuestions();
        System.out.println("Index de la question à Remplacer :");
        int supr= sc.nextInt();
        int choix=1;
        Notion n = new Notion();
        Question q = new QCM();
            /// on affche les autres
            int var=0;
            supr--;
            if(supr<=qcm.size())
            {

                n=rechercheNotion(qcm.get(supr).getNotion());
                var=1;
            }
            else
            {
                supr -=qcm.size();
                if ( supr<=qcu.size())
                {
                    n=rechercheNotion(qcu.get(supr).getNotion());
                    var=2;
                }
                else
                {
                    supr-=qcu.size();
                    n=rechercheNotion(qo.get(supr).getNotion());

                    var=3;
                }
            }
        if (nbQstNotion(n.getNom())==n.getNbqst())
        {
            throw new RemplacementImpossibleException();
        }
        else
        {
            choisirEtRemplacer(var,supr,n);
        }
    }


    private void choisirEtRemplacer(int i ,int j,Notion n) throws OperationAnnuleeException // i pour savoir si qcm qo ou qcu
    {
        // on va afficher les questions restantes et en choisir une

        System.out.println("Etes vous surs de vouloir supprimer cette question par une autre ? ( 1 pour oui et 2 pour non)");
        Scanner sc= new Scanner(System.in);
        int choix= sc.nextInt();
        if (choix==1)
        {
            int index =afficherQstRestantes(n);
            if ( i==1)
            {
                qcm.remove(j);
            }
            else
            {
                if ( i==2)
                {
                    qcu.remove(j);
                }
                else
                {
                    qo.remove(j);
                }
            }

            if ( index<=n.getnbqcm())
            {
                this.qcm.add(n.getListeQCM().get(index-1));
            }
            else
            {
                index-=n.getnbqcm();
                if( index<=n.getnbqcu())
                {
                    this.qcu.add(n.getListeQCU().get(index-1));
                }
                else
                {
                    index-=n.getnbqcu();
                    this.qo.add(n.getListeQO().get(index-1));
                }
            }
        }
        else
        {
            throw new OperationAnnuleeException();
        }



    }

    private Notion rechercheNotion(String n)
    {
        Iterator<Notion> it = this.notions.iterator();
        Notion no2= new Notion();
        while( it.hasNext())
        {
            Notion no=it.next();
            if(no.getNom().equals(n))
            {
                no2=no;
            }

        }
        return no2;
    }



}
