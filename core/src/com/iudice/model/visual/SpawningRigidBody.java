package com.iudice.model.visual;

import com.iudice.model.phys.RigidBody;

public class SpawningRigidBody<T extends RigidBody>
{

    public float x;
    public float y;
    public Class<? extends T> type;

    public SpawningRigidBody(float x, float y, Class<? extends T> type) {
        this.x = x;
        this.y = y;
        this.type = type;
    }
}
