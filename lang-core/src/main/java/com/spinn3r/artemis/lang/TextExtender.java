package com.spinn3r.artemis.lang;

/**
 * A text extender is an interface providing more content should it be necessary
 * for advanced text classification.
 */
public interface TextExtender {

    /**
     * Extend the amount of text we're using or return null.
     */
    public String extend();

}
