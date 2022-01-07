package com.emily.callofthebog.Screens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

public class GameOverScreen implements Screen {
    private Viewport viewport;
    private Stage stage;

    private Game game;

    public GameOverScreen(Game game, double[] scores){
        this.game = game;
        viewport = new FitViewport(com.emily.callofthebog.CallofTheBog.V_WIDTH, com.emily.callofthebog.CallofTheBog.V_HEIGHT, new OrthographicCamera());
        stage = new Stage(viewport, ((com.emily.callofthebog.CallofTheBog) game).batch);

        Label.LabelStyle font = new Label.LabelStyle(new BitmapFont(), Color.WHITE);

        Table table = new Table();
        table.center();
        table.setFillParent(true);

        Label gameOverLabel = new Label("GAME OVER", font);
        Label distanceLabel = new Label(String.format("Distance: %,.2f", scores[0]), font);
        Label timeLabel = new Label(String.format("Time Survived %,.2f", scores[1]), font);
        Label playAgainLabel = new Label("Click to Play Again", font);

        table.add(gameOverLabel).expandX();
        table.row();
        table.row();
        table.add(distanceLabel).expandX();
        table.row();
        table.add(timeLabel).expandX();
        table.row();
        table.add(playAgainLabel).expandX().padTop(10f);

        stage.addActor(table);
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        if(Gdx.input.justTouched()) {
            game.setScreen(new PlayScreen((com.emily.callofthebog.CallofTheBog) game));
            dispose();
        }
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        stage.draw();


    }

    @Override
    public void resize(int width, int height) {

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
}
