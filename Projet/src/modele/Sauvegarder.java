/**
 * Le package importe.
 */
package modele;

/**
 *  @author chanson chloé
 */

/**
 * Une interface definit le comportement d'une classe. C'est un ensemble de methodes abstraites. Cette interface definie
 * des methodes destinees a la sauvegarde.
 */
public interface Sauvegarder {
    /**
     * Cette interface a pour objectif la sauvegarde des donnees.
     */

    /**
     * Cette fonction permet l'ecriture des donnees dans un fichier binaire
     * @param listJoueurs : represente les donnees a serializer.
     */
    void save(ListeJoueurs listJoueurs);

    /**
     * Cette fonction permet de lire un fichier binaire est d'ecrire ses donnees dans une classe.
     * @return les donnees de la classe deserializee.
     */
    ListeJoueurs load();
}
