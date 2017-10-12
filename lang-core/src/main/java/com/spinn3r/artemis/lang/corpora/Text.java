package com.spinn3r.artemis.lang.corpora;

/**
 *
 */
public class Text {

    public static String head(String text, int offset, int length) {

        return text.substring(offset, Math.min(offset+length, text.length()));
    }

}
