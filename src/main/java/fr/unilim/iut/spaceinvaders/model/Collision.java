package fr.unilim.iut.spaceinvaders.model;

public class Collision {
	
	
	public boolean detecterCollision(Sprite spriteQuiVaToucher, Sprite spriteQuiVaEtreTouche){
		
		boolean collision = false;
		
		if (spriteQuiVaToucher != null && spriteQuiVaEtreTouche != null)
		{
			if (spriteQuiVaToucher.origine.abscisse() >= spriteQuiVaEtreTouche.origine.abscisse() && spriteQuiVaToucher.origine.abscisse() < spriteQuiVaEtreTouche.abscisseLaPlusADroite()){
				if (spriteQuiVaToucher.origine.ordonnee() <= spriteQuiVaEtreTouche.origine.ordonnee()){
					collision = true;
				}
				else
				{
					collision = false;
				}
			}
		}
		
		return collision;
		
	}
	
	
}
