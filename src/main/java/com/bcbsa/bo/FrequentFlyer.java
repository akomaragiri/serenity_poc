package com.bcbsa.bo;

import com.bcbsa.helper.Status;

public class FrequentFlyer {

    int balance;
    String status;

    public FrequentFlyer(int balance) {
        this.balance = balance;
    }

    public static FrequentFlyer withInitialBalanceOf(int initialBalance) {
        return new FrequentFlyer(initialBalance);
    }

    public int getBalance() {
        return balance;
    }

    public String getStatus() {
        switch (balance) {
            case 1000:
                status = Status.Silver.getText();
                break;
            case 25000:
                status = Status.Gold.getText();
                break;
            case 50000:
                status = Status.Gold.getText();
                break;
            default:
                status = "Not a valid Status";
        }
        return status;
    }

    public PointsCalculator flies(int distance) {
        return new PointsCalculator(distance);
    }

    public class PointsCalculator {

        int distance;

        public PointsCalculator(int distance) {
            this.distance = distance;
        }

        public void kilometers() {
            balance += (distance / 10);
        }

        public void miles() {
            balance += (int) ((distance * 1.6) / 10);
        }
    }
}


