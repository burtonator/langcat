package com.spinn3r.artemis.lang.ngramcat.matchers.min_distance;

import com.spinn3r.artemis.lang.ngramcat.ProfileManager;
import com.spinn3r.artemis.lang.ngramcat.matchers.MatcherFactory;

/**
 *
 */
public class MinDistanceMatcherFactory implements MatcherFactory {

    @Override
    public MinDistanceMatcher create(ProfileManager profileManager) {
        return new MinDistanceMatcher(profileManager);
    }

    @Override
    public String toString() {
        return getClass().getName();
    }

}
