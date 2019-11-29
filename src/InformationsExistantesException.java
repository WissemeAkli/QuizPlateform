public class InformationsExistantesException extends Exception {
    public InformationsExistantesException()
    {
        System.out.println("Ces informations coincident avec celles d'un autre compte deja existant !");
    }
}
