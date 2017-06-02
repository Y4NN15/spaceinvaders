package fr.unilim.iut.spaceinvaders;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import fr.unilim.iut.spaceinvaders.model.Dimension;
import fr.unilim.iut.spaceinvaders.model.Position;
import fr.unilim.iut.spaceinvaders.model.SpaceInvaders;
import fr.unilim.iut.spaceinvaders.model.Collision;

public class CollisionTest extends Collision {

	private SpaceInvaders spaceInvaders;
	
	@Before
    public void initialisation(){
        spaceInvaders = new SpaceInvaders(15,10);
    }
	
	@Test
	public void test_collisionDetecteeEntreMissileEtEnvahisseur() {
		spaceInvaders.positionnerUnNouvelEnvahisseur(new Dimension(6,2), new Position(0, 1), 1);
		spaceInvaders.positionnerUnNouveauVaisseau(new Dimension(7,2),new Position(0,9), 2);
		spaceInvaders.tirerUnMissile(new Dimension(3,2),2);

		spaceInvaders.deplacerMissile();
		spaceInvaders.deplacerMissile();
		spaceInvaders.deplacerMissile();
		
		assertEquals(true, detecterCollision(spaceInvaders.recupererUnMissile(), spaceInvaders.recupererEnvahisseur()));
	}

	@Test
	public void test_collisionDetectee_JeuFini(){
		spaceInvaders.positionnerUnNouvelEnvahisseur(new Dimension(6,2), new Position(0, 1), 1);
		spaceInvaders.positionnerUnNouveauVaisseau(new Dimension(7,2),new Position(0,9), 2);
		spaceInvaders.tirerUnMissile(new Dimension(3,2),2);

		spaceInvaders.deplacerMissile();
		spaceInvaders.deplacerMissile();
		spaceInvaders.deplacerMissile();
		
		spaceInvaders.disparitionMissileEtEnvahisseur();
		
		assertEquals(true, spaceInvaders.etreFini());
	}
}
