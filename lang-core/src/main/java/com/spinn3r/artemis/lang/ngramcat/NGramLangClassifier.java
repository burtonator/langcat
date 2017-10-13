/*
 * Copyright 2010 "Tailrank, Inc (Spinn3r)"
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License.  You may obtain a copy
 * of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.  See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */
package com.spinn3r.artemis.lang.ngramcat;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableSet;
import com.spinn3r.artemis.lang.Lang;
import com.spinn3r.artemis.lang.LangClassification;
import com.spinn3r.artemis.lang.LangClassificationException;
import com.spinn3r.artemis.lang.LangClassifier;
import com.spinn3r.artemis.lang.ngramcat.matchers.Match;
import com.spinn3r.artemis.lang.ngramcat.matchers.Matcher;
import com.spinn3r.artemis.lang.ngramcat.matchers.MatcherFactory;
import com.spinn3r.artemis.lang.ngramcat.matchers.min_distance.MinDistanceMatcherFactory;

import java.io.IOException;
import java.util.Arrays;

public class NGramLangClassifier implements LangClassifier {

    public static boolean ENABLE_CONFUSION_DETECTION = true;

    public static int MIN_CONTENT_LENGTH = 0;

    public static MatcherFactory MATCHER_FACTORY =  new MinDistanceMatcherFactory();

    private ImmutableSet<Lang> languages = null;

    private ImmutableList<Profile> profiles = null;

    private Matcher matcher;

    private final ImmutableMap<Lang,ProfileDataReference> profileDataReferences;

    public NGramLangClassifier() {
        //FIXME: make this use modern profiles as the default.
        this(ProfileDataReferences.create(ProfileDataReferences.Type.MODERN));
    }

    public NGramLangClassifier(ImmutableMap<Lang, ProfileDataReference> profileDataReferences) {
        this.profileDataReferences = profileDataReferences;
    }

    @Override
    public void init() throws LangClassificationException.InitFailedException {

        try {
            
            ProfileManager profileManager = new ProfileManager();

            for (ProfileDataReference profileDataReference : profileDataReferences.values()) {
                profileManager.load(profileDataReference);
            }

            profiles = profileManager.getProfiles();
            languages = profileManager.getLanguages();

            matcher = MATCHER_FACTORY.create(profileManager);

        } catch (Exception e) {
            throw new LangClassificationException.InitFailedException("Unable to init lang classifier: ", e);
        }

    }

    /**
     *
     */
    @Override
    public ImmutableSet<Lang> getLanguages() {
        return languages;
    }

    @Override
    public LangClassification detect(String text) throws LangClassificationException.UnknownLangException {

        Match match = match(text);
        return new LangClassification(this, match.getLang(), match.getLangs());

    }

    public Match match(String content) throws LangClassificationException.UnknownLangException {

        return match(content.toCharArray(), 0, content.length());

    }

    public Match match(char[] content, int fromIndex, int toIndex) throws LangClassificationException.UnknownLangException {
        
        if ( content.length < MIN_CONTENT_LENGTH ) {

            throw new LangClassificationException.UnknownLangException("Less than minimum content length for classification." );
        }

        try {

            Profile documentProfile = ProfileFactory.create( content, fromIndex, toIndex );
            return match(documentProfile);

        } catch( LangClassificationException e ) {
            throw new LangClassificationException.UnknownLangException(e );
        }

    }

    /**
     * Simple minimum score distance function.  This is a simple scorer and has
     * high accuracy. However, the problem is that it can't compute a probability
     * of the resulting classification.  
     *
     * From "N-Gram-Based Text Categorization" by  William B. Cavnar and John M.
     * Trenkle:
     *
     * The bubble in Figure 2 labelled “Measure Profile Distance” is also very
     * simple. It merely takes two N-gram profiles and calculates a sim- ple
     * rank-order statistic we call the “out-of-place” measure. This measure
     * determines how far out of place an N-gram in one profile is from its
     * place in the other profile. Figure 3 gives a simple example of this
     * calculation using a few N-grams. For each N-gram in the document profile,
     * we find its counterpart in the category profile, and then calculate how
     * far out of place it is. For example, in Figure 3, the N-gram “ING” is at
     * rank 2 in the document, but at rank 5 in the cate- gory. Thus it is 3
     * ranks out of place. If an N-gram (such as “ED” in the figure) is not in
     * the category profile, it takes some maximum out-of-place value. The sum
     * of all of the out-of-place values for all N-grams is the distance measure
     * for the document from the category. We could also use other kinds of
     * statistical measures for ranked lists (such as the Wilcoxin rank sum
     * test). However, the out-of-place score provides a simple and intuitive
     * distance measure that seems to work well enough for these
     * proof-of-concept tests.
     *
     * @param documentProfile The document that we're trying to classify.
     *
     * @throws LangClassificationException.UnknownLangException
     */
    public Match match(Profile documentProfile) throws LangClassificationException.UnknownLangException {
        return matcher.match(documentProfile);
    }


    public ImmutableList<Profile> getProfiles() {
        return profiles;
    }

}
