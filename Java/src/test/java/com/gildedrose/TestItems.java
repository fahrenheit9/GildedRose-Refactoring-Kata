package com.gildedrose;

public class TestItems {

    private static final String NORMAL_ITEM = "normal item";
    private static final String AGED_BRIE = "Aged Brie";
    private static final String SULFURAS = "Sulfuras, Hand of Ragnaros";
    private static final String BACKSTAGE_PASSES = "Backstage passes to a TAFKAL80ETC concert";

    private TestItems() {
    }

    public static Item normalItem(int sellIn, int quality) {
        return new Item(NORMAL_ITEM, sellIn, quality);
    }

    public static Item agedBrie(int sellIn, int quality) {
        return new Item(AGED_BRIE, sellIn, quality);
    }

    public static Item sulfuras(int sellIn, int quality) {
        return new Item(SULFURAS, sellIn, quality);
    }

    public static Item backstagePasses(int sellIn, int quality) {
        return new Item(BACKSTAGE_PASSES, sellIn, quality);
    }
}
