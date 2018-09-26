package com.danielme.blog.testing;

import java.util.ArrayList;
import java.util.List;

public enum UserService {

    INSTANCE;

    private final String[] users = { "Bombur", "Bofur", "Bifur", "Nori", "Ori", "Dori", "Thorin",
            "Balin", "Dwalin", "Oin", "Gloin", "Fili", "Kili" };

    public List<String> getUsersByName(String name) {
        List<String> res = new ArrayList<String>();
        if (name != null) {
            for (String user : users) {
                if (user.toUpperCase().contains(name.toUpperCase())) {
                    res.add(user);
                }
            }
        }
        return res;
    }

    public String getUserByPosition(int pos) {
        return users[pos];
    }

}
