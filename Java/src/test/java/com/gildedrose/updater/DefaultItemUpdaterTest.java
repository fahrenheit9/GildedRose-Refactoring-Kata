package com.gildedrose.updater;

import com.gildedrose.Item;
import com.gildedrose.TestItems;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class DefaultItemUpdaterTest {

    private final DefaultItemUpdater updater = new DefaultItemUpdater();

    @Test
    void decreasesQualityByOneWhenSellInIsPositive() {
        // Setup
        Item item = TestItems.normalItem(10, 20);

        // Exercise
        updater.update(item);

        // Verify
        assertThat(item.sellIn).isEqualTo(9);
        assertThat(item.quality).isEqualTo(19);
    }

    @Test
    void decreasesQualityByTwoWhenSellInIsZero() {
        // Setup
        Item item = TestItems.normalItem(0, 20);

        // Exercise
        updater.update(item);

        // Verify
        assertThat(item.sellIn).isEqualTo(-1);
        assertThat(item.quality).isEqualTo(18);
    }

    @Test
    void decreasesQualityByTwoWhenSellInIsNegative() {
        // Setup
        Item item = TestItems.normalItem(-1, 20);

        // Exercise
        updater.update(item);

        // Verify
        assertThat(item.sellIn).isEqualTo(-2);
        assertThat(item.quality).isEqualTo(18);
    }

    @Test
    void decreasesQualityNotBelowZero() {
        // Setup
        Item item = TestItems.normalItem(0, 1);

        // Exercise
        updater.update(item);

        // Verify
        assertThat(item.sellIn).isEqualTo(-1);
        assertThat(item.quality).isEqualTo(0);
    }
}
