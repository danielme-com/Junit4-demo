package com.danielme.blog.testing;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public enum UserService {

    INSTANCE;

    private final List<String> users = Arrays.asList("Bombur", "Bofur", "Bifur", "Nori", "Ori", "Dori", "Thorin",
            "Balin", "Dwalin", "Oin", "Gloin", "Fili", "Kili");

    public List<String> getUsersByName(String name) {
        if (name == null) {
            return Collections.emptyList();
        }

        return users.stream()
                .filter(u -> u.toUpperCase().contains(name.toUpperCase()))
                .collect(Collectors.toList());
    }

    public String getUserByPosition(int pos) {
        return users.get(pos);
    }

}
