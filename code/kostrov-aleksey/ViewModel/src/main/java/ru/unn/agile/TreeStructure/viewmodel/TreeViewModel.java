package ru.unn.agile.TreeStructure.viewmodel;

import ru.unn.agile.TreeStructure.Model.Node;
import ru.unn.agile.TreeStructure.Model.Tree;

public class TreeViewModel {
    private String key;
    private String data;
    private Operation operation;
    private Tree<Integer, String> tree;
    private String message;
    private String dataBySearchedElement;
    private boolean doButtonEnabled;
    private boolean textDataFieldEnabled;

    private void changeEnableStateDoButton(final boolean keyTextFieldIsEmpty) {
        if (keyTextFieldIsEmpty) {
            doButtonEnabled = false;
        } else {
            doButtonEnabled = true;
        }
    }

    private Operation typeOperationID(final String operation) {
        switch (operation) {
            case "Insert":
                return Operation.INSERT;
            case "Search":
                return Operation.SEARCH;
            default:
                return Operation.TRUNCATE;
        }
    }

    private void changeEnableStateDataTextField() {
        if (operation == Operation.INSERT) {
            textDataFieldEnabled = true;
        } else {
            textDataFieldEnabled = false;
        }
    }

    public TreeViewModel() {
        key = "";
        data = "";
        message = "";
        dataBySearchedElement = "";
        operation = Operation.INSERT;
        doButtonEnabled = false;
        textDataFieldEnabled = true;
        tree = new Tree<>();
    }

    public boolean isDoButtonEnabled() {
        return doButtonEnabled;
    }

    public boolean isDataTextFieldEnabled() {
        return textDataFieldEnabled;
    }

    public void setKey(final String key) {
        this.key = key;
        changeEnableStateDoButton(key.isEmpty());
    }

    public void setDataFromNode(final String data) {
        this.data = data;
        changeEnableStateDataTextField();
    }

    public void setOperation(final String operation) {
        this.operation = typeOperationID(operation);
    }

    public void doOperation() {
        try {
            switch (operation) {
                case INSERT:
                    if (tree.addNode(Integer.valueOf(key), data)) {
                        message = ErrorMessage.SUCCESS;
                    } else {
                        message = ErrorMessage.ALREADY_EXISTS;
                    }
                    break;
                case SEARCH:
                    final Node tempNode = tree.searchByKey(Integer.valueOf(key));
                    if (tempNode == null) {
                        message = ErrorMessage.NOT_FOUND;
                    } else {
                        dataBySearchedElement = (String) (tempNode.getData());
                        message = ErrorMessage.SUCCESS;
                    }
                    break;
                default:
                    tree.truncateByKey(Integer.valueOf(key));
                    message = ErrorMessage.SUCCESS;
                    break;
            }
        } catch (NullPointerException e) {
            message = ErrorMessage.NOT_FOUND;
        } catch (Exception e) {
            message = ErrorMessage.KEY_NOT_CORRECT;
        }
    }

    public Operation getOperation() {
        return operation;
    }

    public String getSearchedData() {
        return dataBySearchedElement;
    }

    public String getErrorMessage() {
        return message;
    }

    public final class ErrorMessage {
        public static final String SUCCESS = "Success";
        public static final String ALREADY_EXISTS = "Node with this key already exists.";
        public static final String NOT_FOUND = "Element not found";
        public static final String KEY_NOT_CORRECT = "Key is not comparable";

        private ErrorMessage() { }
    }

    public enum Operation {
        INSERT("Insert"),
        SEARCH("Search_by_key"),
        TRUNCATE("Truncate");
        private final String name;

        private Operation(final String name) {
            this.name = name;
        }

        @Override
        public String toString() {
            return name;
        }
    }

}
