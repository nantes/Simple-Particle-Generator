package com.me.particulas;








import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;





public class Main {

		public static void main(String[] args) {
		
	
	


		
		LwjglApplicationConfiguration cfg = new LwjglApplicationConfiguration();
		cfg.title = "Sprite Sheet";
		cfg.useGL20 = false;
		cfg.width = 800;
		cfg.height = 600;
		new LwjglApplication(new MenuPrincipal(), cfg);
		
	}

}

