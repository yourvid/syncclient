package com.cmf.exception.enums;

import org.apache.commons.lang3.StringUtils;

public enum  HandlerMessageInterfaceEnums implements MessageInterfaceEnums {
    USER_NEXT_ID("USER_NEXT_ID","/user/getNextUserId"),
    USER_INSERT("USER_INSERT","/user/save"),
    ORDER_INSERT("ORDER_INSERT","/order/save"),
    EQUIP_APPLY_INSERT("EQUIP_APPLY_INSERT","/equipApply/save"),
    CARD_APPLY_INSERT("CARD_APPLY_INSERT","/cardApply/save"),
    SERVICE_APPLY_INSERT("SERVICE_APPLY_INSERT","/serviceApply/save"),
    ORDER_COUPON_INSERT("ORDER_COUPON_INSERT","/orderCouponRelation/save"),
    FACTORY_INFO_INSERT("FACTORY_INFO_INSERT","/coreFactoryInfo/save"),
    BD_EQUIP_INFO_INSERT("BD_EQUIP_INFO_INSERT","/coreBdEquipInfo/save"),
    BD_EQUIP_MODEL_INSERT("BD_EQUIP_MODEL_INSERT","/coreBdEquipModel/save"),
    BD_EQUIP_TYPE_INSERT("BD_EQUIP_TYPE_INSERT","/coreBdEquipType/save");


    private String message;
    private String interfaceUrl;

    private HandlerMessageInterfaceEnums(String message, String interfaceUrl) {
        this.message = message;
        this.interfaceUrl = interfaceUrl;
    }

    public static String getUrl(String message) {
        if (StringUtils.isNotBlank(message)) {
            for (HandlerMessageInterfaceEnums c : HandlerMessageInterfaceEnums.values()) {
                if (c.getMessage().equals(message)) {
                    return c.getInterfaceUrl();
                }
            }
        }
        return null;
    }

    @Override
    public String getMessage() {
        return message;
    }

    @Override
    public String getInterfaceUrl() {
        return interfaceUrl;
    }
}
