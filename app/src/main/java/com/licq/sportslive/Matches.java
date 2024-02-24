package com.licq.sportslive;

import androidx.annotation.NonNull;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Matches implements Iterable<Match> {

    private List<Match> matches = new ArrayList<>();

    public void addMatch(Match match) {
        if (match.getStatus() != Status.ENDED)
            matches.add(match);
    }

    public List<Category> getCategories() {
        List<Category> categories = new ArrayList<>();
        for (Match match : matches) {
            if (!categories.contains(match.getCategory())) {
                categories.add(match.getCategory());
            }
        }
        return categories;
    }


    @NonNull
    @Override
    public Iterator<Match> iterator() {
        return matches.iterator();
    }

    public int getCount() {
        return matches.size();
    }

    public Match get(int position) {
        return matches.get(position);
    }
}
