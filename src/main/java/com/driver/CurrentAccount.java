package com.driver;

import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class CurrentAccount extends BankAccount{
    String tradeLicenseId; //consists of Uppercase English characters only
    public String getTradeLicenseId() {
        return tradeLicenseId;
    }
    public CurrentAccount(String name, double balance, String tradeLicenseId) throws Exception {
        // minimum balance is 5000 by default. If balance is less than 5000, throw "Insufficient Balance" exception
        super(name,balance,5000);
        this.tradeLicenseId = tradeLicenseId;


        if(balance < 5000)
            throw new InsufficientBalance();



    }

    public void validateLicenseId() throws Exception {
        // A trade license Id is said to be valid if no two consecutive characters are same
        // If the license Id is valid, do nothing
        // If the characters of the license Id can be rearranged to create any valid license Id
        // If it is not possible, throw "Valid License can not be generated" Exception

        String lic = this.tradeLicenseId;
        int n = this.tradeLicenseId.length();
        int target = n / 2;
        if(n % 2 != 0)
            target++;
        boolean valid = true;

        for(int i = 1; i < n; i++){
            if(lic.charAt(i -1) == lic.charAt(i) ){
                valid = false;
                break;
            }

        }
        if(valid)
            return;

        LinkedHashMap<Character,Integer> freq = new LinkedHashMap<>();
        char[] ans = new char[n];

        for(int i = 0; i < n; i++){
            char curr = lic.charAt(i);
            freq.put(curr,freq.getOrDefault(curr,0) + 1);
        }

       freq = freq.entrySet().stream()
                .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
                .collect(Collectors.toMap(Map.Entry::getKey,
                        Map.Entry::getValue,
                        (e1, e2) -> e1, LinkedHashMap::new));

        for(Map.Entry<Character,Integer> m : freq.entrySet()){
            char ch = m.getKey();
            int val = m.getValue();
            if(val > target){
                throw new RuntimeException("Valid License can not be generated");
            }

            for(int i = 0; i < n; i += 2){
                if( (int)ans[i] == 0 && val != 0){
                ans[i] = ch;
                val--;
                }
            }

        }
        this.tradeLicenseId = ans.toString();



    }

}
