/**
 * Le package importe.
 */
package view;

/**
 * Les classes importees.
 */
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.*;
import modele.*;
import java.util.Optional;

/**
 *  @author chanson chloe
 */

/**
 * La classe "TableauDesScores" est associee au code fxml de "TableauDesScores.fxml". Elle permet l'affichage de la
 * fenetre affichant le tableau des scores.
 */
public class TableauDesScores {
    /**
     * Parametres FXML de la fenetre "TableauDesScores"
     */
    @FXML
    private TableView<Joueur> tableDesScores;
    @FXML
    private Button buttonSauvegarde;
    @FXML
    private Button buttonSup;
    @FXML
    private  Button buttonLire;

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
     * Parametre qui permet d'acceder a la classe "MethodeCommune" qui regroupe toutes les fonctions utilisees dans
     * l'ensemble des vues.
     * @param joueur :
     * Permet de recuperer le joueur qui est entrain de jouer (soit il est cree dans le cas ou un utilisateur accede
     * au tableau des scores depuis la page d'accueil ou de recupere qui vient de jouer la partie)
     **/

    @FXML
    private VBox controler;

    /*--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------*/


    private Manageur manageur;
    private String nomPage = "Tableau des scores";
    private MethodeCommune methodeCommune;
    private Joueur joueur= new Joueur();

    /**
     * Autres parametres de la fenetre "TableauDesScores"
     * @param listeJoueurs :
     * Parametre qui permet de recuperer la liste de joueur de la classe "ListeJoueurs".
     * @param columnSurname :
     * Parametre qui represente la colonne "Surname" de la TableView "tableDesScores".
     * @param columnPartieGagnee :
     * Parametre qui represente la colonne "Parties gagnees" de la TableView "tableDesScorese.
     * @param columnPartiePerdue :
     * Parametre qui represente la colonne "Parties perdues" de la TableView "tableDesScorese.
     * @param columnDateInscription :
     * Parametre qui represente la colonne "Date d inscription" de la TableView "tableDesScores".
     * */
    private ListeJoueurs listeJoueurs = new ListeJoueurs();
    private final TableColumn<Joueur, String> columnSurname = new TableColumn<>("Surname");
    private final TableColumn<Joueur, Integer> columnPartieGagnee = new TableColumn<>("Parties gagnées");
    private final TableColumn<Joueur, Integer> columnPartiePerdue = new TableColumn<>("Parties perdues");
    private final TableColumn<Joueur, Integer> columnDateInscription = new TableColumn<>("Date d'inscription");


    /*--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------*/

    /**
     * Constructeur de la classe.
     * @param manageur
     * Permet de recuperer le manageur de la classe Launch du package launcher.
     */
    public TableauDesScores(Manageur manageur){
        this.manageur = manageur;
        this.methodeCommune= new MethodeCommune(manageur);
    }

    /**
     * Constructeur de la classe.
     * @param manageur
     * Permet de recuperer le manageur de la classe Launch du package launcher.
     * @param joueur
     * Permet de recuperer le joueur qui vient de jouer, (cela est utile si le joueur decide de jouer une nouvelle partie).
     */
    public TableauDesScores(Manageur manageur, Joueur joueur) {
        this(manageur);
        this.joueur=joueur;
    }


    /*--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------*/


    /**
     * Methode qui permet d'ouvrir une nouvelle fenetre, elle utilise une methode de la classe "MethodeCommune".
     * Elle permet au joueur d'etre redirige vers la fenetre "PreliminaireJeu" pour que le joueur puisse commencer une
     * nouvelle partie.
     * Cette methode emploie des constructeurs differents en fonction de la variable joueur. Si cette variable est vide
     * (c est-a-dire qu'aucun joueur n'a encore jouer) elle utilise le constructeur "PreliminaireJeu(Manageur manageur)",
     * dans le cas contraire le constructeur "PreliminaireJeu(Mnageur manageur, Joueur joueur)" est utilise. Cela permet
     * de communiquer le joueur qui vient d'effectuer la partie a la nouvelle fenêtre (afin qu'il n'est pas a retaper son
     * pseudo) .
     * @throws Exception qui signale un probleme lors de la formation de la nouvelle fenetre.
     */
    @FXML
    private void clicNouvellePartie() throws Exception {
        if (joueur==null){
            methodeCommune.creationFenetrePreliminaireJeu(controler);
        }
        else{
            methodeCommune.creationFenetrePreliminaireJeu(controler, joueur);
        }
    }


    /**
     * Methode qui supprime un joueur de la liste de joueur de la classe "ListeJoueurs".
     * Elle verifie dans un premier temps qu'il y a des joueurs dans la liste des joueurs, et que l'utilisateur a bien
     * selectionne un joueur grace au methodes "Joueur verificationObjetSelectionnee()" et "Boolean verificationAction()".
     */
    @FXML
    private void clicSupprimerUnJoueurDeLaListe(){
        if(verificationAction()){
            return;
        }
        joueur=verificationObjetSelectionnee();
        if(joueur!=null){
            manageur.supprimerUnSeulJoueur(joueur);
        }
        else {
            return;
        }
    }

    /**
     * Methode qui supprime les joueurs de la liste de joueurs de la classe "ListeJoueurs".
     * Elle verifie dans un premier temps qu'il y a des joueurs dans la liste des joueurs, grace a la methode
     * "Boolean verificationAction()."
     * Si elle est vide, une boite de dialogue est ouverte afin d'informer l'utilisateur.
     */
    @FXML
    private void clicSupprimerLaListeDeJoueur(){
        if(verificationAction()){
            return;
        }
        while (listeJoueurs.getListJoueur().size()!=0){
                manageur.supprimerUnSeulJoueur(listeJoueurs.getListJoueur().get(0));
        }
    }

    /**
     * Methode qui permet de lire les donnees enregistrees dans le fichier binaire "listeJoueur.txt".
     * Si le fichier binaire est vide, cette methode ouvre une boite de dialogue affichant un message d'erreur.
     * Cette methode permet egalement q'un même joueur ne sont pas ajouter deux fois dans la liste de joueur grace a la
     * methode "supprimerLesDoublons".
     * Elle verifie aussi qu'il y a des joueurs dans la liste des joueurs, grace a la methode
     * "Boolean verificationAction()."
     **/
    @FXML void clicLire(){
        listeJoueurs.setListJoueur(manageur.LireFichierDeJoueur().getListJoueur());
        if(verificationAction()){
            return;
        }
        manageur.supprimerLesDoublons();
    }

    /**
     * Methode qui permet de fermer cette fenetre et de quitter le jeu, elle utilise une methode de la classe
     * "MethodeCommune".
     **/
    @FXML
    public void clicQuitter() {
        methodeCommune.fermerFenetre(controler);
        manageur.sauvegarderJoueur(listeJoueurs);
    }

    /**
     * Methode qui permet de remettre les scores d'un joueur a zero. La remise a zero des scores d'un joueur induit une
     * sauvegarde.
     * Elle verifie aussi au prealable que la liste des joueurs ne soit pas vide, grace a la methode
     * "Boolean verificationAction()."
     **/
    @FXML
    public void clicRemettreAZero(){
        if(verificationAction()){
            return;
        }
        joueur=verificationObjetSelectionnee();
        if(joueur!=null){
            manageur.remettreScoreJoueurSelectionneAZero(joueur);
            manageur.sauvegarderJoueur(listeJoueurs);
        }
        else {
            return;
        }
    }

    /*--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------*/


     /**
     * Methode qui permet de verifier que l utilisateur est bien selectionne un objet (dans notre cas un joueur de la
     * TableView).
     * Elle recupere dans un premier temps l'objet selectionne par l utilisateur, puis le transforme en joueur. La
     * fonction regarde ensuite si la variable joueur cre est nul. Si c est le cas, cela signifie que l utilisateur n a
     * pas selectionne de joueur dans la TableView, et dans ce cas la methode va creer une boite de dialogue grace a
     * la fonction "Optional<ButtonType> showMessage(Alert.AlertType type, String header, String surname, String message,
     * ButtonType... lesBoutonsDifferents)" avec le message d'erreur suivant : "Vous n'avez pas selectionne de joueur !".
     * Dans le cas contraire la fonction renvoie juste le joueur selectionne.
     * @return le joueur selectionne par l'utilisateur ou, si il n'as pas selectionne de joueur, l valeur nul.
     */
    private Joueur verificationObjetSelectionnee(){
        Object objetSelectionne = tableDesScores.getSelectionModel().getSelectedItem();
        Joueur joueur = (Joueur) objetSelectionne;
        if(objetSelectionne==null){
            showMessage(Alert.AlertType.ERROR, null, "erreur",
                    "Vous n'avez pas selectionné de joueur ! ").filter(bouton -> bouton == ButtonType.OK);
            return null;
        }
        return joueur;
    }

    /**
     * Methode qui permet de verifier que l'utilisateur ne tente pas d effectuer une action sur la liste de joueurs
     * alors que celle-ci est vide. Si l'utilisateur tente une telle action alors, la methode va creer une boite de
     * dialogue grace a la fonction "Optional<ButtonType> showMessage(Alert.AlertType type, String header,
     * String surname, String message, ButtonType... lesBoutonsDifferents)" avec le message d'erreur suivant :
     * "Cette action est impossible ! La liste est vide !". Elle renvoie aussi la valeur "true". Dans le cas contraire
     * la valeur "false" est retournee.
     * @return la valeur "true" si une erreur survient, et "false" dans le cas inverse.
     */
    private Boolean verificationAction(){
        if(listeJoueurs.getListJoueur().size()==0){
            showMessage(Alert.AlertType.ERROR, null, "erreur",
                    "Cette action est impossible ! La liste est vide ! ").filter(bouton -> bouton == ButtonType.OK);
            return true;
        }
        return false;
    }

    /**
     * Methode qui permet de creer les boites de dialogues
     * @param type :
     * corresponds au type d alerte de la boite de dialogue ("INFORMATION_MESSAGE",
     * "WARNING_MESSAGE", "ERROR_MESSAGE").
     * @param header :
     * corresponds a ce qui est ecrit dans le haut de la boite de dialogue.
     * @param surname :
     * corresponds au titre qui nous voulons donner a l'objet.
     * @param message :
     * corresponds au message que nous voulons afficher dans la boite de dialogue.
     * @param lesBoutonsDifferents :
     * correspond au type de bouton que nous voulons appliquer a la boite de dialogue.
     * @return la boite de dialogue et l affiche.
     * @throws Exception signale que le code couleur ne correspond a aucune de celle definie au depart.
     */
    private Optional<ButtonType> showMessage(Alert.AlertType type, String header, String surname, String message){
        Alert boiteDialague = new Alert(type);
        boiteDialague.setHeaderText(header);
        boiteDialague.setContentText(surname);
        boiteDialague.setContentText(message);
        return boiteDialague.showAndWait();
    }


    /*--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------*/


    /**
     * Methode appelee lors de la construction de la fenetre, elle permet d'instancier l'UserControl "MonUserControlTop".
     * Elle utilise une methode de la classe "MethodeCommune".
     * Elle permet aussi de remplir les colonnes : "columnSurname", "columnPartieGagnee", "columnDateInscription" avec
     * les bonnes donnees. Elle permet egalement la sauvegarde des nouvelles informations dans le fichier binaire (par
     * exemple le fait qu'un joueur a gagnee, ou perdu une partie).
     */
    public void initialize() {
        methodeCommune.creationUserControlTop(controler, nomPage);
        manageur.sauvegarderJoueur(listeJoueurs);
        columnSurname.setCellValueFactory(new PropertyValueFactory<>("surname"));
        tableDesScores.getColumns().add(columnSurname);
        columnPartieGagnee.setCellValueFactory(new PropertyValueFactory<>("nbPartieGagnee"));
        tableDesScores.getColumns().add(columnPartieGagnee);
        columnPartiePerdue.setCellValueFactory(new PropertyValueFactory<>("nbPartiePerdue"));
        tableDesScores.getColumns().add(columnPartiePerdue);
        columnDateInscription.setCellValueFactory(new PropertyValueFactory<>("dateInscription"));
        tableDesScores.getColumns().add(columnDateInscription);
        tableDesScores.itemsProperty().bind(listeJoueurs.listJoueurProperty());
        tableDesScores.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        tableDesScores.setRowFactory(messageTable ->new TableRow<>(){
            @Override
            protected void updateItem(Joueur item, boolean empty) {
                super.updateItem(item, empty);
                if(empty){
                    tableDesScores.setPlaceholder(new Label("Le tableau des scores et vide !"));
                }
            }
        });
        if (joueur.getSurname().compareTo("")==0){
            joueur=null;
        }
    }
}
