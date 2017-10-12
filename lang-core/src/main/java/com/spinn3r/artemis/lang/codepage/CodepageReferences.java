package com.spinn3r.artemis.lang.codepage;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.Lists;
import com.spinn3r.artemis.guava.ImmutableCollectors;

import java.util.List;

import static com.spinn3r.artemis.lang.Lang.*;

/**
 *
 */
public class CodepageReferences {

    public static boolean ENABLE_CJK = true;
    public static boolean ENABLE_JA = true;

    public static ImmutableList<CodepageReference> create() {

        List<CodepageReference> result = Lists.newArrayList();

        result.add(new CodepageReference( 0x0530  , 0x058F  , HY  ));
        result.add(new CodepageReference( 0x0590  , 0x05FF  , HE  ));

        result.add(new CodepageReference( 0x0A80  , 0x0AFF  , GU  )); // Gujarati
        result.add(new CodepageReference( 0x0B00  , 0x0B7F  , OR  )); // Oriya
        result.add(new CodepageReference( 0x0B80  , 0x0BFF  , TA  )); // Tamil
        result.add(new CodepageReference( 0x0C00  , 0x0C7F  , TE  )); // Telugu
        result.add(new CodepageReference( 0x0C80  , 0x0CFF  , KN  )); // Kannada
        result.add(new CodepageReference( 0x0D00  , 0x0D7F  , MAL )); // Malyalam
        result.add(new CodepageReference( 0x0E00  , 0x0E7F  , TH  ));
        result.add(new CodepageReference( 0x0E80  , 0x0EFF  , LO  ));
        result.add(new CodepageReference( 0x0F00  , 0x0FFF  , BO  ));
        result.add(new CodepageReference( 0x1000  , 0x109F  , MY  ));
        result.add(new CodepageReference( 0x10A0  , 0x10FF  , KA  ));
        result.add(new CodepageReference( 0x1100  , 0x11FF  , KO  )); // hangul jamo
        result.add(new CodepageReference( 0x1700  , 0x171F  , TL  ));
        result.add(new CodepageReference( 0x2D00  , 0x2D2F  , KA  ));
        result.add(new CodepageReference( 0x3130  , 0x318F  , KO  )); // hangul compat jamo
        result.add(new CodepageReference( 0xAC00  , 0xD7AF  , KO  )); // hangul
        result.add(new CodepageReference( 0xFF00  , 0xFFEF  , KO  )); // Added after 1/20/2009:

        if(ENABLE_JA) {
            result.add(new CodepageReference(0x3000, 0x303F, JA));
            result.add(new CodepageReference(0x3040, 0x309F, JA)); // hiragana
            result.add(new CodepageReference(0x30A0, 0x30FF, JA)); // katakana
            result.add(new CodepageReference(0x31F0, 0x31FF, JA));
        }

        if(ENABLE_CJK) {
            result.add(new CodepageReference( 0x2E80  , 0x2EFF  , CJK )); // Added after 1/20/2009: CJK radicals (http://unicode.org/charts/PDF/U2E80.pdf)
            result.add(new CodepageReference( 0x2F00  , 0x2FDF  , CJK )); // Added after 1/20/2009: Kangxi Radicals
            result.add(new CodepageReference( 0x3190  , 0x319F  , CJK )); // Added after 1/20/2009:
            result.add(new CodepageReference( 0x31C0  , 0x31EF  , CJK )); // Added after 1/20/2009:
            result.add(new CodepageReference( 0x3200  , 0x32FF  , CJK ));
            result.add(new CodepageReference( 0x3300  , 0x33FF  , CJK ));
            result.add(new CodepageReference( 0x3400  , 0x4DBF  , CJK )); // CJK Unified Ideographs Extension A (http://unicode.org/charts/PDF/U4E00.pdf)
            result.add(new CodepageReference( 0x4E00  , 0x9FFF  , CJK )); // Unified CJK Ideographs (http://unicode.org/charts/PDF/U4E00.pdf)
            result.add(new CodepageReference( 0xFE30  , 0xFE4F  , CJK ));
            result.add(new CodepageReference( 0x20000 , 0x2A6DF , CJK )); // Added after 1/20/2009: This is East Asian script so it must be classified at CJK
            result.add(new CodepageReference( 0x2F800 , 0x2FA1F , CJK )); // Added after 1/20/2009: CJK Compatibility Ideographs Supplement
            result.add(new CodepageReference( 0xF900  , 0xFAFF  , CJK )); // Compatibility Ideographs http://unicode.org/charts/PDF/UF900.pdf
        }

        return ImmutableList.copyOf(result);
        
    }

    public static ImmutableList<CodepageReference> createDefault() {
        return create().stream().filter(current -> ! current.getLang().equals(RU)).collect(ImmutableCollectors.toImmutableList());
    }

}
