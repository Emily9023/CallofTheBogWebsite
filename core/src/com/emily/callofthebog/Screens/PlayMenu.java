package com.emily.callofthebog.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.emily.callofthebog.CallofTheBog;
import com.emily.callofthebog.Scenes.PlayMenuButtons;

public class PlayMenu implements Screen {
    private com.emily.callofthebog.CallofTheBog game;

    private OrthographicCamera gameCam;
    private Viewport gamePort;
    private PlayMenuButtons buttons;

    private TmxMapLoader mapLoader; //loads the map
    private TiledMap map;
    private OrthogonalTiledMapRenderer renderer;


    public PlayMenu(CallofTheBog game){

        this.game = game;
        gameCam = new OrthographicCamera();

        //create a FitViewport to maintain virtual aspect ratio despite screen differences
        gamePort = new FitViewport(com.emily.callofthebog.CallofTheBog.V_WIDTH / com.emily.callofthebog.CallofTheBog.PPM, com.emily.callofthebog.CallofTheBog.V_HEIGHT / com.emily.callofthebog.CallofTheBog.PPM, gameCam);

        //create our game HUD for scores/timers/level info
        buttons = new PlayMenuButtons(game.batch);

        //load our map and setup our map renderer
        mapLoader = new TmxMapLoader();
        map = mapLoader.load("level1.tmx");
        renderer = new OrthogonalTiledMapRenderer(map,  1 / com.emily.callofthebog.CallofTheBog.PPM);

        //initially set our gamcacm to be centered correctly at the start of the game
        gameCam.position.set(gamePort.getWorldWidth()/2, gamePort.getWorldHeight()/2, 0); //usually set at (0,0)

    }

    @Override
    public void show() {

    }

    public void handleInput(float dt){

    }

    public void update(float dt){ //updates any inputs
        handleInput(dt);

        gameCam.update(); //update changes in the cam

        renderer.setView(gameCam); //makes the renderer render the gameCam

    }

    @Override
    public void render(float delta) {
        //seperate our update logive from render
        update(delta);


        //Clear the game screen with Black
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        //render our game map
        renderer.render();

        //Set our batch to now draw what the buttons camera seets
        game.batch.setProjectionMatrix(buttons.stage.getCamera().combined); //what is shown via camera
        buttons.stage.draw();



    }

    @Override
    public void resize(int width, int height) {
        //updated our game viewport
        gamePort.update(width, height);
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

    }
}
