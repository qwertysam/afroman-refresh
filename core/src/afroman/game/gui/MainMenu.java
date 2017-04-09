package afroman.game.gui;

import afroman.game.MainGame;
import afroman.game.io.Setting;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

/**
 * Created by Samson on 2017-04-08.
 */
public class MainMenu implements CameraScreen {

    private Stage stage;

    Label fpsCounter;
    Image img;

    public MainMenu() {
        //Skin skin = new Skin(Gdx.files.internal("skin/craftacular-ui.json"));
        Skin skin = new Skin(Gdx.files.internal("assets/skin/afro.json"));

        stage = new Stage(MainGame.createStandardViewport()); // TODO may need to use some sort of viewport
        Gdx.input.setInputProcessor(stage);
        stage.getViewport().getCamera().position.x = 0;
        stage.getViewport().getCamera().position.y = 0;

        img = new Image(new Texture("assets/badlogic.jpg"));
        stage.addActor(img);
        img.setPosition(50, 2);

        int buttonWidth = 72;
        int buttonHeight = 16;

        int buttonYOffset = -46;
        int buttonSpacing = 6;

        fpsCounter = new Label("FPS: 0", skin);
        fpsCounter.setSize(buttonWidth, buttonHeight);
        fpsCounter.setPosition(-100, 0);
        stage.addActor(fpsCounter);

        final Label title = new Label("The Adventures of Afro Man", skin);
        title.setSize(buttonWidth, buttonHeight);
        title.setPosition(-buttonWidth / 2, buttonYOffset + (4 * (buttonHeight + buttonSpacing)));
        title.setTouchable(null); // Can click through this element
        title.setAlignment(Align.center);
        stage.addActor(title);

        final Label label = new Label("Scale: ", skin);
        label.setSize(buttonWidth, buttonHeight);
        label.setPosition(-buttonWidth / 2, buttonYOffset + (3 * (buttonHeight + buttonSpacing)));
        label.setTouchable(null); // Can click through this element to the bar
        label.setAlignment(Align.center);

        final RoundingSlider slider = new RoundingSlider(1.0F, 10.0F, 0.1F, 10F, false, skin);
        slider.setSize(buttonWidth, buttonHeight);
        slider.setPosition(-buttonWidth / 2, buttonYOffset + (3 * (buttonHeight + buttonSpacing)));
        slider.setValue(MainGame.settings.getFloat(Setting.SCALE));
        slider.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                label.setText("Scale: " + slider.getValue());
                MainGame.game.setScale(slider.getValue());
            }
        });
        label.setText("Scale: " + slider.getValue());
        stage.addActor(slider);
        stage.addActor(label);

        TextButton joinButton = new TextButton("Join", skin, "default");
        joinButton.setSize(buttonWidth, buttonHeight);
        joinButton.setPosition(-buttonWidth / 2, buttonYOffset + (2 * (buttonHeight + buttonSpacing)));
        joinButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                System.out.println("clicked");
            }
        });
        stage.addActor(joinButton);

        TextButton hostButton = new TextButton("Host", skin, "default");
        hostButton.setSize(buttonWidth, buttonHeight);
        hostButton.setPosition(-buttonWidth / 2, buttonYOffset + (1 * (buttonHeight + buttonSpacing)));
        hostButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                System.out.println("clicked");
            }
        });
        stage.addActor(hostButton);

        TextButton exitButton = new TextButton("Exit", skin, "default");
        exitButton.setSize(buttonWidth, buttonHeight);
        exitButton.setPosition(-buttonWidth / 2, buttonYOffset + (0 * (buttonHeight + buttonSpacing)));
        exitButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                Gdx.app.exit();
            }
        });
        stage.addActor(exitButton);

        /*
        joinButton.addListener(new InputListener() {
            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                System.out.println("Touched Up");
            }

            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                System.out.println("Touched Down");
                return true;
            }
        });*/


        /*
        Skin skin = new Skin(Gdx.files.internal("data/uiskin.json"));

        Table main = new Table();
        main.setFillParent(true);

        // Create the tab buttons
        HorizontalGroup group = new HorizontalGroup();
        final Button tab1 = new TextButton("Tab1", skin, "toggle");
        final Button tab2 = new TextButton("Tab2", skin, "toggle");
        final Button tab3 = new TextButton("Tab3", skin, "toggle");
        group.addActor(tab1);
        group.addActor(tab2);
        group.addActor(tab3);
        main.add(group);
        main.row();*/
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {

        fpsCounter.setText("FPS: " + Gdx.graphics.getFramesPerSecond());

        stage.act(delta);
        stage.draw();

        float x = img.getX();
        float y = img.getY();

        if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) x -= 1;
        if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) x += 1;
        if (Gdx.input.isKeyPressed(Input.Keys.UP)) y += 1;
        if (Gdx.input.isKeyPressed(Input.Keys.DOWN)) y -= 1;
        img.setPosition(x, y);

        if (Gdx.input.isKeyPressed(Input.Keys.BACK)) {
            System.out.println("dankmeme22222s");
        }
    }

    @Override
    public void resize(int width, int height) {
        stage.getViewport().update(width, height);
        stage.getViewport().getCamera().update();
    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {
        stage.dispose();
    }

    @Override
    public OrthographicCamera getCamera() {
        return (OrthographicCamera) stage.getCamera();
    }

    @Override
    public ScreenViewport getViewport() {
        return (ScreenViewport) stage.getViewport();
    }
}
