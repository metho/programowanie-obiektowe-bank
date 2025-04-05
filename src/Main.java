import service.Bank;
import service.FileStorageService;
import service.UserInputUtil;

public class Main {

    private final static Bank bank = FileStorageService.loadBank();

    public static void main(String[] args) {
        boolean shouldContinueToRun = true;
        while (shouldContinueToRun) {
            UserInputUtil.printMenu();
            shouldContinueToRun = UserInputUtil.handleUserOption(shouldContinueToRun, bank);
        }
        FileStorageService.saveToFile(bank);
    }
}