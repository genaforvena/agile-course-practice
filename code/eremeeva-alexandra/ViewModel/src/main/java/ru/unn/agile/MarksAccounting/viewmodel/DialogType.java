package ru.unn.agile.MarksAccounting.viewmodel;

public enum DialogType {
    ADD_GROUP("Add group"),
    ADD_STUDENT("Add student"),
    ADD_SUBJECT("Add subject"),
    ADD_MARK("Add mark"),
    DELETE_GROUP("Delete group"),
    DELETE_STUDENT("Delete student"),
    DELETE_SUBJECT("Delete subject"),
    DELETE_MARK("Delete mark"),
    DEFAULT_DIALOG("Default dialog window");

    private String typeValue;

    private DialogType(final String type) {
        typeValue = type;
    }

    public String getTypeValue() {
        return typeValue;
    }

    public static DialogType getType(final String pType) {
        for (DialogType type: DialogType.values()) {
            if (type.getTypeValue().equals(pType)) {
                return type;
            }
        }
        throw new RuntimeException("Unknown type");
    }

}
