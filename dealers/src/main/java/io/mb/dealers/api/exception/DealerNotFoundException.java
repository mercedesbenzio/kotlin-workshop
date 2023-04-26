package io.mb.dealers.api.exception;

public final class DealerNotFoundException extends RuntimeException {

    private static final String DEALER_NOT_FOUND_TEMPLATE = "Dealer with id '%s' could not be found";

    public DealerNotFoundException(final String id) {
        super(String.format(DEALER_NOT_FOUND_TEMPLATE, id));
    }
}
