package com.example.hackhathonclientapp;

public class Card {
    private String cardId;
    private String cardType;

    public enum RequestTypes {
        AddCardRequest
    }

    public void SetCardType(String cardId) {
        this.cardType = cardType;
    }

    public String GetCardType() {
        return cardType;
    }

    public void SetCardId(String cardId) {
        this.cardId = cardId;
    }

    public String GetCardId() {
        return cardId;
    }

    public Card(String cardId, String cardType) {
        this.cardId = cardId;
        this.cardType = cardType;
    }

//    public String GetRequest(RequestTypes requestTypes, Card card) {
//        return "^~" + requestTypes.toString() + "~" + card.cardId + "~^";
//    }
}

