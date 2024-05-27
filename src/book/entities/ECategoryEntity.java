package book.entities;

public enum ECategoryEntity {
    action, adventure, fairyTale, fantasy, horror, mystery, romance, scienceFiction, suspense, thriller;

    public ECategoryEntity getECategory(String value) {
        for (ECategoryEntity eBook : ECategoryEntity.values()) {
            if (eBook.name().equals(value)) {
                return eBook;
            }
        }
        return null;
    }
}

