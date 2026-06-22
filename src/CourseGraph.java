//feature 5
public class CourseGraph {
    //attributes
    private Course[] graph;
    private int size;
    private int capacity;

    //constructor
    public CourseGraph(int capacity) {
        this.capacity = capacity;
        this.graph = new Course[capacity];
        this.size = 0;
    }

    //functions

    //add course to the university master catalog
    public void addCourse(Course course) {
        //if the course is null or the id is null, reject it
        if (course == null || course.getId() == null) {
            System.out.println("Error: Invalid course.");
            return;
        }

        //if the catalog array is full, reject the course
        if (size >= capacity) {
            System.out.println("Error: Catalog is full.");
            return;
        }

        //add the course in the next empty index
        graph[size] = course;
        size++;
    }

    //find course in the catalog by its id
    public Course findCourse(String id) {
        //loop through all courses currently stored in the catalog
        for (int i = 0; i < size; i++) {
            //if the id matches, return the course object
            if (graph[i].getId().equals(id)) {
                return graph[i];
            }
        }

        //if no course was found, return null
        return null;
    }

    //check if the student can register for a target course
    public boolean canRegister(Course target) {
        //if the course is null, reject it
        if (target == null) {
            System.out.println("Error: Invalid course.");
            return false;
        }

        //if the course has no prerequisites, the student can register
        if (target.getPre() == null || target.getPre().length == 0) {
            return true;
        }

        //boolean variable to track if all prerequisites are completed
        boolean couldRegister = true;

        //string to store missing prerequisites
        String missingReqs = "";

        //loop through all prerequisites of the target course
        for (int i = 0; i < target.getPre().length; i++) {
            //get the required course id
            String requiredId = target.getPre()[i];

            //find the required course in the catalog
            Course reqCourse = findCourse(requiredId);

            //if prerequisite does not exist or is not completed, registration is not allowed
            if (reqCourse == null || !reqCourse.isCompleted()) {
                couldRegister = false;
                missingReqs += requiredId + " ";
            }
        }

        //if at least one prerequisite is missing, print the missing courses
        if (!couldRegister) {
            System.out.println("Error: Missing completed prerequisites for " + target.getId() + " -> " + missingReqs.trim());
        }

        //return the final registration result
        return couldRegister;
    }

    //print all future courses unlocked by a completed course
    public void printUnlockedCourses(Course completedCourse) {
        //if the course is null, reject it
        if (completedCourse == null) {
            System.out.println("Error: Invalid course.");
            return;
        }

        System.out.println("All Future Courses Dependent On " + completedCourse.getId());

        //visited array to avoid printing the same course again
        boolean[] visited = new boolean[size];

        //start searching for unlocked courses
        boolean found = exploreUnlocks(completedCourse, visited);

        //if no unlocked courses were found, print message
        if (!found) {
            System.out.println(" No future courses are dependent on " + completedCourse.getId() + ".");
        }
    }

    //recursive helper function to explore direct and indirect unlocked courses
    private boolean exploreUnlocks(Course targetCourse, boolean[] visited) {
        //boolean variable to track if any course was unlocked
        boolean foundAny = false;

        //loop through all courses in the catalog
        for (int i = 0; i < size; i++) {
            Course current = graph[i];

            //only check courses that have prerequisites and were not visited before
            if (current.getPre() != null && !visited[i]) {
                //loop through the prerequisites of the current course
                for (int j = 0; j < current.getPre().length; j++) {
                    //if the current course depends on the target course, it is unlocked
                    if (current.getPre()[j].equals(targetCourse.getId())) {
                        visited[i] = true;
                        System.out.println(" Unlocks: " + current.getId() + " : " + current.getTitle());
                        foundAny = true;

                        //recursively check what this newly unlocked course can unlock
                        boolean foundMore = exploreUnlocks(current, visited);

                        if (foundMore) {
                            foundAny = true;
                        }

                        break;
                    }
                }
            }
        }

        //return whether any unlocked course was found
        return foundAny;
    }

    //print all courses in the university master catalog
    public void printCatalog() {
        System.out.println("\n*** University Master Catalog ***");

        //loop through all courses in the catalog
        for (int i = 0; i < size; i++) {
            Course current = graph[i];

            //string to store the prerequisites output
            String reqs = "";

            //if the course has prerequisites, build the prerequisites string
            if (current.getPre() != null && current.getPre().length > 0) {
                reqs = "  (Needs: ";
                for (int j = 0; j < current.getPre().length; j++) {
                    reqs += current.getPre()[j] + " ";
                }
                reqs = reqs.trim() + ")";
            }

            //print course id, title, and prerequisites
            System.out.println("  " + current.getId() + " : " + current.getTitle() + reqs);
        }

        System.out.println("****************************************\n");
    }
}