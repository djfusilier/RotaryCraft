/*******************************************************************************
 * @author Reika Kalseki
 * 
 * Copyright 2013
 * 
 * All rights reserved.
 * Distribution of the software in any form is only allowed with
 * explicit, prior permission from the owner.
 ******************************************************************************/
package Reika.RotaryCraft.GUIs;

import net.minecraft.entity.player.EntityPlayer;
import Reika.DragonAPI.Libraries.IO.ReikaGuiAPI;
import Reika.RotaryCraft.Base.GuiNonPoweredMachine;
import Reika.RotaryCraft.Containers.ContainerBlastFurnace;
import Reika.RotaryCraft.TileEntities.TileEntityBlastFurnace;

public class GuiBlastFurnace extends GuiNonPoweredMachine
{
	private TileEntityBlastFurnace blast;

	public GuiBlastFurnace(EntityPlayer p5ep, TileEntityBlastFurnace BlastFurnace)
	{
		super(new ContainerBlastFurnace(p5ep, BlastFurnace), BlastFurnace);
		blast = BlastFurnace;
		ep = p5ep;
	}

	/**
	 * Draw the foreground layer for the GuiContainer (everything in front of the items)
	 */
	@Override
	protected void drawGuiContainerForegroundLayer(int a, int b)
	{
		int j = (width - xSize) / 2;
		int k = (height - ySize) / 2;

		super.drawGuiContainerForegroundLayer(a, b);

		int c = 0;
		if (blast.temperature >= 1000)
			c = 1;
		ReikaGuiAPI.instance.drawCenteredStringNoShadow(fontRenderer, String.valueOf(blast.temperature)+"C", 17+c, 6, 4210752);
	}

	/**
	 * Draw the background layer for the GuiContainer (everything behind the items)
	 */
	@Override
	protected void drawGuiContainerBackgroundLayer(float par1, int par2, int par3)
	{
		super.drawGuiContainerBackgroundLayer(par1, par2, par3);
		int j = (width - xSize) / 2;
		int k = (height - ySize) / 2;

		int i1 = blast.getCookScaled(24);
		this.drawTexturedModalRect(j+119, k+34, 176, 14, i1+1, 16);
		int i2 = blast.getTemperatureScaled(54);
		this.drawTexturedModalRect(j + 11, k + 70-i2, 176, 86-i2, 10, i2);
	}

	@Override
	public String getGuiTexture() {
		return "blastfurngui";
	}
}
