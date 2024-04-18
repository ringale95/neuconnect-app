package edu.neu.neuconnect.model;

import java.util.ArrayList;
import java.util.List;

public enum ServiceType {
        FITNESS,
        TUTORING,
        DANCER;

    public static List<ServiceType> getFitnessTypes() {
        List<ServiceType> fitnessTypes = new ArrayList<>();
        fitnessTypes.add(ServiceType.FITNESS);
        fitnessTypes.add(ServiceType.DANCER);
        return fitnessTypes;
    }
}
