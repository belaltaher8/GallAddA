package com.example.android.galladda.Model.Engines;

import com.example.android.galladda.EntityComponent.Entities.General.EntityManager;

/**
 * Created by Belal Taher on 8/15/2017.
 */

public abstract class AbstractEngine{

    protected EntityManager myEM;

    public AbstractEngine(EntityManager aEM){
        myEM = aEM;
    }

    public abstract void update();

    public void attachEM(EntityManager aEM){
        myEM = aEM;
    }

}
