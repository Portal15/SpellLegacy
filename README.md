<div align="center">

# ✨ SpellLegacy

**A little magic. A powerful wand. Your own legacy.**

A Java Minecraft plugin bringing spellcasting, magical items, and custom spell loadouts to your server.

![Spigot API](https://img.shields.io/badge/Spigot_API-1.20.4-orange?style=for-the-badge)
![Build](https://img.shields.io/badge/Build-Maven-blue?style=for-the-badge)
![Status](https://img.shields.io/badge/Status-In_Development-8957e5?style=for-the-badge)

[Getting started](#-getting-started) · [Spells](#-the-spellbook) · [Commands](#-commands--permissions) · [Development](#-development)

</div>

---

## 🪄 Make every encounter magical

Craft a wand, learn your spells, and choose three to carry into your next adventure. Switch between them as you play: summon a target, shield yourself from an attack, or send a revealing wave through the world around you.

- **Nine registered spells** with combat, defense, movement, and utility effects.
- **Three spell slots** with quick switching and an action-bar display.
- **Custom crafting recipes** for a magic wand and spell book.
- **Inventory menus** for selecting spells and accessing admin tools.
- **Persistent progression** stored by player UUID in `plugins/SpellLegacy/spells.yml`.
- **Particles, sounds, and cooldowns** that give spells their own character.

## 📖 The spellbook

Use the lowercase spell IDs shown below in commands.

| Spell | ID | Effect |
| :--- | :--- | :--- |
| Protego | `protego` | Briefly shields the caster from damage. |
| Doomto | `doomto` | Temporarily retaliates against attackers with damage and a lightning effect. |
| Expelliarmus | `expelliarmus` | Disarms a target by dropping its held item. |
| Lumos | `lumos` | Creates light around the caster. |
| Stupfy | `stupfy` | Stuns a target. |
| Accio | `accio` | Pulls a target toward the caster. |
| Depulso | `depulso` | Pushes entities away. |
| Diffindo | `diffindo` | Launches a damaging magical slash. |
| Revelio | `revelio` | Sends out a revealing wave and temporarily highlights nearby living entities for the caster. |

> **Development note:** `defusion`, `incendio`, and `spuda` appear in the spell catalog but do not currently have registered casting implementations. Use the nine spells above for loadouts.

## 🚀 Getting started

### Install

The project currently targets **Spigot API `1.20.4-R0.1-SNAPSHOT`** and declares **ProtocolLib** as a required server dependency. The Maven dependency is pinned to ProtocolLib `4.8.0`; compatibility with your server and ProtocolLib combination should be tested, especially for Revelio's packet-based highlighting.

1. Build the plugin using the instructions below.
2. Stop your server and place the built SpellLegacy JAR in its `plugins/` directory.
3. Install a ProtocolLib version compatible with your server.
4. Start the server and check its log for `SpellLegacy has been enabled!`.

### Cast your first spell

With the appropriate permission, run these commands **in game**:

```text
/learn protego
/learn accio
/learn revelio
/selectSpells protego accio revelio
```

Craft a wand using the recipe below, then hold it in your main hand.

| Control | Action |
| :--- | :--- |
| **Right-click** | Cast the currently selected spell. |
| **Swap hands** (default: **F**) | Cycle through your three spell slots. |
| `/mySpells` | View the spells you have learned. |
| `/selectSpells` | Open the spell selection menu. |

Select all three slots before casting. Opening the selection menu currently clears the previous selection, so finish choosing your spells before using the wand again.

## ⚒️ Crafting

Each grid represents a crafting table. A dot is an empty slot.

### Magic wand

```text
. A E
. S A
G . .
```

**A** — Amethyst Shard · **E** — Ender Pearl · **S** — Stick · **G** — Gold Ingot

### Spell book

```text
A G A
G B G
A G A
```

**A** — Amethyst Shard · **G** — Gold Ingot · **B** — Book

## ⌨️ Commands & permissions

`<argument>` is required; `[argument]` is optional.

| Command | Purpose | Permission |
| :--- | :--- | :--- |
| `/mySpells` | Display your learned spells. | — |
| `/learn <spell> [player]` | Teach a spell to yourself or an online player. | `spelllegacy.learn` |
| `/unLearn <spell> [player]` | Remove a learned spell from yourself or an online player. | `spelllegacy.unlearn` |
| `/selectSpells [spell1 spell2 spell3]` | Equip three learned spells, or open the selection menu. | — |
| `/adminMenu` | Open the administration menu. | `spelllegacy.admin` |
| `/safeReload` | Run the plugin's reload command. | `spelllegacy.reload` |

The console must supply a player name for `/learn` and `/unLearn`. `/test` is also registered as a development command.

## 🛠️ Development

### Build from source

Use Maven and a JDK capable of compiling the configured Java 16 source/target level. The server runtime must also meet the requirements of your chosen Minecraft version.

```bash
git clone https://github.com/Portal15/SpellLegacy.git
cd SpellLegacy
```

**Before building:** the `maven-jar-plugin` configuration in `pom.xml` contains an absolute output path pointing to the author's local server. Replace its `<outputDirectory>` value with `${project.build.directory}` to write the JAR into this checkout's `target/` directory.

```bash
mvn clean package
```

With that output path configured, the plugin artifact is `target/SpellLegacy-1.0-SNAPSHOT.jar`.

The POM also contains a legacy `java.version` property set to `1.8`; the compiler plugin explicitly sets source and target to **16**.

### Open in IntelliJ IDEA

1. Open the repository's `pom.xml` as a Maven project.
2. Set a suitable project SDK and Maven runner JDK.
3. Reload Maven dependencies.
4. Configure the JAR output directory as described above.
5. Run **Lifecycle → package** in the Maven tool window.

### Project layout

```text
src/main/
├── java/portal15/spelllegacy/
│   ├── Commands/       # Player and administration commands
│   ├── Files/          # Persistent spell data
│   ├── Items/          # Custom magical items
│   ├── Listeners/      # Gameplay event handlers
│   ├── Menus/          # Inventory menus and GUI screens
│   ├── Spells/         # Spell implementations and shared behavior
│   ├── Utils/          # Shared helpers
│   └── SpellLegacy.java
└── resources/
    ├── plugin.yml      # Plugin metadata, commands, and permissions
    └── spells.yml      # Default spell data resource
```

---

<div align="center">

**SpellLegacy — adds some magic!**
Created by [Portal15](https://github.com/Portal15)

</div>
