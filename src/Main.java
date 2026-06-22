import java.util.Scanner;

public class Main{
    public static void main(String[] args) {
        //create the university course catalog
        CourseGraph spaceCourses = new CourseGraph(20);

        //create the student's personal course notebook
        Linked notebook = new Linked();

        //create the next semester queue
        Queue nextSemesterQueue = new Queue();

        //create the BST for completed course grades
        BST gradeTree = new BST();

        //add courses to the university master catalog
        spaceCourses.addCourse(new Course("AST101", "Introduction to Astronomy", 3, null));
        spaceCourses.addCourse(new Course("PHY101", "Astrophysics Physics I", 3, null));
        spaceCourses.addCourse(new Course("AST201", "Planetary Science", 3, new String[]{"AST101"}));
        spaceCourses.addCourse(new Course("PHY201", "Astrophysics Physics II", 3, new String[]{"PHY101"}));
        spaceCourses.addCourse(new Course("ENG201", "Orbital Mechanics", 3, new String[]{"PHY101"}));
        spaceCourses.addCourse(new Course("AST301", "Stellar Evolution", 3, new String[]{"AST201", "PHY201"}));
        spaceCourses.addCourse(new Course("ENG301", "Spacecraft Propulsion", 3, new String[]{"ENG201"}));
        spaceCourses.addCourse(new Course("AST401", "Cosmology & Dark Matter", 3, new String[]{"AST301"}));

        //create scanner for user input
        Scanner scanner = new Scanner(System.in);

        //boolean variable to keep the system running
        boolean running = true;

        //print the main menu for the first time
        System.out.println("Space HTU Course Planner");
        System.out.println("1. Feature 1: Manage Personal Notebook");
        System.out.println("2. Feature 3: Next Semester Queue");
        System.out.println("3. Feature 4: Course Grades & Analytics");
        System.out.println("4. Feature 5: Check Course Unlocks");
        System.out.println("5. Exit System");
        System.out.println("0. Show Menu Options");

        //main loop for the whole system
        while (running) {
            //ask the user for a main menu option
            System.out.print("Select an option (type '0' for menu): ");
            String choice = scanner.nextLine();

            //choose which feature to open based on user input
            switch (choice) {
                case "0":
                    //print the main menu options again
                    System.out.println("1. Feature 1: Manage Personal Notebook");
                    System.out.println("2. Feature 3: Next Semester Queue");
                    System.out.println("3. Feature 4: Course Grades");
                    System.out.println("4. Feature 5: Check Course Unlocks");
                    System.out.println("5. Exit System");
                    break;

                case "1":
                    //feature 1 menu for managing the personal notebook
                    System.out.println("Feature 1: Manage Personal Notebook");
                    System.out.println("A. Add a Course to Notebook");
                    System.out.println("B. Remove a Course");
                    System.out.println("C. View Personal Notebook");
                    System.out.println("D. View University Course Catalog");
                    System.out.print("Select action: ");

                    //read the user's feature 1 choice
                    String choice1 = scanner.nextLine().toUpperCase();

                    //add a course to the personal notebook
                    if (choice1.equals("A")) {
                        //ask for the course id
                        System.out.print("Enter Course ID to add: ");
                        String addId = scanner.nextLine().toUpperCase();

                        //search for the course in the university catalog
                        Course targetToAdd = spaceCourses.findCourse(addId);

                        //if the course does not exist, print error
                        if (targetToAdd == null) {
                            System.out.println("Error: Course " + addId + " does not exist in the catalog.");
                        } else {
                            //else add the course to the personal notebook
                            notebook.insertLast(targetToAdd);
                        }
                    } else if (choice1.equals("B")) {
                        //remove a course from the personal notebook

                        //ask for the course id to remove
                        System.out.print("Enter Course ID to remove: ");
                        String removeId = scanner.nextLine().toUpperCase();

                        //delete the course from the linked list by id
                        notebook.deleteById(removeId);
                    } else if (choice1.equals("C")) {
                        //print the personal notebook
                        notebook.print();
                    } else if (choice1.equals("D")) {
                        //print the university course catalog
                        spaceCourses.printCatalog();
                    } else {
                        //handle invalid feature 1 input
                        System.out.println("Invalid selection.");
                    }
                    break;

                case "2":
                    //feature 3 menu for next semester queue
                    System.out.println("Feature 3: Next Semester Queue");
                    System.out.println("A. Enqueue Course for Next Semester");
                    System.out.println("B. View Current Queue");
                    System.out.println("C. Process Registration (Confirm/Skip)");
                    System.out.print("Select action: ");

                    //read the user's feature 3 choice
                    String choice2 = scanner.nextLine().toUpperCase();

                    //enqueue course for next semester
                    if (choice2.equals("A")) {
                        //ask for the course id to queue
                        System.out.print("Enter Course ID to queue: ");
                        String queueId = scanner.nextLine().toUpperCase();

                        //search for the course in the university catalog
                        Course targetToQueue = spaceCourses.findCourse(queueId);

                        //if the course does not exist, print error
                        if (targetToQueue == null) {
                            System.out.println("Error: Course does not exist in catalog.");
                        } else if (targetToQueue.isCompleted()) {
                            //if the course is already completed, do not queue it
                            System.out.println("Error: You have already completed this course.");
                        } else if (spaceCourses.canRegister(targetToQueue)) {
                            //if prerequisites are completed, add the course to the queue
                            nextSemesterQueue.enqueue(targetToQueue);
                        }
                    } else if (choice2.equals("B")) {
                        //print the current queue
                        nextSemesterQueue.print();
                    } else if (choice2.equals("C")) {
                        //process the course at the front of the queue

                        //if the queue is empty, there is nothing to process
                        if (nextSemesterQueue.isEmpty()) {
                            System.out.println("Queue is empty. Nothing to process.");
                        } else {
                            //remove the first course from the queue
                            Course top = nextSemesterQueue.dequeue();

                            //ask the user to confirm or skip this course
                            System.out.print("Registering for " + top.getId() + ". Confirm or Skip? (C/S): ");
                            String processChoice = scanner.nextLine().toUpperCase();

                            //if user confirms, print confirmation message
                            if (processChoice.equals("C")) {
                                System.out.println("Confirmed! You are officially registered for " + top.getId());
                            } else {
                                //otherwise treat it as skipped
                                System.out.println("Skipped. " + top.getId() + " has been removed from the queue.");
                            }
                        }
                    } else {
                        //handle invalid feature 3 input
                        System.out.println("Invalid selection.");
                    }
                    break;

                case "3":
                    //feature 4 menu for course grades and BST analytics
                    System.out.println("Feature 4: Course Grades & Analytics");
                    System.out.println("A. Complete Course and Record Grade");
                    System.out.println("B. Print All Grades Sorted (In-Order)");
                    System.out.println("C. Find Highest Graded Course");
                    System.out.println("D. Find Lowest Graded Course");
                    System.out.println("E. Search Course by Specific Grade");
                    System.out.print("Select action: ");

                    //read the user's feature 4 choice
                    String choice3 = scanner.nextLine().toUpperCase();

                    //complete a course and record its grade
                    if (choice3.equals("A")) {
                        //ask for the course id to complete
                        System.out.print("Enter Course ID to mark completed: ");
                        String compId = scanner.nextLine().toUpperCase();

                        //search for the course in the university catalog
                        Course targetToComplete = spaceCourses.findCourse(compId);

                        //if the course does not exist, print error
                        if (targetToComplete == null) {
                            System.out.println("Error: Course does not exist in catalog.");
                        } else if (targetToComplete.isCompleted()) {
                            //if the course is already completed, reject it
                            System.out.println("Error: Course is already marked completed.");
                        } else if (spaceCourses.canRegister(targetToComplete)) {
                            //if prerequisites are completed, ask for final grade
                            System.out.print("Enter final grade (0-100): ");

                            //try to convert the entered grade from string to integer
                            try {
                                int grade = Integer.parseInt(scanner.nextLine());

                                //mark the course as completed
                                targetToComplete.markCompleted(grade);

                                //insert the completed course into the grade tree
                                gradeTree.insert(targetToComplete);
                            } catch (NumberFormatException e) {
                                //handle invalid grade input
                                System.out.println("Error: Invalid number format.");
                            }
                        }
                    } else if (choice3.equals("B")) {
                        //print completed courses sorted by grade
                        gradeTree.printSortedRecords();
                    } else if (choice3.equals("C")) {
                        //print the highest graded course
                        gradeTree.printHighestGrade();
                    } else if (choice3.equals("D")) {
                        //print the lowest graded course
                        gradeTree.printLowestGrade();
                    } else if (choice3.equals("E")) {
                        //search for courses with a specific grade
                        System.out.print("Enter grade to search for: ");

                        //try to convert the entered grade from string to integer
                        try {
                            int searchGrade = Integer.parseInt(scanner.nextLine());

                            //search the BST by grade
                            gradeTree.searchByGrade(searchGrade);
                        } catch (NumberFormatException e) {
                            //handle invalid search grade input
                            System.out.println("Error: Invalid integer format.");
                        }
                    } else {
                        //handle invalid feature 4 input
                        System.out.println("Invalid selection.");
                    }
                    break;

                case "4":
                    //feature 5 for checking what a course unlocks

                    //ask for the completed course id
                    System.out.print("Enter completed Course ID to view unlocks: ");
                    String unlockId = scanner.nextLine().toUpperCase();

                    //search for the course in the university catalog
                    Course targetToUnlock = spaceCourses.findCourse(unlockId);

                    //if the course does not exist, print error
                    if (targetToUnlock == null) {
                        System.out.println("Error: Course " + unlockId + " does not exist in the catalog.");
                    } else {
                        //print all future courses dependent on this course
                        spaceCourses.printUnlockedCourses(targetToUnlock);
                    }
                    break;

                case "5":
                    //exit the system
                    System.out.println("Exiting Planner. Goodbye.");
                    running = false;
                    break;

                default:
                    //handle invalid main menu input
                    System.out.println("Invalid main menu selection. Try again.");
                    break;
            }
        }

        //close the scanner
        scanner.close();
    }
}