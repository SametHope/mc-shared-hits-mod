# Minecraft Shared Hits Mod

This is a simple Fabric mod that adds a gamerule to share damage taken by players with all other active players. It works on **single-player**, **integrated/LAN servers**, and **dedicated servers**.

Unlike most similar mods, shared hits in this mod share the same damage source, attack type, and direction of attack in addition to the amount of damage.

Official pages for this mod are:
- https://github.com/SametHope/mc-shared-hits-mod
- https://www.curseforge.com/minecraft/mc-mods/shared-hits-mod
- https://modrinth.com/mod/shared-hits-mod

## Gamerule

```
/gamerule shareHits [<value>]
```

- **Default:** `true`
- When enabled, any damage a player takes is also applied to all other active players.
- Only affects **survival mode** players; spectators and creative mode players are ignored. 

## Permissions

- Requires **operator permissions (level 2)** or higher to modify the gamerule just like any other gamerule.

## Notes

- This mod is only required on **servers** and will be ignored on clients.
- Works with all vanilla (and probably modded) damage mechanics, accounting for shields, knockback, resistance effects, etc.

## Video Showcase

This video shows an example of how hits are shared and applied to players with different effect and knockback resistances.

https://github.com/user-attachments/assets/a122339e-de20-42ad-86e4-facde3727f5c


