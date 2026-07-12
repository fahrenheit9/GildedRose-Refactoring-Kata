package com.gildedrose.updater;

import com.gildedrose.Item;
import com.gildedrose.TestItems;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class SulfurasItemUpdaterTest {

    private final SulfurasItemUpdater updater = new SulfurasItemUpdater();

    @Test
    void qualityAndSellInNeverChangeWhenSellInIsPositive() {
        // Setup
        Item item = TestItems.sulfuras(5, 80);

        // Exercise
        updater.update(item);

        // Verify
        assertThat(item.sellIn).isEqualTo(5);
        assertThat(item.quality).isEqualTo(80);
    }

    @Test
    void qualityAndSellInNeverChangeWhenSellInIsAlreadyNegative() {
        // Setup
        Item item = TestItems.sulfuras(-5, 80);

        // Exercise
        updater.update(item);

        // Verify
        assertThat(item.sellIn).isEqualTo(-5);
        assertThat(item.quality).isEqualTo(80);
    }
}
