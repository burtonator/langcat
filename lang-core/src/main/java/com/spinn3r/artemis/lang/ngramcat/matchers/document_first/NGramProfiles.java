package com.spinn3r.artemis.lang.ngramcat.matchers.document_first;

import com.google.common.collect.Lists;

import java.util.List;

/**
 * Used as a record in an HashMap for the index of profiles by NGram.  The position
 * is the rank position in the original profile. The profiles are integer references
 * to the internal profile ID.
 */
class NGramProfiles {

    private int position;

    private List<Integer> profiles = Lists.newArrayList();

    public NGramProfiles(int position) {
        this.position = position;
    }

    public int getPosition() {
        return position;
    }

    public List<Integer> getProfiles() {
        return profiles;
    }

    @Override
    public String toString() {
        return "NGramProfiles{" +
                 "position=" + position +
                 ", profiles=" + profiles +
                 '}';
    }

}
