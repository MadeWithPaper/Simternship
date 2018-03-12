package simternship.simternship;

import java.util.List;
import java.util.Arrays;

/**
 * Created by joel on 3/10/18.
 */

public class InterviewFactory {
    RandomGenerator generator;
    List<InterviewQuestion> questions;
    private String f = "false";
    public InterviewFactory(RandomGenerator randomGenerator,
                            List<InterviewQuestion> questions) {
        generator = randomGenerator;
        this.questions = questions;
    }

    public InterviewFactory() {
        this(new RandomGenerator(), Arrays.asList(
                new InterviewQuestion("What is the worst-case time complexity of Quicksort", "quadratic"),
                new InterviewQuestion("Is Java interpreted, compiled, or both?", "both"),
                new InterviewQuestion("What language is used by most relational database management systems", "SQL"),
                new InterviewQuestion("What is the most important aspect of functional programming", "immutability"),
                new InterviewQuestion("What visual language is used to model software behavior", "UML"),
                new InterviewQuestion("What do closures use to implement lexical scoping", "environments"),
                new InterviewQuestion("What common data structure is LIFO", "stack"),
                new InterviewQuestion("What common data structure is FIFO", "queue"),
                new InterviewQuestion("What kind of data structure are structs in C used to define", "record"),
                new InterviewQuestion("What software methodology involves user stores and sprints", "Agile"),
                new InterviewQuestion("What do we call the case when a new bug is introduced while fixing another bug", "regression"),
                new InterviewQuestion("How can you determine the similarity of two strings?", "edit distance"),
                new InterviewQuestion("If a function has no side effects, it is pure (true or false)", "f"),
                new InterviewQuestion("If a function does not depend on mutable state, it is pure (true or false)", "f"),
                new InterviewQuestion("What do we call data structures that preserve their previous version when modified", "persistent data structures"),
                new InterviewQuestion("What does Haskell have that Scala lacks, other than purity", "full type inference"),
                new InterviewQuestion("What paradigm does SQL belong to", "declarative"),
                new InterviewQuestion("What kind of logic does SQL have", "3-valued logic"),
                new InterviewQuestion("What is the simplest and clearest way to implement BFS", "iteration with queue"),
                new InterviewQuestion("Can you write an efficient algorithm to make optimal change for an arbitrary currency", "no"),
                new InterviewQuestion("Binary search takes linear time", "f"),
                new InterviewQuestion("Heap sort and Quick sort have the same worst case time complexity", "f")
        ));
    }

    public JobInterview createInterview(Company company) {
        return new JobInterview(company, generator.sample(questions, 10));
    }
}
