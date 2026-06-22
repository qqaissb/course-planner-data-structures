public class Course {
    //attributes
    private String id;
    private String title;
    private int cred;
    private int grade;
    private boolean isCompleted;
    private String[] pre;

    //constructor
    public Course(String id, String title, int cred, String[] pre) {
        this.id = id;
        this.title = title;
        this.cred = cred;
        this.pre = pre;
        this.grade = 0;
        this.isCompleted = false;
    }

    //getters

    //get the course id
    public String getId() {
        return id;
    }

    //get the course title
    public String getTitle() {
        return title;
    }

    //get the credit hours
    public int getCred() {
        return cred;
    }

    //get the course grade
    public int getGrade() {
        return grade;
    }

    //check if the course is completed or not
    public boolean isCompleted() {
        return isCompleted;
    }

    //get the prerequisites array
    public String[] getPre() {
        return pre;
    }

    //function to update course status after completion
    public void markCompleted(int grade) {
        //if the grade is outside the accepted range, reject it
        if (grade < 0 || grade > 100) {
            System.out.println("Error: Invalid grade. Must be between 0 and 100.");
            return;
        }

        //store the grade and mark the course as completed
        this.grade = grade;
        this.isCompleted = true;
        System.out.println(this.id + " marked as completed with grade: " + this.grade);
    }
}