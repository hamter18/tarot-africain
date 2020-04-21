/**
 * Le package importe.
 */
package view;

/**
 * Les classes importees.
 */
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.VBox;
import modele.Joueur;
import modele.ListeJoueurs;
import modele.Manageur;
import java.time.LocalDate;

/**
 *  @author chanson chloe
 */

/**
 * La classe "PreliminaireJeu" est associee au code fxml de "PreliminaireJeu.fxml". Elle permet l'affichage de la
 * fenetre permettant a l'utilisateur d'entrer ses donnees.
 */
public class PreliminaireJeu {
    /**
     * Parametres FXML de la fenetre "PreliminaireJeu"
     */
    @FXML
    private TextField textAeraSurname;
    @FXML
    private DatePicker dateInscription;
    @FXML
    private Label labelJoueurExistant;
    @FXML
    private ToggleGroup radiosBoutonsPartie;
    @FXML
    private Label labelAucunRadioButtonSelected;
    @FXML
    private FlowPane flowPaneErreur1;
    @FXML
    private FlowPane flowPaneErreur2;

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
     * @param joueur :
     * Parametre qui  permet de recuperer le joueur qui est en train de jouer (cree ou recupere dans la fenetre "PreliminaireJeu").
     * @param typeDePartie :
     * Parametre qui permet de determiner le type de partie choisi par le joueur (reutilise dans d'autres fenetres).
     */
    @FXML
    private VBox controler;


    /*--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------*/


    private Manageur manageur;
    private String nomPage = "Préliminaire";
    private MethodeCommune methodeCommune;
    private Joueur joueur = new Joueur();
    private String typeDePartie;


    /*--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------*/


    /**
     * Constructeur de la classe.
     * @param manageur :
     * Permet de recuperer le manageur de la classe Launch du package launcher.
     */
    public PreliminaireJeu(Manageur manageur) {
        this.manageur=manageur;
        this.methodeCommune= new MethodeCommune(manageur);
    }

    /**
     * Constructeur de la classe.
     * @param manageur :
     * Permet de recuperer le manageur de la classe Launch du package launcher.
     * @param joueur
     * Permet de recuperer les donnees du joueur. Ce constructeur est utilise seulement si celui-ci a deja fait une
     * partie. Cela lui permet d aviter de "re-rentrer" ses donnees.
     */
    public PreliminaireJeu(Manageur manageur, Joueur joueur) {
        this.manageur=manageur;
        this.joueur=joueur;
        this.methodeCommune= new MethodeCommune(manageur);
    }


    /*--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------*/


    /**
     * Methode qui permet d'ouvrir une nouvelle fenetre, elle utilise une methode de la classe "MethodeCommune".
     * Elle permet au joueur de commencer a jouer. Cette methode creee la fenetre "Prevision".
     * Cette methode appelle aussi la methode "Joueur creationNouveauJoueur (Joueur joueur)" et
     * "String verificationRadioButton (ToggleGroup radiosBoutonsPartie)". C'est deux methodes permettent respectivement
     * de creer un nouveau voir et de verifier que l'utilisateur est bien selectionne un des deux "RadioButton".
     * @throws Exception qui signale un probleme lors de la formation de la nouvelle fenetre ou bien que
     * la valeur retournee par les "RadioButton" n'est pas valide (cela est cense jamais arriver).
     */
    @FXML
    private void clicPreliminaire() throws Exception{
        joueur=creationNouveauJoueur(joueur);
        if (joueur==null){
            return;
        }
        typeDePartie = verificationRadioButton(radiosBoutonsPartie);
        if(typeDePartie!=null){
            if(typeDePartie.compareTo("partieComplete")==0 || typeDePartie.compareTo("partieRapide")==0){
                methodeCommune.creationFenetrePrevision(controler, joueur, typeDePartie);
            }
            else {
                throw new Exception("Action non autorisée");
            }
        }
    }


    /**
     * Methode qui permet de "lire" la touche selectionne par l'utilisateur lorsqu'il ecrit son pseudo dans le "TextFiel"
     * "textAeraSurname". Ainsi si l'utilisateur touche sur la touche "Entrer" (pour valider son pseudo et lance une partie)
     * la methode "void clicPreliminaire()" est appelee. Cela permet de directement lancer la partie sans devoir cliquer
     * sur le boutton.
     * @param key :
     * Coorespond a la touche sur laquelle l'utilisateur a appuye.
     */
    @FXML
    private void keyValidation(KeyEvent key) {
        if (key.getCode() ==  KeyCode.ENTER) {
            try {
                clicPreliminaire();
            }
            catch (Exception e){
                e.printStackTrace();
                return;
            }
        }
    }


    /*--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------*/


    /**
     * Methode qui permet de verifier qu'un radioButton soit selectionne par l'utilisateur, ainsi tant que l'utilisateur
     * n'a pas selectionne un radioButon, il ne peut pas ouvrir une autre Stage.
     * Si aucun radioButton n'est selectionne, un message d'erreur est affiche.
     * @param radiosBoutonsPartie :
     * Parametre qui represente le groupe de radioButton
     * @return la valeur (id) du radioButton selectionne par l'utilisateur
     */
    private String verificationRadioButton (ToggleGroup radiosBoutonsPartie){
        RadioButton selectedRadioButton = (RadioButton) radiosBoutonsPartie.getSelectedToggle();
        if (selectedRadioButton==null){
            labelAucunRadioButtonSelected.textProperty().setValue("Aucun radio button de selectionnés");
            flowPaneErreur2.setVisible(true);
            return null;
        }
        return selectedRadioButton.getId();
    }

    /**
     * Methode qui cree un joueur si le pseudo du joueur n'existe pas deja dans la liste de joueur de la classe "ListeJoueurs".
     * Elle permet egalement de verifier qu'un joueur ne soit pas creer sans pseudo ("") ou que le pseudo du joueur est
     * seulement compose de caracteres non visible (tels que des espaces, des tabulations...) et ajoute le joueur a la
     * liste de joueur (si il n'existe pas deja).
     * @param joueur :
     * Correspond au joueur qui a ete cree dans cette fenetre.
     * @return le joueur qui joue ou la valeur nul si le pseudo entre n'est pas valide.
     */
    private Joueur creationNouveauJoueur (Joueur joueur){
        if(joueur==null){
            if(textAeraSurname.getText().trim().compareTo("")==0){
                flowPaneErreur1.setVisible(true);
                labelJoueurExistant.textProperty().setValue("Vous n'avez pas indiqué de pseudo.");
                return null;
            }
            joueur= new Joueur(textAeraSurname.getText(), dateInscription.getValue());
            manageur.ajouterUnJoueur(joueur);
            manageur.sauvegarderJoueur(new ListeJoueurs());
        }
        return joueur;
    }


    /**
     * Methode qui permet de verifier si le pseudo utiliser pour le joueur existe dans la liste de joueur de la classe
     * "ListeJoueurs". Si c'est le cas cette methode permet d'afficher un message dans le label "labelJoueurExistant"
     * indiquant a l'ulitisateur qui ce pseudo est deja existant. Elle permet egalement de mettre la date d'inscription
     * associee au pseudo du joueur dans le "DataPicker" "dateInscription" et de rendre "dateInscription" "disable"
     * (nous avons decide cela car une fois un joueur inscrit, il ne semble pas logique que celui puisse changer sa date
     * d'inscription).
     */
    private void joueurExixtant(){
        joueur =manageur.rechercherJoueur(textAeraSurname.getText());
        if (joueur!=null){
            labelJoueurExistant.textProperty().setValue("Le pseudo que vous voulez utiliser est déjà pris ! Êtes-vous cette personne ?");
            dateInscription.setDisable(true);
            dateInscription.setValue(joueur.getDateInscription());
            flowPaneErreur1.setVisible(true);
        }
    }

    /**
     * Methode qui permet d'enlever les divers elements associe aux messages d'erreurs dans la fenetre.
     */
    private void enleverMessageErreur(){
        flowPaneErreur2.setVisible(false);
        flowPaneErreur1.setVisible(false);
        labelJoueurExistant.getStyleClass().add("pasErreur");
        dateInscription.setDisable(false);
        labelJoueurExistant.textProperty().setValue("");
    }

    /*--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------*/

    /**
     * Methode appelee lors de la construction de la fenetre, elle permet d'instancier l'UserControl "MonUserControlTop".
     * Elle utilise une methode de la classe "MethodeCommune".
     * Dans le "DataPicker" elle permet aussi de mettre, par defaut la date d'incription a la date d'aujourd'hui, de
     * mettre invisible les numeros des semaines, d'appeller la classe "DataPickerConverter" afin de d'utiliser un
     * "Formatter" pour changer la maniere d'afficher la date, et de changer la maniere d'afficher les cellules du
     * "DataPicker" grace a la classe "DataPickerDateCell".
     *
     * Cette methode permet aussi de rechercher si le pseudo que le joueur veux utiliser existe ou non, et l'avertir si
     * c'est le cas,  grace a l'utilisation de la methode "void joueurExixtant()" et "void enleverMessageErreur()".
     * Pour cela nous faisons deux recherches, l'une des le depart sur le texte de base de la "TextFiel" (cela permet de
     * savoir si un joueur avec le pseudo "Nouveau joueur" existe), puis a chaque changement dans le "TextField".
     * Lorsque le pseudo du joueur est un  joueur qui exite.
     * Cette methode permet aussi d afficher le nom du joueur si ce dernier a deja fait une partie.
     */
    public void initialize() {
        methodeCommune.creationUserControlTop(controler, nomPage);
        if(joueur.getSurname().compareTo("")==0){
            textAeraSurname.setText("Nouveau joueur");
            dateInscription.setValue(joueur.getDateInscription());
        }
        else {
            textAeraSurname.setText(joueur.getSurname());
        }
        dateInscription.setValue(LocalDate.now());
        dateInscription.setShowWeekNumbers(false);
        dateInscription.setEditable(false);
        dateInscription.setConverter(new DataPickerConverter());
        dateInscription.setPromptText("dd-MM-yyyy");
        dateInscription.setDayCellFactory(__ -> new DataPickerDateCell());
        enleverMessageErreur();
        if(textAeraSurname.getText()!=null){
            joueurExixtant();
        }
        textAeraSurname.textProperty().addListener((observable, oldValue, newValue) -> {
            enleverMessageErreur();
            joueurExixtant();
        });
    }
}
