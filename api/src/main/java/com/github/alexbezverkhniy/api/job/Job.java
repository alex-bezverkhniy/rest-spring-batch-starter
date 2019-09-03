package com.github.alexbezverkhniy.api.job;

import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Job implements Serializable {
  private static final long serialVersionUID = 614449317017612130L;
  private String name;
}
