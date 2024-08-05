package ru.belyakov;

import org.apache.commons.math3.stat.descriptive.DescriptiveStatistics;
import org.apache.commons.math3.util.ArithmeticUtils;

import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Random;

public class Main {

    private Random random = new Random();
    private Integer choic;
    private Integer ITERATIONS = 1000;
    private float resultIfChange;
    private float resultIfNoChange;

    public static void main(String[] args) {

        Main main = new Main();
        main.changeOfDoorSelection();
        main.noChangeDoorSelection();
        System.out.printf("Шанс поменять дверь и получить приз = %.2f процента  \n", main.resultIfChange ) ;
        System.out.printf("Шанс поменять дверь и не получить приз = %.2f процента \n", (100 - main.resultIfChange));
        System.out.println("==================================================");
        System.out.printf("Шанс получить приз, если не будем менять дверь = %.2f процента \n ", main.resultIfNoChange);
        System.out.println("==================================================");
        System.out.printf("Выгоднее менять выбор двери, тк шанс забрать приз выше на %.2f процента \n", (main.resultIfChange - main.resultIfNoChange));
        System.out.println();
        System.out.println();
        System.out.println("==================================================");
        System.out.println(main.changeOfDoorSelection());
        System.out.println(main.noChangeDoorSelection());
    }


    //метод создания листа с вводными, где 1 - это коза, а 2 - машина
    private ArrayList<Integer> createDoor() {
        ArrayList<Integer> list = new ArrayList<>();
        for (int i = 1; i < 3; i++) {
            list.add(1);
        }
        list.add(random.nextInt(0, 3), 2);
        return list;
    }

    //метод, который считает вероятнсоть получения выигрыша в
    // случае смены решения и записывает все в hashmap
    private HashMap<Integer, Integer> changeOfDoorSelection() {
        int countWin = 0;
        int countLose = 0;
        HashMap<Integer, Integer> hashMapChangeDoor = new HashMap<>();
        for (int i = 1; i <= ITERATIONS; i++) {
            ArrayList<Integer> list = new ArrayList<>(createDoor());
            choic = list.get(random.nextInt(0, 3));

            if (choic == 2) {
                countLose++;
                choic = 1;
            } else if (choic == 1) {
                countWin++;
                choic = 2;
            }
            hashMapChangeDoor.put(i, choic);
        }
        resultIfChange = (float) countWin / ITERATIONS * 100;
        return hashMapChangeDoor;
    }

    //метод, который считает вероятность получения выигрыша,
    //в случае, если мы не будем менять решение и записывает его в Hashmap
    private HashMap<Integer, Integer> noChangeDoorSelection() {
        HashMap<Integer, Integer> hashMapNoChangeDoor = new HashMap<>();
        int countWin = 0;
        int countLose = 0;
        for (int i = 1; i <= ITERATIONS; i++) {
            ArrayList<Integer> list = new ArrayList<>(createDoor());
            choic = list.get(random.nextInt(0, 3));
            hashMapNoChangeDoor.put(i, choic);
            if (choic == 2) countWin++;
            else countLose++;
        }
        resultIfNoChange = (float) countWin / ITERATIONS * 100;
        return hashMapNoChangeDoor;
    }
}





