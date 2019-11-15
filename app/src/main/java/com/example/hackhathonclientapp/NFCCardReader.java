package com.example.hackhathonclientapp;


import android.nfc.NfcAdapter;
import android.nfc.Tag;

public class NFCCardReader implements NfcAdapter.ReaderCallback {
    public NFCCardReader( ) {
    }

    public static String tagID;
    @Override
    public void onTagDiscovered(Tag tag) {
        tagID = bytesToHexString(tag.getId()); //hex
    }

    private String bytesToHexString(byte[] src) {
        StringBuilder stringBuilder = new StringBuilder("0x");
        if (src == null || src.length <= 0) {
            return null;
        }

        long result = 0;
        for (int i = src.length - 1; i >= 0; --i) {
            result <<= 8;
            result |= src[i] & 0x0FF;
        }
        String strResult = "" + result;
        String resultToReturn = "";

        int zeroCount = 10 - strResult.length();
        for (int i = 0; i < zeroCount; i++){
            resultToReturn += "0";
        }
        resultToReturn += strResult;



        return "" + resultToReturn;
    }
}