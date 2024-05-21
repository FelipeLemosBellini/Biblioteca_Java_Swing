package core.enums;

public enum EProfile {
    admin, employee;

    public EProfile getEProfile(String value) {
        for (EProfile eProfile : EProfile.values()) {
            if (eProfile.name().equals(value)) {
                return eProfile;
            }
        }
        return null;
    }
}
