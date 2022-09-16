
public class Student_Major extends Student {
    final Unit_Major Student_MJ; // Create Student_MJ variable from Unit_Major to use Unit_Major attributes

    Student_Major() { // Default constructor
        super();
        Student_MJ = new Unit_Major(); // create Student variable
    }

    Student_Major(String unitID, int unitLevel, String firstName, String lastName, long StudentNo,int day, int month, int year,
                  double assignmentMark, double projectMark, double weeklyLab, double finalMajor) { // Parameterized constructor

        super(firstName, lastName, StudentNo, day, month, year);
        Student_MJ = new Unit_Major(unitID, unitLevel, assignmentMark, projectMark, weeklyLab, finalMajor);
    }
    public double  getOveralMark() {
        return Student_MJ.getOveralMark();
    }

    public String  getFinalGrade() {
        return Student_MJ.getFinalGrade();
    }


    // Override reportGrade method to return student major information
    @Override
    public String reportGrade() // this is automatically called when the main method print out the instance of object created
    {
        String showGrade = super.reportGrade();
        showGrade +="\n Unit Number: " + " " + Student_MJ.unitID + " " +
                "\n Unit Level: " + (Student_MJ.unitLevel<2?"C":"M") + " " + // if unitLevel less than 2 is true
                                                                // Will output C or else will output M
                "\n Assignment: " + Student_MJ.assignmentMark +
                "\n Project Mark: " + Student_MJ.projectMark +
                "\n Practical Work: " + Student_MJ.weeklyLab +
                "\n Final Examination: " + Student_MJ.finalMajor +
                "\n Weighted Average: " + getOveralMark() +
                "\n Grade: " + getFinalGrade()+
                "\n----------------------------------";
        return showGrade;

    }
    @Override
    public String MakeStringForCSV() {  // create this to export TestResult.csv file
        String ForCSV = (Student_MJ.unitLevel==1?"C":"M")+"," + super.MakeStringForCSV() + ","
                + Student_MJ.unitID + "," + Student_MJ.unitLevel + ","
                + Student_MJ.assignmentMark + "," + Student_MJ.projectMark + ","
                + Student_MJ.weeklyLab + "," + Student_MJ.finalMajor + ","
                + getOveralMark() + "," + getFinalGrade();
        return ForCSV;
    }

}