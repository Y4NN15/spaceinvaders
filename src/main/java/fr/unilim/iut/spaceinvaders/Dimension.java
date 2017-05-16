package fr.unilim.iut.spaceinvaders;

/**
 * Created by knarc on 16/05/2017.
 */
public class Dimension {
    int longueur;
    int hauteur;

    public Dimension(int longueur, int hauteur) {
        this.longueur = longueur;
        this.hauteur = hauteur;
    }

    public int longueur() {
        return this.longueur;
    }

    public int hauteur() {
        return this.hauteur;
    }
}
