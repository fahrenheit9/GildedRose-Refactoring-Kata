package com.gildedrose.updater;

import com.gildedrose.Item;

/**
 * Strategy for updating a single item's quality and sellIn according to its
 * own business rule (e.g. normal degradation, Aged Brie, legendary items, backstage passes).
 */
public interface ItemUpdater {

    void update(Item item);

}
