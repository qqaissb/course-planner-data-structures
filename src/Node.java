public class Node {
    //attributes
    private Course course;
    private Node next;

    //constructor
    public Node(Course course) {
        this.course = course;
        this.next = null;
    }

    //getters and setters

    //get the course stored inside the node
    public Course getCourse() {
        return course;
    }

    //get the next node
    public Node getNext() {
        return next;
    }

    //set the next node
    public void setNext(Node next) {
        this.next = next;
    }
}