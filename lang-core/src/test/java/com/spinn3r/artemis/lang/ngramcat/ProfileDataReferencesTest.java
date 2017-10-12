package com.spinn3r.artemis.lang.ngramcat;

import com.google.common.collect.ImmutableMap;
import com.spinn3r.artemis.lang.Lang;
import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.Matchers.*;

public class ProfileDataReferencesTest {

    @Test
    public void testModern() throws Exception {


        ImmutableMap<Lang, ProfileDataReference> profileDataReferences = ProfileDataReferences.create(ProfileDataReferences.Type.MODERN);

        assertFalse(profileDataReferences.containsKey(Lang.TN));

    }
    
}