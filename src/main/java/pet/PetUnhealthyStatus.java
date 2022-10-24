package pet;

public enum PetUnhealthyStatus implements PetStatus{
    exhausted,
    painful,
    injured,
    bleeding,
    dying
    ;

    public String getPetStatusDescription(){
        switch (this) {
        case exhausted:
            return "exhausted";
        case painful:
            return "painful";
        case injured:
            return "injured";
        case bleeding:
            return "bleeding";
        case dying:
            return "dying";
        default:
            return "unhealthy";
        }

    }
}
