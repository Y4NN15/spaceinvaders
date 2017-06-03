package fr.unilim.iut.spaceinvaders.model;

import fr.unilim.iut.spaceinvaders.moteurjeu.Commande;
import fr.unilim.iut.spaceinvaders.moteurjeu.Jeu;
import fr.unilim.iut.spaceinvaders.utils.DebordementEspaceJeuException;
import fr.unilim.iut.spaceinvaders.utils.HorsEspaceJeuException;
import fr.unilim.iut.spaceinvaders.utils.MissileException;

public class SpaceInvaders extends Collision implements Jeu {

    int longueur;
    int hauteur;
    Vaisseau vaisseau;
    Missile missile;
    Envahisseur envahisseur;
    Direction direction = Direction.DROITE;

    public SpaceInvaders(int longueur, int hauteur) {
        this.longueur = longueur;
        this.hauteur = hauteur;
    }

    //
    // Espace de jeu en ASCII
    // Pour les tests
    //
    
    @Override
    public String toString() {
        return recupererEspaceJeuDansChaineASCII();
    }

    public String recupererEspaceJeuDansChaineASCII() {
        StringBuilder espaceDeJeu = new StringBuilder();
        for (int y = 0; y < hauteur; y++) {
            for (int x = 0; x < longueur; x++) {
                espaceDeJeu.append(recupererMarqueDeLaPosition(x, y));
            }
            espaceDeJeu.append(Constante.MARQUE_FIN_LIGNE);
        }
        return espaceDeJeu.toString();
    }

    private char recupererMarqueDeLaPosition(int x, int y) {
        char marque;
        if (this.aUnVaisseauQuiOccupeLaPosition(x, y)){
            marque = Constante.MARQUE_VAISSEAU;
        }
        else if (this.aUnMissileQuiOccupeLaPosition(x, y)){
            marque = Constante.MARQUE_MISSILE;
        }
        else if (this.aUnEnvahisseurQuiOccupeLaPosition(x, y)){
        	marque = Constante.MARQUE_ENVAHISSEUR;
        }
        else {
            marque = Constante.MARQUE_VIDE;
        }
        return marque;
    }

 
    
    
    private boolean aUnMissileQuiOccupeLaPosition(int x, int y) {
        return this.aUnMissile() && missile.occupeLaPosition(x, y);
    }

    public boolean aUnMissile() {
        return missile!=null;
    }


    private boolean aUnVaisseauQuiOccupeLaPosition(int x, int y) {
        return this.aUnVaisseau() && vaisseau.occupeLaPosition(x, y);
    }


    public boolean aUnVaisseau() {
        return vaisseau!=null;
    }
    
    
    private boolean aUnEnvahisseurQuiOccupeLaPosition(int x, int y){
    	return this.aUnEnvahisseur() && envahisseur.occupeLaPosition(x, y);
    }

    public boolean aUnEnvahisseur() {
		return envahisseur!=null;
	}
    
	private boolean estDansEspaceJeu(int x, int y) {
        return ((x >= 0) && (x < longueur)) && ((y >= 0) && (y < hauteur));
    }

	

    public void positionnerUnNouveauVaisseau(Dimension dimension, Position position, int vitesse) {

        int x = position.abscisse();
        int y = position.ordonnee();

        if (!estDansEspaceJeu(x, y))
            throw new HorsEspaceJeuException("La position du vaisseau est en dehors de l'espace jeu");

        int longueurVaisseau = dimension.longueur();
        int hauteurVaisseau = dimension.hauteur();

        if (!estDansEspaceJeu(x + longueurVaisseau - 1, y))
            throw new DebordementEspaceJeuException("Le vaisseau deborde de l'espace jeu vers la droite a cause de sa longueur");
        if (!estDansEspaceJeu(x, y - hauteurVaisseau + 1))
            throw new DebordementEspaceJeuException("Le vaisseau deborde de l'espace jeu vers le bas a cause de sa hauteur");

        vaisseau = new Vaisseau(dimension,position,vitesse);
    }
	
    public void deplacerVaisseauVersLaDroite() {
    	if (vaisseau.abscisseLaPlusADroite() < (longueur - 1))
    	{
    		vaisseau.deplacerHorizontalementVers(Direction.DROITE);;
    	}
    	if (!estDansEspaceJeu(vaisseau.abscisseLaPlusADroite(), vaisseau.ordonneeLaPlusHaute())) 
		{
			vaisseau.positionner(longueur - vaisseau.longueur(), vaisseau.ordonneeLaPlusHaute());
		}
    }

    public void deplacerVaisseauVersLaGauche() {
        if (vaisseau.abscisseLaPlusAGauche() > 0)
        { 
        	vaisseau.deplacerHorizontalementVers(Direction.GAUCHE);
        }
        if (!estDansEspaceJeu(vaisseau.abscisseLaPlusAGauche(), vaisseau.ordonneeLaPlusHaute())) {
			vaisseau.positionner(0, vaisseau.ordonneeLaPlusHaute());
		}
    }
    
    public Vaisseau recupererVaisseau() {
		return this.vaisseau;
	}
    

	

	
    public void tirerUnMissile(Dimension dimensionMissile, int vitesse) {
        if ((vaisseau.hauteur()+ dimensionMissile.hauteur()) > this.hauteur )
            throw new MissileException("Pas assez de hauteur libre entre le vaisseau et le haut de l'espace jeu pour tirer le missile");
        this.missile = this.vaisseau.tirerUnMissile(dimensionMissile,vitesse);
    }
    
    public Missile recupererUnMissile(){
    	return this.missile;
    }


	public void deplacerMissile() {
		
		missile.deplacerVerticalementVers(Direction.HAUT_ECRAN);
		
		if (missile.ordonneeLaPlusBasse() < 0)
		{
			missile = null;
		}
	}	
	
	

    
	public void positionnerUnNouvelEnvahisseur(Dimension dimension, Position position, int vitesse) {
		int x = position.abscisse();
        int y = position.ordonnee();

        if (!estDansEspaceJeu(x, y))
            throw new HorsEspaceJeuException("La position de l'envahisseur est en dehors de l'espace jeu");

        int longueurEnvahisseur = dimension.longueur();
        int hauteurEnvahisseur = dimension.hauteur();

        if (!estDansEspaceJeu(x + longueurEnvahisseur - 1, y))
            throw new DebordementEspaceJeuException("L'envahisseur deborde de l'espace jeu vers la droite a cause de sa longueur");
        if (!estDansEspaceJeu(x, y - hauteurEnvahisseur + 1))
            throw new DebordementEspaceJeuException("L'envahisseur deborde de l'espace jeu vers le bas a cause de sa hauteur");

        envahisseur = new Envahisseur(dimension, position, vitesse);
	}
	
	public void deplacerEnvahisseur() {
		if (this.direction == Direction.DROITE){
			if (envahisseur.abscisseLaPlusADroite() < (longueur - 1))
		    {
		    	envahisseur.deplacerHorizontalementVers(Direction.DROITE);;
		    }
		    if (!estDansEspaceJeu(envahisseur.abscisseLaPlusADroite(), envahisseur.ordonneeLaPlusHaute())) 
			{
				envahisseur.positionner(longueur - envahisseur.longueur(), envahisseur.ordonneeLaPlusHaute());
				this.direction = Direction.GAUCHE;
			}
		}
		else if (this.direction == Direction.GAUCHE){
			if (envahisseur.abscisseLaPlusAGauche() > 0)
		    { 
				envahisseur.deplacerHorizontalementVers(direction);
		    }
		    if (!estDansEspaceJeu(envahisseur.abscisseLaPlusAGauche(), envahisseur.ordonneeLaPlusHaute())) {
		       	envahisseur.positionner(0, envahisseur.ordonneeLaPlusHaute());
		       	this.direction = Direction.DROITE;
		    }
		 }
	    }
	
	public Envahisseur recupererEnvahisseur(){
		return this.envahisseur;
	}


	
	
	public void evoluer(Commande commande) {
		
		if (commande.gauche)
		{
			deplacerVaisseauVersLaGauche();
		}
		if (commande.droite){
			deplacerVaisseauVersLaDroite();
		}
		if (commande.tir && !this.aUnMissile())
		{
	           tirerUnMissile(new Dimension(Constante.MISSILE_LONGUEUR, Constante.MISSILE_HAUTEUR),
						Constante.MISSILE_VITESSE);
		}
		if (this.aUnMissile())
		{
			this.deplacerMissile();
		}
		if (this.aUnEnvahisseur())
		{
			this.deplacerEnvahisseur();
		}
		if (missile != null){
			if (detecterCollision(missile, envahisseur)){
				disparitionMissileEtEnvahisseur();
			}
		}
		
	}

	
	
	
	public void initialiserJeu() {
		Position positionVaisseau = new Position(this.longueur/2,this.hauteur-1);
	    Dimension dimensionVaisseau = new Dimension(Constante.VAISSEAU_LONGUEUR, Constante.VAISSEAU_HAUTEUR);
	    positionnerUnNouveauVaisseau(dimensionVaisseau, positionVaisseau, Constante.VAISSEAU_VITESSE);
	    
	    Position positionEnvahisseur = new Position(this.longueur/2, Constante.ENVAHISSEUR_HAUTEUR);
	    Dimension dimensionEnvahisseur = new Dimension(Constante.ENVAHISSEUR_LONGUEUR, Constante.ENVAHISSEUR_HAUTEUR);
	    positionnerUnNouvelEnvahisseur(dimensionEnvahisseur, positionEnvahisseur, Constante.ENVAHISSEUR_VITESSE);
	}	
	
	public void disparitionMissileEtEnvahisseur() {
		this.missile = null;
		this.envahisseur = null;
	}


	public boolean etreFini() {
		if (missile == null && envahisseur == null){
			return true;
		}
		else
		{
			return false;
		}
	}
}










