package com.mygdx.project.sprites;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;


import org.w3c.dom.css.Rect;

import java.util.Random;

public class Obstacle {
    public static final int Obs_width=84;
    private static final int FLUCTUATION=100;
    private static final int Gap=100;
    private  static final int lowest_opening=120;
    private Rectangle hitboxtop ,hitboxbot;
     private Texture topObstacle, bottomObstacle;
     private Vector2 posOb,posOb2;
     private Random rand;

     public Obstacle(float x){
         topObstacle=new Texture("Obstacle2.png");
         bottomObstacle=new Texture("Obstacle.png");
         rand=new Random();

         posOb=new Vector2(x,rand.nextInt(FLUCTUATION)+Gap+lowest_opening);
         posOb2=new Vector2(x,posOb.y-Gap-bottomObstacle.getHeight());

         hitboxtop=new Rectangle(posOb.x,posOb.y,getTopObstacle().getWidth(),getTopObstacle().getHeight());
         hitboxbot=new Rectangle(posOb2.x,posOb2.y,getBottomObstacle().getWidth(),getBottomObstacle().getHeight());
     }

    public Texture getTopObstacle() {
        return topObstacle;
    }

    public Texture getBottomObstacle() {
        return bottomObstacle;
    }

    public Vector2 getPosOb() {
        return posOb;
    }

    public Vector2 getPosOb2() {
        return posOb2;
    }
    public void reposition(float x)
    {
        posOb.set(x,rand.nextInt(FLUCTUATION)+Gap+lowest_opening);
        posOb2.set(x,posOb.y-Gap-bottomObstacle.getHeight());
        hitboxtop.setPosition(posOb.x,posOb.y);
        hitboxtop.setPosition(posOb2.x,posOb2.y);

    }

    public boolean colides(Rectangle player)
    {
        return player.overlaps(hitboxbot) || player.overlaps(hitboxtop);
    }

    public void dispose(){
         topObstacle.dispose();
         bottomObstacle.dispose();
    }
}
