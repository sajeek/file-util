package sample.app;

import java.io.File;
import java.io.IOException;
import java.io.UncheckedIOException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

import sample.util.FileUtils;

/**
 * the main application to utilize 'FileUtils'
 *
 */
public class Application {

	private static final Logger LOGGER = Logger.getLogger(Application.class.getName());

	public static void main(String[] args) {

		Scanner input = null;

		try {
			input = new Scanner(System.in);
			System.out.println("Enter the source directory path");
			String srcDirPath = input.next();

			System.out.println("Enter the destination directory path");
			String destDirPath = input.next();

			File srcDir = new File(srcDirPath);
			File destDir = new File(destDirPath);

			FileUtils.moveFiles(srcDir, destDir);
			LOGGER.log(Level.INFO, "Files moved successfully");
		} catch (IOException ioe) {
			LOGGER.log(Level.WARNING, "Error : " + ioe.getMessage());
		} catch (UncheckedIOException uioe) {
			LOGGER.log(Level.WARNING, "Error : " + uioe.getMessage());
		} finally {
			input.close();
		}
	}
}
