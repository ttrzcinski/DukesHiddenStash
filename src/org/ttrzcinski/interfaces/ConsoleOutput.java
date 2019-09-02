package org.ttrzcinski.interfaces;

/**
 * While extending the class, eases the System.out.print("[INTO]" down to out(.
 */
public abstract class ConsoleOutput {

  public final void out(final String given) {
    System.out.println("[INFO] " + given);
  }

  public final void err(final String given) {
    System.err.println("[ERR] " + given);
  }

  public final void warn(final String given) {
    System.out.println("[WARN]" + given);
  }
}
