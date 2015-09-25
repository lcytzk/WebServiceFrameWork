/**
 * Yztz.com Inc.
 * Copyright (c) 2013-2015 All Rights Reserved.
 */

package com.yztz.stocktrade.facade.release.dto;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @author Liang Chenye
 * @version $Id: Scheme, v 0.1 2015/6/18 13:27
 */

public class SchemeInfo {

    /**
     * 方案ID
     */
    private Integer id;

    /**
     * 用户ID
     */
    private Integer userId;

    /**
     * 用户名
     */
    private String userName;

    /**
     * 比赛ID
     */
    private Integer matchId;

    /**
     * 关联用户ID
     */
    private Integer connectedUserId;

    /**
     * 借贷类型
     * <p/>
     * 100-按天配资,200-按周配资,300-按月配资,400-极惠配资,800-赢币配资,910-免费体验,920-实盘比赛,990-期货配资
     */
    private Integer type;

    /**
     * 借贷状态 1-投标中 2-验资中 3-开户中 4-借贷中 5-已完结 6-已流单 7-已撤单 0-已删除
     */
    private Integer status;

    /**
     * 配资保证金
     */
    private BigDecimal principal;

    /**
     * 配资杠杆
     */
    private Double lever;

    /**
     * 配资金额
     */
    private BigDecimal money;

    /**
     * 预警线
     */
    private BigDecimal warningLine;

    /**
     * 平仓线
     */
    private BigDecimal openLine;

    /**
     * 保证金追加金额
     */
    private BigDecimal principalAdditional;

    /**
     * 保证金提取金额
     */
    private BigDecimal principalWithdraw;

    /**
     * 利润提取金额
     */
    private BigDecimal profitWithdraw;

    /**
     * 发起时间
     */
    private Date initiateTime;

    /**
     * 开户时间
     */
    private Date accountTime;

    /**
     * 计息时间 默认为发起时间，开户完成时有后台人员设定计息开始时间
     */
    private Date interestTime;

    /**
     * 计息结束时间 默认为发起时间，开户完成后自动计算计息结束时间 计息结束时间=计息开始时间+借贷周期，周期1个月按30天计算
     */
    private Date interestEndTime;

    /**
     * 完结申请时间
     */
    private Date finishApplyTime;

    /**
     * 完结时间
     */
    private Date finishTime;

    /**
     * 停牌市值
     */
    private BigDecimal suspendedValue;

    /**
     * 停牌准备金
     */
    private BigDecimal investMargin;

    /**
     * 是否交了足够的停牌准备金
     */
    private Boolean enoughInvestMargin;

    public Boolean getEnoughInvestMargin() {
        return enoughInvestMargin;
    }

    public void setEnoughInvestMargin(Boolean enoughInvestMargin) {
        this.enoughInvestMargin = enoughInvestMargin;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getConnectedUserId() {
        return connectedUserId;
    }

    public void setConnectedUserId(Integer connectedUserId) {
        this.connectedUserId = connectedUserId;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public BigDecimal getPrincipal() {
        return principal;
    }

    public void setPrincipal(BigDecimal principal) {
        this.principal = principal;
    }

    public Double getLever() {
        return lever;
    }

    public void setLever(Double lever) {
        this.lever = lever;
    }

    public BigDecimal getMoney() {
        return money;
    }

    public void setMoney(BigDecimal money) {
        this.money = money;
    }

    public BigDecimal getWarningLine() {
        return warningLine;
    }

    public void setWarningLine(BigDecimal warningLine) {
        this.warningLine = warningLine;
    }

    public BigDecimal getOpenLine() {
        return openLine;
    }

    public void setOpenLine(BigDecimal openLine) {
        this.openLine = openLine;
    }

    public BigDecimal getPrincipalAdditional() {
        return principalAdditional;
    }

    public void setPrincipalAdditional(BigDecimal principalAdditional) {
        this.principalAdditional = principalAdditional;
    }

    public BigDecimal getPrincipalWithdraw() {
        return principalWithdraw;
    }

    public void setPrincipalWithdraw(BigDecimal principalWithdraw) {
        this.principalWithdraw = principalWithdraw;
    }

    public BigDecimal getProfitWithdraw() {
        return profitWithdraw;
    }

    public void setProfitWithdraw(BigDecimal profitWithdraw) {
        this.profitWithdraw = profitWithdraw;
    }

    public Date getInitiateTime() {
        return initiateTime;
    }

    public void setInitiateTime(Date initiateTime) {
        this.initiateTime = initiateTime;
    }

    public Date getAccountTime() {
        return accountTime;
    }

    public void setAccountTime(Date accountTime) {
        this.accountTime = accountTime;
    }

    public Date getInterestTime() {
        return interestTime;
    }

    public void setInterestTime(Date interestTime) {
        this.interestTime = interestTime;
    }

    public Date getInterestEndTime() {
        return interestEndTime;
    }

    public void setInterestEndTime(Date interestEndTime) {
        this.interestEndTime = interestEndTime;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Integer getMatchId() {
        return matchId;
    }

    public void setMatchId(Integer matchId) {
        this.matchId = matchId;
    }

    public Date getFinishApplyTime() {
        return finishApplyTime;
    }

    public void setFinishApplyTime(Date finishApplyTime) {
        this.finishApplyTime = finishApplyTime;
    }

    public Date getFinishTime() {
        return finishTime;
    }

    public void setFinishTime(Date finishTime) {
        this.finishTime = finishTime;
    }

    public BigDecimal getSuspendedValue() {
        return suspendedValue;
    }

    public void setSuspendedValue(BigDecimal suspendedValue) {
        this.suspendedValue = suspendedValue;
    }

    public BigDecimal getInvestMargin() {
        return investMargin;
    }

    public void setInvestMargin(BigDecimal investMargin) {
        this.investMargin = investMargin;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;

        SchemeInfo that = (SchemeInfo) o;

        if (id != null ? !id.equals(that.id) : that.id != null)
            return false;
        if (userId != null ? !userId.equals(that.userId) : that.userId != null)
            return false;
        if (userName != null ? !userName.equals(that.userName) : that.userName != null)
            return false;
        if (matchId != null ? !matchId.equals(that.matchId) : that.matchId != null)
            return false;
        if (connectedUserId != null ? !connectedUserId.equals(that.connectedUserId) : that.connectedUserId != null)
            return false;
        if (type != null ? !type.equals(that.type) : that.type != null)
            return false;
        if (status != null ? !status.equals(that.status) : that.status != null)
            return false;
        if (principal != null ? !principal.equals(that.principal) : that.principal != null)
            return false;
        if (lever != null ? !lever.equals(that.lever) : that.lever != null)
            return false;
        if (money != null ? !money.equals(that.money) : that.money != null)
            return false;
        if (warningLine != null ? !warningLine.equals(that.warningLine) : that.warningLine != null)
            return false;
        if (openLine != null ? !openLine.equals(that.openLine) : that.openLine != null)
            return false;
        if (principalAdditional != null ?
                !principalAdditional.equals(that.principalAdditional) :
                that.principalAdditional != null)
            return false;
        if (principalWithdraw != null ?
                !principalWithdraw.equals(that.principalWithdraw) :
                that.principalWithdraw != null)
            return false;
        if (profitWithdraw != null ? !profitWithdraw.equals(that.profitWithdraw) : that.profitWithdraw != null)
            return false;
        if (initiateTime != null ? !initiateTime.equals(that.initiateTime) : that.initiateTime != null)
            return false;
        if (accountTime != null ? !accountTime.equals(that.accountTime) : that.accountTime != null)
            return false;
        if (interestTime != null ? !interestTime.equals(that.interestTime) : that.interestTime != null)
            return false;
        if (interestEndTime != null ? !interestEndTime.equals(that.interestEndTime) : that.interestEndTime != null)
            return false;
        if (finishApplyTime != null ? !finishApplyTime.equals(that.finishApplyTime) : that.finishApplyTime != null)
            return false;
        if (finishTime != null ? !finishTime.equals(that.finishTime) : that.finishTime != null)
            return false;
        if (suspendedValue != null ? !suspendedValue.equals(that.suspendedValue) : that.suspendedValue != null)
            return false;
        if (investMargin != null ? !investMargin.equals(that.investMargin) : that.investMargin != null)
            return false;
        return !(enoughInvestMargin != null ?
                !enoughInvestMargin.equals(that.enoughInvestMargin) :
                that.enoughInvestMargin != null);

    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (userId != null ? userId.hashCode() : 0);
        result = 31 * result + (userName != null ? userName.hashCode() : 0);
        result = 31 * result + (matchId != null ? matchId.hashCode() : 0);
        result = 31 * result + (connectedUserId != null ? connectedUserId.hashCode() : 0);
        result = 31 * result + (type != null ? type.hashCode() : 0);
        result = 31 * result + (status != null ? status.hashCode() : 0);
        result = 31 * result + (principal != null ? principal.hashCode() : 0);
        result = 31 * result + (lever != null ? lever.hashCode() : 0);
        result = 31 * result + (money != null ? money.hashCode() : 0);
        result = 31 * result + (warningLine != null ? warningLine.hashCode() : 0);
        result = 31 * result + (openLine != null ? openLine.hashCode() : 0);
        result = 31 * result + (principalAdditional != null ? principalAdditional.hashCode() : 0);
        result = 31 * result + (principalWithdraw != null ? principalWithdraw.hashCode() : 0);
        result = 31 * result + (profitWithdraw != null ? profitWithdraw.hashCode() : 0);
        result = 31 * result + (initiateTime != null ? initiateTime.hashCode() : 0);
        result = 31 * result + (accountTime != null ? accountTime.hashCode() : 0);
        result = 31 * result + (interestTime != null ? interestTime.hashCode() : 0);
        result = 31 * result + (interestEndTime != null ? interestEndTime.hashCode() : 0);
        result = 31 * result + (finishApplyTime != null ? finishApplyTime.hashCode() : 0);
        result = 31 * result + (finishTime != null ? finishTime.hashCode() : 0);
        result = 31 * result + (suspendedValue != null ? suspendedValue.hashCode() : 0);
        result = 31 * result + (investMargin != null ? investMargin.hashCode() : 0);
        result = 31 * result + (enoughInvestMargin != null ? enoughInvestMargin.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("SchemeInfo, {");
        sb.append("id=");
        sb.append(id);
        sb.append(", userId=");
        sb.append(userId);
        sb.append(", userName=");
        sb.append(userName);
        sb.append(", matchId=");
        sb.append(matchId);
        sb.append(", connectedUserId=");
        sb.append(connectedUserId);
        sb.append(", type=");
        sb.append(type);
        sb.append(", status=");
        sb.append(status);
        sb.append(", principal=");
        sb.append(principal);
        sb.append(", lever=");
        sb.append(lever);
        sb.append(", money=");
        sb.append(money);
        sb.append(", warningLine=");
        sb.append(warningLine);
        sb.append(", openLine=");
        sb.append(openLine);
        sb.append(", principalAdditional=");
        sb.append(principalAdditional);
        sb.append(", principalWithdraw=");
        sb.append(principalWithdraw);
        sb.append(", profitWithdraw=");
        sb.append(profitWithdraw);
        sb.append(", initiateTime=");
        sb.append(initiateTime);
        sb.append(", accountTime=");
        sb.append(accountTime);
        sb.append(", interestTime=");
        sb.append(interestTime);
        sb.append(", interestEndTime=");
        sb.append(interestEndTime);
        sb.append(", finishApplyTime=");
        sb.append(finishApplyTime);
        sb.append(", finishTime=");
        sb.append(finishTime);
        sb.append(", suspendedValue=");
        sb.append(suspendedValue);
        sb.append(", investMargin=");
        sb.append(investMargin);
        sb.append(", enoughInvestMargin=");
        sb.append(enoughInvestMargin);
        sb.append('}');
        return sb.toString();
    }
}
