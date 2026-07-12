package com.gildedrose.updater;

import com.gildedrose.Item;
import com.gildedrose.TestItems;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class AgedBrieItemUpdaterTest {

    private final AgedBrieItemUpdater updater = new AgedBrieItemUpdater();

    @Test
    void increasesQualityByOneWhenSellInIsPositive() {
        // Setup
        Item item = TestItems.agedBrie(10, 20);

        // Exercise
        updater.update(item);

        // Verify
        assertThat(item.sellIn).isEqualTo(9);
        assertThat(item.quality).isEqualTo(21);
    }

    @Test
    void increasesQualityByTwoWhenSellInIsZero() {
        // Setup
        Item item = TestItems.agedBrie(0, 20);

        // Exercise
        updater.update(item);

        // Verify
        assertThat(item.sellIn).isEqualTo(-1);
        assertThat(item.quality).isEqualTo(22);
    }

    @Test
    void increasesQualityByTwoWhenSellInIsNegative() {
        // Setup
        Item item = TestItems.agedBrie(-1, 20);

        // Exercise
        updater.update(item);

        // Verify
        assertThat(item.sellIn).isEqualTo(-2);
        assertThat(item.quality).isEqualTo(22);
    }

    @Test
    void increasesQualityNotBeyond50() {
        // Setup
        Item item = TestItems.agedBrie(0, 49);

        // Exercise
        updater.update(item);

        // Verify
        assertThat(item.sellIn).isEqualTo(-1);
        assertThat(item.quality).isEqualTo(50);
    }

    @Test
    void qualityAlreadyAt50() {
        // Setup
        Item item = TestItems.agedBrie(0, 50);

        // Exercise
        updater.update(item);

        // Verify
        assertThat(item.sellIn).isEqualTo(-1);
        assertThat(item.quality).isEqualTo(50);
    }
}
