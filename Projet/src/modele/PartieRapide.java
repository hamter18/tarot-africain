/**
 * Le package importe.
 */
package modele;

/**
 *  @author chanson chloé
 */

/**
 * Une partie rapide est composer d'une seule est une seule manche.
 */
public class PartieRapide extends Partie {

    /**
     * Constructeur de la classe.
     * Il fait appel a la methode "super()"
     * Cela signifie que la classe "PartieRapide" appelle le constructeur de la classe "Partie"
     */
    public PartieRapide(){
        super();
    }


    /*--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------*/


    /**
     * Methode qui permet de creer une nouvelle partie dite rapide.
     * Cette methode fait appelle a la methode "creerPartie()" de la classe "Partie".
     * @param nb_carte :
     * Variable qui represente le nombre de cartes par joueur dans le jeu.
     * @param nb_joueur :
     * Variable qui represente le nombre de joueurs dans le jeu.
     * @return la partie qui vient d'etre cree.
     */
    protected Partie creerPartie(int nb_carte, int nb_joueur){
        Partie partie = super.creerPartie(nb_carte,nb_joueur);
        return partie;
    }

    /**
     * Methode qui permet de determiner le resultat d'une partie lors d'une partie dite rapide (partie composee d'une
     * seule manche). Elle permet de mettre a jour la variable "victoire" en fonction du resultat de la partie. Nous ne
     * prenons pas en compte le joueur adverse, car nous n'en avons pas besoin (soit le joueur gagne et le joueur
     * adverse perd forcement ou bien l'inverse). Cette methode permet aussi de retourner un String adapte au resultat
     * de la partie qui sera utilisee pour l'affichage de la boite de dialogue associe.
     * @param joueur qui represente le joueur qui est en train de jouer.
     * @param partie corresponds a la partie que le joueur est en train de joueur.
     * @return le resultat de la partie qui sera utilisee pour l'affichage de la boite de dialogue associe.
     */
    protected String resultatPartie(Joueur joueur, Partie partie){
        partie.getManche().setVictoire(null);
        if((partie.getManche().getPrevisionJoueur())==((partie.getManche().getTour().getNbToursGagnesJoueur()))){
            partie.getManche().setVictoire(true);
            joueur.setNbPartieGagnee(joueur.getNbPartieGagnee()+1);
            return "Vous avez gagné la manche ! ";
        }
        else {
            partie.setMalusJoueur(partie.getMalusJoueur()+1);
            partie.getManche().setVictoire(false);
            joueur.setNbPartiePerdue(joueur.getNbPartiePerdue()+1);
            return "Vous avez perdu la manche ! Vous n'avez pas respecté votre contrat. Vous avez gagné " +
                    partie.getManche().getTour().getNbToursGagnesJoueur() + " tour(s) au lieu de " + partie.getManche().getPrevisionJoueur() + ".";
        }
    }
}
