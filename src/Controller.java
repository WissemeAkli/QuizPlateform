import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

public class Controller {

     protected static App  a ;

     public void  setApp( App ap)
     {
         a=ap;
     }

     public App getA( )
     {
         return a;
     }


     public void showError(String message)
     {

         Alert alert = new Alert(Alert.AlertType.ERROR);
         alert.setTitle("Erreur ! ");
         alert.setHeaderText("ERREUR !");
         alert.setContentText(message);
         alert.showAndWait().ifPresent(rs -> {
             if (rs == ButtonType.OK) {

             }
         });
     }


    public void showMessage(String message)
    {

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Opération effectuée ");
        alert.setHeaderText("Opération effectuée avec succès");
        alert.setContentText(message);
        alert.showAndWait().ifPresent(rs -> {
            if (rs == ButtonType.OK) {

            }
        });
    }
}
