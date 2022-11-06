package com.example.petnership_kairos;

public class ForReviewApplicantsInfo {

    public String applicationID, dateApplied, timeApplied, adopterID, adopterName, ownOrRentHome, rentPetPolicy = "", hasYard, yardHasFence = "", hasChildren, childrenDetails = "", hasWork, workCompany = "", workPosition = "", hasPet, petNames = "", petBreeds = "", hasSurrendered, petVet = "", isConvicted, convictedDetails = "", hoursAlone, emergency, willCratePet, references, adopterIntentions, petID, petType, petName, petBreed, petAge, petDescription, shelterID, applicationStatus;
    public String appliedToAdopt;
    public ForReviewApplicantsInfo() {
    }
    public ForReviewApplicantsInfo(String appliedToAdopt, String applicationID, String dateApplied, String timeApplied, String adopterID, String adopterName, String ownOrRentHome, String rentPetPolicy, String hasYard, String yardHasFence, String hasChildren, String childrenDetails, String hasWork, String workCompany, String workPosition, String hasPet, String petNames, String petBreeds, String hasSurrendered, String petVet, String isConvicted, String convictedDetails, String hoursAlone, String emergency, String willCratePet, String references, String adopterIntentions, String petID, String petType, String petName, String petBreed, String petAge, String petDescription, String shelterID, String applicationStatus) {
        this.appliedToAdopt = appliedToAdopt;
        this.applicationID = applicationID;
        this.dateApplied = dateApplied;
        this.timeApplied = timeApplied;
        this.adopterID = adopterID;
        this.adopterName = adopterName;
        this.ownOrRentHome = ownOrRentHome;
        this.hasYard = hasYard;
        this.hasChildren = hasChildren;
        this.hasWork = hasWork;
        this.hasPet = hasPet;
        this.hasSurrendered = hasSurrendered;
        this.isConvicted = isConvicted;
        this.hoursAlone = hoursAlone;
        this.emergency = emergency;
        this.willCratePet = willCratePet;
        this.references = references;
        this.adopterIntentions = adopterIntentions;
        this.petID = petID;
        this.petType = petType;
        this.petName = petName;
        this.petBreed = petBreed;
        this.petAge = petAge;
        this.petDescription = petDescription;
        this.shelterID = shelterID;
        this.applicationStatus = applicationStatus;

        if (!rentPetPolicy.equals(""))      this.rentPetPolicy = rentPetPolicy;
        if (!yardHasFence.equals(""))      this.yardHasFence = yardHasFence;
        if (!childrenDetails.equals(""))      this.childrenDetails = childrenDetails;
        if (!workCompany.equals(""))      this.workCompany = workCompany;
        if (!workPosition.equals(""))      this.workPosition = workPosition;
        if (!petNames.equals(""))      this.petNames = petNames;
        if (!petBreeds.equals(""))      this.petBreeds = petBreeds;
        if (!petVet.equals(""))      this.petVet = petVet;
        if (!convictedDetails.equals(""))      this.convictedDetails = convictedDetails;
    }

    public String getApplicationID() {
        return applicationID;
    }

    public void setApplicationID(String applicationID) {
        this.applicationID = applicationID;
    }

    public String getDateApplied() {
        return dateApplied;
    }

    public void setDateApplied(String dateApplied) {
        this.dateApplied = dateApplied;
    }

    public String getTimeApplied() {
        return timeApplied;
    }

    public void setTimeApplied(String timeApplied) {
        this.timeApplied = timeApplied;
    }

    public String getAdopterID() {
        return adopterID;
    }

    public void setAdopterID(String adopterID) {
        this.adopterID = adopterID;
    }

    public String getAdopterName() {
        return adopterName;
    }

    public void setAdopterName(String adopterName) {
        this.adopterName = adopterName;
    }

    public String getOwnOrRentHome() {
        return ownOrRentHome;
    }

    public void setOwnOrRentHome(String ownOrRentHome) {
        this.ownOrRentHome = ownOrRentHome;
    }

    public String getRentPetPolicy() {
        return rentPetPolicy;
    }

    public void setRentPetPolicy(String rentPetPolicy) {
        this.rentPetPolicy = rentPetPolicy;
    }

    public String getHasYard() {
        return hasYard;
    }

    public void setHasYard(String hasYard) {
        this.hasYard = hasYard;
    }

    public String getYardHasFence() {
        return yardHasFence;
    }

    public void setYardHasFence(String yardHasFence) {
        this.yardHasFence = yardHasFence;
    }

    public String getHasChildren() {
        return hasChildren;
    }

    public void setHasChildren(String hasChildren) {
        this.hasChildren = hasChildren;
    }

    public String getChildrenDetails() {
        return childrenDetails;
    }

    public void setChildrenDetails(String childrenDetails) {
        this.childrenDetails = childrenDetails;
    }

    public String getHasWork() {
        return hasWork;
    }

    public void setHasWork(String hasWork) {
        this.hasWork = hasWork;
    }

    public String getWorkCompany() {
        return workCompany;
    }

    public void setWorkCompany(String workCompany) {
        this.workCompany = workCompany;
    }

    public String getWorkPosition() {
        return workPosition;
    }

    public void setWorkPosition(String workPosition) {
        this.workPosition = workPosition;
    }

    public String getHasPet() {
        return hasPet;
    }

    public void setHasPet(String hasPet) {
        this.hasPet = hasPet;
    }

    public String getPetNames() {
        return petNames;
    }

    public void setPetNames(String petNames) {
        this.petNames = petNames;
    }

    public String getPetBreeds() {
        return petBreeds;
    }

    public void setPetBreeds(String petBreeds) {
        this.petBreeds = petBreeds;
    }

    public String getHasSurrendered() {
        return hasSurrendered;
    }

    public void setHasSurrendered(String hasSurrendered) {
        this.hasSurrendered = hasSurrendered;
    }

    public String getPetVet() {
        return petVet;
    }

    public void setPetVet(String petVet) {
        this.petVet = petVet;
    }

    public String getIsConvicted() {
        return isConvicted;
    }

    public void setIsConvicted(String isConvicted) {
        this.isConvicted = isConvicted;
    }

    public String getConvictedDetails() {
        return convictedDetails;
    }

    public void setConvictedDetails(String convictedDetails) {
        this.convictedDetails = convictedDetails;
    }

    public String getHoursAlone() {
        return hoursAlone;
    }

    public void setHoursAlone(String hoursAlone) {
        this.hoursAlone = hoursAlone;
    }

    public String getEmergency() {
        return emergency;
    }

    public void setEmergency(String emergency) {
        this.emergency = emergency;
    }

    public String getWillCratePet() {
        return willCratePet;
    }

    public void setWillCratePet(String willCratePet) {
        this.willCratePet = willCratePet;
    }

    public String getReferences() {
        return references;
    }

    public void setReferences(String references) {
        this.references = references;
    }

    public String getAdopterIntentions() {
        return adopterIntentions;
    }

    public void setAdopterIntentions(String adopterIntentions) {
        this.adopterIntentions = adopterIntentions;
    }

    public String getPetID() {
        return petID;
    }

    public void setPetID(String petID) {
        this.petID = petID;
    }

    public String getPetType() {
        return petType;
    }

    public void setPetType(String petType) {
        this.petType = petType;
    }

    public String getPetName() {
        return petName;
    }

    public void setPetName(String petName) {
        this.petName = petName;
    }

    public String getPetBreed() {
        return petBreed;
    }

    public void setPetBreed(String petBreed) {
        this.petBreed = petBreed;
    }

    public String getPetAge() {
        return petAge;
    }

    public void setPetAge(String petAge) {
        this.petAge = petAge;
    }

    public String getPetDescription() {
        return petDescription;
    }

    public void setPetDescription(String petDescription) {
        this.petDescription = petDescription;
    }

    public String getShelterID() {
        return shelterID;
    }

    public void setShelterID(String shelterID) {
        this.shelterID = shelterID;
    }

    public String getApplicationStatus() {
        return applicationStatus;
    }

    public void setApplicationStatus(String applicationStatus) {
        this.applicationStatus = applicationStatus;
    }

    public String getAppliedToAdopt() {
        return appliedToAdopt;
    }

    public void setAppliedToAdopt(String appliedToAdopt) {
        this.appliedToAdopt = appliedToAdopt;
    }
}

