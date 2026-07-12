package com.gildedrose;

import com.gildedrose.updater.ItemUpdater;
import com.gildedrose.updater.UpdaterFactory;

/**
 * Entry point for updating the item inventory.
 */
class GildedRose {

    private final Item[] items;

    public GildedRose(Item[] items) {
        this.items = items.clone();
    }

    public Item[] items() {
        return items.clone();
    }

    public void updateQuality() {
        for (int i = 0; i < items.length; i++) {

            Item item = items[i];

            ItemUpdater updater = UpdaterFactory.forItem(item);

            updater.update(item);
        }
    }
}
