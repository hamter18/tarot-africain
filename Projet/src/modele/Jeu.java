/**
 * Le package importe.
 */
package modele;

/**
 * La classe importee.
 */
import java.util.*;

/**
 *  @author chanson chloé
 */
/**
 * La classe "Jeu" correspond a un jeu de cartes, soit, un ensemble materiel complet des cartes necessaires pour
 * pratiquer un jeu de societe (dans notre cas, nous avons un jeu de compose des 22 cartes de tarot).
 */
public class Jeu {
    /**
     * @param jeu :
     * Collection de cartes, l'ensemble de cette collection constitue le jeu de carte. Nous avons decide de faire de
     * cette liste de cartes grace a une "ArrayList".
     */
    private List<Carte> jeu = new ArrayList<>();


    /*--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------*/


    /**
     * Constructeur de la classe.
     * Il permet de "remplir" notre jeu de cartes.
     */
    Jeu() {
        jeu.add(new Carte("1", "le Bateleur", 1, "/images/carteTarot1.png"));
        jeu.add(new Carte("2", "la Papesse",2, "/images/carteTarot2.png"));
        jeu.add(new Carte("3", "l'Impératrice",3, ("/images/carteTarot3.png")));
        jeu.add(new Carte("4", "l'Empereur", 4, ("/images/carteTarot4.png")));
        jeu.add(new Carte("5", "le Pape", 5, ("/images/carteTarot5.png")));
        jeu.add(new Carte("6", "l'Amoureux",6, ("/images/carteTarot6.png")));
        jeu.add(new Carte("7", "le Chariot ",7, ("/images/carteTarot7.png")));
        jeu.add(new Carte("8", "la Justice ", 8, ("/images/carteTarot8.png")));
        jeu.add(new Carte("9", "l'Ermite ", 9, "/images/carteTarot9.png"));
        jeu.add(new Carte("10", "la Roue de Fortune", 10, ("/images/carteTarot10.png")));
        jeu.add(new Carte("11", "la Force ", 11,"/images/carteTarot11.png"));
        jeu.add(new Carte("12", "la Mort",12, "/images/carteTarot12.png"));
        jeu.add(new Carte("13", "le Pendu ",13, "/images/carteTarot13.png"));
        jeu.add(new Carte("14","la Tempérance ", 14, "/images/carteTarot14.png"));
        jeu.add(new Carte("15", "le Diable ", 15, ("/images/carteTarot14.png")));
        jeu.add(new Carte("16", "la Foudre ou la Maison-Dieu", 16,("/images/carteTarot16.png")));
        jeu.add(new Carte("17", "l'Étoile ", 17, ("/images/carteTarot17.png")));
        jeu.add(new Carte("18", "la Lune ", 18, ("/images/carteTarot18.png")));
        jeu.add(new Carte("19", "le Soleil ", 19, ("/images/carteTarot19.png")));
        jeu.add(new Carte("20", "le Jugement ", 20, ("/images/carteTarot20.png")));
        jeu.add(new Carte("21","le Monde ", 21, ("/images/carteTarot21.png")));
        jeu.add(new Carte("22","l'excuse ", 22, ("/images/carteTarot22.png")));
    }


    /*--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------*/


    /**
     * Methode qui permet de melanger le jeu avant la distribution des cartes.
     * La methode shuffle (List list) permet de permuter de maniere aleatoire la liste specifiee
     * a l'aide d'une source aleatoire.
     */
    private void melangerJeu() {
        Collections.shuffle(jeu);
    }

    /**
     * Methode qui permet de melanger le jeu, et qui selectionne les cartes qui vont etre utilisees pour une manche.
     * @param nbCarte : corresponds au nombre de cartes par joueur.
     * @param nbJoueurs : corresponds au nombre de joueurs.
     * @return la liste de cartes contenant les cartes selectionnees pour jouer une manche.
     */
    protected List<Carte> doRandom(int nbCarte, int nbJoueurs) {
        Jeu jeu = new Jeu();
        nbCarte = nbCarte*nbJoueurs;
        jeu.melangerJeu();
        jeu.jeu = jeu.jeu.subList(0, nbCarte);
        return jeu.jeu;
    }
}