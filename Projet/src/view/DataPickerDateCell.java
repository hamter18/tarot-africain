/**
 * Le package importe.
 */
package view;

/**
 * Les classes importees.
 */
import javafx.scene.control.DateCell;
import javafx.scene.control.Tooltip;
import java.time.LocalDate;

/**
 *  @author chanson chloe
 */

/**
 * La classe "DataPickerDateCell"  permet de redefinir la maniere dont se forme les cellules des "DataPicker". Cela a pour
 * ojectif que les date de mes "DataPicker" s'affichent differemment en fonction de plusieurs criteres.
 * Cette classe est utilisee par le "DataPicker" de la fenetre "PreliminaireJeu".
 */
public class DataPickerDateCell extends DateCell {

    /**
     * L operation "UpdateItem" est utilisee pour modifier les proprietes d un element existant. Ici je modifie ces
     * proprietes afin d'afficher les dates de differente maniere en fonction de plusieurs criteres.
     * Ainsi la date d'aujourd'hui apparaitra en orange. Les dates apres la date d'aujourd'hui (les jours que ne sont
     * pas encore passes) sont affiches en rouge et ne peuvent pas etre selectionnee. Les jours passes sont affiches
     * couleur lavende. Nous autorisons le joueur a pouvoir selectionne un jour passe.
     * @param item : corresponds a l'objet dans la cellule d'une "DataPicker".
     * @param empty : corresponds a un boolean qui permet de savoir si une cellule et vide ou pas.
     */
    @Override
    public void updateItem(LocalDate item, boolean empty) {
        super.updateItem(item, empty);
        if (item.equals(LocalDate.now())) {
            setTooltip(new Tooltip("Aujourd'hui !"));
            setStyle("-fx-background-color: orange;");
        }
        else {
            if (item.isAfter(LocalDate.now())) {
                setDisable(true);
                setStyle("-fx-background-color: #ffc0cb;");
            }
            else{
                setStyle("-fx-background-color: lavender;");
            }
        }
    }
}
