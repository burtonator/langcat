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


import com.google.common.collect.ImmutableSet;
import com.spinn3r.artemis.lang.Lang;
import com.spinn3r.artemis.lang.LangClassificationException;
import com.spinn3r.artemis.lang.ngramcat.ngrams.AbstractNGram;
import com.spinn3r.artemis.lang.ngramcat.ngrams.NGram;
import com.spinn3r.artemis.lang.ngramcat.ngrams.NGramFactory;

import java.util.*;

import static com.spinn3r.artemis.lang.Lang.*;
import static com.spinn3r.artemis.lang.ngramcat.NGramType.*;


public class ProfileFactory {

    public static int TRAIN_PROFILE_LENGTH = 2_000_000;

    public static int MAX_NGRAM_LIMIT = 1_000;

    public static int DEFAULT_NGRAM_LIMIT = 1_000;

    public static int MAX_PROFILE_LENGTH = 2_000_000;

    public static ImmutableSet<NGramType> NGRAM_TYPES = ImmutableSet.of(BIGRAM, TRIGRAM);

    public static boolean ENABLE_LARGE_PROFILE_TERMINATION = true;

    private static final boolean[] DELIM_CODES = createDelimCodes();

    private static final String DELIMITERS = "\n\r\t !\"#$%&'()*+,-./0123456789:;<=>?@[\\]^_`{|}~";

    public static ImmutableSet<Lang> MAX_PROFILE_LANGS = ImmutableSet.of(U, ZH, JA);

    private static boolean[] createDelimCodes() {

        boolean[] result = new boolean[256];

        for (int i = 0; i < DELIMITERS.length(); ++i) {
            result[(int) DELIMITERS.charAt(i)] = true;
        }

        return result;

    }

    public static Profile create(char[] content, int fromIndex, int toIndex)
            throws LangClassificationException {

        ProfileDataReference testProfileDataReference = new ProfileDataReference(U, null, null, null);
        return create(testProfileDataReference, false, content, fromIndex, toIndex);

    }

    public static Profile create(ProfileDataReference profileDataReference,
                                  boolean categoryProfile,
                                  char[] content )
            throws LangClassificationException {

        return create( profileDataReference,
                       categoryProfile,
                       content,
                       0,
                       content.length );

    }

    public static Profile create( ProfileDataReference profileDataReference,
                                  boolean categoryProfile,
                                  char[] content,
                                  int fromIndex,
                                  int toIndex)
            throws LangClassificationException {

        NGram[] nGrams = read(profileDataReference.getLang(), content, fromIndex, toIndex );

        return new ProfileImpl(profileDataReference, categoryProfile, nGrams);

    }

    public static NGram[] read( Lang lang, char[] content, int fromIndex, int toIndex) throws LangClassificationException {

        Map<NGram, NGram> ngrams = readAsIndex( content, fromIndex, toIndex );

        return calculatePositionsAndCut(lang, ngrams);
    }

    protected static Map<NGram, NGram> readAsIndex(char[] content, int fromIndex, int toIndex) throws LangClassificationException {
        Map<NGram, NGram> ngrams = new HashMap<>(10000);

        int lastDelimIndex = -1;
        for (int i = fromIndex; i < toIndex; ++i) {
            char c = content[i];
            if (c < DELIM_CODES.length && DELIM_CODES[(int) c]) {
                if (lastDelimIndex + 1 < i) {
                    int startWord = lastDelimIndex + 1;
                    processWord(content, startWord, i, ngrams);
                }
                lastDelimIndex = i;
            }
        }
        if (lastDelimIndex + 1 < toIndex) {
            processWord(content, lastDelimIndex + 1, toIndex, ngrams);
        }
        return ngrams;
    }

    protected static NGram[] calculatePositionsAndCut(Lang lang, Map<NGram, NGram> ngrams) {
        List<NGram> nGramList = new ArrayList<>(ngrams.values());
        Collections.sort(nGramList, NGRAM_POSITION_COMPARATOR);

        int ngramLimit = getNgramLimit(lang);

        NGram[] result = new NGram[Math.min(nGramList.size(), ngramLimit)];
        int position = 0;
        for (NGram nGram : nGramList) {
            if (position < ngramLimit) {
                ((AbstractNGram) nGram).position(position);
                result[position] = nGram;
                ++position;
            } else {
                break;
            }
        }
        return result;
    }

    private static int getNgramLimit(Lang lang) {

        if (MAX_PROFILE_LANGS.contains(lang)) {
            return MAX_NGRAM_LIMIT;
        }

        return DEFAULT_NGRAM_LIMIT;

    }

    private static void processWord(char[] content,
                                    int fromIndex,
                                    int toIndex,
                                    Map<NGram, NGram> ngrams)
            throws LangClassificationException {

        for (NGramType nGramType : NGRAM_TYPES) {
            processWord(content, fromIndex, toIndex, nGramType.getLength(), ngrams);
        }

    }

    private static void processWord(char[] content,
                                    int fromIndex,
                                    int toIndex,
                                    int width,
                                    Map<NGram, NGram> ngrams)

            throws LangClassificationException {

        int len = toIndex - fromIndex;
        for (int i = 0; i < len; ++i) {

            //adjust the offset where we need to start reading the nGram to.
            int j = width - 1;

            if (i >= j)
                j = width;

            for (; j <= width; ++j) {

                int end = i + j;

                if (i > 0) {
                    end = i + width;
                }

                if (end > len) {
                    end = len;
                }

                if (i == end) {
                    end = len;
                }

                boolean prefixPadding = (end != len);
                NGram nGram = NGramFactory.create(width, content, fromIndex + i, end - i, prefixPadding);

                NGram nGramFromMap = ngrams.get(nGram);
                if (nGramFromMap != null) {
                    ((AbstractNGram) nGramFromMap).incCount();
                } else {
                    ngrams.put(nGram, nGram);
                }

                if (ENABLE_LARGE_PROFILE_TERMINATION && ngrams.size() > MAX_PROFILE_LENGTH) {

                    String message =
                      String.format( "Too many nGrams to classify without wasted memory.  Max is: %s",
                                     MAX_PROFILE_LENGTH );

                    throw new LangClassificationException.LargeProfileException( message);

                }

                //determine when we're done in this loop.  When end - i == width
                //when we're done.

                if (end + i >= width)
                    break;
            }

        }

    }

    private ProfileFactory() {

    }

    private static final Comparator<NGram> NGRAM_POSITION_COMPARATOR =
      (o1, o2) -> {

          int result = o2.position() - o1.position();

          if ( result != 0 ) {
              return result;
          }

          return o1.compareTo( o2 );

      };

}
