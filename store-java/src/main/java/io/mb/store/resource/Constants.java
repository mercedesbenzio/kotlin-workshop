package io.mb.store.resource;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class Constants {
    // PATH
    public static final String ITEMS_MAPPING = "/items";
    public static final String DEALERS_MAPPING = "/dealers";

    public static final String DEALERS_VEHICLES_MAPPING = "/{dealerId}/vehicles";

    // PARAM
    public static final String DEALER_PARAM = "dealerId";

    // DB
    public static final String DB_SCHEMA = "store";
    public static final String ITEM_TABLE = "item";
    public static final String ITEM_SEQUENCE = "store.item_sequence";
}
