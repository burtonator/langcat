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
package com.spinn3r.artemis.lang.ngramcat.ngrams;

import com.spinn3r.artemis.lang.ngramcat.ngrams.simple.NGram1;
import com.spinn3r.artemis.lang.ngramcat.ngrams.simple.NGram2;
import com.spinn3r.artemis.lang.ngramcat.ngrams.simple.NGram3;
import com.spinn3r.artemis.lang.ngramcat.ngrams.simple.NGram4;

public class NGramFactory {

    public static NGram create(int ngramLength,
                               char[] data,
                               int offset,
                               int len,
                               boolean prefixPadding) {

        switch (ngramLength) {
            case 1:
                return new NGram1(data[offset]);
            case 2:
                return new NGram2(data, offset, len, prefixPadding);
            case 3:
                return new NGram3(data, offset, len, prefixPadding);
            case 4:
                return new NGram4(data, offset, len, prefixPadding);
        }

        throw new RuntimeException("Unsupported NGram length: " + ngramLength);
    }

    private NGramFactory() {
    }
}
