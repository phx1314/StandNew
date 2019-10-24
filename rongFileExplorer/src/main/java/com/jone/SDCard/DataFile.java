package com.jone.SDCard;

import java.io.File;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class DataFile implements Serializable {
    /**
     *
     */
    private static final long serialVersionUID = 1L;
    private String fileSize;
    private String fileName;
    private String fileId;
    private File mFile;
    public String reftable;
    public List<File> mFile_son=new ArrayList<>();
    public int start;

    public File getmFile() {
        return mFile;
    }

    public void setmFile(File mFile) {
        this.mFile = mFile;
    }

    public String getFileSize() {
        return fileSize;
    }

    public void setFileSize(String fileSize) {
        this.fileSize = fileSize;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getFileId() {
        return fileId;
    }

    public void setFileId(String fileId) {
        this.fileId = fileId;
    }

    public DataFile(String fileSize, String fileName, String fileId, File mFile) {
        super();
        this.fileSize = fileSize;
        this.fileName = fileName;
        this.fileId = fileId;
        this.mFile = mFile;
    }

    public DataFile(String fileSize, String fileName, String fileId, File mFile, String reftable) {
        super();
        this.fileSize = fileSize;
        this.fileName = fileName;
        this.fileId = fileId;
        this.reftable = reftable;
        this.mFile = mFile;
    }

}
