import java.util.ArrayList;
import java.util.Iterator;

public class ReponseQCM {

    private ArrayList<String> reponses;

    public ReponseQCM()
    {
        reponses=new ArrayList<>();
    }


    public void ajtReponse(String reponse)
    {
        if(!reponses.contains(reponse))
        {
            reponses.add(reponse);
        }
    }
    public ArrayList<String> getReponses() {
        return reponses;
    }

    public void afficherRep(){
        Iterator<String> itrepqcm = reponses.iterator();
        while(itrepqcm.hasNext())
        {
            System.out.println("Vos reponses sont");
            System.out.println(itrepqcm.next());

        }

    }

}
