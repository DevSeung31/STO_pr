package org.zerock.sto_pr.contract;

import io.reactivex.Flowable;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import javax.annotation.processing.Generated;
import org.web3j.abi.EventEncoder;
import org.web3j.abi.FunctionEncoder;
import org.web3j.abi.TypeReference;
import org.web3j.abi.datatypes.Address;
import org.web3j.abi.datatypes.CustomError;
import org.web3j.abi.datatypes.Event;
import org.web3j.abi.datatypes.Function;
import org.web3j.abi.datatypes.Type;
import org.web3j.abi.datatypes.Utf8String;
import org.web3j.abi.datatypes.generated.Uint256;
import org.web3j.abi.datatypes.generated.Uint8;
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
 * <a href="https://github.com/LFDT-web3j/web3j/tree/main/codegen">codegen module</a> to update.
 *
 * <p>Generated with web3j version 1.8.0.
 */
@SuppressWarnings("rawtypes")
@Generated("org.web3j.codegen.SolidityFunctionWrapperGenerator")
public class StoToken extends Contract {
    public static final String BINARY = "60a060405234801561001057600080fd5b50604051611d98380380611d98833981810160405281019061003291906106b6565b33858581600390816100449190610980565b5080600490816100549190610980565b505050600073ffffffffffffffffffffffffffffffffffffffff168173ffffffffffffffffffffffffffffffffffffffff16036100c95760006040517f1e4fbdf70000000000000000000000000000000000000000000000000000000081526004016100c09190610a61565b60405180910390fd5b6100d88161010160201b60201c565b508160ff1660808160ff16815250506100f781846101c760201b60201c565b5050505050610b40565b6000600560009054906101000a900473ffffffffffffffffffffffffffffffffffffffff16905081600560006101000a81548173ffffffffffffffffffffffffffffffffffffffff021916908373ffffffffffffffffffffffffffffffffffffffff1602179055508173ffffffffffffffffffffffffffffffffffffffff168173ffffffffffffffffffffffffffffffffffffffff167f8be0079c531659141344cd1fd0a4f28419497f9722a3daafe3b4186f6b6457e060405160405180910390a35050565b600073ffffffffffffffffffffffffffffffffffffffff168273ffffffffffffffffffffffffffffffffffffffff16036102395760006040517fec442f050000000000000000000000000000000000000000000000000000000081526004016102309190610a61565b60405180910390fd5b61024b6000838361024f60201b60201c565b5050565b600073ffffffffffffffffffffffffffffffffffffffff168373ffffffffffffffffffffffffffffffffffffffff16036102a15780600260008282546102959190610aab565b92505081905550610374565b60008060008573ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1681526020019081526020016000205490508181101561032d578381836040517fe450d38c00000000000000000000000000000000000000000000000000000000815260040161032493929190610aee565b60405180910390fd5b8181036000808673ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200190815260200160002081905550505b600073ffffffffffffffffffffffffffffffffffffffff168273ffffffffffffffffffffffffffffffffffffffff16036103bd578060026000828254039250508190555061040a565b806000808473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff168152602001908152602001600020600082825401925050819055505b8173ffffffffffffffffffffffffffffffffffffffff168373ffffffffffffffffffffffffffffffffffffffff167fddf252ad1be2c89b69c2b068fc378daa952ba7f163c4a11628f55a4df523b3ef836040516104679190610b25565b60405180910390a3505050565b6000604051905090565b600080fd5b600080fd5b600080fd5b600080fd5b6000601f19601f8301169050919050565b7f4e487b7100000000000000000000000000000000000000000000000000000000600052604160045260246000fd5b6104db82610492565b810181811067ffffffffffffffff821117156104fa576104f96104a3565b5b80604052505050565b600061050d610474565b905061051982826104d2565b919050565b600067ffffffffffffffff821115610539576105386104a3565b5b61054282610492565b9050602081019050919050565b60005b8381101561056d578082015181840152602081019050610552565b60008484015250505050565b600061058c6105878461051e565b610503565b9050828152602081018484840111156105a8576105a761048d565b5b6105b384828561054f565b509392505050565b600082601f8301126105d0576105cf610488565b5b81516105e0848260208601610579565b91505092915050565b6000819050919050565b6105fc816105e9565b811461060757600080fd5b50565b600081519050610619816105f3565b92915050565b600060ff82169050919050565b6106358161061f565b811461064057600080fd5b50565b6000815190506106528161062c565b92915050565b600073ffffffffffffffffffffffffffffffffffffffff82169050919050565b600061068382610658565b9050919050565b61069381610678565b811461069e57600080fd5b50565b6000815190506106b08161068a565b92915050565b600080600080600060a086880312156106d2576106d161047e565b5b600086015167ffffffffffffffff8111156106f0576106ef610483565b5b6106fc888289016105bb565b955050602086015167ffffffffffffffff81111561071d5761071c610483565b5b610729888289016105bb565b945050604061073a8882890161060a565b935050606061074b88828901610643565b925050608061075c888289016106a1565b9150509295509295909350565b600081519050919050565b7f4e487b7100000000000000000000000000000000000000000000000000000000600052602260045260246000fd5b600060028204905060018216806107bb57607f821691505b6020821081036107ce576107cd610774565b5b50919050565b60008190508160005260206000209050919050565b60006020601f8301049050919050565b600082821b905092915050565b6000600883026108367fffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffff826107f9565b61084086836107f9565b95508019841693508086168417925050509392505050565b6000819050919050565b600061087d610878610873846105e9565b610858565b6105e9565b9050919050565b6000819050919050565b61089783610862565b6108ab6108a382610884565b848454610806565b825550505050565b600090565b6108c06108b3565b6108cb81848461088e565b505050565b5b818110156108ef576108e46000826108b8565b6001810190506108d1565b5050565b601f82111561093457610905816107d4565b61090e846107e9565b8101602085101561091d578190505b610931610929856107e9565b8301826108d0565b50505b505050565b600082821c905092915050565b600061095760001984600802610939565b1980831691505092915050565b60006109708383610946565b9150826002028217905092915050565b61098982610769565b67ffffffffffffffff8111156109a2576109a16104a3565b5b6109ac82546107a3565b6109b78282856108f3565b600060209050601f8311600181146109ea57600084156109d8578287015190505b6109e28582610964565b865550610a4a565b601f1984166109f8866107d4565b60005b82811015610a20578489015182556001820191506020850194506020810190506109fb565b86831015610a3d5784890151610a39601f891682610946565b8355505b6001600288020188555050505b505050505050565b610a5b81610678565b82525050565b6000602082019050610a766000830184610a52565b92915050565b7f4e487b7100000000000000000000000000000000000000000000000000000000600052601160045260246000fd5b6000610ab6826105e9565b9150610ac1836105e9565b9250828201905080821115610ad957610ad8610a7c565b5b92915050565b610ae8816105e9565b82525050565b6000606082019050610b036000830186610a52565b610b106020830185610adf565b610b1d6040830184610adf565b949350505050565b6000602082019050610b3a6000830184610adf565b92915050565b60805161123d610b5b600039600061038e015261123d6000f3fe608060405234801561001057600080fd5b50600436106100cf5760003560e01c8063715018a61161008c57806395d89b411161006657806395d89b4114610202578063a9059cbb14610220578063dd62ed3e14610250578063f2fde38b14610280576100cf565b8063715018a6146101be5780637328d569146101c85780638da5cb5b146101e4576100cf565b806306fdde03146100d4578063095ea7b3146100f257806318160ddd1461012257806323b872dd14610140578063313ce5671461017057806370a082311461018e575b600080fd5b6100dc61029c565b6040516100e99190610ded565b60405180910390f35b61010c60048036038101906101079190610ea8565b61032e565b6040516101199190610f03565b60405180910390f35b61012a610351565b6040516101379190610f2d565b60405180910390f35b61015a60048036038101906101559190610f48565b61035b565b6040516101679190610f03565b60405180910390f35b61017861038a565b6040516101859190610fb7565b60405180910390f35b6101a860048036038101906101a39190610fd2565b6103b2565b6040516101b59190610f2d565b60405180910390f35b6101c66103fa565b005b6101e260048036038101906101dd9190610fff565b61040e565b005b6101ec610485565b6040516101f99190611089565b60405180910390f35b61020a6104af565b6040516102179190610ded565b60405180910390f35b61023a60048036038101906102359190610ea8565b610541565b6040516102479190610f03565b60405180910390f35b61026a600480360381019061026591906110a4565b610564565b6040516102779190610f2d565b60405180910390f35b61029a60048036038101906102959190610fd2565b6105eb565b005b6060600380546102ab90611113565b80601f01602080910402602001604051908101604052809291908181526020018280546102d790611113565b80156103245780601f106102f957610100808354040283529160200191610324565b820191906000526020600020905b81548152906001019060200180831161030757829003601f168201915b5050505050905090565b600080610339610671565b9050610346818585610679565b600191505092915050565b6000600254905090565b600080610366610671565b905061037385828561068b565b61037e858585610720565b60019150509392505050565b60007f0000000000000000000000000000000000000000000000000000000000000000905090565b60008060008373ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff168152602001908152602001600020549050919050565b610402610814565b61040c600061089b565b565b610416610814565b8273ffffffffffffffffffffffffffffffffffffffff168473ffffffffffffffffffffffffffffffffffffffff16867f753ae2aaaf39e14f83785986367e2865dbbccf8c9b24ce246f61939bc0f5d7a88585604051610476929190611144565b60405180910390a45050505050565b6000600560009054906101000a900473ffffffffffffffffffffffffffffffffffffffff16905090565b6060600480546104be90611113565b80601f01602080910402602001604051908101604052809291908181526020018280546104ea90611113565b80156105375780601f1061050c57610100808354040283529160200191610537565b820191906000526020600020905b81548152906001019060200180831161051a57829003601f168201915b5050505050905090565b60008061054c610671565b9050610559818585610720565b600191505092915050565b6000600160008473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200190815260200160002060008373ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200190815260200160002054905092915050565b6105f3610814565b600073ffffffffffffffffffffffffffffffffffffffff168173ffffffffffffffffffffffffffffffffffffffff16036106655760006040517f1e4fbdf700000000000000000000000000000000000000000000000000000000815260040161065c9190611089565b60405180910390fd5b61066e8161089b565b50565b600033905090565b6106868383836001610961565b505050565b60006106978484610564565b90507fffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffff81101561071a578181101561070a578281836040517ffb8f41b20000000000000000000000000000000000000000000000000000000081526004016107019392919061116d565b60405180910390fd5b61071984848484036000610961565b5b50505050565b600073ffffffffffffffffffffffffffffffffffffffff168373ffffffffffffffffffffffffffffffffffffffff16036107925760006040517f96c6fd1e0000000000000000000000000000000000000000000000000000000081526004016107899190611089565b60405180910390fd5b600073ffffffffffffffffffffffffffffffffffffffff168273ffffffffffffffffffffffffffffffffffffffff16036108045760006040517fec442f050000000000000000000000000000000000000000000000000000000081526004016107fb9190611089565b60405180910390fd5b61080f838383610b38565b505050565b61081c610671565b73ffffffffffffffffffffffffffffffffffffffff1661083a610485565b73ffffffffffffffffffffffffffffffffffffffff16146108995761085d610671565b6040517f118cdaa70000000000000000000000000000000000000000000000000000000081526004016108909190611089565b60405180910390fd5b565b6000600560009054906101000a900473ffffffffffffffffffffffffffffffffffffffff16905081600560006101000a81548173ffffffffffffffffffffffffffffffffffffffff021916908373ffffffffffffffffffffffffffffffffffffffff1602179055508173ffffffffffffffffffffffffffffffffffffffff168173ffffffffffffffffffffffffffffffffffffffff167f8be0079c531659141344cd1fd0a4f28419497f9722a3daafe3b4186f6b6457e060405160405180910390a35050565b600073ffffffffffffffffffffffffffffffffffffffff168473ffffffffffffffffffffffffffffffffffffffff16036109d35760006040517fe602df050000000000000000000000000000000000000000000000000000000081526004016109ca9190611089565b60405180910390fd5b600073ffffffffffffffffffffffffffffffffffffffff168373ffffffffffffffffffffffffffffffffffffffff1603610a455760006040517f94280d62000000000000000000000000000000000000000000000000000000008152600401610a3c9190611089565b60405180910390fd5b81600160008673ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200190815260200160002060008573ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff168152602001908152602001600020819055508015610b32578273ffffffffffffffffffffffffffffffffffffffff168473ffffffffffffffffffffffffffffffffffffffff167f8c5be1e5ebec7d5bd14f71427d1e84f3dd0314c0f7b2291e5b200ac8c7c3b92584604051610b299190610f2d565b60405180910390a35b50505050565b600073ffffffffffffffffffffffffffffffffffffffff168373ffffffffffffffffffffffffffffffffffffffff1603610b8a578060026000828254610b7e91906111d3565b92505081905550610c5d565b60008060008573ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200190815260200160002054905081811015610c16578381836040517fe450d38c000000000000000000000000000000000000000000000000000000008152600401610c0d9392919061116d565b60405180910390fd5b8181036000808673ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200190815260200160002081905550505b600073ffffffffffffffffffffffffffffffffffffffff168273ffffffffffffffffffffffffffffffffffffffff1603610ca65780600260008282540392505081905550610cf3565b806000808473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff168152602001908152602001600020600082825401925050819055505b8173ffffffffffffffffffffffffffffffffffffffff168373ffffffffffffffffffffffffffffffffffffffff167fddf252ad1be2c89b69c2b068fc378daa952ba7f163c4a11628f55a4df523b3ef83604051610d509190610f2d565b60405180910390a3505050565b600081519050919050565b600082825260208201905092915050565b60005b83811015610d97578082015181840152602081019050610d7c565b60008484015250505050565b6000601f19601f8301169050919050565b6000610dbf82610d5d565b610dc98185610d68565b9350610dd9818560208601610d79565b610de281610da3565b840191505092915050565b60006020820190508181036000830152610e078184610db4565b905092915050565b600080fd5b600073ffffffffffffffffffffffffffffffffffffffff82169050919050565b6000610e3f82610e14565b9050919050565b610e4f81610e34565b8114610e5a57600080fd5b50565b600081359050610e6c81610e46565b92915050565b6000819050919050565b610e8581610e72565b8114610e9057600080fd5b50565b600081359050610ea281610e7c565b92915050565b60008060408385031215610ebf57610ebe610e0f565b5b6000610ecd85828601610e5d565b9250506020610ede85828601610e93565b9150509250929050565b60008115159050919050565b610efd81610ee8565b82525050565b6000602082019050610f186000830184610ef4565b92915050565b610f2781610e72565b82525050565b6000602082019050610f426000830184610f1e565b92915050565b600080600060608486031215610f6157610f60610e0f565b5b6000610f6f86828701610e5d565b9350506020610f8086828701610e5d565b9250506040610f9186828701610e93565b9150509250925092565b600060ff82169050919050565b610fb181610f9b565b82525050565b6000602082019050610fcc6000830184610fa8565b92915050565b600060208284031215610fe857610fe7610e0f565b5b6000610ff684828501610e5d565b91505092915050565b600080600080600060a0868803121561101b5761101a610e0f565b5b600061102988828901610e93565b955050602061103a88828901610e5d565b945050604061104b88828901610e5d565b935050606061105c88828901610e93565b925050608061106d88828901610e93565b9150509295509295909350565b61108381610e34565b82525050565b600060208201905061109e600083018461107a565b92915050565b600080604083850312156110bb576110ba610e0f565b5b60006110c985828601610e5d565b92505060206110da85828601610e5d565b9150509250929050565b7f4e487b7100000000000000000000000000000000000000000000000000000000600052602260045260246000fd5b6000600282049050600182168061112b57607f821691505b60208210810361113e5761113d6110e4565b5b50919050565b60006040820190506111596000830185610f1e565b6111666020830184610f1e565b9392505050565b6000606082019050611182600083018661107a565b61118f6020830185610f1e565b61119c6040830184610f1e565b949350505050565b7f4e487b7100000000000000000000000000000000000000000000000000000000600052601160045260246000fd5b60006111de82610e72565b91506111e983610e72565b9250828201905080821115611201576112006111a4565b5b9291505056fea26469706673582212208a12c681e83397876446a5b75b33d8ad362bb54e1f2527526c25c0f4adda296164736f6c634300081c0033";

    private static String librariesLinkedBinary;

    public static final String FUNC_ALLOWANCE = "allowance";

    public static final String FUNC_APPROVE = "approve";

    public static final String FUNC_BALANCEOF = "balanceOf";

    public static final String FUNC_DECIMALS = "decimals";

    public static final String FUNC_NAME = "name";

    public static final String FUNC_OWNER = "owner";

    public static final String FUNC_RECORDTRADE = "recordTrade";

    public static final String FUNC_RENOUNCEOWNERSHIP = "renounceOwnership";

    public static final String FUNC_SYMBOL = "symbol";

    public static final String FUNC_TOTALSUPPLY = "totalSupply";

    public static final String FUNC_TRANSFER = "transfer";

    public static final String FUNC_TRANSFERFROM = "transferFrom";

    public static final String FUNC_TRANSFEROWNERSHIP = "transferOwnership";

    public static final CustomError ERC20INSUFFICIENTALLOWANCE_ERROR = new CustomError("ERC20InsufficientAllowance", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}, new TypeReference<Uint256>() {}, new TypeReference<Uint256>() {}));
    ;

    public static final CustomError ERC20INSUFFICIENTBALANCE_ERROR = new CustomError("ERC20InsufficientBalance", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}, new TypeReference<Uint256>() {}, new TypeReference<Uint256>() {}));
    ;

    public static final CustomError ERC20INVALIDAPPROVER_ERROR = new CustomError("ERC20InvalidApprover", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}));
    ;

    public static final CustomError ERC20INVALIDRECEIVER_ERROR = new CustomError("ERC20InvalidReceiver", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}));
    ;

    public static final CustomError ERC20INVALIDSENDER_ERROR = new CustomError("ERC20InvalidSender", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}));
    ;

    public static final CustomError ERC20INVALIDSPENDER_ERROR = new CustomError("ERC20InvalidSpender", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}));
    ;

    public static final CustomError OWNABLEINVALIDOWNER_ERROR = new CustomError("OwnableInvalidOwner", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}));
    ;

    public static final CustomError OWNABLEUNAUTHORIZEDACCOUNT_ERROR = new CustomError("OwnableUnauthorizedAccount", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}));
    ;

    public static final Event APPROVAL_EVENT = new Event("Approval", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Address>(true) {}, new TypeReference<Address>(true) {}, new TypeReference<Uint256>() {}));
    ;

    public static final Event OWNERSHIPTRANSFERRED_EVENT = new Event("OwnershipTransferred", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Address>(true) {}, new TypeReference<Address>(true) {}));
    ;

    public static final Event TRADERECORDED_EVENT = new Event("TradeRecorded", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>(true) {}, new TypeReference<Address>(true) {}, new TypeReference<Address>(true) {}, new TypeReference<Uint256>() {}, new TypeReference<Uint256>() {}));
    ;

    public static final Event TRANSFER_EVENT = new Event("Transfer", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Address>(true) {}, new TypeReference<Address>(true) {}, new TypeReference<Uint256>() {}));
    ;

    @Deprecated
    protected StoToken(String contractAddress, Web3j web3j, Credentials credentials,
            BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    protected StoToken(String contractAddress, Web3j web3j, Credentials credentials,
            ContractGasProvider contractGasProvider) {
        super(BINARY, contractAddress, web3j, credentials, contractGasProvider);
    }

    @Deprecated
    protected StoToken(String contractAddress, Web3j web3j, TransactionManager transactionManager,
            BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    protected StoToken(String contractAddress, Web3j web3j, TransactionManager transactionManager,
            ContractGasProvider contractGasProvider) {
        super(BINARY, contractAddress, web3j, transactionManager, contractGasProvider);
    }

    public static List<ApprovalEventResponse> getApprovalEvents(
            TransactionReceipt transactionReceipt) {
        List<Contract.EventValuesWithLog> valueList = staticExtractEventParametersWithLog(APPROVAL_EVENT, transactionReceipt);
        ArrayList<ApprovalEventResponse> responses = new ArrayList<ApprovalEventResponse>(valueList.size());
        for (Contract.EventValuesWithLog eventValues : valueList) {
            ApprovalEventResponse typedResponse = new ApprovalEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.owner = (String) eventValues.getIndexedValues().get(0).getValue();
            typedResponse.spender = (String) eventValues.getIndexedValues().get(1).getValue();
            typedResponse.value = (BigInteger) eventValues.getNonIndexedValues().get(0).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public static ApprovalEventResponse getApprovalEventFromLog(Log log) {
        Contract.EventValuesWithLog eventValues = staticExtractEventParametersWithLog(APPROVAL_EVENT, log);
        ApprovalEventResponse typedResponse = new ApprovalEventResponse();
        typedResponse.log = log;
        typedResponse.owner = (String) eventValues.getIndexedValues().get(0).getValue();
        typedResponse.spender = (String) eventValues.getIndexedValues().get(1).getValue();
        typedResponse.value = (BigInteger) eventValues.getNonIndexedValues().get(0).getValue();
        return typedResponse;
    }

    public Flowable<ApprovalEventResponse> approvalEventFlowable(EthFilter filter) {
        return web3j.ethLogFlowable(filter).map(log -> getApprovalEventFromLog(log));
    }

    public Flowable<ApprovalEventResponse> approvalEventFlowable(DefaultBlockParameter startBlock,
            DefaultBlockParameter endBlock) {
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(APPROVAL_EVENT));
        return approvalEventFlowable(filter);
    }

    public static List<OwnershipTransferredEventResponse> getOwnershipTransferredEvents(
            TransactionReceipt transactionReceipt) {
        List<Contract.EventValuesWithLog> valueList = staticExtractEventParametersWithLog(OWNERSHIPTRANSFERRED_EVENT, transactionReceipt);
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

    public static OwnershipTransferredEventResponse getOwnershipTransferredEventFromLog(Log log) {
        Contract.EventValuesWithLog eventValues = staticExtractEventParametersWithLog(OWNERSHIPTRANSFERRED_EVENT, log);
        OwnershipTransferredEventResponse typedResponse = new OwnershipTransferredEventResponse();
        typedResponse.log = log;
        typedResponse.previousOwner = (String) eventValues.getIndexedValues().get(0).getValue();
        typedResponse.newOwner = (String) eventValues.getIndexedValues().get(1).getValue();
        return typedResponse;
    }

    public Flowable<OwnershipTransferredEventResponse> ownershipTransferredEventFlowable(
            EthFilter filter) {
        return web3j.ethLogFlowable(filter).map(log -> getOwnershipTransferredEventFromLog(log));
    }

    public Flowable<OwnershipTransferredEventResponse> ownershipTransferredEventFlowable(
            DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(OWNERSHIPTRANSFERRED_EVENT));
        return ownershipTransferredEventFlowable(filter);
    }

    public static List<TradeRecordedEventResponse> getTradeRecordedEvents(
            TransactionReceipt transactionReceipt) {
        List<Contract.EventValuesWithLog> valueList = staticExtractEventParametersWithLog(TRADERECORDED_EVENT, transactionReceipt);
        ArrayList<TradeRecordedEventResponse> responses = new ArrayList<TradeRecordedEventResponse>(valueList.size());
        for (Contract.EventValuesWithLog eventValues : valueList) {
            TradeRecordedEventResponse typedResponse = new TradeRecordedEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.tradeId = (BigInteger) eventValues.getIndexedValues().get(0).getValue();
            typedResponse.seller = (String) eventValues.getIndexedValues().get(1).getValue();
            typedResponse.buyer = (String) eventValues.getIndexedValues().get(2).getValue();
            typedResponse.quantity = (BigInteger) eventValues.getNonIndexedValues().get(0).getValue();
            typedResponse.price = (BigInteger) eventValues.getNonIndexedValues().get(1).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public static TradeRecordedEventResponse getTradeRecordedEventFromLog(Log log) {
        Contract.EventValuesWithLog eventValues = staticExtractEventParametersWithLog(TRADERECORDED_EVENT, log);
        TradeRecordedEventResponse typedResponse = new TradeRecordedEventResponse();
        typedResponse.log = log;
        typedResponse.tradeId = (BigInteger) eventValues.getIndexedValues().get(0).getValue();
        typedResponse.seller = (String) eventValues.getIndexedValues().get(1).getValue();
        typedResponse.buyer = (String) eventValues.getIndexedValues().get(2).getValue();
        typedResponse.quantity = (BigInteger) eventValues.getNonIndexedValues().get(0).getValue();
        typedResponse.price = (BigInteger) eventValues.getNonIndexedValues().get(1).getValue();
        return typedResponse;
    }

    public Flowable<TradeRecordedEventResponse> tradeRecordedEventFlowable(EthFilter filter) {
        return web3j.ethLogFlowable(filter).map(log -> getTradeRecordedEventFromLog(log));
    }

    public Flowable<TradeRecordedEventResponse> tradeRecordedEventFlowable(
            DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(TRADERECORDED_EVENT));
        return tradeRecordedEventFlowable(filter);
    }

    public static List<TransferEventResponse> getTransferEvents(
            TransactionReceipt transactionReceipt) {
        List<Contract.EventValuesWithLog> valueList = staticExtractEventParametersWithLog(TRANSFER_EVENT, transactionReceipt);
        ArrayList<TransferEventResponse> responses = new ArrayList<TransferEventResponse>(valueList.size());
        for (Contract.EventValuesWithLog eventValues : valueList) {
            TransferEventResponse typedResponse = new TransferEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.from = (String) eventValues.getIndexedValues().get(0).getValue();
            typedResponse.to = (String) eventValues.getIndexedValues().get(1).getValue();
            typedResponse.value = (BigInteger) eventValues.getNonIndexedValues().get(0).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public static TransferEventResponse getTransferEventFromLog(Log log) {
        Contract.EventValuesWithLog eventValues = staticExtractEventParametersWithLog(TRANSFER_EVENT, log);
        TransferEventResponse typedResponse = new TransferEventResponse();
        typedResponse.log = log;
        typedResponse.from = (String) eventValues.getIndexedValues().get(0).getValue();
        typedResponse.to = (String) eventValues.getIndexedValues().get(1).getValue();
        typedResponse.value = (BigInteger) eventValues.getNonIndexedValues().get(0).getValue();
        return typedResponse;
    }

    public Flowable<TransferEventResponse> transferEventFlowable(EthFilter filter) {
        return web3j.ethLogFlowable(filter).map(log -> getTransferEventFromLog(log));
    }

    public Flowable<TransferEventResponse> transferEventFlowable(DefaultBlockParameter startBlock,
            DefaultBlockParameter endBlock) {
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(TRANSFER_EVENT));
        return transferEventFlowable(filter);
    }

    public RemoteFunctionCall<BigInteger> allowance(String owner, String spender) {
        final Function function = new Function(FUNC_ALLOWANCE, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(160, owner), 
                new org.web3j.abi.datatypes.Address(160, spender)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteFunctionCall<TransactionReceipt> approve(String spender, BigInteger value) {
        final Function function = new Function(
                FUNC_APPROVE, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(160, spender), 
                new org.web3j.abi.datatypes.generated.Uint256(value)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<BigInteger> balanceOf(String account) {
        final Function function = new Function(FUNC_BALANCEOF, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(160, account)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteFunctionCall<BigInteger> decimals() {
        final Function function = new Function(FUNC_DECIMALS, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint8>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteFunctionCall<String> name() {
        final Function function = new Function(FUNC_NAME, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Utf8String>() {}));
        return executeRemoteCallSingleValueReturn(function, String.class);
    }

    public RemoteFunctionCall<String> owner() {
        final Function function = new Function(FUNC_OWNER, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}));
        return executeRemoteCallSingleValueReturn(function, String.class);
    }

    public RemoteFunctionCall<TransactionReceipt> recordTrade(BigInteger tradeId, String seller,
            String buyer, BigInteger quantity, BigInteger price) {
        final Function function = new Function(
                FUNC_RECORDTRADE, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(tradeId), 
                new org.web3j.abi.datatypes.Address(160, seller), 
                new org.web3j.abi.datatypes.Address(160, buyer), 
                new org.web3j.abi.datatypes.generated.Uint256(quantity), 
                new org.web3j.abi.datatypes.generated.Uint256(price)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<TransactionReceipt> renounceOwnership() {
        final Function function = new Function(
                FUNC_RENOUNCEOWNERSHIP, 
                Arrays.<Type>asList(), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<String> symbol() {
        final Function function = new Function(FUNC_SYMBOL, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Utf8String>() {}));
        return executeRemoteCallSingleValueReturn(function, String.class);
    }

    public RemoteFunctionCall<BigInteger> totalSupply() {
        final Function function = new Function(FUNC_TOTALSUPPLY, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteFunctionCall<TransactionReceipt> transfer(String to, BigInteger value) {
        final Function function = new Function(
                FUNC_TRANSFER, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(160, to), 
                new org.web3j.abi.datatypes.generated.Uint256(value)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<TransactionReceipt> transferFrom(String from, String to,
            BigInteger value) {
        final Function function = new Function(
                FUNC_TRANSFERFROM, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(160, from), 
                new org.web3j.abi.datatypes.Address(160, to), 
                new org.web3j.abi.datatypes.generated.Uint256(value)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<TransactionReceipt> transferOwnership(String newOwner) {
        final Function function = new Function(
                FUNC_TRANSFEROWNERSHIP, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(160, newOwner)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    @Deprecated
    public static StoToken load(String contractAddress, Web3j web3j, Credentials credentials,
            BigInteger gasPrice, BigInteger gasLimit) {
        return new StoToken(contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    @Deprecated
    public static StoToken load(String contractAddress, Web3j web3j,
            TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return new StoToken(contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    public static StoToken load(String contractAddress, Web3j web3j, Credentials credentials,
            ContractGasProvider contractGasProvider) {
        return new StoToken(contractAddress, web3j, credentials, contractGasProvider);
    }

    public static StoToken load(String contractAddress, Web3j web3j,
            TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        return new StoToken(contractAddress, web3j, transactionManager, contractGasProvider);
    }

    public static RemoteCall<StoToken> deploy(Web3j web3j, Credentials credentials,
            ContractGasProvider contractGasProvider, String name_, String symbol_,
            BigInteger initialSupply_, BigInteger decimals_, String treasury_) {
        String encodedConstructor = FunctionEncoder.encodeConstructor(Arrays.<Type>asList(new org.web3j.abi.datatypes.Utf8String(name_), 
                new org.web3j.abi.datatypes.Utf8String(symbol_), 
                new org.web3j.abi.datatypes.generated.Uint256(initialSupply_), 
                new org.web3j.abi.datatypes.generated.Uint8(decimals_), 
                new org.web3j.abi.datatypes.Address(160, treasury_)));
        return deployRemoteCall(StoToken.class, web3j, credentials, contractGasProvider, getDeploymentBinary(), encodedConstructor);
    }

    public static RemoteCall<StoToken> deploy(Web3j web3j, TransactionManager transactionManager,
            ContractGasProvider contractGasProvider, String name_, String symbol_,
            BigInteger initialSupply_, BigInteger decimals_, String treasury_) {
        String encodedConstructor = FunctionEncoder.encodeConstructor(Arrays.<Type>asList(new org.web3j.abi.datatypes.Utf8String(name_), 
                new org.web3j.abi.datatypes.Utf8String(symbol_), 
                new org.web3j.abi.datatypes.generated.Uint256(initialSupply_), 
                new org.web3j.abi.datatypes.generated.Uint8(decimals_), 
                new org.web3j.abi.datatypes.Address(160, treasury_)));
        return deployRemoteCall(StoToken.class, web3j, transactionManager, contractGasProvider, getDeploymentBinary(), encodedConstructor);
    }

    @Deprecated
    public static RemoteCall<StoToken> deploy(Web3j web3j, Credentials credentials,
            BigInteger gasPrice, BigInteger gasLimit, String name_, String symbol_,
            BigInteger initialSupply_, BigInteger decimals_, String treasury_) {
        String encodedConstructor = FunctionEncoder.encodeConstructor(Arrays.<Type>asList(new org.web3j.abi.datatypes.Utf8String(name_), 
                new org.web3j.abi.datatypes.Utf8String(symbol_), 
                new org.web3j.abi.datatypes.generated.Uint256(initialSupply_), 
                new org.web3j.abi.datatypes.generated.Uint8(decimals_), 
                new org.web3j.abi.datatypes.Address(160, treasury_)));
        return deployRemoteCall(StoToken.class, web3j, credentials, gasPrice, gasLimit, getDeploymentBinary(), encodedConstructor);
    }

    @Deprecated
    public static RemoteCall<StoToken> deploy(Web3j web3j, TransactionManager transactionManager,
            BigInteger gasPrice, BigInteger gasLimit, String name_, String symbol_,
            BigInteger initialSupply_, BigInteger decimals_, String treasury_) {
        String encodedConstructor = FunctionEncoder.encodeConstructor(Arrays.<Type>asList(new org.web3j.abi.datatypes.Utf8String(name_), 
                new org.web3j.abi.datatypes.Utf8String(symbol_), 
                new org.web3j.abi.datatypes.generated.Uint256(initialSupply_), 
                new org.web3j.abi.datatypes.generated.Uint8(decimals_), 
                new org.web3j.abi.datatypes.Address(160, treasury_)));
        return deployRemoteCall(StoToken.class, web3j, transactionManager, gasPrice, gasLimit, getDeploymentBinary(), encodedConstructor);
    }

    public static void linkLibraries(List<Contract.LinkReference> references) {
        librariesLinkedBinary = linkBinaryWithReferences(BINARY, references);
    }

    private static String getDeploymentBinary() {
        if (librariesLinkedBinary != null) {
            return librariesLinkedBinary;
        } else {
            return BINARY;
        }
    }

    public static class ApprovalEventResponse extends BaseEventResponse {
        public String owner;

        public String spender;

        public BigInteger value;
    }

    public static class OwnershipTransferredEventResponse extends BaseEventResponse {
        public String previousOwner;

        public String newOwner;
    }

    public static class TradeRecordedEventResponse extends BaseEventResponse {
        public BigInteger tradeId;

        public String seller;

        public String buyer;

        public BigInteger quantity;

        public BigInteger price;
    }

    public static class TransferEventResponse extends BaseEventResponse {
        public String from;

        public String to;

        public BigInteger value;
    }
}
