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
import org.web3j.tuples.generated.Tuple2;
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
 * <p>Generated with web3j version 4.8.4.
 */
@SuppressWarnings("rawtypes")
public class Classifieds extends Contract {
    public static final String BINARY = "608060405234801561001057600080fd5b50604051610a6f380380610a6f83398101604081905261002f91610057565b60008054600160a060020a031916600160a060020a0392909216919091178155600255610087565b60006020828403121561006957600080fd5b8151600160a060020a038116811461008057600080fd5b9392505050565b6109d9806100966000396000f3fe608060405260043610610071577c0100000000000000000000000000000000000000000000000000000000600035046302d2838b811461007657806309ec6cc71461008b5780630c493fba146100ab5780631900f44b146100cb5780631e6c598e1461012d5780632db25e05146101a6575b600080fd5b6100896100843660046108ef565b610214565b005b34801561009757600080fd5b506100896100a63660046108ef565b610468565b3480156100b757600080fd5b506100896100c6366004610908565b6106a2565b3480156100d757600080fd5b506100eb6100e63660046108ef565b610807565b604080519283528151600160a060020a0316602080850191909152820151838201528101516060808401919091520151608082015260a0015b60405180910390f35b34801561013957600080fd5b5061017c6101483660046108ef565b60016020819052600091825260409091208054918101546002820154600390920154600160a060020a039093169290919084565b60408051600160a060020a0390951685526020850193909352918301526060820152608001610124565b3480156101b257600080fd5b5061017c6101c13660046108ef565b60009081526001602081815260409283902083516080810185528154600160a060020a03168082529382015492810183905260028201549481018590526003909101546060909101819052919390929190565b60008181526001602081815260409283902083516080810185528154600160a060020a0316815292810154918301919091526002810154928201929092526003909101546060820181905260e160020a6327b832b702146102bf5760405160e560020a62461bcd02815260206004820152601260248201527f5472616465206973206e6f74204f70656e2e000000000000000000000000000060448201526064015b60405180910390fd5b806040015134146103155760405160e560020a62461bcd02815260206004820152600e60248201527f4e6f7420656e6f7567682045544800000000000000000000000000000000000060448201526064016102b6565b6040808201519051339180156108fc02916000818181858888f19350505050158015610345573d6000803e3d6000fd5b5060005460208201516040517f23b872dd0000000000000000000000000000000000000000000000000000000081523060048201523360248201526044810191909152600160a060020a03909116906323b872dd90606401600060405180830381600087803b1580156103b757600080fd5b505af11580156103cb573d6000803e3d6000fd5b505050600083815260016020526040908190207f4578656375746564000000000000000000000000000000000000000000000000600390910155517f4d38ed2c5ea671a5ccddc331f0ebebd94a60aaf6e572543497485d4d148f9edf915061045c908481527f4578656375746564000000000000000000000000000000000000000000000000602082015260400190565b60405180910390a15050565b60008181526001602081815260409283902083516080810185528154600160a060020a03168082529382015492810192909252600281015493820193909352600390920154606083015233146105295760405160e560020a62461bcd02815260206004820152602660248201527f54726164652063616e2062652063616e63656c6c6564206f6e6c79206279207060448201527f6f737465722e000000000000000000000000000000000000000000000000000060648201526084016102b6565b806060015160e160020a6327b832b702146105895760405160e560020a62461bcd02815260206004820152601260248201527f5472616465206973206e6f74204f70656e2e000000000000000000000000000060448201526064016102b6565b600054815160208301516040517f23b872dd000000000000000000000000000000000000000000000000000000008152306004820152600160a060020a03928316602482015260448101919091529116906323b872dd90606401600060405180830381600087803b1580156105fd57600080fd5b505af1158015610611573d6000803e3d6000fd5b505050600083815260016020526040908190207f43616e63656c6c65640000000000000000000000000000000000000000000000600390910155517f4d38ed2c5ea671a5ccddc331f0ebebd94a60aaf6e572543497485d4d148f9edf915061045c908481527f43616e63656c6c65640000000000000000000000000000000000000000000000602082015260400190565b6000546040517f23b872dd00000000000000000000000000000000000000000000000000000000815233600482015230602482015260448101849052600160a060020a03909116906323b872dd90606401600060405180830381600087803b15801561070d57600080fd5b505af1158015610721573d6000803e3d6000fd5b505060408051608081018252338152602080820187815282840187815260e160020a6327b832b702606085019081526002805460009081526001958690529687209551865473ffffffffffffffffffffffffffffffffffffffff1916600160a060020a039091161786559251858501559051848301555160039093019290925581549094509092506107b490849061092a565b90915550506002547f4d38ed2c5ea671a5ccddc331f0ebebd94a60aaf6e572543497485d4d148f9edf906107ea90600190610942565b6040805191825260e160020a6327b832b70260208301520161045c565b604080516080810182526000808252602082018190529181018290526060810182905260005b6002548110156108a45760008181526001602081815260409283902083516080810185528154600160a060020a0316815292810154918301829052600281015493830193909352600390920154606082015290851415610891579094909350915050565b508061089c81610959565b91505061082d565b60405160e560020a62461bcd02815260206004820152600f60248201527f4974656d206e6f74206c6973746564000000000000000000000000000000000060448201526064016102b6565b60006020828403121561090157600080fd5b5035919050565b6000806040838503121561091b57600080fd5b50508035926020909101359150565b6000821982111561093d5761093d610974565b500190565b60008282101561095457610954610974565b500390565b600060001982141561096d5761096d610974565b5060010190565b7f4e487b7100000000000000000000000000000000000000000000000000000000600052601160045260246000fdfea2646970667358221220ff5843e6ef67bd047c478f9c6aa94e017e70feeee7fbac6bef8ee75d1896432264736f6c63430008070033";

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

    public RemoteFunctionCall<Tuple2<BigInteger, Trade>> getTradeByItem(BigInteger _item) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_GETTRADEBYITEM,
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(_item)),
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}, new TypeReference<Trade>() {}));
        return new RemoteFunctionCall<Tuple2<BigInteger, Trade>>(function,
                new Callable<Tuple2<BigInteger, Trade>>() {
                    @Override
                    public Tuple2<BigInteger, Trade> call() throws Exception {
                        List<Type> results = executeCallMultipleValueReturn(function);
                        return new Tuple2<BigInteger, Trade>(
                                (BigInteger) results.get(0).getValue(),
                                (Trade) results.get(1));
                    }
                });
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
