package model;

public class ClientVIP extends Client {

    public ClientVIP() {
    }

    public ClientVIP(int id, String name, String surname, double accountBalance, double interestRate, double additionalInterestRate) {
        super(id, name, surname, accountBalance, interestRate);
        this.additionalInterestRate = additionalInterestRate;
    }

    private double additionalInterestRate;

    public double getAdditionalInterestRate() {
        return additionalInterestRate;
    }

    public void setAdditionalInterestRate(double additionalInterestRate) {
        this.additionalInterestRate = additionalInterestRate;
    }

    @Override
    public double getFullInterestRate() {
        return getInterestRate() + additionalInterestRate;
    }

}
