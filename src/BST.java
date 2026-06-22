//feature 4
public class BST {
    //attributes
    private NodeBST root;

    //constructor
    public BST() {
        root = null;
    }

    //functions

    //insert course in BST
    public void insert(Course course) {
        //if the course is null or the id is null, reject it
        if (course == null || course.getId() == null) {
            System.out.println("Error: Null or invalid course cant be inserted.");
            return;
        }

        //if course isnt completed then no grade to sort
        if (!course.isCompleted()) {
            System.out.println("Error: Course " + course.getId() + " is not completed. No grade to sort.");
            return;
        }

        //otherwise add the course
        NodeBST new_node = new NodeBST(course);

        //if there is no root, make the new node the root
        if (root == null) {
            root = new_node;
            System.out.println("Added " + course.getId() + " to the grade tree as root.");
            return;
        }

        //start traversal from the root
        NodeBST current = root;
        NodeBST parent = null;

        //keep looping until the correct empty position is found
        while (true) {
            parent = current;

            //left branch for lower grades
            if (course.getGrade() < current.getCourse().getGrade()) {
                current = current.getLeft();

                //if the left side is empty, insert the new node there
                if (current == null) {
                    parent.setLeft(new_node);
                    System.out.println("Added " + course.getId() + " to the grade tree.");
                    return;
                }
            }
            //right branch for higher OR EQUAL grades to handle duplicates
            else {
                current = current.getRight();

                //if the right side is empty, insert the new node there
                if (current == null) {
                    parent.setRight(new_node);
                    System.out.println("Added " + course.getId() + " to the grade tree.");
                    return;
                }
            }
        }
    }

    //boolean function to determine if the BST is empty or not
    public boolean isEmpty() {
        return root == null;
    }

    //print function to display the sorted course records
    public void printSortedRecords() {
        //if the tree is empty, print message and stop
        if (isEmpty()) {
            System.out.println("No completed course records found in the tree.");
            return;
        }

        System.out.println("*** Completed Courses Sorted By Grade (Lowest to Highest) ***");

        //call the recursive function starting from the root node
        inOrder(root);

        System.out.println("************************************************************");
    }

    //recursive function to traverse the BST in-order
    private void inOrder(NodeBST node) {
        //only process the node if it is not null
        if (node != null) {
            //traverse the left subtree for smaller grades
            inOrder(node.getLeft());

            //print the current node attributes
            System.out.println(" Grade: " + node.getCourse().getGrade() + " | " + node.getCourse().getId() + " : " + node.getCourse().getTitle());

            //traverse the right subtree for higher or duplicate grades
            inOrder(node.getRight());
        }
    }

    //finds the absolute rightmost node containing the maximum grade
//find and print all courses with the highest grade
    public void printHighestGrade() {
        //if the tree is empty, print message and stop
        if (root == null) {
            System.out.println("No completed courses found in records.");
            return;
        }
        //start from the root
        NodeBST current = root;

        //keep moving right until the highest grade node is found
        while (current.getRight() != null) {
            current = current.getRight();
        }

        //store the highest grade
        int highestGrade = current.getCourse().getGrade();

        //print all courses that have this highest grade
        System.out.println("*** Highest Graded Course(s) ***");
        searchByGrade(highestGrade);
    }

    //find and print all courses with the lowest grade
    public void printLowestGrade() {
        //if the tree is empty, print message and stop
        if (root == null) {
            System.out.println("No completed courses found in records.");
            return;
        }

        //start from the root
        NodeBST current = root;

        //keep moving left until the lowest grade node is found
        while (current.getLeft() != null) {
            current = current.getLeft();
        }

        //store the lowest grade
        int lowestGrade = current.getCourse().getGrade();

        //print all courses that have this lowest grade
        System.out.println("*** Lowest Graded Course(s) ***");
        searchByGrade(lowestGrade);
    }

    //search for all courses matching a specific grade
    public void searchByGrade(int targetGrade) {
        System.out.println("Searching for courses with grade: " + targetGrade);

        //call the recursive helper from the root
        boolean found = searchHelper(root, targetGrade);

        //if no matching course was found, print message
        if (!found) {
            System.out.println("No course found with a grade of " + targetGrade);
        }
    }

    //recursive helper to search the BST by grade
    private boolean searchHelper(NodeBST current, int targetGrade) {
        //if the current node is null, the grade was not found in this path
        if (current == null) {
            return false;
        }

        //boolean variable to track if the current node matches
        boolean foundHere = false;

        //if the current course grade matches the target grade, print it
        if (current.getCourse().getGrade() == targetGrade) {
            System.out.println(" -> Found: " + current.getCourse().getId() + " : " + current.getCourse().getTitle());
            foundHere = true;
        }

        //navigate left for smaller grades, right for larger or equal
        if (targetGrade < current.getCourse().getGrade()) {
            return searchHelper(current.getLeft(), targetGrade) || foundHere;
        } else {
            return searchHelper(current.getRight(), targetGrade) || foundHere;
        }
    }
}