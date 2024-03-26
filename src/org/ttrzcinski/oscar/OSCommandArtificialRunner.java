package org.ttrzcinski.oscar;

import org.ttrzcinski.utils.ParamCheck;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class OSCommandArtificialRunner {

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
            return Arrays.asList();
        }
        List<String> response = new ArrayList<String>();

        ProcessBuilder processBuilder = new ProcessBuilder();
        boolean nixOS = true; // TODO CHECK THE OS
        if (nixOS) {
            // It is a Unix-bases OS
            processBuilder.command(Shells.BASH.name(), "-c", cmd);
        } else {
            // It is Windows
            processBuilder.command(Shells.CMD.name(), "/c", cmd);
        }

        try {
            Process process = processBuilder.start();
            StringBuilder output = new StringBuilder();
            BufferedReader reader = new BufferedReader(
                    new InputStreamReader(process.getInputStream())
            );
            String line;
            while ((line = reader.readLine()) != null) {
                output.append(line + "\n");
            }

            int exitVal = process.waitFor();
            /*if (exitVal == 0) {
                //System.out.println("Success!");
                //System.out.println(output);
                //System.exit(0);
            } else {
                System.err.println("What?!");
            }*/
            if (exitVal == 0) {
                response = Arrays.stream(output.toString().split("\n")).toList();
            }
        } catch (IOException ioe_1) {
            ioe_1.printStackTrace();
        } catch (Exception exc) {
            exc.printStackTrace();
        }

        return response;
    }

    /**
     * Kills opened console.
     */
    public static void quit() {
        ProcessBuilder processBuilder = new ProcessBuilder();
        boolean nixOS = true; // TODO CHECK THE OS
        if (nixOS) {
            // It is a Unix-bases OS
            processBuilder.command("bash", "-c", "exit");
        } else {
            // It is Windows
            processBuilder.command("cmd.exe", "/c", "exit");
        }

        try {
            Process process = processBuilder.start();
            int exitVal = process.waitFor();
            if (exitVal == 0) {
                System.out.println("Sayonara.");
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
     * List of Operating Systems covered.
     */
    public enum OSes {
        NIX("*nix"),
        WINDOWS("win*");

        private OSes(String wanted) {
        }

        /**
         * Returns exact name of picked OS.
         *
         * @return OS'es name
         */
        public String actualName() {
            return this.name();
        }
    }

    /**
     * List of OS console shells covered.
     */
    public enum Shells {
        BASH("bash"),
        CMD("cmd"),
        PS("pswe");

        private Shells(String bash) {
        }

        /**
         * Returns exact name of picked Shell.
         *
         * @return Shell's name
         */
        public String actualName() {
            return this.name();
        }
    }

}
