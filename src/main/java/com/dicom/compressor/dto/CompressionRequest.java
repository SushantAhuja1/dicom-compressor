package com.dicom.compressor.dto;

public class CompressionRequest {
    private String folderPath;
    private String jcode;

    public CompressionRequest() {
    }

    public CompressionRequest(String folderPath, String jcode) {
        this.folderPath = folderPath;
        this.jcode = jcode;
    }

    public String getFolderPath() {
        return folderPath;
    }

    public void setFolderPath(String folderPath) {
        this.folderPath = folderPath;
    }

    public String getJcode() {
        return jcode;
    }

    public void setJcode(String jcode) {
        this.jcode = jcode;
    }
}
