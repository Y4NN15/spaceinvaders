package fr.unilim.iut.spaceinvaders.model;

import fr.unilim.iut.spaceinvaders.utils.MissileException;

public class Vaisseau extends Sprite {

    public Vaisseau(Dimension dimension, Position positionOrigine, int vitesse) {
        super(dimension, positionOrigine, vitesse);
    }


    public Missile tirerUnMissile(Dimension dimensionMissile, int vitesseMissile) {

        if (dimensionMissile.longueur() > dimension.longueur()){
            throw new MissileException("La longueur du missile est supérieur à celle du vaisseau");
        }
        else{
            Position positionOrigineMissile = CalculerLaPositionDeTirMissile(dimensionMissile);
            return new Missile(dimensionMissile, positionOrigineMissile, vitesseMissile);
        }
    }

    private Position CalculerLaPositionDeTirMissile(Dimension dimensionMissile) {
        int abscisseMilieuVaisseau = abscisseLaPlusAGauche() + (this.longueur() / 2);
        int abscisseOrigineMissile = abscisseMilieuVaisseau - (dimensionMissile.longueur() / 2);

        int ordonneeeOrigineMissile = this.ordonneeLaPlusBasse() - 1;
        return new Position(abscisseOrigineMissile, ordonneeeOrigineMissile);
    }
}