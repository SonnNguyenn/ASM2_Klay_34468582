public class Student_Common extends Student  {
    final Unit_Common Student_CM; // Create an Student_CM variable with Unit_Common class and final keywork to specify this
                                    // only class can call and use the object and cannot be overriden by any subclasses

    Student_Common() { // default constructor to store default values in instance variables
        super(); // being call inside method that override it in this subclass (child class)
        Student_CM = new Unit_Common(); // assign Student_CM variable with new Unit_Common() constructor
    }

    Student_Common(String unitID, int unitLevel, String firstName, String lastName, long StudentNo, int day, int month, int year,
                   double asm1, double asm2, double pracWork, double finalExam) {
        super( firstName, lastName, StudentNo, day, month, year); // use the instance variables from superclass (parent class)
        Student_CM = new Unit_Common(unitID, unitLevel, asm1,  asm2,  pracWork,  finalExam);// assign value to class Unit_Common
    }

    //  calculating the weighted average of the student's performance in the assessment components
    public double  getOveralMark() {
        return Student_CM.getOveralMark();
    }

    public String getFinalGrade() {
        return Student_CM.getFinalGrade();
    }

    // Override reportGrade method to return student common information
    @Override
    public String reportGrade()
    {
        String showGrade = super.reportGrade(); // super keyword to call the toString method from the superclass (parent class_
        showGrade +=
                "\n Unit Number: " + " " + Student_CM.unitID + " " +
                        "\n Unit Level: " +(Student_CM.unitLevel==1?"C":"M") + " " + // if unitlevel == 1 (true) then this = "C" or else "M"
                        "\n Assignment1: " + Student_CM.assignment1 +
                        "\n Assignment2: " + Student_CM.assignment2 +
                        "\n Practical Work: " + Student_CM.practicalWork +
                        "\n Final Examination: " + Student_CM.finalExamination +
                        "\n Weighted Average: " + getOveralMark() +
                        "\n Grade: " + getFinalGrade()+
                        "\n----------------------------------";
        return showGrade;
    }
    @Override
    public String MakeStringForCSV() { // create this to export TestResult.csv file
        String ForCSV = (Student_CM.unitLevel<2?"C":"M") + "," + super.MakeStringForCSV() // override method
                + "," + Student_CM.unitID + "," +Student_CM.unitLevel + ","
                + Student_CM.assignment1 + "," + Student_CM.assignment2 + "," +
                Student_CM.practicalWork +"," +Student_CM.finalExamination +","
                + getOveralMark() +"," + getFinalGrade();
        return ForCSV;
    }
}