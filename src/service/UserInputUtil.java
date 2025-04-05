package service;

import model.Client;
import model.ClientVIP;

import java.util.InputMismatchException;
import java.util.Scanner;

public class UserInputUtil {

    private final static Scanner scanner = new Scanner(System.in);

    public static void printMenu() {
        System.out.println("Wybierz opcje: ");
        System.out.println("1: Dodaj klienta, 2: Zasil konto, 3: Wyplata, 4: Przelew, 5: Naliczenie oprocentowania," +
                " 6: Wyswietl wszystkich klientow, 7: Wyszukaj po ID, 8: Usun klienta, 9: Zakoncz");
    }

    public static boolean handleUserOption(boolean shouldContinueToRun, Bank bank) {
        try {
            int option = scanner.nextInt();
            //wyczysc buffor
            scanner.nextLine();
            shouldContinueToRun = handleUserInput(option, bank);
            System.out.print("\n");
        } catch (InputMismatchException e) {
            System.out.println("Podano nieprawidlowa wartosc. Wybierz numer opcji");
            // wyczysc buffor scannera
            scanner.nextLine();
        }
        return shouldContinueToRun;
    }

    private static boolean handleUserInput(int option, Bank bank) {
        switch (option) {
            case 1:
                createClient(bank);
                break;
            case 2:
                addBalance(bank);
                break;
            case 3:
                withdrawBalance(bank);
                break;
            case 4:
                transferBetweenAccounts(bank);
                break;
            case 5:
                calculateInterestsForClient(bank);
                break;
            case 6:
                bank.printAllClients();
                break;
            case 7:
                System.out.println("\nPodaj id klienta");
                bank.printClientById(scanner.nextInt());
                break;
            case 8:
                System.out.println("\nPodaj id klienta");
                bank.removeClientById(scanner.nextInt());
                break;
            case 9:
                return false;
            default:
                System.out.println("Nie wybrano poprawnej opcji");
        }
        return true;
    }

    private static void transferBetweenAccounts(Bank bank) {
        System.out.println("Podaj id klienta z ktorego konta pobrac srodki");
        int clientIdFrom = scanner.nextInt();
        System.out.println("Podaj id klienta na ktorego konto przelac srodki");
        int clientIdTo = scanner.nextInt();
        System.out.println("Podaj kwote");
        double amount = scanner.nextDouble();
        //czyszczenie buffora lini
        scanner.nextLine();
        bank.transferBetweenAccounts(clientIdFrom, clientIdTo, amount);
    }

    private static void calculateInterestsForClient(Bank bank) {
        System.out.println("Podaj id klienta");
        int clientId = scanner.nextInt();
        //czyszczenie buffora lini
        scanner.nextLine();
        bank.calculateInterestsForClient(clientId);
    }

    private static void withdrawBalance(Bank bank) {
        System.out.println("Podaj id klienta");
        int clientId = scanner.nextInt();
        System.out.println("Podaj kwote do wyplaty");
        double amount = scanner.nextDouble();
        //czyszczenie buffora lini
        scanner.nextLine();
        bank.withdrawFromAccount(clientId, amount);
    }

    private static void addBalance(Bank bank) {
        System.out.println("Podaj id klienta");
        int clientId = scanner.nextInt();
        System.out.println("Podaj kwote");
        double amount = scanner.nextDouble();
        //czyszczenie buffora lini
        scanner.nextLine();
        bank.addAccountBalance(clientId, amount);
    }

    private static void createClient(Bank bank) {
        System.out.println("Podaj imie klienta");
        String name = scanner.nextLine();
        System.out.println("Podaj nazwisko klienta");
        String surname = scanner.nextLine();
        System.out.println("Podaj stan konta klienta");
        double accountBalance = scanner.nextDouble();
        System.out.println("Podaj oprocentowanie konta klienta");
        double interestRate = scanner.nextDouble();
        //czyszczenie buffora lini
        scanner.nextLine();

        System.out.println("Czy to klient VIP? tak/nie");
        String clientVIP = scanner.nextLine();

        int newId = bank.nextAvailableClientId();

        Client client;
        if (clientVIP.equals("tak")) {
            System.out.println("Podaj bonusowe oprocentowanie");
            double bonusInterestRate = scanner.nextDouble();
            client = new ClientVIP(newId, name, surname, accountBalance, interestRate, bonusInterestRate);
        } else {
            client = new Client(newId, name, surname, accountBalance, interestRate);
        }
        bank.addClient(client);
    }
}
