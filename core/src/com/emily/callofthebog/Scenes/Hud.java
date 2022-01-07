package com.emily.callofthebog.Scenes;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.Disposable;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

public class Hud implements Disposable {
    public Stage stage;
    private Viewport viewport; //seperate for the tangible world

    private Integer worldTimer;
    private double worldTimertest;
    private float timeCount;
    private double score;

    Label countdownLabel;
    Label scoreLabel;
    Label timeLabel;
    Label levelLabel;
    Label worldLabel;
    Label bogLabel;


    public Hud(SpriteBatch sb){
        worldTimer = 0;
        timeCount = 0;
        score = 0;

        viewport = new FitViewport(com.emily.callofthebog.CallofTheBog.V_WIDTH, com.emily.callofthebog.CallofTheBog.V_HEIGHT, new OrthographicCamera());
        stage = new Stage(viewport, sb);

        Table table = new Table();
        table.top();
        table.setFillParent(true);

        countdownLabel = new Label(String.format("%,.2f", worldTimertest), new Label.LabelStyle(new BitmapFont(), Color.WHITE));
        scoreLabel = new Label(String.format("%,.2f", score), new Label.LabelStyle(new BitmapFont(), Color.WHITE));
        timeLabel = new Label("TIME", new Label.LabelStyle(new BitmapFont(), Color.WHITE));
        levelLabel = new Label("1-1", new Label.LabelStyle(new BitmapFont(), Color.WHITE));
        worldLabel = new Label("WORLD", new Label.LabelStyle(new BitmapFont(), Color.WHITE));
        bogLabel= new Label("Bog", new Label.LabelStyle(new BitmapFont(), Color.WHITE));

        table.add(bogLabel).expandX().padTop(10); //expands to length of screen
        table.add(worldLabel).expandX().padTop(10); //expands to length of screen
        table.add(timeLabel).expandX().padTop(10); //expands to length of screen
        table.row();//adds new row
        table.add(scoreLabel).expandX(); //expands to length of screen
        table.add(levelLabel).expandX(); //expands to length of screen
        table.add(countdownLabel).expandX(); //expands to length of screen

        stage.addActor(table);//adds table to stage
    }

    public void update(float dt, float distance) {
        timeCount += dt;

        //bigger than one second then increment world timer by one sec

        if (timeCount >= 0.01){
            worldTimertest= worldTimertest + 0.01;

            countdownLabel.setText(String.format("%,.2f", worldTimertest));
            timeCount = 0;
        }

        score = distance;

        scoreLabel.setText(String.format("%,.2f", distance));

    }



    @Override
    public void dispose() {

        stage.dispose();
    }

    public double[] getScores() {

        double[] scores = new double[]{score, worldTimertest};

        return scores;

    }
}
