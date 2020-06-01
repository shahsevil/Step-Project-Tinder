package org.tinder_proj.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Like {
  private int id;
  private int who_id;
  private int whom_id;
  private boolean reaction;

  public Like(int who_id, int whom_id, boolean reaction) {
    this.who_id = who_id;
    this.whom_id = whom_id;
    this.reaction = reaction;
  }
}
