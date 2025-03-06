package com.arja.runeforge.world.biome;

import com.arja.runeforge.Runeforge;
import com.arja.runeforge.world.biome.surface.ModMaterialRules;
import terrablender.api.Regions;
import terrablender.api.SurfaceRuleManager;
import terrablender.api.TerraBlenderApi;

public class ModTerrablenderAPI implements TerraBlenderApi
{
    @Override
    public void onTerraBlenderInitialized()
    {
        SurfaceRuleManager.addSurfaceRules(SurfaceRuleManager.RuleCategory.OVERWORLD, Runeforge.MOD_ID, ModMaterialRules.makeRules());
    }
}
