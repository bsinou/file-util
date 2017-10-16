package net.sinou.file.merger.item;

import java.io.File;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;

import org.junit.Assert;
import org.junit.Test;

import net.sinou.file.merger.domain.FileInfo;

public class FileItemProcessorTest extends AbstractItemTest {

	/**
	 * Test method for
	 * {@link net.sinou.file.merger.item.FileItemProcessor#process(java.io.File)}.
	 */
	@Test
	public void testProcess() throws Exception {
		FileItemProcessor processor = new FileItemProcessor();

		try (DirectoryStream<Path> directoryStream = Files.newDirectoryStream(getParent())) {
			for (Path path : directoryStream) {
				File curr = path.toFile();
				FileInfo info = processor.process(curr);
				Assert.assertEquals(curr.getName(), info.getFileName());
			}
		}
	}
}
