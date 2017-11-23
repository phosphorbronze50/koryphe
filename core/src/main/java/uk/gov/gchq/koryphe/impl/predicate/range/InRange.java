/*
 * Copyright 2017 Crown Copyright
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package uk.gov.gchq.koryphe.impl.predicate.range;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import uk.gov.gchq.koryphe.predicate.KoryphePredicate;

/**
 * <p>
 * An <code>InRange</code> is a {@link java.util.function.Predicate}
 * that tests if a {@link Comparable} is within a provided range [start, end].
 * By default the range is inclusive, you can toggle this using the startInclusive
 * and endInclusive booleans.
 * </p>
 * <p>
 * If the start is not set then this will be treated as unbounded.
 * Similarly with the end.
 * </p>
 * <p>
 * If the test value is null then the predicate will return false.
 * </p>
 * <p>
 * This range predicate takes a single value to test, if you want to test
 * a startValue and endValue lies within a range then you can use the
 * {@link InRangeDual} predicate.
 * </p>
 *
 * @see Builder
 */
public class InRange<T extends Comparable<T>> extends KoryphePredicate<T> {
    private final InRangeDual<T> predicate;

    @JsonCreator
    public InRange(@JsonProperty("start") @JsonTypeInfo(use = JsonTypeInfo.Id.CLASS, include = JsonTypeInfo.As.WRAPPER_OBJECT) final T start,
                   @JsonProperty("end") @JsonTypeInfo(use = JsonTypeInfo.Id.CLASS, include = JsonTypeInfo.As.WRAPPER_OBJECT) final T end,
                   @JsonProperty("startInclusive") final Boolean startInclusive,
                   @JsonProperty("endInclusive") final Boolean endInclusive) {
        predicate = new InRangeDual<>(start, end, startInclusive, endInclusive);
    }

    @Override
    public boolean test(final T value) {
        return predicate.test(value, value);
    }

    public T getStart() {
        return predicate.getStart();
    }

    public T getEnd() {
        return predicate.getEnd();
    }

    public Boolean isStartInclusive() {
        return predicate.isStartInclusive();
    }

    public Boolean isEndInclusive() {
        return predicate.isEndInclusive();
    }

    @Override
    public boolean equals(final Object obj) {
        if (this == obj) {
            return true;
        }

        if (null == obj || !getClass().equals(obj.getClass())) {
            return false;
        }

        final InRange otherPredicate = (InRange) obj;
        return new EqualsBuilder()
                .append(predicate, otherPredicate.predicate)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(11, 31)
                .appendSuper(super.hashCode())
                .append(predicate)
                .toHashCode();
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("start", predicate.getStart())
                .append("end", predicate.getEnd())
                .append("startInclusive", predicate.isStartInclusive())
                .append("endInclusive", predicate.isEndInclusive())
                .toString();
    }

    public static class Builder<T extends Comparable<T>> extends InRangeDual.BaseBuilder<Builder<T>, InRange<T>, T> {
        @Override
        public InRange<T> build() {
            return new InRange<>(start, end, startInclusive, endInclusive);
        }
    }
}