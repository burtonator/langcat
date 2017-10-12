package com.spinn3r.artemis.lang;

import com.google.common.collect.*;

import java.util.EnumSet;
import java.util.Map;

import static com.spinn3r.artemis.lang.Lang.*;

/**
 * There are various langauges that we support which are very similar and
 * essentially siblings/identical.  For example, Chinese, Mandarin, and Cantonese.
 * 
 */
public class LangSiblings {

    /**
     * Create a static list of language set tuples.
     */
    public static ImmutableList<ImmutableSet<Lang>> create() {
        return ImmutableList.of(ImmutableSet.of(SH, HR, BS), // Croatian langs
                                ImmutableSet.of(BH, HI), // Hindi and Bihari
                                ImmutableSet.of(NL, STQ, ZEA),
                                ImmutableSet.of(AR, ARZ),
                                ImmutableSet.of(ES, AST, EXT), // spanish and related dialects
                                ImmutableSet.of(DE, PFL, ALS, BAR, NDS, LB),
                                ImmutableSet.of(ID, MS, MIN, JV), // indonesian and malay are related.  ID is the standard register of MS.
                                ImmutableSet.of(NN, NO),  // dialects of norwegian
                                ImmutableSet.of(IT, CO, VEC, LIJ, SC), // dialects of italian
                                ImmutableSet.of(FA, MZN),
                                ImmutableSet.of(ZH, WUU), // chinese dialects
                                ImmutableSet.of(TL, BCL, CEB, ILO),
                                ImmutableSet.of(UR, PA, PNB)
                                );
    }

    public static ImmutableMap<Lang,ImmutableSet<Lang>> createMap() {

        ImmutableList<ImmutableSet<Lang>> langSiblingSets = create();

        Map<Lang,ImmutableSet<Lang>> result = Maps.newHashMap();

        for (Lang lang : EnumSet.allOf(Lang.class)) {
            result.put(lang, ImmutableSet.of(lang));
        }

        for (ImmutableSet<Lang> langSiblingSet : langSiblingSets) {

            for (Lang lang : langSiblingSet) {
                result.put(lang, langSiblingSet);
            }

        }

        return ImmutableMap.copyOf(result);

    }

}
