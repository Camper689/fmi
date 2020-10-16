package com.fmi.controller;

import org.springframework.web.servlet.mvc.support.RedirectAttributes;

public enum RequestResult {

    SUCCESS_ADDED, ERROR_EXISTS, ERROR_EMPTY, ERROR_NOT_FOUND_BY_ID, SUCCESS_EDITED, SUCCESS_DELETED;

    public boolean isSuccess() {
        return this.name().toUpperCase().startsWith("SUCCESS");
    }

    public void write(RedirectAttributes redirectAttributes) {
        redirectAttributes.addFlashAttribute(isSuccess() ? "successMessage" : "errorMessage", getMessage());
    }

    private String getMessage() {
        switch (this) {
            case SUCCESS_ADDED: return "Запис додано";
            case ERROR_EXISTS: return "Такий запис вже існує";
            default: return "#" + name() + "#";
        }
    }
}
