/**
 * Le package importe.
 */
package modele;

/**
 * Les classes importees.
 */
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
/**
 *  @author chanson chloé
 */

/**
 * La classe "Tour" represente l'action ou chaque jour pose une carte... Une manche est composee de plusieurs tours.
 */
public class Tour {

    /**
     * Variable observable
     * @param nbPartieGagneeJoueur :
     * corresponds au nombre de tours gagnés par le joueur, cette variable est observable.
     **/
    private IntegerProperty nbToursGagnesJoueur = new SimpleIntegerProperty();

    /**
     * Variable observable
     * @param nbPartieGagneeAdverse :
     * corresponds au nombre de tours gagnés par le joueur adverse, cette variable est observable.
     **/
    private IntegerProperty nbToursGagnesAdverse = new SimpleIntegerProperty();


    /*--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------*/


    /**
     * Constructeur de la classe.
     * Il permet de mettre a zero le nombre de tours remportes par les joueurs.
     */
    public Tour(){
        setNbToursGagnesJoueur(0);
        setNbToursGagnesAdverse(0);
    }


    /*--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------*/


    /**
     * Methode qui permet de definir quel joueur est le vainqueur du tour. Cette methode permet aussi de supprimer la
     * carte jouee par le joueur et le joueur adverse grace a l'appel de la fonction
     * supressionCarte(Carte carteJoueur, Carte carteAdverse). Elle permet aussi de mettre a
     * jour la valeur victoire de cette classe.
     * @param manche : corresponds a la manche que le joueur est en train de jouer.
     * @param carteAdverse : corresponds a la carte que le joueur adverse vient de jouer.
     * @param carteJoueur : corresponds a la carte que le joueur vient de jouer.
     * @return retourne la partie avec les valeurs misent a jour.
     * @throws Exception signale que les cartes jouees par le joueur et le jouer adverse sont identiques. Le programme
     * n'est sense jamais arriver ici.
     */
    protected Manche tour(Manche manche, Carte carteAdverse, Carte carteJoueur) throws Exception {
        manche.setVictoire(null);
        if (carteAdverse.getValeur() > carteJoueur.getValeur()) {
            manche.getTour().setNbToursGagnesAdverse(manche.getTour().getNbToursGagnesAdverse() + 1);
            manche.supressionCarte(carteJoueur, carteAdverse);
            manche.setVictoire(false);
            return manche;
        } else {
            if(carteJoueur.getValeur() > carteAdverse.getValeur()) {
                manche.getTour().setNbToursGagnesJoueur(manche.getTour().getNbToursGagnesJoueur() + 1);
                manche.supressionCarte(carteJoueur, carteAdverse);
                manche.setVictoire(true);
                return manche;
            }
            else{
                throw new Exception("Problème, la carte jouer par le joueur et le joueur adverse et la même");
            }
        }
    }



    /**
     * Methode qui permet de retourner le message adapter au resultat du tour.
     * Elle sert pour l'affichage de la boite de dialogue (l'affichage change en fonction que ce soit une victoire
     * ou bien une defaite).
     * @param manche : corresponds a la manche que le joueur est en train de jouer.
     * @param carteAdverse : corresponds a la carte que le joueur adverse vient de jouer.
     * @param carteJoueur : corresponds a la carte que le joueur vient de jouer.
     * @return le string qui sera utilisee pour l'affichage de la boite de dialogue associe.
     */
    protected String determinerMessageTour(Manche manche, Carte carteAdverse, Carte carteJoueur) throws Exception {
        if (manche.getVictoire()==false) {
            return "Vous avez perdus le tour!\n Vous avez joué : "+ carteJoueur.getValeur() +
                    "\n Votre adversaire a joué : " + carteAdverse.getValeur();
        }
        else {
            if(manche.getVictoire()==true){
                return "Vous avez gagné le tour! \n Vous avez joué : "+ carteJoueur.getValeur() +
                        "\n Votre adversaire a joué : " + carteAdverse.getValeur();
            }
            else {
                throw new Exception("La valeur de la variable victoire est null");
            }
        }
    }


    /*--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------*/


    /**
     * Methode permettant d'influer sur les variables de la classe.
     */

    /**
     * Methode qui permet de retourner le nombre de tours gagnes par le joueur et de l'afficher dans une fenetre.
     * @return le nombre de tours gagnes par le joueur.
     */
    public IntegerProperty nbToursGagnesJoueurProperty() {return nbToursGagnesJoueur;}

    /**
     * Methode qui permet de retourner le nombre de tours gagnes par le joueur.
     * @return le nombre de tours gagnes par le joueur.
     **/
    public int getNbToursGagnesJoueur() {return nbToursGagnesJoueur.get();}

    /**
     * Methode qui permet de modifier le nombre de tours gagnes par le joueur.
     * @param nbToursGagnesJoueur corresponds a la nouvelle valeur de la variable "nbToursGagnesJoueur" de la classe.
     */
    public void setNbToursGagnesJoueur(int nbToursGagnesJoueur) {this.nbToursGagnesJoueur.set(nbToursGagnesJoueur);}

    /**
     * Methode qui permet de retourner le nombre de tours gagnes par le joueur adverse et de l'afficher dans une fenetre.
     * @return le nombre de tours gagnes par le joueur adverse.
     */
    public IntegerProperty nbToursGagnesAdverseProperty() {return nbToursGagnesAdverse; }

    /**
     * Methode qui permet de retourner le nombre de tours gagnes par le joueur adverse.
     * @return le nombre de tours gagnes par le joueur adverse.
     **/
    public int getNbToursGagnesAdverse() {return nbToursGagnesAdverse.get();}

    /**
     * Methode qui permet de modifier le nombre de tours gagnes par le joueur adverse.
     * @param nbToursGagnesAdverse corresponds a la nouvelle valeur de la variable "nbToursGagnesAdverse" de la classe.
     */
    public void setNbToursGagnesAdverse(int nbToursGagnesAdverse) {this.nbToursGagnesAdverse.set(nbToursGagnesAdverse);}
}
