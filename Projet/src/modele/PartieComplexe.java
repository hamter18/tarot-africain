/**
 * Le package importe.
 */
package modele;


/**
 * La classe importee.
 */
import java.util.ArrayList;
import java.util.List;

/**
 *  @author chanson chloé
 */

/**
 * Une partie complexe est composee de plusieurs manches
 * A chaque manche nous distribuons une carte de moins au joueurs. Nous faisons cela jusqu'a que les joueurs ne
 * possèdent plus de carte en mains.
 */
public class PartieComplexe extends Partie {

    /**
     * Constructeur de la classe.
     * Il fait appel a la methode "super()".
     * Cela signifie que la classe "PartieComplexe" appelle le constructeur de la classe "Partie".
     */
    public PartieComplexe() {
        super();
    }


    /*--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------*/


    /**
     * Methode qui permet de creer une nouvelle partie dite complexe.
     * Cette methode fait appelle a la methode "creerPartie()" de la classe "Partie".
     * Cette methode permet de creer une partie avec plusieurs manches, pour chaque manche, il y a une carte en moins
     * dans la liste de cartes de chaque joueur.
     * @param nb_carte :
     * Variable qui represente le nombre de cartes par joueur dans le jeu.
     * @param nb_joueur :
     * Variable qui represente le nombre de joueurs dans le jeu.
     * @return la partie qui vient d'etre cree. Cette partie contient plusieurs manches.
     */
    protected Partie creerPartie(int nb_carte, int nb_joueur) {
        Partie partie = null;
        List<Manche> list = new ArrayList<>();
        for (int i = 0; i < nb_carte; i++) {
            partie = super.creerPartie(nb_carte-i, nb_joueur);
            list.add(partie.getManche());
            }
        partie.setListManche(list);
        return partie;
    }

    /**
     * Methode qui permet de determiner le resultat d'une partie lors d'une partie dite complexe (partie composee de
     * plusieurs manches). Elle permet de mettre a jour la variable "victoire" en fonction du resultat de la partie. Nous
     * prenons en compte le joueur adverse, car nous en avons besoin. En effet dans le cas d'une partie complexe le
     * gagnant de la partie et le joueur qui a obtenu le moins de malus durant la partie.
     * Cette methode permet aussi de retourner un String adapte au resultat
     * de la partie qui sera utilisee pour l'affichage de la boite de dialogue associe.
     * Cette methode utilise la fonction determinerVictoire(int malusJoueur, int malusAdverse) de la classe "Partie".
     * @param joueur : qui represente le joueur qui est en train de jouer.
     * @param partie : corresponds a la partie que le joueur est en train de joueur.
     * @return le resultat de la partie qui sera utilisee pour l'affichage de la boite de dialogue associe.
     * @throws Exception signale que la valeur retournee par la fonction
     * determinerVictoire(int malusJoueur, int malusAdverse) n'est pas valide, nous sommes sense jamais arriver ici.
     */
    protected String resultatPartie(Joueur joueur, Partie partie) throws Exception {
        partie.getManche().setVictoire(null);
        String msg = partie.determinerVictoire(partie.getMalusJoueur(), partie.getMalusAdverse());
        if(msg.compareTo("Vous-avez gagné !")==0 | msg.compareTo("Vous êtes à égalité !")==0){
            partie.getManche().setVictoire(true);
            joueur.setNbPartieGagnee(joueur.getNbPartieGagnee() + 1);
            return  msg + "Vous avez obtenus "+ partie.getMalusJoueur() +
                    " malus alors que votre adversaire en a eut "+ partie.getMalusAdverse()+".";
        }
        else {
            if (msg.compareTo("Vous-avez perdu !")==0) {
            partie.setMalusJoueur(partie.getMalusJoueur() + 1);
            partie.getManche().setVictoire(false);
            joueur.setNbPartiePerdue(joueur.getNbPartiePerdue() + 1);

            return msg + "Vous avez obtenus "+ partie.getMalusJoueur() +
                    " malus alors que votre adversaire en a eut "+ partie.getMalusAdverse()+".";
        } else {
            throw new Exception("Problème le résultat que vous avez obtenue pour cette partie est non valide");
        }
    }
    }



}

