package com.spinn3r.artemis.lang.ngramcat.matchers.document_first;

import com.spinn3r.artemis.lang.ngramcat.ProfileManager;
import com.spinn3r.artemis.lang.ngramcat.matchers.MatcherFactory;

/**
 *
 */
public class DocumentFirstMatcherFactory implements MatcherFactory {

    @Override
    public DocumentFirstMatcher create(ProfileManager profileManager) {
        return new DocumentFirstMatcher(profileManager);
    }

    @Override
    public String toString() {
        return getClass().getName();
    }

}

