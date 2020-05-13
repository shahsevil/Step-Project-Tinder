package entities;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;

public class Message {

  private int fromUserId;
  private int toUserId;
  private String content;
  private long date;
  private String dateString;

  public Message(int fromUserId, int toUserId, String content, long date, String dateString) {
    this.fromUserId = fromUserId;
    this.toUserId = toUserId;
    this.content = content;
    this.date = date;
    this.dateString = dateString;
  }

  public Message(int fromId, int toId, String content, String dateString) {
    this.fromUserId = fromId;
    this.toUserId = toId;
    this.content = content;
    this.dateString = dateString;
  }

  public int getFromUserId() {
    return fromUserId;
  }

  public int getToUserId() {
    return toUserId;
  }

  public String getContent() {
    return content;
  }

  public long getDate() {
    return date;
  }

  public String getDateString() {
    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
    return sdf.format(date);
  }

  @Override
  public String toString() {
    return String.format("Message[fromUserId=%d, toUserId=%d, content='%s', date=%d, dateString='%s']",
            fromUserId, toUserId, content, date, dateString);
  }
}
