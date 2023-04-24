package net.kath.medieval_rpg_for_dummies.thirst;

import net.minecraft.nbt.CompoundTag;

public class PlayerThirst {

  private int thirst;
  private final int MIN_THIRST = 0;
  private final int MAX_THIRST = 10;

  public int getThirst() {
    return this.thirst;
  }

  public void addThirst(int thirst) {
    this.thirst = Math.min(this.thirst + thirst, MAX_THIRST);
  }

  public void subThirst(int thirst) {
    this.thirst = Math.max(this.thirst - thirst, MIN_THIRST);
  }

  public void copyFrom(PlayerThirst source) {
    this.thirst = source.thirst;
  }

  public void saveNBTData(CompoundTag nbt) {
    nbt.putInt("thirst", this.thirst);
  }

  public void loadNBTData(CompoundTag nbt) {
    this.thirst = nbt.getInt("thirst");
  }

}
