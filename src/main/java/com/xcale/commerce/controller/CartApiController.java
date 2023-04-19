package com.xcale.commerce.controller;

import com.xcale.commerce.model.dto.CartDto;
import com.xcale.commerce.model.dto.ProductDto;
import com.xcale.commerce.service.CartService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@CrossOrigin("*")
@RequiredArgsConstructor
@RequestMapping("/v1/cart")
public class CartApiController implements CartApi {
  private final CartService cartService;

  @PostMapping
  public CartDto create(@RequestBody CartDto cartDto) {
    log.info("Start save(id) controller");
    CartDto cart = cartService.create(cartDto);
    log.info("End save(id) controller");
    return cart;
  }

  @PostMapping("/{id}/products")
  public CartDto addProducts(@PathVariable int id, @RequestBody List<ProductDto> products) {
    log.info("Start addProducts(id) controller");
    CartDto cartDto = cartService.addProducts(id, products);
    log.info("End addProducts(id) controller");
    return cartDto;
  }

  @GetMapping("/{id}")
  public CartDto getById(@PathVariable int id) {
    log.info("Start getById(id) controller");
    CartDto cartDto = cartService.getById(id);
    log.info("End getById(id) controller");
    return cartDto;
  }

  @DeleteMapping("/{id}")
  public void deleteById(@PathVariable int id) {
    log.info("Start deleteById(id) controller");
    cartService.deleteById(id);
    log.info("End deleteById(id) controller");
  }
}
