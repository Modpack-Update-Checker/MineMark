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

package dev.jab125.minemark.minecraft;

import com.mojang.blaze3d.vertex.PoseStack;
import dev.dediamondpro.minemark.MineMarkCore;
import dev.dediamondpro.minemark.MineMarkCoreBuilder;
import dev.dediamondpro.minemark.elements.Elements;
import dev.dediamondpro.minemark.elements.MineMarkElement;
import dev.jab125.minemark.minecraft.elements.*;
import dev.jab125.minemark.minecraft.platform.MarkdownRenderer;
import dev.jab125.minemark.minecraft.style.MarkdownStyle;
import dev.dediamondpro.minemark.utils.MouseButton;
//? if >=1.20
import net.minecraft.client.gui.GuiGraphics;
import org.commonmark.ext.gfm.strikethrough.StrikethroughExtension;
import org.commonmark.ext.gfm.tables.TablesExtension;
import org.xml.sax.SAXException;

import java.io.IOException;
import java.io.Reader;

public class MineMarkDrawable {
    public static final MineMarkCore<MarkdownStyle, MarkdownRenderer> DEFAULT_CORE = addMinecraftExtensions(MineMarkCore.builder())
            .addExtension(StrikethroughExtension.create())
            .addExtension(TablesExtension.create())
            .build();
    private final MineMarkElement<MarkdownStyle, MarkdownRenderer> parsedMarkdown;

    public MineMarkDrawable(MineMarkElement<MarkdownStyle, MarkdownRenderer> parsedMarkdown) {
        this.parsedMarkdown = parsedMarkdown;
    }

    public MineMarkDrawable(String markdown, MarkdownStyle style, MineMarkCore<MarkdownStyle, MarkdownRenderer> core) throws IOException, SAXException {
        this(core.parse(style, markdown));
    }

    public MineMarkDrawable(String markdown, MarkdownStyle style) throws IOException, SAXException {
        this(markdown, style, DEFAULT_CORE);
    }

    public MineMarkDrawable(String markdown) throws IOException, SAXException {
        this(markdown, new MarkdownStyle());
    }

    public MineMarkDrawable(Reader markdown, MarkdownStyle style, MineMarkCore<MarkdownStyle, MarkdownRenderer> core) throws IOException, SAXException {
        this(core.parse(style, markdown));
    }

    public MineMarkDrawable(Reader markdown, MarkdownStyle style) throws IOException, SAXException {
        this(markdown, style, DEFAULT_CORE);
    }

    public MineMarkDrawable(Reader markdown) throws IOException, SAXException {
        this(markdown, new MarkdownStyle());
    }

    public void draw(float x, float y, float width, float mouseX, float mouseY, /*? if >=1.20 {*/GuiGraphics graphics/*?} else {*//*PoseStack pose*//*?}*/) {
        parsedMarkdown.draw(x, y, width, mouseX, mouseY, new MarkdownRenderer(/*? if >=1.20 {*/graphics/*?} else {*//*pose*//*?}*/));
    }

    public void beforeDraw(float x, float y, float width, float mouseX, float mouseY, /*? if >=1.20 {*/GuiGraphics graphics/*?} else {*//*PoseStack pose*//*?}*/) {
        parsedMarkdown.beforeDraw(x, y, width, mouseX, mouseY, new MarkdownRenderer(/*? if >=1.20 {*/graphics/*?} else {*//*pose*//*?}*/));
    }

    public void onMouseClicked(float x, float y, float mouseX, float mouseY, int button) {
        MouseButton mouseButton;
        switch (button) {
            case 0:
            default:
                mouseButton = MouseButton.LEFT;
                break;
            case 1:
                mouseButton = MouseButton.RIGHT;
                break;
            case 2:
                mouseButton = MouseButton.MIDDLE;
                break;
        }
        parsedMarkdown.onMouseClicked(x, y, mouseButton, mouseX, mouseY);
    }

    public MineMarkElement<MarkdownStyle, MarkdownRenderer> getParsedMarkdown() {
        return parsedMarkdown;
    }

    public float getHeight() {
        return parsedMarkdown.getHeight();
    }

    public void close() {
        parsedMarkdown.close();
    }

    public static MineMarkCoreBuilder<MarkdownStyle, MarkdownRenderer> addMinecraftExtensions(MineMarkCoreBuilder<MarkdownStyle, MarkdownRenderer> core) {
        return core.setTextElement(MarkdownTextElement::new)
                .addElement(Elements.HEADING, MarkdownHeadingElement::new)
                .addElement(Elements.CODE_BLOCK, MarkdownCodeBlockElement::new)
                .addElement(Elements.BLOCKQUOTE, MarkdownBlockQuoteElement::new)
                .addElement(Elements.LIST_ELEMENT, MarkdownListElement::new)
                .addElement(Elements.HORIZONTAL_RULE, MarkdownHorizontalRuleElement::new)
                .addElement(Elements.TABLE_CELL, MarkdownTableCellElement::new)
                .addElement(Elements.IMAGE, MarkdownImageElement::new);
    }
}
