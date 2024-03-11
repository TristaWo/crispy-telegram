import java.sql.*;
import java.util.Scanner;
import java.lang.Class;

public class CRUDApp {
    private static Connection connection;
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);

        // For connecting to DB
        String url = "jdbc:postgresql://localhost:5432/COMP3005Assignment3";
        String user = "postgres";
        String password = "admin";

        // variables
        boolean exit = false;
        int input, student_id;
        String first_name, last_name, email, enrollment_date, new_email;

        try {
            // Connect to db
            Class.forName("org.postgresql.Driver");
            connection = DriverManager.getConnection(url, user, password);

            // menu
            while (!exit){
                System.out.print("\nWelcome to Burger King may I take your order: \n1. Get all students\n2. Add student\n3. Update student email\n4. Delete student\n5. Quit\nEnter here: ");
                input = s.nextInt();
                switch (input) {
                    case 1:
                        getAllStudents();
                        break;
                    case 2:
                        s.nextLine();
                        System.out.print("\nEnter the first name: ");
                        first_name = s.nextLine();
                        System.out.print("\nEnter the last name: ");
                        last_name = s.nextLine();
                        System.out.print("\nEnter the email: ");
                        email = s.nextLine();
                        System.out.print("\nEnter the enrollment date: ");
                        enrollment_date = s.nextLine();
                        addStudent(first_name, last_name, email, enrollment_date);
                        break;
                    case 3:
                        s.nextLine();
                        System.out.print("\nEnter the student ID: ");
                        student_id = s.nextInt();
                        s.nextLine();
                        System.out.print("\nEnter the new email: ");
                        new_email = s.nextLine();
                        updateStudentEmail(student_id, new_email);
                        break;
                    case 4:
                        s.nextLine();
                        System.out.print("\nEnter the student ID you want to delete: ");
                        student_id = s.nextInt();
                        s.nextLine();
                        deleteStudent(student_id);
                        break;
                    case 5:
                        exit = true;
                        break;
                }// end switch
            }// end while

        }catch(Exception e){e.printStackTrace();}
        System.out.println("See ya.");
    }// end main

    // delete student_id
    private static void deleteStudent(int student_id){
        // Running "DELETE FROM students WHERE student_id = [userInput]"
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM students WHERE student_id = ?");
            preparedStatement.setInt(1, student_id);
            preparedStatement.executeUpdate();
        }catch(Exception e){
            e.printStackTrace();
            System.out.println("Could not delete student from database");
        }
        System.out.println("Successfully deleted student from database");
    }// end student_id

    // update email
    private static void updateStudentEmail(int student_id, String new_email){
        // Running "UPDATE students SET email = [userInput] WHERE student_id = [userInput]" SQL command
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("UPDATE students SET email = ? WHERE student_id = ?");
            preparedStatement.setString(1, new_email);
            preparedStatement.setInt(2, student_id);
            preparedStatement.executeUpdate();
        }catch (Exception e) {
            e.printStackTrace();
            System.out.println("Could not update email");
        }
        System.out.println("Email successfully updated.");
    }// end updateStudentEmail

    private static void addStudent(String first_name, String last_name, String email, String enrollment_date) {
        // Running "INSERT INTO students (headers) VALUES (user input)"
        try{
            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO students (first_name, last_name, email, enrollment_date) VALUES (?, ?, ?, ?)");
            // add input into prepared statement
            preparedStatement.setString(1, first_name);
            preparedStatement.setString(2, last_name);
            preparedStatement.setString(3, email);
            preparedStatement.setDate(4, Date.valueOf(enrollment_date));
            preparedStatement.executeUpdate();
        }catch(Exception e){
            e.printStackTrace();
            System.out.println("Could not add student to database!");}
        System.out.println("Student successfully added.");
    }// end addStudent

    private static void getAllStudents(){
        // Running "SELECT * FROM STUDENTS" statement to get all students
        try {
            Statement statement = connection.createStatement();
            statement.executeQuery("SELECT * FROM students");
            ResultSet resultSet = statement.getResultSet();
            // Headers
            System.out.printf("%-20s %-20s %-20s %-40s %-10s", "student_id", "first_name", "last_name", "email", "enrollment_date\n");
            while(resultSet.next()){
                // Formatting output nicely
                System.out.printf("%-20s %-20s %-20s %-40s %-10s", resultSet.getString("student_id"), resultSet.getString("first_name"), resultSet.getString("last_name"), resultSet.getString("email"), resultSet.getString("enrollment_date") + "\n");
            }// end while
        }catch(Exception e){}
    }// end getAllStudents
}// end class
