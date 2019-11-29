import java.time.LocalDate;
import java.util.*;

public class GroupeApprenants {

    //** ATTRIBUTS***************************************/

    private ArrayList<CompteApprenant> apprenants;


    //** CONSTRUCTEUR(S)***************************************/

    public GroupeApprenants()
    {
        this.apprenants = new ArrayList<CompteApprenant>();
    }
    public GroupeApprenants(ArrayList<CompteApprenant> apprenants)
    {
        this.apprenants= apprenants;
    }

    //** METHODES ***************************************/
   /* public void classerApprenants() throws GroupeVideException
    {
        LinkedList<CompteApprenant> newList = new LinkedList<CompteApprenant>();
        CompteApprenant m  =new CompteApprenant();
        int indice, i=0;
        int taille=apprenants.size();

        if (apprenants.size()==0) throw  new GroupeVideException();
        else
        {
            while (i<apprenants.size())
            {
                m=this.minMoyenne(apprenants);
                newList.add(m);
                apprenants.removeFirstOccurrence(m);
            }

        }
        apprenants=newList;

    }*/


    public CompteApprenant minMoyenne(LinkedList<CompteApprenant> a)throws GroupeVideException
    {
       double min=100000;
       CompteApprenant d,c = new CompteApprenant();
       Iterator<CompteApprenant> it= a.iterator();
        if (a.size()==0) throw  new GroupeVideException();
        else
        {
            while (it.hasNext())
            {
                d= it.next();
                if (d.getMoyenne()<=min)
                {
                    min=d.getMoyenne();
                    c=d;
                }
            }
            return c;
        }
    }


    /***********************/
    public void ajouterApprenant(CompteApprenant c) throws CompteDejaExistant
    {
      // on va rechercher C dans la liste des apprenants:
      // si il a le meme nom, prenom alors il existe deja !
        if (rechercheApprenant(c)){ throw new CompteDejaExistant();}
        else
        {
            this.apprenants.add(c);
        }
    }

    /******/
    // recherche d'un apprenant :
    public Boolean rechercheApprenant(CompteApprenant c)
    {
        Boolean existe= false;
        CompteApprenant d;
        Iterator<CompteApprenant> it= this.apprenants.iterator();
        while (it.hasNext() && !existe)
        {
            d= it.next();
            if (d.equals(c))
            {
                existe= true;
            }
        }
        return existe;
    }

    /*************/
    public void afficherApprenants()
    {
        Iterator<CompteApprenant> it= this.apprenants.iterator();
        int i=1;
        while ( it.hasNext())
        {
            System.out.println(" Apprenant "+i);
            it.next().afficherApprenant();
            i++;
        }
    }

    public CompteApprenant creerCompteApprenant(String n,String f)
    {
        CompteApprenant apprenant;
        Scanner sc = new Scanner(System.in);
        System.out.println("Veuillez entrer les informations suivantes ");

        System.out.println("Nom :");
        String nom = sc.nextLine();

        System.out.println("Prenom :");
        String prenom = sc.nextLine();

        System.out.println("Adresse :");
        String adresse = sc.nextLine();

        System.out.println("Date de naissance (AAAA-MM-JJ) :");

        String da = sc.nextLine();
        LocalDate d = LocalDate.parse(da);
        apprenant = new CompteApprenant(nom,prenom,d,adresse,n,f);
        return apprenant;

    }


    public String supprimerApprenant() throws CompteInexistantException
    {
        CompteApprenant apprenant;
        String identifiant;
        Scanner sc = new Scanner(System.in);
        System.out.println("Id de l'apprenant que vous voulez supprimer : ");
        String id = sc.nextLine();

        /// on va rechercher l'apprenant
        try

        {
            int indice= this.rechercheIdApprenant(id);
            // si, pas d'exceptions , ie : existe
            // on va le supprimer
            identifiant = this.apprenants.get(indice).getId();
            this.apprenants.remove(indice);
        }
        catch (CompteInexistantException e){ throw new CompteInexistantException();}
        return identifiant;

    }

    public int rechercheIdApprenant( String id) throws CompteInexistantException
    {
        int indice=-2;
        int i=0;
        CompteApprenant a ;
        Iterator<CompteApprenant> it= this.apprenants.iterator();
        while ( it.hasNext())
        {
            a= it.next();
            if ( a.getId().equals(id))
            {
                indice=i;
            }
            i++;
        }
        if ( indice == -2){ throw  new CompteInexistantException();}
        return indice;
    }


    public CompteApprenant modifierApprenant( String id) throws CompteInexistantException
    {
        /// on va d'abord afficher les infos de cet etudiant
        CompteApprenant apprenant;

        /// on va rechercher l'apprenant
        try
        {
            int indice= this.rechercheIdApprenant(id);
            // si, pas d'exceptions , ie : existe

             this.apprenants.get(indice).afficherApprenant();

             // Veuillez entrer les nouvelles informations de ce compte
             apprenant =this.creerCompteApprenant(this.apprenants.get(indice).getFormation(),this.apprenants.get(indice).getFormateur());
             this.apprenants.remove(indice);
        }
        catch (CompteInexistantException e){ throw new CompteInexistantException();}
        return apprenant;

    }

    public void remplacerApprenant( CompteApprenant ancien ,CompteApprenant nouveau) throws CompteInexistantException
    {
        try

        {
            int indice= this.rechercheIdApprenant(ancien.getId());
            // si, pas d'exceptions , ie : existe
            // on va le supprimer

            this.apprenants.remove(indice);
            this.apprenants.add(nouveau);
        }
        catch (CompteInexistantException e){ throw new CompteInexistantException();}
    }

    public void classementApprenant()
    {

        Collections.sort(apprenants,Collections.reverseOrder());
        afficherApprenants();

    }




    public CompteApprenant creerCompteApprenantInterface(String n,String f)
    {
        CompteApprenant apprenant;
        Scanner sc = new Scanner(System.in);
        System.out.println("Veuillez entrer les informations suivantes ");

        System.out.println("Nom :");
        String nom = sc.nextLine();

        System.out.println("Prenom :");
        String prenom = sc.nextLine();

        System.out.println("Adresse :");
        String adresse = sc.nextLine();

        System.out.println("Date de naissance (AAAA-MM-JJ) :");

        String da = sc.nextLine();
        LocalDate d = LocalDate.parse(da);
        apprenant = new CompteApprenant(nom,prenom,d,adresse,n,f);
        return apprenant;

    }




}

