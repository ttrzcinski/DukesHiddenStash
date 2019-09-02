package org.ttrzcinski.utils;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * Environment argument works as a structure with<br/> acronym,
 * full name and help description.
 */
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class EnvArgument {

  /**
   * Known constant of middle of console width.
   */
  private static final int HALF_OF_WIDTH = 40;

  /**
   * Represents unix style short name of argument.
   */
  private String letter;

  /**
   * Represents a full name of argument.
   */
  private String nick;

  /**
   * Represents whole description of the parameter.
   */
  private String help;

  /**
   * Sets acronym (first letters) from argument's name.
   *
   * @param acronym given acronym
   * @return handle to this argument
   */
  public final EnvArgument withAcronym(final String acronym) {
    if (ParamCheck.isSet(acronym)) {
      this.letter = acronym;
    }
    return this;
  }

  /**
   * Sets argument's name.
   *
   * @param name given name
   * @return handle to this argument
   */
  public final EnvArgument withName(final String name) {
    if (ParamCheck.isSet(name)) {
      this.nick = name;
    }
    return this;
  }

  /**
   * Sets argument's description.
   *
   * @param description given description
   * @return handle to this argument
   */
  public final EnvArgument withDescription(final String description) {
    if (ParamCheck.isSet(description)) {
      this.help = description;
    }
    return this;
  }

  /**
   * Passes as string to be presented in console's help.
   *
   * @return help's entry
   */
  public final String asHelp() {
    final String legend = String.format(
        String.format("%%-%ds", HALF_OF_WIDTH),
        String.format("--%s, -%s, --%s, -%s",
            this.letter, this.letter, this.nick, this.nick
        )
    );
    // Final formatting
    return new StringBuilder(legend).append(" : ").append(this.help).toString();
  }
}
