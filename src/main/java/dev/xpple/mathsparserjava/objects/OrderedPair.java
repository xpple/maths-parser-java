package dev.xpple.mathsparserjava.objects;

import java.util.ArrayList;
import java.util.List;

public record OrderedPair(MathsObject left, MathsObject right) implements MathsObject {
    @Override
    public MathsSet toMathsSet() {
        List<MathsObject> elements = new ArrayList<>();
        List<MathsObject> leftElements = new ArrayList<>();
        List<MathsObject> rightElements = new ArrayList<>();
        leftElements.add(this.left);
        rightElements.add(this.left);
        rightElements.add(this.right);
        elements.add(new MathsSet(leftElements));
        elements.add(new MathsSet(rightElements));
        return new MathsSet(elements);
    }

    @Override
    public String toString() {
        return '(' + this.left.toString() + ',' + this.right.toString() + ')';
    }

    @Override
    public boolean equals(Object other) {
        if (other instanceof OrderedPair otherOrderedPair) {
            return this.left.equals(otherOrderedPair.left) && this.right.equals(otherOrderedPair.right);
        }
        if (other instanceof NaturalNumber) {
            return false;
        }
        if (other instanceof MathsObject mathsObject) {
            return this.toMathsSet().equals(mathsObject.toMathsSet());
        }
        return false;
    }
}
