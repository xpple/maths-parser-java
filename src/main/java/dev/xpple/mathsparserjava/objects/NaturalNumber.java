package dev.xpple.mathsparserjava.objects;

import java.util.ArrayList;

public record NaturalNumber(int naturalNumber) implements MathsObject {
    @Override
    public MathsSet toMathsSet() {
        if (this.naturalNumber == 0) {
            return new MathsSet(new ArrayList<>());
        }
        MathsSet predecessor = new NaturalNumber(this.naturalNumber - 1).toMathsSet();
        predecessor.elements().add(predecessor);
        return predecessor;
    }

    @Override
    public String toString() {
        return String.valueOf(this.naturalNumber);
    }

    @Override
    public boolean equals(Object other) {
        if (other instanceof NaturalNumber otherNaturalNumber) {
            return this.naturalNumber == otherNaturalNumber.naturalNumber;
        }
        if (other instanceof OrderedPair) {
            return false;
        }
        if (other instanceof MathsObject mathsObject) {
            return this.toMathsSet().equals(mathsObject.toMathsSet());
        }
        return false;
    }
}
