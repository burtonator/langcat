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

public class NGram3 extends AbstractNGram {
    private char c1;
    private char c2;
    private char c3;

    public NGram3(char[] data, int offset, int len, boolean prefixPadding) {
        int paddingLen = length() - len;
        if (prefixPadding) {
            this.c1 = paddingLen > 0 ? NGram.PADDING_CHAR : data[offset];
            this.c2 = paddingLen > 1 ? NGram.PADDING_CHAR : data[offset + 1 - paddingLen];
            this.c3 = data[offset + 2 - paddingLen];
        } else {
            this.c1 = data[offset];
            this.c2 = paddingLen > 1 ? NGram.PADDING_CHAR : data[offset + 1];
            this.c3 = paddingLen > 0 ? NGram.PADDING_CHAR : data[offset + 2];
        }
    }

    public int length() {
        return 3;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        NGram3 ng = (NGram3) o;
        return c1 == ng.c1 && c2 == ng.c2 && c3 == ng.c3;
    }

    @Override
    public int hashCode() {
        int result = (int) c1;
        result = 31 * result + (int) c2;
        result = 31 * result + (int) c3;
        return result;
    }

    public int compareTo(NGram o) {
        if (length() != o.length()) {
            return length() - o.length();
        }
        NGram3 nGram = (NGram3) o;
        if (c1 != nGram.c1) {
            return (int) c1 - (int) nGram.c1;
        }
        if (c2 != nGram.c2) {
            return (int) c2 - (int) nGram.c2;
        }
        if (c3 != nGram.c3) {
            return (int) c3 - (int) nGram.c3;
        }
        return 0;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder(length());
        builder.append(c1);
        builder.append(c2);
        builder.append(c3);
        return builder.toString();
    }
}
