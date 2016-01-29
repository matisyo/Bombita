package game.model.bombs;


import game.model.Proyectile;

public class BombProyectile implements BombFactory{
	
	public Proyectile create(){
		Proyectile b = new Proyectile(1500000, 1, 5,10);
		
		return b;            
	}

}
