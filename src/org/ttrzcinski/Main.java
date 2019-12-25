package org.ttrzcinski;

import org.ttrzcinski.utils.ConsoleUtil;

/**
 * Entry point to the library.
 */
public final class Main {

  /**
   * Hidden constructor.
   */
  private Main() {
  }

  /**
   * Entry point to the library functionality as a application.
   *
   * @param args given arguments
   */
  public static void main(final String[] args) {

    System.out.println(
        "Actually You suppose not to run this - it's a library."
    );

    //String result = ConsoleUtil.cmd("git -?", null);
    final String result = ConsoleUtil.cmd("dir", null);
    System.out.println(result);

        /*try {
            ProcessingSpinner.process(100, "Taking control over the world.");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }*/
        /*String content = String.valueOf(
            HTTPObtainer.obtainAsListInFuture("http://www.bash.org")
        );
        System.out.println(content);*/

        /*new UnitTestGenerator()
            //.fromIML(
                "C:\\\\Projects\\\\DukesHiddenStash\\\\DukesHiddenStash.iml"
            )
            .fromCurrentProject()
            //.withSource("C:\\Projects\\DukesHiddenStash\\src")
            //.console();
            .generate();*/
    }
}
