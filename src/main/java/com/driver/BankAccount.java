package com.driver;

public class BankAccount {

    private String name;
    private double balance;
    private double minBalance;

    public BankAccount(String name, double balance, double minBalance) {
        this.name = name;
        this.balance = balance;
        this.minBalance = minBalance;
    }
    public String getName() {
        return name;
    }
    public double getBalance() {
        return balance;
    }
    public double getMinBalance() {
        return minBalance;
    }
    public String generateAccountNumber(int digits, int sum) throws Exception{
        //Each digit of an account number can lie between 0 and 9 (both inclusive)
        //Generate account number having given number of 'digits' such that the sum of digits is equal to 'sum'
        //If it is not possible, throw "Account Number can not be generated" exception

        if(sum < 0 || sum > digits * 9)
            throw new RuntimeException("Account Number can not be generated");


        String acc_no = "";
        int remainingSum = sum;

        for(int i = 0; i < digits; i++){
            int min = Math.min(remainingSum,9);
            remainingSum -= min;
            acc_no += min;

        }
        return acc_no;


    }

    public void deposit(double amount) {
        this.balance += amount;

    }

    public void withdraw(double amount) throws Exception {
        // Remember to throw "Insufficient Balance" exception, if the remaining amount would be less than minimum balance
        if(this.minBalance > balance - amount)
            throw new InsufficientBalance();

    }

}