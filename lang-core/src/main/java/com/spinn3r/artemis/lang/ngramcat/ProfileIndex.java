package com.spinn3r.artemis.lang.ngramcat;

/**
 *
 */
public class ProfileIndex {

    private final ProfileNGram[] profileNGrams;

    private final Profile[] profilesArr;

    public ProfileIndex(ProfileNGram[] profileNGrams, Profile[] profilesArr) {
        this.profileNGrams = profileNGrams;
        this.profilesArr = profilesArr;
    }

    public ProfileNGram[] getProfileNGrams() {
        return profileNGrams;
    }

    public Profile[] getProfilesArr() {
        return profilesArr;
    }

}
