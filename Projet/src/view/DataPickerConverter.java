/**
 * Le package importe.
 */
package view;

/**
 * Les classes importees.
 */
import javafx.util.StringConverter;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 *  @author chanson chloe
 */

/**
 * La classe "DataPickerConverter" permet de changer la maniere d afficher la date.
 * Cette classe est utilisee par le "DataPicker" de la fenetre "PreliminaireJeu".
 */
public class DataPickerConverter extends StringConverter<LocalDate> {

    /**
     * @param dateFormatter :
     * corresponds a un formateur d'objets de type date. Cette variable permet de modifier la maniere d'afficher une date.
     * Nous decidons qu'une date soit affichee comme ceci : "dd-MM-yyyy".
     */
    private DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");

    /**
     * Methode qui renvoie la date sous le format que nous avons decider.
     * @param date : corresponds a la date que nous desirons transformer.
     * @return un String qui correspond a la date sous son nouveau format.
     */
    @Override
    public String toString(LocalDate date) {
        if (date != null) {
            return dateFormatter.format(date);
        }
        else {
            return "";
        }
    }

    /**
     * Methode qui analyse entierement le texte, puis produit un objet de type "LocalDate" associe.
     * @param string :corresponds au texte a analiser.
     * @return la date correspondant a la variable string passee en parametre.
     */
    @Override
    public LocalDate fromString(String string) {
        if (string != null && !string.isEmpty()) {
            return LocalDate.parse(string, dateFormatter);
        } else {
            return null;
        }
    }
}
