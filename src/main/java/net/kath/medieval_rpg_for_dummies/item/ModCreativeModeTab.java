package net.kath.medieval_rpg_for_dummies.item;

import net.kath.medieval_rpg_for_dummies.MedievalRpgMod;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;

public class ModCreativeModeTab {

  public static final CreativeModeTab MEDIEVAL_RPG_FOR_DUMMIES_TAB = new CreativeModeTab(MedievalRpgMod.MOD_ID) {
    @Override
    public ItemStack makeIcon() {
      return new ItemStack(ModItems.GOSLIM.get());
    }
  };

}
