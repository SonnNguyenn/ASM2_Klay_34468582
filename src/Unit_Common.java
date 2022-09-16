public class Unit_Common extends Unit {
    String unitID;
    int unitLevel;
    double assignment1;
    double assignment2;
    double practicalWork;
    double finalExamination;
    double overalMark;
    String finalGrade;
    String commonEnrollment;

    public Unit_Common() { // default constructor to store default values in instance variables
        super(); // being call inside method that overriden it in this subclass (child class)
        assignment1 = assignment2 = practicalWork = finalExamination = overalMark = 0.0;
        finalGrade = "";
    }

    public Unit_Common(String unitID, int unitLevel,double asm1, double asm2, double pracWork, double finalExam) {
        this.unitID = unitID;
        this.unitLevel = unitLevel;
        this.commonEnrollment = getCommonEnrollment(); //  inherits Unit class String method
        this.assignment1 = asm1;
        this.assignment2 = asm2;
        this.practicalWork = pracWork;
        this.finalExamination = finalExam;
        this.overalMark = 0;
        this.finalGrade = "";
    }

    //  calculating the weighted average of the student's performance in the assessment components
    void calculateWeightedAvg(){
        this.overalMark = (this.assignment1 * 0.30) + (this.assignment2 * 0.30);
        // The two assignments together count for a total of 60%
        // (30% each) of the final grade
        this.overalMark += this.practicalWork * 0.15; // the practical work is worth 15%
        this.overalMark += this.finalExamination * 0.25; //the final exam is worth 25% of the final grade
        if(this.overalMark > 0.0) {
            if (this.overalMark >= 80.0) //  An overall mark of 80 or higher is an HD
                this.finalGrade = "HD";
            else if (this.overalMark >= 70.0) // overall mark of 70 or higher (but less than 80) is a D
                this.finalGrade = "D";
            else if (this.overalMark >= 60.0) // an overall mark of 60 or higher (but less than 70) is a C
                this.finalGrade = "C";
            else if (this.overalMark >= 50.0) // an overall mark of 50 or higher (but less than 60) is a P
                this.finalGrade = "P";
            else if (this.overalMark < 50.0)
                this.finalGrade = "N"; // and an overall mark below 50 is an N
        } else {
            this.finalGrade = finalgradeReport(); //  A final grade reporting method for reporting the “NA” for not available.
        }                                       // inherits Unit class finalgradeReport()
    }

    double getOveralMark() { //
        calculateWeightedAvg();// recalculate overalMark before assigning this to Student Common
        return this.overalMark;
    }
    public String getFinalGrade() {
        calculateWeightedAvg();// finalGrade assigns value to Student Common
        return this.finalGrade;
    }
}