import java.util.*;
import java.time.LocalDate;

public class CompteFormateur extends Compte {

    public ArrayList<Formation> getListeFormations() {
        return listeFormations;
    }

    public void setListeFormations(ArrayList<Formation> listeFormations) {
        this.listeFormations = listeFormations;
    }

    //** ATTRIBUTS***************************************/
    private ArrayList<Formation> listeFormations;


    //** CONSTRUCTEUR(S)***************************************/


    public CompteFormateur(String id, String pwd, String nom, String prenom, LocalDate dateN, String adresse)
    {
        super(id,pwd,nom,prenom,dateN,adresse);
        this.listeFormations= new ArrayList<Formation>();
    }

    public CompteFormateur()
    {
        super();
        this.listeFormations= new ArrayList<Formation>();
    }



    //** METHODES ***************************************/
    public void modifierPWD( String ancien, String newpwd)
    {
        /// a revoir
        super.setPwd(newpwd);

    }

    public void ajouterFormation(Formation f)
    {
        this.listeFormations.add(f);
    }

    //***********************************
    public Formation rechercheFormation(String nom) throws FormationNonExistanteException
    {
        Boolean t= false;
        Iterator<Formation> it = this.listeFormations.iterator();
        Formation f;
        Formation f1= new Formation();
        while (it.hasNext())
        {
            f= it.next();
            if (f.getNom().equals(nom) )
            {
                t= true;
                f1=f;
            }
        }
        if ( !t)
        {
            throw new FormationNonExistanteException();
        }
        return f1;
    }

    //****************************************/





}
