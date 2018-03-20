package xyz.zveredith.magicrituals.network;

import io.netty.buffer.ByteBuf;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import xyz.zveredith.magicrituals.tile.TileVacuousChalkMark;

public class PacketRequestUpdateVacuousChalkMark implements IMessage {
    private BlockPos pos;
    private int dimension;

    public PacketRequestUpdateVacuousChalkMark(BlockPos pos, int dimension) {
        this.pos = pos;
        this.dimension = dimension;
    }

    public PacketRequestUpdateVacuousChalkMark(TileVacuousChalkMark te) {
        this(te.getPos(), te.getWorld().provider.getDimension());
    }

    public PacketRequestUpdateVacuousChalkMark() {
    }

    @Override
    public void toBytes(ByteBuf buf) {
        buf.writeLong(pos.toLong());
        buf.writeInt(dimension);
    }

    @Override
    public void fromBytes(ByteBuf buf) {
        pos = BlockPos.fromLong(buf.readLong());
        dimension = buf.readInt();
    }

    public static class Handler implements IMessageHandler<PacketRequestUpdateVacuousChalkMark, PacketUpdateVacuousChalkMark> {

        @Override
        public PacketUpdateVacuousChalkMark onMessage(PacketRequestUpdateVacuousChalkMark message, MessageContext ctx) {
            World world = FMLCommonHandler.instance().getMinecraftServerInstance().worldServerForDimension(message.dimension);
            TileVacuousChalkMark te = (TileVacuousChalkMark) world.getTileEntity(message.pos);
            if (te != null) {
                return new PacketUpdateVacuousChalkMark(te);
            } else {
                return null;
            }
        }

    }
}
