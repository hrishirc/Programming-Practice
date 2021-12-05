package com.phonepe;

public class FileMetadata {
    private int startIdx;
    private int size;

    public FileMetadata(int startIdx, int size) {
        this.startIdx = startIdx;
        this.size = size;
    }

    public int getStartIdx() {
        return startIdx;
    }

    public int getSize() {
        return size;
    }

    public void setStartIdx(int startIdx) {
        this.startIdx = startIdx;
    }
}
