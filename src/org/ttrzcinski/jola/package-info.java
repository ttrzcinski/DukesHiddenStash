/**
 * Java's Overall Logger API - suppose to be one tool to cover logging in the project.
 * <p>
 * Features:<br/>
 * - Option to write in multiple places at once
 * - Simple work with annotations
 * - Internal catcher for errors to log them down with StackTrace
 * - Measure time between start and stop click to log that down. // TODO THAT MIGHT BE SEPARATE PROJECT
 * - Generates separate view (like Swagger or ApiDoc) to read from logs and refresh them
 * - Option to send further log entries in case of lack access to standard log
 * Contains:<br/> -Log1 - annotation and class to initialize handle to log and save/log/sent it to log stash
 * <b/>-Stopper - small class to measure time between start and stop - it passes generated unique key to mark start and stop.
 *
 * @author <a href="mailto:trzcinski.tomasz.1988@gmail.com">Tomasz T.</a>
 * @version 1.0
 * @since 1.0
 */
// TODO MOVE CLASSES TO THIS PACKAGE
// TODO ADD DESCRIPTION OF OTHER USED HERE CLASSES
// TODO FIND SOME ANIMAL WITH A-LIKE NAME TO MAKE IT A MASCOT-AVATAR FOR GITHUB
package org.ttrzcinski.jola;