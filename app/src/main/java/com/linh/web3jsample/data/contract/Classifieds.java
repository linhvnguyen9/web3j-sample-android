package com.linh.web3jsample.data.contract;

import io.reactivex.Flowable;
import io.reactivex.functions.Function;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Callable;
import org.web3j.abi.EventEncoder;
import org.web3j.abi.FunctionEncoder;
import org.web3j.abi.TypeReference;
import org.web3j.abi.datatypes.Address;
import org.web3j.abi.datatypes.Event;
import org.web3j.abi.datatypes.StaticStruct;
import org.web3j.abi.datatypes.Type;
import org.web3j.abi.datatypes.generated.Bytes32;
import org.web3j.abi.datatypes.generated.Uint256;
import org.web3j.crypto.Credentials;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.DefaultBlockParameter;
import org.web3j.protocol.core.RemoteCall;
import org.web3j.protocol.core.RemoteFunctionCall;
import org.web3j.protocol.core.methods.request.EthFilter;
import org.web3j.protocol.core.methods.response.BaseEventResponse;
import org.web3j.protocol.core.methods.response.Log;
import org.web3j.protocol.core.methods.response.TransactionReceipt;
import org.web3j.tuples.generated.Tuple4;
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
public class Classifieds extends Contract {
    public static final String BINARY = "608060405234801561001057600080fd5b5060405161092a38038061092a83398101604081905261002f91610057565b600080546001600160a01b0319166001600160a01b0392909216919091178155600255610087565b60006020828403121561006957600080fd5b81516001600160a01b038116811461008057600080fd5b9392505050565b610894806100966000396000f3fe6080604052600436106100555760003560e01c806302d2838b1461005a57806309ec6cc71461006f5780630c493fba1461008f5780631900f44b146100af5780631e6c598e146101145780632db25e051461018d575b600080fd5b61006d6100683660046107c3565b6101fb565b005b34801561007b57600080fd5b5061006d61008a3660046107c3565b6103e9565b34801561009b57600080fd5b5061006d6100aa3660046107dc565b6105b7565b3480156100bb57600080fd5b506100cf6100ca3660046107c3565b6106f0565b60405161010b919081516001600160a01b0316815260208083015190820152604080830151908201526060918201519181019190915260800190565b60405180910390f35b34801561012057600080fd5b5061016361012f3660046107c3565b600160208190526000918252604090912080549181015460028201546003909201546001600160a01b039093169290919084565b604080516001600160a01b039095168552602085019390935291830152606082015260800161010b565b34801561019957600080fd5b506101636101a83660046107c3565b600090815260016020818152604092839020835160808101855281546001600160a01b03168082529382015492810183905260028201549481018590526003909101546060909101819052919390929190565b600081815260016020818152604092839020835160808101855281546001600160a01b031681529281015491830191909152600281015492820192909252600390910154606082018190526327b832b760e11b146102955760405162461bcd60e51b81526020600482015260126024820152712a3930b2329034b9903737ba1027b832b71760711b60448201526064015b60405180910390fd5b806040015134146102d95760405162461bcd60e51b815260206004820152600e60248201526d09cdee840cadcdeeaced0408aa8960931b604482015260640161028c565b6040808201519051339180156108fc02916000818181858888f19350505050158015610309573d6000803e3d6000fd5b5060005460208201516040516323b872dd60e01b815230600482015233602482015260448101919091526001600160a01b03909116906323b872dd90606401600060405180830381600087803b15801561036257600080fd5b505af1158015610376573d6000803e3d6000fd5b5050506000838152600160205260409081902067115e1958dd5d195960c21b600390910155517f4d38ed2c5ea671a5ccddc331f0ebebd94a60aaf6e572543497485d4d148f9edf91506103dd9084815267115e1958dd5d195960c21b602082015260400190565b60405180910390a15050565b600081815260016020818152604092839020835160808101855281546001600160a01b03168082529382015492810192909252600281015493820193909352600390920154606083015233146104905760405162461bcd60e51b815260206004820152602660248201527f54726164652063616e2062652063616e63656c6c6564206f6e6c79206279207060448201526537b9ba32b91760d11b606482015260840161028c565b80606001516327b832b760e11b146104df5760405162461bcd60e51b81526020600482015260126024820152712a3930b2329034b9903737ba1027b832b71760711b604482015260640161028c565b600054815160208301516040516323b872dd60e01b81523060048201526001600160a01b03928316602482015260448101919091529116906323b872dd90606401600060405180830381600087803b15801561053a57600080fd5b505af115801561054e573d6000803e3d6000fd5b505050600083815260016020526040908190206810d85b98d95b1b195960ba1b600390910155517f4d38ed2c5ea671a5ccddc331f0ebebd94a60aaf6e572543497485d4d148f9edf91506103dd908481526810d85b98d95b1b195960ba1b602082015260400190565b6000546040516323b872dd60e01b8152336004820152306024820152604481018490526001600160a01b03909116906323b872dd90606401600060405180830381600087803b15801561060957600080fd5b505af115801561061d573d6000803e3d6000fd5b50506040805160808101825233815260208082018781528284018781526327b832b760e11b60608501908152600280546000908152600195869052968720955186546001600160a01b0319166001600160a01b039091161786559251858501559051848301555160039093019290925581549094509092506106a09084906107fe565b90915550506002547f4d38ed2c5ea671a5ccddc331f0ebebd94a60aaf6e572543497485d4d148f9edf906106d690600190610816565b604080519182526327b832b760e11b6020830152016103dd565b6040805160808101825260008082526020820181905291810182905260608101829052905b60025481101561078957600081815260016020818152604092839020835160808101855281546001600160a01b0316815292810154918301829052600281015493830193909352600390920154606082015290841415610776579392505050565b50806107818161082d565b915050610715565b60405162461bcd60e51b815260206004820152600f60248201526e125d195b481b9bdd081b1a5cdd1959608a1b604482015260640161028c565b6000602082840312156107d557600080fd5b5035919050565b600080604083850312156107ef57600080fd5b50508035926020909101359150565b6000821982111561081157610811610848565b500190565b60008282101561082857610828610848565b500390565b600060001982141561084157610841610848565b5060010190565b634e487b7160e01b600052601160045260246000fdfea2646970667358221220207675a6a7c633ad359210f1110b1bf078b6f0c4769dfd027cfaae67751a701864736f6c63430008070033";

    public static final String FUNC_CANCELTRADE = "cancelTrade";

    public static final String FUNC_EXECUTETRADE = "executeTrade";

    public static final String FUNC_GETTRADE = "getTrade";

    public static final String FUNC_GETTRADEBYITEM = "getTradeByItem";

    public static final String FUNC_OPENTRADE = "openTrade";

    public static final String FUNC_TRADES = "trades";

    public static final Event TRADESTATUSCHANGE_EVENT = new Event("TradeStatusChange", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}, new TypeReference<Bytes32>() {}));
    ;

    @Deprecated
    protected Classifieds(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    protected Classifieds(String contractAddress, Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        super(BINARY, contractAddress, web3j, credentials, contractGasProvider);
    }

    @Deprecated
    protected Classifieds(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    protected Classifieds(String contractAddress, Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        super(BINARY, contractAddress, web3j, transactionManager, contractGasProvider);
    }

    public List<TradeStatusChangeEventResponse> getTradeStatusChangeEvents(TransactionReceipt transactionReceipt) {
        List<Contract.EventValuesWithLog> valueList = extractEventParametersWithLog(TRADESTATUSCHANGE_EVENT, transactionReceipt);
        ArrayList<TradeStatusChangeEventResponse> responses = new ArrayList<TradeStatusChangeEventResponse>(valueList.size());
        for (Contract.EventValuesWithLog eventValues : valueList) {
            TradeStatusChangeEventResponse typedResponse = new TradeStatusChangeEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.ad = (BigInteger) eventValues.getNonIndexedValues().get(0).getValue();
            typedResponse.status = (byte[]) eventValues.getNonIndexedValues().get(1).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public Flowable<TradeStatusChangeEventResponse> tradeStatusChangeEventFlowable(EthFilter filter) {
        return web3j.ethLogFlowable(filter).map(new Function<Log, TradeStatusChangeEventResponse>() {
            @Override
            public TradeStatusChangeEventResponse apply(Log log) {
                Contract.EventValuesWithLog eventValues = extractEventParametersWithLog(TRADESTATUSCHANGE_EVENT, log);
                TradeStatusChangeEventResponse typedResponse = new TradeStatusChangeEventResponse();
                typedResponse.log = log;
                typedResponse.ad = (BigInteger) eventValues.getNonIndexedValues().get(0).getValue();
                typedResponse.status = (byte[]) eventValues.getNonIndexedValues().get(1).getValue();
                return typedResponse;
            }
        });
    }

    public Flowable<TradeStatusChangeEventResponse> tradeStatusChangeEventFlowable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(TRADESTATUSCHANGE_EVENT));
        return tradeStatusChangeEventFlowable(filter);
    }

    public RemoteFunctionCall<TransactionReceipt> cancelTrade(BigInteger _trade) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(
                FUNC_CANCELTRADE, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(_trade)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<TransactionReceipt> executeTrade(BigInteger _trade) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(
                FUNC_EXECUTETRADE, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(_trade)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<Tuple4<String, BigInteger, BigInteger, byte[]>> getTrade(BigInteger _trade) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_GETTRADE, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(_trade)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}, new TypeReference<Uint256>() {}, new TypeReference<Uint256>() {}, new TypeReference<Bytes32>() {}));
        return new RemoteFunctionCall<Tuple4<String, BigInteger, BigInteger, byte[]>>(function,
                new Callable<Tuple4<String, BigInteger, BigInteger, byte[]>>() {
                    @Override
                    public Tuple4<String, BigInteger, BigInteger, byte[]> call() throws Exception {
                        List<Type> results = executeCallMultipleValueReturn(function);
                        return new Tuple4<String, BigInteger, BigInteger, byte[]>(
                                (String) results.get(0).getValue(), 
                                (BigInteger) results.get(1).getValue(), 
                                (BigInteger) results.get(2).getValue(), 
                                (byte[]) results.get(3).getValue());
                    }
                });
    }

    public RemoteFunctionCall<Trade> getTradeByItem(BigInteger _item) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_GETTRADEBYITEM, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(_item)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Trade>() {}));
        return executeRemoteCallSingleValueReturn(function, Trade.class);
    }

    public RemoteFunctionCall<TransactionReceipt> openTrade(BigInteger _item, BigInteger _price) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(
                FUNC_OPENTRADE, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(_item), 
                new org.web3j.abi.datatypes.generated.Uint256(_price)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<Tuple4<String, BigInteger, BigInteger, byte[]>> trades(BigInteger param0) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_TRADES, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(param0)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}, new TypeReference<Uint256>() {}, new TypeReference<Uint256>() {}, new TypeReference<Bytes32>() {}));
        return new RemoteFunctionCall<Tuple4<String, BigInteger, BigInteger, byte[]>>(function,
                new Callable<Tuple4<String, BigInteger, BigInteger, byte[]>>() {
                    @Override
                    public Tuple4<String, BigInteger, BigInteger, byte[]> call() throws Exception {
                        List<Type> results = executeCallMultipleValueReturn(function);
                        return new Tuple4<String, BigInteger, BigInteger, byte[]>(
                                (String) results.get(0).getValue(), 
                                (BigInteger) results.get(1).getValue(), 
                                (BigInteger) results.get(2).getValue(), 
                                (byte[]) results.get(3).getValue());
                    }
                });
    }

    @Deprecated
    public static Classifieds load(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        return new Classifieds(contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    @Deprecated
    public static Classifieds load(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return new Classifieds(contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    public static Classifieds load(String contractAddress, Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        return new Classifieds(contractAddress, web3j, credentials, contractGasProvider);
    }

    public static Classifieds load(String contractAddress, Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        return new Classifieds(contractAddress, web3j, transactionManager, contractGasProvider);
    }

    public static RemoteCall<Classifieds> deploy(Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider, String _itemTokenAddress) {
        String encodedConstructor = FunctionEncoder.encodeConstructor(Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(160, _itemTokenAddress)));
        return deployRemoteCall(Classifieds.class, web3j, credentials, contractGasProvider, BINARY, encodedConstructor);
    }

    public static RemoteCall<Classifieds> deploy(Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider, String _itemTokenAddress) {
        String encodedConstructor = FunctionEncoder.encodeConstructor(Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(160, _itemTokenAddress)));
        return deployRemoteCall(Classifieds.class, web3j, transactionManager, contractGasProvider, BINARY, encodedConstructor);
    }

    @Deprecated
    public static RemoteCall<Classifieds> deploy(Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit, String _itemTokenAddress) {
        String encodedConstructor = FunctionEncoder.encodeConstructor(Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(160, _itemTokenAddress)));
        return deployRemoteCall(Classifieds.class, web3j, credentials, gasPrice, gasLimit, BINARY, encodedConstructor);
    }

    @Deprecated
    public static RemoteCall<Classifieds> deploy(Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit, String _itemTokenAddress) {
        String encodedConstructor = FunctionEncoder.encodeConstructor(Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(160, _itemTokenAddress)));
        return deployRemoteCall(Classifieds.class, web3j, transactionManager, gasPrice, gasLimit, BINARY, encodedConstructor);
    }

    public static class Trade extends StaticStruct {
        public String poster;

        public BigInteger item;

        public BigInteger price;

        public byte[] status;

        public Trade(String poster, BigInteger item, BigInteger price, byte[] status) {
            super(new org.web3j.abi.datatypes.Address(poster),new org.web3j.abi.datatypes.generated.Uint256(item),new org.web3j.abi.datatypes.generated.Uint256(price),new org.web3j.abi.datatypes.generated.Bytes32(status));
            this.poster = poster;
            this.item = item;
            this.price = price;
            this.status = status;
        }

        public Trade(Address poster, Uint256 item, Uint256 price, Bytes32 status) {
            super(poster,item,price,status);
            this.poster = poster.getValue();
            this.item = item.getValue();
            this.price = price.getValue();
            this.status = status.getValue();
        }
    }

    public static class TradeStatusChangeEventResponse extends BaseEventResponse {
        public BigInteger ad;

        public byte[] status;
    }
}
