package com.spinn3r.artemis.lang.iso639;

/**
 * Represents an ISO lang code.
 *
 * Data taken from:
 *
 * http://data.okfn.org/data/core/language-codes
 *
 * And more specifically:
 *
 * http://data.okfn.org/data/core/language-codes/r/language-codes-full.csv
 *
 * From: https://en.wikipedia.org/wiki/ISO_639-2
 *
 * "While most languages are given one code by the standard, twenty of the
 * languages described have two three-letter codes, a "bibliographic" code (ISO
 * 639-2/B), which is derived from the English name for the language and was a
 * necessary legacy feature, and a "terminological" code (ISO 639-2/T), which is
 * derived from the native name for the language and resembles the language's
 * two-letter code in ISO 639-1. (There were originally 22 B codes; scc and scr
 * are now deprecated.) In general the T codes are favored; ISO 639-3 uses ISO
 * 639-2/T. However, ISO 15924 derives its codes when possible from ISO 639-2/B."
 *

 */
public class ISOLangCode {

    private final String alpha3;

    private final String alpha3B;

    private final String alpha3T;

    private final String alpha2;

    private final String english;

    public ISOLangCode(String alpha3, String alpha3B, String alpha3T, String alpha2, String english) {
        this.alpha3 = alpha3;
        this.alpha3B = alpha3B;
        this.alpha3T = alpha3T;
        this.alpha2 = alpha2;
        this.english = english;
    }

    public String getAlpha3() {
        return alpha3;
    }

    public String getAlpha3B() {
        return alpha3B;
    }

    public String getAlpha3T() {
        return alpha3T;
    }

    public String getAlpha2() {
        return alpha2;
    }

    public String getEnglish() {
        return english;
    }

    @Override
    public String toString() {
        return "ISOLangCode{" +
                 "alpha3='" + alpha3 + '\'' +
                 ", alpha3B='" + alpha3B + '\'' +
                 ", alpha3T='" + alpha3T + '\'' +
                 ", alpha2='" + alpha2 + '\'' +
                 ", english='" + english + '\'' +
                 '}';
    }

}
