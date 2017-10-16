package net.sinou.file.merger.item;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.BasicFileAttributes;
import java.nio.file.attribute.FileOwnerAttributeView;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemProcessor;

import net.sinou.file.merger.domain.FileInfo;
import net.sinou.file.util.DigestUtils;

/**
 * Read info from the passed Path and compute a MD5 digest to store in the
 * domain FileInfo object
 */
public class FileItemProcessor implements ItemProcessor<File, FileInfo> {

	private static final Logger log = LoggerFactory.getLogger(FileItemProcessor.class);

	@Override
	public FileInfo process(final File file) throws Exception {
		Path path = Paths.get(file.getPath());
		FileInfo fileInfo = new FileInfo();
		fileInfo.setFileName(file.getName());
		fileInfo.setFilePath(file.getAbsolutePath());
		fileInfo.setSize(file.length());
		fileInfo.setLastModified(file.lastModified());
		FileOwnerAttributeView ownerAttributeView = Files.getFileAttributeView(path, FileOwnerAttributeView.class);
		fileInfo.setOwner(ownerAttributeView.getOwner().getName());
		BasicFileAttributes attributes = Files.readAttributes(path, BasicFileAttributes.class);
		fileInfo.setCreated(attributes.creationTime().toMillis());

		// Generates the MD5 digest
		fileInfo.setFileMd5(DigestUtils.getMD5Digest(file));
		return fileInfo;
	}

}