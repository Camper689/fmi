package com.fmi.service;

public enum ImageUploadResult {

    SUCCESS, ERROR_LARGE_FILE, ERROR_SAVE, ERROR_BAD_FILE_EXTENSION;

    public boolean isError() {
        return this.name().toUpperCase().startsWith("ERROR");
    }
}
