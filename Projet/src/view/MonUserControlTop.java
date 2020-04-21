/**
 * Le package importe.
 */
package view;

/**
 * Les classes importees.
 */
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import java.io.IOException;

/**
 *  @author chanson chloe
 */

/**
 * La classe "MonUserControlTop" est associee au code fxml de "MonUserControlTop.fxml". Elle permet l'affichage de
 * de l'UserControl dans les fenetres qui l'utilisent.
 */
public class MonUserControlTop extends HBox {

    /**
     * Parametre FXML de "MonUserControlTop"
     * */
    @FXML
    private Label labelNomPage;


    /*--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------*/


    /**
     * Autres parametres de la fenetre "MonUserControlTop"
     * @param nomPage :
     * Object String qui est utilise dans l'UserControl, il permet d'afficher ou l'utilisateur se trouve dans l'application.
     * @param nomJeu :
     * Permet de definir de maniere immuable le nom du jeu.
     */
    private String nomPage;
    private String nomJeu ="Tarot africain : ";


    /*--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------*/


    /**
     * Constructeur de l'UserControl
     * @param nomPage :
     * Cette variable permet de savoir comment completer la variable "nomPage", il correspond au nom de la fenetre ou
     * l'UserControl est utilise.
     * @throws IOException qui signale un probleme lors de la formation de l'UserControl.
     */
    public MonUserControlTop(String nomPage) throws IOException {
        this.nomPage=nomPage;
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/MonUserControlTop.fxml"));
        loader.setController(this);
        loader.setRoot(this);
        loader.load();
    }


    /*--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------*/


    /**
     * Methode appelee lors de la construction de l'UserControl "MonUserControlTop".
     * Elle concat le nom de la fenetre de la variable "nomPage" a la variable "nomJeu".
     * puis, modifie "labelNomPage" avec la nouvelle variable "nomPage".
     */
    public void initialize() {
        /*La classe String est immuable. Pour rappel, une classe immuable est une classe dont les objets sont immuables.
         Un objet est immuable si une fois qu'il est créé, il est impossible de changer son état. Ainsi, une fois une
         chaîne de caractères créée, il est impossible de la modifier. Il n'y a aucune méthode mutateur (setString par
          exemple) dans la classe String. Donc, lorsque vous utilisez l'opérateur de concaténation avec des objets de
          type String, c'est à chaque fois des nouveaux objets qui sont créés. En conséquent avec la ligne de code
          suivante, je démontre bien de ma maîtrise la notion d’immuabilité de la classe String.
         */
        nomPage = nomJeu.concat(nomPage);
        labelNomPage.textProperty().setValue(nomPage);
    }
}
