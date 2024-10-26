package com.yassine.practice.Email;

import lombok.Data;
import lombok.Getter;

@Getter
public enum EmailTemplateName {
    ACTIVATE_ACCOUNT("activate_account");
    final String name;

    EmailTemplateName(String name) {
        this.name = name;
    }
}
