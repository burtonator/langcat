package com.spinn3r.artemis.lang.ngramcat;

import com.google.common.collect.ImmutableSet;
import com.spinn3r.artemis.lang.Lang;

import static com.spinn3r.artemis.lang.Lang.*;

/**
 * Langs that are currently manually excluded due to accuracy issues.
 */
public class ExcludedLangs {

    public static ImmutableSet<Lang> create() {
        return ImmutableSet.of(PS, MR, PAM, YO, MR, SU, BPY, KM, TG, VLS, NEW, LO, WA, MY, GOM, BH, SI);
    }

}
