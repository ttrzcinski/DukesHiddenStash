package org.ttrzcinski.utils;

import lombok.experimental.UtilityClass;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

/**
 * Console class to call processes.<br/>
 * Created 31.08.2019 12:06 as a part of project DukesHiddenStash
 *
 * @author <a href="mailto:trzcinski.tomasz.1988@gmail.com">Tomasz T.</a>
 * @version %I% from %G%
 * @since 1.12
 */
@UtilityClass
public class ConsoleUtil {

  private final int PROCESS_TIMEOUT = 10;

  /**
   * Calls command on default console.
   *
   * @param cmd given command
   * @param args command's arguments
   * @return output of command
   */
  public String cmd(final String cmd, final String args) {
    final String result = "";
    final Runtime r = Runtime.getRuntime();
    Process p;
    final String newCmd = "cmd.exe -c " + cmd;
    final String[] command = ParamCheck.isSet(args)
        ? new String[]{newCmd, args}
        : new String[]{newCmd};

    try {
      // TODO MIGRATE TO PROCESSBUILDER
      p = r.exec(command[0]);
      p.waitFor(PROCESS_TIMEOUT, TimeUnit.SECONDS);
      while (p.isAlive()) {
        System.out.println("Not yet..");
      }
      System.out.printf("%s returned %d%n", command[0], p.exitValue());
    } catch (NullPointerException e) {
      System.out.println("NullPointerException executing " + command[0]);
      e.printStackTrace();
    } catch (InterruptedException e) {
      System.out.println("InterruptedException executing " + command[0]);
      e.printStackTrace();
    } catch (IOException e) {
      System.out.println("IOException executing " + command[0]);
      e.printStackTrace();
    } catch (SecurityException e) {
      System.out.println("SecurityException executing " + command[0]);
      e.printStackTrace();
    }

    return result;
  }
}
