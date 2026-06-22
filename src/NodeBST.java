//feature 4
public class NodeBST {
    //attributes
    private Course course;
    private NodeBST left;
    private NodeBST right;

    //constructor with course parameter as the node data
    public NodeBST(Course course) {
        this.course = course;
        this.left = null;
        this.right = null;
    }

    //getters and setters

    //get the course stored inside the node
    public Course getCourse() {
        return course;
    }

    //get the left child node
    public NodeBST getLeft() {
        return left;
    }

    //set the left child node
    public void setLeft(NodeBST left) {
        this.left = left;
    }

    //get the right child node
    public NodeBST getRight() {
        return right;
    }

    //set the right child node
    public void setRight(NodeBST right) {
        this.right = right;
    }
}