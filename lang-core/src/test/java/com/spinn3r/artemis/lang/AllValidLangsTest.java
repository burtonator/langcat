package com.spinn3r.artemis.lang;

import com.google.common.collect.Lists;
import org.junit.Test;

import java.util.List;

/**
 *
 */
public class AllValidLangsTest {

    @Test
    public void testAllValidLangs() throws Exception {

        // make sure we have symbols for all the languages we're currently using
        // in production

        for (String lang : langs) {
            Lang.create(lang);
        }

    }

    List<String> langs = Lists.newArrayList("am",
                                            "ar",
                                            "bg",
                                            "bn",
                                            "bo",
                                            "cjk",
                                            "ckb",
                                            "cs",
                                            "cy",
                                            "da",
                                            "de",
                                            "dv",
                                            "el",
                                            "en",
                                            "es",
                                            "et",
                                            "eu",
                                            "fa",
                                            "fi",
                                            "fr",
                                            "fy",
                                            "ga",
                                            "gu",
                                            "he",
                                            "hi",
                                            "hr",
                                            "ht",
                                            "hu",
                                            "hy",
                                            "id",
                                            "in",
                                            "io",
                                            "is",
                                            "it",
                                            "iw",
                                            "ja",
                                            "ka",
                                            "km",
                                            "kn",
                                            "ko",
                                            "lo",
                                            "lt",
                                            "lv",
                                            "mal",
                                            "ml",
                                            "mn",
                                            "mr",
                                            "my",
                                            "ne",
                                            "nl",
                                            "no",
                                            "or",
                                            "pa",
                                            "pl",
                                            "ps",
                                            "pt",
                                            "ro",
                                            "ru",
                                            "sd",
                                            "si",
                                            "sl",
                                            "sr",
                                            "sv",
                                            "ta",
                                            "te",
                                            "th",
                                            "tl",
                                            "tr",
                                            "U",
                                            "ug",
                                            "uk",
                                            "ur",
                                            "vi",
                                            "zh");

}



