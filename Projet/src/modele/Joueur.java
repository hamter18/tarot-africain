/**
 * Le package importe.
 */
package modele;

/**
 * Les classes importees.
 */
import java.io.Serializable;
import java.time.LocalDate;

/**
 *  @author chanson chloé
 */

/**
 * La classe "Joueur" est une classe qui est serialisable. Cette classe represente une personne qui joue au jeu.
 */
public class Joueur implements Serializable {

    /**
     * Variables qui permettent de définir un joueur.
     * @param surname :
     * corresponds au pseudo que le joueur a choisi pour jouer.
     */
    private  String surname;

    /**
     * @param nbPartieGagnee :
     * corresponds au nombre de partie que le joueur a gagne.
     */
    private Integer nbPartieGagnee;

    /**
     * @param nbPartiePerdue :
     * corresponds au nombre de partie que le joueur a perdu.
     */
    private Integer nbPartiePerdue;

    /**
     * @param dateInscription :
     * corresponds a la date d'inscription du joueur, c'est-a-dire le premier jour ou le jouer s'est inscrit au jeu.
     */
    private LocalDate dateInscription;


    /*--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------*/


    /**
     * Constructeur vide de la classe.
     */
    public  Joueur(){
        this("", null);
    }

    /**
     * Autre constructeur de la classe.
     * @param surname : corresponds au pseudo que le joueur a choisi pour jouer.
     * @param dateInscription : corresponds a la date d'inscription du joueur, c'est-a-dire le premier jour ou le
     * jouer s'est inscrit au jeu.
     */
    public Joueur(String surname, LocalDate dateInscription){
        this.surname= surname;
        this.nbPartieGagnee=0;
        this.nbPartiePerdue=0;
        this.dateInscription= dateInscription;
    }

    /**
     * Autre constructeur de la classe.
     * Ce constructeur est utilise pour initialiser des joueurs de "base" dans l'application
     * @param surname : corresponds au pseudo que le joueur a choisi pour jouer.
     * @param dateInscription : corresponds a la date d'inscription du joueur, c'est-a-dire le premier jour ou le
     * jouer s'est inscrit au jeu.
     * @param nbPartieGagnee : corresponds au nombre de partie que le joueur a gagne.
     * @param nbPartiePerdue : corresponds au nombre de partie que le joueur a perdu.
     */
    public Joueur(String surname, LocalDate dateInscription, int nbPartieGagnee, int nbPartiePerdue){
        this.surname= surname;
        this.nbPartieGagnee=nbPartieGagnee;
        this.nbPartiePerdue=nbPartiePerdue;
        this.dateInscription= dateInscription;
    }


    /*--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------*/

    /**
     * Methode qui permet de comparer deux joueurs afin de savoir s'ils sont identiques.
     * @param joueur : joueur a comparer
     * @return vrai si le joueur et trouver, et faux si il n'est pas trouve.
     */
    public boolean equals(Joueur joueur){
        if ((this.surname.compareTo(joueur.getSurname())==0) & this.dateInscription.isEqual(joueur.getDateInscription())){
            return true;
        }
        else{
            return false;
        }
    }

    /**
     * Methode, qui permet de remettre les scores (le nombre de parties gagnees et perdues) d'un joueur, a zero.
     * @param joueur : joueur dont nous voulons remettre les scores a zero.
     * @return le joueur avec la mise a jour des scores.
     */
    protected Joueur remettreScoreJoueurAZero(Joueur joueur){
        joueur.setNbPartiePerdue(0);
        joueur.setNbPartieGagnee(0);
        return joueur;
    }


    /*--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------*/


    /**
     * Methode permettant d'influer sur les variables de la classe.
     */

    /**
     * Methode qui permet de retourner le nom du joueur.
     * @return le nom associe au joueur.
     */
    public String getSurname() {return surname;}

    /**
     * Methode qui permet de retourner le nombre de partie que le joueur a gagne.
     * @return le nombre de partie que le joueur a gagne.
     */
    public int getNbPartieGagnee() {return nbPartieGagnee;}

    /**
     * Methode qui permet de modifier le nombre de partie que le joueur a gagne.
     * @param valeur : corresponds a la nouvelle valeur que nous appliquer a la variable "nbPartieGagnee".
     */
    public void setNbPartieGagnee(int valeur) {this.nbPartieGagnee= valeur;}

    /**
     * Methode qui permet de retourner le nombre de partie que le joueur a perdu.
     * @return le nombre de partie que le joueur a perdu.
     */
    public int getNbPartiePerdue() {return nbPartiePerdue;}

    /**
     * Methode qui permet de modifier le nombre de partie que le joueur a perdu.
     * @param valeur : corresponds a la nouvelle valeur que nous appliquer a la variable "nbPartiePerdue".
     */
    public void setNbPartiePerdue(int valeur) {this.nbPartiePerdue= valeur;}

    /**
     * Methode qui permet de retourner la date d'incription d'un joueur.
     * (elle est utilisee pour l'affichage de la date dans la fenetre "TableauDesScores")
     * @return la date d'inscription du joueur.
     */
    public LocalDate getDateInscription() {return dateInscription;}
}
