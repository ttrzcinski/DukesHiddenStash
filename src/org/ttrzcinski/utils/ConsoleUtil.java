package org.ttrzcinski.utils;

import lombok.experimental.UtilityClass;
import org.ttrzcinski.oscar.OSCommandArtificialRunner;

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
     * @param cmd  given command
     * @param args command's arguments
     * @return output of command
     */
    public String cmd(final String cmd, final String args) {
        final String result = "";
        final Runtime r = Runtime.getRuntime();
        Process p;
        final String newCmd = String.format("cmd.exe -c %s", cmd);
        final String[] command = ParamCheck.isSet(args)
                ? new String[]{newCmd, args}
                : new String[]{newCmd};

        try {
            ProcessBuilder processBuilder = new ProcessBuilder();
            //process = r.exec(command[0]);
            if (OSCommandArtificialRunner.isNix()) {
                // It is a Unix-bases OS
                processBuilder.command("bash", "-c", command[0]);
            } else {
                // It is Windows
                processBuilder.command("cmd.exe", "/c", command[0]);
            }
            Process process = processBuilder.start();
            process.waitFor(PROCESS_TIMEOUT, TimeUnit.SECONDS);
            while (process.isAlive()) {
                System.out.println("Not yet..");
            }
            System.out.printf("%s returned %d%n", command[0], process.exitValue());
        } catch (NullPointerException npe_1) {
            System.out.printf("NullPointerException executing %s%n", command[0]);
            npe_1.printStackTrace();
        } catch (InterruptedException intExc_01) {
            System.out.printf("InterruptedException executing %s%n", command[0]);
            intExc_01.printStackTrace();
        } catch (IOException ioExc_01) {
            System.out.printf("IOException executing %s%n", command[0]);
            ioExc_01.printStackTrace();
        } catch (SecurityException secExc_01) {
            System.out.printf("SecurityException executing%s%n ", command[0]);
            secExc_01.printStackTrace();
        }

        return result;
    }
}
