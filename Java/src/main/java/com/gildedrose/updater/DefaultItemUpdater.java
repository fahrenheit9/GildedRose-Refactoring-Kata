package com.gildedrose.updater;

import com.gildedrose.Item;

class DefaultItemUpdater extends AgingItemUpdater {

    @Override
    void updateQualityBeforeDecreasingSellIn(Item item) {

        if (item.sellIn <= 0) {
            decreaseQuality(item, 2);
        } else {
            decreaseQuality(item, 1);
        }
    }
}
