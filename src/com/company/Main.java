package com.company;

import java.util.Scanner;

public class Main {
    static final int monthInYear = 12;
    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        String userAnswer;
        while (true) {
            System.out.println("Для рассчета прибыли по вкладу с пополнением - введите '1'.\n" +
                    "Для рассчета прибыли по вкладу без пополнения - введите '2'\n" +
                    "Для выхода - нажмите '3'");

            userAnswer = scanner.next();
            if (userAnswer.equals("1")) donationCalculator();
            else if (userAnswer.equals("2")) sumCalculator();

            System.out.print("\n");
        }
    }

    static void donationCalculator() {
        int month, couponsPerYear;
        double percent, donatePerMonth, profit;


        System.out.println("Срок накопления (мес.):");
        month = scanner.nextInt();
        System.out.println("Ежемесячный платеж (руб.):");
        donatePerMonth = scanner.nextInt();
        System.out.println("Процентная ставка:");
        percent = scanner.nextDouble();
        System.out.println("Купонов в год:");
        couponsPerYear = scanner.nextInt();

        profit = calculateProfitForReplenishingSum(month, donatePerMonth, percent, couponsPerYear);
        System.out.printf("Накопленная сумма:\t\t\t%.2fруб.\n", profit);
        System.out.printf("Из них вложенные:\t\t\t%.2fруб.\n", donatePerMonth * month);
        System.out.printf("Накопленные по процентам:\t%.2fруб.\n", profit - donatePerMonth * month);
    }
    static void sumCalculator() {
        int month, couponsPerYear;
        double percent, money, profit;

        System.out.println("Срок накопления (мес.):");
        month = scanner.nextInt();
        System.out.println("Вкладываемая сумма (руб.):");
        money = scanner.nextInt();
        System.out.println("Процентная ставка:");
        percent = scanner.nextDouble();
        System.out.println("Купонов в год:");
        couponsPerYear = scanner.nextInt();

        profit = calculateProfitForFixedSum(month, money, percent, couponsPerYear);
        System.out.printf("Накопленная сумма: %.2fруб.\n", profit);
        System.out.printf("Из них начисленные по процентам: %.2fруб.\n", profit - money);
    }
    static double calculateProfitForReplenishingSum(int months, double donate, double percent, int couponsPerYear) {
        double currentSum = 0;
        double collectedFromPercents = 0;

        for (int month = 0; month < months; month++) {
            currentSum += donate;
            collectedFromPercents += (currentSum / 100 * percent) / monthInYear;

            if ((month + 1) % (monthInYear / couponsPerYear) == 0) {
                currentSum += collectedFromPercents;
                collectedFromPercents = 0;
            }
        }

        return currentSum;
    }
    static double calculateProfitForFixedSum(int months, double money, double percent, int couponsPerYear) {
        double collectedFromPercents = 0;

        for (int month = 0; month < months; month++) {
            collectedFromPercents += money / 100 * percent / monthInYear;

            if ((month + 1) % (monthInYear / couponsPerYear) == 0) {
                money += collectedFromPercents;
                collectedFromPercents = 0;
            }
        }

        return money;
    }
}
