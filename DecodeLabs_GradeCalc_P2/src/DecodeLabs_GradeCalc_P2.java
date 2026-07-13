import java.util.Scanner;

/**
 * Project 2: Student Grade Calculator
 * Powered by DecodeLabs
 */
public class DecodeLabs_GradeCalc_P2 {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        System.out.println("===========================================");
        System.out.println("    DECODELABS STUDENT GRADE CALCULATOR    ");
        System.out.println("===========================================");

        // Input Phase: Determine number of subjects
        int numSubjects = 0;
        while (numSubjects <= 0) {
            System.out.print("Enter the number of subjects: ");
            try {
                numSubjects = Integer.parseInt(scanner.nextLine());
                if (numSubjects <= 0) {
                    System.out.println("ERROR: Please enter a positive number of subjects.");
                }
            } catch (NumberFormatException e) {
                System.out.println("ERROR: Invalid input. Please enter a valid integer.");
            }
        }

        // Accumulator Loop: Collect marks for each subject
        int totalMarks = 0;
        for (int i = 1; i <= numSubjects; i++) {
            int marks = -1;
            while (marks < 0 || marks > 100) {
                System.out.print("Enter marks for Subject " + i + " (0-100): ");
                try {
                    marks = Integer.parseInt(scanner.nextLine());
                    // Defensive Programming: Validate marks BEFORE processing
                    if (marks < 0 || marks > 100) {
                        System.out.println("ERROR: Marks must be between 0 and 100. Please re-enter.");
                    }
                } catch (NumberFormatException e) {
                    System.out.println("ERROR: Invalid input. Please enter an integer between 0 and 100.");
                }
            }
            totalMarks += marks;
        }

        // Process Phase: Calculation (The Engine Room)
        // Using explicit casting to double to preserve precision (85.5 instead of 85.0)
        double averagePercentage = (double) totalMarks / numSubjects;

        // Process Phase: Classification (The Logic Ladder)
        String grade;
        String passFail;
        
        if (averagePercentage >= 90) {
            grade = "A";
            passFail = "PASS";
        } else if (averagePercentage >= 80) {
            grade = "B";
            passFail = "PASS";
        } else if (averagePercentage >= 70) {
            grade = "C";
            passFail = "PASS";
        } else if (averagePercentage >= 60) {
            grade = "D";
            passFail = "PASS";
        } else if (averagePercentage >= 50) {
            grade = "E";
            passFail = "PASS";
        } else {
            grade = "F";
            passFail = "FAIL";
        }

        // Output Phase: The Presentation Layer
        System.out.println("\n===========================================");
        System.out.println("              STUDENT REPORT               ");
        System.out.println("===========================================");
        System.out.println("Total Subjects:     " + numSubjects);
        System.out.println("Total Marks:        " + totalMarks + " / " + (numSubjects * 100));
        
        // Professional formatting using printf with 2 decimal places
        System.out.printf("Average Percentage: %.2f%%\n", averagePercentage);
        
        System.out.println("Final Grade:        " + grade);
        System.out.println("Status:             " + passFail);
        System.out.println("===========================================");
        System.out.println("     Thank you for using DecodeLabs!       ");
        System.out.println("===========================================");

        scanner.close();
    }
}
