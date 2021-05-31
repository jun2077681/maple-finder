package kr.pe.hasu.maplefinder.item.application;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class ItemCrawlingService {

    public void getItemList(String[] nicknames) throws Exception {
        for(String nickname: nicknames) {
            getItemList(nickname);
        }
    }

    public List<String> findItems(List<List<String>> itemList, String itemName) {

    }

    public List<List<String>> getItemList(String nickname) throws Exception {
        String url = "https://maplestory.nexon.com/Ranking/World/Total?c=" + nickname;
        Document doc = Jsoup.connect(url).get();
        String equipmentLink = doc
                .select(".search_com_chk a")
                .attr("href")
                .replace("?p", "/Inventory?p");

        url = "https://maplestory.nexon.com/" + equipmentLink;
        doc = Jsoup.connect(url).get();

        List<List<String>> items = new ArrayList<>();

        for (int i = 1; i <= 5; i++) {
            Elements tmp = doc.select(".tab0" + i + "_con_wrap")
                    .select(".inven_item_memo_title > h1 > a");

            items.add(
                    tmp.stream()
                    .map(x -> x.text())
                    .collect(Collectors.toList())
            );
        }

        return items;

        /*
        List<String> itemLinks = items.stream()
                .map(x -> x.attr("href"))
                .collect(Collectors.toList());

        url = "https://maplestory.nexon.com" + itemLinks.get(0);

        Document res = Jsoup.connect(url)
                .header("Host", "maplestory.nexon.com")
                .header("X-Requested-With", "XMLHttpRequest")
                .header("Connection", "close")
                .ignoreContentType(true)
                .get();

        JSONObject jsonObject = new JSONObject(res.text());
        res = Jsoup.parse(jsonObject.get("view").toString());
         */
    }
}
