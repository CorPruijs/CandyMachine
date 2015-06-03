package com.company;

import java.util.*;

public class CandyMachine {

    public ArrayList<Candy> candyList = new ArrayList<Candy>();
    public int[] coinTypeList = {200, 100, 50, 20, 10, 5, 2, 1};

    int balance = 673;

    public CandyMachine() {

        fillCandyList();

    }

    boolean enoughCandyLeft(Candy candy) {
        return candy.amountLeft > 0;
    }

    boolean hasEnoughBalanceToBuyCandy(Candy candy) {
        return balance - candy.price >=0;
    }


    void fillCandyList() {

        candyList.add(new Candy("Mars", 1, 90, 5));
        candyList.add(new Candy("Twix", 2, 105, 15));
        candyList.add(new Candy("KitKat", 3, 80, 30));
        candyList.add(new Candy("Snickers", 4, 70, 20));
        candyList.add(new Candy("Autodrop", 5, 90, 30));

    }
    double balanceInEuro() {

        return (double) balance / 100;
    }

    String formatedBalanceInEuro() {
        return String.format("€%.2f", balanceInEuro());
    }

    void printInventory() {

        for (int i = 0; i < candyList.size(); i++ ) {
            System.out.println( candyList.get(i).description());
        }

    }

    void addMoney(int amount) {

        balance += amount;
    }

    Candy findCandy(int code) {

        for (Candy candy : candyList) {

            if (candy.code == code) {
                return candy;
            }
        }

        return null;
    }

    Candy buyCandy(int code) {

        Candy candy = findCandy(code);

        if (candy != null) {

            balance -= candy.price;
            candy.amountLeft--;
            return candy;
        }

        else {
            return null;
        }

    }

    String getChangeString() {

        String changeString = "";

        if (balance > 0) {

            changeString += "Here's your money:\n";

            while (balance > 0) {

                int grootstMogelijkeMunt = 0;

                for (int i = 0; i < coinTypeList.length; i++) {
                    if (balance >= coinTypeList[i]) {
                        grootstMogelijkeMunt = coinTypeList[i];
                        break;
                    }
                }

                balance -= grootstMogelijkeMunt;
                changeString += String.format("€%.2f\n", (double)grootstMogelijkeMunt / 100 );
            }

        } else {
            changeString = "You do not get any money back";
        }

        return changeString;
    }
}