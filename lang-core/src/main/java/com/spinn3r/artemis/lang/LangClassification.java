package com.spinn3r.artemis.lang;

import com.google.common.annotations.VisibleForTesting;
import com.google.common.collect.ImmutableSet;

/**
 *
 */
public class LangClassification {

    private final LangClassifier langClassifier;

    private final String code;

    private final Lang lang;

    private final ImmutableSet<Lang> langs;

    public LangClassification(LangClassifier langClassifier, Lang lang, ImmutableSet<Lang> langs) {
        this(langClassifier, lang.getCode(), lang, langs);
    }

    public LangClassification(LangClassifier langClassifier, String code, Lang lang, ImmutableSet<Lang> langs) {
        this.langClassifier = langClassifier;
        this.code = code;
        this.lang = lang;
        this.langs = langs;
    }

    /**
     * Get the ISO code for this language.
     *
     * @return
     */
    public String getCode() {
        return code;
    }

    public Lang getLang() {
        return lang;
    }

    public ImmutableSet<Lang> getLangs() {
        return langs;
    }

    /**
     * Get the language classifier that classified this content.
     *
     * Primarily used for testing purposes.
     *
     */
    @VisibleForTesting
    public LangClassifier getLangClassifier() {
        return langClassifier;
    }

    @Override
    public String toString() {
        return code;
    }

}
