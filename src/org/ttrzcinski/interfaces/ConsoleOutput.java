package org.ttrzcinski.interfaces;

/**
 * While extending the class, eases the System.out.print("[INTO]" down to out(.
 */
public abstract class ConsoleOutput {

  public void out(String given) {
    System.out.println("[INFO] " + given);
  }

  public void err(String given) {
    System.err.println("[ERR] " + given);
  }

  public void warn(String given) {
    System.out.println("[WARN]" + given);
  }
}
