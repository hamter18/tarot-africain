/**
 * Le package importe.
 */
package modele;

/**
 * La classe importee.
 */
import java.util.List;

/**
 *  @author chanson chloé
 */

/**
 * La classe "Mangeur" est une classe qui permet une meilleure encapsulation des donnees. Elle appelle les differentes
 * methode de l'application afin que l'application ne puisse pas acceder directement a ces methodes.
 */
public class Manageur {


    /**
     * Variable non-observable
     * @param partieRapide : variable que permet d'acceder aux methodes la classe "PartieRapide"
     */
    private PartieRapide partieRapide = new PartieRapide();

    /**
     * Variable non-observable
     * @param partieComplexe : variable que permet d'acceder aux methodes la classe "PartieComplexe"
     */
    private PartieComplexe partieComplexe = new PartieComplexe();

    /**
     * Variable non-observable
     * @param partie : variable que permet d'acceder aux methodes la classe "Partie"
     */
    private Partie partie = new Partie();

    /**
     * Variable non-observable
     * @param manche : variable que permet d'acceder aux methodes la classe "Manche"
     */
    private Manche manche = new Manche();

    /**
     * Variable non-observable
     * @param saveJoueur : variable que permet d'acceder aux methodes la classe "SauvegarderJoueur"
     */
    private SauvegarderJoueur saveJoueur = new SauvegarderJoueur();

    /**
     * Variable non-observable
     * @param listeJoueur : variable que permet d'acceder aux methodes la classe "ListeJoueurs"
     */
    private ListeJoueurs listeJoueur = new ListeJoueurs();

    /**
     * Variable non-observable
     * @param tour : variable que permet d'acceder aux methodes la classe "Tour"
     */
    private Tour tour = new Tour();


    /*--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------*/


    /**
     * Constructeur vide de la classe.
     */
    public  Manageur (){}


    /*--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------*/


    /**
     * Methodes qui affectent la classe "ListeJoueurs"
     */

    /**
     * Methode qui permet de rechercher un joueur en particulier dans la liste de joueurs de la classe
     * "ListeJoueurs".
     * @param joueurRechercher : represente le pseudo du joueur que nous recherchons dans la liste de joueurs.
     * @return le joueur recherche ou bien la valeur null si le joueur n'est pas trouve.
     */
    public Joueur rechercherJoueur(String joueurRechercher){
        return listeJoueur.rechercherJoueur(joueurRechercher);
    }

    /**
     * Methode qui permet de supprimer un joueur particulier dans la liste de joueurs de la classe
     * "ListeJoueurs".
     * @param joueur corresponds au nouveau joueur que nous voulons supprimer.
     * */
    public void supprimerUnSeulJoueur (Joueur joueur){
        listeJoueur.supprimerUnSeulJoueur(joueur);
    }

    /**
     * Methode qui permet d'ajouter un joueur dans la liste de joueurs de la classe "ListeJoueurs".
     * @param joueur : corresponds au nouveau joueur que nous voulons ajouter dans la liste "listJoueurObs".
     */
    public void ajouterUnJoueur(Joueur joueur) {
        listeJoueur.ajouterUnJoueur(joueur);
    }

    /**
     * Methode qui permet de remettre le score d'un joueur (son nombre de parties gagnees ou perdues) a zero.
     * @param joueur correspond au joueur dont nous voulons remettre les scores a zero.
     */
    public void remettreScoreJoueurSelectionneAZero(Joueur joueur){
        listeJoueur.remettreScoreJoueurSelectionneAZero(joueur);
    }

    /**
     * Methode qui recherche les doublons dans la liste de joueurs, et qui les supprime s'il y en n'a.
     */
    public void supprimerLesDoublons (){ listeJoueur.supprimerLesDoublons();}


        /*--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------*/


    /**
     * Methodes qui permettent la sérialization
     */

    /**
     * Cette fonction permet l'ecriture des donnees dans un fichier binaire.
     * @param listJoueurs : represente les donnees a serializer
     */
    public void sauvegarderJoueur(ListeJoueurs listJoueurs) {
        saveJoueur.save(listJoueurs);
    }

    /**
     * Cette fonction permet de lire un fichier binaire et d'ecrire ses donnees dans une classe.
     * @return les donnees de la classe deserializee.
     */
    public ListeJoueurs LireFichierDeJoueur() {
        return saveJoueur.load();
    }


    /*--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------*/


    /**
     * Methodes qui permettent de creer les differents types de partie
     */

    /**
     * Methode qui permet de creer une nouvelle partie dite rapide.
     * Cette methode fait appelle a la methode "creerPartie()" de la classe "Partie".
     * @param nb_carte :
     * Variable qui represente le nombre de cartes par joueur dans le jeu.
     * @param nb_joueur :
     * Variable qui represente le nombre de joueurs dans le jeu.
     * @return la partie qui vient d'etre cree.
     */
    public Partie creerPartieRapide(int nb_carte, int nb_joueur){
        return partieRapide.creerPartie(nb_carte,nb_joueur);
    }

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
    public Partie creerPartieComplexe(int nb_carte, int nb_joueur){
        return partieComplexe.creerPartie(nb_carte,nb_joueur);
    }


    /*--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------*/


    /**
     * Methodes qui permettent de "simuler" les actions du joueur adverse.
     */

    /**
     * Methode qui permet a l'application de choisir la carte que le joueur adverse joue lors d'un tour. Ce choix se
     * fait de maniere a aleatoire.
     * @param carteList : corresponds a la liste de cartes du joueur adverse.
     * @return la carte qui va jouer par le joueur adverse.
     */
    public Carte choixCarteAdverse(List<Carte> carteList){
        return manche.choixCarteAdverse(carteList);
    }

    /**
     * Methode qui permet a l'application de "choisir" la prediction pour le joueur adverse.
     * Tout comme dans la vrai regle du "Tarot Africain" la somme de la prediction du joueur adverse, et la prediction
     * du joueur ne peut pas etre egale au nombre de cartes qu'ils possedent dans la main (sinon il y aurait une chance
     * que les deux joueurs puissent respecter leur contrat, et le jeu perdrait de son interet).
     * @param manche : represente la manche que le joueur est en train du jouer.
     * @return represente la manche que le joueur est en train du jouer (la prevision du joueur adverse a ete rajoutee
     * a la manche).
     */
    public Manche choixPrevisionAdverse(Manche manche){
        try{
            return partie.choixPrevisionAdverse(manche);
        }
        catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }



    /*--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------*/


    /**
     * Methodes qui permettent les différents affichages.
     */

    /**
     * Methode qui permet de determiner le resultat d'une partie lors d'une partie dite rapide. Cette methode permet
     * aussi de retourner un String adapte au resultat de la partie qui sera utilisee pour l'affichage de
     * la boite de dialogue associe.
     * @param joueur qui represente le joueur qui est en train de jouer.
     * @param partie corresponds a la partie que le joueur est en train de joueur.
     * @return le resultat de la partie qui sera utilisee pour l'affichage de la boite de dialogue associe.
     */
    public String resultatFinPartieRapide(Joueur joueur, Partie partie){
        return this.partieRapide.resultatPartie(joueur,partie);
    }

    /**
     * Methode qui permet de determiner le resultat d'une partie lors d'une partie dite complexe
     * Cette methode permet aussi de retourner un String adapte au resultat
     * de la partie qui sera utilisee pour l'affichage de la boite de dialogue associe.
     * @param joueur : qui represente le joueur qui est en train de jouer.
     * @param partie : corresponds a la partie que le joueur est en train de joueur.
     * @return le resultat de la partie qui sera utilisee pour l'affichage de la boite de dialogue associe.
     */
    public String resultatFinPartieComplexe(Joueur joueur, Partie partie){
        try {
            return this.partieComplexe.resultatPartie(joueur, partie);
        }
        catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Methode qui permet de determiner le resultat d'une manche lors d'une partie dite complexe. Elle renvoie aussi
     * un String adapte au resultat de la manche qui sera utilisee pour l'affichage de la boite de dialogue associe.
     * @param manche corresponds a la manche que le joueur est en train de jouer (cette variable est un raccourcie pour
     * acceder a la manche).
     * @param partie corresponds a la partie que le joueur est en train de joueur.
     * @return resultat de la manche qui sera utilisee pour l'affichage de la boite de dialogue associe.
     */
    public String resultatManchePartieComplexe(Manche manche, Partie partie){
        return this.manche.resultatManchePartieComplexe(manche,partie);
    }
    /*--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------*/


    /**
     * Methode qui permet de renvoyer les messages (conseils) adaptes a la carte selectionnee par le joueur
     * */


    /**
     * Methode qui permet de conseiller le joueur en renvoyant un String qui varie en fonction de la valeur de la carte
     * passee en parametre.
     * @param carte : Represente la carte selectionnee par le joueur.
     * @return le conseil adapte au joueur, le return "null" est sense jamais arriver.
     */
    public String probaDeGagner (Carte carte){return carte.probaDeGagner(carte);}


    /*--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------*/
    /**
     * Methodes qui permettent de faire fonctionner le jeu.
     * */

    /**
     * Methode qui permet de definir quel joueur est le vainqueur du tour. Cette methode permet aussi de supprimer la
     * carte jouee par le joueur et le joueur adverse. Elle permet egalement de mettre a
     * jour la valeur victoire de cette classe.
     * @param manche : corresponds a la manche que le joueur est en train de jouer.
     * @param carteAdverse : corresponds a la carte que le joueur adverse vient de jouer.
     * @param carteJoueur : corresponds a la carte que le joueur vient de jouer.
     * @return retourne la partie avec les valeurs misent a jour.
     */
    public Manche tour(Manche manche, Carte carteAdverse, Carte carteJoueur){
        try {
            return tour.tour(manche, carteAdverse, carteJoueur);
        }
        catch (Exception e){
            e.printStackTrace();
            return null;
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
    public String determinerMessageTour(Manche manche, Carte carteAdverse, Carte carteJoueur){
        try {
            return tour.determinerMessageTour(manche, carteAdverse, carteJoueur);
        }
        catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }
}
