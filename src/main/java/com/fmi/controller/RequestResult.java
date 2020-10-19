package com.fmi.controller;

import com.fmi.service.ImageUploadResult;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

public enum RequestResult {

    SUCCESS_ADDED, SUCCESS_IMAGES_ADDED, ERROR_EXISTS, ERROR_EMPTY, ERROR_NOT_FOUND_BY_ID, SUCCESS_EDITED, SUCCESS_DELETED, ERROR_BAD_FILE_EXTENSION, ERROR_LARGE_FILE, ERROR_SAVE_IMAGE, SUCCESS, SUCCESS_AVATAR_ERROR, ERROR_TEACHER_NOT_IN_DEPARTMENT, ERROR_TOO_LONG, ERROR_TITLE_REQUIRED_IF_IN_SECTION, ERROR_TITLE_LENGTH, ERROR_BODY_LENGTH;

    public static RequestResult valueOf(ImageUploadResult imageUploadResult) {
        switch (imageUploadResult) {
            case ERROR_BAD_FILE_EXTENSION:
                return ERROR_BAD_FILE_EXTENSION;
            case ERROR_LARGE_FILE:
                return ERROR_LARGE_FILE;
            case ERROR_SAVE:
                return SUCCESS_AVATAR_ERROR;
            case SUCCESS:
            default:
                return SUCCESS;
        }
    }

    public boolean isSuccess() {
        return this.name().toUpperCase().startsWith("SUCCESS");
    }

    public RequestResult write(RedirectAttributes redirectAttributes) {
        redirectAttributes.addFlashAttribute(isSuccess() ? "successMessage" : "errorMessage", getMessage());
        return this;
    }

    public String getMessage() {
        switch (this) {
            case SUCCESS_ADDED: return "Запис додано";
            case ERROR_EXISTS: return "Такий запис вже існує";
            default: return "#" + name() + "#";
        }
    }
}
