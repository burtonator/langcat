package com.spinn3r.artemis.lang;

import com.google.common.collect.ImmutableSet;
import com.spinn3r.artemis.lang.codepage.CodepageLangClassifier;
import com.spinn3r.artemis.lang.ngramcat.NGramLangClassifier;

import java.util.Set;

/**
 * Provide a language classifier that should work for most of our use cases.
 */
public class DefaultLangClassifier implements LangClassifier {

    private int minLength;

    private int truncateLength;

    private LangClassifier classifier;

    public DefaultLangClassifier(int minLength, int truncateLength) {
        this.minLength = minLength;
        this.truncateLength = truncateLength;
    }

    @Override
    public void init() throws LangClassificationException.InitFailedException {

        CodepageLangClassifier codepageLangClassifier = new CodepageLangClassifier();
        codepageLangClassifier.init();

        NGramLangClassifier nGramLangClassifier = new NGramLangClassifier();
        nGramLangClassifier.init();

        // now create a composite classifier from the two main classifiers
        this.classifier = new CompositeLangClassifier( codepageLangClassifier, nGramLangClassifier );

        // now wrap that in a truncating classifier.

        this.classifier = new LengthAwareLangClassifier( minLength, truncateLength, classifier );

    }

    @Override
    public LangClassification detect(String text) throws LangClassificationException.UnknownLangException {
        return classifier.detect( text );
    }

    @Override
    public ImmutableSet<Lang> getLanguages() {
        return classifier.getLanguages();
    }

}
