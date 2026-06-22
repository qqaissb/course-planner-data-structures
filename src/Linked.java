//feature 1
public class Linked {
    //attributes
    private Node head;
    private int size;

    //constructor
    public Linked() {
        head = null;
        size = 0;
    }

    //functions

    //check if a course already exists in the notebook by id
    public boolean contains(String id) {
        //start from the head node
        Node current = head;

        //loop through the linked list
        while (current != null) {
            //if the course id matches, return true
            if (current.getCourse().getId().equals(id)) {
                return true;
            }

            //move to the next node
            current = current.getNext();
        }

        //if no matching course was found, return false
        return false;
    }

    //insert course at the end of the linked list
    public void insertLast(Course course) {
        //if the course is null or the id is null, reject it
        if (course == null || course.getId() == null) {
            System.out.println("Error: Null or invalid course cant be inserted.");
            return;
        }

        //if the course already exists in the notebook, reject it
        if (contains(course.getId())) {
            System.out.println("Error: " + course.getId() + " is already in your notebook.");
            return;
        }

        //make a new node
        Node new_node = new Node(course);

        //if the linked list is empty, make the new node the head
        if (isEmpty()) {
            head = new_node;
        } else {
            //else traverse until the last node
            Node current = head;
            while (current.getNext() != null) {
                current = current.getNext();
            }

            //connect the last node to the new node
            current.setNext(new_node);
        }
        System.out.println(course.getId() + " successfully added to your notebook.");
        //increase the size
        size++;
    }

    //delete course from the linked list by id
    public void deleteById(String id) {
        //if the list is empty or the id is null, reject it
        if (isEmpty() || id == null) {
            System.out.println("Notebook is empty or invalid entry.");
            return;
        }

        //if the course is in the head node, remove the head
        if (head.getCourse().getId().equals(id)) {
            head = head.getNext();
            size--;
            System.out.println("Removed " + id + " from your notebook.");
            return;
        }

        //start with previous as head and current as the node after head
        Node prev = head;
        Node current = head.getNext();

        //loop through the linked list
        while (current != null) {
            //if the current node has the needed course id, remove it
            if (current.getCourse().getId().equals(id)) {
                prev.setNext(current.getNext());
                size--;
                System.out.println("Removed " + id + " from your notebook.");
                return;
            }

            //move both pointers forward
            prev = current;
            current = current.getNext();
        }

        //if the course was not found, print error
        System.out.println("Error: Course " + id + " not found in your notebook.");
    }

    //print all courses in the personal notebook
    public void print() {
        //if the notebook is empty, print message and stop
        if (isEmpty()) {
            System.out.print("Your personal course notebook is empty.\n");
            return;
        }

        System.out.println("*** Personal Course Notebook ***");

        //start from the head node
        Node current = head;

        //loop through all nodes in the notebook
        while (current != null) {
            //get the course stored inside the current node
            Course c = current.getCourse();

            //string to store prerequisites
            String prereqs = "";

            //if there are no prerequisites, print None
            if (c.getPre() == null || c.getPre().length == 0) {
                prereqs = "None";
            } else {
                //build prerequisites string
                for (int i = 0; i < c.getPre().length; i++) {
                    prereqs += c.getPre()[i];
                    if (i < c.getPre().length - 1) {
                        prereqs += ", ";
                    }
                }
            }

            //string to store completion status
            String maybeCompleted;

            //check if the course is completed or not
            if (c.isCompleted()) {
                maybeCompleted = "Completed";
            } else {
                maybeCompleted = "Not Completed";
            }

            //print course information
            System.out.println("--------------------------------------------------");
            System.out.println("Course ID    : " + c.getId());
            System.out.println("Title        : " + c.getTitle());
            System.out.println("Credit Hours : " + c.getCred());
            System.out.println("Prerequisites: " + prereqs);
            System.out.println("Status       : " + maybeCompleted);

            //if the course is completed, print the grade
            if (c.isCompleted()) {
                System.out.println("Grade        : " + c.getGrade());
            }

            //move to the next node
            current = current.getNext();
        }

        System.out.println("--------------------------------------------------\n");
    }

    //boolean function to determine if the linked list is empty or not
    public boolean isEmpty() {
        return head == null;
    }

    //return the number of courses in the notebook
    public int size() {
        return size;
    }
}