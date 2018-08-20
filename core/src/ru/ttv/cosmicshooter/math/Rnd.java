package ru.ttv.cosmicshooter.math;

import java.util.Random;

public class Rnd {
    private static final Random random = new Random();

    /**
     * Generate random number
     * @param min min value
     * @param max max value
     * @return result
     */

    public static float nextFloat(float min, float max){
        return random.nextFloat()*(max - min) + min;
    }
}
