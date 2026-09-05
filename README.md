# DuskUI — Fabric 1.20.1

Recolors the vanilla Minecraft interface into a near-black theme with a
curated accent palette, instead of leaving one flat color everywhere.

## Palette (see `DuskPalette.java`)
| Role | Color |
|---|---|
| Base background | near-black with a blue tint `#0A0A0D` |
| Panels / slots | `#12101 6` slightly lighter than base |
| Primary accent (buttons, selection) | violet `#8C5CF0` |
| Positive / confirm | teal `#2FD1B3` |
| Highlight / hover glow | gold `#E0B23C` |
| Danger / cancel | crimson `#E0526B` |
| Info / links | blue `#4FA3E3` |

Colors were picked at similar perceived brightness so no single button or
bar visually dominates the rest — the accents rotate by *role*, not
randomly.

## What it touches
- Every `Screen`'s background (options, pause menu, world select, chat, etc.)
- `ButtonWidget` fill + 1px border, with distinct idle/hover/disabled states
- Tooltips (item + button hover boxes) — violet→teal gradient border
- Sliders (Options → Video/Audio/etc.) — dark track, violet fill, gold handle
- Inventory-style screens (`HandledScreen`: inventory, chest, crafting table,
  furnace, anvil...) get the same dark wash behind their slot grid

## Compatibility
- **Sodium**: replaces the chunk/entity/particle *renderer*, never touches
  `Screen`/`ButtonWidget`/`SliderWidget` — no shared mixin targets.
- **Lithium**: server-side and game-logic optimizations only, zero UI code
  — no overlap at all.
- **Wurst**: Wurst's ClickGUI is drawn by Wurst's own screen classes, not
  vanilla's `Screen`/`ButtonWidget`, so DuskUI's mixins never touch it. Both
  can be installed together; Wurst's own GUI keeps its own theme unless you
  configure Wurst separately.

Load order doesn't matter for any of the three since there are no shared
mixin targets — Fabric Loader will happily merge all four mods' mixins.

## Building
1. Install a JDK 17.
2. From this folder: `./gradlew build` (first run downloads Loom + mappings,
   needs internet access).
3. The compiled jar appears in `build/libs/duskui-1.0.0.jar`.
4. Drop it into your `.minecraft/mods` folder alongside `fabric-api`,
   `sodium`, `lithium`, and `wurst`, all built for **1.20.1 / Fabric**.

## Known limitations / next steps
- The tooltip mixin (`TooltipRenderMixin`) targets an internal draw method
  whose exact name can shift between Yarn mapping builds — it's marked
  `require = 0` so a mismatch won't crash the game, it'll just skip that
  one recolor. If it doesn't apply, tell me the Yarn build you're on and
  I'll adjust the target.
- Slot backgrounds inside inventories still use vanilla's beige 9-slice
  sprite texture (only the screen-wide wash behind them is dark); a full
  slot-texture swap needs a resource pack layered on top of this mod —
  happy to generate one next if you want that too.
