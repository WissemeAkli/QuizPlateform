import java.awt.*;
import java.time.LocalDate;
import java.util.Date;
import java.util.Scanner;

public abstract class Compte  {
    //** ATTRIBUTS***************************************/
    private String id; // identifiant
    private String pwd;// mot de passe
    private String nom;
    private String prenom;
    private LocalDate dateNaissance;
    private String adresse;




    //** CONSTRUCTEUR(S)***************************************/
    public Compte(String id, String pwd, String nom, String prenom, LocalDate dateN, String adresse)
    {
        this.id=id;
        this.pwd=pwd;
        this.nom= nom;
        this.prenom=prenom;
        this.dateNaissance=dateN;
        this.adresse=adresse;
    }

    public Compte( String nom, String prenom, LocalDate dateN, String adresse)
    {

        this.nom= nom;
        this.prenom=prenom;
        this.dateNaissance=dateN;
        this.adresse=adresse;
    }


    public Compte()
    {

    }



    //** METHODES ******************************************************/
    public void modifierPWD( String newpwd)
    {
        this.pwd=newpwd;
    }
    public void modifierPWD() throws  PwdErroneException
    {
        Scanner sc = new Scanner(System.in);
        Boolean erreur = false;
        System.out.println("Veuillez entrer votre ancien mot de passe svp: ");
        String pwd = sc.nextLine();
        // on verifie
        if ( pwd.equals(this.pwd))
        {

            while (!erreur)
            {
                // on peut modifier
                System.out.println("Nouveau mot de passe : ");
                String p1= sc.nextLine();
                System.out.println("Confirmation mot de passe : ");
                String p2= sc.nextLine();
                if ( p1.equals(p2))
                {
                    /// on modifie direct
                    this.modifierPWD(p1);
                    erreur = true;
                }
                else
                {
                    System.out.println("Erreur ! réessayez !");
                }
            }
        }
        else
        {
            throw  new PwdErroneException();
        }
    }







    /*****************************/


    public void modifierAdresse( String adresse)
    {
        this.adresse=adresse;
    }
    public void modifierAdresse() throws  PwdErroneException
    {
        Scanner sc = new Scanner(System.in);

        System.out.println("Veuillez entrer votre  mot de passe svp: ");
        String pwd = sc.nextLine();
        // on verifie
        if ( pwd.equals(this.pwd))
        {

                // on peut modifier
                System.out.println("Ancienne Adresse :"+this.adresse);
                System.out.println("Nouvelle Adresse ");
                String p1= sc.nextLine();
                modifierAdresse(p1);
        }
        else
        {
            throw  new PwdErroneException();
        }
    }


    /***************************************************/

    public void modifierNom( String adresse)
    {
        this.nom=adresse;
    }
    public void modifierNom() throws  PwdErroneException
    {
        Scanner sc = new Scanner(System.in);

        System.out.println("Veuillez entrer votre  mot de passe svp: ");
        String pwd = sc.nextLine();
        // on verifie
        if ( pwd.equals(this.pwd))
        {

            // on peut modifier
            System.out.println("Ancien Nom : "+this.nom);
            System.out.println("Nouveau Nom : ");
            String p1= sc.nextLine();
            modifierNom(p1);
        }
        else
        {
            throw  new PwdErroneException();
        }
    }


/**************************************************************/
    public void modifierPrenom( String adresse)
    {
        this.prenom=adresse;
    }
    public void modifierPrenom() throws  PwdErroneException
    {
        Scanner sc = new Scanner(System.in);

        System.out.println("Veuillez entrer votre  mot de passe svp: ");
        String pwd = sc.nextLine();
        // on verifie
        if ( pwd.equals(this.pwd))
        {
            // on peut modifier
            System.out.println("Ancien Prenom : "+this.prenom);
            System.out.println("Nouveau Prenom : ");
            String p1= sc.nextLine();
            modifierPrenom(p1);
        }
        else
        {
            throw  new PwdErroneException();
        }
    }


    public void afficherInfos()
    {
        System.out.println("********Informations du Compte********");
        System.out.println("Nom: "+this.nom+"\nPrenom: "+this.prenom+" \nDate de naissance: "+this.dateNaissance+"   \nId: "+this.id+"\nAdresse: "+this.adresse);
        System.out.println("**************************************");
    }




    /** GETTERS SETTERS**********************************/
    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }
/////////////////////////

    public LocalDate getDateNaissance() {
        return dateNaissance;
    }

    public void setDateNaissance(LocalDate dateNaissance) {
        this.dateNaissance = dateNaissance;
    }
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

}
