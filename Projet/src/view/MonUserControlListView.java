/**
 * Le package importe.
 */
package view;

/**
 * Les classes importees.
 */
import javafx.beans.binding.Bindings;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import modele.Joueur;
import modele.Manageur;
import modele.Manche;
import java.io.IOException;

/**
 *  @author chanson chloe
 */

/**
 * La classe "MonUserControlListView" est associee au code fxml de "MonUserControlListView.fxml". Elle permet l'affichage de
 * de l'UserControl dans les fenetres qui l'utilisent ("JeuRapide" et "JeuComplexe").
 */
public class MonUserControlListView extends HBox {

    /**
     * Paramètres FXML de "MonUserControlListView"
     * */
    @FXML
    private Label labelBindPresivionAdverse;
    @FXML
    private  Label labelBindPresivionJoueur;
    @FXML
    private Label labelBindPartieGagneeJoueur;
    @FXML
    private Label labelBindPartieGagneeAdverse;
    @FXML
    private Label labelSurname;
    @FXML
    private HBox logoJoueur;


    /*--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------*/


    /**
     * Parametres reutilises dans plusieurs Stages
     * @param manageur :
     * Corresponds au manageur, c'est la seule personne qui a acces aux methodes des classes.
     * @param joueur :
     * Parametre qui  permet de recuperer le joueur qui est en train de jouer (cree ou recupere dans la fenetre "PreliminaireJeu").
     * @param manche :
     * Parametre, qui correspond, a un raccourci pour acceder a la manche que le joueur est en train de jouer.
     */
    private Manageur manageur;
    private Joueur joueur;
    private Manche manche;


    /*--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------*/


    /**
     * Parametre utilise dans "MonUserControlListView"
     * @param nomJoueur :
     * Parametre qui permet de recuperer le nom du joueur qui est en train de jouer. Cela a pour but de le recuperer
     * afin de le transformer en StringProperty pour l'afficher dans mon UserControl. Faire de cette façon, a nottament
     * pour but de ne pas faire de la variable "surname" de la classe "Joueur" un StringProperty car cela rendrai la classe
     * joueur non serializable (or cette classe doit etre serializee).
     * */
    private String nomJoueur = new String();


    /*--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------*/


    /**
     * Contructeur de l'UserControl
     * @param manageur :
     * Permet de recuperer le manageur de la classe Launch du package launcher.
     * @param joueur :
     * Permet de recuperer le joueur qui est entrain de jouer (cree ou recupere dans la fenetre "PreliminaireJeu").
     * @param manche :
     * Permet de recuperer la partie que le joueur est en train de jouer.
     * @throws IOException qui signale un probleme lors de la formation de l'UserControl.
     */
    public MonUserControlListView(Manageur manageur, Manche manche, Joueur joueur) throws IOException {
        this.manche= manche;
        this.manageur=manageur;
        this.joueur=joueur;
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/MonUserControlListView.fxml"));
        loader.setController(this);
        loader.setRoot(this);
        loader.load();
    }


    /*--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------*/

    /**
     * Methode qui permet de tranformer un string en StringProperty afin de pouvoir afficher ce string dans une fentre.
     * @return le StringProperty a afficher dans l'UserControl
     */
    StringProperty surnameProperty() {return stringAConvertire();}

    /**
     * Methode qui permet de mettre la valeur du String (le nom du joueur) dans la variable "nomJoueur" de l'UserControl.
     * Cette methode utilise la fonction surnameProperty().
     * @return un StringProperty contenant le nom du joueur qui joue.
     */
    public StringProperty stringAConvertire (){
        nomJoueur= joueur.getSurname();
        return new SimpleStringProperty(nomJoueur);
    }


    /*--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------*/


    /**
     * Methode appelee lors de la construction de l'UserControl "MonUserControlListView",
     * Elle permet de binder les informations de la manche dans les labels associes
     * Elle permet aussi d'afficher le nom du joueur qui est entrain de jouer dans le label "labelSurname".
     */
    public void initialize() {
        labelBindPresivionJoueur.textProperty().bind(Bindings.convert(manche.previsionJoueurProperty()));
        labelBindPresivionAdverse.textProperty().bind(Bindings.convert(manche.previsionAdverseProperty()));
        labelBindPartieGagneeJoueur.textProperty().bind(Bindings.convert(manche.getTour().nbToursGagnesJoueurProperty()));
        labelBindPartieGagneeAdverse.textProperty().bind(Bindings.convert(manche.getTour().nbToursGagnesAdverseProperty()));
        labelSurname.textProperty().bind(surnameProperty());
    }
}
