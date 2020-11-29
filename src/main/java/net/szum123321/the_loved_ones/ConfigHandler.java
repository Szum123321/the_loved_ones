package net.szum123321.the_loved_ones;

import blue.endless.jankson.Comment;
import io.github.cottonmc.cotton.config.annotations.ConfigFile;

@SuppressWarnings("CanBeFinal")
@ConfigFile(name = "the_loved_ones")
public class ConfigHandler {
    @Comment("\nShould pets be damaged by other players when PVP is enabled?\n")
    public boolean petsDamagePVP = true;

    @Comment("\nShould pets be damaged by other players when PVP is disabled?\n")
    public boolean petsDamageNoPVP = false;

    @Comment("\nShould pets be damaged by other players when playing on LAN?\n")
    public boolean petsDamageOnLAN = false;

}