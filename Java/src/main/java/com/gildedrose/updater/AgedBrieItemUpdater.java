package com.gildedrose.updater;

import com.gildedrose.Item;

class AgedBrieItemUpdater extends AgingItemUpdater {

    @Override
    void updateQualityBeforeDecreasingSellIn(Item item) {

        if (item.sellIn <= 0) {
            increaseQuality(item, 2);
        } else {
            increaseQuality(item, 1);
        }
    }
}
