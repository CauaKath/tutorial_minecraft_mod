package net.kath.medieval_rpg_for_dummies.client;

public class ClientThirstData {

  private static int playerThirst;

  public static void setPlayerThirst(int thirst) {
    ClientThirstData.playerThirst = thirst;
  }

  public static int getPlayerThirst() {
    return playerThirst;
  }

}
