package com.gildedrose.updater;

import com.gildedrose.Item;
import com.gildedrose.TestItems;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class UpdaterFactoryTest {

    @Test
    void returnsAgedBrieItemUpdaterForAgedBrie() {
        // Setup
        Item item = TestItems.agedBrie(10, 20);

        // Exercise
        ItemUpdater updater = UpdaterFactory.forItem(item);

        // Verify
        assertThat(updater).isInstanceOf(AgedBrieItemUpdater.class);
    }

    @Test
    void returnsSulfurasItemUpdaterForSulfuras() {
        // Setup
        Item item = TestItems.sulfuras(5, 80);

        // Exercise
        ItemUpdater updater = UpdaterFactory.forItem(item);

        // Verify
        assertThat(updater).isInstanceOf(SulfurasItemUpdater.class);
    }

    @Test
    void returnsBackstagePassesItemUpdaterForBackstagePasses() {
        // Setup
        Item item = TestItems.backstagePasses(10, 20);

        // Exercise
        ItemUpdater updater = UpdaterFactory.forItem(item);

        // Verify
        assertThat(updater).isInstanceOf(BackstagePassesItemUpdater.class);
    }

    @Test
    void returnsConjuredItemUpdaterForConjured() {
        // Setup
        Item item = TestItems.conjured(10, 20);

        // Exercise
        ItemUpdater updater = UpdaterFactory.forItem(item);

        // Verify
        assertThat(updater).isInstanceOf(ConjuredItemUpdater.class);
    }

    @Test
    void returnsDefaultItemUpdaterForAnyOtherItem() {
        // Setup
        Item item = TestItems.normalItem(10, 20);

        // Exercise
        ItemUpdater updater = UpdaterFactory.forItem(item);

        // Verify
        assertThat(updater).isInstanceOf(DefaultItemUpdater.class);
    }
}
