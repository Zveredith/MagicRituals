package xyz.zveredith.magicrituals.network;

import io.netty.buffer.ByteBuf;
import net.minecraft.client.Minecraft;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.fml.common.network.ByteBufUtils;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import xyz.zveredith.magicrituals.tile.TileVacuousChalkMark;

public class PacketUpdateVacuousChalkMark implements IMessage {
    private BlockPos pos;
    private ItemStack stack;
    private long lastChangeTime;

    public PacketUpdateVacuousChalkMark(BlockPos pos, ItemStack stack, long lastChangeTime) {
        this.pos = pos;
        this.stack = stack;
        this.lastChangeTime = lastChangeTime;
    }
    public PacketUpdateVacuousChalkMark(TileVacuousChalkMark te) {
        this(te.getPos(), te.inventory.getStackInSlot(0), te.lastChangeTime);
    }
    @Override
    public void fromBytes(ByteBuf buf) {
        pos = BlockPos.fromLong(buf.readLong());
        stack = ByteBufUtils.readItemStack(buf);
        lastChangeTime = buf.readLong();
    }

    @Override
    public void toBytes(ByteBuf buf) {
        buf.writeLong(pos.toLong());
        ByteBufUtils.writeItemStack(buf, stack);
        buf.writeLong(lastChangeTime);
    }
    public static class Handler implements IMessageHandler<PacketUpdateVacuousChalkMark, IMessage> {

        @Override
        public IMessage onMessage(PacketUpdateVacuousChalkMark message, MessageContext ctx) {
            Minecraft.getMinecraft().addScheduledTask(() -> {
                TileVacuousChalkMark te = (TileVacuousChalkMark) Minecraft.getMinecraft().theWorld.getTileEntity(message.pos);
                te.inventory.setStackInSlot(0, message.stack);
                te.lastChangeTime = message.lastChangeTime;
            });
            return null;
        }
    }
}
