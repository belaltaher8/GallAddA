package com.example.android.galladda.EntityComponent.Entities.Enemies;

import com.example.android.galladda.EntityComponent.Components.ComponentType;
import com.example.android.galladda.EntityComponent.Components.PositionComponent;
import com.example.android.galladda.EntityComponent.Components.VelocityComponent;
import com.example.android.galladda.EntityComponent.Entities.Bullets.BadBulletEntity;
import com.example.android.galladda.EntityComponent.Entities.General.AbstractEntity;

/**
 * Created by Belal Taher on 8/25/2017.
 */

public abstract class AbstractEnemy extends AbstractEntity {

    public AbstractEnemy(){
        super();
    }

    public BadBulletEntity shoot(){
        PositionComponent enemyPos = (PositionComponent) this.getComponent(ComponentType.Position);
        BadBulletEntity myBullet = new BadBulletEntity(enemyPos.getX() +50, enemyPos.getY());
        VelocityComponent bulletVC = (VelocityComponent) myBullet.getComponent(ComponentType.Velocity);
        return myBullet;
    }
}
