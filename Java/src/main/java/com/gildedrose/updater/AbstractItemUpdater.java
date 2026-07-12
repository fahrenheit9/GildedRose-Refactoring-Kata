package com.gildedrose.updater;

import com.gildedrose.Item;

abstract class AbstractItemUpdater implements ItemUpdater {

    protected static final int MIN_QUALITY = 0;
    protected static final int MAX_QUALITY = 50;

    protected void increaseQuality(Item item, int qualityFactor) {
        if (item.quality + qualityFactor < MAX_QUALITY) {
            item.quality += qualityFactor;
        } else {
            item.quality = MAX_QUALITY;
        }
    }

    protected void decreaseQuality(Item item, int qualityFactor) {
        if (item.quality - qualityFactor > MIN_QUALITY) {
            item.quality -= qualityFactor;
        } else {
            item.quality = MIN_QUALITY;
        }
    }

}
