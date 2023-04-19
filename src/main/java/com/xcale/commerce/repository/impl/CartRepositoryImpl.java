package com.xcale.commerce.repository.impl;

import com.xcale.commerce.config.exception.NotFoundException;
import com.xcale.commerce.model.entity.CartEntity;
import com.xcale.commerce.model.entity.ProductEntity;
import com.xcale.commerce.repository.CartRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Repository;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

@Slf4j
@Repository
@RefreshScope
public class CartRepositoryImpl implements CartRepository {
  private final Map<Integer, CartEntity> cartEntityMap = new ConcurrentHashMap<>();
  private static int id = 1;

  @Value("${carts.timer.expired}")
  private Integer minutes;

  @Override
  public CartEntity save(CartEntity cartEntity) {
    cartEntity.setId(id++);
    cartEntityMap.put(cartEntity.getId(), cartEntity);
    return cartEntity;
  }

  @Override
  public CartEntity update(int cartId, List<ProductEntity> products) {
    expireCarts();
    return Optional.ofNullable(cartEntityMap.get(cartId))
        .map(
            cart -> {
              cart.getProducts().addAll(products);
              cartEntityMap.put(cartId, cart);
              return cart;
            })
        .orElseThrow(() -> new NotFoundException("Cart not exist"));
  }

  @Override
  public CartEntity findById(int id) {
    expireCarts();
    return Optional.ofNullable(cartEntityMap.get(id))
        .orElseThrow(() -> new NotFoundException("Cart not exist"));
  }

  @Override
  public void delete(int id) {
    cartEntityMap.remove(id);
  }

  @Scheduled(fixedDelayString = "${carts.timer.delay}")
  public void expireCarts() {
    log.info("Start expireCarts()");
    LocalDateTime now = LocalDateTime.now();
    cartEntityMap.forEach(
        (cartId, cart) -> {
          if (Duration.between(cart.getCreated(), now).toMinutes() >= minutes) {
            delete(cart.getId());
          }
        });
  }
}
