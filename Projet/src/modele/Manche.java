/**
 * Le package importe.
 */
package modele;

/**
 * Les classes importees.
 */
import javafx.beans.property.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

/**
 *  @author chanson chloé
 */

/**
 * La classe "Manche" represente un "morceau" d'un jeu... Une manche est composee de plusieurs tours.
 */
public class Manche {

    /**
     * Variable observable
     * @param carteListJoueurObs :
     * Collection de cartes, l'ensemble de cette collection constitue une collection de cartes observable.
     **/
    private ObservableList<Carte> carteListJoueurObs = FXCollections.observableArrayList();

    /**
     * Variable observable
     * @param carteListJoueur :
     * Collection de cartes, l'ensemble de cette collection constitue une collection de cartes observable.
     **/
    private ListProperty<Carte> carteListJoueur = new SimpleListProperty<>(carteListJoueurObs);

    /**
     * Variable observable
     * @param previsionJoueur :
     * corresponds a la prevision du joueur (le nombre de parties qu'il pense gagner), cette variable est observable.
     */
    private ObjectProperty<Integer> previsionJoueur = new SimpleObjectProperty();

    /**
     * Variable observable
     * @param previsionAdverse :
     * corresponds a la prevision du joueur adverse (le nombre de parties qu'il pense gagner), cette variable est
     * observable.
     */
    private IntegerProperty previsionAdverse = new SimpleIntegerProperty();

    /**
     * Variable non-observable
     * @param carteListAdverse :
     * corresponds a la liste de cartes du joueur adverse. Cette variable est la seule non-observable, car c'est la
     * seule qui n'a pas besoin d'atre affiche par l'application.
     **/
    private List<Carte> carteListAdverse = new ArrayList<>();


    /**
     * Variable non-observable
     * @param victoire :
     * permet de savoir si un joueur a remporter ou pas le tour ou la manche. Si la variable est a "true" il a gagne
     * sinon il a perdu.
     **/
    private Boolean victoire;

    private Tour tourList = new Tour();


    /*--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------*/


    /**
     * Constructeur de la classe.
     * Il permet de mettre a zero les previsions des joueurs. Il met aussi la variable victoire a null.
     */
    public Manche() {
        setPrevisionJoueur(0);
        setPrevisionAdverse(0);
        setVictoire(null);
    }


    /*--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------*/


    /**
     * Methode qui permet de distribuer les cartes aux deux joueurs. Cette methode appelle la methode "doRandom" de la
     * classe "Jeu". Elle recupere la liste de cartes de "doRandom" puis la separe en deux ( la liste de cartes du
     * joueur et la liste de cartes du joueur adverse). Elle met ensuite les listes de cartes dans les bonnes variables.
     * @param nbCarte : corresponds au nombre de cartes qu'a chaque joueur dans sa main.
     * @param nbJoueur : corresponds au nombre de joueurs de la manche.
     */
    protected void distribuer(int nbCarte, int nbJoueur) {
        List<Carte> listCarte;
        Jeu jeu = new Jeu();
        int i = 0;
        listCarte = jeu.doRandom(nbCarte, nbJoueur);
        Iterator<Carte> it = listCarte.iterator();
        while (it.hasNext()) {
            if (i < nbCarte)
                carteListJoueurObs.add(it.next());
            else
                carteListAdverse.add(it.next());
            i = i + 1;
        }
    }

    /**
     * Methode qui permet a l'application de choisir la carte que le joueur adverse joue lors d'un tour. Ce choix se
     * fait de maniere a aleatoire grace a la methode "Random". Pour faire ce choix, je determine une variable i (la
     * valeur de i ne peux pas etre superieur au nombre de cartes que possede le joueur). Cette valeur de i determine
     * la place de la carte dans la liste du joueur. C'est cette carte qui sera alors jouer par le joueur.
     * @param carteList : corresponds a la liste de cartes du joueur adverse.
     * @return la carte qui va jouer par le joueur adverse.
     */
    protected Carte choixCarteAdverse(List<Carte> carteList) {
        Random rand = new Random();
        Carte carte = new Carte();
        int i = rand.nextInt(carteList.size());
        for (int j = 0; j <= i; j++) {
            Iterator it = carteList.iterator();
            it.hasNext();
            carte = (Carte) it.next();
        }
        return carte;
    }


    /**
     * Methode qui permet de realiser la suppression de la carte du joueur et du joueur adverse qui vient d'etre joue
     * (lors d'un tour).
     * @param carteJoueur : corresponds a la carte que le joueur vient de jouer.
     * @param carteAdverse : corresponds a la carte que le joueur adverse vient de jouer.
     */
    protected void supressionCarte (Carte carteJoueur, Carte carteAdverse){
        carteListAdverse.remove(carteAdverse);
        carteListJoueurObs.remove(carteJoueur);
    }




    /**
     * Methode qui permet de determiner le resultat d'une manche lors d'une partie dite complexe. Cette fonction permet
     * de mettre a jour les malus du jour et du joueur adverse. Un malus est ajoute a un joueur des lors ou la prevision
     * du joueur ne correspond pas au nombre de parties gagnees par ce joueur. Cette methode permet aussi de mettre a
     * jour la valeur victoire de la classe "Manche". Elle renvoie aussi un String adapte au resultat de la manche qui
     * sera utilisee pour l'affichage de la boite de dialogue associe.
     * @param manche corresponds a la manche que le joueur est en train de jouer (cette variable est un raccourcie pour
     * acceder a la manche).
     * @param partie corresponds a la partie que le joueur est en train de joueur.
     * @return resultat de la manche qui sera utilisee pour l'affichage de la boite de dialogue associe.
     */
    protected String resultatManchePartieComplexe(Manche manche, Partie partie){
        manche.setVictoire(null);
        if(manche.getPrevisionAdverse()!=((manche.getTour().getNbToursGagnesAdverse()))){
            partie.setMalusAdverse(partie.getMalusAdverse()+1);
        }
        if((manche.getPrevisionJoueur())==((manche.getTour().getNbToursGagnesJoueur()))){
            manche.setVictoire(true);
            return "Vous avez gagné la manche ! ";
        }
        else {
            partie.setMalusJoueur(partie.getMalusJoueur()+1);
            manche.setVictoire(false);
            return "Vous avez perdu la manche ! Vous n'avez pas respecté le contrat Vous avez gagné " +
                    manche.getTour().getNbToursGagnesJoueur() + " tour(s) au lieu de " + manche.getPrevisionJoueur() +".";
        }
    }


    /*--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------*/

    /**
     * Methode permettant d'influer sur les variables de la classe.
     */

    /**
     * Methode qui permet de retourner la liste de cartes du joueur adverse.
     * @return la liste de cartes du joueur adverse.
     **/
    public List<Carte> getListCarteAdverse(){return this.carteListAdverse;}

    /**
     * Methode qui permet de modifier la liste de cartes du joueur adverse.
     * @param carteList : corresponds a la nouvelle liste de cartes du joueur adverse : "carteListAdverse".
     */
    public void setListCarteAdverse(List<Carte> carteList) { this.carteListAdverse=carteList;}

    /**
     * Methode qui permet de retourner la variable "victoire" da la classe.
     * @return la variable "victoire" da la classe.
     */
    public Boolean getVictoire(){
        return  this.victoire;
    }

    /**
     * Methode qui permet de modifier la variable "victoire" da la classe.
     * @param valeur  corresponds a la nouvelle valeur de la variable "victoire" de la classe.
     */
    public void setVictoire(Boolean valeur){this.victoire =valeur;}

    /**
     * Methode qui permet de retourner la liste de cartes du joueur.
     * @return la liste de cartes du joueur.
     */
    public ObservableList<Carte> getCarteListJoueur() {return carteListJoueur.get();}

    /**
     * Methode qui permet de retourner la liste de cartes du joueur et de l'afficher dans une fenetre.
     * @return la liste de cartes du joueur.
     */
    public ListProperty<Carte> carteListJoueurProperty() {return carteListJoueur;}

    /**
     * Methode qui permet de retourner la prevision du joueur et de l'afficher dans une fenetre.
     * @return la prevision du joueur.
     */
    public ObjectProperty<Integer> previsionJoueurProperty() {return previsionJoueur; }

    /**
     * Methode qui permet de retourner la prevision du joueur.
     * @return la prevision du joueur.
     */
    public int getPrevisionJoueur() {return previsionJoueur.get();}

    /**
     * Methode qui permet de modifier la prevision du joueur.
     * @param previsionJoueur  corresponds a la nouvelle valeur de la variable "previsionJoueur" de la classe.
     */
    public void setPrevisionJoueur(int previsionJoueur) {this.previsionJoueur.set(previsionJoueur);}


    /**
     * Methode qui permet de retourner la prevision du joueur adverse et de l'afficher dans une fenetre.
     * @return la prevision du joueur adverse.
     */
    public IntegerProperty previsionAdverseProperty() {return previsionAdverse;}


    /**
     * Methode qui permet de retourner la prevision du joueur adverse.
     * @return la prevision du joueur adverse.
     **/
    public int getPrevisionAdverse() {return previsionAdverse.get();}

    /**
     * Methode qui permet de modifier la prevision du joueur.
     * @param previsionAdverse  corresponds a la nouvelle valeur de la variable "previsionJoueur" de la classe.
     */
    public void setPrevisionAdverse(int previsionAdverse) {this.previsionAdverse.set(previsionAdverse);}

    public Tour getTour() {return tourList;}


}