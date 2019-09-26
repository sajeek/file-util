package sample.app;

import static org.hamcrest.CoreMatchers.endsWith;
import static org.hamcrest.CoreMatchers.startsWith;

import java.io.File;
import java.io.IOException;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.rules.TemporaryFolder;

import sample.util.FileUtils;;

public class FileUtilsTest {

	@Rule
	public TemporaryFolder temporaryFolder = new TemporaryFolder();

	@Rule
	public ExpectedException thrown = ExpectedException.none();

	@Test
	public void throwsErrorWhenSourceIsFile() throws IOException {
		File srcDir = temporaryFolder.newFile();
		File destDir = temporaryFolder.newFolder();

		thrown.expect(IOException.class);
		thrown.expectMessage(startsWith("Source"));
		thrown.expectMessage(endsWith("is not a directory"));

		FileUtils.moveFiles(srcDir, destDir);
	}

	@Test
	public void throwsErrorWhenDestinationIsFile() throws IOException {
		File srcDir = temporaryFolder.newFolder();
		File destDir = temporaryFolder.newFile();

		thrown.expect(IOException.class);
		thrown.expectMessage(startsWith("Destination"));
		thrown.expectMessage(endsWith("is not a directory"));

		FileUtils.moveFiles(srcDir, destDir);

	}

	@Test
	public void throwsErrorWhenSourceAndDestinationSame() throws IOException {
		File srcDir = temporaryFolder.newFolder();
		File destDir = srcDir;

		thrown.expect(IOException.class);
		thrown.expectMessage(endsWith("are the same"));

		FileUtils.moveFiles(srcDir, destDir);
	}

	@Test
	public void throwsErrorWhenSourceDirecotryContainsZeroFiles() throws IOException {
		File srcDir = temporaryFolder.newFolder();
		File destDir = temporaryFolder.newFolder();

		thrown.expect(IOException.class);
		thrown.expectMessage(startsWith("Source"));
		thrown.expectMessage(endsWith("contains no file to move"));

		FileUtils.moveFiles(srcDir, destDir);
	}
	
	@Test
	public void throwsErrorWhenNonExistingSourceDirectory() throws IOException {
		File srcDir = new File("non_existing_directory_src");
		File destDir = temporaryFolder.newFolder();

		thrown.expect(IOException.class);
		thrown.expectMessage(startsWith("Source"));
		thrown.expectMessage(endsWith("is not a directory"));

		FileUtils.moveFiles(srcDir, destDir);
	}
	
	@Test
	public void throwsErrorWhenNonExistingDestianationDirectory() throws IOException {
		File destDir = new File("non_existing_directory_dest");
		File srcDir = temporaryFolder.newFolder();

		thrown.expect(IOException.class);
		thrown.expectMessage(startsWith("Destination"));
		thrown.expectMessage(endsWith("is not a directory"));

		FileUtils.moveFiles(srcDir, destDir);
		
	}
	
}