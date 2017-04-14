package com.isacademy.jjdd1.czterystrony.utilities;

import java.math.BigDecimal;

public class LocalExtremaFinderConfigurator {
    private int backwardRatingsSensitivity;
    private int forwardRatingsSensitivity;
    private BigDecimal maximumExistenceSensitivity;
    private BigDecimal minimumExistenceSensitivity;

    public static class Builder {
        private int backwardRatingsSensitivity;
        private int forwardRatingsSensitivity;
        private BigDecimal maximumExistenceSensitivity;
        private BigDecimal minimumExistenceSensitivity;

        public Builder withBackwardRatingsSensitivity(int backwardRatingsSensitivity) {
            this.backwardRatingsSensitivity = backwardRatingsSensitivity;
            return this;
        }

        public Builder withForwardRatingsSensitivity(int forwardRatingsSensitivity) {
            this.forwardRatingsSensitivity = forwardRatingsSensitivity;
            return this;
        }

        public Builder withMaximumExistenceSensitivity(BigDecimal maximumExistenceSensitivity) {
            this.maximumExistenceSensitivity = maximumExistenceSensitivity;
            return this;
        }

        public Builder withMinimumExistenceSensitivity(BigDecimal minimumExistenceSensitivity) {
            this.minimumExistenceSensitivity = minimumExistenceSensitivity;
            return this;
        }

        public LocalExtremaFinderConfigurator build() {
            return new LocalExtremaFinderConfigurator(this);
        }
    }

    public LocalExtremaFinderConfigurator(Builder builder) {
        this.backwardRatingsSensitivity = builder.backwardRatingsSensitivity;
        this.forwardRatingsSensitivity = builder.forwardRatingsSensitivity;
        this.maximumExistenceSensitivity = builder.maximumExistenceSensitivity;
        this.minimumExistenceSensitivity = builder.minimumExistenceSensitivity;
    }

    public int getBackwardRatingsSensitivity() {
        return backwardRatingsSensitivity;
    }

    public int getForwardRatingsSensitivity() {
        return forwardRatingsSensitivity;
    }

    public BigDecimal getMaximumExistenceSensitivity() {
        return maximumExistenceSensitivity;
    }

    public BigDecimal getMinimumExistenceSensitivity() {
        return minimumExistenceSensitivity;
    }
}