package com.gildedrose;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

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
        assertThat(app.items[0].name).isEqualTo(expectedName);
    }

}
