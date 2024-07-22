import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class StudentGradeTracker {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ArrayList<Integer> grades = new ArrayList<>();
        
        System.out.println("Enter Student Grades (type 1 to finish):");
        
        while (true) {
            int grade = scanner.nextInt();
            if (grade == 1) {
                break;
            }
            grades.add(grade);
        }
        
        if (grades.isEmpty()) {
            System.out.println("No grades were entered.");
        } else {
            int highestGrade = Collections.max(grades);
            int lowestGrade = Collections.min(grades);
            double averageGrade = calculateAverage(grades);

            System.out.println("Highest Grade: " + highestGrade);
            System.out.println("Lowest Grade: " + lowestGrade);
            System.out.printf("Average Grade: %.2f%n", averageGrade);
        }
        
        scanner.close();
    }

    private static double calculateAverage(ArrayList<Integer> grades) {
        int sum = 0;
        for (int grade : grades) {
            sum += grade;
        }
        return (double) sum / grades.size();
    }
}
