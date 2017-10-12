package com.spinn3r.artemis.lang;

import com.google.common.collect.ImmutableSet;

import java.util.Set;

/**
 * <p>
 * LangClassifier that truncates the text used for detection to avoid performance
 * issues and out of memory errors when attempting to categorize too much text.
 * </p>
 *
 * <p>
 * Most of the language classifiers here will do just fine when truncated without
 * any loss in accuracy. Some unusual situations arise when using documents with
 * multiple languages and truncation might confuse them.
 * </p>
 *
 * <p>
 * Additionally, we fail fast if the input text is too short and would cause
 * invalid classification.
 * </p>
 */
public class LengthAwareLangClassifier implements LangClassifier {

    private int minLength;

    private int truncateLength;

    private LangClassifier delegate;

    /**
     *
     *
     * @param minLength Fail if the text we're trying to detect or <= than this value.
     * @param truncateLength Truncate the text if >= this value.
     * @param delegate The classifier to forward detect methods.
     */
    public LengthAwareLangClassifier(int minLength, int truncateLength, LangClassifier delegate) {
        this.minLength = minLength;
        this.truncateLength = truncateLength;
        this.delegate = delegate;
    }

    @Override
    public void init() throws LangClassificationException.InitFailedException {

    }

    @Override
    public LangClassification detect(String text) throws LangClassificationException.UnknownLangException {

        requireMinLength( text );

        return delegate.detect( textForClassification( text ) );

    }

    protected void requireMinLength( String text ) throws LangClassificationException.UnknownLangException {

        if ( text == null )
            throw new NullPointerException( "No input text." );

        if ( text.length() < minLength ) {

            String message =
              String.format( "Input text too short for classification (text length=%s, vs minLength=%s)",
                             text.length(), minLength );

            throw new InsufficientLengthException( message );

        }

    }

    /**
     * Compute the text for classification, optionally truncating it.
     */
    protected String textForClassification( String text ) {

        if ( text.length() > truncateLength) {
            text = text.substring( 0, truncateLength );
        }

        return text;

    }

    @Override
    public ImmutableSet<Lang> getLanguages() {
        return delegate.getLanguages();
    }

}
