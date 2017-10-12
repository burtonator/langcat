package com.spinn3r.artemis.lang.ngramcat;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Lists;
import com.spinn3r.artemis.guava.ImmutableCollectors;
import com.spinn3r.artemis.lang.Lang;
import com.spinn3r.artemis.lang.LangClassificationException;
import com.spinn3r.artemis.lang.ngramcat.matchers.min_distance.MinDistanceMatcher;
import com.spinn3r.artemis.lang.ngramcat.matchers.min_distance.MinDistanceMatcherFactory;
import com.spinn3r.artemis.util.misc.Files;
import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.*;

public class ProfileManagerTest {

    MinDistanceMatcherFactory matcherFactory = new MinDistanceMatcherFactory();

    @Test
    public void testDeterministicOrdering() throws Exception {

        ProfileManager profileManager = new ProfileManager();

        profileManager.load("ar", "/train/ar-utf8.txt", "utf8", "arabic");
        profileManager.load("bg", "/train/bg-utf8.txt", "utf8", "bulgarian");

        ProfileNGram[] profileNGrams0 = matcherFactory.create(profileManager).getProfileNGramsArr();

        //System.out.printf("%s\n",NGrams.format(Lists.newArrayList(profileNGrams0).stream().map(ProfileNGram::nGram).collect(ImmutableCollectors.toImmutableList())) );

        // now load them in opposite order..
        profileManager = new ProfileManager();

        profileManager.load("bg", "/train/bg-utf8.txt", "utf8", "bulgarian");
        profileManager.load("ar", "/train/ar-utf8.txt", "utf8", "arabic");

        ProfileNGram[] profileNGrams1 = matcherFactory.create(profileManager).getProfileNGramsArr();

        assertEquals(format(profileNGrams0), format(profileNGrams1));

    }

    @Test
    public void testSorted() throws Exception {

        // FIXME: ok . this is the bug. The problem is that this function isn't
        // deterministic and the ordering of the languages actually matters and
        // we're not returning the proper index.

        ProfileManager profileManager = createProfileManagerWithAllLangs();

        ProfileNGram[] profileNGrams0 = matcherFactory.create(profileManager).getProfileNGramsArr();

        // now load them in opposite order..
        profileManager = createProfileManagerWithAllLangs();

        ProfileNGram[] profileNGrams1 = matcherFactory.create(profileManager).getProfileNGramsArr();

        //Files.writeTo("profile0.txt", format(profileNGrams0));
        //Files.writeTo("profile1.txt", format(profileNGrams1));

        assertEquals(format(profileNGrams0), format(profileNGrams1));

    }

    protected ProfileManager createProfileManagerWithAllLangs() throws IOException, LangClassificationException {

        ProfileManager profileManager = new ProfileManager();

        ImmutableMap<Lang, ProfileDataReference> profileDataReferences = ProfileDataReferences.create(ProfileDataReferences.Type.LEGACY);

        for (ProfileDataReference profileDataReference : profileDataReferences.values()) {
            profileManager.load(profileDataReference);
        }

        return profileManager;

    }

    protected String format(ProfileNGram[] profileNGrams) {
        return NGrams.format(Lists.newArrayList(profileNGrams).stream().map(ProfileNGram::nGram).collect(ImmutableCollectors.toImmutableList()));
    }

}