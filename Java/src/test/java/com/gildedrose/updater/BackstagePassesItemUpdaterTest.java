package com.gildedrose.updater;

import com.gildedrose.Item;
import com.gildedrose.TestItems;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class BackstagePassesItemUpdaterTest {

    private final BackstagePassesItemUpdater updater = new BackstagePassesItemUpdater();

    @Test
    void increasesQualityByOneWhenSellInIsGt10() {
        // Setup
        Item item = TestItems.backstagePasses(11, 20);

        // Exercise
        updater.update(item);

        // Verify
        assertThat(item.sellIn).isEqualTo(10);
        assertThat(item.quality).isEqualTo(21);
    }

    @Test
    void increasesQualityByTwoWhenSellInIsLeq10() {
        // Setup
        Item item = TestItems.backstagePasses(10, 20);

        // Exercise
        updater.update(item);

        // Verify
        assertThat(item.sellIn).isEqualTo(9);
        assertThat(item.quality).isEqualTo(22);
    }

    @Test
    void increasesQualityByTwoWhenSellInIsSix() {
        // Setup
        Item item = TestItems.backstagePasses(6, 20);

        // Exercise
        updater.update(item);

        // Verify
        assertThat(item.sellIn).isEqualTo(5);
        assertThat(item.quality).isEqualTo(22);
    }

    @Test
    void increasesQualityByThreeWhenSellInIsLeq5() {
        // Setup
        Item item = TestItems.backstagePasses(5, 20);

        // Exercise
        updater.update(item);

        // Verify
        assertThat(item.sellIn).isEqualTo(4);
        assertThat(item.quality).isEqualTo(23);
    }

    @Test
    void increasesQualityByThreeOnLastValidDayBeforeConcert() {
        // Setup
        Item item = TestItems.backstagePasses(1, 20);

        // Exercise
        updater.update(item);

        // Verify
        assertThat(item.sellIn).isEqualTo(0);
        assertThat(item.quality).isEqualTo(23);
    }

    @Test
    void dropsQualityToZeroWhenSellInIsZero() {
        // Setup
        Item item = TestItems.backstagePasses(0, 20);

        // Exercise
        updater.update(item);

        // Verify
        assertThat(item.sellIn).isEqualTo(-1);
        assertThat(item.quality).isEqualTo(0);
    }

    @Test
    void dropsQualityToZeroWhenSellInIsNegative() {
        // Setup
        Item item = TestItems.backstagePasses(-1, 20);

        // Exercise
        updater.update(item);

        // Verify
        assertThat(item.sellIn).isEqualTo(-2);
        assertThat(item.quality).isEqualTo(0);
    }

    @Test
    void increasesQualityNotBeyond50() {
        // Setup
        Item item = TestItems.backstagePasses(5, 48);

        // Exercise
        updater.update(item);

        // Verify
        assertThat(item.sellIn).isEqualTo(4);
        assertThat(item.quality).isEqualTo(50);
    }

    @Test
    void qualityAlreadyAt50() {
        // Setup
        Item item = TestItems.backstagePasses(5, 50);

        // Exercise
        updater.update(item);

        // Verify
        assertThat(item.sellIn).isEqualTo(4);
        assertThat(item.quality).isEqualTo(50);
    }
}
