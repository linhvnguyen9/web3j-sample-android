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
import org.web3j.abi.TypeReference;
import org.web3j.abi.datatypes.Address;
import org.web3j.abi.datatypes.Bool;
import org.web3j.abi.datatypes.DynamicArray;
import org.web3j.abi.datatypes.Event;
import org.web3j.abi.datatypes.Type;
import org.web3j.abi.datatypes.Utf8String;
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
public class NonFungibleToken4 extends Contract {
    public static final String BINARY = "60806040523480156200001157600080fd5b50604080518082018252601481527f4e6f6e2d66756e6769626c6520546f6b656e203400000000000000000000000060208083019182528351808501909452600484527f4e465434000000000000000000000000000000000000000000000000000000009084015281519192916200008c9160009162000139565b508051620000a290600190602084019062000139565b5050600b805460ff1916905550620000d5620000c6640100000000620000db810204565b640100000000620000df810204565b62000235565b3390565b600b8054600160a060020a0383811661010081810261010060a860020a031985161790945560405193909204169182907f8be0079c531659141344cd1fd0a4f28419497f9722a3daafe3b4186f6b6457e090600090a35050565b8280546200014790620001df565b90600052602060002090601f0160209004810192826200016b5760008555620001b6565b82601f106200018657805160ff1916838001178555620001b6565b82800160010185558215620001b6579182015b82811115620001b657825182559160200191906001019062000199565b50620001c4929150620001c8565b5090565b5b80821115620001c45760008155600101620001c9565b600281046001821680620001f457607f821691505b602082108114156200022f577f4e487b7100000000000000000000000000000000000000000000000000000000600052602260045260246000fd5b50919050565b61244a80620002456000396000f3fe608060405234801561001057600080fd5b5060043610610190576000357c0100000000000000000000000000000000000000000000000000000000900480635c975abb116100fb57806395d89b41116100b4578063b88d4fde1161008e578063b88d4fde14610325578063c87b56dd14610338578063e985e9c51461034b578063f2fde38b1461038757600080fd5b806395d89b41146102f5578063a22cb465146102fd578063b5a993451461031057600080fd5b80635c975abb1461029e5780636352211e146102a957806370a08231146102bc578063715018a6146102cf5780638456cb59146102d75780638da5cb5b146102df57600080fd5b80632f745c591161014d5780632f745c59146102375780633f4ba83a1461024a57806340d097c31461025257806342842e0e1461026557806342966c68146102785780634f6ccce71461028b57600080fd5b806301ffc9a71461019557806306fdde03146101bd578063081812fc146101d2578063095ea7b3146101fd57806318160ddd1461021257806323b872dd14610224575b600080fd5b6101a86101a3366004612048565b61039a565b60405190151581526020015b60405180910390f35b6101c56103ab565b6040516101b4919061218f565b6101e56101e0366004612080565b61043d565b604051600160a060020a0390911681526020016101b4565b61021061020b36600461201f565b6104eb565b005b6008545b6040519081526020016101b4565b610210610232366004611ed5565b610623565b61021661024536600461201f565b610658565b610210610703565b610210610260366004611e89565b610740565b610210610273366004611ed5565b610796565b610210610286366004612080565b6107b1565b610216610299366004612080565b610838565b600b5460ff166101a8565b6101e56102b7366004612080565b6108f0565b6102166102ca366004611e89565b61097e565b610210610a1b565b610210610a58565b600b546101009004600160a060020a03166101e5565b6101c5610a93565b61021061030b366004611fe5565b610aa2565b610318610b6a565b6040516101b4919061212f565b610210610333366004611f10565b610c3d565b6101c5610346366004612080565b610c78565b6101a8610359366004611ea3565b600160a060020a03918216600090815260056020908152604080832093909416825291909152205460ff1690565b610210610395366004611e89565b610c83565b60006103a582610d3e565b92915050565b6060600080546103ba90612300565b80601f01602080910402602001604051908101604052809291908181526020018280546103e690612300565b80156104335780601f1061040857610100808354040283529160200191610433565b820191906000526020600020905b81548152906001019060200180831161041657829003601f168201915b5050505050905090565b600081815260026020526040812054600160a060020a03166104cf5760405160e560020a62461bcd02815260206004820152602c60248201527f4552433732313a20617070726f76656420717565727920666f72206e6f6e657860448201527f697374656e7420746f6b656e000000000000000000000000000000000000000060648201526084015b60405180910390fd5b50600090815260046020526040902054600160a060020a031690565b60006104f6826108f0565b905080600160a060020a031683600160a060020a031614156105835760405160e560020a62461bcd02815260206004820152602160248201527f4552433732313a20617070726f76616c20746f2063757272656e74206f776e6560448201527f720000000000000000000000000000000000000000000000000000000000000060648201526084016104c6565b33600160a060020a038216148061059f575061059f8133610359565b6106145760405160e560020a62461bcd02815260206004820152603860248201527f4552433732313a20617070726f76652063616c6c6572206973206e6f74206f7760448201527f6e6572206e6f7220617070726f76656420666f7220616c6c000000000000000060648201526084016104c6565b61061e8383610d7c565b505050565b61062e335b82610df7565b61064d5760405160e560020a62461bcd0281526004016104c690612234565b61061e838383610f02565b60006106638361097e565b82106106da5760405160e560020a62461bcd02815260206004820152602b60248201527f455243373231456e756d657261626c653a206f776e657220696e646578206f7560448201527f74206f6620626f756e647300000000000000000000000000000000000000000060648201526084016104c6565b50600160a060020a03919091166000908152600660209081526040808320938352929052205490565b600b54600160a060020a036101009091041633146107365760405160e560020a62461bcd0281526004016104c6906121ff565b61073e6110ed565b565b600b54600160a060020a036101009091041633146107735760405160e560020a62461bcd0281526004016104c6906121ff565b61078581610780600c5490565b61118c565b610793600c80546001019055565b50565b61061e83838360405180602001604052806000815250610c3d565b6107ba33610628565b61082f5760405160e560020a62461bcd02815260206004820152603060248201527f4552433732314275726e61626c653a2063616c6c6572206973206e6f74206f7760448201527f6e6572206e6f7220617070726f7665640000000000000000000000000000000060648201526084016104c6565b610793816111aa565b600061084360085490565b82106108ba5760405160e560020a62461bcd02815260206004820152602c60248201527f455243373231456e756d657261626c653a20676c6f62616c20696e646578206f60448201527f7574206f6620626f756e6473000000000000000000000000000000000000000060648201526084016104c6565b600882815481106108de5760e060020a634e487b7102600052603260045260246000fd5b90600052602060002001549050919050565b600081815260026020526040812054600160a060020a0316806103a55760405160e560020a62461bcd02815260206004820152602960248201527f4552433732313a206f776e657220717565727920666f72206e6f6e657869737460448201527f656e7420746f6b656e000000000000000000000000000000000000000000000060648201526084016104c6565b6000600160a060020a0382166109ff5760405160e560020a62461bcd02815260206004820152602a60248201527f4552433732313a2062616c616e636520717565727920666f7220746865207a6560448201527f726f20616464726573730000000000000000000000000000000000000000000060648201526084016104c6565b50600160a060020a031660009081526003602052604090205490565b600b54600160a060020a03610100909104163314610a4e5760405160e560020a62461bcd0281526004016104c6906121ff565b61073e60006111b3565b600b54600160a060020a03610100909104163314610a8b5760405160e560020a62461bcd0281526004016104c6906121ff565b61073e61121a565b6060600180546103ba90612300565b600160a060020a038216331415610afe5760405160e560020a62461bcd02815260206004820152601960248201527f4552433732313a20617070726f766520746f2063616c6c65720000000000000060448201526064016104c6565b336000818152600560209081526040808320600160a060020a03871680855290835292819020805460ff191686151590811790915590519081529192917f17307eab39ab6107e8899845ad3d59bd9653f200f220920489ca2b5937696c31910160405180910390a35050565b60606000610b7760085490565b905060008167ffffffffffffffff811115610ba55760e060020a634e487b7102600052604160045260246000fd5b604051908082528060200260200182016040528015610bd857816020015b6060815260200190600190039081610bc35790505b50905060005b82811015610c36576000610bf182610c78565b905080838381518110610c175760e060020a634e487b7102600052603260045260246000fd5b6020026020010181905250508080610c2e9061233e565b915050610bde565b5092915050565b610c473383610df7565b610c665760405160e560020a62461bcd0281526004016104c690612234565b610c72848484846112a5565b50505050565b60606103a5826112db565b600b54600160a060020a03610100909104163314610cb65760405160e560020a62461bcd0281526004016104c6906121ff565b600160a060020a038116610d355760405160e560020a62461bcd02815260206004820152602660248201527f4f776e61626c653a206e6577206f776e657220697320746865207a65726f206160448201527f646472657373000000000000000000000000000000000000000000000000000060648201526084016104c6565b610793816111b3565b6000600160e060020a031982167f780e9d630000000000000000000000000000000000000000000000000000000014806103a557506103a58261145c565b6000818152600460205260409020805473ffffffffffffffffffffffffffffffffffffffff1916600160a060020a0384169081179091558190610dbe826108f0565b600160a060020a03167f8c5be1e5ebec7d5bd14f71427d1e84f3dd0314c0f7b2291e5b200ac8c7c3b92560405160405180910390a45050565b600081815260026020526040812054600160a060020a0316610e845760405160e560020a62461bcd02815260206004820152602c60248201527f4552433732313a206f70657261746f7220717565727920666f72206e6f6e657860448201527f697374656e7420746f6b656e000000000000000000000000000000000000000060648201526084016104c6565b6000610e8f836108f0565b905080600160a060020a031684600160a060020a03161480610eca575083600160a060020a0316610ebf8461043d565b600160a060020a0316145b80610efa5750600160a060020a0380821660009081526005602090815260408083209388168352929052205460ff165b949350505050565b82600160a060020a0316610f15826108f0565b600160a060020a031614610f945760405160e560020a62461bcd02815260206004820152602960248201527f4552433732313a207472616e73666572206f6620746f6b656e2074686174206960448201527f73206e6f74206f776e000000000000000000000000000000000000000000000060648201526084016104c6565b600160a060020a0382166110125760405160e560020a62461bcd028152602060048201526024808201527f4552433732313a207472616e7366657220746f20746865207a65726f2061646460448201527f726573730000000000000000000000000000000000000000000000000000000060648201526084016104c6565b61101d8383836114f7565b611028600082610d7c565b600160a060020a03831660009081526003602052604081208054600192906110519084906122bd565b9091555050600160a060020a038216600090815260036020526040812080546001929061107f908490612291565b9091555050600081815260026020526040808220805473ffffffffffffffffffffffffffffffffffffffff1916600160a060020a0386811691821790925591518493918716917fddf252ad1be2c89b69c2b068fc378daa952ba7f163c4a11628f55a4df523b3ef91a4505050565b600b5460ff166111425760405160e560020a62461bcd02815260206004820152601460248201527f5061757361626c653a206e6f742070617573656400000000000000000000000060448201526064016104c6565b600b805460ff191690557f5db9ee0a495bf2e6ff9c91a7834c1ba4fdd244a5e8aa4e537bd38aeae4b073aa335b604051600160a060020a03909116815260200160405180910390a1565b6111a6828260405180602001604052806000815250611558565b5050565b6107938161158e565b600b8054600160a060020a0383811661010081810274ffffffffffffffffffffffffffffffffffffffff001985161790945560405193909204169182907f8be0079c531659141344cd1fd0a4f28419497f9722a3daafe3b4186f6b6457e090600090a35050565b600b5460ff16156112705760405160e560020a62461bcd02815260206004820152601060248201527f5061757361626c653a207061757365640000000000000000000000000000000060448201526064016104c6565b600b805460ff191660011790557f62e78cea01bee320cd4e420270b5ea74000d11b0c9f74754ebdbfc544b05a25861116f3390565b6112b0848484610f02565b6112bc848484846115ce565b610c725760405160e560020a62461bcd0281526004016104c6906121a2565b600081815260026020526040902054606090600160a060020a031661136b5760405160e560020a62461bcd02815260206004820152603160248201527f45524337323155524953746f726167653a2055524920717565727920666f722060448201527f6e6f6e6578697374656e7420746f6b656e00000000000000000000000000000060648201526084016104c6565b6000828152600a60205260408120805461138490612300565b80601f01602080910402602001604051908101604052809291908181526020018280546113b090612300565b80156113fd5780601f106113d2576101008083540402835291602001916113fd565b820191906000526020600020905b8154815290600101906020018083116113e057829003601f168201915b50505050509050600061140e611710565b9050805160001415611421575092915050565b81511561145357808260405160200161143b9291906120c4565b60405160208183030381529060405292505050919050565b610efa84611730565b6000600160e060020a031982167f80ac58cd0000000000000000000000000000000000000000000000000000000014806114bf5750600160e060020a031982167f5b5e139f00000000000000000000000000000000000000000000000000000000145b806103a557507f01ffc9a700000000000000000000000000000000000000000000000000000000600160e060020a03198316146103a5565b600b5460ff161561154d5760405160e560020a62461bcd02815260206004820152601060248201527f5061757361626c653a207061757365640000000000000000000000000000000060448201526064016104c6565b61061e83838361181c565b61156283836118d4565b61156f60008484846115ce565b61061e5760405160e560020a62461bcd0281526004016104c6906121a2565b61159781611a35565b6000818152600a6020526040902080546115b090612300565b159050610793576000818152600a6020526040812061079391611e1f565b6000600160a060020a0384163b15611705576040517f150b7a02000000000000000000000000000000000000000000000000000000008152600160a060020a0385169063150b7a029061162b9033908990889088906004016120f3565b602060405180830381600087803b15801561164557600080fd5b505af1925050508015611675575060408051601f3d908101601f1916820190925261167291810190612064565b60015b6116d2573d8080156116a3576040519150601f19603f3d011682016040523d82523d6000602084013e6116a8565b606091505b5080516116ca5760405160e560020a62461bcd0281526004016104c6906121a2565b805181602001fd5b600160e060020a0319167f150b7a0200000000000000000000000000000000000000000000000000000000149050610efa565b506001949350505050565b60606040518060800160405280604681526020016123cf60469139905090565b600081815260026020526040902054606090600160a060020a03166117c05760405160e560020a62461bcd02815260206004820152602f60248201527f4552433732314d657461646174613a2055524920717565727920666f72206e6f60448201527f6e6578697374656e7420746f6b656e000000000000000000000000000000000060648201526084016104c6565b60006117ca611710565b905060008151116117ea5760405180602001604052806000815250611815565b806117f484611ae9565b6040516020016118059291906120c4565b6040516020818303038152906040525b9392505050565b600160a060020a0383166118775761187281600880546000838152600960205260408120829055600182018355919091527ff3f7a9fe364faab93b216da50a3214154f22a0a2b415b23a84c8169e8b636ee30155565b61189a565b81600160a060020a031683600160a060020a03161461189a5761189a8382611c5c565b600160a060020a0382166118b15761061e81611cf9565b82600160a060020a031682600160a060020a03161461061e5761061e8282611ddb565b600160a060020a03821661192d5760405160e560020a62461bcd02815260206004820181905260248201527f4552433732313a206d696e7420746f20746865207a65726f206164647265737360448201526064016104c6565b600081815260026020526040902054600160a060020a0316156119955760405160e560020a62461bcd02815260206004820152601c60248201527f4552433732313a20746f6b656e20616c7265616479206d696e7465640000000060448201526064016104c6565b6119a1600083836114f7565b600160a060020a03821660009081526003602052604081208054600192906119ca908490612291565b9091555050600081815260026020526040808220805473ffffffffffffffffffffffffffffffffffffffff1916600160a060020a03861690811790915590518392907fddf252ad1be2c89b69c2b068fc378daa952ba7f163c4a11628f55a4df523b3ef908290a45050565b6000611a40826108f0565b9050611a4e816000846114f7565b611a59600083610d7c565b600160a060020a0381166000908152600360205260408120805460019290611a829084906122bd565b9091555050600082815260026020526040808220805473ffffffffffffffffffffffffffffffffffffffff1916905551839190600160a060020a038416907fddf252ad1be2c89b69c2b068fc378daa952ba7f163c4a11628f55a4df523b3ef908390a45050565b606081611b2957505060408051808201909152600181527f3000000000000000000000000000000000000000000000000000000000000000602082015290565b8160005b8115611b535780611b3d8161233e565b9150611b4c9050600a836122a9565b9150611b2d565b60008167ffffffffffffffff811115611b7f5760e060020a634e487b7102600052604160045260246000fd5b6040519080825280601f01601f191660200182016040528015611ba9576020820181803683370190505b5090505b8415610efa57611bbe6001836122bd565b9150611bcb600a86612359565b611bd6906030612291565b7f010000000000000000000000000000000000000000000000000000000000000002818381518110611c1b5760e060020a634e487b7102600052603260045260246000fd5b60200101907effffffffffffffffffffffffffffffffffffffffffffffffffffffffffffff1916908160001a905350611c55600a866122a9565b9450611bad565b60006001611c698461097e565b611c7391906122bd565b600083815260076020526040902054909150808214611cc657600160a060020a03841660009081526006602090815260408083208584528252808320548484528184208190558352600790915290208190555b506000918252600760209081526040808420849055600160a060020a039094168352600681528383209183525290812055565b600854600090611d0b906001906122bd565b60008381526009602052604081205460088054939450909284908110611d445760e060020a634e487b7102600052603260045260246000fd5b906000526020600020015490508060088381548110611d765760e060020a634e487b7102600052603260045260246000fd5b6000918252602080832090910192909255828152600990915260408082208490558582528120556008805480611dbf5760e060020a634e487b7102600052603160045260246000fd5b6001900381819060005260206000200160009055905550505050565b6000611de68361097e565b600160a060020a039093166000908152600660209081526040808320868452825280832085905593825260079052919091209190915550565b508054611e2b90612300565b6000825580601f10611e3b575050565b601f01602090049060005260206000209081019061079391905b80821115611e695760008155600101611e55565b5090565b8035600160a060020a0381168114611e8457600080fd5b919050565b600060208284031215611e9a578081fd5b61181582611e6d565b60008060408385031215611eb5578081fd5b611ebe83611e6d565b9150611ecc60208401611e6d565b90509250929050565b600080600060608486031215611ee9578081fd5b611ef284611e6d565b9250611f0060208501611e6d565b9150604084013590509250925092565b60008060008060808587031215611f25578081fd5b611f2e85611e6d565b9350611f3c60208601611e6d565b925060408501359150606085013567ffffffffffffffff80821115611f5f578283fd5b818701915087601f830112611f72578283fd5b813581811115611f8457611f8461239f565b604051601f8201601f19908116603f01168101908382118183101715611fac57611fac61239f565b816040528281528a6020848701011115611fc4578586fd5b82602086016020830137918201602001949094529598949750929550505050565b60008060408385031215611ff7578182fd5b61200083611e6d565b915060208301358015158114612014578182fd5b809150509250929050565b60008060408385031215612031578182fd5b61203a83611e6d565b946020939093013593505050565b600060208284031215612059578081fd5b8135611815816123b8565b600060208284031215612075578081fd5b8151611815816123b8565b600060208284031215612091578081fd5b5035919050565b600081518084526120b08160208601602086016122d4565b601f01601f19169290920160200192915050565b600083516120d68184602088016122d4565b8351908301906120ea8183602088016122d4565b01949350505050565b6000600160a060020a038087168352808616602084015250836040830152608060608301526121256080830184612098565b9695505050505050565b6000602080830181845280855180835260408601915060408482028701019250838701855b8281101561218257603f19888603018452612170858351612098565b94509285019290850190600101612154565b5092979650505050505050565b6020815260006118156020830184612098565b60208082526032908201527f4552433732313a207472616e7366657220746f206e6f6e20455243373231526560408201527f63656976657220696d706c656d656e7465720000000000000000000000000000606082015260800190565b6020808252818101527f4f776e61626c653a2063616c6c6572206973206e6f7420746865206f776e6572604082015260600190565b60208082526031908201527f4552433732313a207472616e736665722063616c6c6572206973206e6f74206f60408201527f776e6572206e6f7220617070726f766564000000000000000000000000000000606082015260800190565b600082198211156122a4576122a461236d565b500190565b6000826122b8576122b8612386565b500490565b6000828210156122cf576122cf61236d565b500390565b60005b838110156122ef5781810151838201526020016122d7565b83811115610c725750506000910152565b60028104600182168061231457607f821691505b602082108114156123385760e060020a634e487b7102600052602260045260246000fd5b50919050565b60006000198214156123525761235261236d565b5060010190565b60008261236857612368612386565b500690565b60e060020a634e487b7102600052601160045260246000fd5b60e060020a634e487b7102600052601260045260246000fd5b60e060020a634e487b7102600052604160045260246000fd5b600160e060020a03198116811461079357600080fdfe68747470733a2f2f6d792d6a736f6e2d7365727665722e74797069636f64652e636f6d2f6c696e68766e677579656e392f6e66742d7365727665722d746573742f6e6674732fa26469706673582212200531cdde0ad269fa727e907c02f3bd71aabd16a60c16ebbe87292dd81c5f117e64736f6c63430008040033";

    public static final String FUNC_APPROVE = "approve";

    public static final String FUNC_BALANCEOF = "balanceOf";

    public static final String FUNC_BURN = "burn";

    public static final String FUNC_GETALLURI = "getAllURI";

    public static final String FUNC_GETAPPROVED = "getApproved";

    public static final String FUNC_ISAPPROVEDFORALL = "isApprovedForAll";

    public static final String FUNC_NAME = "name";

    public static final String FUNC_OWNER = "owner";

    public static final String FUNC_OWNEROF = "ownerOf";

    public static final String FUNC_PAUSE = "pause";

    public static final String FUNC_PAUSED = "paused";

    public static final String FUNC_RENOUNCEOWNERSHIP = "renounceOwnership";

    public static final String FUNC_SAFEMINT = "safeMint";

    public static final String FUNC_safeTransferFrom = "safeTransferFrom";

    public static final String FUNC_SETAPPROVALFORALL = "setApprovalForAll";

    public static final String FUNC_SUPPORTSINTERFACE = "supportsInterface";

    public static final String FUNC_SYMBOL = "symbol";

    public static final String FUNC_TOKENBYINDEX = "tokenByIndex";

    public static final String FUNC_TOKENOFOWNERBYINDEX = "tokenOfOwnerByIndex";

    public static final String FUNC_TOKENURI = "tokenURI";

    public static final String FUNC_TOTALSUPPLY = "totalSupply";

    public static final String FUNC_TRANSFERFROM = "transferFrom";

    public static final String FUNC_TRANSFEROWNERSHIP = "transferOwnership";

    public static final String FUNC_UNPAUSE = "unpause";

    public static final Event APPROVAL_EVENT = new Event("Approval",
            Arrays.<TypeReference<?>>asList(new TypeReference<Address>(true) {}, new TypeReference<Address>(true) {}, new TypeReference<Uint256>(true) {}));
    ;

    public static final Event APPROVALFORALL_EVENT = new Event("ApprovalForAll",
            Arrays.<TypeReference<?>>asList(new TypeReference<Address>(true) {}, new TypeReference<Address>(true) {}, new TypeReference<Bool>() {}));
    ;

    public static final Event OWNERSHIPTRANSFERRED_EVENT = new Event("OwnershipTransferred",
            Arrays.<TypeReference<?>>asList(new TypeReference<Address>(true) {}, new TypeReference<Address>(true) {}));
    ;

    public static final Event PAUSED_EVENT = new Event("Paused",
            Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}));
    ;

    public static final Event TRANSFER_EVENT = new Event("Transfer",
            Arrays.<TypeReference<?>>asList(new TypeReference<Address>(true) {}, new TypeReference<Address>(true) {}, new TypeReference<Uint256>(true) {}));
    ;

    public static final Event UNPAUSED_EVENT = new Event("Unpaused",
            Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}));
    ;

    @Deprecated
    protected NonFungibleToken4(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    protected NonFungibleToken4(String contractAddress, Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        super(BINARY, contractAddress, web3j, credentials, contractGasProvider);
    }

    @Deprecated
    protected NonFungibleToken4(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    protected NonFungibleToken4(String contractAddress, Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        super(BINARY, contractAddress, web3j, transactionManager, contractGasProvider);
    }

    public List<ApprovalEventResponse> getApprovalEvents(TransactionReceipt transactionReceipt) {
        List<Contract.EventValuesWithLog> valueList = extractEventParametersWithLog(APPROVAL_EVENT, transactionReceipt);
        ArrayList<ApprovalEventResponse> responses = new ArrayList<ApprovalEventResponse>(valueList.size());
        for (Contract.EventValuesWithLog eventValues : valueList) {
            ApprovalEventResponse typedResponse = new ApprovalEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.owner = (String) eventValues.getIndexedValues().get(0).getValue();
            typedResponse.approved = (String) eventValues.getIndexedValues().get(1).getValue();
            typedResponse.tokenId = (BigInteger) eventValues.getIndexedValues().get(2).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public Flowable<ApprovalEventResponse> approvalEventFlowable(EthFilter filter) {
        return web3j.ethLogFlowable(filter).map(new Function<Log, ApprovalEventResponse>() {
            @Override
            public ApprovalEventResponse apply(Log log) {
                Contract.EventValuesWithLog eventValues = extractEventParametersWithLog(APPROVAL_EVENT, log);
                ApprovalEventResponse typedResponse = new ApprovalEventResponse();
                typedResponse.log = log;
                typedResponse.owner = (String) eventValues.getIndexedValues().get(0).getValue();
                typedResponse.approved = (String) eventValues.getIndexedValues().get(1).getValue();
                typedResponse.tokenId = (BigInteger) eventValues.getIndexedValues().get(2).getValue();
                return typedResponse;
            }
        });
    }

    public Flowable<ApprovalEventResponse> approvalEventFlowable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(APPROVAL_EVENT));
        return approvalEventFlowable(filter);
    }

    public List<ApprovalForAllEventResponse> getApprovalForAllEvents(TransactionReceipt transactionReceipt) {
        List<Contract.EventValuesWithLog> valueList = extractEventParametersWithLog(APPROVALFORALL_EVENT, transactionReceipt);
        ArrayList<ApprovalForAllEventResponse> responses = new ArrayList<ApprovalForAllEventResponse>(valueList.size());
        for (Contract.EventValuesWithLog eventValues : valueList) {
            ApprovalForAllEventResponse typedResponse = new ApprovalForAllEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.owner = (String) eventValues.getIndexedValues().get(0).getValue();
            typedResponse.operator = (String) eventValues.getIndexedValues().get(1).getValue();
            typedResponse.approved = (Boolean) eventValues.getNonIndexedValues().get(0).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public Flowable<ApprovalForAllEventResponse> approvalForAllEventFlowable(EthFilter filter) {
        return web3j.ethLogFlowable(filter).map(new Function<Log, ApprovalForAllEventResponse>() {
            @Override
            public ApprovalForAllEventResponse apply(Log log) {
                Contract.EventValuesWithLog eventValues = extractEventParametersWithLog(APPROVALFORALL_EVENT, log);
                ApprovalForAllEventResponse typedResponse = new ApprovalForAllEventResponse();
                typedResponse.log = log;
                typedResponse.owner = (String) eventValues.getIndexedValues().get(0).getValue();
                typedResponse.operator = (String) eventValues.getIndexedValues().get(1).getValue();
                typedResponse.approved = (Boolean) eventValues.getNonIndexedValues().get(0).getValue();
                return typedResponse;
            }
        });
    }

    public Flowable<ApprovalForAllEventResponse> approvalForAllEventFlowable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(APPROVALFORALL_EVENT));
        return approvalForAllEventFlowable(filter);
    }

    public List<OwnershipTransferredEventResponse> getOwnershipTransferredEvents(TransactionReceipt transactionReceipt) {
        List<Contract.EventValuesWithLog> valueList = extractEventParametersWithLog(OWNERSHIPTRANSFERRED_EVENT, transactionReceipt);
        ArrayList<OwnershipTransferredEventResponse> responses = new ArrayList<OwnershipTransferredEventResponse>(valueList.size());
        for (Contract.EventValuesWithLog eventValues : valueList) {
            OwnershipTransferredEventResponse typedResponse = new OwnershipTransferredEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.previousOwner = (String) eventValues.getIndexedValues().get(0).getValue();
            typedResponse.newOwner = (String) eventValues.getIndexedValues().get(1).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public Flowable<OwnershipTransferredEventResponse> ownershipTransferredEventFlowable(EthFilter filter) {
        return web3j.ethLogFlowable(filter).map(new Function<Log, OwnershipTransferredEventResponse>() {
            @Override
            public OwnershipTransferredEventResponse apply(Log log) {
                Contract.EventValuesWithLog eventValues = extractEventParametersWithLog(OWNERSHIPTRANSFERRED_EVENT, log);
                OwnershipTransferredEventResponse typedResponse = new OwnershipTransferredEventResponse();
                typedResponse.log = log;
                typedResponse.previousOwner = (String) eventValues.getIndexedValues().get(0).getValue();
                typedResponse.newOwner = (String) eventValues.getIndexedValues().get(1).getValue();
                return typedResponse;
            }
        });
    }

    public Flowable<OwnershipTransferredEventResponse> ownershipTransferredEventFlowable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(OWNERSHIPTRANSFERRED_EVENT));
        return ownershipTransferredEventFlowable(filter);
    }

    public List<PausedEventResponse> getPausedEvents(TransactionReceipt transactionReceipt) {
        List<Contract.EventValuesWithLog> valueList = extractEventParametersWithLog(PAUSED_EVENT, transactionReceipt);
        ArrayList<PausedEventResponse> responses = new ArrayList<PausedEventResponse>(valueList.size());
        for (Contract.EventValuesWithLog eventValues : valueList) {
            PausedEventResponse typedResponse = new PausedEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.account = (String) eventValues.getNonIndexedValues().get(0).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public Flowable<PausedEventResponse> pausedEventFlowable(EthFilter filter) {
        return web3j.ethLogFlowable(filter).map(new Function<Log, PausedEventResponse>() {
            @Override
            public PausedEventResponse apply(Log log) {
                Contract.EventValuesWithLog eventValues = extractEventParametersWithLog(PAUSED_EVENT, log);
                PausedEventResponse typedResponse = new PausedEventResponse();
                typedResponse.log = log;
                typedResponse.account = (String) eventValues.getNonIndexedValues().get(0).getValue();
                return typedResponse;
            }
        });
    }

    public Flowable<PausedEventResponse> pausedEventFlowable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(PAUSED_EVENT));
        return pausedEventFlowable(filter);
    }

    public List<TransferEventResponse> getTransferEvents(TransactionReceipt transactionReceipt) {
        List<Contract.EventValuesWithLog> valueList = extractEventParametersWithLog(TRANSFER_EVENT, transactionReceipt);
        ArrayList<TransferEventResponse> responses = new ArrayList<TransferEventResponse>(valueList.size());
        for (Contract.EventValuesWithLog eventValues : valueList) {
            TransferEventResponse typedResponse = new TransferEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.from = (String) eventValues.getIndexedValues().get(0).getValue();
            typedResponse.to = (String) eventValues.getIndexedValues().get(1).getValue();
            typedResponse.tokenId = (BigInteger) eventValues.getIndexedValues().get(2).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public Flowable<TransferEventResponse> transferEventFlowable(EthFilter filter) {
        return web3j.ethLogFlowable(filter).map(new Function<Log, TransferEventResponse>() {
            @Override
            public TransferEventResponse apply(Log log) {
                Contract.EventValuesWithLog eventValues = extractEventParametersWithLog(TRANSFER_EVENT, log);
                TransferEventResponse typedResponse = new TransferEventResponse();
                typedResponse.log = log;
                typedResponse.from = (String) eventValues.getIndexedValues().get(0).getValue();
                typedResponse.to = (String) eventValues.getIndexedValues().get(1).getValue();
                typedResponse.tokenId = (BigInteger) eventValues.getIndexedValues().get(2).getValue();
                return typedResponse;
            }
        });
    }

    public Flowable<TransferEventResponse> transferEventFlowable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(TRANSFER_EVENT));
        return transferEventFlowable(filter);
    }

    public List<UnpausedEventResponse> getUnpausedEvents(TransactionReceipt transactionReceipt) {
        List<Contract.EventValuesWithLog> valueList = extractEventParametersWithLog(UNPAUSED_EVENT, transactionReceipt);
        ArrayList<UnpausedEventResponse> responses = new ArrayList<UnpausedEventResponse>(valueList.size());
        for (Contract.EventValuesWithLog eventValues : valueList) {
            UnpausedEventResponse typedResponse = new UnpausedEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.account = (String) eventValues.getNonIndexedValues().get(0).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public Flowable<UnpausedEventResponse> unpausedEventFlowable(EthFilter filter) {
        return web3j.ethLogFlowable(filter).map(new Function<Log, UnpausedEventResponse>() {
            @Override
            public UnpausedEventResponse apply(Log log) {
                Contract.EventValuesWithLog eventValues = extractEventParametersWithLog(UNPAUSED_EVENT, log);
                UnpausedEventResponse typedResponse = new UnpausedEventResponse();
                typedResponse.log = log;
                typedResponse.account = (String) eventValues.getNonIndexedValues().get(0).getValue();
                return typedResponse;
            }
        });
    }

    public Flowable<UnpausedEventResponse> unpausedEventFlowable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(UNPAUSED_EVENT));
        return unpausedEventFlowable(filter);
    }

    public RemoteFunctionCall<TransactionReceipt> approve(String to, BigInteger tokenId) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(
                FUNC_APPROVE,
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(160, to),
                        new org.web3j.abi.datatypes.generated.Uint256(tokenId)),
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<BigInteger> balanceOf(String owner) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_BALANCEOF,
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(160, owner)),
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteFunctionCall<TransactionReceipt> burn(BigInteger tokenId) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(
                FUNC_BURN,
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(tokenId)),
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<List> getAllURI() {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_GETALLURI,
                Arrays.<Type>asList(),
                Arrays.<TypeReference<?>>asList(new TypeReference<DynamicArray<Utf8String>>() {}));
        return new RemoteFunctionCall<List>(function,
                new Callable<List>() {
                    @Override
                    @SuppressWarnings("unchecked")
                    public List call() throws Exception {
                        List<Type> result = (List<Type>) executeCallSingleValueReturn(function, List.class);
                        return convertToNative(result);
                    }
                });
    }

    public RemoteFunctionCall<String> getApproved(BigInteger tokenId) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_GETAPPROVED,
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(tokenId)),
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}));
        return executeRemoteCallSingleValueReturn(function, String.class);
    }

    public RemoteFunctionCall<Boolean> isApprovedForAll(String owner, String operator) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_ISAPPROVEDFORALL,
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(160, owner),
                        new org.web3j.abi.datatypes.Address(160, operator)),
                Arrays.<TypeReference<?>>asList(new TypeReference<Bool>() {}));
        return executeRemoteCallSingleValueReturn(function, Boolean.class);
    }

    public RemoteFunctionCall<String> name() {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_NAME,
                Arrays.<Type>asList(),
                Arrays.<TypeReference<?>>asList(new TypeReference<Utf8String>() {}));
        return executeRemoteCallSingleValueReturn(function, String.class);
    }

    public RemoteFunctionCall<String> owner() {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_OWNER,
                Arrays.<Type>asList(),
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}));
        return executeRemoteCallSingleValueReturn(function, String.class);
    }

    public RemoteFunctionCall<String> ownerOf(BigInteger tokenId) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_OWNEROF,
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(tokenId)),
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}));
        return executeRemoteCallSingleValueReturn(function, String.class);
    }

    public RemoteFunctionCall<TransactionReceipt> pause() {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(
                FUNC_PAUSE,
                Arrays.<Type>asList(),
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<Boolean> paused() {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_PAUSED,
                Arrays.<Type>asList(),
                Arrays.<TypeReference<?>>asList(new TypeReference<Bool>() {}));
        return executeRemoteCallSingleValueReturn(function, Boolean.class);
    }

    public RemoteFunctionCall<TransactionReceipt> renounceOwnership() {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(
                FUNC_RENOUNCEOWNERSHIP,
                Arrays.<Type>asList(),
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<TransactionReceipt> safeMint(String to) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(
                FUNC_SAFEMINT,
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(160, to)),
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<TransactionReceipt> safeTransferFrom(String from, String to, BigInteger tokenId) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(
                FUNC_safeTransferFrom,
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(160, from),
                        new org.web3j.abi.datatypes.Address(160, to),
                        new org.web3j.abi.datatypes.generated.Uint256(tokenId)),
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<TransactionReceipt> safeTransferFrom(String from, String to, BigInteger tokenId, byte[] _data) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(
                FUNC_safeTransferFrom,
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(160, from),
                        new org.web3j.abi.datatypes.Address(160, to),
                        new org.web3j.abi.datatypes.generated.Uint256(tokenId),
                        new org.web3j.abi.datatypes.DynamicBytes(_data)),
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<TransactionReceipt> setApprovalForAll(String operator, Boolean approved) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(
                FUNC_SETAPPROVALFORALL,
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(160, operator),
                        new org.web3j.abi.datatypes.Bool(approved)),
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<Boolean> supportsInterface(byte[] interfaceId) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_SUPPORTSINTERFACE,
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Bytes4(interfaceId)),
                Arrays.<TypeReference<?>>asList(new TypeReference<Bool>() {}));
        return executeRemoteCallSingleValueReturn(function, Boolean.class);
    }

    public RemoteFunctionCall<String> symbol() {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_SYMBOL,
                Arrays.<Type>asList(),
                Arrays.<TypeReference<?>>asList(new TypeReference<Utf8String>() {}));
        return executeRemoteCallSingleValueReturn(function, String.class);
    }

    public RemoteFunctionCall<BigInteger> tokenByIndex(BigInteger index) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_TOKENBYINDEX,
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(index)),
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteFunctionCall<BigInteger> tokenOfOwnerByIndex(String owner, BigInteger index) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_TOKENOFOWNERBYINDEX,
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(160, owner),
                        new org.web3j.abi.datatypes.generated.Uint256(index)),
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteFunctionCall<String> tokenURI(BigInteger tokenId) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_TOKENURI,
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(tokenId)),
                Arrays.<TypeReference<?>>asList(new TypeReference<Utf8String>() {}));
        return executeRemoteCallSingleValueReturn(function, String.class);
    }

    public RemoteFunctionCall<BigInteger> totalSupply() {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_TOTALSUPPLY,
                Arrays.<Type>asList(),
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteFunctionCall<TransactionReceipt> transferFrom(String from, String to, BigInteger tokenId) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(
                FUNC_TRANSFERFROM,
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(160, from),
                        new org.web3j.abi.datatypes.Address(160, to),
                        new org.web3j.abi.datatypes.generated.Uint256(tokenId)),
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<TransactionReceipt> transferOwnership(String newOwner) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(
                FUNC_TRANSFEROWNERSHIP,
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(160, newOwner)),
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<TransactionReceipt> unpause() {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(
                FUNC_UNPAUSE,
                Arrays.<Type>asList(),
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    @Deprecated
    public static NonFungibleToken4 load(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        return new NonFungibleToken4(contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    @Deprecated
    public static NonFungibleToken4 load(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return new NonFungibleToken4(contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    public static NonFungibleToken4 load(String contractAddress, Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        return new NonFungibleToken4(contractAddress, web3j, credentials, contractGasProvider);
    }

    public static NonFungibleToken4 load(String contractAddress, Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        return new NonFungibleToken4(contractAddress, web3j, transactionManager, contractGasProvider);
    }

    public static RemoteCall<NonFungibleToken4> deploy(Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        return deployRemoteCall(NonFungibleToken4.class, web3j, credentials, contractGasProvider, BINARY, "");
    }

    public static RemoteCall<NonFungibleToken4> deploy(Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        return deployRemoteCall(NonFungibleToken4.class, web3j, transactionManager, contractGasProvider, BINARY, "");
    }

    @Deprecated
    public static RemoteCall<NonFungibleToken4> deploy(Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        return deployRemoteCall(NonFungibleToken4.class, web3j, credentials, gasPrice, gasLimit, BINARY, "");
    }

    @Deprecated
    public static RemoteCall<NonFungibleToken4> deploy(Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return deployRemoteCall(NonFungibleToken4.class, web3j, transactionManager, gasPrice, gasLimit, BINARY, "");
    }

    public static class ApprovalEventResponse extends BaseEventResponse {
        public String owner;

        public String approved;

        public BigInteger tokenId;
    }

    public static class ApprovalForAllEventResponse extends BaseEventResponse {
        public String owner;

        public String operator;

        public Boolean approved;
    }

    public static class OwnershipTransferredEventResponse extends BaseEventResponse {
        public String previousOwner;

        public String newOwner;
    }

    public static class PausedEventResponse extends BaseEventResponse {
        public String account;
    }

    public static class TransferEventResponse extends BaseEventResponse {
        public String from;

        public String to;

        public BigInteger tokenId;
    }

    public static class UnpausedEventResponse extends BaseEventResponse {
        public String account;
    }
}
