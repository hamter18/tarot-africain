/**
 * Le package importe.
 */
package view;

/**
 * Les classes importees.
 */
import java.util.List;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import modele.*;

/**
 *  @author chanson chloe
 */

/**
 * La classe "JeuRapide" est associee au code fxml de "JeuRapide.fxml". Elle permet l'affichage de la fenetre du
 * permettant de jouer un jeu.
 */
public class JeuRapide {
    /**
     * Paramètres FXML de la fenêtre "JeuRapide"
     */
    @FXML
    private ListView listDeCarte;
    @FXML
    private VBox controlerJeu;


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
     * @param joueur :
     * Parametre qui  permet de recuperer le joueur qui est en train de jouer (cree ou recupere dans la fenetre "PréliminaireJeu").
     * @param  partie :
     * Parametre qui represente la partie que le joueur a commencer.
     * @param manche :
     * Parametre, qui correspond, a un raccourci pour acceder a la manche que le joueur est en train de jouer.
     */

    @FXML
    private VBox controler;



    /*--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------*/


    private Manageur manageur;
    private String nomPage = "Jeu";
    private MethodeCommune methodeCommune;
    private Joueur joueur;
    private Partie partie;
    private Manche manche;

    /**
     * Autres parametres de la fenetre "JeuRapide" :
     * @param listCarteJoueur :
     * Parametre qui permet de recuperer la liste de cartes du joueur de la partie que le joueur est en train de jouer.
     * @param listCarteAdverse :
     * Parametre qui permet de recuperer la liste de cartes du joueur adverse de la partie que le joueur est en train
     * de jouer.
     * @param carteJoueur :
     * Parametre qui permet de recuperer la carte selectionnee que le joueur vient de selectionne.
     * @param methodeJeu :
     * Parametre qui permet d'acceder à la classe "MethodeJeu" qui regroupe toutes les fonctions utilisees dans
     * l'ensemble des vues de jeu ("JeuComplexe" et "JeuRapide").
     **/
    private ObservableList<Carte> listCarteJoueur;
    private List<Carte> listCarteAdverse;
    private Carte carteJoueur;
    private MethodeJeu methodeJeu;



    /*--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------*/

    /**
     * Constructeur de la classe.
     * @param manageur :
     * Permet de recuperer le manageur de la classe Launch du package launcher.
     * @param joueur :
     * Permet de recuperer le joueur qui est entrain de jouer (cree ou recupere dans la fenetre "PreliminaireJeu").
     * @param partie :
     * Cette variable permet de recuperer la partie en cours et en consequent la liste de manches.
     */
    public JeuRapide(Manageur manageur, Partie partie, Joueur joueur){
        this.joueur = joueur;
        this.manageur = manageur;
        this.partie = partie;
        this.manche = partie.getManche();
        this.methodeCommune = new MethodeCommune(manageur);
        this.methodeJeu = new MethodeJeu(manageur);
    }


    /*--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------*/


    /**
     * Methode qui permet d'ouvrir une nouvelle fenetre une fois que la liste est vide, elle utilise une methode de
     * la classe "MethodeCommune".
     * Dans un premier temps, tant que la liste n'est pas vide, a chaque clique sur la ListView la fonction
     * determinerCarteSelectionnee() est appelee. Si "carteJoueur" est null (est donc que le joueur n'a pas selectionnee
     * de carte) la méthode return rien. Dans le cas contraire la variable "carteJoueur" prend la valeur de la carte
     * selectionnee par le joueur.
     * Ensuite la fonction appelle la methode deroulementJeu(List<Carte> listCarteAdverse, Carte carteJoueur, Manche manche)
     * de la classe "MethodeJeu". Tant que la liste de carte du joueur et la liste de carte du joueur adverse n'est pas
     * vide, la meme "routine" est realisee.
     * Si ces deux listes sont vides alors la fenetre "TableauDesScores" sera creee. Cette fenetre est cree
     * grace une methode de la classe "MethodeCommune".
     * @throws Exception qui signale un probleme lors de la formation de la nouvelle fenetre.
     */
    @FXML
    private void carteChoix() throws Exception{
        carteJoueur = determinerCarteSelectionnee();
        if (carteJoueur==null){
            return;
        }
        methodeJeu.deroulementJeu(listCarteAdverse, carteJoueur, manche);
        if(listCarteAdverse.size()==0 && listCarteJoueur.size()==0){
            methodeJeu.affichageFinDePartieRapide(joueur, manche, partie);
            methodeCommune.creationFenetreTableDesScore(controler, joueur);
        }
    }


    /*--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------*/


    /**
     * Methode qui determine la carte selectionnee par le joueur dans la ListView. Elle verifie que l'element
     * selectionne par le joueur n'est pas nul, tant que celui-ci est nul, nous attendons que celui-ci ne le soit plus.
     * @return la carte selectionnee par le joueur.
     */
    private Carte determinerCarteSelectionnee(){
        Object objetSelectionne = listDeCarte.getSelectionModel().getSelectedItem();
        listDeCarte.getSelectionModel().clearSelection();
        if(objetSelectionne==null){
            return null;
        }
        return  (Carte) objetSelectionne;
    }


    /*--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------*/


    /**
     * Methode appelee lors de la construction de la fenetre, elle permet d'instancier l'UserControl "MonUserControlTop"
     * et l'UserControl " MonUserControlListView".Pour cela, elle utilise une methode de la classe "MethodeCommune" et
     * de la classe "MethodeJeu".
     *
     * Elle permet aussi de remplir des le depart les variables "listCarteJoueur" et "listCarteAdverse" qui sont utilisees
     * dans cette classe.
     * Elle permet aussi d'afficher la liste de carte du joueur dans la ListView "listDeCarte" dans la fenetre.
     **/
    public void initialize() {
        methodeCommune.creationUserControlTop(controler, nomPage);
        methodeJeu.creationUserControlListView(controlerJeu, manche, joueur);
        listCarteJoueur = manche.getCarteListJoueur();
        listCarteAdverse = manche.getListCarteAdverse();
        listDeCarte.itemsProperty().bind(manche.carteListJoueurProperty());
        listDeCarte.setCellFactory(param -> new CarteCellFactory());
    }
}