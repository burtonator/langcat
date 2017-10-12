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

public abstract class AbstractNGram implements NGram {

    // optimization:
    // we use the same field for counting and then for assigned position
    // to save a little of memory
    private int position;

    public int incCount() {
        return ++position;
    }

    public void position(int position) {
        this.position = position;
    }

    public int position() {
        return position;
    }

}
