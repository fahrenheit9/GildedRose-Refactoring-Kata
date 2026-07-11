package com.gildedrose;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;

class GildedRoseTest {

    @Test
    void foo() {
        // Setup
        String expectedName = "foo";

        Item[] items = new Item[] { new Item(expectedName, 0, 0) };
        GildedRose app = new GildedRose(items);

        // Exercise
        app.updateQuality();

        // Verify
        assertThat(app.items()[0].name).isEqualTo(expectedName);
    }

    @Nested
    @DisplayName("Normal item")
    class NormalItemTests {

        @Test
        void decreasesQualityByOneWhenSellInIsPositive() {
            // Setup
            Item[] items = new Item[] { TestItems.normalItem(10, 20) };
            GildedRose app = new GildedRose(items);

            // Exercise
            app.updateQuality();

            // Verify
            assertThat(app.items()[0].sellIn).isEqualTo(9);
            assertThat(app.items()[0].quality).isEqualTo(19);
        }

        @Test
        void decreasesQualityByTwoWhenSellInIsZero() {
            // Setup
            Item[] items = new Item[] { TestItems.normalItem(0, 20) };
            GildedRose app = new GildedRose(items);

            // Exercise
            app.updateQuality();

            // Verify
            assertThat(app.items()[0].sellIn).isEqualTo(-1);
            assertThat(app.items()[0].quality).isEqualTo(18);
        }

        @Test
        void decreasesQualityByTwoWhenSellInIsNegative() {
            // Setup
            Item[] items = new Item[] { TestItems.normalItem(-1, 20) };
            GildedRose app = new GildedRose(items);

            // Exercise
            app.updateQuality();

            // Verify
            assertThat(app.items()[0].sellIn).isEqualTo(-2);
            assertThat(app.items()[0].quality).isEqualTo(18);
        }

        @Test
        void decreasesQualityNotBelowZero() {
            // Setup
            Item[] items = new Item[] { TestItems.normalItem(0, 1) };
            GildedRose app = new GildedRose(items);

            // Exercise
            app.updateQuality();

            // Verify
            assertThat(app.items()[0].sellIn).isEqualTo(-1);
            assertThat(app.items()[0].quality).isEqualTo(0);
        }
    }

    @Nested
    @DisplayName("Aged Brie")
    class AgedBrieTests {

        @Test
        void increasesQualityByOneWhenSellInIsPositive() {
            // Setup
            Item[] items = new Item[] { TestItems.agedBrie(10, 20) };
            GildedRose app = new GildedRose(items);

            // Exercise
            app.updateQuality();

            // Verify
            assertThat(app.items()[0].sellIn).isEqualTo(9);
            assertThat(app.items()[0].quality).isEqualTo(21);
        }

        @Test
        void increasesQualityByTwoWhenSellInIsZero() {
            // Setup
            Item[] items = new Item[] { TestItems.agedBrie(0, 20) };
            GildedRose app = new GildedRose(items);

            // Exercise
            app.updateQuality();

            // Verify
            assertThat(app.items()[0].sellIn).isEqualTo(-1);
            assertThat(app.items()[0].quality).isEqualTo(22);
        }

        @Test
        void increasesQualityByTwoWhenSellInIsNegative() {
            // Setup
            Item[] items = new Item[] { TestItems.agedBrie(-1, 20) };
            GildedRose app = new GildedRose(items);

            // Exercise
            app.updateQuality();

            // Verify
            assertThat(app.items()[0].sellIn).isEqualTo(-2);
            assertThat(app.items()[0].quality).isEqualTo(22);
        }

        @Test
        void increasesQualityNotBeyond50() {
            // Setup
            Item[] items = new Item[] { TestItems.agedBrie(0, 49) };
            GildedRose app = new GildedRose(items);

            // Exercise
            app.updateQuality();

            // Verify
            assertThat(app.items()[0].sellIn).isEqualTo(-1);
            assertThat(app.items()[0].quality).isEqualTo(50);
        }

        @Test
        void qualityAlreadyAt50() {
            // Setup
            Item[] items = new Item[] { TestItems.agedBrie(0, 50) };
            GildedRose app = new GildedRose(items);

            // Exercise
            app.updateQuality();

            // Verify
            assertThat(app.items()[0].sellIn).isEqualTo(-1);
            assertThat(app.items()[0].quality).isEqualTo(50);
        }
    }

    @Nested
    @DisplayName("Sulfuras")
    class SulfurasTests {
        @Test
        void qualityAndSellInNeverChangeWhenSellInIsPositive() {
            // Setup
            Item[] items = new Item[] { TestItems.sulfuras(5, 80) };
            GildedRose app = new GildedRose(items);

            // Exercise
            app.updateQuality();

            // Verify
            assertThat(app.items()[0].sellIn).isEqualTo(5);
            assertThat(app.items()[0].quality).isEqualTo(80);
        }

        @Test
        void qualityAndSellInNeverChangeWhenSellInIsAlreadyNegative() {
            // Setup
            Item[] items = new Item[] { TestItems.sulfuras(-5, 80) };
            GildedRose app = new GildedRose(items);

            // Exercise
            app.updateQuality();

            // Verify
            assertThat(app.items()[0].sellIn).isEqualTo(-5);
            assertThat(app.items()[0].quality).isEqualTo(80);
        }
    }

    @Nested
    @DisplayName("Backstage passes")
    class BackstagePassesTests {

        @Test
        void increasesQualityByOneWhenSellInIsGt10() {
            // Setup
            Item[] items = new Item[] { TestItems.backstagePasses(11, 20) };
            GildedRose app = new GildedRose(items);

            // Exercise
            app.updateQuality();

            // Verify
            assertThat(app.items()[0].sellIn).isEqualTo(10);
            assertThat(app.items()[0].quality).isEqualTo(21);
        }

        @Test
        void increasesQualityByTwoWhenSellInIsLeq10() {
            // Setup
            Item[] items = new Item[] { TestItems.backstagePasses(10, 20) };
            GildedRose app = new GildedRose(items);

            // Exercise
            app.updateQuality();

            // Verify
            assertThat(app.items()[0].sellIn).isEqualTo(9);
            assertThat(app.items()[0].quality).isEqualTo(22);
        }

        @Test
        void increasesQualityByTwoWhenSellInIsSix() {
            // Setup
            Item[] items = new Item[] { TestItems.backstagePasses(6, 20) };
            GildedRose app = new GildedRose(items);

            // Exercise
            app.updateQuality();

            // Verify
            assertThat(app.items()[0].sellIn).isEqualTo(5);
            assertThat(app.items()[0].quality).isEqualTo(22);
        }

        @Test
        void increasesQualityByThreeWhenSellInIsLeq5() {
            // Setup
            Item[] items = new Item[] { TestItems.backstagePasses(5, 20) };
            GildedRose app = new GildedRose(items);

            // Exercise
            app.updateQuality();

            // Verify
            assertThat(app.items()[0].sellIn).isEqualTo(4);
            assertThat(app.items()[0].quality).isEqualTo(23);
        }

        @Test
        void increasesQualityByThreeOnLastValidDayBeforeConcert() {
            // Setup
            Item[] items = new Item[] { TestItems.backstagePasses(1, 20) };
            GildedRose app = new GildedRose(items);

            // Exercise
            app.updateQuality();

            // Verify
            assertThat(app.items()[0].sellIn).isEqualTo(0);
            assertThat(app.items()[0].quality).isEqualTo(23);
        }

        @Test
        void dropsQualityToZeroWhenSellInIsZero() {
            // Setup
            Item[] items = new Item[] { TestItems.backstagePasses(0, 20) };
            GildedRose app = new GildedRose(items);

            // Exercise
            app.updateQuality();

            // Verify
            assertThat(app.items()[0].sellIn).isEqualTo(-1);
            assertThat(app.items()[0].quality).isEqualTo(0);
        }

        @Test
        void dropsQualityToZeroWhenSellInIsNegative() {
            // Setup
            Item[] items = new Item[] { TestItems.backstagePasses(-1, 20) };
            GildedRose app = new GildedRose(items);

            // Exercise
            app.updateQuality();

            // Verify
            assertThat(app.items()[0].sellIn).isEqualTo(-2);
            assertThat(app.items()[0].quality).isEqualTo(0);
        }

        @Test
        void increasesQualityNotBeyond50() {
            // Setup
            Item[] items = new Item[] { TestItems.backstagePasses(5, 48) };
            GildedRose app = new GildedRose(items);

            // Exercise
            app.updateQuality();

            // Verify
            assertThat(app.items()[0].sellIn).isEqualTo(4);
            assertThat(app.items()[0].quality).isEqualTo(50);
        }

        @Test
        void qualityAlreadyAt50() {
            // Setup
            Item[] items = new Item[] { TestItems.backstagePasses(5, 50) };
            GildedRose app = new GildedRose(items);

            // Exercise
            app.updateQuality();

            // Verify
            assertThat(app.items()[0].sellIn).isEqualTo(4);
            assertThat(app.items()[0].quality).isEqualTo(50);
        }
    }

    @Nested
    @DisplayName("Cross-cutting edge cases")
    class EdgeCasesTests {

        @Test
        void emptyListNotThrowingError() {
            // Setup
            GildedRose app = new GildedRose(new Item[0]);

            // Exercise & Verify
            assertThatCode(app::updateQuality).doesNotThrowAnyException();
        }

        @Test
        void accumulatesQualityOverMultipleDays() {
            // Setup
            Item[] items = new Item[] { TestItems.agedBrie(10, 20) };
            GildedRose app = new GildedRose(items);

            // Exercise
            app.updateQuality();
            int sellInDay1 = app.items()[0].sellIn;
            int qualityDay1 = app.items()[0].quality;

            app.updateQuality();
            int sellInDay2 = app.items()[0].sellIn;
            int qualityDay2 = app.items()[0].quality;

            app.updateQuality();
            int sellInDay3 = app.items()[0].sellIn;
            int qualityDay3 = app.items()[0].quality;

            // Verify
            assertThat(sellInDay1).isEqualTo(9);
            assertThat(qualityDay1).isEqualTo(21);

            assertThat(sellInDay2).isEqualTo(8);
            assertThat(qualityDay2).isEqualTo(22);

            assertThat(sellInDay3).isEqualTo(7);
            assertThat(qualityDay3).isEqualTo(23);
        }

        @Test
        void updatesEachItemIndependentlyAccordingToItsOwnRule() {
            // Setup
            Item[] items = new Item[] {
                TestItems.normalItem(10, 20),
                TestItems.agedBrie(10, 20),
                TestItems.sulfuras(5, 80),
                TestItems.backstagePasses(10, 20)
            };
            GildedRose app = new GildedRose(items);

            // Exercise
            app.updateQuality();

            // Verify
            assertThat(app.items()[0].sellIn).isEqualTo(9);
            assertThat(app.items()[0].quality).isEqualTo(19);

            assertThat(app.items()[1].sellIn).isEqualTo(9);
            assertThat(app.items()[1].quality).isEqualTo(21);

            assertThat(app.items()[2].sellIn).isEqualTo(5);
            assertThat(app.items()[2].quality).isEqualTo(80);

            assertThat(app.items()[3].sellIn).isEqualTo(9);
            assertThat(app.items()[3].quality).isEqualTo(22);
        }
    }
}
