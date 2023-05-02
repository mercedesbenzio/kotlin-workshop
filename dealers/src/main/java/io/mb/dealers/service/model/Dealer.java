package io.mb.dealers.service.model;

import java.util.Objects;

// @Builder -> Lombok's @Builder annotation could be used here to avoid the boilerplate code
public record Dealer(String id, String name, String description) {

    public static Builder builder() {
        return new Builder();
    }

    public static final class Builder {

        private static final String DEALER_ID_NOT_NULL_MESSAGE = "Dealer id must not be null";
        private static final String DEALER_NAME_NOT_NULL_MESSAGE = "Dealer name must not be null";

        private String id;
        private String name;
        private String description;

        Builder() {

        }

        public Builder id(final String id) {
            this.id = Objects.requireNonNull(id, DEALER_ID_NOT_NULL_MESSAGE);
            return this;
        }

        public Builder name(final String name) {
            this.name = Objects.requireNonNull(name, DEALER_NAME_NOT_NULL_MESSAGE);
            return this;
        }

        public Builder description(final String description) {
            this.description = description;
            return this;
        }

        public Dealer build() {
            return new Dealer(id, name, description);
        }
    }
}
