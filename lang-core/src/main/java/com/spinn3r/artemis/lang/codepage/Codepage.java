package com.spinn3r.artemis.lang.codepage;

import com.spinn3r.artemis.lang.Lang;

import java.util.Comparator;

/**
 *
 */
public class Codepage implements Comparator<Codepage>, Comparable<Codepage> {

    protected final int start;
    protected final int end;

    private  final Lang lang;

    protected final int id;

    public Codepage(int start, int end, Lang lang, int id) {
        this.start = start;
        this.end = end;
        this.lang = lang;
        this.id = id;
    }

    @Override
    public int compareTo( Codepage o2 ) {
        return compare( this, o2 );
    }

    @Override
    public int compare(Codepage r1, Codepage r2 ) {

        //regular search

        if ( r1.start < r2.start )
            return -1;

        if ( r1.start > r2.start )
            return 1;

        return 0;

    }

    @Override
    public String toString() {
        return String.format("Codepage{start=0x%04X, end=0x%04X, lang=%s, id=%s}", start, end, lang, id);
    }

    public Lang getLang() {
        return lang;
    }

    public int getStart() {
        return start;
    }

    public int getEnd() {
        return end;
    }
}
