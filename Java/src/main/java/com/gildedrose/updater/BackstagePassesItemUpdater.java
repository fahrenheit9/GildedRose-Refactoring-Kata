package com.gildedrose.updater;

import com.gildedrose.Item;

class BackstagePassesItemUpdater extends AgingItemUpdater {

    @Override
    void updateQualityBeforeDecreasingSellIn(Item item) {

        if (item.sellIn <= 0) {
            item.quality = 0;
        } else if (item.sellIn <= 5) {
            increaseQuality(item, 3);
        } else if (item.sellIn <= 10) {
            increaseQuality(item, 2);
        } else {
            increaseQuality(item, 1);
        }

    }
}
