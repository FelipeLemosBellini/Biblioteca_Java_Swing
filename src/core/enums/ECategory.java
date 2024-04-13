package core.enums;

public enum ECategory {
    action, adventure, fairyTale, fantasy, horror, mystery, romance, scienceFiction, suspense, thriller;

    public ECategory getECategory(String value) {
        for (ECategory eBook : ECategory.values()) {
            if (eBook.name().equals(value)) {
                return eBook;
            }
        }
        return null;
    }
}

