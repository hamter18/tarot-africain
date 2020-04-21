/**
 * Le package importe.
 */
package view;

/**
 * Les classes importees.
 */
import javafx.scene.control.ListCell;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import modele.Carte;


/**
 *  @author chanson chloé
 */

/**
 * La classe "CarteCellFactory" permet de redefinir la maniere dont se forme les cellules des ListView. Cela a pour
 * objectif que les ListView affiche mes cartes.
 */
public class CarteCellFactory extends ListCell<Carte> {

    /**
     * L operation "UpdateItem" est utilisee pour modifier les proprietes d un element existant. Ici je modifie ces
     * proprietes afin d afficher une carte.
     * @param item : corresponds a l'objet dans la cellule de la ListView.
     * @param empty : corresponds a un boolean qui permet de savoir si une cellule et vide ou pas.
     */
    @Override
    protected void updateItem(Carte item, boolean empty) {
        super.updateItem(item, empty);
        if (!empty) {
            VBox contenant = new VBox();
            Image img=new Image(getClass().getResource(item.getImage()).toExternalForm());
            ImageView imageView = new ImageView(img);
            imageView.setFitHeight(170);
            imageView.setPreserveRatio(true);
            contenant.getChildren().add(imageView);
            this.setGraphic(contenant);
        }
        else {
            this.setGraphic(null);
        }
    }
}
