package afroman.game.gui.components;

import afroman.game.MainGame;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

/**
 * Created by samson on 21/04/17.
 */
public class NoisyClickListener extends ClickListener {
    public static Sound buttonUp;
    public static Sound buttonDown;

    public Actor actor;

    public NoisyClickListener(Actor actor) {
        this.actor = actor;
    }

    @Override
    public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
        super.touchUp(event, x, y, pointer, button);
        if (actor instanceof Button && !((Button) actor).isDisabled()) {
            MainGame.game.playSound(buttonUp, 1);
        }
    }

    @Override
    public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
        if (actor instanceof Button && !((Button) actor).isDisabled()) {
            MainGame.game.playSound(buttonDown, 1);
        }
        return super.touchDown(event, x, y, pointer, button);
    }
}
