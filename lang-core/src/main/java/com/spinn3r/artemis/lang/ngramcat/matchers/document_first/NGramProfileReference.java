package com.spinn3r.artemis.lang.ngramcat.matchers.document_first;

/**
 *
 */
public class NGramProfileReference {

    private final int profile;

    private final int position;

    public NGramProfileReference(int profile, int position) {
        this.profile = profile;
        this.position = position;
    }

    public int getProfile() {
        return profile;
    }

    public int getPosition() {
        return position;
    }

    @Override
    public String toString() {
        return "NGramProfileReference{" +
                 "profile=" + profile +
                 ", position=" + position +
                 '}';
    }
    
}
