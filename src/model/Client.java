package model;

import java.io.Serializable;

public class Client implements Serializable {
    private int id;
    private String name;
    private String surname;
    private double accountBalance;
    private double interestRate;

    public Client() {
    }

    public Client(int id, String name, String surname, double accountBalance, double interestRate) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.accountBalance = accountBalance;
        this.interestRate = interestRate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public double getAccountBalance() {
        return accountBalance;
    }

    public void setAccountBalance(double accountBalance) {
        this.accountBalance = accountBalance;
    }

    public double getInterestRate() {
        return interestRate;
    }

    public void setInterestRate(double interestRate) {
        this.interestRate = interestRate;
    }

    public double getFullInterestRate() {
        return interestRate;
    }
}
