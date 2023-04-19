package com.xcale.commerce.controller;

import com.xcale.commerce.model.dto.CartDto;
import com.xcale.commerce.model.dto.ProductDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;

import java.util.List;

@Tag(name = "Cart Api", description = "Cart API Rest")
public interface CartApi {

  @Operation(
      summary = "Create a cart",
      description = "Create cart and add products",
      tags = {"Cart Api"},
      responses = {
        @ApiResponse(
            responseCode = "201",
            description = "Create a cart",
            content =
                @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = CartDto.class))),
        @ApiResponse(
            responseCode = "400",
            description = "HTTP Status 400 - Body incorrect",
            content = @Content),
        @ApiResponse(
            responseCode = "405",
            description = "HTTP Status 405 - Method Not Allowed",
            content =
                @Content(
                    mediaType = "application/json",
                    examples = {
                      @ExampleObject(
                          name = "Method Not Allowed",
                          description = "Method Not Allowed",
                          value =
                              "{\n"
                                  + "    \"status\": \"METHOD_NOT_ALLOWED\",\n"
                                  + "    \"timestamp\": \"2023-04-18T15:59:15.4097322\",\n"
                                  + "    \"message\": \"El método 'GET' no es soportado\",\n"
                                  + "    \"debugMessage\": \"Request method 'GET' not supported\",\n"
                                  + "    \"subErrors\": null\n"
                                  + "}")
                    }))
      })
  CartDto create(CartDto cartDto);

  @Operation(
      summary = "Add products a cart",
      description = "Add products a cart by id",
      tags = {"Cart Api"},
      responses = {
        @ApiResponse(
            responseCode = "200",
            description = "Add products",
            content =
                @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = CartDto.class))),
        @ApiResponse(
            responseCode = "400",
            description = "HTTP Status 400 - Body incorrect",
            content = @Content),
        @ApiResponse(
            responseCode = "405",
            description = "HTTP Status 405 - Method Not Allowed",
            content =
                @Content(
                    mediaType = "application/json",
                    examples = {
                      @ExampleObject(
                          name = "Method Not Allowed",
                          description = "Method Not Allowed",
                          value =
                              "{\n"
                                  + "    \"status\": \"METHOD_NOT_ALLOWED\",\n"
                                  + "    \"timestamp\": \"2023-04-18T15:59:15.4097322\",\n"
                                  + "    \"message\": \"El método 'GET' no es soportado\",\n"
                                  + "    \"debugMessage\": \"Request method 'GET' not supported\",\n"
                                  + "    \"subErrors\": null\n"
                                  + "}")
                    }))
      })
  CartDto addProducts(int id, List<ProductDto> products);

  @Operation(
      summary = "Get cart",
      description = "Get cart by id",
      tags = {"Cart Api"},
      responses = {
        @ApiResponse(
            responseCode = "200",
            description = "Get cart",
            content =
                @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = CartDto.class))),
        @ApiResponse(
            responseCode = "404",
            description = "HTTP Status 404 - Not Found",
            content =
                @Content(
                    mediaType = "application/json",
                    examples = {
                      @ExampleObject(
                          name = "Not Found",
                          description = "Not Found",
                          value =
                              "{\n"
                                  + "    \"status\": \"NOT_FOUND\",\n"
                                  + "    \"timestamp\": \"2023-04-18T15:49:53.6691334\",\n"
                                  + "    \"message\": \"Cart not exist\",\n"
                                  + "    \"debugMessage\": \"Cart not exist\",\n"
                                  + "    \"subErrors\": null\n"
                                  + "}")
                    })),
        @ApiResponse(
            responseCode = "405",
            description = "HTTP Status 405 - Method Not Allowed",
            content =
                @Content(
                    mediaType = "application/json",
                    examples = {
                      @ExampleObject(
                          name = "Method Not Allowed",
                          description = "Method Not Allowed",
                          value =
                              "{\n"
                                  + "    \"status\": \"METHOD_NOT_ALLOWED\",\n"
                                  + "    \"timestamp\": \"2023-04-18T15:59:15.4097322\",\n"
                                  + "    \"message\": \"El método 'POST' no es soportado\",\n"
                                  + "    \"debugMessage\": \"Request method 'POST' not supported\",\n"
                                  + "    \"subErrors\": null\n"
                                  + "}")
                    }))
      })
  CartDto getById(int id);

  @Operation(
      summary = "Delete cart",
      description = "Delete cart by id",
      tags = {"Cart Api"},
      responses = {
        @ApiResponse(responseCode = "200", description = "Delete cart", content = @Content),
        @ApiResponse(
            responseCode = "404",
            description = "HTTP Status 404 - Not Found",
            content = @Content),
        @ApiResponse(
            responseCode = "405",
            description = "HTTP Status 405 - Method Not Allowed",
            content =
                @Content(
                    mediaType = "application/json",
                    examples = {
                      @ExampleObject(
                          name = "Method Not Allowed",
                          description = "Method Not Allowed",
                          value =
                              "{\n"
                                  + "    \"status\": \"METHOD_NOT_ALLOWED\",\n"
                                  + "    \"timestamp\": \"2023-04-18T15:59:15.4097322\",\n"
                                  + "    \"message\": \"El método 'POST' no es soportado\",\n"
                                  + "    \"debugMessage\": \"Request method 'POST' not supported\",\n"
                                  + "    \"subErrors\": null\n"
                                  + "}")
                    }))
      })
  void deleteById(int id);
}
