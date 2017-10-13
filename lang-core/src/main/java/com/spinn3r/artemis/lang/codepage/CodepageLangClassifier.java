package com.spinn3r.artemis.lang.codepage;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Sets;
import com.spinn3r.artemis.lang.Lang;
import com.spinn3r.artemis.lang.LangClassification;
import com.spinn3r.artemis.lang.LangClassificationException;
import com.spinn3r.artemis.lang.LangClassifier;

import java.util.*;

/**
 * A language categorizer that uses the unicode range of the text when classifying
 * the content.
 *
 * @Deprecated This is now deprecated as the ngram classifier is far superior.
 */
@Deprecated
public class CodepageLangClassifier implements LangClassifier {

    public List<Codepage> codepages = new ArrayList<>();

    private CodepageLookup codepageLookup;

    private Map<Lang,Codepage> registry = new HashMap<>();

    // We started here with 0.5. I think we can get away with 0.3
    private double threshold = 0.4;

    private final ImmutableList<CodepageReference> codepageReferences;

    public CodepageLangClassifier() {
        this(CodepageReferences.createDefault());
    }

    public CodepageLangClassifier(ImmutableList<CodepageReference> codepageReferences) {
        this.codepageReferences = codepageReferences;
    }

    @Override
    public LangClassification detect(String text ) throws LangClassificationException.UnknownLangException {

        // simple lookup index so that we can keep track of the codepages by
        // their unique identifier
        int[] votes = new int[codepages.size()];

        //whitespace, digits, and punctuation should NOT count towards a score
        //since ALL code pages use these.

        int misses       = 0;
        int whitespace   = 0;
        int digit        = 0;
        int punctuation  = 0;

        for( int i = 0; i < text.length(); ++i ) {

            char c = text.charAt(i);

            Codepage hit = codepageLookup.lookup(c);

            if (hit != null) {

                ++votes[ hit.id ];

            } else {

                if (Character.isWhitespace(c))
                    ++whitespace;

                if (Character.isDigit(c))
                    ++digit;

                if (isPunctuation(c))
                    ++punctuation;

                ++misses;

            }

        }

        //compute votes.....

        int winner = -1;
        int winning_vote = 0;

        for( int i = 0; i < votes.length; ++i ) {

            int current = votes[i];

            if ( current > winning_vote ) {
                winning_vote = current;
                winner = i;
            }

        }

        //ASSERT that the winner has more than 50% of the vote.

        double total = (double)text.length();
        total -= whitespace;
        total -= digit;
        total -= punctuation;

        double p = (double)winning_vote / total;

        if ( p > threshold ) {

            Lang lang = codepages.get(winner).getLang();
            String code = lang.getCode();
            return new LangClassification(this, code, lang, ImmutableSet.of(lang));

        }

        throw new LangClassificationException.UnknownLangException("Unknown language" );

    }

    @Override
    public void init() {

        for (CodepageReference codepageReference : codepageReferences) {
            initCodepage(codepageReference.getStart(), codepageReference.getEnd(), codepageReference.getLang());
        }

        //Collections.sort( codepages );

        codepageLookup = new CodepageLookup(ImmutableList.copyOf(codepages));

    }

    @Override
    public ImmutableSet<Lang> getLanguages() {

        Set<Lang> result = Sets.newTreeSet();

        for (Codepage codepage : codepages) {
            result.add(codepage.getLang());
        }

        return ImmutableSet.copyOf(result);

    }

    private void initCodepage( int start,
                               int end,
                               Lang lang ) {

        int id = codepages.size();
        Codepage codepage = new Codepage(start , end , lang, id );
        registry.put( lang, codepage );

        codepages.add( codepage );

    }

    private static boolean isPunctuation( char c ) {

        if ( c >= '!' && c <= '/' )
            return true;

        if ( c >= ':' && c <= '@' )
            return true;

        if ( c >= '[' && c <= '`' )
            return true;

        return false;

    }

}
