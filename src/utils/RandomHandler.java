package utils;

import java.util.Random;

public class RandomHandler {

    private static final Random RANDOM = new Random();
    public static Long generateRandomLong() {
        Long randomNumber = Math.abs(RANDOM.nextLong());
        return randomNumber;
    }
}
