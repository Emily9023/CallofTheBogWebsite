package com.emilyn.callofthebog.Sprites;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.physics.box2d.World;


public class Enemies extends InteractiveTileObject{
    public Enemies(World world, TiledMap map, Rectangle bounds){
        super(world, map, bounds);
        fixture.setUserData(this);
        setCategoryFilter(com.emilyn.callofthebog.CallofTheBog.EVIL_BIT);
    }

    @Override
    public void onHit() {

        Gdx.app.log("Coin", "Collision");

    }

}
