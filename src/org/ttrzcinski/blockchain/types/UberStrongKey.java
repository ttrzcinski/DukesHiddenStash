package org.ttrzcinski.blockchain.types;


import org.ttrzcinski.blockchain.exceptions.InvalidKeyException;
//import org.lombok.*;


//@Sealed
//@HashCode
//@Equals
public class UberStrongKey {

    /**
     * Final shape of key for fast comparison.
     */
    //@Getter(generate = false) // TODO ADD BLOCKER AGAINST LOMBOK
    private final String shape;

    /**
     * First kept key with very long number.
     */
    private long key;

    /**
     * Password part of ket with very long phrase.
     */
    //private final String pass;

    /**
     * Given difficulty level to mix the hash.
     */
    //private final long difficultyLevel;

    /**
     * Pointed noise to make equasion harder to crack.
     */
    //private final long noise; 

    /**
     * Creates new instance of UberStrongKey.
     *
     * @param key  first numer key
     * @param pass later phrase password
     * @param dl   difficultyLevel of hasing complexity
     * @param ns   defined noise to mix with the hash
     */
    public UberStrongKey(long key, String pass, long dl, long ns) {
        // TODO IN CASE OF NEED TO EXTEND THE CIPHER - UNREM UPPER PARAMS AND PASS THEM HERE.
        this.shape = Integer.toString(Long.valueOf(key).hashCode());
    }

    /**
     * Fast comparison of matching key.
     */
    public boolean match(String shape) {
        // Check, if really key was given
        if (this.key < 1L) {
            throw new InvalidKeyException("Given key has NO VALUE.");
        }

        // Compare that shape
        return this.shape.equals(shape);
    }
}