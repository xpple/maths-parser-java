package dev.xpple.mathsparserjava.objects;

import java.util.List;

public record MathsSet(List<MathsObject> elements) implements MathsObject {
    public OrderedPair toOrderedPair() {
        if (this.elements.size() != 2) {
            return null;
        }

        MathsSet leftSet = this.elements.get(0).toMathsSet();
        MathsSet rightSet = this.elements.get(1).toMathsSet();

        MathsObject leftElement;
        MathsObject rightElementOne;
        MathsObject rightElementTwo;

        if (leftSet.elements.size() == 1 && rightSet.elements.size() == 2) {
            leftElement = leftSet.elements.get(0);
            rightElementOne = rightSet.elements.get(0);
            rightElementTwo = rightSet.elements.get(1);
        } else if (leftSet.elements.size() == 2 && rightSet.elements.size() == 1) {
            leftElement = rightSet.elements.get(0);
            rightElementOne = leftSet.elements.get(0);
            rightElementTwo = leftSet.elements.get(1);
        } else {
            return null;
        }

        if (leftElement.equals(rightElementOne)) {
            return new OrderedPair(leftElement, rightElementTwo);
        }
        if (leftElement.equals(rightElementTwo)) {
            return new OrderedPair(leftElement, rightElementOne);
        }
        return null;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append('{');
        if (!this.elements.isEmpty()) {
            final int size = this.elements.size();
            for (int i = 0; i < size - 1; i++) {
                builder.append(this.elements.get(i));
                builder.append(',');
            }
            builder.append(this.elements.get(size - 1));
        }
        builder.append('}');
        return builder.toString();
    }

    @Override
    public boolean equals(Object other) {
        if (other instanceof MathsSet otherMathsSet) {
            if (!this.elements.containsAll(otherMathsSet.elements)) {
                return false;
            }
            if (!otherMathsSet.elements.containsAll(this.elements)) {
                return false;
            }
            return true;
        }
        if (other instanceof MathsObject mathsObject) {
            return this.equals(mathsObject.toMathsSet());
        }
        return false;
    }

    @Override
    public MathsSet toMathsSet() {
        return this;
    }
}
