package org.example;

/*
    Implementation of Karp-Rabin algorithm for finding patterns in text
 */
public class KarpRabin {
    int r;
    int mod;
    long pow;

    public KarpRabin(){
        this.r = 256;
        this.mod = 1000000007;
        this.pow = 1;
    }

    public KarpRabin(int r, int mod){
        this.r = r;
        this.mod = mod;
        this.pow = 1;
    }

    public void setPower(int length){
        for(int i = 0; i<length-1; i++){
            this.pow = (this.pow * r) %mod;
        }
    }

    public long createHash(String text){
        int m = text.length();
        long hash = 0;

        for (int i = 0; i <= m-1; i++) {
            hash = ( hash * r + (int) text.charAt(i) )%mod;
        }
        return hash;
    }

    public long moveWindow(long hash, char toRemove, char toAdd){

        hash = ((hash - (toRemove * (pow))%mod) + mod)%mod;
        hash = (hash * r) % mod;
        hash = (hash + toAdd) % mod;

        return hash;
    }
}
