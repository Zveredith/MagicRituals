package xyz.zveredith.magicrituals.ritual;

import net.minecraft.block.Block;

public class RitualComponent {

    private Block type;
    private int xOffset;
    private int zOffset;

    public RitualComponent(Block type, int xOffset, int zOffset) {
        this.type = type;
        this.xOffset = xOffset;
        this.zOffset = zOffset;
    }
    public Block getType() {
        return this.type;
    }
    public int getXOffset() {
        return this.xOffset;
    }
    public int getZOffset() {
        return this.zOffset;
    }
}
