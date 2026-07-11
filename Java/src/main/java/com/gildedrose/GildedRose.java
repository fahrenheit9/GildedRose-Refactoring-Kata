package com.gildedrose;

class GildedRose {

    private static final String AGED_BRIE = "Aged Brie";
    private static final String SULFURAS = "Sulfuras, Hand of Ragnaros";
    private static final String BACKSTAGE_PASSES = "Backstage passes to a TAFKAL80ETC concert";
    private static final int MIN_QUALITY = 0;
    private static final int MAX_QUALITY = 50;

    final Item[] items;

    public GildedRose(Item[] items) {
        this.items = items;
    }

    public void updateQuality() {
        for (int i = 0; i < items.length; i++) {

            Item item = items[i];

            updateItemQuality(item);
        }
    }

    ////////////////////
    // HELPER METHODS
    ////////////////////

    private void updateItemQuality(Item item) {

        if (!item.name.equals(AGED_BRIE) && !item.name.equals(BACKSTAGE_PASSES)) {
            if (!item.name.equals(SULFURAS)) {
                decreaseQuality(item);
            }
        } else {
            increaseQuality(item);

            if (item.name.equals(BACKSTAGE_PASSES)) {
                if (item.sellIn < 11) {
                    increaseQuality(item);
                }

                if (item.sellIn < 6) {
                    increaseQuality(item);
                }
            }
        }

        if (!item.name.equals(SULFURAS)) {
            item.sellIn--;
        }

        if (item.sellIn < 0) {
            if (!item.name.equals(AGED_BRIE)) {
                if (!item.name.equals(BACKSTAGE_PASSES)) {
                    if (!item.name.equals(SULFURAS)) {
                        decreaseQuality(item);
                    }
                } else {
                    item.quality = MIN_QUALITY;
                }
            } else {
                increaseQuality(item);
            }
        }
    }

    private void increaseQuality(Item item) {
        if (item.quality < MAX_QUALITY) {
            item.quality++;
        }
    }

    private void decreaseQuality(Item item) {
        if (item.quality > MIN_QUALITY) {
            item.quality--;
        }
    }
}
