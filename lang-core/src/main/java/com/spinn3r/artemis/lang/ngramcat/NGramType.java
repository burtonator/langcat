package com.spinn3r.artemis.lang.ngramcat;

/**
 *
 */
public enum NGramType {
    
    UNIGRAM(1), BIGRAM(2), TRIGRAM(3), QUADGRAM(4);

    private int length;

    NGramType(int length) {
        this.length = length;
    }

    public int getLength() {
        return length;
    }

}
