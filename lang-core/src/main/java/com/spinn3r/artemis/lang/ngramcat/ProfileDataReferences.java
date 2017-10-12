package com.spinn3r.artemis.lang.ngramcat;

import com.google.common.base.Charsets;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Lists;
import com.google.common.io.ByteStreams;
import com.spinn3r.artemis.guava.ImmutableCollectors;
import com.spinn3r.artemis.lang.Lang;

import java.io.IOException;
import java.net.URL;
import java.util.EnumSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

import static com.spinn3r.artemis.lang.Lang.*;

/**
 * Create the list of profiles we should work.
 */
public class ProfileDataReferences {

    public static ImmutableMap<Lang,ProfileDataReference> create(ProfileDataReferences.Type type) {

        if(Type.MODERN.equals(type)) {

            List<ProfileDataReference> result = Lists.newArrayList();

            ImmutableSet<Lang> langs = EnumSet.allOf(Lang.class).stream().collect(ImmutableCollectors.toImmutableSet());

            ImmutableSet<Lang> excludedLangs = ExcludedLangs.create();

            for (Lang lang : langs) {

                if( excludedLangs.contains(lang))
                    continue;

                String path = "/corpora/" + lang.getCode().toLowerCase() + ".dat.gz";

                // FIXME: remove this size check and move it somewhere else
                // we must trim/prune on load
                URL resource = ProfileDataReferences.class.getResource(path);

                if(resource == null) {
                    // no profile for this lang
                    continue;
                }

                result.add(new ProfileDataReference(lang, path, Charsets.UTF_8.name(), lang.getCode()));
            }

            return ImmutableMap.copyOf(result.stream().collect(Collectors.toMap(ProfileDataReference::getLang,
                                                                                Function.identity(),
                                                                                ImmutableCollectors.noDuplicatesMerger(),
                                                                                LinkedHashMap::new)));

        } else if (Type.LEGACY.equals(type)) {

            // FIXME: remove this in the future to clean up things once we're confident
            // in our new languages

            List<ProfileDataReference> result = Lists.newArrayList();

            result.add(new ProfileDataReference(AR, "/train/ar-utf8.txt", "utf8", "arabic"));
            result.add(new ProfileDataReference(BG, "/train/bg-utf8.txt", "utf8", "bulgarian"));
            result.add(new ProfileDataReference(CS, "/train/cs-utf8.txt", "utf8", "czech"));
            result.add(new ProfileDataReference(DA, "/train/da-iso-8859-1.txt", "iso-8859-1", "danish"));
            result.add(new ProfileDataReference(DE, "/train/de-utf8.txt", "utf8", "german"));
            result.add(new ProfileDataReference(EL, "/train/el-utf8.txt", "utf8", "greek"));
            result.add(new ProfileDataReference(EN, "/train/en-iso-8859-1.txt", "iso-8859-1", "english"));
            result.add(new ProfileDataReference(ET, "/train/et-utf8.txt", "utf8", "estonian"));
            result.add(new ProfileDataReference(ES, "/train/es-utf8.txt", "utf8", "spanish"));
            result.add(new ProfileDataReference(FA, "/train/fa-utf8.txt", "utf8", "farsi"));
            result.add(new ProfileDataReference(FI, "/train/fi-utf8.txt", "utf8", "finnish"));
            result.add(new ProfileDataReference(FR, "/train/fr-utf8.txt", "utf8", "french"));
            result.add(new ProfileDataReference(FY, "/train/fy-utf8.txt", "utf8", "frisian"));
            result.add(new ProfileDataReference(GA, "/train/ga-utf8.txt", "utf8", "irish"));
            result.add(new ProfileDataReference(HE, "/train/he-utf8.txt", "utf8", "hebrew"));
            result.add(new ProfileDataReference(HI, "/train/hi-utf8.txt", "utf8", "hindi"));
            result.add(new ProfileDataReference(HR, "/train/hr-utf8.txt", "utf8", "croatian"));
            result.add(new ProfileDataReference(ID, "/train/id-utf8.txt", "utf8", "id"));
            result.add(new ProfileDataReference(IO, "/train/io-utf8.txt", "utf8", "ido"));
            result.add(new ProfileDataReference(IS, "/train/is-utf8.txt", "utf8", "icelandic"));
            result.add(new ProfileDataReference(IT, "/train/it-utf8.txt", "utf8", "italian"));
            result.add(new ProfileDataReference(KO, "/train/ko-utf8.txt", "utf8", "korean"));
            result.add(new ProfileDataReference(HU, "/train/hu-utf8.txt", "utf8", "hungarian"));
            result.add(new ProfileDataReference(NL, "/train/nl-iso-8859-1.txt", "iso-8859-1", "dutch"));
            result.add(new ProfileDataReference(NO, "/train/no-utf8.txt", "utf8", "norwegian"));
            result.add(new ProfileDataReference(PL, "/train/pl-utf8.txt", "utf8", "polish"));
            result.add(new ProfileDataReference(PT, "/train/pt-utf8.txt", "utf8", "portuguese"));
            result.add(new ProfileDataReference(RO, "/train/ro-utf8.txt", "utf8", "romanian"));
            result.add(new ProfileDataReference(RU, "/train/ru-utf8.txt", "utf8", "russian"));
            result.add(new ProfileDataReference(SL, "/train/sl-utf8.txt", "utf8", "slovenian"));
            result.add(new ProfileDataReference(SV, "/train/sv-iso-8859-1.txt", "iso-8859-1", "swedish"));
            result.add(new ProfileDataReference(TH, "/train/th-utf8.txt", "utf8", "thai"));
            result.add(new ProfileDataReference(UK, "/train/uk-utf8.txt", "utf8", "ukraninan"));
            result.add(new ProfileDataReference(VI, "/train/vi-utf8.txt", "utf8", "vietnamese"));

            // TODO: we need traditional and simplified chinese but should
            // probably combine them for now.
            result.add(new ProfileDataReference(ZH, "/train/zh-utf8.txt", "utf8", "chinese"));
            result.add(new ProfileDataReference(JA, "/train/ja-utf8.txt", "utf8", "japanese"));

            return ImmutableMap.copyOf(result.stream().collect(Collectors.toMap(ProfileDataReference::getLang,
                                                                                Function.identity(),
                                                                                ImmutableCollectors.noDuplicatesMerger(),
                                                                                LinkedHashMap::new)));
        }

        throw new RuntimeException("Invalid type: " + type);

    }

    public enum Type {

        LEGACY,
        MODERN

    }

}
