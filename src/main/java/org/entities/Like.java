package org.entities;

public class Like {
  private int likerUserId;
  private int likedUserId;
  private boolean action;

  public Like(int likerUserId, int likedUserId, boolean action) {
    this.likerUserId = likerUserId;
    this.likedUserId = likedUserId;
    this.action = action;
  }

  public Like(int likerUserId, int likedUserId) {
    this.likerUserId = likerUserId;
    this.likedUserId = likedUserId;
  }

  public int getLikerUserId() {
    return likerUserId;
  }

  public int getLikedUserId() {
    return likedUserId;
  }

  public boolean isAction() {
    return action;
  }

  @Override
  public String toString() {
    return String.format("Like{likerUserId=%d, likedUserId=%d, action=%s}", likerUserId, likedUserId, action);
  }
}
