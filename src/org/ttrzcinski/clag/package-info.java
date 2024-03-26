/**
 * Command Line API Generator
 * <p>
 * Generates console handling commands to the application. It has three parts:<br/>
 * - arguments with their parsing, skipping, processing and post-processing into flags,<br/>
 * - preparation of help command table<br/>
 * - generating standard params (X - all errors, V - version, verbose - debug log, h-help)
 * <p>
 * - CLAG - man class with builder to call and generate CLA for pointed application or from given description<br/>
 * - Argument - set of methods usable to process given entry arguments to the application<br/>
 * - EnvArgument - read value of Environment or Path from where it is running
 *
 * @author <a href="mailto:trzcinski.tomasz.1988@gmail.com">Tomasz T.</a>
 * @version 1.0
 * @since 1.0
 */
package org.ttrzcinski.clag;
