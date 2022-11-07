package com.example.petnership_kairos;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStoreOwner;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MCDM {

    private MCDMAnswersViewModel mViewModel;
    private List<MCDMAlternative> alternatives;
    private List<MCDMCriteriaValue> criteriaValues;

    String adopterEmail, adopterID, dateAnswered, timeAnswered;
    boolean finished;

    // final values
    private final double DEFAULT_COMPARISON_VAL = 1.0;
    private final int INTENSITY_COUNT = 3;
    private final int DOG_MAIN_MATRICES_SIZE = 7;
    private final int[] DOG_SUBCRITERIA_MATRICES_COUNT = {3, 2, 2, 3};
    private final int DOG_INTENSITY_MATRICES_COUNT = 11;
    private final int CAT_MAIN_MATRICES_SIZE = 8;
    private final int[] CAT_SUBCRITERIA_MATRICES_COUNT = {1, 2};
    private final int CAT_INTENSITY_MATRICES_COUNT = 9;

    private List<MCDMCriteria> criteria;
    private int numberOfAlternatives = 0;
    private int numberOfCriteria = 0;
    private int animalType = 0; // 1 for dog, 2 for cat
    private int sentinelCounter = 0;
    // int matrixDimension = 0;
    private double[][] normalizedDecisionMatrix;
    private double[] idealBest;
    private double[] idealWorst;
    private double[] distancesFromIdealWorst;
    private double[] distancesFromIdealBest;

    //criteria dog answers
    MCDMCriteria criteriaObedience = new MCDMCriteria("Obedience");
    MCDMCriteria criteriaTrainability = new MCDMCriteria("Trainability");
    MCDMCriteria criteriaNonShedding = new MCDMCriteria("Non-Shedding");
    MCDMCriteria criteriaNonDrooling = new MCDMCriteria("Non-Drooling");
    MCDMCriteria criteriaEaseOfCare = new MCDMCriteria("Ease of Care");
    MCDMCriteria criteriaFriendliness = new MCDMCriteria("Friendliness");
    MCDMCriteria criteriaToHumans = new MCDMCriteria("To Humans");
    MCDMCriteria criteriaToOtherPets = new MCDMCriteria("To Other Pets");
    MCDMCriteria criteriaToEnvironment = new MCDMCriteria("To Environment");
    MCDMCriteria criteriaPopularity = new MCDMCriteria("Popularity");
    MCDMCriteria criteriaLongevity = new MCDMCriteria("Longevity");

    //criteria cat answers
    MCDMCriteria criteriaActivity = new MCDMCriteria("Activity");
    MCDMCriteria criteriaCalmness = new MCDMCriteria("Calmness");
    MCDMCriteria criteriaNonAggression = new MCDMCriteria("Non-Aggression");
    MCDMCriteria criteriaLitterBox = new MCDMCriteria("Litter Box");
    MCDMCriteria criteriaHealthyGrooming = new MCDMCriteria("Healthy Grooming");
    MCDMCriteria criteriaAge = new MCDMCriteria("Age");

    //intensity holders
    private int q1, q2, q3, q4, q5, q6, q7, q8, q9, q10, q11;
    private String animalBreed;

    //database references

    DatabaseReference adoptersDBRef = FirebaseDatabase.getInstance().getReference("Adopters");
    DatabaseReference sheltersDBRef = FirebaseDatabase.getInstance().getReference("Shelters");

    DatabaseReference petsCatsDBRef = FirebaseDatabase.getInstance().getReference().child("Pets").child("Cats");
    DatabaseReference petsDogsDBRef = FirebaseDatabase.getInstance().getReference().child("Pets").child("Dogs");
    DatabaseReference petsDBRef = FirebaseDatabase.getInstance().getReference().child("Pets");
    private FirebaseAuth authProfile;
    private FirebaseUser firebaseUser;
    private String userEmail, petID, petName, petAge, petDesc, petSex, petStatus, petType, petShelter, petImageName;
    private ArrayList<String> petIDs = new ArrayList<>();
    // instance for firebase storage and StorageReference
    StorageReference storageReference = FirebaseStorage.getInstance().getReference();

    //breed  thingies
    String[] dogBreeds1 = {"Retrievers (Labrador)",
            "French Bulldogs",
            "Retrievers (Golden)",
            "German Shepherd Dogs",
            "Poodles",
            "Bulldogs",
            "Beagles",
            "Rottweilers",
            "Pointers (German Shorthaired)",
            "Dachshunds",
            "Pembroke Welsh Corgis",
            "Australian Shepherds",
            "Yorkshire Terriers",
            "Boxers",
            "Cavalier King Charles Spaniels",
            "Doberman Pinschers",
            "Great Danes",
            "Miniature Schnauzers",
            "Siberian Huskies",
            "Bernese Mountain Dogs",
            "Cane Corso",
            "Shih Tzu",
            "Boston Terriers",
            "Pomeranians",
            "Havanese"};
    String[] dogBreeds2 = {"Spaniels (English Springer)",
            "Brittanys",
            "Shetland Sheepdogs",
            "Spaniels (Cocker)",
            "Miniature American Shepherds",
            "Border Collies",
            "Vizslas",
            "Pugs",
            "Basset Hounds",
            "Mastiffs",
            "Belgian Malinois",
            "Chihuahuas",
            "Collies",
            "Maltese",
            "Weimaraners",
            "Rhodesian Ridgebacks",
            "Shiba Inu",
            "Spaniels (English Cocker)",
            "Portuguese Water Dogs",
            "Newfoundlands",
            "West Highland White Terriers",
            "Bichons Frises",
            "Retrievers (Chesapeake Bay)",
            "Dalmatians",
            "Bloodhounds",
            "Australian Cattle Dogs",
            "Akitas",
            "St. Bernards",
            "Papillons",
            "Samoyeds",
            "Bullmastiffs",
            "Whippets",
            "Scottish Terriers",
            "Pointers (German Wirehaired)",
            "Wirehaired Pointing Griffons",
            "Bull Terriers",
            "Airedale Terriers",
            "Great Pyrenees",
            "Chinese Shar-Pei",
            "Giant Schnauzers",
            "Soft Coated Wheaten Terriers",
            "Cardigan Welsh Corgis",
            "Alaskan Malamutes",
            "Old English Sheepdogs",
            "Dogues de Bordeaux",
            "Setters (Irish)",
            "Russell Terriers",
            "Italian Greyhounds",
            "Cairn Terriers",
            "Staffordshire Bull Terriers",
            "Miniature Pinschers",
            "Chinese Crested",
            "Greater Swiss Mountain Dogs",
            "Lagotti Romagnoli",
            "Chow Chows",
            "American Staffordshire Terriers",
            "Biewer Terriers",
            "Coton de Tulear",
            "Lhasa Apsos",
            "Irish Wolfhounds",
            "Rat Terriers",
            "Basenjis",
            "Anatolian Shepherd Dogs",
            "Dogo Argentinos",
            "Spaniels (Boykin)",
            "Border Terriers",
            "Retrievers (Nova Scotia Duck Tolling)",
            "Retrievers (Flat-Coated)",
            "Pekingese",
            "Keeshonden",
            "Standard Schnauzers",
            "Brussels Griffons",
            "Setters (English)",
            "Fox Terriers (Wire)",
            "Norwegian Elkhounds"};
    String[] catBreeds1 = {"Maine Coon", "Bengal", "Siamese", "Siberian", "Ragdoll",
            "British Shorthair", "Persian", "Scottish Fold", "Bombay", "Birman"

    };
    String[] catBreeds2 = {"Snowshoe", "Abyssinian", "Norwegian Forest", "Burmese", "Turkish Angora",
            "Ragamuffin", "Sphynx", "Nebelung", "Russian Blue", "Burmilla", "Chartreux", "American Shorthair",
            "Himalayan", "Turkish Van", "Tonkinese"

    };

    // mcdm matrices
    private double[][] mainMatrix;
    private double[][][] subcriteriaMatrix;
    private double[][][] intensityMatrix;
    private double[][] mainPriorityMatrix;
    private double[][][] subcriteriaPriorityMatrix;
    private double[][][] intensityPriorityMatrix;
    private double[] mainPriorityVector;
    private double[][] subcriteriaPriorityVector;
    private double[][] intensityPriorityVector;
    private double[][] idealizedIntensityPriorityVector;
    private double[][] temporaryPriorityMatrix;
    private double[] temporaryPriorityVector;
    private double[] globalPrioritiesVector;
    private double[][] populationScoreMatrix;
    private boolean[] hasSubcriteria;

    public void fetchAlternatives(Context context, int animalType){

        mViewModel = new ViewModelProvider((ViewModelStoreOwner) context).get(MCDMAnswersViewModel.class);

        //ACCESS DB
        authProfile = FirebaseAuth.getInstance();
        firebaseUser = authProfile.getCurrentUser();
        userEmail = firebaseUser.getEmail();

        this.animalType = animalType;
        if (animalType == 1) {
            numberOfCriteria = 7;
            getDogAlternatives();
            System.out.println("EXITED ALGO: " + numberOfAlternatives);

        }
        else if (animalType == 2) {
            numberOfCriteria = 8;
            getCatAlternatives();
        }
        else {
            System.out.println("Animal type not valid.");
        }

    }
    private void getDogAlternatives() {
        petsDogsDBRef.orderByChild("petName").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {


                // check why this isn't working
                numberOfAlternatives = (int) snapshot.getChildrenCount();
                alternatives = new ArrayList<MCDMAlternative>();


                for(DataSnapshot ds : snapshot.getChildren()) {
                    petID = ds.getKey();
                    petIDs.add(petID);

                    criteriaValues = new ArrayList<MCDMCriteriaValue>();
                    petName = String.valueOf(snapshot.child(petID).child("petName").getValue());
                    petAge = String.valueOf(snapshot.child(petID).child("petAge").getValue());
                    petDesc = String.valueOf(snapshot.child(petID).child("petDesc").getValue());
                    petSex = String.valueOf(snapshot.child(petID).child("petSex").getValue());
                    petStatus = String.valueOf(snapshot.child(petID).child("petStatus").getValue());
                    petType = String.valueOf(snapshot.child(petID).child("petType").getValue());
                    petShelter = String.valueOf(snapshot.child(petID).child("shelter").getValue());
                    petImageName = String.valueOf(snapshot.child(petID).child("imageName").getValue());

                    q1 = Integer.parseInt(String.valueOf(snapshot.child(petID).child("q1").getValue()));
                    q2 = Integer.parseInt(String.valueOf(snapshot.child(petID).child("q2").getValue()));
                    q3 = Integer.parseInt(String.valueOf(snapshot.child(petID).child("q3").getValue()));
                    q4 = Integer.parseInt(String.valueOf(snapshot.child(petID).child("q4").getValue()));
                    q5 = Integer.parseInt(String.valueOf(snapshot.child(petID).child("q5").getValue()));
                    q6 = Integer.parseInt(String.valueOf(snapshot.child(petID).child("q6").getValue()));
                    q7 = Integer.parseInt(String.valueOf(snapshot.child(petID).child("q7").getValue()));
                    q8 = Integer.parseInt(String.valueOf(snapshot.child(petID).child("q8").getValue()));
                    q9 = Integer.parseInt(String.valueOf(snapshot.child(petID).child("q9").getValue()));
                    q11 = Integer.parseInt(String.valueOf(snapshot.child(petID).child("q11").getValue()));
                    animalBreed = String.valueOf(snapshot.child(petID).child("q10").getValue());
                    getAnimalBreedIntensity();

                    criteriaValues.add(new MCDMCriteriaValue(criteriaObedience, q1));
                    criteriaValues.add(new MCDMCriteriaValue(criteriaTrainability, q2));
                    criteriaValues.add(new MCDMCriteriaValue(criteriaNonShedding, q3));
                    criteriaValues.add(new MCDMCriteriaValue(criteriaNonDrooling, q4));
                    criteriaValues.add(new MCDMCriteriaValue(criteriaEaseOfCare, q5));
                    criteriaValues.add(new MCDMCriteriaValue(criteriaFriendliness, q6));
                    criteriaValues.add(new MCDMCriteriaValue(criteriaToHumans, q7));
                    criteriaValues.add(new MCDMCriteriaValue(criteriaToOtherPets, q8));
                    criteriaValues.add(new MCDMCriteriaValue(criteriaToEnvironment, q9));
                    criteriaValues.add(new MCDMCriteriaValue(criteriaPopularity, q10));
                    criteriaValues.add(new MCDMCriteriaValue(criteriaLongevity, q11));

                    alternatives.add(new MCDMAlternative(petName, criteriaValues, petAge, petDesc, petSex, petStatus, petType, petShelter, petImageName, petID, animalBreed));


                    sentinelCounter++;
                }/*
                    for (int i = 0; i<sentinelCounter; i++) {
                        System.out.println("PET #"  + (i+1));
                        System.out.println( alternatives.get(i).getName());
                        criteriaValues  = alternatives.get(i).getCriteriaValues();
                        for (MCDMCriteriaValue c : criteriaValues) {

                            System.out.println( c.getCriteria().getName() + ": " + c.getIntensityLevel());
                        }
                    }*/

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
    private void getCatAlternatives() {

        petsCatsDBRef.orderByChild("petName").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                sentinelCounter = 0;
                numberOfAlternatives = (int) snapshot.getChildrenCount();
                alternatives = new ArrayList<MCDMAlternative>();
                criteriaValues = new ArrayList<MCDMCriteriaValue>();

                for(DataSnapshot ds : snapshot.getChildren()) {
                    petID = ds.getKey();
                    petIDs.add(petID);

                    petName = String.valueOf(snapshot.child(petID).child("petName").getValue());
                    petAge = String.valueOf(snapshot.child(petID).child("petAge").getValue());
                    petDesc = String.valueOf(snapshot.child(petID).child("petDesc").getValue());
                    petSex = String.valueOf(snapshot.child(petID).child("petSex").getValue());
                    petStatus = String.valueOf(snapshot.child(petID).child("petStatus").getValue());
                    petType = String.valueOf(snapshot.child(petID).child("petType").getValue());
                    petShelter = String.valueOf(snapshot.child(petID).child("shelter").getValue());
                    petImageName = String.valueOf(snapshot.child(petID).child("imageName").getValue());


                    q1 = Integer.parseInt(String.valueOf(snapshot.child(petID).child("q1").getValue()));
                    q2 = Integer.parseInt(String.valueOf(snapshot.child(petID).child("q2").getValue()));
                    q3 = Integer.parseInt(String.valueOf(snapshot.child(petID).child("q3").getValue()));
                    q4 = Integer.parseInt(String.valueOf(snapshot.child(petID).child("q4").getValue()));
                    q5 = Integer.parseInt(String.valueOf(snapshot.child(petID).child("q5").getValue()));
                    q6 = Integer.parseInt(String.valueOf(snapshot.child(petID).child("q6").getValue()));
                    q7 = Integer.parseInt(String.valueOf(snapshot.child(petID).child("q7").getValue()));
                    q8 = Integer.parseInt(String.valueOf(snapshot.child(petID).child("q8").getValue()));
                    animalBreed = String.valueOf(snapshot.child(petID).child("q9").getValue());
                    getAnimalBreedIntensity();

                    criteriaValues = new ArrayList<MCDMCriteriaValue>();
                    criteriaValues.add(new MCDMCriteriaValue(criteriaActivity, q1));
                    criteriaValues.add(new MCDMCriteriaValue(criteriaCalmness, q2));
                    criteriaValues.add(new MCDMCriteriaValue(criteriaNonAggression, q3));
                    criteriaValues.add(new MCDMCriteriaValue(criteriaToHumans, q4));
                    criteriaValues.add(new MCDMCriteriaValue(criteriaToOtherPets, q5));
                    criteriaValues.add(new MCDMCriteriaValue(criteriaLitterBox, q6));
                    criteriaValues.add(new MCDMCriteriaValue(criteriaHealthyGrooming, q7));
                    criteriaValues.add(new MCDMCriteriaValue(criteriaAge, q8));
                    criteriaValues.add(new MCDMCriteriaValue(criteriaPopularity, q9));

                    alternatives.add(new MCDMAlternative(petName, criteriaValues, petAge, petDesc, petSex, petStatus, petType, petShelter, petImageName, petID, animalBreed));

                    sentinelCounter++;
                }
                for (int i = 0; i<sentinelCounter; i++) {
                    System.out.println("PET #"  + (i+1));
                    System.out.println( alternatives.get(i).getName());
                    criteriaValues  = alternatives.get(i).getCriteriaValues();
                    for (MCDMCriteriaValue c : criteriaValues) {

                        System.out.println( c.getCriteria().getName() + ": " + c.getIntensityLevel());
                    }
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
    private void getAnimalBreedIntensity() {
        if (animalType==1) {
            if (Arrays.asList(dogBreeds1).contains(animalBreed)) {
                q10 = 1;
            }
            else if (Arrays.asList(dogBreeds2).contains(animalBreed)) {
                q10 = 2;
            }
            else {
                q10 = 3;
            }
        }
        else {
            if (Arrays.asList(catBreeds1).contains(animalBreed)) {
                q9 = 1;
            }
            else if (Arrays.asList(catBreeds2).contains(animalBreed)) {
                q9 = 2;
            }
            else {
                q9 = 3;
            }

        }
    }
    public void beginMCDM() {
        if (animalType == 1) {
            for (int i = 0; i < numberOfAlternatives; i++){
                System.out.println("Alternative: " + alternatives.get(i).getName());
                for (int j = 0; j < DOG_INTENSITY_MATRICES_COUNT; j++) {
                    System.out.print("Criteria " + alternatives.get(i).getCriteriaValues().get(j).getCriteria().getName() + ": ");
                    System.out.print(alternatives.get(i).getCriteriaValues().get(j).getIntensityLevel()+ " ");
                }
                System.out.println();
            }

        }
        else {

            for (int i = 0; i < numberOfAlternatives; i++){
                System.out.println("Alternative: " + alternatives.get(i).getName());
                for (int j = 0; j < CAT_INTENSITY_MATRICES_COUNT; j++) {
                    System.out.print("Criteria " + alternatives.get(i).getCriteriaValues().get(j).getCriteria().getName() + ": ");
                    System.out.print(alternatives.get(i).getCriteriaValues().get(j).getIntensityLevel()+ " ");
                }
                System.out.println();
            }
        }
        System.out.println("BEFORE STARTING MAIN ALGO: " + numberOfAlternatives);
        // Start Algo
        calculateRecommendationResults();
        System.out.println("AFTER FINISHING MAIN ALGO: " + numberOfAlternatives);
        saveToDatabase();

        // TODO: Clear ViewModel values
    }
    private void calculateRecommendationResults() {
        determineWhichHasSubcriteria();
        constructMainMatrix();
        constructSubcriteriaMatrix();
        constructIntensityMatrix();
        populateGlobalPrioritiesVector();
        populatePopulationScoreMatrix();
        sortAlternativesByPerformanceScore();
        saveResultsToModel();
        //checkConsistencyRatio();
        //sensitivityAnalysis()


/*
        //validateData();
        calculateCriteriaValues();
        calculateNormalizedDecisionMatrix();
        findIdealBestAndWorst();
        calculateDistancesFromIdealBestAndWorst();
        calculatePerformanceScore();
        sortAlternativesByPerformanceScoreDesc();


        return alternatives; // Sorted result from the ideal solution to the worse one.*/
    }
    private void determineWhichHasSubcriteria() {
        if (animalType == 1) {
            hasSubcriteria = new boolean[]{true, true, false, false, true, false, false};
        }
        else {
            hasSubcriteria = new boolean[]{false, false, false, true, false, false, false, false};
        }
    }
    private void constructMainMatrix() {
        if (animalType == 1) {
            mainMatrix = new double[DOG_MAIN_MATRICES_SIZE][DOG_MAIN_MATRICES_SIZE];
            mainPriorityMatrix = new double[DOG_MAIN_MATRICES_SIZE][DOG_MAIN_MATRICES_SIZE];
            int sentinelCount = 0;
            int tempVal;
            int j;

            for (int i = 0; i < DOG_MAIN_MATRICES_SIZE; i++ ) {
                for (j = i; j < DOG_MAIN_MATRICES_SIZE; j++) {
                    if (i == j) {
                        mainMatrix[i][j] = DEFAULT_COMPARISON_VAL;
                    }
                    else {
                        tempVal = mViewModel.getMainAnswer(sentinelCount);
                        if (tempVal > 0) {
                            mainMatrix[i][j] = tempVal * 1.0;
                            mainMatrix[j][i] = 1 / (tempVal * 1.0);
                        }
                        else {
                            mainMatrix[i][j] = 1 / (tempVal * (-1.0));
                            mainMatrix[j][i] = tempVal * (-1.0);
                        }
                        sentinelCount++;
                    }
                }
            }

            // TODO: REMOVE LATER
            //check matrix
            for (int i = 0; i < DOG_MAIN_MATRICES_SIZE; i++) {
                for (int k = 0; k < DOG_MAIN_MATRICES_SIZE; k++) {
                    System.out.print(mainMatrix[i][k] + "   ");
                }
                System.out.println();
            }
            derivePriorities(DOG_MAIN_MATRICES_SIZE);
        }
        else {

            mainMatrix = new double[CAT_MAIN_MATRICES_SIZE][CAT_MAIN_MATRICES_SIZE];
            mainPriorityMatrix = new double[CAT_MAIN_MATRICES_SIZE][CAT_MAIN_MATRICES_SIZE];
            int sentinelCount = 0;
            int tempVal;
            int j;

            for (int i = 0; i < CAT_MAIN_MATRICES_SIZE; i++ ) {
                for (j = i; j < CAT_MAIN_MATRICES_SIZE; j++) {
                    if (i == j) {
                        mainMatrix[i][j] = DEFAULT_COMPARISON_VAL;
                    }
                    else {
                        tempVal = mViewModel.getMainAnswer(sentinelCount);
                        if (tempVal > 0) {
                            mainMatrix[i][j] = tempVal * 1.0;
                            mainMatrix[j][i] = 1 / (tempVal * 1.0);
                        }
                        else {
                            mainMatrix[i][j] = 1 / (tempVal * (-1.0));
                            mainMatrix[j][i] = tempVal * (-1.0);
                        }
                        sentinelCount++;
                    }
                }
            }
            // TODO: REMOVE LATER
            //check matrix
            for (int i = 0; i < CAT_MAIN_MATRICES_SIZE; i++) {
                for (int k = 0; k < CAT_MAIN_MATRICES_SIZE; k++) {
                    System.out.print(mainMatrix[i][k] + "   ");
                }
                System.out.println();
            }
            derivePriorities(CAT_MAIN_MATRICES_SIZE);
        }
    }
    private void constructSubcriteriaMatrix() {
        if (animalType == 1) {
            int subSize;
            double[][] tempMatrix;
            subcriteriaMatrix = new double[DOG_SUBCRITERIA_MATRICES_COUNT[0]][][];
            subcriteriaPriorityMatrix = new double[DOG_SUBCRITERIA_MATRICES_COUNT[0]][][];
            subcriteriaPriorityVector = new double[DOG_SUBCRITERIA_MATRICES_COUNT[0]][];

            int sentinelCount = 0;

            for (int h = 0; h < DOG_SUBCRITERIA_MATRICES_COUNT[0]; h++)  {
                subSize = DOG_SUBCRITERIA_MATRICES_COUNT[h+1];
                subcriteriaPriorityMatrix[h] = new double[subSize][subSize];
                tempMatrix = new double[subSize][subSize];

                int tempVal = -1;
                int j;

                for (int i = 0; i < subSize; i++ ) {
                    for (j = i; j < subSize; j++) {
                        if (i == j) {
                            tempMatrix[i][j] = 1.0;
                        }
                        else {
                            tempVal = mViewModel.getSubcriteriaAnswer(sentinelCount);
                            if (tempVal > 0) {
                                tempMatrix[i][j] = tempVal * 1.0;
                                tempMatrix[j][i] = 1 / (tempVal * 1.0);
                            }
                            else {
                                tempMatrix[i][j] = 1 / (tempVal * (-1.0));
                                tempMatrix[j][i] = tempVal * (-1.0);
                            }
                            sentinelCount++;
                        }
                    }
                }
                subcriteriaMatrix[h] = Arrays.copyOf(tempMatrix, subSize);
                sentinelCount = 0;

            }

            // TODO: REMOVE LATER
            //check matrix
            for (int i = 0; i < DOG_SUBCRITERIA_MATRICES_COUNT[0]; i++) {

                System.out.println("SUBCRITERIA MATRIX #" + (i+1));
                for (int k = 0; k < DOG_SUBCRITERIA_MATRICES_COUNT[i+1]; k++) {

                    for (int l = 0; l < DOG_SUBCRITERIA_MATRICES_COUNT[i+1]; l++) {
                        System.out.print(subcriteriaMatrix[i][k][l] + "   ");
                    }
                    System.out.println();
                }
                System.out.println();
                deriveSubcriteriaPriorities(DOG_SUBCRITERIA_MATRICES_COUNT[i+1], i);
            }



        }
        else {

            int subSize;
            double[][] tempMatrix;
            subcriteriaMatrix = new double[CAT_SUBCRITERIA_MATRICES_COUNT[0]][][];
            subcriteriaPriorityMatrix = new double[CAT_SUBCRITERIA_MATRICES_COUNT[0]][][];
            subcriteriaPriorityVector = new double[CAT_SUBCRITERIA_MATRICES_COUNT[0]][];

            int sentinelCount = 0;

            for (int h = 0; h < CAT_SUBCRITERIA_MATRICES_COUNT[0]; h++)  {
                subSize = CAT_SUBCRITERIA_MATRICES_COUNT[h+1];
                subcriteriaPriorityMatrix[h] = new double[subSize][subSize];
                tempMatrix = new double[subSize][subSize];

                int tempVal = -1;
                int j;

                for (int i = 0; i < subSize; i++ ) {
                    for (j = i; j < subSize; j++) {
                        if (i == j) {
                            tempMatrix[i][j] = 1.0;
                        }
                        else {
                            tempVal = mViewModel.getSubcriteriaAnswer(sentinelCount);
                            if (tempVal > 0) {
                                tempMatrix[i][j] = tempVal * 1.0;
                                tempMatrix[j][i] = 1 / (tempVal * 1.0);
                            }
                            else {
                                tempMatrix[i][j] = 1 / (tempVal * (-1.0));
                                tempMatrix[j][i] = tempVal * (-1.0);
                            }
                            sentinelCount++;
                        }
                    }
                }
                subcriteriaMatrix[h] = Arrays.copyOf(tempMatrix, subSize);
                sentinelCount = 0;

            }

            // TODO: REMOVE LATER
            //check matrix
            for (int i = 0; i < CAT_SUBCRITERIA_MATRICES_COUNT[0]; i++) {

                System.out.println("SUBCRITERIA MATRIX #" + (i+1));
                for (int k = 0; k < CAT_SUBCRITERIA_MATRICES_COUNT[i+1]; k++) {

                    for (int l = 0; l < CAT_SUBCRITERIA_MATRICES_COUNT[i+1]; l++) {
                        System.out.print(subcriteriaMatrix[i][k][l] + "   ");
                    }
                    System.out.println();
                }
                System.out.println();
                deriveSubcriteriaPriorities(CAT_SUBCRITERIA_MATRICES_COUNT[i+1], i);
            }
        }
    }
    private void constructIntensityMatrix() {
        if (animalType == 1) {
            double[][] tempMatrix;
            intensityMatrix = new double[DOG_INTENSITY_MATRICES_COUNT][INTENSITY_COUNT][INTENSITY_COUNT];
            intensityPriorityMatrix = new double[DOG_INTENSITY_MATRICES_COUNT][INTENSITY_COUNT][INTENSITY_COUNT];
            intensityPriorityVector = new double[DOG_INTENSITY_MATRICES_COUNT][];
            idealizedIntensityPriorityVector = new double[DOG_INTENSITY_MATRICES_COUNT][];

            int sentinelCount = 0;

            for (int h = 0; h < DOG_INTENSITY_MATRICES_COUNT; h++)  {
                tempMatrix = new double[INTENSITY_COUNT][INTENSITY_COUNT];

                int tempVal = -1;
                int j;

                for (int i = 0; i < INTENSITY_COUNT; i++ ) {
                    for (j = i; j < INTENSITY_COUNT; j++) {
                        if (i == j) {
                            tempMatrix[i][j] = 1.0;
                        }
                        else {
                            tempVal = mViewModel.getIntensityAnswer(sentinelCount);
                            if (tempVal > 0) {
                                tempMatrix[i][j] = tempVal * 1.0;
                                tempMatrix[j][i] = 1 / (tempVal * 1.0);
                            }
                            else {
                                tempMatrix[i][j] = 1 / (tempVal * (-1.0));
                                tempMatrix[j][i] = tempVal * (-1.0);
                            }
                            sentinelCount++;
                        }
                    }
                }
                intensityMatrix[h] = tempMatrix;

                System.out.println("Intensity matrix added at index " + h);
                for (double[] x : tempMatrix) {
                    for (double y : x) {
                        System.out.print(y + "  ");
                    }
                    System.out.println();
                }

            }

            // TODO: REMOVE LATER
            //check matrix
            for (int i = 0; i < DOG_INTENSITY_MATRICES_COUNT; i++) {

                System.out.println("INTENSITY MATRIX #" + (i+1));
                for (int k = 0; k < INTENSITY_COUNT; k++) {

                    for (int l = 0; l < INTENSITY_COUNT; l++) {
                        System.out.print(intensityMatrix[i][k][l] + "   ");
                    }
                    System.out.println();
                }
                System.out.println();
                deriveIntensityPriorities(i);
            }



        }
        else {

            double[][] tempMatrix;
            intensityMatrix = new double[CAT_INTENSITY_MATRICES_COUNT][INTENSITY_COUNT][INTENSITY_COUNT];
            intensityPriorityMatrix = new double[CAT_INTENSITY_MATRICES_COUNT][INTENSITY_COUNT][INTENSITY_COUNT];
            intensityPriorityVector = new double[CAT_INTENSITY_MATRICES_COUNT][];
            idealizedIntensityPriorityVector = new double[CAT_INTENSITY_MATRICES_COUNT][];

            int sentinelCount = 0;

            for (int h = 0; h < CAT_INTENSITY_MATRICES_COUNT; h++)  {
                tempMatrix = new double[INTENSITY_COUNT][INTENSITY_COUNT];

                int tempVal = -1;
                int j;

                for (int i = 0; i < INTENSITY_COUNT; i++ ) {
                    for (j = i; j < INTENSITY_COUNT; j++) {
                        if (i == j) {
                            tempMatrix[i][j] = 1.0;
                        }
                        else {
                            tempVal = mViewModel.getIntensityAnswer(sentinelCount);
                            if (tempVal > 0) {
                                tempMatrix[i][j] = tempVal * 1.0;
                                tempMatrix[j][i] = 1 / (tempVal * 1.0);
                            }
                            else {
                                tempMatrix[i][j] = 1 / (tempVal * (-1.0));
                                tempMatrix[j][i] = tempVal * (-1.0);
                            }
                            sentinelCount++;
                        }
                    }
                }
                intensityMatrix[h] = tempMatrix;

                System.out.println("Intensity matrix added at index " + h);
                for (double[] x : tempMatrix) {
                    for (double y : x) {
                        System.out.print(y + "  ");
                    }
                    System.out.println();
                }

            }

            // TODO: REMOVE LATER
            //check matrix
            for (int i = 0; i < CAT_INTENSITY_MATRICES_COUNT; i++) {

                System.out.println("INTENSITY MATRIX #" + (i+1));
                for (int k = 0; k < INTENSITY_COUNT; k++) {

                    for (int l = 0; l < INTENSITY_COUNT; l++) {
                        System.out.print(intensityMatrix[i][k][l] + "   ");
                    }
                    System.out.println();
                }
                System.out.println();
                deriveIntensityPriorities(i);
            }

        }
    }
    private void derivePriorities(int matrixDimension) {

        temporaryPriorityMatrix = Arrays.copyOf(mainMatrix, matrixDimension);
        mainPriorityVector = new double[matrixDimension];
        temporaryPriorityVector = new double[matrixDimension];
        int derivationCount = 0;
        double stopCriterion = 0.008;
        boolean lessThanStopCriterion = false;
        double tempSum = 0.0;
        double tempPrioritySum = 0.0;
        double allPrioritySum = 0.0;
        do {
            derivationCount++;
            for (int i = 0; i < matrixDimension; i++) {
                for (int j = 0; j < matrixDimension; j++) {
                    //System.out.println("Tempsum at start: " + tempSum);
                    for (int k = 0; k < matrixDimension; k++) {
                        //System.out.println("Added to tempsum: [" + i + "][" + k + "] * [" + k + "][" + j + "] = " + temporaryPriorityMatrix[i][k] + " + " + temporaryPriorityMatrix[k][j] + " = " + (temporaryPriorityMatrix[i][k] * temporaryPriorityMatrix[k][j]));
                        tempSum += (temporaryPriorityMatrix[i][k] * temporaryPriorityMatrix[k][j]);
                    }
                    mainPriorityMatrix[i][j] = tempSum;
                    //System.out.println("Tempsum set to mainprioritymatrix: " + mainPriorityMatrix[i][j] );
                    tempPrioritySum += tempSum;
                    // reset value
                    tempSum = 0.0;
                    //System.out.println("Tempsum is reset ");
                }
                mainPriorityVector[i] = tempPrioritySum;
                allPrioritySum += tempPrioritySum;
                // reset value
                tempPrioritySum = 0;
            }
            /*
            System.out.println("Printing MATRIX while doing priority values " + derivationCount);
            for (int i = 0; i < matrixDimension; i++) {
                for (int k = 0; k < matrixDimension; k++) {
                    System.out.print(mainPriorityMatrix[i][k] + "   ");
                }
                System.out.println();
            }*/

            // normalize priorities
            for (int i = 0; i < matrixDimension; i++) {
                mainPriorityVector[i] /= allPrioritySum;
                if (derivationCount > 1) {
                    lessThanStopCriterion = Math.abs(mainPriorityVector[i] - temporaryPriorityVector[i]) < stopCriterion;
                }
            }
            /*System.out.println("Printing VECTOR while doing priority values " + derivationCount);
            for (int i = 0; i < matrixDimension; i++) {
                System.out.print(mainPriorityVector[i]);
            }
            System.out.println();*/

            // deep copy of previous matrix
            for (int i = 0; i < matrixDimension; i++) {
                temporaryPriorityMatrix[i] = Arrays.copyOf(mainPriorityMatrix[i], matrixDimension);
            }
            // deep copy of previous vector
            temporaryPriorityVector = Arrays.copyOf(mainPriorityVector, matrixDimension);

            // reset value
            allPrioritySum = 0.0;
        } while (!lessThanStopCriterion);
/*
        System.out.println("Printing MATRIX after doing priority values " + derivationCount);
        for (int i = 0; i < matrixDimension; i++) {
            for (int k = 0; k < matrixDimension; k++) {
                System.out.print(mainPriorityMatrix[i][k] + "   ");
            }
            System.out.println();
        }
        System.out.println("Printing VECTOR after doing priority values " + derivationCount);
        for (int i = 0; i < matrixDimension; i++) {
            System.out.print(mainPriorityVector[i]);
        }
        System.out.println();
*/
    }
    private void deriveSubcriteriaPriorities(int matrixDimension, int matrixIndex) {

        temporaryPriorityMatrix = new double[matrixDimension][matrixDimension];
        temporaryPriorityMatrix = Arrays.copyOf(subcriteriaMatrix[matrixIndex], matrixDimension);
        subcriteriaPriorityVector[matrixIndex] = new double[matrixDimension];
        temporaryPriorityVector = new double[matrixDimension];

        int derivationCount = 0;
        double stopCriterion = 0.008;
        boolean lessThanStopCriterion = false;
        double tempSum = 0.0;
        double tempPrioritySum = 0.0;
        double allPrioritySum = 0.0;
        do {
            derivationCount++;
            for (int i = 0; i < matrixDimension; i++) {
                for (int j = 0; j < matrixDimension; j++) {
                    //System.out.println("Tempsum at start: " + tempSum);
                    for (int k = 0; k < matrixDimension; k++) {
                        //System.out.println("Added to tempsum: [" + i + "][" + k + "] * [" + k + "][" + j + "] = " + temporaryPriorityMatrix[i][k] + " + " + temporaryPriorityMatrix[k][j] + " = " + (temporaryPriorityMatrix[i][k] * temporaryPriorityMatrix[k][j]));
                        tempSum += (temporaryPriorityMatrix[i][k] * temporaryPriorityMatrix[k][j]);
                    }
                    subcriteriaPriorityMatrix[matrixIndex][i][j] = tempSum;
                    //System.out.println("Tempsum set to mainprioritymatrix: " + mainPriorityMatrix[i][j] );
                    tempPrioritySum += tempSum;
                    // reset value

                    tempSum = 0.0;
                    //System.out.println("Tempsum is reset ");
                }
                subcriteriaPriorityVector[matrixIndex][i] = tempPrioritySum;
                allPrioritySum += tempPrioritySum;
                // reset value
                tempPrioritySum = 0;
            }

            /*
            System.out.println("Printing SUBCRITERIA MATRIX while doing priority values " + derivationCount);
            for (int i = 0; i < matrixDimension; i++) {
                for (int k = 0; k < matrixDimension; k++) {
                    System.out.print(subcriteriaPriorityMatrix[matrixIndex][i][k] + "   ");
                }
                System.out.println();
            }*/


            // normalize priorities
            for (int i = 0; i < matrixDimension; i++) {
                subcriteriaPriorityVector[matrixIndex][i] /= allPrioritySum;
                if (derivationCount > 1) {
                    lessThanStopCriterion = Math.abs(subcriteriaPriorityVector[matrixIndex][i] - temporaryPriorityVector[i]) < stopCriterion;
                }
            }
            /*
            System.out.println("Printing SUBCRITERIA VECTOR while doing priority values " + derivationCount);
            for (int i = 0; i < matrixDimension; i++) {
                System.out.print(subcriteriaPriorityVector[matrixIndex][i]);
            }
            System.out.println();*/

            // deep copy of previous matrix
            for (int i = 0; i < matrixDimension; i++) {
                temporaryPriorityMatrix[i] = Arrays.copyOf(subcriteriaPriorityMatrix[matrixIndex][i], matrixDimension);
            }
            // deep copy of previous vector
            temporaryPriorityVector = Arrays.copyOf(subcriteriaPriorityVector[matrixIndex], matrixDimension);

            // reset value
            allPrioritySum = 0.0;
        } while (!lessThanStopCriterion);
/*
        System.out.println("Printing SUBCRITERIA MATRIX after doing priority values " + derivationCount);
        for (int i = 0; i < matrixDimension; i++) {
            for (int k = 0; k < matrixDimension; k++) {
                System.out.print(subcriteriaPriorityMatrix[matrixIndex][i][k] + "   ");
            }
            System.out.println();
        }
        System.out.println("Printing SUBCRITERIA VECTOR after doing priority values " + derivationCount);
        for (int i = 0; i < matrixDimension; i++) {
            System.out.print(subcriteriaPriorityVector[matrixIndex][i]);
        }
        System.out.println();
*/

    }
    private void deriveIntensityPriorities(int matrixIndex) {

        temporaryPriorityMatrix = new double[INTENSITY_COUNT][INTENSITY_COUNT];
        temporaryPriorityMatrix = Arrays.copyOf(intensityMatrix[matrixIndex], INTENSITY_COUNT);
        intensityPriorityVector[matrixIndex] = new double[INTENSITY_COUNT];
        idealizedIntensityPriorityVector[matrixIndex] = new double[INTENSITY_COUNT];
        temporaryPriorityVector = new double[INTENSITY_COUNT];

        int derivationCount = 0;
        double stopCriterion = 0.1;
        boolean lessThanStopCriterion = false;
        double tempSum = 0.0;
        double tempPrioritySum = 0.0;
        double allPrioritySum = 0.0;
        do {
            derivationCount++;
            for (int i = 0; i < INTENSITY_COUNT; i++) {
                for (int j = 0; j < INTENSITY_COUNT; j++) {
                    //System.out.println("Tempsum at start: " + tempSum);
                    for (int k = 0; k < INTENSITY_COUNT; k++) {
                        //System.out.println("Added to tempsum: [" + i + "][" + k + "] * [" + k + "][" + j + "] = " + temporaryPriorityMatrix[i][k] + " + " + temporaryPriorityMatrix[k][j] + " = " + (temporaryPriorityMatrix[i][k] * temporaryPriorityMatrix[k][j]));
                        tempSum += (temporaryPriorityMatrix[i][k] * temporaryPriorityMatrix[k][j]);
                    }
                    intensityPriorityMatrix[matrixIndex][i][j] = tempSum;
                    //System.out.println("Tempsum set to mainprioritymatrix: " + mainPriorityMatrix[i][j] );
                    tempPrioritySum += tempSum;
                    // reset value

                    tempSum = 0.0;
                    //System.out.println("Tempsum is reset ");
                }
                intensityPriorityVector[matrixIndex][i] = tempPrioritySum;
                allPrioritySum += tempPrioritySum;
                // reset value
                tempPrioritySum = 0;
            }
            /*
            System.out.println("Printing INTENSITY MATRIX while doing priority values " + derivationCount);
            for (int i = 0; i < INTENSITY_COUNT; i++) {
                for (int k = 0; k < INTENSITY_COUNT; k++) {
                    System.out.print(intensityPriorityMatrix[matrixIndex][i][k] + "   ");
                }
                System.out.println();
            }

             */


            // normalize priorities
            for (int i = 0; i < INTENSITY_COUNT; i++) {
                intensityPriorityVector[matrixIndex][i] /= allPrioritySum;
                if (derivationCount > 1) {
                    lessThanStopCriterion = Math.abs(intensityPriorityVector[matrixIndex][i] - temporaryPriorityVector[i]) < stopCriterion;
                }
            }
            /*
            System.out.println("Printing INTENSITY VECTOR while doing priority values " + derivationCount);
            for (int i = 0; i < INTENSITY_COUNT; i++) {
                System.out.print(intensityPriorityVector[matrixIndex][i]);
            }
            System.out.println();*/

            // deep copy of previous matrix
            for (int i = 0; i < INTENSITY_COUNT; i++) {
                temporaryPriorityMatrix[i] = Arrays.copyOf(intensityPriorityMatrix[matrixIndex][i], INTENSITY_COUNT);
            }
            // deep copy of previous vector
            temporaryPriorityVector = Arrays.copyOf(intensityPriorityVector[matrixIndex], INTENSITY_COUNT);

            // reset value
            allPrioritySum = 0.0;
        } while (!lessThanStopCriterion);
/*
        System.out.println("Printing INTENSITY MATRIX after doing priority values " + derivationCount);
        for (int i = 0; i < INTENSITY_COUNT; i++) {
            for (int k = 0; k < INTENSITY_COUNT; k++) {
                System.out.print(intensityPriorityMatrix[matrixIndex][i][k] + "   ");
            }
            System.out.println();
        }
        System.out.println("Printing INTENSITY VECTOR after doing priority values " + derivationCount);
        for (int i = 0; i < INTENSITY_COUNT; i++) {
            System.out.print(intensityPriorityVector[matrixIndex][i]);
        }
        System.out.println();
*/

        idealizedIntensityPriorityVector[matrixIndex] = Arrays.copyOf(idealizePriorities(intensityPriorityVector[matrixIndex]), INTENSITY_COUNT);
/*
        // CHECKING IDEALIZED PRIORITIES:
        System.out.println("Printing IDEALIZED INTENSITY PRIORITY VECTOR for Intensity Matrix " + matrixIndex);
        for (double t : idealizedIntensityPriorityVector[matrixIndex]) {
            System.out.print(
                    new BigDecimal(t).setScale(3, RoundingMode.HALF_UP).doubleValue() + " ");
        }
        System.out.println();
*/
    }
    private double[] idealizePriorities(double[] baseMatrix) {
        double[] idealMatrix = new double[baseMatrix.length];
        double highestValue = 0.0;
        for (double e : baseMatrix) {
            highestValue = (Math.max(e, highestValue));
        }
        for (int i = 0; i < baseMatrix.length; i++) {
            idealMatrix[i] = baseMatrix[i]/highestValue;
        }
        return idealMatrix;
    }
    private void populateGlobalPrioritiesVector() {
        int t = 0;
        int subCounter = 0;
        if (animalType == 1) {
            globalPrioritiesVector = new double[DOG_INTENSITY_MATRICES_COUNT];
            for (int i = 0; i < DOG_MAIN_MATRICES_SIZE; i++) {
                //System.out.println("AT MAIN INDEX: " + i);
                if (hasSubcriteria[i]) {
                    ++subCounter;
                    for (int j = 0; j < DOG_SUBCRITERIA_MATRICES_COUNT[subCounter]; j++ ){
                        //System.out.println("AT SUBCRITERIA INDEX: " + subCounter + " - added a subcriteria priority");
                        globalPrioritiesVector[t] = mainPriorityVector[i] * subcriteriaPriorityVector[subCounter-1][j];

                        t++;
                    }
                }
                else {
                    //System.out.println("Added main priority!");
                    globalPrioritiesVector[t] = mainPriorityVector[i];
                    t++;
                }
            }
        }
        else {

            globalPrioritiesVector = new double[CAT_INTENSITY_MATRICES_COUNT];
            for (int i = 0; i < CAT_MAIN_MATRICES_SIZE; i++) {
                //System.out.println("AT MAIN INDEX: " + i);
                if (hasSubcriteria[i]) {
                    ++subCounter;
                    for (int j = 0; j < CAT_SUBCRITERIA_MATRICES_COUNT[subCounter]; j++ ){
                        //System.out.println("AT SUBCRITERIA INDEX: " + subCounter + " - added a subcriteria priority");
                        globalPrioritiesVector[t] = mainPriorityVector[i] * subcriteriaPriorityVector[subCounter-1][j];

                        t++;
                    }
                }
                else {
                   // System.out.println("Added main priority!");
                    globalPrioritiesVector[t] = mainPriorityVector[i];
                    t++;
                }
            }
        }
        //TODO: REMOVE LATER
        System.out.println("PRINTING GLOBAL PRIORITIES");
        for (double e : globalPrioritiesVector) {
            System.out.println(e + " ");
        }
    }
    private void populatePopulationScoreMatrix() {
        if(animalType == 1) {
            double populationSum = 0.0;
            populationScoreMatrix = new double[numberOfAlternatives][DOG_INTENSITY_MATRICES_COUNT+3];


            for (int i = 0; i < numberOfAlternatives; i++){
                System.out.println("Alternative: " + alternatives.get(i).getName());
                for (int j = 0; j < DOG_INTENSITY_MATRICES_COUNT; j++) {
                    System.out.print("Criteria " + alternatives.get(i).getCriteriaValues().get(j).getCriteria().getName() + ": ");
                    System.out.print(alternatives.get(i).getCriteriaValues().get(j).getIntensityLevel()+ " ");
                }
                System.out.println();
            }

            // COLUMN 0-10 performance score; 11 total score; 12 normalized score, 13 indexNumber
            for (int i =0; i < numberOfAlternatives; i++){
                System.out.println("ALTERNATIVE: " + alternatives.get(i).getName());
                for (int j = 0; j < DOG_INTENSITY_MATRICES_COUNT; j++) {
                    System.out.println("Current Intensity: " + j);
                    System.out.println("Current Intensity Level: " + (alternatives.get(i).getCriteriaValues().get(j).getIntensityLevel()-1));
                    System.out.println("idealizedIntensityPriorityVector[][]: " + (idealizedIntensityPriorityVector[j][alternatives.get(i).getCriteriaValues().get(j).getIntensityLevel()-1]));
                    System.out.println("populationScoreMatrix[][]: " + (populationScoreMatrix[i][j]));
                    populationScoreMatrix[i][j] = idealizedIntensityPriorityVector[j][alternatives.get(i).getCriteriaValues().get(j).getIntensityLevel()-1];
                    populationScoreMatrix[i][j] *= globalPrioritiesVector[j];
                    populationSum += populationScoreMatrix[i][j];
                    System.out.print("(" + (j+1) + ") " + populationScoreMatrix[i][j] + "  ");
                }
                System.out.println();

                // save sum at column 11
                populationScoreMatrix[i][DOG_INTENSITY_MATRICES_COUNT] = populationSum;

                // save original index at column 13
                populationScoreMatrix[i][DOG_INTENSITY_MATRICES_COUNT+2] = i;

                // SAVE TO ALTERNATIVE ITSELF
                alternatives.get(i).setCalculatedPerformanceScore(populationSum);

                // reset value
                populationSum = 0.0;
            }


            // TODO: REMOVE LATER
            // CHECK
            System.out.println("CHECK POPULATION SCORE");
            System.out.println("Number of Alternatives: " + numberOfAlternatives);
            for (int i = 0; i < numberOfAlternatives; i++) {
                System.out.println("Alternative #" + (i+1));
                for (int j = 0; j < DOG_INTENSITY_MATRICES_COUNT+3; j++) {
                    System.out.print(new BigDecimal(populationScoreMatrix[i][j]).setScale(3, RoundingMode.HALF_UP).doubleValue() + " ");

                }
                System.out.println();
            }
        }
        else {
            double populationSum = 0.0;
            populationScoreMatrix = new double[numberOfAlternatives][CAT_INTENSITY_MATRICES_COUNT+3];
            // COLUMN 0-8 performance score; 9 total score; 10 normalized score, 11 indexNumber
            for (int i =0; i < numberOfAlternatives; i++){
                for (int j = 0; j < CAT_INTENSITY_MATRICES_COUNT; j++) {
                    populationScoreMatrix[i][j] = idealizedIntensityPriorityVector[j][alternatives.get(i).getCriteriaValues().get(j).getIntensityLevel()-1];
                    populationScoreMatrix[i][j] *= globalPrioritiesVector[j];
                    populationSum += populationScoreMatrix[i][j];
                }

                // save sum at column 11
                populationScoreMatrix[i][CAT_INTENSITY_MATRICES_COUNT] = populationSum;

                // save original index at column 13
                populationScoreMatrix[i][CAT_INTENSITY_MATRICES_COUNT+2] = i;

                // SAVE TO ALTERNATIVE ITSELF
                alternatives.get(i).setCalculatedPerformanceScore(populationSum);

                // reset value
                populationSum = 0.0;
            }


            // TODO: REMOVE LATER
            // CHECK
            System.out.println("CHECK POPULATION SCORE");
            System.out.println("Number of Alternatives: " + numberOfAlternatives);
            for (int i = 0; i < numberOfAlternatives; i++) {
                System.out.println("Alternative #" + (i+1));
                for (int j = 0; j < CAT_INTENSITY_MATRICES_COUNT+3; j++) {
                    System.out.print(new BigDecimal(populationScoreMatrix[i][j]).setScale(3, RoundingMode.HALF_UP).doubleValue() + " ");

                }
                System.out.println();
            }

        }

    }
    private void sortAlternativesByPerformanceScore() {
        Collections.sort(alternatives, new Comparator<MCDMAlternative>() {

            @Override
            public int compare(MCDMAlternative o1, MCDMAlternative o2) {
                return Double.compare(o2.getCalculatedPerformanceScore(), o1.getCalculatedPerformanceScore());
            }

        });
    }
    private void saveResultsToModel() {
        for (int i = 0; i < numberOfAlternatives; i++) {
            System.out.println(alternatives.get(i).getName() + ": " + alternatives.get(i).getCalculatedPerformanceScore());
        }
        int resultsSize = Math.min(alternatives.size(), 3);

        MCDMAlternative[] topThree = new MCDMAlternative[resultsSize];
        for (int i = 0; i < resultsSize; i++) {
            // TODO: Implement checker if less than 3

            topThree[i] = alternatives.get(i);
        }
        mViewModel.setTopThree(topThree);
        System.out.println("ADDED TOP " + resultsSize + ":");
        for (int i = 0; i < resultsSize; i++) {
            // TODO: Implement checker if less than 3

            System.out.println(mViewModel.getTopThree()[i].getImageName());
        }
    }
    private void saveToDatabase() {

        authProfile = FirebaseAuth.getInstance();
        firebaseUser = authProfile.getCurrentUser();
        adopterEmail = firebaseUser.getEmail();
        adoptersDBRef.orderByChild("email").equalTo(adopterEmail)
                .addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        if(snapshot.exists()) {
                            for (DataSnapshot ds : snapshot.getChildren()) {
                                adopterID = ds.getKey();
                            }
                        }

                        DatabaseReference reference = FirebaseDatabase.getInstance().getReference();
                        String applicationID = reference.push().getKey();
                        // get date and time
                        dateAnswered = new SimpleDateFormat("MM/dd/yyyy").format(Calendar.getInstance().getTime());
                        timeAnswered = new SimpleDateFormat("HH:mm:ss").format(Calendar.getInstance().getTime());
                        System.out.println("applicationID === " + applicationID);
                        finished = true;

                        // instantiate classes
                        MCDMContainerQuestionnaireAnswers conQuestion = new MCDMContainerQuestionnaireAnswers(animalType, mViewModel.getMainAnswers(), mViewModel.getSubcriteriaAnswers(), mViewModel.getIntensityAnswers(), finished, dateAnswered, timeAnswered);
                       // MCDMContainerOtherAnswers conOthers = new MCDMContainerOtherAnswers();

                        // save to adopter
                        adoptersDBRef.child(adopterID).child("MCDM").child((animalType == 1 ? "Dog" : "Cat")).setValue(conQuestion);

                        // save to adopters all pet history
                        Double calcHolder;
                        String petIDHolder;
                        for (MCDMAlternative e : alternatives) {
                            calcHolder = e.getCalculatedPerformanceScore();
                            petIDHolder = e.getId();
                            Map<String, Object> matchPercentageMap = new HashMap<>();
                            matchPercentageMap.put("MatchPercentage", calcHolder);
                            adoptersDBRef.child(adopterID).child("AdopterAllPets").child(petIDHolder).updateChildren(matchPercentageMap);
                        }

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });


    }
}

