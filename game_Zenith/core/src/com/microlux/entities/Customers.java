package com.microlux.entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/**
 * Created by maxlin on 12/9/15.
 */
public class Customers {
    // customer type
    public static final int NORMAL = 0;
    public static final int VIP = 1;
    public static final float VIP_DENSITY = 1f/5f;

    int[] customerQueue;
    int index;

    public Customers() {
        index = 0;
        customerQueue = new int[100];  // 100 customers to serve

        // populate the queue with customers at the specified percentage of VIPs
        for(int i = 0; i < (int) (customerQueue.length * VIP_DENSITY); i++) {
            int random = (int)(Math.random() * 100);
            customerQueue[random] = VIP;
            Gdx.app.log("Customers", "Customer: " + random);
        }

    }

    public int getCustomerType() {
        return customerQueue[index];
    }

    public void nextCustomer() {
        index++;
        if (index > customerQueue.length) {
            // you win basically
            // this is temp
            index = 0;
        }
    }

}
