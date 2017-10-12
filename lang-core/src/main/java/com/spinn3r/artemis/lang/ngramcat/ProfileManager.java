package com.spinn3r.artemis.lang.ngramcat;

import com.google.common.base.Charsets;
import com.google.common.collect.*;
import com.google.common.io.ByteStreams;
import com.spinn3r.artemis.guava.ImmutableCollectors;
import com.spinn3r.artemis.lang.Lang;
import com.spinn3r.artemis.lang.LangClassificationException;
import com.spinn3r.artemis.lang.corpora.CorporaType;
import com.spinn3r.artemis.lang.corpora.CorporaUtils;

import java.io.IOException;
import java.util.*;

/**
 *
 */
public class ProfileManager {

    private Map<String,Profile> index = Maps.newHashMap();

    protected void load(String lang, String path, String encoding, String language) throws IOException, LangClassificationException {
        load(new ProfileDataReference(Lang.create(lang), path, encoding, language));
    }

    protected void load(ProfileDataReference profileDataReference) throws IOException, LangClassificationException {

        String content = CorporaUtils.toText(profileDataReference.getPath(), profileDataReference.getEncoding(), CorporaType.TRAIN);

        if(content.getBytes(Charsets.UTF_8).length < ProfileFactory.TRAIN_PROFILE_LENGTH)
            return;

        char[] chars = content.toCharArray();

        Profile profile = ProfileFactory.create(profileDataReference, true,chars);

        index.put( profileDataReference.getLang().getCode(), profile );

    }

    public ImmutableMap<String,Profile> toMap() {
        return ImmutableMap.copyOf(index);
    }

    public ImmutableSet<Lang> getLanguages() {
        return index.keySet().stream().map(Lang::create).collect(ImmutableCollectors.toImmutableSet());
    }

    public ImmutableList<Profile> getProfiles() {
        return ImmutableList.copyOf(index.values());
    }

}
