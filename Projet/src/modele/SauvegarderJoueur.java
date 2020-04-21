/**
 * Le package importe.
 */
package modele;

/**
 * La classe importee.
 */
import java.io.*;

/**
 *  @author chanson chloé
 */

/**
 * La classe "SauvegarderJoueur" permet de gerer la serialisation de la liste de joueur.
 * Cette classe implemente l'interface "Sauvegarder" (interface qui sert a la sauvegarde de donnee).
 */
public class SauvegarderJoueur implements Sauvegarder{

    /**
     * Constructeur vide de la classe.
     */
    public SauvegarderJoueur(){}

    /**
     * Cette fonction permet l'ecriture des donnees dans un fichier binaire du nom de "listeJoueur.txt".
     * @param listJoueurs : represente les donnees a serializer
     */
    public void save(ListeJoueurs listJoueurs) {
        try(FileOutputStream fos = new FileOutputStream("listeJoueur.txt")){
            ObjectOutputStream oss = new ObjectOutputStream(fos);
            oss.writeObject(listJoueurs);
            oss.close();
        }
        catch (IOException e){
            e.printStackTrace();
        }
    }


    /**
     * Cette fonction permet de lire un fichier binaire du nom de "listeJoueur.txt" et d'ecrire
     * ses donnees dans une classe.
     * @return les donnees de la classe deserializee.
     */
    public ListeJoueurs load() {
        ListeJoueurs listJoueurs = new ListeJoueurs();
        try (FileInputStream fis = new FileInputStream("listeJoueur.txt")) {
            ObjectInputStream ois = new ObjectInputStream(fis);
            listJoueurs= (ListeJoueurs) ois.readObject();
        }
        catch (IOException | ClassNotFoundException e){
            e.printStackTrace();
        }
        return listJoueurs;
    }
}