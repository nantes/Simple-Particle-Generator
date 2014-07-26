package com.me.particulas;


import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.sun.org.apache.xpath.internal.operations.Bool;

public class Particulas extends Sprite implements ApplicationListener {



	public Vector2 posicion;
	public Vector2 direccion;
	public float velocidad;
	public float angulo;
	public int vida;
	public float stateTime;
	
	
	

	
						
	public Particulas(float posX , float posY,int velocidad, int vida)
	{
		//guardo posicion y tiempo inicial
		this.velocidad=velocidad ;
		this.vida=vida;
		this.posicion = new Vector2(posX,posY);
		
		//genero al azar direccion
		//this.direccion = new Vector2(MathUtils.random(-1,1),MathUtils.random(-1,1));
		this.angulo = MathUtils.random(0, 360);
		this.direccion = new Vector2(MathUtils.cos(angulo),-MathUtils.sin(angulo));
		direccion.x = direccion.x * velocidad ;
		direccion.y = direccion.y * velocidad ;
		
		stateTime=0;
		
	}
	

	public void present()
	{
	}
	public void render()
	{

	}
	
	public void Recorrido(float deltaTime)
	{
		
		//actualizo movimiento de particulas
		
		posicion.add(direccion);
		//posicion.x=Linear.easeNone  (stateTime, posicion.x, direccion.x*velocidad, vida);
		//posicion.y=Linear.easeNone  (stateTime, posicion.y, direccion.y*velocidad, vida);
		stateTime+=deltaTime;
	}
	
	public Boolean Vida()
	{
		if(stateTime<vida)
			return true;
		else
			return false;
		
	}

	@Override
	public void dispose() {
		return;
		
	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void resize(int arg0, int arg1) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void create() {
		// TODO Auto-generated method stub
		
	}
}


