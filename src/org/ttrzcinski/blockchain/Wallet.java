package org.ttrzcinski.blockchain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.ttrzcinski.blockchain.exceptions.InvalidKeyException;
import org.ttrzcinski.blockchain.services.NodeService;

import java.util.HashSet;
import java.util.Set;

/**
 * Represents a privately-owned set of bought or obtained Blocks.
 */
@AllArgsConstructor
@Getter
public class Wallet {
    /**
     * Date of Creation Timestamp - when the wallet was persisted.
     */
    private long doc;

    /**
     * Kept copy of owner's key to compare access rights.
     */
    private String ownersKey;

    /**
     * Kept blocks in the wallet.
     */
    private Set<Block> blocks;

    /**
     * Adds new block to the wallet after valdiating all business conditions.
     *
     * @param key   callers key to compare
     * @param block given block to add
     * @throws InvalidKeyException in case of problems with key
     */
    public void addBlock(String key, Block block) {
        // Check, if key is valid.
        if (key == null) {
            System.err.print("Given key is NULL.");
            throw new InvalidKeyException("Given key to wallet is NULL.");
        } else if (!this.ownersKey.equals(key)) {
            System.err.print("Given key is INVALID.");
            throw new InvalidKeyException("Given key to wallet is INVALID.");
        }

        // Check, if block is not null - can be annotation
        if (block == null) {
            System.err.print("Given block entry is NULL.");
            throw new NullPointerException("Given block to wallet is NULL.");
        }

        // Check initialization of wallet
        if (this.blocks == null) {
            this.blocks = new HashSet<Block>();
        } else {
            // Check, if block is not already there
            if (this.blocks.contains(block)) {

            }
        }

        // TODO CHECK HERE ALL POSSIBLE EXCEPTIONS AND BUSINESS LOGIC INPUTS
        // Finally add block
        this.blocks.add(block);

        // Persist changes and sync to nearest NodeServer
        // Obtain an instance of node service
        NodeService node = NodeService.getInstance();
        // TODO CHECK HERE OF ANY OCCURRING EXCEPTIONS AND AWAIT ACK OR SOME ASYNC HTTP MODIFIED
        node.sync(this.ownersKey, this.blocks);
    }
}
