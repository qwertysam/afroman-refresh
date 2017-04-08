package afroman.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

public class MainGame extends Game {

    private static MainGame game;

    public static MainGame instance() {
        return game;
    }

    private static float SCALE = 3F; // TODO get from file
    private OrthographicCamera camera;
    private ScreenViewport viewport;
    private boolean isYInverted;

    SpriteBatch batch;
    Texture img;

    @Override
    public void create() {
        game = this;

        isYInverted = false;

        camera = new OrthographicCamera();
        camera.setToOrtho(isYInverted);
        viewport = new ScreenViewport();
        viewport.setUnitsPerPixel(1 / SCALE);

        batch = new SpriteBatch();
        img = new Texture("badlogic.jpg");
    }

    public int getScreenWidth() {
        return viewport.getScreenWidth();
    }

    public int getScreenHeight() {
        return viewport.getScreenHeight();
    }

    public float getWorldWidth() {
        return viewport.getWorldWidth();
    }

    public float getWorldHeight() {
        return viewport.getWorldHeight();
    }

    public void resize(int width, int height) {
        viewport.update(width, height);
        viewport.getCamera().update();
    }

    @Override
    public void render() {
        Gdx.gl.glClearColor(1, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.setTransformMatrix(getCamera().view);
        batch.setProjectionMatrix(getCamera().projection);
        batch.begin();
        batch.draw(img, 0, 0);
        batch.end();
    }

    @Override
    public void dispose() {
        batch.dispose();
        img.dispose();
    }

    /**
     * Brings the camera to the centre of the screen.
     */
    public void centerCamera() {
        // TODO
    }

    /**
     * @return if the screen is inverted in the Y axis (if the y ordinates start at the bottom or the top).
     */
    public boolean isInverted() {
        return isYInverted;
    }

    public ScreenViewport getViewport() {
        return viewport;
    }

    public OrthographicCamera getCamera() {
        return (OrthographicCamera) viewport.getCamera();
    }
}
