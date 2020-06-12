package lapr2.isep.pot.UI.console.utils;

import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;

public class FileChooserTransactionUI {
    private FileChooser fileChooserCsv;
    private FileChooser fileChooserTxt;

    private FileChooserTransactionUI() {
        FileChooserTransactionUICsv();
        FileChooserTransactionUITxt();
    }

    private void FileChooserTransactionUICsv() {
            fileChooserCsv = new FileChooser();
            associateFilterCsv("Transaction File", "*.csv");
    }

    private void FileChooserTransactionUITxt() {
        fileChooserTxt = new FileChooser();
        associateFilterTxt("Transaction File", "*.txt");
    }

    public static FileChooser createFileChooserPaymentListCsv() {
        FileChooserTransactionUI fcListTransactions = new FileChooserTransactionUI();
        return fcListTransactions.fileChooserCsv;
    }

    public static FileChooser createFileChooserPaymentListTxt() {
        FileChooserTransactionUI fcListTransactions = new FileChooserTransactionUI();
        return fcListTransactions.fileChooserTxt;
    }

    private void associateFilterCsv(String description, String extension) {
        ExtensionFilter filter = new ExtensionFilter(description, extension);
        fileChooserCsv.getExtensionFilters().add(filter);
    }

    private void associateFilterTxt(String description, String extension) {
        ExtensionFilter filter = new ExtensionFilter(description, extension);
        fileChooserTxt.getExtensionFilters().add(filter);
    }
}