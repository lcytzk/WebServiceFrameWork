package com.yztz.stocktrade.facade.receiver;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.yztz.common.message.CustomerListener;
import com.yztz.model.enums.NotifyPriorityEnum;
import com.yztz.stocktrade.biz.service.SmsService;
import com.yztz.stocktrade.dao.domain.UserSmsDO;
import com.yztz.stocktrade.facade.release.dto.UserSmsDTO;

/**
 * @author Liang Chenye
 * @version $Id: SmsServiceFacadeImpl, v 0.1 2015/6/23 11:17
 */
@Component
public class SmsReceiver implements CustomerListener<Object> {

    @Autowired
    private SmsService smsService;

    private static NotifyPriorityEnum DEFAULT_PRIORITY = NotifyPriorityEnum.PRIORITY_3;

    public void receiveMessage(Object obj) {
        if (obj instanceof UserSmsDTO) {
            sendSms((UserSmsDTO) obj);
        }
    }

    private void sendSms(UserSmsDTO userSmsDTO) {
        UserSmsDO userSmsDO = new UserSmsDO();
        userSmsDO.setUserId(userSmsDTO.getUserId());
        userSmsDO.setMsg(userSmsDTO.getMsg());
        userSmsDO.setCause(userSmsDTO.getCause());
        userSmsDO.setPriority(DEFAULT_PRIORITY);
        smsService.sendUserSms(userSmsDO);
    }
}
