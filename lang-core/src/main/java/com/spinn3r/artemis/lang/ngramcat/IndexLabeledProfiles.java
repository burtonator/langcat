package com.spinn3r.artemis.lang.ngramcat;

import com.google.common.collect.*;

import java.util.List;
import java.util.Map;

/**
 *
 */
public class IndexLabeledProfiles {

    public static ImmutableMap<Integer,IndexLabeledProfile> create(ImmutableCollection<Profile> profiles) {

        Map<Integer,IndexLabeledProfile> result = Maps.newHashMap();

        int idx = 0;

        for (Profile profile : profiles) {

            result.put(idx, new IndexLabeledProfile(idx, profile));

            ++idx;
        }

        return ImmutableMap.copyOf(result);

    }

}
