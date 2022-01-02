package com.emilyn.callofthebog.Sprites;

import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTile;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.Filter;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;


public abstract class InteractiveTileObject {
    protected World world;
    protected TiledMap map;
    protected TiledMapTile tile;
    protected Rectangle bounds;
    protected Body body;

    protected Fixture fixture;

    public InteractiveTileObject(World world, TiledMap map, Rectangle bounds){
        this.world = world;
        this.map = map;
        this.bounds = bounds;


        BodyDef bdef = new BodyDef();
        FixtureDef fdef = new FixtureDef();
        PolygonShape shape = new PolygonShape();

        bdef.type = BodyDef.BodyType.StaticBody; //defines that the body is static
        bdef.position.set((bounds.getX() + bounds.getWidth() / 2) / com.emilyn.callofthebog.CallofTheBog.PPM, (bounds.getY() + bounds.getHeight() / 2) / com.emilyn.callofthebog.CallofTheBog.PPM); //sets the position of where the body is (position of the rect

        body = world.createBody(bdef); //create a body in the world according to the specifications of bdef

        shape.setAsBox(bounds.getWidth() / 2 / com.emilyn.callofthebog.CallofTheBog.PPM, bounds.getHeight() /2 / com.emilyn.callofthebog.CallofTheBog.PPM ); //set shape
        fdef.shape = shape; //initialize the shape of the fixture definition
        fixture = body.createFixture(fdef); //sets the fixture of the body according to the specifications of fdef



    }

    public void onHit(){

    }

    public void setCategoryFilter(short filterBit){
        Filter filter = new Filter();
        filter.categoryBits = filterBit;
        fixture.setFilterData(filter);
    }
}

