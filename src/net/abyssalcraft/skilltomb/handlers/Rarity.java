package net.abyssalcraft.skilltomb.handlers;

public enum  Rarity {

    EMPTY("EMPTY"),
    COMMON("COMMON"),
    RARE("RARE"),
    SUPER_RARE("SUPER_RARE"),
    ULTRA_RARE("ULTRA_RARE");

    private static Rarity rarity;
    private String name;

    Rarity(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public static void setRarity(Rarity rarity){
        rarity = rarity;
    }

    public static Rarity getRarity(){
        return rarity;
    }

}
