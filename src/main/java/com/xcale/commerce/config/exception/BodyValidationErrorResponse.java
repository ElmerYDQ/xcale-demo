package com.xcale.commerce.config.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BodyValidationErrorResponse extends BodySubErrorResponse {
  private String object;
  private String field;
  private Object rejectedValue;
  private String message;

  BodyValidationErrorResponse(String object, String message) {
    this.object = object;
    this.message = message;
  }
}
