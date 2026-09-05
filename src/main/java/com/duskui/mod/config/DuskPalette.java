package com.duskui.mod.config;

/**
 * DuskUI color palette.
 *
 * Base is near-black with a faint blue tint (never pure #000000, which reads
 * as "dead" on screen) and every accent is picked to sit at a similar
 * perceived brightness so no single UI element visually screams over the rest.
 * All values are ARGB ints ready for Minecraft's fill()/drawText() calls.
 */
public final class DuskPalette {
    private DuskPalette() {}

    // ---- Base surfaces -----------------------------------------------
    public static final int BG_BASE          = 0xF00A0A0D; // near-black, 94% opaque menu background
    public static final int BG_PANEL         = 0xE6121016; // slightly lighter panel / slot background
    public static final int BG_PANEL_LIGHT   = 0xE01B1820; // hovered panel background
    public static final int OUTLINE          = 0x552A2733; // subtle 1px borders

    // ---- Accents (chosen for even brightness + good contrast on black) --
    public static final int ACCENT_VIOLET    = 0xFF8C5CF0; // primary accent — buttons, selection
    public static final int ACCENT_TEAL      = 0xFF2FD1B3; // success / positive / confirm
    public static final int ACCENT_GOLD      = 0xFFE0B23C; // highlights, hover glow, XP-style bar
    public static final int ACCENT_CRIMSON   = 0xFFE0526B; // danger, cancel, low health
    public static final int ACCENT_BLUE      = 0xFF4FA3E3; // links / info / progress bars

    // ---- Button states --------------------------------------------------
    public static final int BUTTON_IDLE      = 0xF0161420;
    public static final int BUTTON_HOVER     = 0xF0221D33; // shifts toward ACCENT_VIOLET
    public static final int BUTTON_DISABLED  = 0xA00E0D12;
    public static final int BUTTON_BORDER_IDLE   = 0x662A2740;
    public static final int BUTTON_BORDER_HOVER  = ACCENT_VIOLET;

    public static final int TEXT_PRIMARY     = 0xFFEDEBF5;
    public static final int TEXT_MUTED       = 0xFF8C889B;
    public static final int TEXT_DISABLED    = 0xFF55515F;

    // ---- Tooltip -----------------------------------------------------
    public static final int TOOLTIP_BG_TOP    = 0xF0100E18;
    public static final int TOOLTIP_BG_BOTTOM = 0xF0100E18;
    public static final int TOOLTIP_BORDER_TOP    = 0x888C5CF0; // violet glow
    public static final int TOOLTIP_BORDER_BOTTOM = 0x882FD1B3; // teal glow, gradient border

    // ---- Slider / progress --------------------------------------------
    public static final int SLIDER_TRACK      = 0xFF161420;
    public static final int SLIDER_FILLED     = ACCENT_VIOLET;
    public static final int SLIDER_HANDLE     = ACCENT_GOLD;

    /** Blend two ARGB colors, t in [0,1]. Used for hover/press interpolation. */
    public static int lerpArgb(int colorA, int colorB, float t) {
        int aA = (colorA >> 24) & 0xFF, rA = (colorA >> 16) & 0xFF, gA = (colorA >> 8) & 0xFF, bA = colorA & 0xFF;
        int aB = (colorB >> 24) & 0xFF, rB = (colorB >> 16) & 0xFF, gB = (colorB >> 8) & 0xFF, bB = colorB & 0xFF;
        int a = (int) (aA + (aB - aA) * t);
        int r = (int) (rA + (rB - rA) * t);
        int g = (int) (gA + (gB - gA) * t);
        int b = (int) (bA + (bB - bA) * t);
        return (a << 24) | (r << 16) | (g << 8) | b;
    }
}
