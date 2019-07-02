package com.mygdx.project.States;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Array;
import com.mygdx.project.Game;
import com.mygdx.project.sprites.Hero;
import com.mygdx.project.sprites.Obstacle;

public class PlayState extends State {
    private static final int obstacle_spacing=125;
    private static final int obstacle_count=4;
    private Hero hero;
    private Texture bg;
    private Array<Obstacle> obs;

    public PlayState(GameStateManager gsm) {
        super(gsm);
        hero=new Hero(50,300);
        cam.setToOrtho(false, Game.WIDTH/2,Game.HEIGHT/2);
        bg= new Texture("background.png");

        obs=new Array<Obstacle>();
        for(int i=1;i<=obstacle_count;i++)
        {
            obs.add(new Obstacle(i*(obstacle_spacing+Obstacle.Obs_width)));
        }
    }

    @Override
    protected void handleInput() {
        if(Gdx.input.justTouched())
        {
            hero.teleport();
        }
    }

    @Override
    public void update(float dt) {
        handleInput();
        hero.update(dt);
        cam.position.x= hero.getPosition().x +80;
        for (int i=0;i<obs.size;i++)
        {
            Obstacle obstacle=obs.get(i);
            if(cam.position.x-(cam.viewportWidth/2)>obstacle.getPosOb().x+obstacle.getTopObstacle().getWidth())
            {
                obstacle.reposition(obstacle.getPosOb().x+ ((Obstacle.Obs_width+obstacle_spacing))*obstacle_count);
            }

            //ne e za preporu4vane ama kvo da se pravi
            if(obstacle.colides(hero.getBounds()))
            {
                gsm.set(new PlayState(gsm));
            }
        }

        cam.update();
    }

    @Override
    public void render(SpriteBatch sb) {
        sb.setProjectionMatrix(cam.combined);
        sb.begin();
        sb.draw(bg,cam.position.x-(cam.viewportWidth/2),0);
        sb.draw(hero.getTexture(),hero.getPosition().x,hero.getPosition().y);
       // sb.draw(obstacle.getTopObstacle(),obstacle. getPosOb().x,obstacle.getPosOb().y);
        //sb.draw(obstacle.getBottomObstacle(),obstacle.getPosOb2().x,obstacle.getPosOb2().y);
        for(Obstacle obstacle:obs)
        {
            sb.draw(obstacle.getTopObstacle(),obstacle. getPosOb().x,obstacle.getPosOb().y);
            sb.draw(obstacle.getBottomObstacle(),obstacle.getPosOb2().x,obstacle.getPosOb2().y);
        }
        sb.end();
    }

    @Override
    public void dispose() {
        bg.dispose();
        hero.dispose();
        for(Obstacle obstacle:obs)
        {
            obstacle.dispose();
        }
    }
}
