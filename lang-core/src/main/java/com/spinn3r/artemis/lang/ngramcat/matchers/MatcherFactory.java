package com.spinn3r.artemis.lang.ngramcat.matchers;

import com.spinn3r.artemis.lang.ngramcat.ProfileManager;

/**
 *
 */
public interface MatcherFactory {

    Matcher create(ProfileManager profileManager);

}
