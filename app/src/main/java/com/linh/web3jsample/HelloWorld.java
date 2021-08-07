package com.linh.web3jsample;

import io.reactivex.Flowable;
import io.reactivex.functions.Function;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import org.web3j.abi.EventEncoder;
import org.web3j.abi.FunctionEncoder;
import org.web3j.abi.TypeReference;
import org.web3j.abi.datatypes.Event;
import org.web3j.abi.datatypes.Type;
import org.web3j.abi.datatypes.Utf8String;
import org.web3j.crypto.Credentials;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.DefaultBlockParameter;
import org.web3j.protocol.core.RemoteCall;
import org.web3j.protocol.core.RemoteFunctionCall;
import org.web3j.protocol.core.methods.request.EthFilter;
import org.web3j.protocol.core.methods.response.BaseEventResponse;
import org.web3j.protocol.core.methods.response.Log;
import org.web3j.protocol.core.methods.response.TransactionReceipt;
import org.web3j.tx.Contract;
import org.web3j.tx.TransactionManager;
import org.web3j.tx.gas.ContractGasProvider;

/**
 * <p>Auto generated code.
 * <p><strong>Do not modify!</strong>
 * <p>Please use the <a href="https://docs.web3j.io/command_line.html">web3j command line tools</a>,
 * or the org.web3j.codegen.SolidityFunctionWrapperGenerator in the 
 * <a href="https://github.com/web3j/web3j/tree/master/codegen">codegen module</a> to update.
 *
 * <p>Generated with web3j version 1.4.1.
 */
@SuppressWarnings("rawtypes")
public class HelloWorld extends Contract {
    public static final String BINARY = "608060405234801561001057600080fd5b506040516107c43803806107c483398101604081905261002f916100f4565b600080546001600160a01b03191633179055805161005490600190602084019061005b565b5050610214565b828054610067906101c3565b90600052602060002090601f01602090048101928261008957600085556100cf565b82601f106100a257805160ff19168380011785556100cf565b828001600101855582156100cf579182015b828111156100cf5782518255916020019190600101906100b4565b506100db9291506100df565b5090565b5b808211156100db57600081556001016100e0565b6000602080838503121561010757600080fd5b82516001600160401b038082111561011e57600080fd5b818501915085601f83011261013257600080fd5b815181811115610144576101446101fe565b604051601f8201601f19908116603f0116810190838211818310171561016c5761016c6101fe565b81604052828152888684870101111561018457600080fd5b600093505b828410156101a65784840186015181850187015292850192610189565b828411156101b75760008684830101525b98975050505050505050565b600181811c908216806101d757607f821691505b602082108114156101f857634e487b7160e01b600052602260045260246000fd5b50919050565b634e487b7160e01b600052604160045260246000fd5b6105a1806102236000396000f3fe608060405234801561001057600080fd5b50600436106100415760003560e01c806341c0e1b5146100465780634ac0d66e14610050578063ef690cc014610063575b600080fd5b61004e610081565b005b61004e61005e36600461028a565b6100b7565b61006b61015f565b60405161007891906103f5565b60405180910390f35b6000546001600160a01b031633146100b45760405162461bcd60e51b81526004016100ab906104a8565b60405180910390fd5b33ff5b6000546001600160a01b031633146100e15760405162461bcd60e51b81526004016100ab906104a8565b806040516100ef9190610367565b604051809103902060016040516101069190610383565b60405180910390207f047dcd1aa8b77b0b943642129c767533eeacd700c7c1eab092b8ce05d2b2faf560018460405161014092919061040f565b60405180910390a3805161015b9060019060208401906101f1565b5050565b60606001805461016e9061051a565b80601f016020809104026020016040519081016040528092919081815260200182805461019a9061051a565b80156101e75780601f106101bc576101008083540402835291602001916101e7565b820191906000526020600020905b8154815290600101906020018083116101ca57829003601f168201915b5050505050905090565b8280546101fd9061051a565b90600052602060002090601f01602090048101928261021f5760008555610265565b82601f1061023857805160ff1916838001178555610265565b82800160010185558215610265579182015b8281111561026557825182559160200191906001019061024a565b50610271929150610275565b5090565b5b808211156102715760008155600101610276565b60006020828403121561029c57600080fd5b813567ffffffffffffffff808211156102b457600080fd5b818401915084601f8301126102c857600080fd5b8135818111156102da576102da610555565b604051601f8201601f19908116603f0116810190838211818310171561030257610302610555565b8160405282815287602084870101111561031b57600080fd5b826020860160208301376000928101602001929092525095945050505050565b600081518084526103538160208601602086016104ea565b601f01601f19169290920160200192915050565b600082516103798184602087016104ea565b9190910192915050565b60008083546103918161051a565b600182811680156103a957600181146103ba576103e9565b60ff198416875282870194506103e9565b8760005260208060002060005b858110156103e05781548a8201529084019082016103c7565b50505082870194505b50929695505050505050565b602081526000610408602083018461033b565b9392505050565b6040815260008084546104218161051a565b8060408601526060600180841660008114610443576001811461045757610488565b60ff19851688840152608088019550610488565b8960005260208060002060005b8681101561047f5781548b8201870152908401908201610464565b8a018501975050505b5050505050828103602084015261049f818561033b565b95945050505050565b60208082526022908201527f4f6e6c79206f776e65722063616e2063616c6c20746869732066756e6374696f604082015261371760f11b606082015260800190565b60005b838110156105055781810151838201526020016104ed565b83811115610514576000848401525b50505050565b600181811c9082168061052e57607f821691505b6020821081141561054f57634e487b7160e01b600052602260045260246000fd5b50919050565b634e487b7160e01b600052604160045260246000fdfea26469706673582212202c38f7c984a85641bc1f44195c7eb98d4b84309ec137fdac44461ba53490522364736f6c63430008060033";

    public static final String FUNC_GREETING = "greeting";

    public static final String FUNC_KILL = "kill";

    public static final String FUNC_NEWGREETING = "newGreeting";

    public static final Event MODIFIED_EVENT = new Event("Modified", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Utf8String>(true) {}, new TypeReference<Utf8String>(true) {}, new TypeReference<Utf8String>() {}, new TypeReference<Utf8String>() {}));
    ;

    @Deprecated
    protected HelloWorld(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    protected HelloWorld(String contractAddress, Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        super(BINARY, contractAddress, web3j, credentials, contractGasProvider);
    }

    @Deprecated
    protected HelloWorld(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    protected HelloWorld(String contractAddress, Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        super(BINARY, contractAddress, web3j, transactionManager, contractGasProvider);
    }

    public List<ModifiedEventResponse> getModifiedEvents(TransactionReceipt transactionReceipt) {
        List<Contract.EventValuesWithLog> valueList = extractEventParametersWithLog(MODIFIED_EVENT, transactionReceipt);
        ArrayList<ModifiedEventResponse> responses = new ArrayList<ModifiedEventResponse>(valueList.size());
        for (Contract.EventValuesWithLog eventValues : valueList) {
            ModifiedEventResponse typedResponse = new ModifiedEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.oldGreetingIdx = (byte[]) eventValues.getIndexedValues().get(0).getValue();
            typedResponse.newGreetingIdx = (byte[]) eventValues.getIndexedValues().get(1).getValue();
            typedResponse.oldGreeting = (String) eventValues.getNonIndexedValues().get(0).getValue();
            typedResponse.newGreeting = (String) eventValues.getNonIndexedValues().get(1).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public Flowable<ModifiedEventResponse> modifiedEventFlowable(EthFilter filter) {
        return web3j.ethLogFlowable(filter).map(new Function<Log, ModifiedEventResponse>() {
            @Override
            public ModifiedEventResponse apply(Log log) {
                Contract.EventValuesWithLog eventValues = extractEventParametersWithLog(MODIFIED_EVENT, log);
                ModifiedEventResponse typedResponse = new ModifiedEventResponse();
                typedResponse.log = log;
                typedResponse.oldGreetingIdx = (byte[]) eventValues.getIndexedValues().get(0).getValue();
                typedResponse.newGreetingIdx = (byte[]) eventValues.getIndexedValues().get(1).getValue();
                typedResponse.oldGreeting = (String) eventValues.getNonIndexedValues().get(0).getValue();
                typedResponse.newGreeting = (String) eventValues.getNonIndexedValues().get(1).getValue();
                return typedResponse;
            }
        });
    }

    public Flowable<ModifiedEventResponse> modifiedEventFlowable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(MODIFIED_EVENT));
        return modifiedEventFlowable(filter);
    }

    public RemoteFunctionCall<String> greeting() {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_GREETING, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Utf8String>() {}));
        return executeRemoteCallSingleValueReturn(function, String.class);
    }

    public RemoteFunctionCall<TransactionReceipt> kill() {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(
                FUNC_KILL, 
                Arrays.<Type>asList(), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<TransactionReceipt> newGreeting(String _greet) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(
                FUNC_NEWGREETING, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Utf8String(_greet)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    @Deprecated
    public static HelloWorld load(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        return new HelloWorld(contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    @Deprecated
    public static HelloWorld load(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return new HelloWorld(contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    public static HelloWorld load(String contractAddress, Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        return new HelloWorld(contractAddress, web3j, credentials, contractGasProvider);
    }

    public static HelloWorld load(String contractAddress, Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        return new HelloWorld(contractAddress, web3j, transactionManager, contractGasProvider);
    }

    public static RemoteCall<HelloWorld> deploy(Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider, String _greet) {
        String encodedConstructor = FunctionEncoder.encodeConstructor(Arrays.<Type>asList(new org.web3j.abi.datatypes.Utf8String(_greet)));
        return deployRemoteCall(HelloWorld.class, web3j, credentials, contractGasProvider, BINARY, encodedConstructor);
    }

    public static RemoteCall<HelloWorld> deploy(Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider, String _greet) {
        String encodedConstructor = FunctionEncoder.encodeConstructor(Arrays.<Type>asList(new org.web3j.abi.datatypes.Utf8String(_greet)));
        return deployRemoteCall(HelloWorld.class, web3j, transactionManager, contractGasProvider, BINARY, encodedConstructor);
    }

    @Deprecated
    public static RemoteCall<HelloWorld> deploy(Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit, String _greet) {
        String encodedConstructor = FunctionEncoder.encodeConstructor(Arrays.<Type>asList(new org.web3j.abi.datatypes.Utf8String(_greet)));
        return deployRemoteCall(HelloWorld.class, web3j, credentials, gasPrice, gasLimit, BINARY, encodedConstructor);
    }

    @Deprecated
    public static RemoteCall<HelloWorld> deploy(Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit, String _greet) {
        String encodedConstructor = FunctionEncoder.encodeConstructor(Arrays.<Type>asList(new org.web3j.abi.datatypes.Utf8String(_greet)));
        return deployRemoteCall(HelloWorld.class, web3j, transactionManager, gasPrice, gasLimit, BINARY, encodedConstructor);
    }

    public static class ModifiedEventResponse extends BaseEventResponse {
        public byte[] oldGreetingIdx;

        public byte[] newGreetingIdx;

        public String oldGreeting;

        public String newGreeting;
    }
}
