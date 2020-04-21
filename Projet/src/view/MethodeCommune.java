/**
 * Le package importe.
 */
package view;

/**
 * Les classes importees.
 */
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import modele.Joueur;
import modele.Manageur;
import modele.Partie;
import java.io.IOException;

/**
 *  @author chanson chloe
 */

/**
 * La classe "MethodeCommune" regroupe l'ensemble des methodes qui sont utilisees dans l ensemble de l application.
 */
public class MethodeCommune {

    /**
     * @param manageur :
     * Corresponds au manageur, c'est la seule personne qui a acces aux methodes des classes.
     **/
    private Manageur manageur;


    /*--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------*/


    /**
     * Constructeur de la classe.
     * @param manageur
     * Permet de recuperer le manageur de la classe Launch du package launcher.
     */
    public MethodeCommune(Manageur manageur){
        this.manageur=manageur;
    }


    /*--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------*/

    /**
     * Methode qui permet de creer l "UserControlTop".
     * @param elmStage :
     * Corresponds a la structure qui contient l UserControl.
     * @param nomPage :
     * Object String qui est utilise dans l'UserControl, il permet d'afficher ou l'utilisateur se trouve dans l'application.
     */
    protected void creationUserControlTop(VBox elmStage, String nomPage){
        try {
            MonUserControlTop Uc = new MonUserControlTop(nomPage);
            elmStage.getChildren().add(Uc);
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }


    /*--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------*/


    /**
     * Methodes qui permettent de creer les nouvelles fenetres. Elles permettent a l'utilisateur de "naviguer" dans
     * l application.
     */


    /**************************************CREATION DE L'USERCONTROLTOP**************************************/


    /**
     * Methode qui permet de creer la fenetre "Accueil" de l'application. Elle fait appelle aux methodes "private
     * void creationStage(Parent root)" et "void closeStage (VBox elmStage)". C'est deux methodes permettent respectivement
     * de creer une nouvelle fenetre et de fermer la fenetre au se trouve actuellement l'utilisateur.
     * @param manageur
     * @throws IOException
     */
    public void creationFenetreAccueil(Manageur manageur) throws IOException {
        FXMLLoader leLoader = new FXMLLoader(getClass().getResource("/fxml/Accueil.fxml"));
        leLoader.setController(new Accueil(manageur));
        Parent root = leLoader.load();
        creationStage(root);
    }



    /**************************************CREATION DE LA FENETRE "PreliminaireJeu" **************************************/

    /**
     * Methode qui permet de creer la fenetre "PreliminaireJeu" de l'application. Elle fait appelle aux methodes "private
     * void creationStage(Parent root)" et "void closeStage (VBox elmStage)". C'est deux methodes permettent respectivement
     * de creer une nouvelle fenetre et de fermer la fenetre au se trouve actuellement l'utilisateur.
     * @param elmStage :
     * Corresponds a un element de la fenetre que nous desirons fermer.
     * @throws IOException
     */
    protected void creationFenetrePreliminaireJeu(VBox elmStage) throws IOException {
        FXMLLoader leLoader = new FXMLLoader(getClass().getResource("/fxml/PreliminaireJeu.fxml"));
        leLoader.setController(new PreliminaireJeu(manageur));
        Parent root = leLoader.load() ;
        creationStage(root);
        closeStage(elmStage);
    }

    /**
     * Methode qui permet de creer la fenetre "PreliminaireJeu" de l'application. Elle fait appelle aux methodes "private
     * void creationStage(Parent root)" et "void closeStage (VBox elmStage)". C'est deux methodes permettent respectivement
     * de creer une nouvelle fenetre et de fermer la fenetre au se trouve actuellement l'utilisateur.
     * @param elmStage :
     * Corresponds a un element de la fenetre que nous desirons fermer.
     * @param joueur
     * Permet de recuperer les donnees du joueur. Ce constructeur est utilise seulement si celui-ci a deja fait une
     * partie. Cela lui permet d aviter de "re-rentrer" ses donnees.
     * @throws IOException
     */
    protected void creationFenetrePreliminaireJeu(VBox elmStage, Joueur joueur) throws IOException {
        FXMLLoader leLoader = new FXMLLoader(getClass().getResource("/fxml/PreliminaireJeu.fxml"));
        leLoader.setController(new PreliminaireJeu(manageur, joueur));
        Parent root = leLoader.load() ;
        creationStage(root);
        closeStage(elmStage);
    }



    /**************************************CREATION DE LA FENETRE "Prevision" **************************************/


    /**
     * Methode qui permet de creer la fenetre "Prevision" de l'application. Elle fait appelle aux methodes "private
     * void creationStage(Parent root)" et "void closeStage (VBox elmStage)". C'est deux methodes permettent respectivement
     * de creer une nouvelle fenetre et de fermer la fenetre au se trouve actuellement l'utilisateur.
     * @param elmStage :
     * Corresponds a un element de la fenetre que nous desirons fermer.
     * @param joueur :
     * Permet de recuperer le joueur qui a ete cree ou recupere dans la fenetre "PreliminaireJeu".
     * @param typeDePartie :
     * Permet de recuperer le type de partie choisie par le joueur dans la fenetre "PreliminaireJeu".
     * @throws IOException
     */
    protected void creationFenetrePrevision(VBox elmStage, Joueur joueur, String typeDePartie) throws IOException {
        FXMLLoader leLoader = new FXMLLoader(getClass().getResource("/fxml/Prevision.fxml"));
        leLoader.setController(new Prevision(manageur, joueur, typeDePartie));
        Parent root = leLoader.load() ;
        creationStage(root);
        closeStage(elmStage);
    }


    /**
     * Methode qui permet de creer la fenetre "Prevision" de l'application. Elle fait appelle aux methodes "private
     * void creationStage(Parent root)" et "void closeStage (VBox elmStage)". C'est deux methodes permettent respectivement
     * de creer une nouvelle fenetre et de fermer la fenetre au se trouve actuellement l'utilisateur.
     * @param elmStage :
     * Corresponds a un element de la fenetre que nous desirons fermer.
     * @param joueur :
     * Permet de recuperer le joueur qui a ete cree ou recupere dans la fenetre "PreliminaireJeu".
     * @param typeDePartie :
     * Permet de recuperer le type de partie choisie par le joueur dans la fenetre "PreliminaireJeu".
     * @param partie :
     * Cette variable n'est utile que dans le cas d'une partie complexe, elle permet de recuperer la partie en cours
     * et en consequent la liste de manches.
     * @throws IOException
     */
    protected void creationFenetrePrevision(VBox elmStage, Joueur joueur, String typeDePartie, Partie partie) throws IOException {
        FXMLLoader leLoader = new FXMLLoader(getClass().getResource("/fxml/Prevision.fxml"));
        leLoader.setController(new Prevision(manageur, joueur, typeDePartie, partie));
        Parent root = leLoader.load() ;
        creationStage(root);
        closeStage(elmStage);
    }


    /**************************************CREATION DE LA FENETRE "TableauDesScores" **************************************/



    /**
     * Methode qui permet de creer la fenetre "TableauDesScores" de l'application. Elle fait appelle aux methodes "private
     * void creationStage(Parent root)" et "void closeStage (VBox elmStage)". C'est deux methodes permettent respectivement
     * de creer une nouvelle fenetre et de fermer la fenetre au se trouve actuellement l'utilisateur.
     * @param elmStage :
     * Corresponds a un element de la fenetre que nous desirons fermer.
     * @throws IOException qui signale un probleme lors de la formation de la nouvelle fenetre.
     */
    protected void creationFenetreTableDesScore( VBox elmStage) throws IOException {
        FXMLLoader leLoader = new FXMLLoader(getClass().getResource("/fxml/TableauDesScores.fxml"));
        leLoader.setController(new TableauDesScores(manageur));
        Parent root = leLoader.load() ;
        creationStage(root);
        closeStage(elmStage);
    }

    /**
     * Methode qui permet de creer la fenetre "TableauDesScores" de l'application. Elle fait appelle aux methodes "private
     * void creationStage(Parent root)" et "void closeStage (VBox elmStage)". C'est deux methodes permettent respectivement
     * de creer une nouvelle fenetre et de fermer la fenetre au se trouve actuellement l'utilisateur.
     * @param elmStage :
     * Corresponds a un element de la fenetre que nous desirons fermer.
     * @param joueur :
     * Permet de recuperer le joueur qui est entrain de jouer (soit il est cree dans le cas ou un utilisateur accede
     * au tableau des scores depuis la page d'accueil ou de recupere qui vient de jouer la partie)
     * @throws IOException qui signale un probleme lors de la formation de la nouvelle fenetre.
     */
    protected void creationFenetreTableDesScore( VBox elmStage, Joueur joueur) throws IOException {
        FXMLLoader leLoader = new FXMLLoader(getClass().getResource("/fxml/TableauDesScores.fxml"));
        leLoader.setController(new TableauDesScores(manageur, joueur));
        Parent root = leLoader.load() ;
        creationStage(root);
        closeStage(elmStage);
    }

    /**************************************CREATION DE LA FENETRE "JeuRapide" **************************************/



    /**
     * Methode qui permet de creer la fenetre "JeuRapide" de l'application. Elle fait appelle aux methodes "private
     * void creationStage(Parent root)" et "void closeStage (VBox elmStage)". C'est deux methodes permettent respectivement
     * de creer une nouvelle fenetre et de fermer la fenetre au se trouve actuellement l'utilisateur.
     * @param elmStage :
     * Corresponds a un element de la fenetre que nous desirons fermer.
     * @param partie
     * @param joueur
     * @throws IOException
     */
    protected void creationFenetreJeuRapide(VBox elmStage, Partie partie, Joueur joueur) throws IOException {
        FXMLLoader leLoader = new FXMLLoader(getClass().getResource("/fxml/JeuRapide.fxml"));
        leLoader.setController(new JeuRapide(manageur, partie, joueur));
        Parent root = leLoader.load();
        creationStage(root);
        closeStage(elmStage);
    }


    /**************************************CREATION DE LA FENETRE "JeuComplexe" **************************************/


    /**
     * Methode qui permet de creer la fenetre "JeuComplexe" de l'application. Elle fait appelle aux methodes "private
     * void creationStage(Parent root)" et "void closeStage (VBox elmStage)". C'est deux methodes permettent respectivement
     * de creer une nouvelle fenetre et de fermer la fenetre au se trouve actuellement l'utilisateur.
     * @param elmStage :
     * Corresponds a un element de la fenetre que nous desirons fermer.
     * @param partie :
     * Cette variable permet de recuperer la partie en cours et en consequent la liste de manches.
     * @param joueur :
     * Permet de recuperer le joueur qui est entrain de jouer (cree ou recupere dans la fenetre "PreliminaireJeu").
     * @param typeDePartie :
     * Cette variable qui permet de determiner le type de partie choisi par le joueur.
     * @throws IOException
     */
    protected void creationFenetreJeuComplexe(VBox elmStage, Partie partie, Joueur joueur, String typeDePartie) throws IOException {
        FXMLLoader leLoader = new FXMLLoader(getClass().getResource("/fxml/JeuComplexe.fxml"));
        leLoader.setController(new JeuComplexe(manageur, partie, joueur, typeDePartie));
        Parent root = leLoader.load();
        creationStage(root);
        closeStage(elmStage);
    }


    /*--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------*/


    /**
     * Methode qui appelle la methode "void closeStage (VBox elmStage)". Elle permet de fermer la fenetre sur laquelle
     * se trouve l'utilisateur.
     * @param elmStage
     */
    protected void fermerFenetre(VBox elmStage) {
        closeStage(elmStage);
    }


    /*--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------*/


    /**
     * Methode qui permet de fermer la fenetre sur laquelle se trouve l'utilisateur.
     * @param elmStage :
     * Corresponds a un element de la fenetre. Cette element nous permet de fermer cette derniere.
     * J utilise la Vbox qui me sert a afficher l "UserControlTop" car cet element est reprit dans toutes mes fenetres.
     */
    private void closeStage (VBox elmStage){
        Stage stage = (Stage) elmStage.getScene().getWindow();
        stage.close();
    }


    /*--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------*/

    /**
     * Methode qui permet de creer une fenetre. Comme nous utilisons la meme feuille de style pour toutes les fenetres,
     * nous avons pris la decision de l'integrer ici. De plus nous importons aussi les differentes "fonts" que nous
     * utilisons dans le projet. Les differents styles d'ecritures proviennent du site "Google Font".
     * @param root :
     * Correspond a ce que doit contenir la fenetre.
     */
    private void creationStage(Parent root){
        Stage stage= new Stage();
        Scene scene = new Scene(root);
        scene.getStylesheets().add(getClass().getResource("/css/application.css").toExternalForm());
        scene.getStylesheets().add("https://fonts.googleapis.com/css?family=Orbitron&display=swap");
        scene.getStylesheets().add("https://fonts.googleapis.com/css?family=Merienda+One&display=swap");
        scene.getStylesheets().add("https://fonts.googleapis.com/css?family=Ultra&display=swap");
        scene.getStylesheets().add("https://fonts.googleapis.com/css?family=Itim&display=swap");
        stage.setScene(scene);
        stage.setTitle("Tarot Africain");
        stage.show();
    }

}
