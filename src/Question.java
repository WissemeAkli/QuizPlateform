import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;

public abstract class Question {


    //** ATTRIBUTS***************************************/
    private String enonce;

    public String getNotion() {
        return notion;
    }

    public void setNotion(String notion) {
        this.notion = notion;
    }

    private String notion;

    //** CONSTRUCTEUR(S)***************************************/

    public Question() { }

    public Question(String e,String n)
    {
        this.enonce=e; this.notion=n;
    }
    //** Getters et setters********************************/

    public String getEnonce() {
        return enonce;
    }

    public void setEnonce(String enonce) {
        this.enonce = enonce;
    }





    //** METHODES ***************************************/
    public void afficherQst()
    {
        System.out.println( "ENONCE : "+enonce+"   Notion :"+this.notion);
    }





}
