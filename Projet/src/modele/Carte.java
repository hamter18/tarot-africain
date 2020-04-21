/**
 * Le package importe.
 */
package modele;

/**
 * La classe importee.
 */
import javafx.beans.property.*;

/**
 *  @author chanson chloe.
 */
/**
 * La classe "Carte" correspond a l'objet (physique) "carte". Une carte a jouer est une carte en carton rectangulaire
 * dont l'une des faces porte une illustration et qui est utilise dans differents jeux.
 */
public class Carte  {
    /**
     * Variables observables
     * @param nom :
     * Represente le nom de la carte.
     */
    private final StringProperty nom = new SimpleStringProperty();

    /**
     * @param nomSpecifique :
     * Représente le nom spécifique (le vrai nom) de la carte de tarot.
     * */
    private final StringProperty nomSpecifique = new SimpleStringProperty();

    /**
     * @param valeur :
     * Corresponds a la valeur de la carte (dans notre cas, la valeur et le nom se confondent).
     */
    private final IntegerProperty valeur = new SimpleIntegerProperty();

    /**
     * @param image :
     * Represente l'image associee à la carte.
     */
    private String image;

    /*--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------*/


    /**
     * Constructeur vide de la classe.
     */
    public Carte(){};


    /**
     * Autre constructeur de la classe.
     * @param nom :
     * Represente le nom qu'y est donne a la carte.
     * @param nomSpecifique :
     * Represente le nom specifique (le vrai nom) de la carte de tarot.
     * @param valeur :
     * Corresponds a la valeur de la carte (dans notre cas, la valeur et le nom se confondent).
     * @param image :
     * Represente l'image associee a la carte.
     */
    public Carte(String nom, String nomSpecifique, int valeur, String image) {
        this.valeur.set(valeur);
        this.nom.set(nom);
        this.nomSpecifique.set(nomSpecifique);
        this.image=image;
    }

    /*--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------*/


    /**
     * Methode qui permet de conseiller le joueur en renvoyant un String qui varie en fonction de la valeur de la carte
     * passee en parametre.
     *
     * Si la valeur de la carte est inferieure a 8, nous considerons qu'il a peu de chance qu'il remporte un tour
     * (dans une manche) avec cette carte. Si la valeur de la carte est inferieure a 14 et superieure a 8, nous
     * considerons qu'il a autant de chance qu'il remporte un tour qu'il en perd un. Si la valeur de la carte est
     * superieure a 14, nous considerons qu'il a de forte chance qu'il remporte un tour dans une manche avec cette
     * carte.
     *
     * @param carte : Represente la carte selectionnee par le joueur.
     * @return le conseil adapte au joueur, le return "null" est sense jamais arriver.
     */
    protected String probaDeGagner (Carte carte){
        if(carte.getValeur()<8){
            return "Vous avez peu de chance de gagner avec la carte";
        }
        if(carte.getValeur()<14){
            return "Cette carte présente autant de chance gagnée que de perdre. Suivez votre intuition pour la carte";
        }
        if(carte.getValeur()<=22){
            return "Il y a de forte chance que vous gagnez avec la carte";
        }
        else{
            return null;
        }
    }


    /*--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------*/

    /**
     * Methode permettant d'influer sur les variables de la classe.
     */


    /**
     * Cette methode permet de retourner le chemin de l'image.
     * @return le chemin de l'image associe a une carte.
     */
    public String getImage() {
        return this.image;
    }

    /**
     * Cette methode permet de retourner le nom de l'image.
     * @return le nom de associe a une carte.
     */
    public String getNom() {return nom.get();}

    /**
     * Cette methode permet de retourner le nom de la carte et de l'afficher dans une fenetre.
     * @return le nom de associe a une carte.
     */
    public StringProperty nomCarteProperty() {return nom;}

    /**
     * Cette methode permet de retourner le nom specifique de la carte et de l'afficher dans une fenetre.
     * @return le nom de associe specifique a une carte.
     */
    public StringProperty nomSpecifiqueProperty() {return nomSpecifique;}

    /**
     * Cette methode permet de retourner la valeur de la carte.
     * @return la valeur de associee a une carte.
     */
    public Integer getValeur() {return valeur.get();}

    /**
     * Cette methode permet de retourner la valeur de la carte et de l'afficher dans une fenetre.
     * @return la valeur associe a une carte.
     */
    public IntegerProperty valeurProperty() {return valeur;}

}