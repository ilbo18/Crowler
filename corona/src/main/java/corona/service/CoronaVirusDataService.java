package corona.service;

import corona.dto.KoreaStat;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class CoronaVirusDataService {

    private static String KOREA_COVID_DATAS_URL = "http://ncov.mohw.go.kr/bdBoardList_Real.do?brdId=1&brdGubun=13";

    @PostConstruct
    public void getKoreaCovidDatas() throws IOException {
        Document doc = Jsoup.connect(KOREA_COVID_DATAS_URL).get();
        Elements contents = doc.select(".midsize tbody tr");

        List<?> list = new ArrayList<>();
        for(Element tag : contents) {
            System.out.println(tag);
            Elements tdContents = tag.select("td");
            KoreaStat koreaStat = KoreaStat.builder()
                    .country(tag.select("th").text())
                    .diffFromPrevDay(Integer.parseInt(tdContents.get(0).text().replaceAll("\\,", "" )))
                    .total(Integer.parseInt(tdContents.get(1).text().replaceAll("\\,", "" )))
                    .death(Integer.parseInt(tdContents.get(2).text().replaceAll("\\,", "" )))
                    .holding(Integer.parseInt(tdContents.get(3).text().replaceAll("\\,", "" )))
                    .released(Integer.parseInt(tdContents.get(5).text().replaceAll("\\,", "" )))
                    .incidence(Integer.parseInt(tdContents.get(6).text().replaceAll("\\,", "" )))
                    .inspection(Integer.parseInt(tdContents.get(7).text().replaceAll("\\-", "0" )))
                    .build();

            System.out.println(koreaStat.toString());
        }

//        contents.forEach(tag -> {
//            System.out.println(tag);
//        });

    }
}
