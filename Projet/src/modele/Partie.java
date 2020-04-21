/**
 * Le package importe.
 */
package modele;

/**
 * Les classes importees.
 */
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 *  @author chanson chloe
 */

/**
 * Classe "Partie" qui represente la duree d'un jeu a l'issue de laquelle sont designes des gagnant et des perdants.
 * Une "Partie" peut etre soit composee de plusieurs manches ou bien d'une seule manche.
 */
public class Partie {

    /**
     * Variables observables
     */

    /**
     * @param malusJoueur :
     * corresponds au malus (nombre de manches perdues) du joueur, cette variable est observable.
     */
    private final ObjectProperty<Integer> malusJoueur = new SimpleObjectProperty();

    /**
     * @param malusAdverse :
     * corresponds au malus (nombre de manches perdues) du joueur adverse, cette variable est observable.
     */
    private final ObjectProperty<Integer> malusAdverse = new SimpleObjectProperty();


    /**
     * Variables non observables
     */

    /**
     * @param mancheList :
     * corresponds à la liste de manches d'une partie. Cette variable n'est pas non observable, car c'est elle n'as pas
     * besoin d'etre affiche par l'application. Cette variable est seulement utilisee dans le cas d'une partie dite
     * complexe.
     */
    private List<Manche> mancheList = new ArrayList<>() {};


    /**
     * @param manche :
     * corresponds à la manche d'une partie. Cette variable n'est pas non observable, car c'est elle n'as pas besoin
     * d'etre affiche par l'application. Cette variable est utilisee dans le cas d'une partie dite rapide.
     */
    private Manche manche = new Manche();


    /*--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------*/


    /**
     * Constructeur vide de la classe.
     * Il initialise les malus a zero.
     */
    public Partie(){
        this.setMalusJoueur(0);
        this.setMalusAdverse(0);
    }


    /*--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------*/

    /**
     * Methode qui permet de creer une nouvelle partie.
     * @param nb_carte
     * Variable qui represente le nombre de cartes par joueur dans le jeu.
     * @param nb_joueur
     * Variable qui represente le nombre de joueurs dans le jeu.
     * @return la partie qui vient d'etre cree.
     */
    protected Partie creerPartie(int nb_carte, int nb_joueur){
        Partie partie = new Partie();
        Manche manche = new Manche();
        manche.distribuer(nb_carte, nb_joueur);
        partie.manche= manche;
        return partie;
    }


    /**
     * Methode qui permet a l'application de "choisir" la prediction pour le joueur adverse. Cette prediction s'aide de
     * la methode "probaDeGagner()" de la classe "Carte" afin que la prediction du joueur adverse soit la plus juste
     * possible.
     * Si la carte du joueur adverse presente peu de chance de remporter une partie, nous n'ajoutons rien a la
     * prediction (representer par le int i). Si la carte presente autant de chance de gagner ou de perdre, la
     * decision est choisi a l'aide d'un "Random" tire entre 0 et 1 (represente par le int j). Cette valeur sera
     * ensuite rajoutee a la prevision. Enfin si la carte presente de grande chance de gagner, nous rajoutons 1 a la
     * prevision.
     * Tout comme dans la vrai regle du "Tarot Africain" la somme de la prediction du joueur adverse, et la prediction
     * du joueur ne peut pas etre egale au nombre de cartes qu'ils possedent en mains (sinon il y aurait une chance que
     * les deux joueurs puissent respecter leur contrat, et le jeu perdrait de son interet). Dans cette fonction, c'est
     * la prevision du jeu adverse qui s'adapte afin de respecter ce principe. Le joueur et donc avantager. Si jamais
     * la prevision du joueur adverse entraine une telle egalite, sa prevision sera le resultat d'un nombre tire
     * aleatoirement entre 0 et le nombre de cartes de chaque joueur grace a la methode "Random". Pour finir, la
     * prevision du joueur et "ajouter" a la manche.
     * @param manche : represente la manche que le joueur est en train du jouer.
     * @return represente la manche que le joueur est en train du jouer (la prevision du joueur adverse a ete rajoutee
     * a la manche).
     * @throws Exception signale que le conseil retourne par la fonction "probaDeGagner()" de la classe "Carte" n'est
     * pas un ceux prevus par l'application au depart.
     */
    protected Manche choixPrevisionAdverse(Manche manche) throws Exception {
        String messageProba;
        Random rand = new Random();
        int i=0;
        int j;
        for(Carte str:(manche.getListCarteAdverse())){
            messageProba=str.probaDeGagner(str);
            if(messageProba.compareTo("Vous avez peu de chance de gagner avec la carte")==0){
                i=i+0;
            }
            else {
                if(messageProba.compareTo("Cette carte présente autant de chance gagnée que de perdre. Suivez votre intuition pour la carte")==0){
                    j =rand.nextInt(1);
                    i=i+j;
                }
                else {
                    if(messageProba.compareTo("Il y a de forte chance que vous gagnez avec la carte")==0){
                    i=i+1;
                    }
                    else{
                        throw new Exception("Le conseil n'est pas reconnu ! ");
                    }
                }
            }
        }
        if((manche.getPrevisionJoueur()+i)==manche.getListCarteAdverse().size()){
            while ((manche.getPrevisionJoueur()+i)==manche.getListCarteAdverse().size()){
                i = rand.nextInt(manche.getListCarteAdverse().size());
            }
        }
        manche.setPrevisionAdverse(i);
        return  manche;
    }

    /**
    * Methode qui permet de determiner quel joueur a gagne lors d'une partie dite complexe. Le joueur qui a perdu le
    * plus grand nombre de fois aura perdu la partie.
    * @param malusJoueur
    * corresponds au nombre de fois que le joueur a perdu.
    * @param malusAdverse
    * corresponds au nombre de fois que le joueur adverse a perdu
    * @return le message a affiche dans la boite de dialogue.
    */
    protected String determinerVictoire(int malusJoueur, int malusAdverse){
        if(malusAdverse>malusJoueur){
            return "Vous-avez gagné !";
            }
        if(malusAdverse==malusJoueur){
            return "Vous êtes à égalité !";
            }
        else{
            return "Vous-avez perdu !";
            }
    }


    /*--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------*/

    /**
     * Methode permettant d'influer sur les variables de la classe.
     */

    /**
    * Methode qui permet de recuperer la liste de manches de la partie. Cette methode est utilisee dans les parties
    * dite complexe.
    * @return la liste de manches d'une partie.
    */
    public List<Manche> getListManche(){return this.mancheList;}

    /**
    * Methode que permet de modifier la liste de manches de la partie. Cette methode est utilisee dans les parties
    * dite complexe.
    * @param mancheList : indique la nouvelle liste de manches de la partie.
    */
    public void setListManche(List<Manche> mancheList) { this.mancheList=mancheList;}

    /**
    * Methode qui permet de recuperer la manche de la partie. Cette methode est utilisee dans les parties dite rapide.
    * @return la manche d'une partie.
    */
    public Manche getManche(){return this.manche;}

    /**
    * Methode que permet de modifier la manche de la partie. Cette methode est utilisee dans les parties dite rapide.
    * @param manche :  indique la nouvelle manches de la partie.
    */
    public void setManche(Manche manche) { this.manche=manche;}

    /**
     * Cette methode permet de retourner le malus du joueur.
     * @return le malus du joueur d'une partie.
     */
    public int getMalusJoueur() {return malusJoueur.get();}

    /**
     * Methode qui permet de modifier le malus du joueur.
     * @param val : corresponds a la nouvelle valeur que nous appliquer a la variable "malusJoueur".
     */
    public void setMalusJoueur(int val) {this.malusJoueur.set(val);}

    /**
     * Cette methode permet de retourner le malus du joueur et de l'afficher dans une fenetre.
     * @return le malus d'un joueur pendant une partie.
     */
    public ObjectProperty<Integer> malusJoueurProperty() {return malusJoueur;}

    /**
     * Cette methode permet de retourner le malus du joueur adverse et de l'afficher dans une fenetre.
     * @return le malus d'un joueur adverse pendant une partie.
     */
    public ObjectProperty<Integer> malusAdverseProperty() {return malusAdverse;}

    /**
     * Cette methode permet de retourner le malus du joueur adverse.
     * @return le malus du joueur adverse d'une partie.
     */
    public int getMalusAdverse() {return malusAdverse.get();}

    /**
     * Methode qui permet de modifier le malus du joueur adverse.
     * @param val : corresponds a la nouvelle valeur que nous appliquer a la variable "malusAdverse".
     */
    public void setMalusAdverse(int val) {this.malusAdverse.set(val);}

}
