package net.sinou.file.merger.domain;

import java.util.Arrays;

/** Useful file info to enable later merging */
public class FileInfo {

	private long fileId;
	private String fileName;
	private String filePath;
	private long created;
	private long lastModified;
	private String owner;
	private long size;
	private byte[] fileMd5;

	public FileInfo() {
	}

	public long getFileId() {
		return fileId;
	}

	public void setFileId(long fileId) {
		this.fileId = fileId;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getFilePath() {
		return filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

	public void setCreated(long created) {
		this.created = created;
	}

	public long getCreated() {
		return created;
	}

	public long getLastModified() {
		return lastModified;
	}

	public void setLastModified(long lastModified) {
		this.lastModified = lastModified;
	}

	public String getOwner() {
		return owner;
	}

	public void setOwner(String owner) {
		this.owner = owner;
	}

	public byte[] getFileMd5() {
		return fileMd5;
	}

	public void setFileMd5(byte[] fileMd5) {
		this.fileMd5 = fileMd5;
	}

	public void setSize(long size) {
		this.size = size;
	}

	public long getSize() {
		return size;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + Arrays.hashCode(fileMd5);
		result = prime * result + ((filePath == null) ? 0 : filePath.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		FileInfo other = (FileInfo) obj;
		if (!Arrays.equals(fileMd5, other.fileMd5))
			return false;
		if (filePath == null) {
			if (other.filePath != null)
				return false;
		} else if (!filePath.equals(other.filePath))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return filePath;
	}
}