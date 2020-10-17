package com.dmos5.projeto_marisol_dmos5.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Ticket implements Serializable {

    @SerializedName("Age")
    @Expose
    private Integer age;

    @SerializedName("PriorityID")
    @Expose
    private String priorityID;

    @SerializedName("ServiceID")
    @Expose
    private String serviceID;

    @SerializedName("Type")
    @Expose
    private String type;

    @SerializedName("Responsible")
    @Expose
    private String responsible;

    @SerializedName("StateID")
    @Expose
    private String stateID;

    @SerializedName("ResponsibleID")
    @Expose
    private String responsibleID;

    @SerializedName("ChangeBy")
    @Expose
    private String changeBy;

    @SerializedName("EscalationTime")
    @Expose
    private String escalationTime;

    @SerializedName("OwnerID")
    @Expose
    private String ownerID;

    @SerializedName("Changed")
    @Expose
    private String changed;

    @SerializedName("RealTillTimeNotUsed")
    @Expose
    private String realTillTimeNotUsed;

    @SerializedName("GroupID")
    @Expose
    private String groupID;

    @SerializedName("Owner")
    @Expose
    private String owner;

    @SerializedName("CustomerID")
    @Expose
    private Object customerID;

    @SerializedName("TypeID")
    @Expose
    private Integer typeID;

    @SerializedName("Created")
    @Expose
    private String created;

    @SerializedName("Priority")
    @Expose
    private String priority;

    @SerializedName("UntilTime")
    @Expose
    private Integer untilTime;

    @SerializedName("EscalationUpdateTime")
    @Expose
    private String escalationUpdateTime;

    @SerializedName("Queue")
    @Expose
    private String queue;

    @SerializedName("QueueID")
    @Expose
    private String queueID;

    @SerializedName("State")
    @Expose
    private String state;

    @SerializedName("Title")
    @Expose
    private String title;

    @SerializedName("CreateBy")
    @Expose
    private String createBy;

    @SerializedName("TicketID")
    @Expose
    private String ticketID;

    @SerializedName("StateType")
    @Expose
    private String stateType;

    @SerializedName("UnlockTimeout")
    @Expose
    private String unlockTimeout;

    @SerializedName("EscalationResponseTime")
    @Expose
    private String escalationResponseTime;

    @SerializedName("EscalationSolutionTime")
    @Expose
    private String escalationSolutionTime;

    @SerializedName("LockID")
    @Expose
    private String lockID;

    @SerializedName("TicketNumber")
    @Expose
    private String ticketNumber;

    @SerializedName("ArchiveFlag")
    @Expose
    private String archiveFlag;

    @SerializedName("CreateTimeUnix")
    @Expose
    private String createTimeUnix;

    @SerializedName("Lock")
    @Expose
    private String lock;

    @SerializedName("SLAID")
    @Expose
    private String sLAID;

    @SerializedName("CustomerUserID")
    @Expose
    private Object customerUserID;

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getPriorityID() {
        return priorityID;
    }

    public void setPriorityID(String priorityID) {
        this.priorityID = priorityID;
    }

    public String getServiceID() {
        return serviceID;
    }

    public void setServiceID(String serviceID) {
        this.serviceID = serviceID;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getResponsible() {
        return responsible;
    }

    public void setResponsible(String responsible) {
        this.responsible = responsible;
    }

    public String getStateID() {
        return stateID;
    }

    public void setStateID(String stateID) {
        this.stateID = stateID;
    }

    public String getResponsibleID() {
        return responsibleID;
    }

    public void setResponsibleID(String responsibleID) {
        this.responsibleID = responsibleID;
    }

    public String getChangeBy() {
        return changeBy;
    }

    public void setChangeBy(String changeBy) {
        this.changeBy = changeBy;
    }

    public String getEscalationTime() {
        return escalationTime;
    }

    public void setEscalationTime(String escalationTime) {
        this.escalationTime = escalationTime;
    }

    public String getOwnerID() {
        return ownerID;
    }

    public void setOwnerID(String ownerID) {
        this.ownerID = ownerID;
    }

    public String getChanged() {
        return changed;
    }

    public void setChanged(String changed) {
        this.changed = changed;
    }

    public String getRealTillTimeNotUsed() {
        return realTillTimeNotUsed;
    }

    public void setRealTillTimeNotUsed(String realTillTimeNotUsed) {
        this.realTillTimeNotUsed = realTillTimeNotUsed;
    }

    public String getGroupID() {
        return groupID;
    }

    public void setGroupID(String groupID) {
        this.groupID = groupID;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public Object getCustomerID() {
        return customerID;
    }

    public void setCustomerID(Object customerID) {
        this.customerID = customerID;
    }

    public Integer getTypeID() {
        return typeID;
    }

    public void setTypeID(Integer typeID) {
        this.typeID = typeID;
    }

    public String getCreated() {
        return created;
    }

    public void setCreated(String created) {
        this.created = created;
    }

    public String getPriority() {
        return priority;
    }

    public void setPriority(String priority) {
        this.priority = priority;
    }

    public Integer getUntilTime() {
        return untilTime;
    }

    public void setUntilTime(Integer untilTime) {
        this.untilTime = untilTime;
    }

    public String getEscalationUpdateTime() {
        return escalationUpdateTime;
    }

    public void setEscalationUpdateTime(String escalationUpdateTime) {
        this.escalationUpdateTime = escalationUpdateTime;
    }

    public String getQueue() {
        return queue;
    }

    public void setQueue(String queue) {
        this.queue = queue;
    }

    public String getQueueID() {
        return queueID;
    }

    public void setQueueID(String queueID) {
        this.queueID = queueID;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }

    public String getTicketID() {
        return ticketID;
    }

    public void setTicketID(String ticketID) {
        this.ticketID = ticketID;
    }

    public String getStateType() {
        return stateType;
    }

    public void setStateType(String stateType) {
        this.stateType = stateType;
    }

    public String getUnlockTimeout() {
        return unlockTimeout;
    }

    public void setUnlockTimeout(String unlockTimeout) {
        this.unlockTimeout = unlockTimeout;
    }

    public String getEscalationResponseTime() {
        return escalationResponseTime;
    }

    public void setEscalationResponseTime(String escalationResponseTime) {
        this.escalationResponseTime = escalationResponseTime;
    }

    public String getEscalationSolutionTime() {
        return escalationSolutionTime;
    }

    public void setEscalationSolutionTime(String escalationSolutionTime) {
        this.escalationSolutionTime = escalationSolutionTime;
    }

    public String getLockID() {
        return lockID;
    }

    public void setLockID(String lockID) {
        this.lockID = lockID;
    }

    public String getTicketNumber() {
        return ticketNumber;
    }

    public void setTicketNumber(String ticketNumber) {
        this.ticketNumber = ticketNumber;
    }

    public String getArchiveFlag() {
        return archiveFlag;
    }

    public void setArchiveFlag(String archiveFlag) {
        this.archiveFlag = archiveFlag;
    }

    public String getCreateTimeUnix() {
        return createTimeUnix;
    }

    public void setCreateTimeUnix(String createTimeUnix) {
        this.createTimeUnix = createTimeUnix;
    }

    public String getLock() {
        return lock;
    }

    public void setLock(String lock) {
        this.lock = lock;
    }

    public String getSLAID() {
        return sLAID;
    }

    public void setSLAID(String sLAID) {
        this.sLAID = sLAID;
    }

    public Object getCustomerUserID() {
        return customerUserID;
    }

    public void setCustomerUserID(Object customerUserID) {
        this.customerUserID = customerUserID;
    }
}
