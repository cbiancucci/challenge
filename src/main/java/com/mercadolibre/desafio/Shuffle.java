package com.mercadolibre.desafio;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Shuffle {

    public static void main(String[] args) {

        List<Integer> shuffledItems = shuffle(1, 10);
        System.out.println(shuffledItems);

        List<String> shuffledStringItems = shuffle(Arrays.asList(new String[]{
                "one", "two", "three", "four", "five", "six", "seven", "eight"
        }));

        System.out.println(shuffledStringItems);
    }

    public static List<Integer> shuffle(Integer start, Integer count) {

        List<Integer> items = new ArrayList<Integer>(count);
        List<Integer> shuffledItems = new ArrayList<Integer>(count);

        for (int i = 0; i < count; i++) {
            items.add(start + i);
        }

        while (items.size() > 0) {
            shuffledItems.add(items.remove((int) (1729 * Math.random()) % items.size()));
        }

        return shuffledItems;
    }

    public static List<String> shuffle(List<String> items) {

        List<Integer> shuffledItems = shuffle(0, items.size());
        List<String> shuffledStringItems = new ArrayList<String>(items.size());

        for (int i = 0; i < shuffledItems.size(); i++) {
            shuffledStringItems.add(items.get(shuffledItems.get(i)));
        }

        return shuffledStringItems;
    }
}
