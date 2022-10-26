package pet;

public enum PetHealthyStatus implements PetStatus {
    fantastic,
    energetic,
    happy,
    boring,
    sleepy,
    ;

    public String getPetStatusDescription() {
        switch (this) {
        case happy:
            return "happy";
        case boring:
            return "boring";
        case sleepy:
            return "sleepy";
        case energetic:
            return "energetic";
        case fantastic:
            return "fantastic";
        default:
            return "Healthy";
        }
    }
}
