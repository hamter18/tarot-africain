/**
 * Le package importe.
 */
package view;

/**
 * Les classes importees.
 */
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.Stop;
import modele.*;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

/**
 *  @author chanson chloe
 */

/**
 * La classe "MethodeJeu" regroupe l'ensemble des methodes qui sont utilisees dans les fenetres de jeu ("JeuRapide" et
 * "JeuComplexe ) de l application.
 */
public class MethodeJeu {

    /**
     * @param manageur :
     * Corresponds au manageur, c'est la seule personne qui a acces aux methodes des classes.
     **/
    private Manageur manageur;

    /**
     * @param carteAdversaire :
     * Corresponds a la carte qui est selectionnee par l'adversaire durant un tour.
     **/
    private Carte carteAdversaire;


    /*--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------*/

    /**
     * Constructeur de la classe.
     * @param manageur :
     * Permet de recuperer le manageur de la classe Launch du package launcher.
     */
    public MethodeJeu(Manageur manageur){
        this.manageur=manageur;
    }

    /*--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------*/

    /**************************************CREATION DES BOITES DE DIALOGUES **************************************/
             /**************************************Fin d'un tour **************************************/


    /**
     * Methode permettant d'afficher une boite de dialogue affichant le resultat du tour.
     * @param resultatTour :
     * Correspond au message que nous devons afficher dans la boite de dialogue.
     * @param victoire :
     * Correspond au resultat du tour, la valeur "true" signifie que le joueur a gagne, et false signifie qu'il a perdu.
     */
    protected void afficherResultatTour(String resultatTour, Boolean victoire){
        if(victoire==true){
            try {
                Image img = new Image(getClass().getResource("/images/logoVictoireTour.png").toExternalForm());
                showMessage(Alert.AlertType.INFORMATION, victoire,"Pour ce tour :", "Surname",  img, resultatTour
                        ).filter(bouton -> bouton == ButtonType.OK);
            }
            catch (Exception e){
                e.printStackTrace();
            }
        }
        else {
            try{
                Image img = new Image(getClass().getResource("/images/logoDefaiteTour.png").toExternalForm());
                showMessage(Alert.AlertType.INFORMATION,  victoire,  "Pour ce tour :", "Surname", img, resultatTour
                        ).filter(bouton -> bouton == ButtonType.OK);
            }
            catch (Exception e){
                e.printStackTrace();
            }
        }
    }

              /**************************************Fin d'une manche **************************************/


    /**
     * Methode permettant d'afficher une boite de dialogue affichant le resultat d'une manche d'une partie complexe.
     * @param manche :
     * Corresponds a la manche que le joueur est en train de jouer (cette variable est un raccourcie pour
     * acceder a la manche).
     * @param partie :
     * Corresponds a la partie que le joueur est en train de joueur.
     * @throws Exception signale un probleme dans la fonction "String resultatManchePartieComplexe(Manche manche,
     * Partie partie)" de la classe "Manche".
     */
    protected void affichageFinDeManchePartieComplexe(Manche manche, Partie partie) throws Exception {
        String text = manageur.resultatManchePartieComplexe(manche, partie);
        if(manche.getVictoire()==true){
            Image img = new Image(getClass().getResource("/images/logoVictoireManche.png").toExternalForm());
            showMessage(Alert.AlertType.INFORMATION, manche.getVictoire(), "Pour cette partie :", "NbPartieGagnee",
                    img, text);
        }
        else{
            Image img = new Image(getClass().getResource("/images/logoDefaiteManche.png").toExternalForm());
            showMessage(Alert.AlertType.INFORMATION, manche.getVictoire(), "Pour cette partie :", "NbPartieGagnee",
                    img, text);
        }
    }


        /**************************************Fin d'une partie **************************************/

    /**
     * Methode permettant d'afficher une boite de dialogue affichant le resultat de la partie dite "rapide"
     * @param manche :
     * Corresponds a la manche que le joueur est en train de jouer.
     * @param joueur :
     * Correspond au joueur qui est en train de jouer.
     * @param partie :
     * Corresponds a la partie que le joueur est en train de joueur.
     * @throws Exception si il y a un probleme lors de la creation de la boite de dialoque par le fonction
     * Optional<ButtonType> showMessage(Alert.AlertType type, Boolean couleur, String header, String surname,
     * Image image, String message, ButtonType... lesBoutonsDifferents).
     */
    protected void affichageFinDePartieRapide(Joueur joueur, Manche manche, Partie partie) throws Exception {
        String text = manageur.resultatFinPartieRapide(joueur, partie);
        if(manche.getVictoire()==true){
            Image img = new Image(getClass().getResource("/images/logoVictoirePartie.png").toExternalForm());
            showMessage(Alert.AlertType.INFORMATION, manche.getVictoire(), "Pour cette partie :", "NbPartieGagnee",
                    img, text);
        }
        else{
            Image img = new Image(getClass().getResource("/images/logoDefaitePartie.png").toExternalForm());
            showMessage(Alert.AlertType.INFORMATION, manche.getVictoire(), "Pour cette partie :", "NbPartieGagnee",
                    img, text);
        }
    }


    /**
     * Methode permettant d'afficher une boite de dialogue affichant le resultat de la partie dite "complexe"
     * @param manche :
     * Corresponds a la manche que le joueur est en train de jouer.
     * @param joueur :
     * Correspond au joueur qui est en train de jouer.
     * @param partie :
     * Corresponds a la partie que le joueur est en train de joueur.
     * @throws Exception si il y a un probleme lors de la creation de la boite de dialoque par le fonction
     * Optional<ButtonType> showMessage(Alert.AlertType type, Boolean couleur, String header, String surname,
     * Image image, String message, ButtonType... lesBoutonsDifferents).
     */
    protected void affichageFinDePartieComplexe(Manche manche, Joueur joueur, Partie partie) throws Exception {
        String msg = manageur.resultatFinPartieComplexe(joueur, partie);
        if(manche.getVictoire()==true){
            Image img = new Image(getClass().getResource("/images/logoVictoirePartie.png").toExternalForm());
            showMessage(Alert.AlertType.INFORMATION, true, "Pour cette partie :", "NbPartieGagnee", img, msg);
        }
        else {
            if (manche.getVictoire()==false){
                Image img = new Image(getClass().getResource("/images/logoDefaitePartie.png").toExternalForm());
                showMessage(Alert.AlertType.INFORMATION, false, "Pour cette partie :", "NbPartieGagnee", img, msg);
            } else {
                throw new Exception("Problème le résultat que vous avez obtenue pour cette partie est non valide");
            }
        }
    }


    /*--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------*/

    /**************************************METHODE QUI PERMET D ORGANISER UN TOUR DANS LE JEU **************************************/

    /**
     * Methode qui permet de faire la "routine" du jeu. Cette routine correspond aux differents elements d un tour.
     * Dans un premier temps elle determine une carte selectionnee par le joueur adverse grace a la fonction
     * "Carte choixCarteAdverse(List<Carte> carteList)" de la classe "Manche". (la carte est choisie en fonction des cartes
     * qu'il y a dans la liste de cartes du joueur adverse). Ensuite nous faisons appelle a la methode
     * "Manche tour(Manche manche, Carte carteAdverse, Carte carteJoueur)" de la classe "Tour". Nous determinons ainsi
     * quel joueur est le vainqueur du tour. Nous recherchons se resultat grace aux methodes " String
     * determinerMessageTour(Manche manche, Carte carteAdverse, Carte carteJoueur)" puis nous affichons le resultat du tour
     * grace a la methode "void afficherResultatTour(String resultatTour, Boolean victoire)".
     * Enfin nous mettons a jour la listes de carte du joueur adverse afin de retirer la carte qu'il vient de jouer.
     * @param listCarteAdverse :
     * Corresponds a la liste de carte du joueur adverse.
     * @param carteJoueur :
     * Corresponds a la carte choisi par le joueur.
     * @param manche :
     * Corresponds a la manche que nous sommes en train de jouer.
     */
    protected void deroulementJeu(List<Carte> listCarteAdverse, Carte carteJoueur, Manche manche ){
        carteAdversaire = manageur.choixCarteAdverse(listCarteAdverse);
        manche=manageur.tour( manche, carteAdversaire, carteJoueur);
        String resultatTour = manageur.determinerMessageTour(manche, carteAdversaire, carteJoueur);
        afficherResultatTour(resultatTour,manche.getVictoire());
        manche.setListCarteAdverse(listCarteAdverse);
    }


    /*--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------*/

                     /**************************************AUTRES METHODES **************************************/


    /**
     * Methode qui permet de creer les boites de dialogues.
     * Dans un premier temps, la boite de dialogue est creee, nous definissons ensuite sa taille, puis nous ajoutons son
     * surname.
     * Nous ajoutons dans une "HBox" du nom de "contenant1" l'image, et le header correspondant a l'affichage voulue dans la boite de
     * dialogue.
     * Dans une seconde "HBox" nous ajoutons le message a afficher.
     * Pour finir nous modelisons la couleur du background de la boite de dialogue en fonction de la variable "couleur".
     * @param type  : permet de definir le type d'alerte ("INFORMATION_MESSAGE", "WARNING_MESSAGE", "ERROR_MESSAGE").
     * @param couleur : permet de definir la couleur de fond de la boite de dialogue.
     * @param header : permet de definir ce qui est ecrit dans le haut de la boite de dialogue.
     * @param surname : permet de donner un titre a l'objet.
     * @param image : permet de modifier l'image de la boite de dialogue selon l affichage.
     * @param message : permet d'afficher le message voulu dans la boite de dialogue.
     * @throws Exception signale que le code couleur ne correspond a aucun de ceux definie au depart.
     */
     private Optional<ButtonType> showMessage(Alert.AlertType type, Boolean couleur, String header, String surname, Image image, String message) throws Exception {
        Alert boiteDialague = new Alert(type);
        boiteDialague.getDialogPane().setPrefWidth(500);
        HBox contenant1 = new HBox();
        HBox contenant2 = new HBox();
        contenant1.setAlignment(Pos.CENTER_LEFT);
        contenant1.setSpacing(15);
        Label headerText = new Label(header);
        headerText.setStyle("-fx-font-family: Merienda One; -fx-font-size: 28;");
        Label msg = new Label(message);
        boiteDialague.setContentText(surname);
        ImageView imageView = new ImageView(image);
        imageView.setFitHeight(100);
        imageView.setFitWidth(100);
        contenant1.getChildren().add(imageView);
        contenant1.getChildren().add(headerText);
        contenant1.setAlignment(Pos.CENTER);
        msg.setWrapText(true);
        msg.setPrefWidth(350);
        msg.setAlignment(Pos.CENTER);
        contenant2.getChildren().add(msg);
        contenant2.setAlignment(Pos.CENTER);
        contenant1.setBackground(new Background(new BackgroundFill(Color.BEIGE, CornerRadii.EMPTY, Insets.EMPTY)));
        contenant1.setPadding(new Insets(10));
        contenant2.setPadding(new Insets(10));
        boiteDialague.getDialogPane().getStyleClass().add("boiteDeDialogue");
         if(couleur==true){
             boiteDialague.getDialogPane().setBackground(new Background(new BackgroundFill(
                     new LinearGradient(0, 50, 0, 150, false, CycleMethod.NO_CYCLE,
                     new Stop(0, Color.LIGHTGREEN ),
                     new Stop(1, Color.LIMEGREEN)), CornerRadii.EMPTY, Insets.EMPTY)));
                     msg.setStyle("-fx-font-family: Itim; -fx-font-size: 18;");
                     (boiteDialague.getDialogPane().lookupButton(boiteDialague.getButtonTypes().get(0))).setStyle(
                             "    -fx-border-radius: 75;\n" +
                             "    -fx-border-width:1;\n" +
                             "    -fx-padding: 10;\n" +
                             "    -fx-text-fill: white;\n" +
                             "    -fx-font-weight: bold;\n" +
                             "    -fx-border-color: lightgrey;\n" +
                             "    -fx-background-color: linear-gradient( #008ae6,  #4da6ff);\n" +
                             "    -fx-background-radius: 75;\n" +
                             "    -fx-effect: dropshadow( three-pass-box , rgba(0,0,0,0.6) , 5, 0.0 , 0 , 1 );");
         }
        else{
            if(couleur==false){
                boiteDialague.getDialogPane().setBackground(new Background(new BackgroundFill(
                        new LinearGradient(0, 50, 0, 150, false, CycleMethod.NO_CYCLE,
                        new Stop(0, Color.SALMON ),
                        new Stop(1, Color.FIREBRICK)), CornerRadii.EMPTY, Insets.EMPTY)));
                        msg.setStyle("-fx-font-family: Itim; -fx-font-size: 18; -fx-text-fill:white;");
                        (boiteDialague.getDialogPane().lookupButton(boiteDialague.getButtonTypes().get(0))).setStyle(
                                "    -fx-border-radius: 75;\n" +
                                "    -fx-border-width:1;\n" +
                                "    -fx-padding: 10;\n" +
                                "    -fx-text-fill: white;\n" +
                                "    -fx-font-weight: bold;\n" +
                                "    -fx-border-color: lightgrey;\n" +
                                "    -fx-background-color: linear-gradient(#F11A1A, #FF8C00);\n" +
                                "    -fx-background-radius: 75;\n" +
                                "    -fx-effect: dropshadow( three-pass-box , rgba(0,0,0,0.6) , 5, 0.0 , 0 , 1 );");
            }
            else {
                throw new Exception("Problème le code couleur n'existe pas !");
            }
        }
        boiteDialague.getDialogPane().setHeader(contenant1);
        boiteDialague.getDialogPane().setContent(contenant2);
        boiteDialague.setGraphic(contenant1);
        return boiteDialague.showAndWait();
    }


    /**
     * Methode qui permet de creer l "MonUserControlListView".
     * @param elmStage :
     * Corresponds a la structure qui contient l UserControl.
     * @param joueur :
     * Parametre qui  permet de recuperer le joueur qui est en train de jouer (cree ou recupere dans la fenetre "PreliminaireJeu").
     * @param manche :
     * Parametre, qui correspond, a un raccourci pour acceder a la manche que le joueur est en train de jouer.
     */
    protected void creationUserControlListView(VBox elmStage, Manche manche, Joueur joueur){
        try {
            MonUserControlListView Uc2 = new MonUserControlListView(manageur, manche, joueur);
            elmStage.getChildren().add(Uc2);
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
}
