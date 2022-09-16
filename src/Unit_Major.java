public class Unit_Major extends Unit {

    String unitID;
    int unitLevel;
    double assignmentMark;
    double projectMark;
    double weeklyLab;
    double finalMajor;
    double overalMark;
    String finalGrade;
    String majorEnrollment;
    Unit_Major() { // Default constructor
        super();
        assignmentMark = projectMark = weeklyLab = finalMajor = overalMark = 0;
        finalGrade = "";
    }

    Unit_Major(String unitID, int unitLevel,double assignmentMark, double projectMark, double weeklyLab, double finalMajor) { // Parameterized constructor
        this.unitID = unitID;
        this.unitLevel = unitLevel;
        this.majorEnrollment = getMajorEnrollment(); // inherits Unit class
        this.assignmentMark = assignmentMark;
        this.projectMark = projectMark;
        this.weeklyLab = weeklyLab;
        this.finalMajor = finalMajor;
        this.overalMark = 0.0;
        this.finalGrade = "";
    }

    void calculateWeightedAvg(){
        overalMark = (assignmentMark * 0.15); // The two assignment worths 15%
        overalMark += projectMark * 0.35; // the project work is worth 35%
        overalMark += weeklyLab * 0.10; // the weekly work is worth 15%
        overalMark += finalMajor * 0.40; //the final work is worth 40% of the final grade.

    if(overalMark > 0.0) {
        if (overalMark >= 80.0) //  An overall mark of 80 or higher is an HD
            finalGrade = "HD";
        else if (overalMark >= 70.0) // overall mark of 70 or higher (but less than 80) is a D
            finalGrade = "D";
        else if (overalMark >= 60.0) // an overall mark of 60 or higher (but less than 70) is a C
            finalGrade = "C";
        else if (overalMark >= 50.0) // an overall mark of 50 or higher (but less than 60) is a P
            finalGrade = "P";
        else if (overalMark < 50.0)
            finalGrade = "N"; // and an overall mark below 50 is an N
    } else {
        finalGrade = finalgradeReport(); //  A final grade reporting method for reporting the “NA” for not available.
                                        // inherits Unit class
    }
    }

    double getOveralMark() {
        calculateWeightedAvg();
        return overalMark;
    }
    public String getFinalGrade() {
        calculateWeightedAvg();
        return finalGrade;
    }
}