package core.enums;

public enum EBook {
    action, adventure, fairyTale, fantasy, horror, mystery, romance, scienceFiction, suspense, thriller;

    public EBook getEBook(String value) {
        for (EBook eBook : EBook.values()) {
            if (eBook.name().equals(value)) {
                return eBook;
            }
        }
        return null;
    }
}

