package ua.com.social.demo.entity.impl;

import ua.com.social.demo.entity.DomainObject;

import java.sql.Timestamp;

public class Message implements DomainObject {
    private int messageId;
    private String messageContext;
    private Timestamp messageDate;
    private int conversationId;

    public Message(int messageId, String messageContext, Timestamp messageDate, int conversationId) {
        this.messageId = messageId;
        this.messageContext = messageContext;
        this.messageDate = messageDate;
        this.conversationId = conversationId;
    }

    public Message() {
    }

    public Message(String messageContext) {
        this.messageContext = messageContext;
    }

    public int getMessageId() {
        return messageId;
    }

    public void setMessageId(int messageId) {
        this.messageId = messageId;
    }

    public String getMessageContext() {
        return messageContext;
    }

    public void setMessageContext(String messageContext) {
        this.messageContext = messageContext;
    }

    public Timestamp getMessageDate() {
        return messageDate;
    }

    public void setMessageDate(Timestamp messageDate) {
        this.messageDate = messageDate;
    }

    public int getConversationId() {
        return conversationId;
    }

    public void setConversationId(int conversationId) {
        this.conversationId = conversationId;
    }
}
