package com.gildedrose.updater;

import com.gildedrose.Item;

/**
 * Selects and provides the appropriate {@link ItemUpdater} for a given item, based on its name.
 */
public class UpdaterFactory {

    private static final String AGED_BRIE = "Aged Brie";
    private static final String SULFURAS = "Sulfuras, Hand of Ragnaros";
    private static final String BACKSTAGE_PASSES = "Backstage passes to a TAFKAL80ETC concert";

    private UpdaterFactory() {
    }

    public static ItemUpdater forItem(Item item) {
        switch (item.name) {
            case AGED_BRIE:
                return new AgedBrieItemUpdater();
            case SULFURAS:
                return new SulfurasItemUpdater();
            case BACKSTAGE_PASSES:
                return new BackstagePassesItemUpdater();
            default:
                return new DefaultItemUpdater();
        }
    }
}
