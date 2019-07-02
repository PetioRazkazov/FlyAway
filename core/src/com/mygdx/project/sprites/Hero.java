package com.mygdx.project.sprites;


import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;
import com.mygdx.project.Game;


public class Hero {
    private static final int gravity=-15;
    private static final int movement=100;
    private Vector3 position;
    private Vector3 velocity;
    private Rectangle bounds;
    private Texture Hero;

    public Hero(int x,int y)
    {
        position=new Vector3(x,y,0);
        velocity=new Vector3(0,0,0);
        Hero=new Texture("Hero.png");
        bounds=new Rectangle(x,y,Hero.getWidth(),Hero.getHeight());
    }

    public void update(float dt){
        if(position.y>0)
        velocity.add(0,gravity,0) ;
        velocity.scl(dt);
        position.add(movement*dt,velocity.y,0);
        velocity.scl(1/dt);
        if(position.y < 0){
            position.y=0;
        }

        bounds.setPosition(position.x,position.y);
    }


    public Vector3 getPosition() {
        return position;
    }

    public Texture getTexture() {
        return Hero;
    }

    public void teleport()
    {
        velocity.y=250;
    }

    public Rectangle getBounds(){
        return bounds;
    }

    public void dispose()
    {
        Hero.dispose();
    }
}
