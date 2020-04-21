/**
 * Le package importe.
 */
package modele;

/**
 * Les classes importees.
 */
import javafx.beans.property.ListProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 *  @author chanson chloé
 */

/**
 * La classe "ListeJoueurs" est une classe qui est serialisable. Elle correspond a l'ensemble des joueurs qui ont joue.
 */
public class ListeJoueurs implements Serializable {

    /**
     * Variable observable
     * @param listJoueurObs :
     * Collection de joueur, l'ensemble de cette collection constitue une collection de joueurs observable.
     */
    private static ObservableList<Joueur> listJoueurObs = FXCollections.observableArrayList();

    /**
     * Variable observable
     * @param listJoueur :
     * Collection de joueur, l'ensemble de cette collection constitue une collection de joueurs observable.
     */
    private static ListProperty<Joueur> listJoueur = new SimpleListProperty<>(listJoueurObs);


    /*--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------*/

    /**
     * Constructeur de la classe.
     * Nous considerons "listJoueurObs" comme static car :
     * les variables statiques n'existent qu'en un seul exemplaire pour toutes les instances de la classe,
     * au contraire des variables objets. Pour resumer, le mot cle static est utilise pour les variables dont on
     * souhaite n'avoir qu'une seule instance dans notre projet, comme ça, on pourrait accéder à leur contenu depuis
     * n'importe quelle autre classe, et meme de lui affecter une nouvelle valeur qui sera partagee par toutes les
     * classes. Ainsi, c'est pour ces raisons que nous avons decide de faire la liste de joueurs un objet static.
     */
    static {/*
            listJoueurObs.add(new Joueur("cloclo", LocalDate.of(2019,8,18),1,10));
            listJoueurObs.add(new Joueur("vava", LocalDate.of(2019,8,18),20,25));
            listJoueurObs.add(new Joueur("hamster", LocalDate.of(2019,11,21),5,0));
           */
   }


    /*--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------*/

    /**
     * Methode qui permet d'ajouter un joueur dans la liste de joueurs "listJoueurObs".
     * @param joueur : corresponds au nouveau joueur que nous voulons ajouter dans la liste "listJoueurObs".
     */
    protected void ajouterUnJoueur (Joueur joueur){
       listJoueurObs.add(joueur);
    }

    /**
     * Methode qui permet de supprimer un joueur particulier dans la liste de joueurs "listJoueurObs".
     * Cette methode utilise la methode  rechercherJoueur(String joueurRechercher).
     * @param joueur corresponds au nouveau joueur que nous voulons supprimer.
     */
    protected void supprimerUnSeulJoueur (Joueur joueur){
        for(int i=0; i<listJoueurObs.size(); i++){
            joueur= rechercherJoueur(joueur.getSurname());
        }
        if(joueur!=null){
            listJoueurObs.remove(joueur);
        }
    }

    /**
     * Methode qui permet de remettre le score d'un joueur (son nombre de parties gagnees ou perdues) a zero.
     * Cette methode utilise la methode  rechercherJoueur(String joueurRechercher).
     * @param joueur correspond au joueur dont nous voulons remettre les scores a zero.
     */
    protected void remettreScoreJoueurSelectionneAZero(Joueur joueur){
        for(int i=0; i<listJoueurObs.size(); i++){
            joueur= rechercherJoueur(joueur.getSurname());
            if(joueur!=null){
                supprimerUnSeulJoueur(joueur);
                ajouterUnJoueur(joueur.remettreScoreJoueurAZero(joueur));
            }
        }
    }

    /**
     * Methode qui permet de rechercher un joueur en particulier dans la liste de joueurs "listJoueurObs"
     * a partir du surname du joueur.
     * @param joueurRechercher : represente le pseudo du joueur que nous recherchons dans la liste de joueurs.
     * @return le joueur recherche ou bien la valeur null si le joueur n'est pas trouve.
     */
    protected Joueur rechercherJoueur(String joueurRechercher){
        for(Joueur str:(listJoueurObs)){
            if (str.getSurname().compareTo(joueurRechercher)==0){
                return str;
            }
        }
        return null;
    }

    /**
     * Methode qui recherche les doublons dans la liste de joueurs, et qui les supprime s'il y en n'a.
     * Cette méthode fait appelle a la methode "equals" de la classe "Joueur".
     */
    protected void supprimerLesDoublons (){
        for(int i=0; i<listJoueurObs.size();i++){
            for(int j=i+1;j<listJoueurObs.size();j++){
                if(listJoueurObs.get(i).equals(listJoueurObs.get(j))){
                    listJoueurObs.remove(listJoueurObs.get(j));
                }
             }
        }
    }


    /*--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------*/

    /**
     * Methodes qui permettent de redefinir les methodes de serialisation afin de serialiser la classe "ListeJoueurs".
     * En effet, cette classe contient des elements non serialisables ... C'est pour cette raison que nous avons du
     * redefinir les methodes readObject() et writeObject().
     */

    /**
     * Methode qui permet de redefinir la methode readObject() afin de lire les donnees du fichier binaire et de
     * "remplir" la liste de joueurs "listJoueurObs" de la classe "ListeJoueurs".
     * @param ois : objet qui permet de deserialiser les donnees primitives et les objets precedemment ecrits à l'aide
     * d'un ObjectOutputStream.
     * @throws IOException : signale qu'une exception d'entrer ou de sortie quelconque s'est produite.
     * Plus exactement, dans cette methode, lorsqu'il y a un probleme lors de lecture dans le fichier binaire.
     * @throws ClassNotFoundException : signale que l'application essaie de se charger dans une classe cependant
     * cette classe n'a pu être trouvee.
     */
    private void readObject(final ObjectInputStream ois) throws IOException, ClassNotFoundException {
        List<Joueur> list;
        list=(ArrayList<Joueur>) ois.readObject();
        for(Joueur str:(list)){
            listJoueurObs.add(str);
        }
    }

    /**
     * Methode qui permet de redefinir la methode writeObject() afin d'ecrire dans le fichier binaire la liste de joueur
     * pour la serialisation.
     * @param oos : objet qui ecrit des types de donnees primitifs et des graphiques d'objets java dans un OutputStream.
     * @throws IOException : signale qu'une exception d'entrer ou de sortie quelconque s'est produite.
     * Plus exactement, dans cette methode, lorsqu'il y a un probleme lors de l'ecriture dans le fichier binaire
     */
    private void writeObject(final ObjectOutputStream oos) throws IOException {
      List<Joueur> list = new ArrayList<>();
        for(Joueur str:(listJoueurObs)){
            list.add(str);
        }
        oos.writeObject(list);
    }


    /*--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------*/

    /**
     * Methode permettant d'influer sur les variables de la classe.
     */

    /**
     * Cette methode permet de retourner la liste de joueurs.
     * @return la liste de joueurs.
     */
    public ObservableList<Joueur> getListJoueur() {return listJoueur.get();}

    /**
     * Methode qui permet de modifier la liste de joueurs.
     * @param value : la nouvelle liste de joueurs.
     */
    public void setListJoueur(ObservableList<Joueur> value) {listJoueur.set(value);}

    /**
     * Cette methode permet de retourner la liste de joueur et de l'afficher dans une fenetre.
     * @return la liste de joueurs.
     */
    public static ListProperty<Joueur> listJoueurProperty() {return listJoueur; }
}
