package com.mygdx.project.States;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.project.Game;

import javax.xml.soap.Text;

public class MenuState extends State {

    private Texture background;
    private Texture platbutton;

    public MenuState(GameStateManager gsm) {
        super(gsm);
        background=new Texture("background.png");
        platbutton=new Texture("playButton.png");
    }

    @Override
    public void handleInput() {
        if(Gdx.input.justTouched())
        {
            gsm.set(new PlayState(gsm));

        }
    }

    @Override
    public void update(float dt) {
        handleInput();

    }

    @Override
    public void render(SpriteBatch sb) {
        sb.begin();
        sb.draw(background,0,0, Game.WIDTH,Game.HEIGHT);
        sb.draw(platbutton,Game.WIDTH/2-(platbutton.getWidth())/2,Game.HEIGHT/2);
        sb.end();
    }

    @Override
    public void dispose()
    {
        background.dispose();
        platbutton.dispose();

    }
}
