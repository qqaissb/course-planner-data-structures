# Course Planner Data Structures

A space-themed Java console application that demonstrates custom implementations of core data structures and algorithms through a student degree-planning system.

The project was originally developed as a university assignment and is presented here as a portfolio project focused on data-structure design, object-oriented programming, and algorithmic problem-solving.

## Project Overview

The application allows a student to manage a personal course plan, organize next-semester registration, record completed-course grades, and explore prerequisite relationships between astronomy and engineering courses.

All main data structures were implemented from scratch rather than using Java's built-in collection classes.

## Core Features

- **Personal course notebook:** add, remove, and browse courses using a custom singly linked list.
- **Next-semester planning:** enqueue and process courses in FIFO order using a custom queue.
- **Grade records:** store completed courses in a binary search tree ordered by grade.
- **Grade analysis:** print grades in sorted order, search by grade, and find the highest- or lowest-graded course.
- **Prerequisite validation:** prevent registration when required courses are incomplete.
- **Course unlock exploration:** recursively traverse direct and indirect prerequisite relationships using depth-first search.
- **Input handling:** validate course IDs, duplicate entries, prerequisites, grades, and menu selections.

## Data Structures and Algorithms

| Component | Implementation | Purpose |
|---|---|---|
| Linked list | Custom singly linked list | Stores the student's personal course notebook |
| Queue | Custom linked FIFO queue | Organizes next-semester registration priorities |
| Binary search tree | Grade-keyed custom BST | Stores and searches completed courses |
| Directed prerequisite graph | Array-backed course catalog with prerequisite edges | Represents course dependencies |
| Depth-first search | Recursive traversal with a visited array | Finds direct and indirect course unlocks |
| In-order traversal | Recursive BST traversal | Prints completed courses from lowest to highest grade |

Courses with equal grades are retained in the BST by inserting equal values into the right subtree. Grade searches continue through that branch so every matching course can be displayed.

## Example Course Domain

The sample catalog uses a space and astronomy theme, including courses such as:

- Introduction to Astronomy
- Planetary Science
- Stellar Evolution
- Orbital Mechanics
- Spacecraft Propulsion
- Cosmology and Dark Matter

The theme provides a clear prerequisite chain while keeping the main focus on the underlying data structures.

## Project Structure

```text
course-planner-data-structures/
├── docs/
│   └── class-diagram.puml
├── src/
│   ├── BST.java
│   ├── Course.java
│   ├── CourseGraph.java
│   ├── Linked.java
│   ├── Main.java
│   ├── Node.java
│   ├── NodeBST.java
│   └── Queue.java
├── .gitignore
└── README.md
```

## Running the Application

### Requirements

- Java Development Kit installed
- `javac` and `java` available from the command line

### Windows CMD

From the repository root:

```cmd
javac -d out src\*.java
java -cp out Main
```

The program then displays an interactive console menu.

## Class Design

The PlantUML source for the system class diagram is available at:

```text
docs/class-diagram.puml
```

The design separates course data, linked nodes, BST nodes, data-structure operations, prerequisite logic, and menu coordination into dedicated classes.

## Technical Notes

- `Course` represents course metadata, completion state, grade, and prerequisite IDs.
- `Linked` maintains the student's selected courses.
- `Queue` uses front and rear references for FIFO operations.
- `BST` indexes completed courses by grade and supports duplicate grades.
- `CourseGraph` stores the course catalog and interprets prerequisite IDs as directed dependencies.
- `Main` creates the sample catalog and coordinates the console workflow.

## Limitations

- Data exists only in memory and is lost when the program exits.
- The sample course catalog is hardcoded.
- The catalog uses a fixed capacity.
- The interface is console-based.
- The prerequisite graph is represented through course arrays and prerequisite ID arrays rather than a dedicated adjacency-list structure.
- The repository does not currently include automated unit tests.
- This is an educational prototype, not a production-ready academic records system.

## Intended Use

This project is intended to demonstrate Java OOP, manually implemented abstract data types, recursive traversal, and basic algorithm analysis. It should not be used as an official registration, advising, or student-record platform.

## Technologies

- Java
- Object-oriented programming
- PlantUML
- Git and GitHub
