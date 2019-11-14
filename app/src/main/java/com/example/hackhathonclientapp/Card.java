package com.example.hackhathonclientapp;

public class Card {
    private String cardId;

    public enum RequestTypes {
        AddCardRequest
    }


    public void SetCardId(String cardId) {
        this.cardId = cardId;
    }

    public String GetCardId() {
        return cardId;
    }

    public Card(String cardId) {
        this.cardId = cardId;
    }

    public String GetRequest(RequestTypes requestTypes, Card card) {
        return "^~" + requestTypes.toString() + "~" + card.cardId + "~^";
    }
}

