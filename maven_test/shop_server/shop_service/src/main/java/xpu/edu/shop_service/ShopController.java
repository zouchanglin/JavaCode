package xpu.edu.shop_service;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.web.bind.annotation.*;
import xpu.edu.shop_common.ShopInfoInput;
import xpu.edu.shop_common.ShopInfoOutput;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.stream.Collectors;

/**
 * @author zouchanglin
 * @date 2020/6/15
 */
@RestController
@RequestMapping("/shop")
public class ShopController {
    private static final List<ShopInfo> list = Arrays.asList(
            new ShopInfo(UUID.randomUUID().toString(), "ThinkPad X1", 10),
            new ShopInfo(UUID.randomUUID().toString(), "MacBook Air", 5),
            new ShopInfo(UUID.randomUUID().toString(), "MacBook Pro", 20)
            );
    private static final CopyOnWriteArrayList<ShopInfo> collect = new CopyOnWriteArrayList<>(list);

    @GetMapping("show")
    public List<ShopInfoOutput> getAllShop(){
        return collect.stream()
                .map(x -> new ShopInfoOutput(x.getShopId(), x.getShopName()))
                .collect(Collectors.toList());
    }

    @PostMapping("create")
    public List<ShopInfoOutput> addOneShop(@RequestBody ShopInfoInput shopInfoInput){
        collect.add(new ShopInfo(UUID.randomUUID().toString(), shopInfoInput.getShopName(), shopInfoInput.getShopStock()));
        return collect.stream()
                .map(x -> new ShopInfoOutput(x.getShopId(), x.getShopName()))
                .collect(Collectors.toList());
    }
}

@Data
@AllArgsConstructor
class ShopInfo {
    private String shopId;
    private String shopName;
    private Integer shopStock;
}

