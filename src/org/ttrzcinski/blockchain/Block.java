package org.ttrzcinski.blockchain;

import lombok.Getter;
import org.ttrzcinski.blockchain.dictionaries.BlockOperation;
import org.ttrzcinski.blockchain.dictionaries.StateOfLife;
import org.ttrzcinski.blockchain.exceptions.InvalidKeyException;
import org.ttrzcinski.blockchain.exceptions.UnknownOperationException;

@Getter
public final class Block {

    /**
     * Additional zeros in the final hash in order to make it harder to break.
     */
    private static final String nonce = "000";

    /**
     * Date of Dig-Up - when it was created since the beginning of time 01-01-1970.
     */
    private long dodu;

    /**
     * Data of Last Modification - when it was modified from point of previous operation.
     */
    private long dolm;

    /**
     * Kept hash of the predecessor in order to make "the-block-chain"
     */
    // TODO Block generation of getter in lombok
    private String predecessorsHash;

    /**
     * Kept private key of the owner for further security comparisons.
     */
    private String ownersKey;

    /**
     * Current state of lifecycle of the block.
     */
    private StateOfLife currentState;

    /**
     * Counted own hash in order not to repeat counting - whole value comes from counting power used.
     */
    @Getter
    private String ownHash;

    /**
     * Main value wrapped around by the block.
     */
    private Object payload;
    /**
     * Kept type of payload.
     */
    private String payloadsType;

    /**
     * Creates new block based of given mandatory details.
     *
     * @param ownersKey       owner's key for security comparisons
     * @param predecessorHash has of previous block
     */
    public Block(String ownersKey, String predecessorHash, Object payload, String payloadsType) {
        // Pass the payload
        this.payload = payload;
        this.payloadsType = payloadsType;

        if (this.currentState == null) {
            this.currentState = new StateOfLife();
            this.currentState.modifyState(StateOfLife.DIGGED_UP);
        }

        // Defaults known and set on initialization
        this.dodu = new java.util.Date().getTime();
        this.currentState.modifyState(StateOfLife.DIGGED_UP);
        this.dolm = new java.util.Date().getTime();
    }

    // TODO NEEDS TO BE VALIDATED (HASH-CHECK) WITH PUBLIC KEY

    /**
     * Hidden constructor - initialization of an instance suppose to happen outside in miner.
     */
    private Block() {

    }

    /**
     * Modifies inners of the block striclty following business logic and lifecycle.
     */
    public void modify(String guessedHash, long operation, Object[] metaData) {
        // Check, if hash is OK
        if (!this.ownHash.equals(guessedHash)) {
            throw new InvalidKeyException("You are not the owner of the block.");
        }

        // Process operation
        if (operation == BlockOperation.DIG) {// TODO WHAT HAPPENS, WHEN DIGGED UP
            return;
        } else if (operation == BlockOperation.CLAIM) {// TODO WHAT HAPPENS, WHEN CLAIMED
            return;
            // ...
        }
        throw new UnknownOperationException("Unknown operation code passed.");
    }

    /**
     * Counts has of current Block, but keeps it inside. To read it method getHash must be used.
     */
    public void countHash() throws Exception {
        // Works only, when calling it first time
        if (this.ownHash != null) {
            throw new Exception("It was counted once. If You don't know it, than it is Your problem.");
        }
        // Add any random factors to make it harder to decipher
        int randFactor = new java.util.Random().nextInt();
        // Count new Hash starting with predecessor's hash
        String newHash = this.predecessorsHash
                // adding some random noise
                + Block.nonce + Integer.valueOf(randFactor).toString()
                // and finally counting hash from payload
                + this.payload.toString().hashCode();
        // Rewrite hash
    }

    /**
     * Checks, if pointed instance it the same as this.
     *
     * @param o instance to compare
     * @return true, if the same, false otherwise
     */
    //@Override
    public boolean equals(Object o) {
        if (o == null) return false;
        if (!(o instanceof Block oConverted)) return false;
        return (this.getOwnHash().equals(oConverted.getOwnHash()));
        // TODO MAKE THE LOGIC TO BEHAVE AS SINGLETON FROM POINT OF WHOLE BLOCKCHAIN SPACE AND WALLET
    }
}
