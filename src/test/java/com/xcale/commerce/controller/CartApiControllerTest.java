package com.xcale.commerce.controller;

import com.xcale.commerce.model.dto.CartDto;
import com.xcale.commerce.service.CartService;
import com.xcale.commerce.util.FileUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static com.xcale.commerce.util.MockMvcUtils.getJacksonMessageConverterInstance;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

@ExtendWith(MockitoExtension.class)
class CartApiControllerTest {
  @Mock private CartService cartService;

  @InjectMocks private CartApiController cartApiController;

  protected MockMvc mockMvc;

  @BeforeEach
  public void setUp() {
    mockMvc =
        standaloneSetup(cartApiController)
            .setMessageConverters(getJacksonMessageConverterInstance())
            .build();
  }

  @Test
  void create() throws Exception {
    CartDto cartDto = FileUtils.loadObject("data/model/dto/cartDto-id.json", CartDto.class);
    String cart = FileUtils.toStringFromJson("data/model/dto/cartDto.json");
    String result = FileUtils.toStringFromJson("data/model/dto/cartDto-id.json");

    when(cartService.create(any(CartDto.class))).thenReturn(cartDto);

    mockMvc
        .perform(post("/v1/cart").content(cart).contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isCreated())
        .andExpect(content().json(result))
        .andExpect(content().contentType(MediaType.APPLICATION_JSON));

    verify(cartService).create(any(CartDto.class));
  }

  @Test
  void addProducts() throws Exception {
    CartDto cartDto = FileUtils.loadObject("data/model/dto/cartDto-id.json", CartDto.class);
    String products = FileUtils.toStringFromJson("data/model/dto/productDto-list.json");
    String result = FileUtils.toStringFromJson("data/model/dto/cartDto-id.json");

    when(cartService.addProducts(anyInt(), any())).thenReturn(cartDto);

    mockMvc
        .perform(
            post("/v1/cart/1/products").content(products).contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk())
        .andExpect(content().json(result))
        .andExpect(content().contentType(MediaType.APPLICATION_JSON));

    verify(cartService).addProducts(anyInt(), any());
  }

  @Test
  void getById() throws Exception {
    CartDto cartDto = FileUtils.loadObject("data/model/dto/cartDto-id.json", CartDto.class);
    String result = FileUtils.toStringFromJson("data/model/dto/cartDto-id.json");

    when(cartService.getById(anyInt())).thenReturn(cartDto);

    mockMvc
        .perform(get("/v1/cart/1"))
        .andExpect(status().isOk())
        .andExpect(content().json(result))
        .andExpect(content().contentType(MediaType.APPLICATION_JSON));

    verify(cartService).getById(anyInt());
  }

  @Test
  void deleteById() throws Exception {
    doNothing().when(cartService).deleteById(1);

    mockMvc
        .perform(delete("/v1/cart/1").contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk());

    verify(cartService).deleteById(anyInt());
  }
}
