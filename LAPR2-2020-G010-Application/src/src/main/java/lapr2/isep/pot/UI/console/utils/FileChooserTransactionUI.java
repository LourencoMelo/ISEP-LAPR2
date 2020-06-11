package lapr2.isep.pot.UI.console.utils;

import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;

public class FileChooserTransactionUI {
    private FileChooser fileChooser;

    private FileChooserTransactionUI() {
        fileChooser = new FileChooser();
        associarFiltro("Transaction File", "*.csv");
    }

    public static FileChooser criarFileChooserListaTelefonica() {
        FileChooserTransactionUI fcListTransactions = new FileChooserTransactionUI();
        return fcListTransactions.fileChooser;
    }

    private void associarFiltro(String description, String extension) {
        ExtensionFilter filter = new ExtensionFilter(description, extension);
        fileChooser.getExtensionFilters().add(filter);
    }
}