package com.spinn3r.artemis.lang.ngramcat.matchers;

import com.google.common.collect.ImmutableSet;
import com.spinn3r.artemis.lang.Lang;

/**
 *
 */
public class Match {

    private Lang lang;

    private ImmutableSet<Lang> langs;

    public Match(Lang lang, ImmutableSet<Lang> langs) {
        this.lang = lang;
        this.langs = langs;
    }

    public Lang getLang() {
        return lang;
    }

    public ImmutableSet<Lang> getLangs() {
        return langs;
    }

    @Override
    public String toString() {
        return "Match{" +
                 "lang=" + lang +
                 ", langs=" + langs +
                 '}';
    }

}
