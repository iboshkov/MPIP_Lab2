package com.example.user.lab2_1;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Helper class for providing sample content for user interfaces created by
 * Android template wizards.
 * <p>
 * TODO: Replace all uses of this class before publishing your app.
 */
public class GradItem {
    public final String content;
    public final String details;

    public GradItem(String content, String details) {
        this.content = content;
        this.details = details;
    }

    @Override
    public String toString() {
        return content;
    }
}