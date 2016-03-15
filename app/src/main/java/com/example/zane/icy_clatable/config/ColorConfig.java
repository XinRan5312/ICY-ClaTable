package com.example.zane.icy_clatable.config;

import com.example.zane.icy_clatable.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by Zane on 16/3/15.
 */
public class ColorConfig {

    private static List<Integer> Colors = new ArrayList<>();

    private static final Random RANDOM = new Random();

    private static int getRandomColor() {
        switch (RANDOM.nextInt(5)) {
            default:
            case 0:
                return R.color.class1;
            case 1:
                return R.color.class2;
            case 2:
                return R.color.class3;
            case 3:
                return R.color.class4;
            case 4:
                return R.color.class5;
        }
    }

    public static List<Integer> getAllColor(){
        for (int i = 0; i < 42; i++){
            Colors.add(getRandomColor());
        }

        return Colors;
    }

}
