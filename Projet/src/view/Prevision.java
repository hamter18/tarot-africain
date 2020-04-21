/**
 * Le package importe.
 */
package view;

/**
 * Les classes importees.
 */
import javafx.beans.binding.Bindings;
import javafx.beans.property.Property;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.util.converter.NumberStringConverter;
import modele.*;


/**
 *  @author chanson chloe
 */

/**
 * La classe "Prevision" est associee au code fxml de "Prevision.fxml". Elle permet l'affichage de la
 * fenetre affichant le tableau des scores.
 */
public class Prevision {
    /**
     * Parametres FXML de la fenetre "Prevision"
     */
    @FXML
    private Spinner<Integer> spinnerPrevision;
    @FXML
    public ListView<Carte> listDeCarte;
    @FXML
    private Label labelFieldDetailCarte;
    @FXML
    private Label labelnomSpecifique;
    @FXML
    private ImageView imageCarte1 ;
    @FXML
    private ImageView imageCarte2 ;
    @FXML
    private Label labelTextNomSpecifique;
    @FXML
    private Label labelValeur;
    @FXML
    private Label labelValeurText;


    /*--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------*/


    /**
     * Parametres reutilises dans plusieurs Stage
     * @param : controler
     * Objet graphique permettant d'afficher le contenu de l'UserControl
     * Les attributs : controler, manageur, joueur et nomPage, sont present dans toutes les fenetre
     * @param : manageur
     * Represente le manageur, la seule personne qui a acces a toutes les methodes.
     * @param : joueur
     * Parametre qui permet de creer un nouveau joueur ou bien de recuperer un joueur existant
     * @param : nomPage
     * Object String qui est utilise dans l'UserControl
     * @param : typeDePartie
     * Parametre qui permet de determiner le type de partie choisi par le joueur
     */


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
     * Parametre qui permet d'acceder a la classe "MethodeCommune" qui regroupe toutes les fonctions utilisees dans l'ensemble des vues.
     * @param typeDePartie :
     * Parametre qui permet de determiner le type de partie choisi par le joueur.
     * @param joueur :
     * Parametre qui  permet de recuperer le joueur qui est en train de jouer (cree ou recupere dans la fenetre "PreliminaireJeu").
     * @param  partie :
     * Parametre qui represente la partie que le joueur a commencer.
     * @param manche :
     * Parametre, qui correspond, a un raccourci pour acceder a la manche que le joueur est en train de jouer.
     */
    @FXML
    private VBox controler;


    /*--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------*/


    private Manageur manageur;
    private String nomPage = "Prévison";
    private MethodeCommune methodeCommune;
    private String typeDePartie;
    private Joueur joueur;
    private Partie partie;
    private Manche manche;


    /**
     * Autres parametres de la fenetre "Prevision"
     * @param textConseilleCarte :
     * Parametre qui correspond a la variable intermediaire permettant d afficher un texte qui permet de conseiller le
     * joueur lors de sa prevision.
     * @param nbCarte :
     * Nous declarons le nombre de carte maximun pour une partie.
     * Le nombre de cartes a ete choisi afin de realiser des parties relativement courtes et interessantes pour le
     * joueur.
     * @param nbJoueur :
     * Parametre qui nom permet de declarer le nombre de joueurs de la partie.
     */
    private String textConseilleCarte;
    private Integer nbCarte = 4;
    private Integer nbJoueur = 2;


    /*--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------*/

    /**
     * Constructeur de la classe.
     * @param manageur :
     * Permet de recuperer le manageur de la classe Launch du package launcher.
     * @param joueur :
     * Permet de recuperer le joueur qui a ete cree ou recupere dans la fenetre "PreliminaireJeu".
     * @param typeDePartie :
     * Permet de recuperer le type de partie choisie par le joueur dans la fenetre "PreliminaireJeu".
     */
    public Prevision(Manageur manageur, Joueur joueur, String typeDePartie) {
        this.joueur = joueur;
        this.manageur = manageur;
        this.typeDePartie=typeDePartie;
        this.methodeCommune= new MethodeCommune(manageur);
    }

    /**
     * Constructeur de la classe.
     * @param manageur :
     * Permet de recuperer le manageur de la classe Launch du package launcher.
     * @param joueur :
     * Permet de recuperer le joueur qui a ete cree ou recupere dans la fenetre "PreliminaireJeu".
     * @param typeDePartie :
     * Permet de recuperer le type de partie choisie par le joueur dans la fenetre "PreliminaireJeu".
     * @param partie :
     * Cette variable n'est utile que dans le cas d'une partie complexe, elle permet de recuperer la partie en cours
     * et en consequent la liste de manches.
     */
    public Prevision(Manageur manageur, Joueur joueur, String typeDePartie, Partie partie) {
        this(manageur,joueur, typeDePartie);
        this.partie=partie;
    }


    /*--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------*/


    /**
     * Methode qui permet d'ouvrir une nouvelle fenetre, elle utilise une methode de la classe "MethodeCommune".
     * Elle compare le "typeDePartie" afin de savoir qu'elle fen"tre "Jeu" ouvrir ("JeuRapide", ou "JeuComplexe").0
     * Nous "choisissons" une prevision pour je joueur adverse grace a la methode "choixPrevisionAdverse".
     * @throws Exception qui signale un probleme lors de la formation de la nouvelle fenetre ou bien que
     * la valeur retournee par les "RadioButton" n'est pas valide (cela est cense jamais arriver).
     */
    @FXML
    private void clicCommencerLeJeu() throws Exception {
        if(typeDePartie.compareTo("partieRapide")==0 | typeDePartie.compareTo("partieComplete")==0){
            if(typeDePartie.compareTo("partieRapide")==0){
                manche = manageur.choixPrevisionAdverse(manche);
                methodeCommune.creationFenetreJeuRapide(controler,partie,joueur);
            }
            else{
                nbCarte = partie.getListManche().size();
                manche = manageur.choixPrevisionAdverse(manche);
                methodeCommune.creationFenetreJeuComplexe(controler, partie, joueur, typeDePartie);
            }
        }
        else{
            throw new Exception("Action non autorisée");
        }
    }


    /*--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------*/


    /**
     * Methode appelee lors de la construction de la fenetre, elle permet d'instancier l'UserControl "MonUserControlTop".
     * Elle utilise une methode de la classe "MethodeCommune".
     *
     * Elle permet de gerer les differentes listes de cartes a afficher en fonction du typeDePartie (partieRapide,
     * partieComplexe).
     *
     * Dans le cas d'une partie dite rapide, une partie est creee, puis nous recuperons la prevision choisie par le joueur
     * grace au "Spinner" "spinnerPrevison".
     * Pour finir, nous modifions la manche avec les nouvelles "donnees" (previsions du joueur et du joueur adverse).
     *
     *
     * Dans le cas d'une partie dite complexe, nous verifions dans un premier temps si la partie existe, si ce n'est pas le cas
     * une nouvelle partie est creee. Ensuite la premiere manche de la "mancheList" de la partie est "chargee" dans la
     * "ListView" "listDeCarte".
     * Par la suite, tout comme pour une partie dite rapide, nous recuperons la prevision choisie par le joueur grace au
     * "Spinner" "spinnerPrevison".
     * Pour finir, nous modifions la premiere manche de la liste avec les nouvelles "donnees" (previsions du joueur et
     * du joueur adverse) (nous travaillons ici seulement sur la premiere manche de la "listDeCarte", car une fois que
     * la manche est "jouee", elle est supprimee, en consequant la premiere manche de la liste et toujours non joue).
     *
     * Cette methode permet aussi "surveiller" si un element de la "ListView" "listDeCarte" est selectionne. Si c'est le
     * cas un texte est affiche en dessous de la liste afin de conseiller le joueur pour sa previson.
     * Ce conseil est "module" en fonction de la carte selectionnee a l'aide de la fonction "String
     * probaDeGagner(Carte carte)" . De plus lorsqu'une carte est selectionnee son nom specifique mais aussi
     * l'image qui lui correspond son affiches dans la fenetre.
     *
     * Nous avons fait en sorte que le joueur adverse ou le joueur (au niveau du spinner) ne puisse pas choisir une
     * prevision dite impossible c'est-a-dire une previson superieure au nombre de cartes.
     */
    public void initialize() {
        methodeCommune.creationUserControlTop(controler, nomPage);
        if (typeDePartie.compareTo("partieRapide") == 0) {
            partie = manageur.creerPartieRapide(nbCarte,nbJoueur);
            manche = partie.getManche();
            spinnerPrevision.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(0, manche.getCarteListJoueur().size(), 0));
            spinnerPrevision.getValueFactory().valueProperty().bindBidirectional(partie.getManche().previsionJoueurProperty());
        } else {
            if (partie == null) {
                partie = manageur.creerPartieComplexe(nbCarte,nbJoueur);
            }
            manche = partie.getListManche().get(0);
            spinnerPrevision.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(0, manche.getCarteListJoueur().size(), 0));
            spinnerPrevision.getValueFactory().valueProperty().bindBidirectional(partie.getListManche().get(0).previsionJoueurProperty());
        }
        imageCarte1.setFitHeight(250);
        imageCarte1.setPreserveRatio(true);
        imageCarte2.setFitHeight(250);
        imageCarte2.setPreserveRatio(true);
        labelTextNomSpecifique.setVisible(false);
        labelValeurText.setVisible(false);
        listDeCarte.itemsProperty().bind(manche.carteListJoueurProperty());
        listDeCarte.setCellFactory(param -> new CarteCellFactory());
        listDeCarte.getSelectionModel().selectedItemProperty().addListener((__, ov, nv) -> {
            labelTextNomSpecifique.setVisible(true);
            labelValeurText.setVisible(true);
            if (ov != null) {
                labelnomSpecifique.textProperty().unbindBidirectional(ov.nomSpecifiqueProperty());
                labelValeur.textProperty().unbindBidirectional(ov.valeurProperty());
            }
            if (nv != null) {
                textConseilleCarte = manageur.probaDeGagner(nv);
                labelnomSpecifique.textProperty().bindBidirectional(nv.nomSpecifiqueProperty());
                labelValeur.textProperty().bindBidirectional(nv.valeurProperty(), new NumberStringConverter());
                Image img=new Image(getClass().getResource(nv.getImage()).toExternalForm());
                imageCarte1.setImage(img);
                imageCarte2.setImage(img);
                labelFieldDetailCarte.textProperty().bind(Bindings.format(textConseilleCarte + " %s", nv.nomCarteProperty()));
            }
            else {
                labelFieldDetailCarte.textProperty().bind(Bindings.format("" ));
            }
        });
    }
}
