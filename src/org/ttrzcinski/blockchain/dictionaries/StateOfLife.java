package org.ttrzcinski.blockchain.dictionaries;


// import org.lombok.AllArgConstructor;

// TODO ADD SOME SECURITY CHECK TO THE CONSTRUCTOR
// TODO @AllArgConstructor
public class StateOfLife {
    public static final long DIGGED_UP = 1;

    public static final long ASSIGNED = 100;

    public static final long TO_COMMIT = 200;

    public static final long PERSISTED = 300;

    // TODO UNREM, WHEN LOGIC WILL BE DELIVERED
    //private long keptState = 0;

    public void modifyState(long wantedState) {
        // TODO HERE SOME BUSINESS LOGIC HAPPENS OR EXTERNAL RULES ENGINE WILL BE CALLED FOR THAT
        // TODO THHINK, IF BETTER WILL BE VOID WITH EXCEPTION OR RETURNED 1 FOR SUCCCESS AND -1 FOR FAILURE
        //long keptState = wantedState;
    }
}