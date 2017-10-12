package com.spinn3r.artemis.lang;

import com.google.common.collect.ImmutableSet;

import java.util.Set;

/**
 * Language classifier for detecting the lang from the given text.  Note that
 * cate
 */
public interface LangClassifier {

    /**
     * Init the lang classifier. Must be called before detect.
     */
    void init() throws LangClassificationException.InitFailedException;

    /**
     * Classify the given text and return a language.
     *
     * @param text The text to classify.  Must NOT be HTML or this will result
     *             in lower accuracy for international content since html is in
     *             english.
     *
     * @return A Lang object representing the language by the object.
     *
     * @throws LangClassificationException If there was a problem during
     *                                     classification OR no language could
     *                                     be determined.
     */
    LangClassification detect(String text) throws LangClassificationException.UnknownLangException;

    ImmutableSet<Lang> getLanguages();

    default LangClassification verify(String text, Lang lang) throws LangClassificationException.UnknownLangException, LangClassificationException.IncorrectLangException {

        LangClassification langClassification = detect(text);

        if ( ! langClassification.getCode().equals(lang.getCode())) {
            String msg = String.format("Expected lang %s but was %s", lang.getCode(), langClassification.getCode());
            throw new LangClassificationException.IncorrectLangException(msg);
        }

        return langClassification;

    }

    /**
     * Type definition to build lang lang classifiers by directive. 
     */
    enum Type {

        NGRAM,

        CODEPAGE,

        COMPOSITE

    }

}
