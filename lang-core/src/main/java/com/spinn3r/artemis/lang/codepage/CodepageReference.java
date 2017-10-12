package com.spinn3r.artemis.lang.codepage;

import com.google.common.base.Preconditions;
import com.spinn3r.artemis.lang.Lang;

import static com.google.common.base.Preconditions.checkArgument;

/**
 * Reference to a unicode range and a langauge.
 */
public class CodepageReference {

    private final int start;

    private final int end;

    private final Lang lang;

    public CodepageReference(int start, int end, Lang lang) {
        checkArgument(start < end, "Start is not less than end");
        this.start = start;
        this.end = end;
        this.lang = lang;
    }

    public int getStart() {
        return start;
    }

    public int getEnd() {
        return end;
    }

    public Lang getLang() {
        return lang;
    }

    @Override
    public String toString() {
        return "CodepageReference{" +
                 "start=" + start +
                 ", end=" + end +
                 ", lang=" + lang +
                 '}';
    }

}
