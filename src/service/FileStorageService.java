package service;

import java.io.*;

public class FileStorageService {

    public static Bank loadBank() {
        try {
            FileInputStream is = new FileInputStream("Bank.ser");
            ObjectInputStream ois = new ObjectInputStream(is);
            return (Bank) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Blad przy wczytywaniu pliku bazy, uruchamiam z nowym stanem");
            return new Bank();
        }
    }
    public static void saveToFile(Bank bank) {
        try {
            FileOutputStream fos = new FileOutputStream("Bank.ser");
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            // zapisz do pliku
            oos.writeObject(bank);
            // zamknij zasoby
            oos.close();
            fos.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
