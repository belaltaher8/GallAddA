package com.example.android.galladda.EntityComponent.Entities.Bullets;

import com.example.android.galladda.EntityComponent.Components.ComponentType;
import com.example.android.galladda.EntityComponent.Components.PositionComponent;
import com.example.android.galladda.EntityComponent.Entities.General.AbstractEntity;

/**
 * Created by Belal Taher on 8/25/2017.
 */

public abstract class BulletEntity extends AbstractEntity {

    public BulletEntity(float x, float y){
        super();
        myComponents.put(ComponentType.Position, new PositionComponent(x,y));
    }
}
