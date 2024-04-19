package edu.neu.neuconnect.model;

import java.util.ArrayList;
import java.util.List;

public enum ServiceType {
        FITNESS,
        TUTORING,
        DANCER,

        CAREERCONSULTANT;

    public static List<ServiceType> getFitnessTypes() {
        List<ServiceType> fitnessTypes = new ArrayList<>();
        fitnessTypes.add(ServiceType.FITNESS);
        fitnessTypes.add(ServiceType.DANCER);
        return fitnessTypes;
    }

    public static List<ServiceType> getTutorTypes() {
        List<ServiceType> fitnessTypes = new ArrayList<>();
        fitnessTypes.add(ServiceType.TUTORING);
        return fitnessTypes;
    }

    public static List<ServiceType> getCareerConsultantTypes() {
        List<ServiceType> fitnessTypes = new ArrayList<>();
        fitnessTypes.add(ServiceType.CAREERCONSULTANT);
        return fitnessTypes;
    }
}
