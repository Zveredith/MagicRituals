package xyz.zveredith.magicrituals.client.render.block;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.RenderItem;
import net.minecraft.client.renderer.block.model.ItemCameraTransforms;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.items.IItemHandler;
import xyz.zveredith.magicrituals.tile.TileVacuousChalkMark;

public class RenderVacuousChalkMark extends TileEntitySpecialRenderer<TileVacuousChalkMark> {

    @Override
    public void renderTileEntityAt(TileVacuousChalkMark tileVacuousChalkMark, double x, double y, double z, float partialTicks, int destroyStage) {

        IItemHandler itemHandler = tileVacuousChalkMark.inventory;
        ItemStack stack = itemHandler.getStackInSlot(0);
        GlStateManager.pushMatrix();
        GlStateManager.translate(x, y, z);
        this.renderItem(tileVacuousChalkMark.getWorld(), stack, partialTicks);
        GlStateManager.popMatrix();
    }
    private void renderItem(World world, ItemStack stack, float partialTicks)
    {
        RenderItem itemRenderer = Minecraft.getMinecraft().getRenderItem();

        if (stack != null)
        {
            GlStateManager.translate(0.5, 0.5, 0.5);
            EntityItem entityitem = new EntityItem(world, 0.0D, 0.0D, 0.0D, stack);
            entityitem.getEntityItem().stackSize = 1;
            entityitem.hoverStart = 0.0F;
            GlStateManager.pushMatrix();
            GlStateManager.disableLighting();

            float rotation = (float) (720.0 * (System.currentTimeMillis() & 0x3FFFL) / 0x3FFFL);

            GlStateManager.rotate(rotation, 0.0F, 1.0F, 0);
            GlStateManager.scale(0.5F, 0.5F, 0.5F);
            GlStateManager.pushAttrib();
            RenderHelper.enableStandardItemLighting();
            itemRenderer.renderItem(entityitem.getEntityItem(), ItemCameraTransforms.TransformType.FIXED);
            RenderHelper.disableStandardItemLighting();
            GlStateManager.popAttrib();

            GlStateManager.enableLighting();
            GlStateManager.popMatrix();
        }
    }
}
