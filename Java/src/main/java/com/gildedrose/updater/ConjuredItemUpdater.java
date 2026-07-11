package com.gildedrose.updater;

import com.gildedrose.Item;

public class ConjuredItemUpdater extends AgingItemUpdater {

    @Override
    void updateQualityBeforeDecreasingSellIn(Item item) {

        if (item.sellIn <= 0) {
            decreaseQuality(item, 4);
        } else {
            decreaseQuality(item, 2);
        }
    }
}
