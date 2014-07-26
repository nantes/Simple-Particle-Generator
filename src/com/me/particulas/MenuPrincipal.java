package com.me.particulas;



import java.io.IOException;
import java.util.ArrayList;

import javax.swing.text.html.HTMLDocument.Iterator;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Buttons;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.XmlReader;
import com.badlogic.gdx.utils.XmlReader.Element;



public class MenuPrincipal extends Game implements ApplicationListener
{
	int	FRAMECOLS;		
	int	FRAMEROWS;		
	
	float posX;
	float posY;
	
	float stateTime;
	int index=0;
	
	String				fileName="example.png";
	int 				frameWidth=128;
	int 				frameHeight=128;
	int 				fileNameNumberFrames;
	float 				frameRate=20;
	float				timeElapsed;
	
	Animation 			walkAnimation;		
	Texture 			walkSheet;		
	TextureRegion[]		walkFrames;			
	TextureRegion		 currentFrame;
	ArrayList<Particulas> particulas = new ArrayList<Particulas>();
	private OrthographicCamera camara;
	SpriteBatch 		batch;
	
	//valores del xml
	int velocidadMinima;
	int velocidadMaxima;
	int gravedad;
	int vidaMinima;
	int vidaMaxima;
	
	
	
	public MenuPrincipal() 
	{
	
		
		 
	}
	
	
	public void resize(int width, int height)
	{
		// TODO Auto-generated method stub
		
	}

	public void render() 
	{
	
		Gdx.gl.glClearColor(1, 1, 1, 1);
		Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
		
		batch.begin();
		
		


      
		//recorro la lista de Objetos SpriteSheets 
        for (int i =0 ; i<particulas.size() ; i ++)
        {
     
        	Particulas ss = particulas.get(i);
        	stateTime = ss.stateTime + Gdx.graphics.getDeltaTime();;
        	currentFrame = walkAnimation.getKeyFrame(stateTime, true);
        	ss.stateTime= stateTime;
        	
        	//si aun no paso el tiempo de vda
        	if(ss.Vida())
        	{
        		//actualizo
        		ss.Recorrido(Gdx.graphics.getDeltaTime());
        		
        		//gravedad direct
        		ss.posicion.y-= (ss.stateTime * gravedad);
        	
        		ss.velocidad-= (gravedad *  ss.stateTime);
 
        		batch.draw(currentFrame, ss.posicion.x, ss.posicion.y);
        	}
        	// si ya paso tiempo de vida, elimino
        	else
        	{
        		//quito del arraylist y libero objeto
        		particulas.remove(i);
        		ss.dispose();
        	}

       
        }
        batch.end();
        
        
        //si presiona boton se agrega continuamente particulas
       	if (Gdx.input.isButtonPressed(Buttons.LEFT))
    	{
       		
       		Vector3 coords = new Vector3(Gdx.input.getX(), Gdx.input.getY(),0);
			camara.project(coords);
			
			//genero al azar velocidad y vida en base minimos y maximos
			int velocidad = MathUtils.random(velocidadMinima, velocidadMaxima);
			int vida = MathUtils.random(vidaMinima, vidaMaxima);
			
			//instancio particula
			Particulas tmpParticulas = new Particulas(coords.x-(frameWidth/2),coords.y-(frameHeight/2), velocidad, vida);

       		//agrego objeto a la lista
       		particulas.add(tmpParticulas);
       		
    	}
		
       	       
		
	
       	
       	
	}

	public void pause() 
	{
		// TODO Auto-generated method stub
		
	}

	public void resume()
	{
		// TODO Auto-generated method stub
		
	}

	public void dispose()
	{
		// TODO Auto-generated method

		
	}

	public void create()
	{
		//creo camara
		camara = new OrthographicCamera();
		camara.setToOrtho(true);
		batch = new SpriteBatch();
		timeElapsed=Gdx.graphics.getDeltaTime();
		
		// por defecto no deja imagenes que no son multiplo de 2. Deshabilito eso.
		walkSheet.setEnforcePotImages(false);
		
		

			walkSheet = new Texture(Gdx.files.internal("assets/data/example.png"));	 //Cargo la textura en variable
		
		// calculo columnas y filas
		FRAMEROWS=walkSheet.getWidth() / frameWidth;
		FRAMECOLS=walkSheet.getHeight() / frameHeight;
		
		
		//  guarda cada frame de forma independiente
		TextureRegion[][] tmp = TextureRegion.split(walkSheet, walkSheet.getWidth() / 
FRAMEROWS, walkSheet.getHeight() / FRAMECOLS);
		

			walkFrames = new TextureRegion[FRAMECOLS * FRAMEROWS];
			
		
		// indexo  los frames de la  anim
		for (int i = 0; i < FRAMEROWS; i++) {
			//Gdx.app.log("row: " , String.valueOf(i));
			for (int j = 0; j < FRAMECOLS; j++) {
				//Gdx.app.log(String.valueOf(i) , String.valueOf(j));
				walkFrames[index++] = tmp[j][i];
			}
		}
		
		//calculo framerate en base 100
		float fframeRate = 1 / frameRate;
	
		//genero y guardo anim
		walkAnimation = new Animation(fframeRate, walkFrames);	
		
		//inicializo tiempo en 0
		stateTime = 0f;						
		
		LeerXML();
				

		 }
	
	public void LeerXML()
	{
		
	
		try {
			XmlReader xml = new XmlReader();
			Element root = xml.parse(Gdx.files.internal("assets/data/particles_config.xml"));
			
			Element velocidad = root.getChildByName("velocidad");
			Element vida = root.getChildByName("vida");
			
			// cargo los valores del XML
			velocidadMinima=velocidad.getInt("min");
			velocidadMaxima=velocidad.getInt("max");
			gravedad = root.getInt("gravedad");
			vidaMinima=vida.getInt("min");
			vidaMaxima=vida.getInt("max");

		} catch(IOException e) {
			e.printStackTrace();
		}
	}
	}











