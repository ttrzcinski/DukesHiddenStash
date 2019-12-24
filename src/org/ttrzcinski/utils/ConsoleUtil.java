package org.ttrzcinski.utils;

public class ConsoleUtil {

  public static String cmd(String cmd, String args) {
    String result = "";
    Runtime r = Runtime.getRuntime();
    Process p;
    String[] command = new StringHasValue().hasValue(args) ? new String[]{cmd, args} : new String[]{cmd};
    //cmd = "cmd.exe -c " + cmd;

    try {
      p = r.exec(command);
      p.waitFor();
      System.out.printf("%s returned %d%n", command[0], p.exitValue());
    } catch (Exception e) {
      System.out.println("error executing " + command[0]);
      e.printStackTrace();
    }

    return result;
  }
}
