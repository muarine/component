/* 
 * RT MAP, Home of Professional MAP 
 * Copyright 2016 Bit Main Inc. and/or its affiliates and other contributors
 * as indicated by the @author tags. All rights reserved.
 * See the copyright.txt in the distribution for a
 * full listing of individual contributors.
 */
package test.com.rmtap.http;

import com.fasterxml.jackson.databind.JsonNode;
import com.rtmap.utils.http.RestfulTemplate;
import com.rtmap.wx.sdk.mp.event.InShakearoundUserShakeEvent;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import test.com.rmtap.card.AbstractCardTest;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

/**
 * TestHttp.    Test
 * 
 * @author Muarine <maoyun@rtmap.com>
 * @date   2016年4月17日
 * @since 1.0.0
 */
public class HttpTest extends AbstractCardTest {


    /**
     * 解决POST请求中文乱码的问题
     * @throws IOException
     * @throws URISyntaxException
     */
    public void testDownloadImage() throws IOException, URISyntaxException{
        String url = "http://dev.rtmap.com/component-web/card/wxf3a057928b881466/create";
//        Map<String,Object> multiValueMap = new LinkedHashMap<String,Object>();
        MultiValueMap<String,String> multiValueMap = new LinkedMultiValueMap<String,String>();
        multiValueMap.add("card_type", "5");
        multiValueMap.add("promoid", "100211");
        multiValueMap.add("default_detail", "优惠券详情介绍");
        multiValueMap.add("logo_url", "http://i2.sinaimg.cn/lx/fa/2009/1104/U3072P8T1D930551F913DT20091104151839.jpg");
        multiValueMap.add("code_type", "CODE_TYPE_ONLY_QRCODE");
        multiValueMap.add("logo_url", "http://ss.ss.com/aa");
        
        HttpHeaders headers = new HttpHeaders();
        MediaType type = MediaType.parseMediaType("application/x-www-form-urlencoded;charset=UTF-8");
        headers.setContentType(type);
        HttpEntity<MultiValueMap<String,String>> entity = new HttpEntity<MultiValueMap<String,String>>(multiValueMap , headers);  
        
        JsonNode node = RestfulTemplate.INSTANCE.postForObject(url, entity, JsonNode.class);
        System.out.println(node.toString());
        
    }


    public void testLbsPostBeaconinfo(){

        String postdata = "{\n" +
                "    \"toUserName\": \"gh_1127dff68423\", \n" +
                "    \"fromUserName\": \"oikd5jl2gw3PZUpo0bQHQEIihoF4\", \n" +
                "    \"createTime\": 1467862381, \n" +
                "    \"msgType\": \"event\", \n" +
                "    \"event\": \"ShakearoundUserShake\", \n" +
                "    \"uuid\": \"FDA50693-A4E2-4FB1-AFCF-C6EB07647825\", \n" +
                "    \"major\": 10010, \n" +
                "    \"minor\": 8585, \n" +
                "    \"distance\": 2.9633574, \n" +
                "    \"chosenPageId\": 3475298, \n" +
                "    \"aroundBeaconList\": [\n" +
                "        {\n" +
                "            \"uuid\": \"FDA50693-A4E2-4FB1-AFCF-C6EB07647825\", \n" +
                "            \"major\": 10010, \n" +
                "            \"minor\": 8539, \n" +
                "            \"distance\": 117.32239, \n" +
                "            \"rssi\": -96, \n" +
                "            \"measure\": -1\n" +
                "        }, \n" +
                "        {\n" +
                "            \"uuid\": \"FDA50693-A4E2-4FB1-AFCF-C6EB07647825\", \n" +
                "            \"major\": 10010, \n" +
                "            \"minor\": 8540, \n" +
                "            \"distance\": 61.999928, \n" +
                "            \"rssi\": -90, \n" +
                "            \"measure\": -1\n" +
                "        }, \n" +
                "        {\n" +
                "            \"uuid\": \"FDA50693-A4E2-4FB1-AFCF-C6EB07647825\", \n" +
                "            \"major\": 10010, \n" +
                "            \"minor\": 8541, \n" +
                "            \"distance\": 49.6899, \n" +
                "            \"rssi\": -88, \n" +
                "            \"measure\": -1\n" +
                "        }, \n" +
                "        {\n" +
                "            \"uuid\": \"FDA50693-A4E2-4FB1-AFCF-C6EB07647825\", \n" +
                "            \"major\": 10010, \n" +
                "            \"minor\": 8543, \n" +
                "            \"distance\": 49.6899, \n" +
                "            \"rssi\": -88, \n" +
                "            \"measure\": -1\n" +
                "        }, \n" +
                "        {\n" +
                "            \"uuid\": \"FDA50693-A4E2-4FB1-AFCF-C6EB07647825\", \n" +
                "            \"major\": 10010, \n" +
                "            \"minor\": 8544, \n" +
                "            \"distance\": 39.644444, \n" +
                "            \"rssi\": -86, \n" +
                "            \"measure\": -1\n" +
                "        }\n" +
                "    ]\n" +
                "}";


        InShakearoundUserShakeEvent msg = new InShakearoundUserShakeEvent("gh_1127dff68423" , "oikd5jl2gw3PZUpo0bQHQEIihoF4" , 1467862381 , "event" ,3475298L);

        msg.setMajor(10010);
        msg.setMinor(8585);
        msg.setUuid("FDA50693-A4E2-4FB1-AFCF-C6EB07647825");
        msg.setDistance(2.9633574f);
        List<InShakearoundUserShakeEvent.AroundBeacon> aroundBeaconList = new ArrayList<>();

        InShakearoundUserShakeEvent.AroundBeacon aroundBeacon1 = new InShakearoundUserShakeEvent.AroundBeacon();
        aroundBeacon1.setUuid("FDA50693-A4E2-4FB1-AFCF-C6EB07647825");
        aroundBeacon1.setMinor(8539);
        aroundBeacon1.setMajor(10010);
        aroundBeacon1.setDistance(117.32239f);
        aroundBeacon1.setRssi(-96f);
        aroundBeacon1.setMeasure(-1f);
        aroundBeaconList.add(aroundBeacon1);

        InShakearoundUserShakeEvent.AroundBeacon aroundBeacon2 = new InShakearoundUserShakeEvent.AroundBeacon();
        aroundBeacon2.setUuid("FDA50693-A4E2-4FB1-AFCF-C6EB07647825");
        aroundBeacon2.setMinor(8540);
        aroundBeacon2.setMajor(10010);
        aroundBeacon2.setDistance(61.999928f);
        aroundBeacon2.setRssi(-90f);
        aroundBeacon2.setMeasure(-1f);
        aroundBeaconList.add(aroundBeacon2);

        InShakearoundUserShakeEvent.AroundBeacon aroundBeacon3 = new InShakearoundUserShakeEvent.AroundBeacon();
        aroundBeacon3.setUuid("FDA50693-A4E2-4FB1-AFCF-C6EB07647825");
        aroundBeacon3.setMinor(8541);
        aroundBeacon3.setMajor(10010);
        aroundBeacon3.setDistance(49.6899f);
        aroundBeacon3.setRssi(-88f);
        aroundBeacon3.setMeasure(-1f);
        aroundBeaconList.add(aroundBeacon3);

        InShakearoundUserShakeEvent.AroundBeacon aroundBeacon4 = new InShakearoundUserShakeEvent.AroundBeacon();
        aroundBeacon4.setUuid("FDA50693-A4E2-4FB1-AFCF-C6EB07647825");
        aroundBeacon4.setMinor(8543);
        aroundBeacon4.setMajor(10010);
        aroundBeacon4.setDistance(49.6899f);
        aroundBeacon4.setRssi(-88f);
        aroundBeacon4.setMeasure(-1f);
        aroundBeaconList.add(aroundBeacon4);

        InShakearoundUserShakeEvent.AroundBeacon aroundBeacon5 = new InShakearoundUserShakeEvent.AroundBeacon();
        aroundBeacon5.setUuid("FDA50693-A4E2-4FB1-AFCF-C6EB07647825");
        aroundBeacon5.setMinor(8544);
        aroundBeacon5.setMajor(10010);
        aroundBeacon5.setDistance(39.644444f);
        aroundBeacon5.setRssi(-86.00f);
        aroundBeacon5.setMeasure(-1f);
        aroundBeaconList.add(aroundBeacon5);

        msg.setAroundBeaconList(aroundBeaconList);

        String url = "http://lbsapi.rtmap.com/rtmap_lbs_api/v1/rtmap/lbs_locateinfo";

        MultiValueMap<String,String> multiValueMap = new LinkedMultiValueMap<String,String>();
        multiValueMap.add("user_id", "oikd5jl2gw3PZUpo0bQHQEIihoF4");
        multiValueMap.add("user_id_desc", "微信寻鹿公众号openId");
        multiValueMap.add("key", "IR732EVNPJ");
        multiValueMap.add("latitude", "");
        multiValueMap.add("longitude", "");
        multiValueMap.add("apinfo", "[]");
        multiValueMap.add("beaconinfo", "");

        HttpHeaders headers = new HttpHeaders();
//        MediaType type = MediaType.parseMediaType("application/x-www-form-urlencoded;charset=UTF-8");
        MediaType type = MediaType.parseMediaType("application/json;charset=UTF-8");
        headers.setContentType(type);
        HttpEntity<MultiValueMap<String,String>> requestParam = new HttpEntity<MultiValueMap<String,String>>(multiValueMap , headers);
        RestfulTemplate.INSTANCE.postForObject(url , requestParam ,JsonNode.class);

    }

    
}
