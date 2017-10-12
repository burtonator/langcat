package com.spinn3r.artemis.lang.ngramcat;

/**
 * A profile and index identifier mapping.
 */
public class IndexLabeledProfile {

    private final int index;

    private final Profile profile;

    public IndexLabeledProfile(int index, Profile profile) {
        this.index = index;
        this.profile = profile;
    }

    public int getIndex() {
        return index;
    }

    public Profile getProfile() {
        return profile;
    }

}
