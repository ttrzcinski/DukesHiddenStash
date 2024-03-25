package org.ttrzcinski.blockchain.services;

import org.ttrzcinski.blockchain.Block;
import org.ttrzcinski.blockchain.exceptions.InvalidKeyException;
import org.ttrzcinski.utils.ParamCheck;

import java.util.Set;


public class NodeService {

    /**
     * The only kept instance.
     */
    private static NodeService _instance;// = new NodeService();

    // Singleton
    /**
     * Hidden Constructor - it is a singleton after all.
     */
    private NodeService() {
    }

    /**
     * Passed the instance to the node.
     *
     * @return NodeService the only instance
     */
    public static NodeService getInstance() {
        // Check, if instance is initialized
        if (NodeService._instance == null) {
            NodeService._instance = new NodeService();
        }
        // ..and pass it
        return NodeService._instance;
    }

    /**
     * Synchronizes with the nearest Nodes changes in given object.
     */
    public void sync(String ownerKey, Set<Block> blocks) {
        // Check given key
        if (ParamCheck.isSet(ownerKey)) {
            System.err.println("Cannot sync with no key given.");
            throw new InvalidKeyException("Cannot sync with no key given.");
        }

        // Check, if any data was passed
        if (ParamCheck.isSet(blocks)) {
            System.err.println("Cannot sync with empty block.");
            throw new InvalidKeyException("Cannot sync with empty block.");
        }

        // TODO MAYBE TRY ASYNC
        // Check, if server is responding
        boolean worldResponds = true;
        if (!worldResponds) {
            System.err.println("There is no server responding.");
            throw new InvalidKeyException("There is no server responding.");
        }

        // Pass for verification
        // TODO ADD SOME CURL-WRAPPER HERE
    }

}
