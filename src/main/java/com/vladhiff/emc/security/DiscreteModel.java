package com.vladhiff.emc.security;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Scanner;

public class DiscreteModel {
    final static HashMap<Integer, String> accessObject = new HashMap<>();

    public static void main(String[] args) {
        setAccessObject();
        String[] users = { "Ivan", "Ilya", "Vlad", "Petr", "Gleb" };
        int[][] accessMatrix = getRandomAccessMatrix(users.length);

        // Вывод матрицы доступа в консоль и в файл
        printAccessMatrix(users, accessMatrix);

        Scanner in = new Scanner(System.in);
        System.out.print("Пользователь: ");
        String currentUser;
        do {
            currentUser = in.nextLine();
            if (!Arrays.asList(users).contains(currentUser)) {
                System.out.print("\n Такого пользователя нет \n Пользователь: ");
            }
        } while(!Arrays.asList(users).contains(currentUser));

        int userIndex = Arrays.asList(users).indexOf(currentUser);
        printUserAccess(userIndex, accessMatrix);

        String enteredString;
        do {
            in = new Scanner(System.in);
            System.out.print("Жду ваших указаний > ");
            enteredString = in.nextLine();

            int curEvent = enteredString.equals("read") ?
                    1 : enteredString.equals("write") ?
                    2 : enteredString.equals("grant") ?
                    3 : 4;
            int eventObjet;
            switch (curEvent) {
                case 1:
                    in = new Scanner(System.in);
                    System.out.print("Над каким объектом производится операция? > ");
                    eventObjet = in.nextByte();
                    eventObjet -= 1;
                    if ((accessMatrix[userIndex][eventObjet] & 4) != 0) {
                        System.out.println("Операция прошла успешно!");
                    } else {
                        System.out.println("Отказ в выполнении операции. У вас нет прав для её осуществления.");
                    }
                    break;
                case 2:
                    in = new Scanner(System.in);
                    System.out.print("Над каким объектом производится операция? > ");
                    eventObjet = in.nextByte();
                    eventObjet -= 1;
                    if ((accessMatrix[userIndex][eventObjet] & 2) != 0) {
                        System.out.println("Операция прошла успешно!");
                    } else {
                        System.out.println("Отказ в выполнении операции. У вас нет прав для её осуществления.");
                    }
                    break;
                case 3:
                    in = new Scanner(System.in);
                    System.out.print("Право на какой объект передается? > ");
                    eventObjet = in.nextByte();
                    eventObjet -= 1;
                    if ((accessMatrix[userIndex][eventObjet] & 1) != 0) {
                        in = new Scanner(System.in);
                        System.out.print("Какое право передается? (write - 1; read - 2) > ");
                        int accessN = in.nextByte();

                        accessN = (int ) Math.pow(2, accessN);
                        if ((accessMatrix[userIndex][eventObjet] & accessN) != 0) {
                            in = new Scanner(System.in);
                            System.out.print("Какому пользователю предоставляется право? > ");
                            String user = in.nextLine();

                            if (Arrays.asList(users).contains(user)) {
                                accessMatrix[Arrays.asList(users).indexOf(user)][eventObjet] = accessN;
                                System.out.println("Операция прошла успешно.");
                            } else {
                                System.out.println("Такого пользователя не существует. Операция не выполнена.");
                            }
                        } else {
                            System.out.println("Отказ в выполнении операции. У вас нет прав для её осуществления.");
                        }
                    } else {
                        System.out.println("Отказ в выполнении операции. У вас нет прав для её осуществления.");
                    }
                    break;
            }

        } while (!enteredString.equals("quit"));
        System.out.println("Работа пользователя " + currentUser + " завершена. До свидания.");
    }

    public static int[][] getRandomAccessMatrix(int usersLength) {
        int[][] access;
        access = new int[5][5];

        int[] adminAccess = {7, 7, 7, 7, 7};

        int adminIndex = (int) (Math.random() * 5);
        access[adminIndex] = adminAccess;

        for (int i = 0; i < usersLength; i++) {
            if (i == adminIndex) {
                continue;
            }

            access[i] = getRandomUserAccess();
        }

        return access;
    }

    public static int[] getRandomUserAccess() {
        int[] access;
        access = new int[5];
        for (int i = 0; i < 5; i++) {
            access[i] = (int) (Math.random() * 6);
        }
        return access;
    }

    public static void printAccessMatrix(String[] users, int[][] accessMatrix) {
        System.out.println("Матрица доступа");
        System.out.println("------------------------------------");
        for (String user: users) {
            System.out.print(user + "   ");
        }
        System.out.print("\n");
        for (int[] userAccess: accessMatrix) {
            for (int objectAccess: userAccess) {
                System.out.print(objectAccess);
            }
            System.out.print("  ");
        }
        System.out.print("\n");
        System.out.println("------------------------------------");
    }

    public static void printUserAccess(int userIndex, int[][] accessMatrix) {
        int numberObject = 1;
        System.out.println("Перечень Ваших прав: ");
        for (int accessNumber: accessMatrix[userIndex]) {
            System.out.println("Объект " + numberObject + ": " + accessObject.get(accessNumber));
            numberObject += 1;
        }
    }

    public static void setAccessObject() {
        accessObject.put(0, "Полный запрет");
        accessObject.put(1, "Передача прав");
        accessObject.put(2, "Запись");
        accessObject.put(3, "Запись. Передача прав");
        accessObject.put(4, "Чтение");
        accessObject.put(5, "Чтение. Передача прав");
        accessObject.put(6, "Чтение. Запись");
        accessObject.put(7, "Полный доступ");
    }
}
