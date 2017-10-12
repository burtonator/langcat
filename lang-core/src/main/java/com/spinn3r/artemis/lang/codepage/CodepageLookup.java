package com.spinn3r.artemis.lang.codepage;

import com.google.common.collect.ImmutableList;
import com.spinn3r.artemis.lang.Lang;
import org.jetbrains.annotations.Nullable;

/**
 * Lookup API to resolve a given char to the codepage it's within. The way we
 * implement this is to create a lookup array of values and then map each
 * character into the map. This uses about 8MB of RAM but is VERY VERY fast
 * for lookups.
 *
 */
public class CodepageLookup {

    private static final int MAX_RANGE = (int) Math.pow(2, 21);

    private final Codepage[] codepages;

    private final int[] index;

    public CodepageLookup(ImmutableList<Codepage> codepages) {

        this.codepages = new Codepage[codepages.size() + 1];
        this.index = new int[ MAX_RANGE ];

        int codepageIdx = 0;

        for (Codepage codepage : codepages) {
            this.codepages[ ++codepageIdx ] = codepage;

            for (int charPtr = codepage.getStart(); charPtr <= codepage.getEnd() ; charPtr++) {

                if(index[charPtr] != 0 && index[charPtr] != codepageIdx) {
                    Codepage existing = lookup((char)charPtr);

                    throw new RuntimeException(String.format("Index already marked at char ptr: %s(existing=%s, conflicting=%s)" , charPtr, existing, codepage));
                }

                index[charPtr] = codepageIdx;

            }

        }

    }
    
    @Nullable
    public Codepage lookup(char c) {
        return codepages[index[c]];
    }

}
