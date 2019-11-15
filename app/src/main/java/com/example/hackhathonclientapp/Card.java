package com.example.hackhathonclientapp;

import java.util.ArrayList;

public class Card {
    private String cardId;
    private String cardType;

    public static ArrayList <Card> CardList = new ArrayList<>();

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

    public Card(String cardType) {
        this.cardType = cardType;
    }

    @Override
    public String toString()
    {
        return cardType;
    }

//    public String GetRequest(RequestTypes requestTypes, Card card) {
//        return "^~" + requestTypes.toString() + "~" + card.cardId + "~^";
//    }
}

