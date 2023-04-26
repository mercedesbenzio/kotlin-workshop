package io.mb.dealers.api.exception;

public final class DealerAlreadyExistsException extends RuntimeException {

    private static final String DEALER_ALREADY_EXISTS_TEMPLATE = "Dealer with id '%s' already exists";

    public DealerAlreadyExistsException(final String id) {
        super(String.format(DEALER_ALREADY_EXISTS_TEMPLATE, id));
    }
}
