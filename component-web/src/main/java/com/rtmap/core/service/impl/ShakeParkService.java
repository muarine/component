/* 
 * RT MAP, Home of Professional MAP 
 * Copyright 2015 Bit Main Inc. and/or its affiliates and other contributors
 * as indicated by the @author tags. All rights reserved.
 * See the copyright.txt in the distribution for a
 * full listing of individual contributors.
 */
package com.rtmap.core.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.rtmap.core.cache.KeyConfig;
import com.rtmap.utils.http.RestfulTemplate;
import com.rtmap.utils.json.JsonMapper;
import com.rtmap.wx.sdk.mp.event.InShakearoundUserShakeEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;
import org.springframework.web.client.AsyncRestTemplate;
import org.springframework.web.client.RestClientException;

/**
 * ShakeParkService.
 * 
 * @author Muarine maoyun@rtmap.com
 * @date 2015年7月14日
 * @since 2.0
 */
@Service
public class ShakeParkService {
	
	private static final Logger SHAKEAROUND_LOGGER = LoggerFactory.getLogger("Shake_Around");

//	@Autowired
//	private ShakeDeviceMapper shakeDeviceMapper;
//	@Autowired
//	private ShakeParkMapper shakeParkMapper;

    @Autowired
    private Memcache memcache;
	/**
	 * 摇一摇事件推送消息体
	 * @param msg
	 * @throws RestClientException
	 * @throws JsonProcessingException
     */
	public void parseShakeAround(InShakearoundUserShakeEvent msg) throws RestClientException, JsonProcessingException {
		// 1. 筛选西单大悦城的摇一摇
		/*if(Integer.valueOf(msg.getMajor()) == 10002){	// 西单大悦城
			Map<String,Object> map = new HashMap<String, Object>();
			map.put("uuid", msg.getUuid());
			map.put("major", msg.getMajor());
			map.put("minor", msg.getMinor());
			ShakeDevice device = shakeDeviceMapper.selectRecordByMap(map);
			if(null != device){
				String floor = device.getFloor() == null ? "":device.getFloor().toUpperCase();
				// 2. 继续筛选 B3B4停车场摇一摇
				if(floor.equals("B3") || floor.equals("B4")){
					// 3. 对接LBS接口
					List<Beacons> beaconss = new ArrayList<Beacons>();
					Beacons bc = new Beacons();
					bc.setAccuracy(msg.getDistance()+"");
					bc.setMajor(msg.getMajor());
					bc.setMinor(msg.getMinor());
					bc.setUuid(msg.getUuid());
					beaconss.add(bc);
					for (AroundBeacon around : msg.getAroundBeaconList()) {
						bc = new Beacons();
						bc.setUuid(msg.getUuid());	 // 会出现uuid=null情况
						bc.setAccuracy(around.getDistance()+"");
						bc.setMajor(around.getMajor());
						bc.setMinor(around.getMinor());
						beaconss.add(bc);
					}
					ObjectMapper mapper = new ObjectMapper();
					RestTemplate template = new RestTemplate();
					String beacons = mapper.writeValueAsString(beaconss);
					JsonNode node = template.getForObject("http://park.rtmap.com:8080/rtmap_lbs_api/v1/rtmap/beacon_lbsinfo?key=IR732EVNPJ&beacons={beacons}", JsonNode.class , beacons);
					if(node.get("result").get("error_code").asInt() == 0){
						log.debug("摇一摇事件推送对接LBS成功：{}",node.toString());
						double x = node.get("lbsinfo").get("x").asDouble();
						double y = node.get("lbsinfo").get("y").asDouble();
						DecimalFormat df = new DecimalFormat("#.000");
						// 4. 返回数据入库记录
						device.getBuild_id();
						ShakePark shakePark = new ShakePark();
						shakePark.setOpen_id(msg.getFromUserName());
						shakePark.setBuild_id(device.getBuild_id()+"");
						shakePark.setSelf_x(Double.valueOf(df.format(x)));
						shakePark.setSelf_y(Double.valueOf(df.format(y)));
						shakePark.setShake_time(new Date());
						shakePark.setFloor(floor);
						shakePark.setStatus(1);
						shakeParkMapper.insert(shakePark);
					}else{
						log.info("摇一摇事件推送对接LBS请求:{},响应结果：{}",
									"http://park.rtmap.com:8080/rtmap_lbs_api/v1/beacon_lbsinfo?key=IR732EVNPJ&beacons="+beacons,
									node.toString());
					}
				}
			}
		}*/

		// 摇一摇日志记录
//		ObjectMapper mapper = new ObjectMapper();
//		SHAKEAROUND_LOGGER.error("postdata:{}" , mapper.writeValueAsString(msg));
        AsyncRestTemplate asyncInstance = RestfulTemplate.ASYNC_INSTANCE;
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
        MediaType type = MediaType.parseMediaType("application/json;charset=UTF-8");
        headers.setContentType(type);
        HttpEntity<MultiValueMap<String,String>> requestParam = new HttpEntity<MultiValueMap<String,String>>(multiValueMap , headers);

        ListenableFuture<ResponseEntity<JsonNode>> future = asyncInstance.postForEntity(url , requestParam , JsonNode.class);
        future.addCallback(new FutureCallback(msg , memcache));

    }

    class FutureCallback<JsonNode> implements ListenableFutureCallback<JsonNode>{

        private final Logger LOGGER = LoggerFactory.getLogger(ShakeParkService.class);

        private InShakearoundUserShakeEvent msg;
        private Memcache memcache;

        public FutureCallback(InShakearoundUserShakeEvent msg , Memcache memcache) {
            this.msg = msg;
            this.memcache = memcache;
        }

        @Override
        public void onFailure(Throwable ex) {
            LOGGER.error("postdata:{}" , JsonMapper.toJsonString(msg));
            LOGGER.error(ex.getMessage() , ex);

        }

        @Override
        public void onSuccess(JsonNode result) {



            memcache.setKV(String.format(KeyConfig.Lbs_BeaconInfo.Authorizer_Openid_Shake_Result , msg.getToUserName() , msg.getFromUserName()) ,
                    "" ,
                    KeyConfig.HOUR_1);
            LOGGER.info("异步处理成功.");


        }
    }
}
