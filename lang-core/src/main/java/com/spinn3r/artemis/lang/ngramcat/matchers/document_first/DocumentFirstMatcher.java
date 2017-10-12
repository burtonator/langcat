package com.spinn3r.artemis.lang.ngramcat.matchers.document_first;

import com.google.common.collect.*;
import com.spinn3r.artemis.lang.Lang;
import com.spinn3r.artemis.lang.LangClassificationException;
import com.spinn3r.artemis.lang.LangSiblings;
import com.spinn3r.artemis.lang.ngramcat.*;
import com.spinn3r.artemis.lang.ngramcat.matchers.Match;
import com.spinn3r.artemis.lang.ngramcat.matchers.Matcher;
import com.spinn3r.artemis.lang.ngramcat.ngrams.NGram;

import java.util.*;
import java.util.function.Function;

/**
 *
 */
public class DocumentFirstMatcher implements Matcher {
    
    private Multimap<NGram,NGramProfileReference> index;

    private final ImmutableList<Profile> profiles;

    private ImmutableMap<Integer,IndexLabeledProfile> indexLabeledProfiles;

    private ImmutableMap<Lang,ImmutableSet<Lang>> langSiblingsMap = LangSiblings.createMap();

    DocumentFirstMatcher(ProfileManager profileManager) {

        this.profiles = profileManager.getProfiles();

        this.indexLabeledProfiles = IndexLabeledProfiles.create(profiles);

        this.index = ArrayListMultimap.create();

        for (IndexLabeledProfile indexLabeledProfile : indexLabeledProfiles.values()) {
            
            for (NGram nGram : indexLabeledProfile.getProfile().nGrams()) {

                this.index.put(nGram, new NGramProfileReference(indexLabeledProfile.getIndex(), nGram.position()));

            }

        }

        // resize this so that each key has no leaf nodes.
        this.index = createMultimapWithExpectedSize(this.index);

    }

    private static <K,V> Multimap<K,V> createMultimapWithExpectedSize(Multimap<K,V> input) {

        ArrayListMultimap<K, V> result = ArrayListMultimap.create(input.size(), 1);
        result.putAll(input);
        return result;

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

        // TODO: if we need better performance here it might make sense to split
        // the index up into sections which each has the same length.  However,
        // if we're using a hashmap we should be ok as we're still O(1)

        // create a fast index so that we can update it frequently and make
        // updates very fast.
        int[] profileScores = createProfileScores(documentProfile);

        NGram[] documentNGrams = documentProfile.nGrams();

        for (NGram documentNGram : documentNGrams) {

            Collection<NGramProfileReference> nGramProfileReferences = index.get(documentNGram);

            // TODO: this iterator shows up high in CPU usage. Maybe make them arrays.
            // hasNext and next() take all the time apparently.. about 15% of the
            // total time.
            for (NGramProfileReference nGramProfileReference : nGramProfileReferences) {

                int delta = Math.abs(documentNGram.position() - nGramProfileReference.getPosition()) - ProfileFactory.MAX_NGRAM_LIMIT;

                profileScores[nGramProfileReference.getProfile()] += delta;

            }

        }

        List<ScoredProfileReference> scoredProfileReferences = Lists.newArrayList();

        for (int profileIndex = 0; profileIndex < profileScores.length; profileIndex++) {
            scoredProfileReferences.add(new ScoredProfileReference(profileIndex, profileScores[profileIndex]));
        }

        // TODO: this takes about 5% of the total time for scoring but I don't
        // really see how we could make it faster.
        scoredProfileReferences.sort(Comparator.comparingLong(ScoredProfileReference::getScore));

        Profile result = indexLabeledProfiles.get(scoredProfileReferences.get(0).getIndex()).getProfile();

        return new Match(result.getLang(), langSiblingsMap.get(result.getLang()));

    }

    private int[] createProfileScores(Profile documentProfile) {

        int[] profileScores = new int[profiles.size()];

        for (IndexLabeledProfile indexLabeledProfile : indexLabeledProfiles.values()) {

            // TODO: I don't know why this is setup as N*M as I don't think
            // it's actually N*M but I think it's the Nth triangular number.
            //
            // https://en.wikipedia.org/wiki/Triangular_number

            profileScores[indexLabeledProfile.getIndex()] = documentProfile.nGrams().length * ProfileFactory.MAX_NGRAM_LIMIT;

        }

        return profileScores;

    }

}
