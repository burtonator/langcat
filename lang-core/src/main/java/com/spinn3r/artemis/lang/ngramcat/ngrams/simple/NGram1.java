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
package com.spinn3r.artemis.lang.ngramcat.ngrams.simple;

import com.spinn3r.artemis.lang.ngramcat.ngrams.AbstractNGram;
import com.spinn3r.artemis.lang.ngramcat.ngrams.NGram;

public class NGram1 extends AbstractNGram {

    private char c1;

    public NGram1(char c1) {
        this.c1 = c1;
    }

    public int length() {
        return 1;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        NGram1 ng = (NGram1) o;
        return c1 == ng.c1;
    }

    @Override
    public int hashCode() {
        return (int) c1;
    }

    public int compareTo(NGram o) {
        if (length() != o.length()) {
            return length() - o.length();
        }
        NGram1 nGram = (NGram1) o;
        return (int) c1 - (int) nGram.c1;
    }

    @Override
    public String toString() {
        return String.valueOf(c1);
    }

}
