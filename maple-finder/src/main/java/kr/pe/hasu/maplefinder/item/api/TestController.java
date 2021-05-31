package kr.pe.hasu.maplefinder.item.api;

import kr.pe.hasu.maplefinder.item.application.ItemCrawlingService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Slf4j
@RestController
@RequestMapping("/items")
public class TestController {

    private final ItemCrawlingService itemCrawlingService;

    public TestController(ItemCrawlingService itemCrawlingService) {
        this.itemCrawlingService = itemCrawlingService;
    }

    @RequestMapping(value="/home")
    public String home() {
        return "index.html";
    }

    @ResponseBody
    @RequestMapping("/valueTest")
    public String valueTest() {
        try {
            itemCrawlingService.getItemList("하수언니");
        } catch (Exception e) {
            log.info("ERROR" + e.getMessage());
        }
        String value = "테스트 String";
        return value;
    }

}
