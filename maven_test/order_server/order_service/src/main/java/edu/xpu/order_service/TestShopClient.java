package edu.xpu.order_service;

import com.alibaba.fastjson.JSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import xpu.edu.shop_client.ShopClient;
import xpu.edu.shop_common.ShopInfoInput;

/**
 * @author zouchanglin
 * @date 2020/6/16
 */
@RestController
@RequestMapping("/")
public class TestShopClient {
    @Autowired
    private ShopClient shopClient;

    @GetMapping
    public String test(){
        ShopInfoInput infoInput = new ShopInfoInput();
        infoInput.setShopName("iPad Pro");
        infoInput.setShopStock(100);
        shopClient.addOneShop(infoInput);
        return JSON.toJSONString(shopClient.getAllShop());
    }
}
