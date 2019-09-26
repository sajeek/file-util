package sample.util;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.stream.Collectors;

public class FileUtils {

	/**
	 * the utility function to move all files in a source directory to destination directory
	 * @param srcDir - source directory
	 * @param destDir - destination directory
	 * @throws IOException - checked exceptions during the operation
	 */
	public static void moveFiles(final File srcDir, final File destDir) throws IOException {
		if (!srcDir.isDirectory()) {
			throw new IOException("Source '" + srcDir + "' is not a directory");
		}
		if (!destDir.isDirectory()) {
			throw new IOException("Destination '" + destDir + "' is not a directory");
		}
		if (srcDir.getCanonicalPath().equals(destDir.getCanonicalPath())) {
			throw new IOException("Source '" + srcDir + "' and destination '" + destDir + "' are the same");
		}

		List<File> files2Move = retreiveFiles(srcDir);

		if (files2Move.size() < 1) {
			throw new IOException("Source '" + srcDir + "' contains no file to move");
		}

		for (File fileItr : files2Move) {
			moveFile(fileItr.getName(), srcDir.getAbsolutePath(), destDir.getAbsolutePath());
		}
	}

	/**
	 * function moves named file from source directory to destination directory
	 * 
	 * @param fileName - file name with extension
	 * @param srcPath  - source directory path
	 * @param destPath - destination directory path
	 * @throws IOException - checked exceptions during the operation
	 */
	private static void moveFile(String fileName, String srcPath, String destPath) throws IOException {

		Files.move(Paths.get(srcPath + "/" + fileName), Paths.get(destPath + "/" + fileName),
				StandardCopyOption.REPLACE_EXISTING);
	}

	/**
	 * function returns list of files of a give directory
	 * @param srcDir - source directory
	 * @return - list of files, except directories within the source
	 * @throws IOException - checked exceptions during the operation
	 */
	private static List<File> retreiveFiles(final File srcDir) throws IOException {

		// accumulating files, ignoring directories
		List<File> files = Files.walk(Paths.get(srcDir.getAbsolutePath())).filter(Files::isRegularFile)
				.map(x -> x.toFile()).collect(Collectors.toList());
		return files;
	}

}
