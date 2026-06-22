//feature 3
public class Queue {
    //attributes
    private Node front;
    private Node rear;
    private int size;

    //constructor
    public Queue() {
        front = null;
        rear = null;
        size = 0;
    }

    //functions

    //enqueue function for adding courses
    public void enqueue(Course course) {
        //if the course is null or the id is null, reject it
        if (course == null || course.getId() == null) {
            System.out.println("Error: Null or invalid course cant be enqueued.");
            return;
        }

        //if the course already exists in the queue, reject it
        if (contains(course.getId())) {
            System.out.println("Error: " + course.getId() + " is already in the next semester queue.");
            return;
        }

        //make a new node
        Node new_node = new Node(course);

        //if the queue is empty make the front the new node
        if (isEmpty()) {
            front = new_node;
        } else {
            //else the node after the rear would be the new node
            rear.setNext(new_node);
        }

        //rear is always updated to the new node
        rear = new_node;
        size++;
        System.out.println("Enqueued " + course.getId() + " for next semester.");
    }

    //dequeue function for removing nodes, with Course datatype to return
    public Course dequeue() {
        //if it was empty return
        if (isEmpty()) {
            System.out.println("Error: Next semester queue is empty.");
            return null;
        }

        //store the course at the front
        Course targetCourse = front.getCourse();

        //move front to the next node
        front = front.getNext();

        //if the queue is now empty, reset rear as well
        if (front == null) {
            rear = null;
        }

        //decrease the size and return the removed course
        size--;
        return targetCourse;
    }

    //check if a course already exists in the queue by id
    public boolean contains(String id) {
        //start from the front node
        Node current = front;

        //loop through the queue
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

    //print function to display all queued courses in order
    public void print() {
        //if the queue is empty, print message and stop
        if (isEmpty()) {
            System.out.println("No courses lined up for next semester.");
            return;
        }

        System.out.println("Next Semester Course Queue");

        //start from the front node
        Node current = front;

        //position counter for display
        int position = 1;

        //loop through all nodes in the queue
        while (current != null) {
            System.out.println(" Course " + position + ": " + current.getCourse().getId() + " : " + current.getCourse().getTitle());

            //move to the next node
            current = current.getNext();

            //increase position number
            position++;
        }
    }

    //boolean function to determine if the queue is empty or not
    public boolean isEmpty() {
        return front == null;
    }

    //return the number of courses in the queue
    public int size() {
        return size;
    }
}