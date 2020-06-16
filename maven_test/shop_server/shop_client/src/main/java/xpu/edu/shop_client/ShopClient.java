package xpu.edu.shop_client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import xpu.edu.shop_common.ShopInfoInput;
import xpu.edu.shop_common.ShopInfoOutput;

import java.util.List;

/**
 * @author zouchanglin
 * @date 2020/6/15
 */
@FeignClient(name = "SHOP-CLIENT")
public interface ShopClient {

    @GetMapping("/shop/show")
    List<ShopInfoInput> getAllShop();

    @PostMapping("/shop/create")
    List<ShopInfoOutput> addOneShop(@RequestBody ShopInfoInput shopInfoInput);
}
