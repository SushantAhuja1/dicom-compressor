package com.dicom.compressor.dto;

public class CompressionResponse {
    private boolean success;
    private String message;
    private String jcode;
    private int filesProcessed;

    public CompressionResponse() {
    }

    public CompressionResponse(boolean success, String message, String jcode, int filesProcessed) {
        this.success = success;
        this.message = message;
        this.jcode = jcode;
        this.filesProcessed = filesProcessed;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getJcode() {
        return jcode;
    }

    public void setJcode(String jcode) {
        this.jcode = jcode;
    }

    public int getFilesProcessed() {
        return filesProcessed;
    }

    public void setFilesProcessed(int filesProcessed) {
        this.filesProcessed = filesProcessed;
    }
}
