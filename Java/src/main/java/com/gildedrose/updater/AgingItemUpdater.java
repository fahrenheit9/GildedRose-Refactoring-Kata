package com.gildedrose.updater;

import com.gildedrose.Item;

abstract class AgingItemUpdater extends AbstractItemUpdater {

    @Override
    public final void update(Item item) {

        updateQualityBeforeDecreasingSellIn(item);

        item.sellIn--;
    }

    abstract void updateQualityBeforeDecreasingSellIn(Item item);
}
