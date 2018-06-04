package com.cmf.mq;

import com.alibaba.fastjson.JSON;
import com.cmf.base.ResponseModel;
import com.cmf.config.ClientProperties;
import com.cmf.constants.SystemConstants;
import com.cmf.exception.XcHandlerException;
import com.cmf.exception.enums.HandlerExceptionEnums;
import com.cmf.exception.enums.HandlerMessageInterfaceEnums;
import com.cmf.utils.OkHttpUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

@Component
public class DealMsgHandler {
    private static final Logger logger = LoggerFactory.getLogger(DealMsgHandler.class);

    @Autowired
    private ClientProperties clientProperties;
    @Autowired
    private RestTemplate restTemplate;

    public ResponseModel dealMsg(String topic, String message){
        String interfaceUrl = clientProperties.getAddress()+ HandlerMessageInterfaceEnums.getUrl(topic);
        ResponseModel re;
        if (interfaceUrl!=null) {
//            Map<String,String> map = new HashMap<>();
//            map.put("data",message);
//            String res = OkHttpUtil.post(interfaceUrl,map);
//            logger.info(res);
//            re = JSON.parseObject(res,ResponseModel.class);
            MultiValueMap<String, String> requestEntity = new LinkedMultiValueMap<>();
            requestEntity.add("data",message);
            re = restTemplate.postForObject(interfaceUrl,requestEntity,ResponseModel.class);
            if(re==null||re.getResult()==null||re.getResult().intValue()!=SystemConstants.SUCCESS){
                throw new XcHandlerException(re.getMessage(),re.getResult());
            }
        }else{
            throw new XcHandlerException(HandlerExceptionEnums.HANDLER_CALL_INTERFACE_NOT_FOUND);
        }
        return re;

    }
}
