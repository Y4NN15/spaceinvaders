package fr.unilim.iut.spaceinvaders;


import fr.unilim.iut.spaceinvaders.utils.HorsEspaceJeuException;
import org.junit.Test;
import org.junit.Before;

import java.lang.annotation.Target;

import static junit.framework.TestCase.*;

public class SpaceInvadersTest {

    private SpaceInvaders spaceInvaders;

    @Before
    public void initialisation(){
        spaceInvaders = new SpaceInvaders(15,10);
    }
    @Test
    public void test_AuDebut_JeuSpaceInvaderEstVide() {
        assertEquals("" +
                "...............\n" +
                "...............\n" +
                "...............\n" +
                "...............\n" +
                "...............\n" +
                "...............\n" +
                "...............\n" +
                "...............\n" +
                "...............\n" +
                "...............\n" , spaceInvaders.recupererEspaceJeuDansChaineASCII());
    }

    @Test
    public void test_unNouveauVaisseauEstCorrectementPositionneDansEspaceJeu() {
        spaceInvaders.positionnerUnNouveauVaisseau(7,9);
        assertEquals("" +
                "...............\n" +
                "...............\n" +
                "...............\n" +
                "...............\n" +
                "...............\n" +
                "...............\n" +
                "...............\n" +
                "...............\n" +
                "...............\n" +
                ".......V.......\n" , spaceInvaders.recupererEspaceJeuDansChaineASCII());
    }

    @Test
    public void test_UnNouveauVaisseauPositionneHorsEspaceJeu_DoitLeverUneException(){

        try{
            spaceInvaders.positionnerUnNouveauVaisseau(15,9);
            fail("Position trop à droite : devrait déclencher une exception HorsEspaceJeuException");
        }
        catch (final HorsEspaceJeuException e){

        }

        try {
            spaceInvaders.positionnerUnNouveauVaisseau(-1,9);
            fail("Position trop à gauche : devrait déclencher une exception HorsEspaceJeuException");
        }
        catch(final HorsEspaceJeuException e) {

        }
        try {
            spaceInvaders.positionnerUnNouveauVaisseau(14,10);
            fail("Position trop en bas : devrait déclencher une exception HorsEspaceJeuException");
        } catch (final HorsEspaceJeuException e) {
        }


        try {
            spaceInvaders.positionnerUnNouveauVaisseau(14,-1);
            fail("Position trop à haut : devrait déclencher une exception HorsEspaceJeuException");
        } catch (final HorsEspaceJeuException e) {
        }
    }

    @Test
    public void test_VaisseauAvance_DeplacerVaisseauVersLaDroite() {

        spaceInvaders.positionnerUnNouveauVaisseau(7,9);

        spaceInvaders.deplacerVaisseauVersLaDroite();

        assertEquals("" +
                "...............\n" +
                "...............\n" +
                "...............\n" +
                "...............\n" +
                "...............\n" +
                "...............\n" +
                "...............\n" +
                "...............\n" +
                "...............\n" +
                "........V......\n" , spaceInvaders.recupererEspaceJeuDansChaineASCII());
    }

    @Test
    public void test_VaisseauImmobile_DeplacerVaisseauVersLaDroite() {

        spaceInvaders.positionnerUnNouveauVaisseau(14,9);

        spaceInvaders.deplacerVaisseauVersLaDroite();

        assertEquals("" +
                "...............\n" +
                "...............\n" +
                "...............\n" +
                "...............\n" +
                "...............\n" +
                "...............\n" +
                "...............\n" +
                "...............\n" +
                "...............\n" +
                "..............V\n" , spaceInvaders.recupererEspaceJeuDansChaineASCII());
    }

    @Test
    public void test_VaisseauAvance_DeplacerVaisseauVersLaGauche() {

        spaceInvaders.positionnerUnNouveauVaisseau(7,9);

        spaceInvaders.deplacerVaisseauVersLaGauche();

        assertEquals("" +
                "...............\n" +
                "...............\n" +
                "...............\n" +
                "...............\n" +
                "...............\n" +
                "...............\n" +
                "...............\n" +
                "...............\n" +
                "...............\n" +
                "......V........\n" , spaceInvaders.recupererEspaceJeuDansChaineASCII());
    }

    @Test
    public void test_VaisseauImmobile_DeplacerVaisseauVersLaGauche() {

        spaceInvaders.positionnerUnNouveauVaisseau(0,9);

        spaceInvaders.deplacerVaisseauVersLaGauche();

        assertEquals("" +
                "...............\n" +
                "...............\n" +
                "...............\n" +
                "...............\n" +
                "...............\n" +
                "...............\n" +
                "...............\n" +
                "...............\n" +
                "...............\n" +
                "V..............\n" , spaceInvaders.recupererEspaceJeuDansChaineASCII());
    }
}