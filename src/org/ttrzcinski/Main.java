package org.ttrzcinski;

import org.ttrzcinski.utils.UnitTestGenerator;

/**
 * Entry point to the library.
 */
public class Main {

    /**
     * Entry proint to the library functionality as a application.
     *
     * @param args given arguments
     */
    public static void main(String[] args) {

        System.out.println("Actually You suppose not to run this - it's a library.");

        /*try {
            ProcessingSpinner.process(100, "Taking control over the world.");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }*/
        /*String content = String.valueOf(HTTPObtainer.obtainAsListInFuture("http://www.bash.org"));
        System.out.println(content);*/

        new UnitTestGenerator()
            //.fromIML("C:\\\\Projects\\\\DukesHiddenStash\\\\DukesHiddenStash.iml")
            .fromCurrentProject()
            //.withSource("C:\\Projects\\DukesHiddenStash\\src")
            //.console();
            .generate();
    }
}
