package com.spinn3r.artemis.lang.ngramcat.matchers.document_first;

/**
 *
 */
public class ScoredProfileReference {

    private final int index;

    private final long score;

    public ScoredProfileReference(int index, long score) {
        this.index = index;
        this.score = score;
    }

    public int getIndex() {
        return index;
    }

    public long getScore() {
        return score;
    }

    @Override
    public String toString() {
        return "ScoredProfileReference{" +
                 "index=" + index +
                 ", score=" + score +
                 '}';
    }

}
