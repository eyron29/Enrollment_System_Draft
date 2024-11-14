package enrollmentDemo;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    //default shared accounts
    static User admin = new User("admin", "admin123", "admin");
    static User student = new User("s", "s", "student");
    static User instructor = new User("i", "i", "instructor");

    //id generator for Student & Instuctor
    static int studentId = 2023100508;
    static int instructorId = 102;

    //ask the user to try again
    static String ans = "";

    //error handling: for user input
    static boolean isValidInput1 = false;

    //shared scanner object
    static Scanner input = new Scanner(System.in);

    public static void main(String[] agrs){

        //add primary users of system
        Login login = new Login();
        login.addUser(admin);
        login.addUser(student);
        login.addUser(instructor);

        String role = "";
        //ask user for username & password
        do{
            System.out.print("Enter username: ");
            String username = input.nextLine();

            System.out.print("Enter password: ");
            String password = input.nextLine();

            if(login.authenticate(username, password)){
                System.out.println("---------------------------------------");
                System.out.println("Welcome back!");
                role = login.getUserRole(username);
                break;
            }else{
                System.out.println("Invalid username or password, try again");
            }
        }while(true);

        //get user's role: for specific menu to show up
        do{
            if(role.equals("admin")){
                //call to admin menu
                showAdminMenu();
            }else if(role.equals("student")){
                //call to student menu
                showStudentMenu1();
            }else if(role.equals("instructor")) {
                //call to instructor menu
                showInstructorMenu1();
            }else{
                System.out.println("Invalid role detected.");
            }

            //ask user if he/she wants to log out
            System.out.print("Try again? (YES/NO): ");
            ans = input.nextLine();
        }while(ans.equalsIgnoreCase("yes"));

    }//main method: run java program

    //Admin: management of Department, Course, viewing list of Student, Instructor
    public static void showAdminMenu() {
        int choice = 0;

        do{
            //call to adminOptions
            showAdminOptions();
            //validate input
            while(!isValidInput1){
                try{
                    System.out.print("Enter Choice: ");
                    String inp  = input.nextLine();

                    if(inp.isEmpty()){
                        throw new InvalidInputException("Input is empty");
                    }

                    choice = Integer.parseInt(inp);

                    if(choice <= 0 || choice > 6){
                        System.out.println();
                        throw new InvalidInputException("Invalid choice");
                    }else{
                        isValidInput1 = true;
                    }

                }catch(InvalidInputException e){
                    System.out.println("Please select one of these option: [ 1 | 2 | 3 | 4 | 5 | 6 ]");
                    System.out.println("------------------------------------------------------------------");
                }catch(NumberFormatException e){
                    System.out.println();
                    System.out.println("Invalid input format: Enter a valid number");
                    System.out.println("Please select one of these option: [ 1 | 2 | 3 | 4 | 5 | 6 ]");
                    System.out.println("------------------------------------------------------------------");
                }
            }//validateInput

            isValidInput1 = false;

            switch (choice) {

                case 1: {
                    //Department management
                    departmentMenu();
                    break;
                }

                case 2: {
                    //Program management
                    programMenu();
                    break;
                }

                case 3: {
                    //Course management
                    courseMenu();
                    break;
                }

                case 4:{
                    //display all students
                    showStudentList();
                    break;
                }

                case 5:{
                    //display all instructors
                    showInstructorList();
                    break;
                }

                case 6:{
                    //exit to menu
                    break;
                }

                default: {
                    break;
                }
            }//end of switch statement

            System.out.print("Back to Main Menu? (YES/NO): ");
            ans = input.nextLine();
        }while(ans.equalsIgnoreCase("yes"));
    }

    //admin options
    public static void showAdminOptions(){
        System.out.println("---------------------------------------");
        System.out.println("MAIN MENU");
        System.out.println("---------------------------------------");
        System.out.println("[1] - Manage Departments");
        System.out.println("[2] - Manage Programs");
        System.out.println("[3] - Manage Courses");
        System.out.println("[4] - View All Students");
        System.out.println("[5] - View All Instructors");
        System.out.println("[6] - Exit");
        System.out.println("---------------------------------------");
    }//showAdminOptions

    //ArrayList is used to store list of Students
    static ArrayList<Student> studentList;

    public static void showStudentList(){
        //DepartmentDAOImpl depTemp = new DepartmentDAOImpl();
        //departmentList = depTemp.getDepartments();

        //retrieve list of students from the database
        StudentDAOImpl temp = new StudentDAOImpl();
        studentList = temp.getAllStudents();

        if(studentList.isEmpty()){
            //if record is empty
            System.out.println("Student List is empty...");
        }else{
            System.out.println("Student List:");
            System.out.println("--------------------------------------------");
            for(Student student: studentList){
                //use of toString method
                System.out.println(student);
            }
            System.out.println("--------------------------------------------");
        }
    }//studentList

    //menu for department
    public static void departmentMenu(){
        int choice = 0;

        do{
            System.out.println("---------------------------------------");
            System.out.println("DEPARTMENT MANAGEMENT");
            System.out.println("---------------------------------------");
            System.out.println("[1] - Add Department");
            System.out.println("[2] - View All Departments");
            System.out.println("[3] - Exit");
            System.out.println("---------------------------------------");

            //validate input
            while(!isValidInput1){
                try{
                    System.out.print("Enter Choice: ");
                    String inp  = input.nextLine();

                    if(inp.isEmpty()){
                        throw new InvalidInputException("Input is emtpy");
                    }

                    choice = Integer.parseInt(inp);

                    if(choice <= 0 || choice > 3){
                        System.out.println();
                        throw new InvalidInputException("Invalid choice");
                    }else{
                        isValidInput1 = true;
                    }

                }catch(InvalidInputException e){
                    System.out.println("Please select one of these option: [ 1 | 2 | 3 ]");
                    System.out.println("------------------------------------------------------------------");
                }catch(NumberFormatException e){
                    System.out.println();
                    System.out.println("Invalid input format: Enter a valid number");
                    System.out.println("Please select one of these option: [ 1 | 2 | 3 ]");
                    System.out.println("------------------------------------------------------------------");
                }
            }
            isValidInput1 = false;

            switch (choice){
                case 1: {
                    //add department
                    addDepartment();
                    break;
                }
                case 2: {
                    //view all departments
                    getDepartments();
                    break;
                }
                case 3: {
                    //exit menu
                    break;
                }
                default:{
                    break;
                }
            }//end of switch statement

            System.out.print("Back to department menu? (YES/NO): ");
            ans = input.nextLine();
        }while(ans.equals("yes"));
    }//departmentMenu

    //addition of department
    public static void addDepartment(){
        System.out.println("---------------------------------------");
        System.out.println("ADD DEPARTMENT");
        System.out.println("---------------------------------------");

        System.out.print("Enter Department Code: ");
        String deptCode = input.nextLine();
        //convert user input as uppercase
        deptCode = deptCode.toUpperCase();

        System.out.print("Enter Department Name: ");
        String depName = input.nextLine();

        //call to department constructor: with provided arguments
        Department department = new Department(deptCode, depName);
        //add the department to the database
        DepartmentDAOImpl temp = new DepartmentDAOImpl();
        temp.addDepartment(department);

    }//add Department

    //ArrayList used to store list of department
    static ArrayList<Department> departmentList;

    public static void getDepartments(){
        //retrieve department list from the database
        DepartmentDAOImpl temp = new DepartmentDAOImpl();
        departmentList = temp.getDepartments();

        if(departmentList.isEmpty()){
            //if no record is found
            System.out.println("Department List is empty...");
        }else{
            System.out.println("Department List:");
            System.out.println("-----------------------------------");
            for(Department department: departmentList){
                //use of toString method
                System.out.println(department);
            }
        }
    }//show list of all Department

    public static void programMenu(){
        int choice = 0;
        do{
            System.out.println("---------------------------------------");
            System.out.println("PROGRAM MANAGEMENT");
            System.out.println("---------------------------------------");
            System.out.println("[1] - Add Program");
            System.out.println("[2] - View All Programs");
            System.out.println("[3] - Exit");
            System.out.println("---------------------------------------");

            //validate input
            while(!isValidInput1){
                try{
                    System.out.print("Enter Choice: ");
                    String inp  = input.nextLine();

                    if(inp.isEmpty()){
                        throw new InvalidInputException("Input is empty");
                    }

                    choice = Integer.parseInt(inp);

                    if(choice <= 0 || choice > 4){
                        System.out.println();
                        throw new InvalidInputException("Invalid choice");
                    }else{
                        isValidInput1 = true;
                    }

                }catch(InvalidInputException e){
                    System.out.println("Please select one of these option: [ 1 | 2 | 3 ]");
                    System.out.println("------------------------------------------------------------------");
                }catch(NumberFormatException e){
                    System.out.println();
                    System.out.println("Invalid input format: Enter a valid number");
                    System.out.println("Please select one of these option: [ 1 | 2 | 3 ]");
                    System.out.println("------------------------------------------------------------------");
                }
            }

            isValidInput1 = false;

            switch (choice){
                case 1: {
                    addProgram();
                    break;
                }

                case 2: {
                    getPrograms();
                    break;
                }

                case 3: {
                    //exit menu
                    break;
                }

                default:{
                    break;
                }
            }
            System.out.print("Back to Program Menu? (YES/NO): ");
            ans = input.nextLine();
        }while(ans.equals("yes"));
    }//programMenu

    //admin: add Program
    public static void addProgram(){
        System.out.println("---------------------------------------");
        System.out.println("ADD PROGRAM");
        System.out.println("--------------------------------------");

        System.out.print("Enter Program Code: ");
        String programCode = input.nextLine();
        programCode = programCode.toUpperCase();

        System.out.print("Enter Program Name: ");
        String programName = input.nextLine();

        //use constructor of Program class: with provided arguments
        Program program = new Program(programCode, programName);
        //instantiate an object from ProgramDAOImpl class
        ProgramDAOImpl temp = new ProgramDAOImpl();
        //add the program to the database
        temp.addProgram(program);
    }//add Program

    //ArrayList used to store list of programs
    static ArrayList<Program> programList;

    public static void getPrograms(){
        ProgramDAOImpl temp = new ProgramDAOImpl();
        programList = temp.getPrograms();

        if(programList.isEmpty()){
            //if no record is found
            System.out.println("Program list is empty...");
        }else{
            System.out.println("Program List:");
            System.out.println("-------------------------------------");
            for(Program program: programList){
                //use of toString method
                System.out.println(program);
            }
        }
    }//get list of programs

    //course management menu
    public static void courseMenu(){
        int choice = 0;
        do{
            System.out.println("---------------------------------------");
            System.out.println("COURSE MANAGEMENT");
            System.out.println("---------------------------------------");
            System.out.println("[1] - Add Course");
            System.out.println("[2] - Remove Course");
            System.out.println("[3] - View All Courses");
            System.out.println("[4] - Exit");
            System.out.println("---------------------------------------");

            //validate input
            while(!isValidInput1){
                try{
                    System.out.print("Enter Choice: ");
                    String inp  = input.nextLine();

                    if(inp.isEmpty()){
                        throw new InvalidInputException("Input is empty");
                    }

                    choice = Integer.parseInt(inp);

                    if(choice <= 0 || choice > 4){
                        System.out.println();
                        throw new InvalidInputException("Invalid choice");
                    }else{
                        isValidInput1 = true;
                    }

                }catch(InvalidInputException e){
                    System.out.println("Please select one of these option: [ 1 | 2 | 3 | 4 ]");
                    System.out.println("------------------------------------------------------------------");
                }catch(NumberFormatException e){
                    System.out.println();
                    System.out.println("Invalid input format: Enter a valid number");
                    System.out.println("Please select one of these option: [ 1 | 2 | 3 | 4 ]");
                    System.out.println("------------------------------------------------------------------");
                }
            }
            isValidInput1 = false;

            switch (choice){

                case 1: {
                    addCourse();
                    break;
                }

                case 2: {
                    break;
                }

                case 3: {
                    viewCourses();
                    break;
                }

                case 4: {
                    break;
                }
                default:{
                    break;
                }
            }
            System.out.print("Back to Course Menu? (YES/NO): ");
            ans = input.nextLine();
        }while(ans.equals("yes"));
    }//course Menu

    //ArrayList used to store list of instructors
    static ArrayList<Instructor> instructorList;

    public static void showInstructorList(){
        //retrieve list of all instructors from the database
        InstructorDAOImpl temp =  new InstructorDAOImpl();
        instructorList = temp.getAllInstructors();

        if(instructorList.isEmpty()){
            //if no record is found
            System.out.println("Faculty list is empty...");
        }else{
            System.out.println("Faculty List: ");
            System.out.println("--------------------------------------------");
            for(Instructor instructor: instructorList){
                System.out.println(instructor);
            }
            System.out.println("--------------------------------------------");
        }
    }//get list of intructors

    //addition of new course
    public static void addCourse(){
        System.out.println("---------------------------------------");
        System.out.println("ADD COURSE");
        System.out.println("---------------------------------------");

        System.out.print("Enter Course Code: ");
        String courseCode = input.nextLine();
        courseCode = courseCode.toUpperCase();

        System.out.print("Enter Course Name: ");
        String courseName = input.nextLine();

        System.out.print("Enter Credits: ");
        int credits = input.nextInt();
        input.nextLine();

        //ask user if an instructor will be assigned
        System.out.println("Do you want to assign instructor? (YES/NO): ");
        ans = input.nextLine();

        //instantiate an CourseDAOImpl object
        CourseDAOImpl temp = new CourseDAOImpl();
        if(ans.equalsIgnoreCase("yes")){
            showInstructorList();
            System.out.println("----------------------------");
            System.out.print("Enter Instructor to Assign (InstructorId) : ");
            int instructorId = input.nextInt();
            input.nextLine();

            InstructorDAOImpl temp1 = new InstructorDAOImpl();
            //retrieve the specific instructor from database
            Instructor instructor = temp1.getInstructorById(instructorId);
            //add a course with an instructor assigned
            Course course = new Course(courseCode,courseName, credits, instructor);
            temp.addCourse(course);
        }else{
            //the Course instructor will be null
            Course course = new Course(courseCode, courseName, credits);
            //add a course without an instructor
            temp.addCourse(course);
        }
    }//add Course

    //ArrayList to store all courses
    static ArrayList<Course> offeredCourses = new ArrayList<>();

    public static void viewCourses(){
        CourseDAOImpl temp = new CourseDAOImpl();
        //retrieve all courses from database
        offeredCourses = temp.getOfferedCourses();
        System.out.println("----------------------------------------------");
        System.out.println("COURSE CODE   |   COURSE NAME");
        for(Course course : offeredCourses){
            System.out.println(course);
        }
        System.out.println("----------------------------------------------");

    }//viewCourses

    //menu #1 for  Student
    public static void showStudentMenu1(){
        System.out.println("---------------------------------------");
        System.out.println("STUDENT OPTIONS");
        System.out.println("---------------------------------------");
        System.out.println("[1] - Create New Profile");
        System.out.println("[2] - Manage Profile");
        System.out.println("[3] - Exit");
        System.out.println("---------------------------------------");

        int choice = 0;
        //validate input
        while(!isValidInput1){
            try{
                System.out.print("Enter Choice: ");
                String inp  = input.nextLine();

                if(inp.isEmpty()){
                    throw new InvalidInputException("Input is empty");
                }

                choice = Integer.parseInt(inp);

                if(choice <= 0 || choice > 3){
                    System.out.println();
                    throw new InvalidInputException("Invalid choice");
                }else{
                    isValidInput1 = true;
                }

            }catch(InvalidInputException e){
                System.out.println("Please select one of these option: [ 1 | 2 | 3 | 4 | 5 | 6 ]");
                System.out.println("------------------------------------------------------------------");
            }catch(NumberFormatException e){
                System.out.println();
                System.out.println("Invalid input format: Enter a valid number");
                System.out.println("Please select one of these option: [ 1 | 2 | 3 | 4 | 5 | 6 ]");
                System.out.println("------------------------------------------------------------------");
            }
        }
        isValidInput1 = false;

        switch(choice){
            case 1: {
                registerStudent();
                break;
            }
            case 2: {
                manageProfile();
                //showStudentMenu2();
                break;
            }
            case 3: {
                break;
            }
        }//end of switch statemnt
    }//STUDENT: MENU #1

    //menu #2 for Student
    public static void showStudentMenu2(){
        int choice = 0;

        do{
            System.out.println("---------------------------------------");
            System.out.println("STUDENT MENU");
            System.out.println("---------------------------------------");
            System.out.println("[1] - View Profile");
            System.out.println("[3] - Update Profile");
            System.out.println("[3] - View Courses");
            System.out.println("[4] - Enroll Courses");
            System.out.println("[5] - Subjects Enrolled");
            System.out.println("[6] - Exit");
            System.out.println("---------------------------------------");

            //validate input
            while(!isValidInput1){
                try{
                    System.out.print("Enter Choice: ");
                    String inp  = input.nextLine();

                    if(inp.isEmpty()){
                        throw new InvalidInputException("Input is empty");
                    }

                    choice = Integer.parseInt(inp);

                    if(choice <= 0 || choice > 6){
                        System.out.println();
                        throw new InvalidInputException("Invalid choice");
                    }else{
                        isValidInput1 = true;
                    }

                }catch(InvalidInputException e){
                    System.out.println("Please select one of these option: [ 1 | 2 | 3 | 4 | 5 | 6 ]");
                    System.out.println("------------------------------------------------------------------");
                }catch(NumberFormatException e){
                    System.out.println();
                    System.out.println("Invalid input format: Enter a valid number");
                    System.out.println("Please select one of these option: [ 1 | 2 | 3 | 4 | 5 | 6 ]");
                    System.out.println("------------------------------------------------------------------");
                }
            }
            isValidInput1 = false;

            switch (choice){

                case 1: {
                    viewProfile();
                    break;
                }
                case 2: {
                    updateProfile();
                    break;
                }
                case 3: {
                    viewCourses();
                    break;
                }
                case 4:{
                    enrollCourses();
                    break;
                }

                case 5:{
                    viewEnrolledCourses();
                    break;
                }

                case 6:{
                    //exit menu
                    break;
                }

                default:{
                    break;
                }
            }//end of switch statement

            System.out.println("----------------------------------------------");
            System.out.print("Back to Student Menu? (YES/NO): ");
            ans = input.nextLine();
        }while(ans.equalsIgnoreCase("yes"));
    }//showStudentMenu2

    //registration for student
    public static void registerStudent() {
        System.out.println("--------------------------------------");
        System.out.println("STUDENT REGISTRATION");
        System.out.println("--------------------------------------");
        System.out.print("Enter Last Name: ");
        String lName = input.nextLine();

        System.out.print("Enter First name: ");
        String fName = input.nextLine();

        System.out.print("Enter Contact No: ");
        String contactNo = input.nextLine();

        System.out.print("Enter Email: ");
        String email = input.nextLine();

        System.out.print("Enter year level: ");
        String yearLevel = input.nextLine();

        System.out.println("ENTER YOUR DATE OF BIRTH:");
        System.out.print("Day: ");
        int day = 0;

        System.out.print("Month: ");
        int month = input.nextInt();

        System.out.print("Year: ");
        int year = input.nextInt();
        input.nextLine();
        String dateOfBirth = day + "/" + month + "/" + year;

        //RETRIEVE: Programs from the database, so we can pass Program as an argument in the Student's constructor
        getPrograms();

        System.out.print("Enter Academic Program (CODE): ");
        String programCode = input.nextLine();

        ProgramDAOImpl temp = new ProgramDAOImpl();
        Program major = temp.getProgramByCode(programCode);

        if(major != null){
            Student student = new Student(studentId, lName, fName, contactNo, email, dateOfBirth, yearLevel, major);
            StudentDAO enrollment = new StudentDAOImpl();
            enrollment.addStudent(student);

            System.out.println("Successfully registered...");
            System.out.println("--------------------------------------------------------");
            System.out.println("PLEASE TAKE NOTE OF YOUR STUDENT NO. : " + studentId);
            System.out.println("--------------------------------------------------------");
            studentId++;
        }else{
            System.out.println("Program not found, please try again");
        }
    }//registration process for: STUDENT

    //instantiate a Student object
    static Student studentObj = new Student();

    public static void manageProfile(){
        System.out.println("---------------------------------");
        System.out.print("Enter Student ID: ");
        int studId = input.nextInt();
        input.nextLine();

        StudentDAOImpl temp = new StudentDAOImpl();
        studentObj = temp.getStudentById(studId);

        if(studentObj == null){
            System.out.println("Record not found, please try again");
            System.out.println("-------------------------------------");
        }else{
            //call to STUDENT MENU # 2 if the record is found
            showStudentMenu2();
        }
    }

    public static void viewProfile(){
        //use of abstract method: printDetails
        studentObj.printDetails1();
    }//view Profile

    public static void updateProfile(){
        studentId = studentObj.getId();

        StudentDAOImpl temp = new StudentDAOImpl();
        Student student = temp.getStudentById(studentId);
        student.printDetails1();

        System.out.print("Enter column to update: ");
        String column = input.nextLine();

        if(column.equalsIgnoreCase("Last Name")){
            System.out.print("Enter New Value: ");
            String newLName = input.nextLine();
            student.setLastName(newLName);
            temp.updateStudent(student);
            System.out.println("--------------------------------");
            System.out.println("Successfully updated...");
        }else if(column.equalsIgnoreCase("First Name")){
            System.out.print("Enter New Value: ");
            String newFName = input.nextLine();
            student.setFirstName(newFName);
            temp.updateStudent(student);
            System.out.println("Successfully updated...");
        } else{
            System.out.println("Invalid column...");
        }
    }//update specific column: STUDENT

    public static void enrollCourses(){
        //view list of offered courses
        viewCourses();

        //no option to select courses not to enroll
        System.out.print("Enroll All Courses (YES/NO)? ");
        ans = input.nextLine();
        if(ans.equalsIgnoreCase("yes")){
            for(Course course : offeredCourses){
                //instantiate an enrollment object then pass studentObj and each course as arguments
                Enrollment enrollment = new Enrollment(studentObj, course);
                //access Enrollment method to add the course to Student's courses(ArrayList)
                enrollment.enroll();
            }
        }else{
            System.out.println("Please try again...");
        }
        System.out.println("Enrolled successfully...");
    }//enrollCourses

    public static void viewEnrolledCourses(){
        System.out.println("-----------------------------------------");
        System.out.println("CERTIFICATE OF REGISTRATION");
        System.out.println("-----------------------------------------");
        studentObj.printDetails1();
        studentObj.printList();
    }

    //menu #1 for instructor
    public static void showInstructorMenu1(){
        System.out.println("---------------------------------------");
        System.out.println("INSTRUCTOR OPTIONS");
        System.out.println("---------------------------------------");
        System.out.println("[1] - Create New Profile");
        System.out.println("[2] - Manage Profile");
        System.out.println("[3] - Exit");
        System.out.println("---------------------------------------");

        int choice = 0;
        //validate input
        while(!isValidInput1){
            try{
                System.out.print("Enter Choice: ");
                String inp  = input.nextLine();

                if(inp.isEmpty()){
                    throw new InvalidInputException("Input is empty");
                }

                choice = Integer.parseInt(inp);

                if(choice <= 0 || choice > 3){
                    System.out.println();
                    throw new InvalidInputException("Invalid choice");
                }else{
                    isValidInput1 = true;
                }

            }catch(InvalidInputException e){
                System.out.println("Please select one of these option: [ 1 | 2 | 3 ]");
                System.out.println("------------------------------------------------------------------");
            }catch(NumberFormatException e){
                System.out.println();
                System.out.println("Invalid input format: Enter a valid number");
                System.out.println("Please select one of these option: [ 1 | 2 | 3 ]");
                System.out.println("------------------------------------------------------------------");
            }
        }
        isValidInput1 = false;

        switch(choice){
            case 1: {
                registerInstructor();
                break;
            }
            case 2: {
                manageInsProfile();
                //showStudentMenu2();
                break;
            }
            case 3: {
                break;
            }
        }//end of switch statement
    }//STUDENT: MENU #1

    //menu #2 for instructor
    public static void showInstructorMenu2(){
        do{
            System.out.println("---------------------------------------");
            System.out.println("INSTRUCTOR MENU");
            System.out.println("---------------------------------------");
            System.out.println("[1] - View Profile");
            System.out.println("[2] - Exit");
            System.out.println("---------------------------------------");

            int choice = 0;
            //validate input
            while(!isValidInput1){
                try{
                    System.out.print("Enter Choice: ");
                    String inp  = input.nextLine();

                    if(inp.isEmpty()){
                        throw new InvalidInputException("Input is empty");
                    }

                    choice = Integer.parseInt(inp);

                    if(choice <= 0 || choice > 2){
                        System.out.println();
                        throw new InvalidInputException("Invalid choice");
                    }else{
                        isValidInput1 = true;
                    }

                }catch(InvalidInputException e){
                    System.out.println("Please select one of these option: [ 1 | 2 ]");
                    System.out.println("------------------------------------------------------------------");
                }catch(NumberFormatException e){
                    System.out.println();
                    System.out.println("Invalid input format: Enter a valid number");
                    System.out.println("Please select one of these option: [ 1 | 2 ]");
                    System.out.println("------------------------------------------------------------------");
                }
            }
            isValidInput1 = false;

            switch (choice){

                case 1: {
                    //view details
                    viewInsProfile();
                    break;
                }

                case 2:{
                    //exit menu
                    break;
                }

                default:{
                    break;
                }
            }

            System.out.println("----------------------------------------------");
            System.out.print("Back to Instructor Menu? (YES/NO): ");
            ans = input.nextLine();
        }while(ans.equalsIgnoreCase("yes"));
    }//showStudentMenu

    //Instructor: Menu: register, view, assignCourses
    public static void registerInstructor(){
        System.out.println("--------------------------------------");
        System.out.println("INSTRUCTOR REGISTRATION");
        System.out.println("--------------------------------------");
        System.out.print("Enter Last Name: ");
        String lName = input.nextLine();

        System.out.print("Enter First Name: ");
        String fName = input.nextLine();

        System.out.print("Enter Contact No: ");
        String contactNo = input.nextLine();

        System.out.print("Enter Email: ");
        String email = input.nextLine();

        System.out.println("--------------------------");
        System.out.println("ENTER YOUR HIRE DATE");
        System.out.println("--------------------------");
        System.out.print("Day: ");
        int day = input.nextInt();

        System.out.print("Month: ");
        int month = input.nextInt();

        System.out.print("Year: ");
        int year = input.nextInt();
        input.nextLine();
        String date = day + "/" + month + "/" + year;

        //retrieve list of departments
        getDepartments();
        System.out.print("ENTER Department (CODE): ");
        String deptCode = input.nextLine();

        //RETRIEVE: from database first,then pass department to the instructor's constructor
        DepartmentDAOImpl temp = new DepartmentDAOImpl();
        Department department = temp.getByDeptCode(deptCode);

        if (department != null) {
            Instructor instructor = new Instructor(instructorId, lName, fName, contactNo, email, date, department);
            InstructorDAOImpl temp1 = new InstructorDAOImpl();
            temp1.addInstructor(instructor);
        }else{
            System.out.println("Department not found.");
        }
        instructorId++;
    }//registerInstructor

    static Instructor instructorObj = new Instructor();

    //profile management for instructor
    public static void manageInsProfile(){
        System.out.println("---------------------------------");
        System.out.print("Enter Instructor ID: ");
        int insId = input.nextInt();
        input.nextLine();

        InstructorDAOImpl temp = new InstructorDAOImpl();
        instructorObj = temp.getInstructorById(insId);

        if(instructorObj == null){
            System.out.println("Record not found, please try again");
            System.out.println("-------------------------------------");
        }else{
            //call to INSTRUCTOR MENU # 2 if the record is
            showInstructorMenu2();
        }
    }

    public static void viewInsProfile(){
        //use of abstract method: printDetails
        instructorObj.printDetails();
    }//view Profile

}//class: END OF SOURCE CODE