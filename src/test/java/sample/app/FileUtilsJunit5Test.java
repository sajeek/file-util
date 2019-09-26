package sample.app;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import sample.util.FileUtils;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.hamcrest.CoreMatchers.endsWith;
import static org.junit.jupiter.api.Assertions.assertAll;

public class FileUtilsJunit5Test {

	@TempDir
	Path tempDir;

	@BeforeEach
	void beforeEach() {
		assertTrue(Files.isDirectory(this.tempDir));
	}

	@Test
	void moveSingleFile2DestinationDirectory() throws IOException {

		File srcDir = Files.createDirectories(tempDir.resolve("source")).toFile();

		Files.createFile(srcDir.toPath().resolve("sample-file-2-move.txt")).toFile();

		File destDir = Files.createDirectories(tempDir.resolve("destination")).toFile();

		FileUtils.moveFiles(srcDir, destDir);

		assertAll(() -> assertEquals(1, destDir.list().length),
				() -> assertEquals("sample-file-2-move.txt", destDir.list()[0]));

	}

	@Test
	void moveMultipleFiles2DestinationDirectory() throws IOException {

		File srcDir = Files.createDirectories(tempDir.resolve("source")).toFile();

		for (int i = 0; i < 10; i++)
			Files.createFile(srcDir.toPath().resolve(i + "-sample-file-2-move.txt")).toFile();

		File destDir = Files.createDirectories(tempDir.resolve("destination")).toFile();

		FileUtils.moveFiles(srcDir, destDir);

		assertAll(() -> assertEquals(10, destDir.list().length));

	}
	
	@Test
	void moveMultipleFilesExcludingDirectories() throws IOException {

		File srcDir = Files.createDirectories(tempDir.resolve("source")).toFile();
		
		int noOfFiles = 10;

		for (int i = 0; i < noOfFiles; i++)
			Files.createFile(srcDir.toPath().resolve(i + "-sample-file-2-move.txt")).toFile();
		
		Files.createDirectories(srcDir.toPath().resolve("sample-directory.txt")).toFile();

		File destDir = Files.createDirectories(tempDir.resolve("destination")).toFile();

		FileUtils.moveFiles(srcDir, destDir);

		assertAll(() -> assertEquals(noOfFiles, destDir.list().length));

	}
	
}
