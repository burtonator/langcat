package com.spinn3r.artemis.lang;

import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Lists;
import com.google.common.collect.Sets;

import java.util.List;
import java.util.Set;

/**
 * A composite lang classifier to taking a set of input LangClassifiers, and we
 * keep testing until one is found.
 *
 */
public class CompositeLangClassifier implements LangClassifier {

    List<LangClassifier> classifiers;

    public CompositeLangClassifier(LangClassifier... classifiers) {
        this.classifiers = Lists.newArrayList( classifiers );
    }

    @Override
    public void init() throws LangClassificationException.InitFailedException {

    }

    @Override
    public LangClassification detect(String text) throws LangClassificationException.UnknownLangException {

        for( LangClassifier current : classifiers ) {

            try {
                return current.detect( text );
            } catch ( LangClassificationException.UnknownLangException e ) {
                // this is acceptable... we need to keep trying.
            }

        }

        throw new LangClassificationException.UnknownLangException("Tried all classifiers with no success" );

    }

    @Override
    public ImmutableSet<Lang> getLanguages() {

        Set<Lang> result = Sets.newTreeSet();

        for (LangClassifier classifier : classifiers) {
            result.addAll( classifier.getLanguages() );
        }

        return ImmutableSet.copyOf(result);

    }
}
