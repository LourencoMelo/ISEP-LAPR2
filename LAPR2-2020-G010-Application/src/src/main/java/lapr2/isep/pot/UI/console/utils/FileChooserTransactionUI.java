package lapr2.isep.pot.UI.console.utils;

import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;

public class FileChooserTransactionUI {
    private FileChooser fileChooser;

    private FileChooserTransactionUI() {
        fileChooser = new FileChooser();
        associateFilter("Transaction File", "*.csv");
    }

    public static FileChooser createFileChooserPaymentList() {
        FileChooserTransactionUI fcListTransactions = new FileChooserTransactionUI();
        return fcListTransactions.fileChooser;
    }

    private void associateFilter(String description, String extension) {
        ExtensionFilter filter = new ExtensionFilter(description, extension);
        fileChooser.getExtensionFilters().add(filter);
    }
}