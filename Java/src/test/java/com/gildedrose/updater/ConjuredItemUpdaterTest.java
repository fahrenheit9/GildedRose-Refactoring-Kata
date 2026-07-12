package com.gildedrose.updater;

import com.gildedrose.Item;
import com.gildedrose.TestItems;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class ConjuredItemUpdaterTest {

    private final ConjuredItemUpdater updater = new ConjuredItemUpdater();

    @Test
    void decreasesQualityByTwoWhenSellInIsPositive() {
        // Setup
        Item item = TestItems.conjured(10, 20);

        // Exercise
        updater.update(item);

        // Verify
        assertThat(item.sellIn).isEqualTo(9);
        assertThat(item.quality).isEqualTo(18);
    }

    @Test
    void decreasesQualityByFourWhenSellInIsZero() {
        // Setup
        Item item = TestItems.conjured(0, 20);

        // Exercise
        updater.update(item);

        // Verify
        assertThat(item.sellIn).isEqualTo(-1);
        assertThat(item.quality).isEqualTo(16);
    }

    @Test
    void decreasesQualityByFourWhenSellInIsNegative() {
        // Setup
        Item item = TestItems.conjured(-1, 20);

        // Exercise
        updater.update(item);

        // Verify
        assertThat(item.sellIn).isEqualTo(-2);
        assertThat(item.quality).isEqualTo(16);
    }

    @Test
    void decreasesQualityNotBelowZero() {
        // Setup
        Item item = TestItems.conjured(0, 1);

        // Exercise
        updater.update(item);

        // Verify
        assertThat(item.sellIn).isEqualTo(-1);
        assertThat(item.quality).isEqualTo(0);
    }

}
