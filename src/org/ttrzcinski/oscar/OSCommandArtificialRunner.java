package org.ttrzcinski.oscar;

import org.ttrzcinski.utils.ParamCheck;
import org.ttrzcinski.utils.SafeClose;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class OSCommandArtificialRunner {

    // TODO CHANGE TO SEQ COLL - getLast will be used.
    /**
     * List of used commands on local Shell.
     */
    private static LinkedList<String> lastCommands;

    /**
     * Adds given command to list of last used commands.
     *
     * @param cmd given command
     */
    private static void appendUsedCommand(String cmd) {
        // Check, if list was initialized
        if (lastCommands == null) {
            OSCommandArtificialRunner.lastCommands = new LinkedList<>();
        }
        // Keep it for later
        lastCommands.add(cmd);
    }

    public static List<String> getUsedCommands() {
        return OSCommandArtificialRunner.lastCommands.stream().toList();
    }

    public static String getLast() {
        return OSCommandArtificialRunner.lastCommands.getLast();
    }

    /**
     * Calls a single command and passes response as lines of output.
     *
     * @param cmd given command to run in the console
     * @return lines of output, if there was an output, empty list in case of no command
     */
    public static List<String> run(String cmd) {
        // Check enter command
        if (!ParamCheck.isSet(cmd)) {
            System.err.println("Couldn't run empty command.");
            return List.of();
        }
        List<String> response = new ArrayList<>();

        ProcessBuilder processBuilder = new ProcessBuilder();
        if (isNix()) {
            // It is a Unix-bases OS
            processBuilder.command(Shells.BASH.actualName(), "-c", cmd);
        } else {
            // It is Windows
            processBuilder.command(Shells.CMD.actualName(), "/c", cmd);
        }

        BufferedReader reader = null;
        InputStreamReader isr = null;
        try {
            // Keep it for later
            appendUsedCommand(cmd);
            // Start the process
            Process process = processBuilder.start();
            StringBuilder output = new StringBuilder();
            isr = new InputStreamReader(process.getInputStream());
            reader = new BufferedReader(isr);
            String line;
            while ((line = reader.readLine()) != null) {
                output.append(line + "\n");
            }

            int exitVal = process.waitFor();
            if (exitVal == 0) {
                response = Arrays.stream(output.toString().split("\n")).toList();
            }
        } catch (IOException ioe_1) {
            ioe_1.printStackTrace();
        } catch (Exception exc) {
            exc.printStackTrace();
        } finally {
            SafeClose.close(isr);
            SafeClose.close(reader);
        }

        return response;
    }

    /**
     * Kills opened console.
     */
    public static void quit() {
        ProcessBuilder processBuilder = new ProcessBuilder();
        if (isNix()) {
            // It is a Unix-bases OS
            processBuilder.command("bash", "-c", "exit");
        } else {
            // It is Windows
            processBuilder.command("cmd.exe", "/c", "exit");
        }

        try {
            // Keep it for later
            appendUsedCommand("exit");
            // Start the process
            Process process = processBuilder.start();
            int exitVal = process.waitFor();
            if (exitVal == 0) {
                System.out.println("KILLED the shell.");
                System.exit(0);
            } else {
                System.err.println("What?!");
            }
        } catch (IOException ioe_1) {
            ioe_1.printStackTrace();
        } catch (Exception exc) {
            exc.printStackTrace();
        }
    }

    /**
     * Checks, on what system run the application.
     *
     * @return true means windows, false other OS
     */
    public static boolean isWindows() {
        return System.getProperty("os.name").startsWith("Windows");
    }

    /**
     * Checks, on what system run the application.
     *
     * @return true means *nix, false other OS
     */
    public static boolean isNix() {
        String actualResponse = System.getProperty("os.name").toLowerCase();
        return actualResponse.contains("unix") || actualResponse.contains("linux");
    }

    /**
     * List of Operating Systems covered.
     */
    public enum OSes {
        UNIX("unix"),
        LINUX("linux"),
        WINDOWS("windows");

        private final String value;

        OSes(String wanted) {
            this.value = wanted;
        }

        /**
         * Returns exact name of picked OS.
         *
         * @return OS'es name
         */
        public String actualName() {
            return this.value;
        }
    }

    // TODO CHANGE TO STATIC LIST AS USER OR SCRIPT CAN ADD/INSTALL SHELLS, WHILE RUNNING APPLICATION
    /**
     * List of OS console shells covered.
     */
    public enum Shells {
        BASH("bash"),
        CMD("cmd"),
        PS("pswe");

        private final String value;

        Shells(String bash) {
            value = bash;
        }

        /**
         * Returns exact name of picked Shell.
         *
         * @return Shell's name
         */
        public String actualName() {
            return this.value;
        }
    }

}
