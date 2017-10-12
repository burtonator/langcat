package com.spinn3r.artemis.lang.ngramcat.matchers;

import com.spinn3r.artemis.lang.LangClassificationException;
import com.spinn3r.artemis.lang.ngramcat.Profile;

/**
 * A matcher is an algorithm implementation that takes a profile document and
 * performs language classification. Some language classifiers have features that
 * other classifiers are missing, specifically, the ability to determine
 * probability.
 */
public interface Matcher {

    /**
     * Function to take a given document and return its language profile.
     *
     * @param documentProfile The document that we're trying to classify.
     *
     * @throws LangClassificationException.UnknownLangException When we are unable to classify a language.
     */
    Match match(Profile documentProfile) throws LangClassificationException.UnknownLangException;

}
