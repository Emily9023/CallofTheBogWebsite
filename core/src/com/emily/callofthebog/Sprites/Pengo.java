package com.emily.callofthebog.Sprites;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Array;
import com.emily.callofthebog.Screens.PlayScreen;

public class Pengo extends Sprite {
    public enum State {DEAD, RUNNING, STANDING};
    public State currentState;
    public State previousState; //previousState is not used atm but may be implemented later (Nov 7th 2021)

    public World world;
    public Body b2body;

    private TextureRegion PengoStanding;
    private Animation PengoRun;
    private float stateTimer;
    public boolean pengoIsDead;

    //not used rn but might be implemented later (November 7th)
    private boolean runningRight;

    //set death to false until hit evil
    public boolean destroyed = false;


    private TextureRegion img;

    private int pengoSize = 7;


    public Pengo(World world, PlayScreen screen){
        super(screen.getAtlas().findRegion("PengoRunning_Spritesheet"));
        currentState = State.STANDING;
        previousState = State.STANDING;
        stateTimer = 0;

        runningRight = true; // not used atm may be implemented later (Nov 7th)




        this.world = world;
        definePengo();

        Array<TextureRegion> frames = new Array<TextureRegion>();
        for(int i = 1; i < 4; i ++){
            frames.add(new TextureRegion(getTexture(), i* 362, 0, 362, 326));
        }

        PengoRun = new Animation<TextureRegion>(0.1f, frames);
        frames.clear();

        PengoStanding = new TextureRegion(getTexture(),0, 0, 362, 326);
        setBounds(0, 0, 362 / com.emily.callofthebog.CallofTheBog.PPM/10, 326 / com.emily.callofthebog.CallofTheBog.PPM/10);
        setRegion(PengoStanding);
    }

    public void update(float dt){
        //checks if pengo is dead
        if (destroyed){

            Gdx.app.log("PENGO", "has died");
            die();
        }
        setPosition(b2body.getPosition().x - getWidth() /2, b2body.getPosition().y - getWidth() /2);
        setRegion(getFrame(dt));
    }

    private void die() {
        if(!isDEAD()){
            pengoIsDead = true;
        }
    }

    public boolean isDEAD(){

        return pengoIsDead;

    }

    public TextureRegion getFrame(float dt){
        currentState = getState();

        TextureRegion region;
        switch(currentState){
            case RUNNING:
                region = (TextureRegion) PengoRun.getKeyFrame(stateTimer, true);
                break;
            default:
                region = PengoStanding;
                break;
        }

        if ((b2body.getLinearVelocity().x<0 || !runningRight) && !region.isFlipX()){
            region.flip(true, false);
            runningRight = false;
        }
        else if ((b2body.getLinearVelocity().x >0|| runningRight) && region.isFlipX()){
            region.flip(true, false);
            runningRight = true;
        }


        stateTimer = currentState == previousState ? stateTimer + dt : 0;
        previousState = currentState;
        return region;
    }

    //determines what the state of movement of pengo
    public State getState(){

        if(pengoIsDead){
            return State.DEAD;
        }
        else if(b2body.getLinearVelocity().x != 0)
            return State.RUNNING;
        else
            return State.STANDING;
    }

    public void definePengo(){
        BodyDef bdef = new BodyDef();
        bdef.position.set(32 / com.emily.callofthebog.CallofTheBog.PPM, 32 / com.emily.callofthebog.CallofTheBog.PPM);
        bdef.type = BodyDef.BodyType.DynamicBody;
        b2body = world.createBody(bdef);

        FixtureDef fdef = new FixtureDef();
        CircleShape shape = new CircleShape();
        shape.setRadius(pengoSize / com.emily.callofthebog.CallofTheBog.PPM);
        fdef.filter.categoryBits = com.emily.callofthebog.CallofTheBog.PENGO_BIT;
        fdef.filter.maskBits = com.emily.callofthebog.CallofTheBog.DEFAULT_BIT | com.emily.callofthebog.CallofTheBog.BOULDER_BIT | com.emily.callofthebog.CallofTheBog.EVIL_BIT;


        fdef.shape = shape;
        b2body.createFixture(fdef).setUserData(this);
/*

        EdgeShape body = new EdgeShape();
        body.set(new Vector2(pengoSize/ CallofTheBog.PPM, pengoSize/ CallofTheBog.PPM), new Vector2(pengoSize/ CallofTheBog.PPM, pengoSize/ CallofTheBog.PPM));
        fdef.shape = body;
        fdef.isSensor = true;

        b2body.createFixture(fdef).setUserData("body");
*/
    }

    public void onHit(){
        Gdx.app.log("PENGO", "hit");
        destroyed = true;
    }


}