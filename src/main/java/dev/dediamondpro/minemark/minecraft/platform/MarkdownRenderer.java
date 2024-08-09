/*
 * This file is part of MineMark
 * Copyright (C) 2024 DeDiamondPro
 *
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License Version 3 as published by the Free Software Foundation.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with this program. If not, see <https://www.gnu.org/licenses/>.
 */

package dev.dediamondpro.minemark.minecraft.platform;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Font;
//? if >=1.20
import net.minecraft.client.gui.GuiGraphics;
//? if <1.20
/*import net.minecraft.client.gui.GuiComponent;*/
import net.minecraft.resources.ResourceLocation;

public class MarkdownRenderer {
    //? if >=1.20
    private final GuiGraphics graphics;
    //? if <1.20
	/*private final PoseStack pose;*/

    //? if >=1.20 {
    public MarkdownRenderer(GuiGraphics graphics) {
        this.graphics = graphics;
    }
    //?} else {
    /*public MarkdownRenderer(PoseStack pose) {
		this.pose = pose;
	}
    *///?}

    public void push() {
        getPose().pushPose();
    }

    public void pop() {
        getPose().popPose();
    }

    public void scale(float x, float y, float z) {
        getPose().scale(x, y, z);
    }

    public void drawText(String text, int x, int y, float scale, int color, boolean hasShadow) {
        Font textRenderer = Minecraft.getInstance().font;
        push();
        scale(scale, scale, 1f);
        //? if >=1.20 {
        graphics.drawString(textRenderer, text, (int) (x / scale), (int) (y / scale), color, hasShadow);
        //?} else {
        /*if (hasShadow) {
			textRenderer.drawShadow(pose, text,(int) (x / scale), (int) (y / scale), color);
		} else {
            textRenderer.draw(pose, text, (int) (x / scale), (int) (y / scale), color);
        }
        *///?}
        pop();
    }

    public float getTextWidth(String text, float scale) {
        Font font = Minecraft.getInstance().font;
        return font.width(text) * scale;
    }

    public void drawRect(int x, int y, int width, int height, int color) {
        //? if >=1.20
        graphics.fill(x, y, x + width, y + height, color);
        //? if <1.20
        /*GuiComponent.fill(pose, x, y, x + width, y + height, color);*/
	}

    public void drawTexture(ResourceLocation location, int x, int y, int width, int height) {
        //? if >=1.20 {
        graphics.blit(location, x, y, 0, 0, width, height, width, height);
        //?} else {
        /*RenderSystem.setShaderTexture(0, location);
        GuiComponent.blit(pose, x, y, 0, 0, width, height, width, height);
		*///?}
    }

	public PoseStack getPose() {
		//? if >=1.20
		return graphics.pose();
		//? if <1.20
		/*return pose;*/
	}
}
