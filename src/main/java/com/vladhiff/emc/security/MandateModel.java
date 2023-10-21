package com.vladhiff.emc.security;

import java.util.*;

public class MandateModel {
    final static String[] accessLevels = {"Открытые данные", "Секретно", "Совершенно секретно"};
    final static String[] users = {"Ivan", "Ilya", "Vlad"};
    final static int objectNumber = 4;
    final static int subjectNumber = 3;
    public static void main(String[] args) {
        String[] objectAccesses = getAccessLevels(objectNumber);
        String[] subjectAccesses = getAccessLevels(subjectNumber);

        printObjectAccess(objectAccesses);
        printSubjectAccess(subjectAccesses);

        Scanner in = new Scanner(System.in);
        System.out.print("Пользователь: ");
        String currentUser;
        do {
            currentUser = in.nextLine();
            if (!Arrays.asList(users).contains(currentUser)) {
                System.out.print("\n Такого пользователя нет \n Пользователь: ");
            }
        } while(!Arrays.asList(users).contains(currentUser));

        int[] accessIndexes = getUserAccessAndPrint(currentUser, subjectAccesses, objectAccesses);

        String request;
        do {
            in = new Scanner(System.in);
            System.out.print("Жду ваших указаний > ");
            request = in.nextLine();

            if (request.equals("request")) {
                in = new Scanner(System.in);
                System.out.print("К какому объекту хотите осуществить доступ? > ");
                int objectNum = in.nextByte();

                boolean containsObjectNum = false;
                for (int i = 0; i < accessIndexes.length; i++) {
                    if (accessIndexes[i] == objectNum) {
                        containsObjectNum = true;
                        break;
                    }
                }

                if (containsObjectNum) {
                    System.out.println("Операция прошла успешно.");
                } else {
                    System.out.println("Отказ в выполнении операции. Недостаточно прав.");
                }

            } else if (!request.equals("quit")) {
                System.out.println("Нераспознанный запрос. Попробуйте снова.");
            }
        } while (!request.equals("quit"));

        System.out.println("Работа пользователя " + currentUser + " завершена. До свидания.");
    }

    public static String[] getAccessLevels(int objectN) {
        String[] access;
        access = new String[objectN];

        for (int i = 0; i != objectN; i++) {
            int randAccessIndex = (int) (Math.random() * accessLevels.length);
            access[i] = accessLevels[randAccessIndex];
        }

        return access;
    }

    public static void printObjectAccess(String[] objectAccesses) {
        System.out.println("Уровни конфиденциальности объектов (ОV):");
        System.out.println("------------------------------------");
        int numberObject = 1;
        for (String access: objectAccesses) {
            System.out.println("Объект " + numberObject + ": " + access);
            numberObject += 1;
        }
        System.out.println("------------------------------------");
    }

    public static void printSubjectAccess(String[] subjectAccesses) {
        System.out.println("Уровни допуска пользователей (UV):");
        System.out.println("------------------------------------");
        int numberSubject = 0;
        for (String access: subjectAccesses) {
            System.out.println(users[numberSubject] + ": " + access);
            numberSubject += 1;
        }
        System.out.println("------------------------------------");
    }

    public static int[] getUserAccessAndPrint(String userName, String[] subjectAccesses, String[] objectAccesses) {
        int userIndex = Arrays.asList(users).indexOf(userName);
        String subjectAccess =  subjectAccesses[userIndex];
        int subjectAccessIndex = Arrays.asList(accessLevels).indexOf(subjectAccess);

        int count = 0;
        int[] accessIndexes;
        accessIndexes = new int[objectNumber];
        int j = 0;
        System.out.print("Перечень доступных объектов: ");
        for (int i = 0; i < objectAccesses.length; i++) {
            String objectAccess =  objectAccesses[i];
            int objectAccessIndex = Arrays.asList(accessLevels).indexOf(objectAccess);

            if (subjectAccessIndex >= objectAccessIndex) {
                System.out.print("Объект" + (i + 1) + "; ");
                count += 1;
                accessIndexes[j] = i + 1;
                j += 1;
            }
        }
        System.out.print("\n");
        if (count == 0) {
            System.out.println("Нет доступных объектов :(");
        }

        return accessIndexes;
    }
}
