package entities;

public class Like {
  private int likerUserId;
  private int likedUserId;

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

  @Override
  public String toString() {
    return String.format("Like{likerUserId=%d, likedUserId=%d}", likerUserId, likedUserId);
  }
}
