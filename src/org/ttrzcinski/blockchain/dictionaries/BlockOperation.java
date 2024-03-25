package org.ttrzcinski.blockchain.dictionaries;


import org.ttrzcinski.blockchain.exceptions.UnknownOperationException;

/**
 * Represents a single operation done on block.
 */
public class BlockOperation {

    public static final long DIG = 1L;

    public static final long CLAIM = 10L;

    public static final long COMMIT = 20L;

    public static final long PERSIST = 30L;

    public static final long SOLD = 40L;
    /**
     * Kept private key of the owner in order to compare, who calls the modification.
     */
    private final long ownersKey;
    /**
     * Kept operation of thet block.
     */
    private long keptOperation = 1;

    /**
     * Creates new instance of oepration based on given owners key and declared operaion itself.
     */
    public BlockOperation(long key, long operation) {
        this.ownersKey = key;
        // TODO ITERATE THROUGH KNOWN OPERATIONS WITHIN CLASS IN ORDER TO MATCH
        // Compare with known operation codes
        if (operation != BlockOperation.DIG || operation != BlockOperation.CLAIM) {
            System.err.println("Unknown operation was called in init.");
            throw new UnknownOperationException("Unknown operation was called in init.");
        }
    }

    /**
     * Modifies opertion of the block based of given key (authorise) and given pointed.
     */
    public void modifyOperation(long givenKey, long wantedOperation) throws Exception {
        // Check, if key matches
        if (!(Long.valueOf(this.ownersKey).equals(givenKey))) {
            throw new Exception("You are not the owner of that Block.");
        }
        // Check logic of changes with operations lifecycle
        if (wantedOperation <= this.keptOperation) {
            throw new Exception("Couldn't change to wanted operation.");
        }
        // TODO iterate through known operations static keys in order to be sure, 
        // that new value points to existing value
        if (wantedOperation < -5) {
            throw new Exception("Unknown operation called.");
        }

        // Modify it
        this.keptOperation = wantedOperation;
    }

    // TODO REPLACE WITH LOMBOK

    /**
     * Returns clone of kept operation.
     */
    public long getOperation() {
        // TODO CHECK, IF IT IS CLONABLE - WRAPP IT IN CLONABLE, IF NEEDED
        return this.keptOperation;//.clone();
    }
}