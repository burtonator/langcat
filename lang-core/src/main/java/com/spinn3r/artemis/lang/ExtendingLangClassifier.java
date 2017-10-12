package com.spinn3r.artemis.lang;

import com.google.inject.Inject;
import com.google.inject.Provider;

/**
 * A LangClassifier that allows us to keep extending the amount of text we're
 * working with so that we can get the right amount of text to work with.
 *
 * This is better than outright failing and returning an unknown language..
 */
public class ExtendingLangClassifier {

    private final Provider<LangClassifier> langClassifierProvider;

    @Inject
    public ExtendingLangClassifier(Provider<LangClassifier> langClassifierProvider) {
        this.langClassifierProvider = langClassifierProvider;
    }

    //public Lang detect( String text, )

}



