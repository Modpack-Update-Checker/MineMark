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

package dev.jab125.minemark.minecraft.platform;

import com.mojang.blaze3d.platform.NativeImage;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.texture.DynamicTexture;
import net.minecraft.resources.ResourceLocation;

import java.io.Closeable;
import java.io.IOException;

public class MarkdownDynamicImage implements Closeable {
    private final ResourceLocation location;

    public MarkdownDynamicImage(ResourceLocation location) {
        this.location = location;
    }

    public void draw(int x, int y, int width, int height, MarkdownRenderer renderer) {
        renderer.drawTexture(location, x, y, width, height);
    }

    @Override
    public void close() {
        Minecraft.getInstance().getTextureManager().release(location);
    }

    public static MarkdownDynamicImage of(NativeImage image) throws IOException {
        DynamicTexture texture = new DynamicTexture(image);
        ResourceLocation location = Minecraft.getInstance().getTextureManager().register("minemark", texture);
        return new MarkdownDynamicImage(location);
    }
}
