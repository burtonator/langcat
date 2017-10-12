package com.spinn3r.artemis.lang.ngramcat.matchers.min_distance;

import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Lists;
import com.spinn3r.artemis.lang.LangClassificationException;
import com.spinn3r.artemis.lang.ngramcat.*;
import com.spinn3r.artemis.lang.ngramcat.matchers.Match;
import com.spinn3r.artemis.lang.ngramcat.matchers.Matcher;
import com.spinn3r.artemis.lang.ngramcat.ngrams.NGram;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 *
 */
public class MinDistanceMatcher implements Matcher {

    private static final boolean SORTED = false;

    protected ProfileNGram[] profileNGramsArr = null;

    protected Profile[] profilesArr = null;

    MinDistanceMatcher(ProfileManager profileManager) {

        Map<String, Profile> profiles = profileManager.toMap();

        List<ProfileNGram> profileNGrams = new ArrayList<>();
        int profileIndex = 0;

        List<Profile> profilesList;

        if(SORTED) {

            profilesList
              = profiles
                  .values()
                  .stream()
                  .sorted(Comparator.comparing(Profile::getLang))
                  .collect(Collectors.toList());

        } else {
            profilesList = Lists.newArrayList(profiles.values());
        }


        profilesArr = profilesList.stream().toArray(Profile[]::new);

        for (Profile profile : profilesList) {

            for (NGram nGram : profile.nGrams()) {
                profileNGrams.add(new ProfileNGram(profileIndex, nGram));
            }

            ++profileIndex;

        }

        profileNGrams.sort((profileNGram0, profileNGram1) -> {
            int result = profileNGram0.nGram().compareTo(profileNGram1.nGram());

            if (result != 0) {
                return result;
            }

            // now sort by position so we return deterministic results
            return profileNGram0.nGram().position() - profileNGram1.nGram().position();

        });

        profileNGramsArr = profileNGrams.toArray(new ProfileNGram[0]);
        
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
    @Override
    public Match match(Profile documentProfile) throws LangClassificationException.UnknownLangException {

        if (profilesArr == null) {
            throw new LangClassificationException.UnknownLangException("Profiles are not initialized yet");
        }

        int[] diffSums = createDiffSums(documentProfile);

        int curIndex = 0;
        NGram[] nGrams = documentProfile.nGrams();

        // this is expensive when we have a lot of ngrams and since the profile
        // ngrams are SORTED when we change the arguments it makes sense why the
        // results of classification could change.
        for (ProfileNGram profileNGram : profileNGramsArr) {

            while (curIndex < nGrams.length) {
                NGram nGram = nGrams[curIndex];

                int result = profileNGram.nGram().compareTo(nGram);
                if (result == 0) {
                    int delta = Math.abs(nGram.position() - profileNGram.nGram().position()) - ProfileFactory.MAX_NGRAM_LIMIT;
                    diffSums[profileNGram.profileIndex()] += delta;
                }

                if (result > 0) {
                    ++curIndex;
                } else {
                    break;
                }
            }
        }

        int smallestDiffIndex = -1;
        int prevSmallestDiff = Integer.MAX_VALUE;
        int smallestDiff = Integer.MAX_VALUE;
        for (int i = 0; i < diffSums.length; ++i) {
            if (smallestDiff > diffSums[i]) {
                prevSmallestDiff = smallestDiff;
                smallestDiff = diffSums[i];
                smallestDiffIndex = i;
            }
        }

        Profile result = profilesArr[smallestDiffIndex];

        if (NGramLangClassifier.ENABLE_CONFUSION_DETECTION && prevSmallestDiff - smallestDiff < ProfileFactory.MAX_NGRAM_LIMIT * 5) {

            String msg = String.format( "Unable to determine for a certain category, probably %s, difference was too small, %s",
                                        result.getLang(), smallestDiff );

            throw new LangClassificationException.UnknownLangException(msg);

        }

        return new Match(result.getLang(), ImmutableSet.of(result.getLang()));

    }

    private int[] createDiffSums(Profile documentProfile) {

        int[] diffSums = new int[profilesArr.length];
        for (int i = 0; i < diffSums.length; ++i) {

            diffSums[i] = documentProfile.nGrams().length * ProfileFactory.MAX_NGRAM_LIMIT;

        }

        return diffSums;

    }

    public ProfileNGram[] getProfileNGramsArr() {
        return profileNGramsArr;
    }

    public Profile[] getProfilesArr() {
        return profilesArr;
    }
}
