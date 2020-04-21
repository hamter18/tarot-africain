/**
 * Le package importe.
 */
package view;

/**
 * Les classes importees.
 */
import javafx.fxml.FXML;
import javafx.scene.layout.VBox;
import modele.Manageur;

/**
 *  @author chanson chloe
 */

/**
 * La classe "Accueil" est associee au code fxml de "Accueil.fxml". Elle permet l'affichage de la premiere fenetre du jeu.
 */
public class Accueil {
    /**
     * Parametres reutilises dans plusieurs Stages
     * Les attributs : controler, manageur et nomPage, sont present dans toutes les fenetres.
     * @param controler :
     * Objet graphique permettant d'afficher le contenu de l'UserControl
     * @param manageur :
     * Corresponds au manageur, c'est la seule personne qui a acces aux methodes des classes.
     * @param nomPage :
     * Object String qui est utilise dans l'UserControl, il permet d'afficher ou l'utilisateur se trouve dans l'application.
     * @param methodeCommune :
     * Parametre qui permet d'acceder à la classe "MethodeCommune" qui regroupe toutes les fonctions utilisees dans l'ensemble des vues.
     */
    @FXML
    private VBox controler;


    /*--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------*/


    private Manageur manageur;
    private String nomPage = "Accueil";
    private MethodeCommune methodeCommune;


    /*--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------*/


    /**
     * Constructeur de la classe.
     * @param manageur
     * Permet de recuperer le manageur de la classe Launch du package launcher.
     */
    public Accueil(Manageur manageur) {
        this.manageur=manageur;
        this.methodeCommune= new MethodeCommune(manageur);
    }


    /*--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------*/


    /**
     * Methode qui permet d'ouvrir une nouvelle fenetre, elle utilise une methode de la classe "MethodeCommune".
     * Elle permet au joueur de commencer a jouer. Cette methode creee la fenetre "PreliminaireJeu".
     * @throws Exception qui signale un probleme lors de la formation de la nouvelle fenetre.
     **/
    @FXML
    public void clicJouer() throws Exception {
        methodeCommune.creationFenetrePreliminaireJeu(controler);
    }

    /**
     * Methode qui permet d'ouvrir une nouvelle fenetre, elle utilise une methode de la classe "MethodeCommune".
     * Elle permet au joueur de regarder le tableau des scores du jeu. Cette methode creee la fenetre "TableauDesScores".
     * @throws Exception qui signale un probleme lors de la formation de la nouvelle fenetre.
     */
    @FXML
    public void clicTableauDesScores() throws Exception {
    methodeCommune.creationFenetreTableDesScore(controler);
    }

    /**
     * Methode qui permet de fermer cette fenetre et de quitter le jeu, elle utilise une methode de la classe
     * "MethodeCommune".
     */
    @FXML
    public void clicQuitter() {
        methodeCommune.fermerFenetre(controler);
    }


    /*--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------*/


    /**
     * Methode appelee lors de la construction de la fenetre, elle permet d'instancier l'UserControl "MonUserControlTop".
     * Elle utilise une methode de la classe "MethodeCommune".
     */
    public void initialize() {
        methodeCommune.creationUserControlTop(controler, nomPage);
    }
}
