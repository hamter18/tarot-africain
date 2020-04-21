/**
 * Le package importe.
 */
package launcher;

/**
 * Les classes importees.
 */
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import modele.Manageur;
import view.Accueil;
import view.MethodeCommune;

/**
 *  @author chanson chloe
 */

/**
 * La classe "Launch" permet de lancer l' application.
 */
public class Launch extends Application {

    /**
     * Parametre reutilise dans toutes les fenetres.
     * @param manageur :
     * Parametre qui permet de faire appelle aux methodes des differents objects utilises dans l' application.
     */
    private Manageur manageur = new Manageur();

    /**
     * @param methodeCommune :
     * Parametre qui permet d' acceder a la classe "MethodeCommune" qui regroupe toutes les fonctions utilisees dans
     * l' ensemble des vues.
     */
    private MethodeCommune methodeCommune = new MethodeCommune(manageur);



    /*--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------*/


    /**
     * Methode qui lance l' execution des traitements, elle ouvre la premiere fenetre de l' application.
     * Elle lit le fichier binaire afin de remplir la liste de joueurs.
     * @param stagePage :
     * Corresponds a la premiere fenetre de l' application.
     * @throws Exception qui signale un probleme lors de la formation de la nouvelle fenetre.
     */
    @Override
    public void start(Stage stagePage) throws Exception {
        manageur.LireFichierDeJoueur().getListJoueur();
        manageur.supprimerLesDoublons();
        methodeCommune.creationFenetreAccueil(manageur);
    }

    /**
     * Methode qui se trouve dans la classe principale, dans la racine de la pile d execution.
     * @param args :
     * Argument sous forme d' un tableau de chaine de caracteres. Ce tableau est le mecanisme pour
     * que le systeme passe les informations a l' application. Chaque "String" est une ligne de commande.
     */
    public static void main(String[] args) { launch(args);}



}

