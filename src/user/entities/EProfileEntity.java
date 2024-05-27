package user.entities;

public enum EProfileEntity {
    admin, employee;

    public EProfileEntity getEProfile(String value) {
        for (EProfileEntity eProfileEntity : EProfileEntity.values()) {
            if (eProfileEntity.name().equals(value)) {
                return eProfileEntity;
            }
        }
        return null;
    }
}
