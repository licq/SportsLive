package com.licq.sportslive;

import androidx.annotation.NonNull;

import java.util.Objects;

public class Category {
    private final String text;

    public Category(String text) {

        this.text = text;
    }

    public String getText() {
        return text;
    }

    @NonNull
    @Override
    public String toString() {
        return this.text;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Category category = (Category) o;
        return Objects.equals(text, category.text);
    }

    @Override
    public int hashCode() {
        return Objects.hash(text);
    }
}
