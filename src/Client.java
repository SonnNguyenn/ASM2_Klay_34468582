import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Client {

    static Scanner sc = new Scanner(System.in); // create a Scanner Object to
    static ArrayList<Student> students = new ArrayList<Student>();

    int menu() { // print menu option to the screen
        System.out.print("\n\n ********** MENU ********** ");
        System.out.print("\n\t 1 - Quit " +
                "\n\t 2 - Add Student" +
                "\n\t 3 - Remove Student" +
                "\n\t 4 - Output all details  " +
                "\n\t 5 - Common Student with Below or Above average" +
                "\n\t 6 - Report student grade by StudentNo" +
                "\n\t 7 - Sort ArrayList by order of the StudentNo" +
                "\n\t 8 - Output the sorted ArrayList to CSV" +
                "\n\n Please select your choice: ");
        return sc.nextInt(); // return sc.nextInt to input the number
    }
    public static void StudentInfo() {
        System.out.println("Student name: Son Nguyen Thanh");// print student name
        System.out.println("Student number: 34468582");// print student number
        System.out.println("Mode of enrolment: Online");// print enrollment mode
        System.out.println("Teacher name: Professor Kevin Wong");// print teacher name
        System.out.println("Tutorial attendance day and time: Saturday, 2 April 2022, 11:59 PM");
    }
    public static void main(String[] s) {
        Client sr = new Client();
        sr.StudentInfo();
        do { // do the switch case option
            switch (sr.menu()) {
                case 1:
                    System.exit(0); // exit the program
                case 2:
                    sr.readStudents(); // add the students.csv into Array
                    break;
                case 3:
                    sr.removeStudent(); // remove Student by ID
                    break;
                case 4:
                    sr.showAllStudent(); // output all student info
                    break;
                case 5:
                    sr.showCommonStudentBelowAboveAvg(); // count above and below avg grade for common student
                    break;
                case 6:
                    sr.showReportThGradeStudent(); // show the specific student by Student ID
                    break;
                case 7:
                    sr.SortListStudentByIDNumber(); // sort student in the ArrayList by bubble sort
                    break;
                case 8:
                    sr.OutputSortedArrayListSToCSV(); // output sorted array into csv file
                    break;

                default:
                    System.out.print("\n Invalid choice!!"); // print Invalid when there is no option chose meet above number
            }
        } while (true);
    }

    public void readStudents() {

        try (BufferedReader br = new BufferedReader(new FileReader("Students.csv")))
        { // BufferredReder and FileReader built-in functions to read csv file

            String line; // initilize line as String
            while ((line = br.readLine()) != null) { // assign line with br and only readLine from Students.csv when it's not null

                String[] values = line.split(","); // A String Array that stores split value index with "," in between
                Student st; // st variable as each Student in the ArrayList
                // Because csv is always a String file so it has to be Parsed into approriate data type in order to assign to Student Objects
                String FisrtName = values[1]; // FirstName and LastName are already String as index 1 and 2
                String LastName = values[2];
                Long StudentNo = Long.parseLong(values[3]); // ParseLong datatype from String csv data type at index 3
                int Day = Integer.parseInt(values[4]); // ParseInt datatype from String csv data type at index 4
                int Month = Integer.parseInt(values[5]); // ParseInt datatype from String csv data type at index 5
                int Year = Integer.parseInt(values[6]); // ParseInt datatype from String csv data type at index 6
                if(values[0].toString().trim().equals("C")) // index 0 == to "C" as Common Student
                {
                    double asm1 = Double.parseDouble(values[7]); // ParseDouble datatype from String csv data type at index 7
                    double asm2 = Double.parseDouble(values[8]); // ParseDouble datatype from String csv data type at index 8
                    double pracWork = Double.parseDouble(values[9]); // ParseDouble datatype from String csv data type at index 9
                    double finalExam = Double.parseDouble(values[10]); // ParseDouble datatype from String csv data type at index 10
                    st = new Student_Common("ICT167",1,FisrtName,LastName,StudentNo,Day,Month,Year,asm1,asm2,pracWork,finalExam);
                // assign st variable (Parent class) to Student_Common Object (Child class) with approriate values above
                }

                else {
                    double assignmentMark=Double.parseDouble(values[7]);
                    double projectMark=Double.parseDouble(values[8]);
                    double weeklyLab=Double.parseDouble(values[9]);
                    double finalMajor=Double.parseDouble(values[10]);
                    st = new Student_Major("ICT167",2,FisrtName,LastName,StudentNo,Day,Month,Year,assignmentMark,projectMark,weeklyLab,finalMajor);
                }
                // Add the students into ArrayList
                students.add(st);
            }
            System.out.print("\n Insert completed");
        } catch (FileNotFoundException e) { // handling exception error
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void showCommonStudentBelowAboveAvg() {
        int CountBelowAVG = 0; // initialise CountBelowAVG with int datatype
        int CountAboveAVG = 0; // initialise CountAboveAVG with int datatype
        for (Student student : students) { // for each student in students ArrayList
            if(student.getClass() == Student_Common.class) { // compare each student from the List with Student_Common class
                Student_Common st = (Student_Common) student; // assign to st variable for each Student_Common
                if(st.getOveralMark() > 50.0) { // check if instance object st above or below average with getOverMark method
                    CountAboveAVG++; // Increment by 1
                }
                else {
                    CountBelowAVG++; // Increment by 1
                }
            }
        }
        System.out.println("Below Average Grade is: " + CountBelowAVG);
        System.out.println("Above Average Grade is: " + CountAboveAVG);
    }
    public void OutputSortedArrayListSToCSV() {
        File file = new File("TestResult.csv"); // create csv file name output
        FileWriter fw; // initialise FileWriter with fw variable
        try { // try catch exception
            fw = new FileWriter(file); // FileWriter class is used to write character-oriented data to a csv file
            BufferedWriter bw = new BufferedWriter(fw); // create buffer with varibale name bw and pass fw varible inside the argument
            for (Student student : students) { // for ArrayList Student
                bw.write(student.MakeStringForCSV()); // MakeStringForCSV method add student values into file csv
                bw.newLine();
            }
            bw.close();
            fw.close(); // close File and Buffer
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    public void SortListStudentByIDNumber() {
        for(int i = 0; i < students.size()-i-1; i++) // Use bubble sort
        {
            for(int j = i + 1; j < students.size(); j++) // iteration through unsorted elements
            {
                if(students.get(i).getStudentNo() > students.get(j).getStudentNo())   // check if the students with StudentID are in order
                                                                                    // if not, swap them
                {
                    var temp= students.get(i); // store the student in temporaty varibale

                    students.set(i, students.get(j)); // swap student index i in the ArrayList to j
                    students.set(j, temp); // done swapping
                }
            }
        }
        System.out.println("-------ArrayList is sorted-----------");
    }

    public void showReportThGradeStudent() {
        System.out.print("\nEnter ID Number: "); // prompt Student ID
        long StudentID = sc.nextLong(); // Scanner user input
        Student st = new Student(); // assign st to new Student
        for (Student student : students) { // for each student in the ArrayList students
            if(student.getStudentNo() == StudentID) // Check StudentID in the ArrayList
            {
                st = student; // if true with student = st, StudentID is checked correctly and break the loop
                break;
            }

        }
        if(st.getStudentNo() == 0) // If studentID = 0
        {
            System.out.println("\n Not Found!!"); // Not Found when st variable value doesn't match with StudentID
        }
        else {
            System.out.println(st.reportGrade()); // If it is matched then st will print student info with reportGrade method
        }

    }

    public void showAllStudent() {
        for (Student student : students) { // Print all Student information by for each loop
            System.out.println(student.reportGrade());
        }

    }

    public void removeStudent() {
        System.out.print("\nEnter ID Number: ");
        long StudentID = sc.nextLong();  // Scanner to read user input with long data type
        final Student StudentRemove =  new Student(); // the StudentRemove variable will be used for all of the removeStudent() method
        int index = 0; // initialise index position 0 for the loop
        for (Student student : students) { // for each student  in the students ArrayList
            if(student.getStudentNo() == StudentID) // student in the arrayList id = studentID

            {   System.out.print("\nDo you want to detete? Y/Yes or N/No ");
                String confirm = sc.next(); // SCanner user input with confirm variable to promp yes or no
                if(confirm.equals("yes")||confirm.equals("Yes")||confirm.equals("Y")||confirm.equals("y")) // yes or Yes or Y or y are all ok
                {
                    StudentRemove.setStudentNo(student.getStudentNo()); 
                    StudentRemove.setLastName(student.getLastName());
                    StudentRemove.setFirstName(student.getFirstName());
                    students.remove(index); // remove students Arraylist at index Student ID
                    break;
                }
            }
            index++; // increment when the Student is not matched
        }

        if(StudentRemove.getStudentNo() != 0) // StudentID != then print the result
        {
            System.out.println("-------Student Is Already Removed-----------");
            System.out.println("First Name: "+StudentRemove.getFirstName());
            System.out.println("Last Name: "+StudentRemove.getLastName());
            System.out.println("Student Code ID: "+StudentRemove.getStudentNo());
        }
        else { // if the user choose N/No  then print this
            System.out.println("-------Student Not Found-----------");
        }

    }


}