package com.electrifiedded.oreendermen;

/** The seven ore enderman kinds of the original mod (health, egg colour, smelting result of the pearl). */
public enum Variant {
    COAL("coal", 50.0, 0x000000, 0.5F, 5),
    REDSTONE("redstone", 60.0, 0xCC0000, 0.6F, 6),
    IRON("iron", 70.0, 0x998866, 0.8F, 4),
    GOLD("gold", 70.0, 0xFFFF00, 0.8F, 4),
    LAPIS("lapis", 60.0, 0x003399, 0.6F, 8),
    DIAMOND("diamond", 80.0, 0x66FFFF, 1.0F, 3),
    EMERALD("emerald", 80.0, 0x00FF33, 1.0F, 3);

    public static final int EGG_PRIMARY = 0xFFFFCC;
    public final String id;
    public final double health;
    public final int eggSecondary;
    public final float xp;
    public final int smeltCount;

    Variant(String id, double health, int eggSecondary, float xp, int smeltCount) {
        this.id = id;
        this.health = health;
        this.eggSecondary = eggSecondary;
        this.xp = xp;
        this.smeltCount = smeltCount;
    }

    public String entityName() { return id + "_enderman"; }
    public String pearlName() { return id + "_pearl"; }
}
