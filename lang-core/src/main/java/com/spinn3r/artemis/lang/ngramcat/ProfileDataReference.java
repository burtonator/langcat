package com.spinn3r.artemis.lang.ngramcat;

import com.spinn3r.artemis.lang.Lang;

/**
 *
 */
public class ProfileDataReference {

    private final Lang lang;

    private final String path;

    private final String encoding;

    private final String language;

    public ProfileDataReference(Lang lang, String path, String encoding, String language) {
        this.lang = lang;
        this.path = path;
        this.encoding = encoding;
        this.language = language;
    }

    public Lang getLang() {
        return lang;
    }

    public String getPath() {
        return path;
    }

    public String getEncoding() {
        return encoding;
    }

    public String getLanguage() {
        return language;
    }

    @Override
    public String toString() {
        return "ProfileDataReference{" +
                 "lang=" + lang +
                 ", path='" + path + '\'' +
                 ", encoding='" + encoding + '\'' +
                 ", language='" + language + '\'' +
                 '}';
    }

}
