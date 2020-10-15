/*
 * Copyright 2020 The caver-java-ext-kas Authors
 *
 * Licensed under the Apache License, Version 2.0 (the “License”);
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an “AS IS” BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package xyz.groundx.caver_ext_kas.kas.wallet;

import com.klaytn.caver.account.AccountKeyWeightedMultiSig;
import com.squareup.okhttp.Call;
import xyz.groundx.caver_ext_kas.kas.utils.KASUtils;
import xyz.groundx.caver_ext_kas.kas.wallet.accountkey.KeyTypeMultiSig;
import xyz.groundx.caver_ext_kas.kas.wallet.accountkey.KeyTypePublic;
import xyz.groundx.caver_ext_kas.kas.wallet.accountkey.KeyTypeRoleBased;
import xyz.groundx.caver_ext_kas.rest_client.io.swagger.client.ApiCallback;
import xyz.groundx.caver_ext_kas.rest_client.io.swagger.client.ApiClient;
import xyz.groundx.caver_ext_kas.rest_client.io.swagger.client.ApiException;
import xyz.groundx.caver_ext_kas.rest_client.io.swagger.client.api.wallet.api.*;
import xyz.groundx.caver_ext_kas.rest_client.io.swagger.client.api.wallet.model.*;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Representing an wrapping class tha connects Wallet API.
 */
public class Wallet {

    /**
     * Account API rest client object.
     */
    AccountApi accountApi;

    /**
     * Basic transaction API rest client object.
     */
    BasicTransactionApi basicTransactionApi;

    /**
     * Fee delegated transaction(fee paid by KAS) API rest client object.
     */
    FeeDelegatedTransactionPaidByKasApi feeDelegatedTransactionPaidByKasApi;

    /**
     * Fee delegated transaction(fee paid by user) API rest client object.
     */
    FeeDelegatedTransactionPaidByUserApi feeDelegatedTransactionPaidByUserApi;

    /**
     * Multiple signature transaction management API rest client object.
     */
    MultisigTransactionManagementApi multisigTransactionManagementApi;

    /**
     * Statistics API rest client object.
     */
    StatisticsApi statisticsApi;

    /**
     * Klaytn network id.
     */
    String chainId;

    /**
     * Creates an WalletAPI instnace.
     * @param chainId A Klaytn network chain id.
     * @param walletApiClient The Api client for connection with KAS.
     */
    public Wallet(String chainId, ApiClient walletApiClient) {
        setChainId(chainId);
        setAccountApi(new AccountApi(walletApiClient));
        setBasicTransactionApi(new BasicTransactionApi(walletApiClient));
        setFeeDelegatedTransactionPaidByKasApi(new FeeDelegatedTransactionPaidByKasApi(walletApiClient));
        setFeeDelegatedTransactionPaidByUserApi(new FeeDelegatedTransactionPaidByUserApi(walletApiClient));
        setMultisigTransactionManagementApi(new MultisigTransactionManagementApi(walletApiClient));
        setStatisticsApi(new StatisticsApi(walletApiClient));
    }

    /**
     * Creates a Klaytn account.
     * It generates Klaytn address and private key, public key pair.
     * POST /v2/account
     * @return Account
     * @throws ApiException
     */
    public Account createAccount() throws ApiException {
        return getAccountApi().createAccount(chainId);
    }

    /**
     * Creates a Klaytn account asynchronously.
     * It generates Klaytn address and private key, public key pair.
     * POST /v2/account
     * @param callback The callback function to handle response.
     * @return Call
     * @throws ApiException
     */
    public Call createAccountAsync(ApiCallback<Account> callback) throws ApiException {
        return getAccountApi().createAccountAsync(chainId, callback);
    }

    /**
     * Get the list of accounts created previously.
     * It will send a request without filter options.
     * GET /v2/account
     * @return Accounts
     * @throws ApiException
     */
    public Accounts getAccountList() throws ApiException {
        return getAccountList(new WalletQueryOptions());
    }

    /**
     * Get the list of accounts created previously asynchronously.
     * It will send a request without filter options.
     * GET /v2/account
     * @param callback The callback function to handle response.
     * @return Call
     * @throws ApiException
     */
    public Call getAccountListAsync(ApiCallback<Accounts> callback) throws ApiException {
        return getAccountListAsync(new WalletQueryOptions(), callback);
    }

    /**
     * Get the list of accounts created previously.
     * GET /v2/account
     * @param options Filters required when retrieving data. `to-timestamp`, `from-timestamp`, `size`, and `cursor`.
     * @return Accounts
     * @throws ApiException
     */
    public Accounts getAccountList(WalletQueryOptions options) throws ApiException {
        return getAccountApi().retrieveAccounts(chainId, options.getSize(), options.getCursor(), options.getToTimestamp(), options.getFromTimestamp());
    }

    /**
     * Get the list of accounts created previously asynchronously.
     * GET /v2/account
     * @param options Filters required when retrieving data. `to-timestamp`, `from-timestamp`, `size`, and `cursor`.
     * @param callback The callback function to handle response.
     * @return Call
     * @throws ApiException
     */
    public Call getAccountListAsync(WalletQueryOptions options, ApiCallback<Accounts> callback) throws ApiException {
        return getAccountApi().retrieveAccountsAsync(chainId, options.getSize(), options.getCursor(), options.getToTimestamp(), options.getFromTimestamp(), callback);
    }

    /**
     * Get the account information passed as a parameter.
     * GET /v2/account/{address}
     * @param address The address to get account information.
     * @return Account
     * @throws ApiException
     */
    public Account getAccount(String address) throws ApiException {
        return getAccountApi().retrieveAccount(chainId, address);
    }

    /**
     * Get the account information passed as a parameter asynchronously.
     * GET /v2/account/{address}
     * @param address The address to get account information.
     * @param callback The callback function to handle response.
     * @return Call
     * @throws ApiException
     */
    public Call getAccountAsync(String address, ApiCallback<Account> callback) throws ApiException {
        return getAccountApi().retrieveAccountAsync(chainId, address, callback);
    }

    /**
     * Delete an account.
     * DELETE /v2/account/{address}
     * @param address The address to delete.
     * @return AccountStatus
     * @throws ApiException
     */
    public AccountStatus deleteAccount(String address) throws ApiException {
        return getAccountApi().deleteAccount(chainId, address);
    }

    /**
     * Delete an account asynchronously.
     * DELETE /v2/account/{address}
     * @param address The address to delete.
     * @param callback The callback function to handle response.
     * @return Call
     * @throws ApiException
     */
    public Call deleteAccountAsync(String address, ApiCallback<AccountStatus> callback) throws ApiException {
        return getAccountApi().deleteAccountAsync(chainId, address, callback);
    }

    /**
     * Disable an account.
     * The disabled account will not be retrieved account information.
     * PUT /v2/account/{address}/disable
     * @param address The address to disable
     * @return AccountStatus
     * @throws ApiException
     */
    public AccountSummary disableAccount(String address) throws ApiException {
        return getAccountApi().deactivateAccount(chainId, address);
    }

    /**
     * Disable an account asynchronously.
     * The disabled account will not be retrieved account information.
     * PUT /v2/account/{address}/disable
     * @param address The address to disable
     * @param callback The callback function to handle response.
     * @return Call
     * @throws ApiException
     */
    public Call disableAccountAsync(String address, ApiCallback<AccountSummary> callback) throws ApiException {
        return getAccountApi().deactivateAccountAsync(chainId, address, callback);
    }

    /**
     * Enable an account.
     * PUT /v2/account/{address}/enable
     * @param address The address to enable.
     * @return AccountStatus
     * @throws ApiException
     */
    public AccountSummary enableAccount(String address) throws ApiException {
        return getAccountApi().activateAccount(chainId, address);
    }

    /**
     * Enable an account asynchronously.
     * PUT /v2/account/{address}/enable
     * @param address The address to enable.
     * @param callback The callback function to handle response.
     * @return Call
     * @throws ApiException
     */
    public Call enableAccountAsync(String address, ApiCallback<AccountSummary> callback) throws ApiException {
        return getAccountApi().activateAccountAsync(chainId, address, callback);
    }

    /**
     * Signs the transaction corresponding to the passed TransactionID with the key of the passed address.
     * POST /v2/account/{address}/tx/{transaction-id}/sign
     * @param address The address to sign transaction.
     * @param transactionId The transaction id to get transaction for sign.
     * @return Signature
     * @throws ApiException
     */
    public Signature signTransaction(String address, String transactionId) throws ApiException {
        return getAccountApi().signTransactionIDResponse(chainId, address, transactionId);
    }

    /**
     * Signs the transaction corresponding to the passed TransactionID with the key of the passed address asynchronously.
     * POST /v2/account/{address}/tx/{transaction-id}/sign
     * @param address The address to sign transaction.
     * @param transactionId The transaction id to get transaction for sign.
     * @param callback The callback function to handle response.
     * @return Call
     * @throws ApiException
     */
    public Call signTransactionAsync(String address, String transactionId, ApiCallback<Signature> callback) throws ApiException {
        return getAccountApi().signTransactionIDResponseAsync(chainId, address, transactionId, callback);
    }

    /**
     * Updates an account to MultiSig Account.
     * PUT /v2/account/{address}/multisig
     * @param address The address to update multisig account.
     * @param request The MultisigAccountUpdateRequest instance required for updating account.
     * @return MultisigAccount
     * @throws ApiException
     */
    public MultisigAccount updateToMultiSigAccount(String address, MultisigAccountUpdateRequest request) throws ApiException {
        return getAccountApi().multisigAccountUpdate(chainId, address, request);
    }

    /**
     * Updates an account to MultiSig Account asynchronously.
     * PUT /v2/account/{address}/multisig
     * @param address The address to update multisig account.
     * @param request The MultisigAccountUpdateRequest instance required for updating account.
     * @param callback The callback function to handle response.
     * @return Call
     * @throws ApiException
     */
    public Call updateToMultiSigAccountAsync(String address, MultisigAccountUpdateRequest request, ApiCallback<MultisigAccount> callback) throws ApiException {
        return getAccountApi().multisigAccountUpdateAsync(chainId, address, request, callback);
    }

    /**
     * Get list of accounts that has a public key passed as a parameter.
     * GET /v2/pubkey/{public-key}/account
     * @param publicKey A public key to get list of accounts.
     * @return AccountsByPubkey
     * @throws ApiException
     */
    public AccountsByPubkey getAccountListByPublicKey(String publicKey) throws ApiException {
        return getAccountApi().retrieveAccountsByPubkey(chainId ,publicKey);
    }

    /**
     * Get list of accounts that has a public key passed as a parameter asynchronously.
     * GET /v2/pubkey/{public-key}/account
     * @param publicKey A public key to get list of accounts.
     * @param callback The callback function to handle response.
     * @return Call
     * @throws ApiException
     */
    public Call getAccountListByPublicKeyAsync(String publicKey, ApiCallback<AccountsByPubkey> callback) throws ApiException {
        return getAccountApi().retrieveAccountsByPubkeyAsync(chainId ,publicKey, callback);
    }

    /**
     * Send a Legacy transaction.
     * POST /v2/tx/legacy
     * @param request The LegacyTransactionRequest instance to send a transaction.
     * @return TransactionResult
     * @throws ApiException
     */
    public TransactionResult requestLegacyTransaction(LegacyTransactionRequest request) throws ApiException {
        return getBasicTransactionApi().legacyTransaction(chainId, request);
    }

    /**
     * Send a Legacy transaction asynchronously.
     * POST /v2/tx/legacy
     * @param request The LegacyTransactionRequest instance to send a transaction.
     * @param callback The callback function to handle response.
     * @return Call
     * @throws ApiException
     */
    public Call requestLegacyTransactionAsync(LegacyTransactionRequest request, ApiCallback<TransactionResult> callback) throws ApiException {
        return getBasicTransactionApi().legacyTransactionAsync(chainId, request, callback);
    }

    /**
     * Send a ValueTransfer transaction.
     * POST /v2/tx/value
     * @param request The ValueTransferTransactionRequest instance to send a transaction.
     * @return TransactionResult
     * @throws ApiException
     */
    public TransactionResult requestValueTransfer(ValueTransferTransactionRequest request) throws ApiException {
        return getBasicTransactionApi().valueTransferTransaction(chainId, request);
    }

    /**
     * Send a ValueTransfer transaction asynchronously.
     * POST /v2/tx/value
     * @param request The ValueTransferTransactionRequest instance to send a transaction.
     * @param callback THe callback function to handle response.
     * @return Call
     * @throws ApiException
     */
    public Call requestValueTransferAsync(ValueTransferTransactionRequest request, ApiCallback<TransactionResult> callback) throws ApiException {
        return getBasicTransactionApi().valueTransferTransactionAsync(chainId, request, callback);
    }

    /**
     * Send a SmartContractDeploy transaction.
     * POST /v2/tx/contract/deploy
     * @param request The ContractDeployTransactionRequest instance to send a transaction.
     * @return TransactionResult
     * @throws ApiException
     */
    public TransactionResult requestSmartContractDeploy(ContractDeployTransactionRequest request) throws ApiException {
        return getBasicTransactionApi().contractDeployTransaction(chainId, request);
    }

    /**
     * Send a SmartContractDeploy transaction asynchronously.
     * POST /v2/tx/contract/deploy
     * @param request The ContractDeployTransactionRequest instance to send a transaction.
     * @param callback The callback function to handle response.
     * @return Call
     * @throws ApiException
     */
    public Call requestSmartContractDeployAsync(ContractDeployTransactionRequest request, ApiCallback<TransactionResult> callback) throws ApiException {
        return getBasicTransactionApi().contractDeployTransactionAsync(chainId, request, callback);
    }

    /**
     * Send a SmartContractExecution transaction.
     * POST /v2/tx/contract/execute
     * @param request The ContractExecutionTransactionRequest instance to send a transaction.
     * @return TransactionResult
     * @throws ApiException
     */
    public TransactionResult requestSmartContractExecution(ContractExecutionTransactionRequest request) throws ApiException {
        return getBasicTransactionApi().contractExecutionTransaction(chainId, request);
    }

    /**
     * Send a SmartContractExecution transaction asynchronously.
     * POST /v2/tx/contract/execute
     * @param request The ContractExecutionTransactionRequest instance to send a transaction.
     * @param callback The callback function to handle response.
     * @return Call
     * @throws ApiException
     */
    public Call requestSmartContractExecutionAsync(ContractExecutionTransactionRequest request, ApiCallback<TransactionResult> callback) throws ApiException {
        return getBasicTransactionApi().contractExecutionTransactionAsync(chainId, request, callback);
    }

    /**
     * Send a Cancel transaction.
     * DELETE /v2/tx
     * @param request The CancelTransactionRequest instance to send a transaction.
     * @return TransactionResult
     * @throws ApiException
     */
    public TransactionResult requestCancel(CancelTransactionRequest request) throws ApiException {
        return getBasicTransactionApi().cancelTransaction(chainId, request);
    }

    /**
     * Send a Cancel transaction asynchronously.
     * DELETE /v2/tx
     * @param request The CancelTransactionRequest instance to send a transaction.
     * @param callback The callback function to handle response.
     * @return Call
     * @throws ApiException
     */
    public Call requestCancelAsync(CancelTransactionRequest request, ApiCallback<TransactionResult> callback) throws ApiException {
        return getBasicTransactionApi().cancelTransactionAsync(chainId, request, callback);
    }

    /**
     * Send a ChainDataAnchoring transaction.
     * POST /v2/tx/anchor
     * @param request The AnchorTransactionRequest instance to send a transaction.
     * @return TransactionResult
     * @throws ApiException
     */
    public TransactionResult requestChainDataAnchoring(AnchorTransactionRequest request) throws ApiException {
        return getBasicTransactionApi().anchorTransaction(chainId, request);
    }

    /**
     * Send a ChainDataAnchoring transaction asynchronously.
     * POST /v2/tx/anchor
     * @param request The AnchorTransactionRequest instance to send a transaction.
     * @param callback The callback function to handle response.
     * @return Call
     * @throws ApiException
     */
    public Call requestChainDataAnchoringAsync(AnchorTransactionRequest request, ApiCallback<TransactionResult> callback) throws ApiException {
        return getBasicTransactionApi().anchorTransactionAsync(chainId, request, callback);
    }

    /**
     * Send a raw transaction.
     * POST /v2/tx/rlp
     * @param request The ProcessRLPRequest instance to send a transaction.
     * @return TransactionResult
     * @throws ApiException
     */
    public TransactionResult requestRawTransaction(ProcessRLPRequest request) throws ApiException {
        return getBasicTransactionApi().processRLP(chainId, request);
    }

    /**
     * Send a raw transaction asynchronously.
     * POST /v2/tx/rlp
     * @param request The ProcessRLPRequest instance to send a transaction.
     * @param callback The callback function to handle response.
     * @return Call
     * @throws ApiException
     */
    public Call requestRawTransactionAsync(ProcessRLPRequest request, ApiCallback<TransactionResult> callback) throws ApiException {
        return getBasicTransactionApi().processRLPAsync(chainId, request, callback);
    }

    /**
     * Send a AccountUpdate transaction.
     * POST /v2/tx/account
     * @param request The AccountUpdateTransactionRequest instance to send a transaction.
     * @return TransactionResult
     * @throws ApiException
     */
    public TransactionResult requestAccountUpdate(AccountUpdateTransactionRequest request) throws ApiException {
        request.setAccountKey(makeUncompressedKeyFormat(request.getAccountKey()));
        return getBasicTransactionApi().accountUpdateTransaction(chainId, request);
    }

    /**
     * Send a AccountUpdate transaction asynchronously.
     * POST /v2/tx/account
     * @param request The AccountUpdateTransactionRequest instance to send a transaction.
     * @param callback The callback function to handle response.
     * @return Call
     * @throws ApiException
     */
    public Call requestAccountUpdateAsync(AccountUpdateTransactionRequest request, ApiCallback<TransactionResult> callback) throws ApiException {
        request.setAccountKey(makeUncompressedKeyFormat(request.getAccountKey()));
        return getBasicTransactionApi().accountUpdateTransactionAsync(chainId, request, callback);
    }

    /**
     * Get a transaction receipt.
     * @param transactionHash A transaction hash to get a transaction receipt.
     * @return TransactionReceipt
     * @throws ApiException
     */
    public TransactionReceipt getTransaction(String transactionHash) throws ApiException {
        return getBasicTransactionApi().transactionReceipt(chainId, transactionHash);
    }

    /**
     * Get a transaction receipt asynchronously.
     * @param transactionHash A transaction hash to get a transaction receipt.
     * @param callback The callback function to handle response.
     * @return Call
     * @throws ApiException
     */
    public Call getTransactionAsync(String transactionHash, ApiCallback<TransactionReceipt> callback) throws ApiException {
        return getBasicTransactionApi().transactionReceiptAsync(chainId, transactionHash, callback);
    }

    /**
     * Send a FeeDelegatedValueTransfer(WithRatio) transaction.
     * KAS pays the fee for this transaction.
     * If you want to send withRatio Transaction, you can set feeRatio field in request.
     * @param request The FDValueTransferTransactionRequest instance to send a transaction.
     * @return FDTransactionResult
     * @throws ApiException
     */
    public FDTransactionResult requestFDValueTransferPaidByGlobalFeePayer(FDValueTransferTransactionRequest request) throws ApiException {
        return getFeeDelegatedTransactionPaidByKasApi().fDValueTransferTransaction(chainId, request);
    }

    /**
     * Send a FeeDelegatedValueTransfer(WithRatio) transaction asynchronously.
     * KAS pays the fee for this transaction.
     * If you want to send withRatio Transaction, you can set feeRatio field in request.
     * @param request The FDValueTransferTransactionRequest instance to send a transaction.
     * @param callback The callback function to handle response.
     * @return Call
     * @throws ApiException
     */
    public Call requestFDValueTransferPaidByGlobalFeePayerAsync(FDValueTransferTransactionRequest request, ApiCallback<FDTransactionResult> callback) throws ApiException {
        return getFeeDelegatedTransactionPaidByKasApi().fDValueTransferTransactionAsync(chainId, request, callback);
    }

    /**
     * Send a FeeDelegatedSmartContractDeploy(WithRatio) transaction.
     * KAS pays the fee for this transaction.
     * If you want to send withRatio Transaction, you can set feeRatio field in request.
     * @param request The FDContractDeployTransactionRequest instance to send a transaction.
     * @return FDTransactionResult
     * @throws ApiException
     */
    public FDTransactionResult requestFDSmartContractDeployPaidByGlobalFeePayer(FDContractDeployTransactionRequest request) throws ApiException {
        return getFeeDelegatedTransactionPaidByKasApi().fDContractDeployTransaction(chainId, request);
    }

    /**
     * Send a FeeDelegatedSmartContractDeploy(WithRatio) transaction asynchronously.
     * KAS pays the fee for this transaction.
     * If you want to send withRatio Transaction, you can set feeRatio field in request.
     * @param request The FDContractDeployTransactionRequest instance to send a transaction.
     * @return Call
     * @throws ApiException
     */
    public Call requestFDSmartContractDeployPaidByGlobalFeePayerAsync(FDContractDeployTransactionRequest request, ApiCallback<FDTransactionResult> callback) throws ApiException {
        return getFeeDelegatedTransactionPaidByKasApi().fDContractDeployTransactionAsync(chainId, request, callback);
    }

    /**
     * Send a FeeDelegatedSmartContractExecution(WithRatio) transaction.
     * KAS pays the fee for this transaction.
     * If you want to send withRatio Transaction, you can set feeRatio field in request.
     * @param request The FDContractExecutionTransactionRequest instance to send a transaction.
     * @return FDTransactionResult
     * @throws ApiException
     */
    public FDTransactionResult requestFDSmartContractExecutionPaidByGlobalFeePayer(FDContractExecutionTransactionRequest request) throws ApiException {
        return getFeeDelegatedTransactionPaidByKasApi().fDContractExecutionTransaction(chainId, request);
    }

    /**
     * Send a FeeDelegatedSmartContractExecution(WithRatio) transaction asynchronously.
     * KAS pays the fee for this transaction.
     * If you want to send withRatio Transaction, you can set feeRatio field in request.
     * @param request The FDContractExecutionTransactionRequest instance to send a transaction.
     * @return Call
     * @throws ApiException
     */
    public Call requestFDSmartContractExecutionPaidByGlobalFeePayerAsync(FDContractExecutionTransactionRequest request, ApiCallback<FDTransactionResult> callback) throws ApiException {
        return getFeeDelegatedTransactionPaidByKasApi().fDContractExecutionTransactionAsync(chainId, request, callback);
    }

    /**
     * Send a FeeDelegatedCancelTransaction(WithRatio) transaction.
     * KAS pays the fee for this transaction.
     * If you want to send withRatio Transaction, you can set feeRatio field in request.
     * @param request The FDCancelTransactionRequest instance to send a transaction.
     * @return FDTransactionResult
     * @throws ApiException
     */
    public FDTransactionResult requestFDCancelPaidByGlobalFeePayer(FDCancelTransactionRequest request) throws ApiException {
        return getFeeDelegatedTransactionPaidByKasApi().fDCancelTransactionResponse(chainId, request);
    }

    /**
     * Send a FeeDelegatedCancelTransaction(WithRatio) transaction asynchronously.
     * KAS pays the fee for this transaction.
     * If you want to send withRatio Transaction, you can set feeRatio field in request.
     * @param request The FDCancelTransactionRequest instance to send a transaction.
     * @param callback The callback function to handle response.
     * @return Call
     * @throws ApiException
     */
    public Call requestFDCancelPaidByGlobalFeePayerAsync(FDCancelTransactionRequest request, ApiCallback<FDTransactionResult> callback) throws ApiException {
        return getFeeDelegatedTransactionPaidByKasApi().fDCancelTransactionResponseAsync(chainId, request, callback);
    }

    /**
     * Send a FeeDelegatedChainDataAnchoring(WithRatio) transaction.
     * KAS pays the fee for this transaction.
     * If you want to send withRatio Transaction, you can set feeRatio field in request.
     * @param request The FDAnchorTransactionRequest instance to send a transaction.
     * @return FDTransactionResult
     * @throws ApiException
     */
    public FDTransactionResult requestFDChainDataAnchoringPaidByGlobalFeePayer(FDAnchorTransactionRequest request) throws ApiException {
        return getFeeDelegatedTransactionPaidByKasApi().fDAnchorTransaction(chainId, request);
    }

    /**
     * Send a FeeDelegatedChainDataAnchoring(WithRatio) transaction asynchronously.
     * KAS pays the fee for this transaction.
     * If you want to send withRatio Transaction, you can set feeRatio field in request.
     * @param request The FDAnchorTransactionRequest instance to send a transaction.
     * @param callback The callback function to handle response.
     * @return Call
     * @throws ApiException
     */
    public Call requestFDChainDataAnchoringPaidByGlobalFeePayerAsync(FDAnchorTransactionRequest request, ApiCallback<FDTransactionResult> callback) throws ApiException {
        return getFeeDelegatedTransactionPaidByKasApi().fDAnchorTransactionAsync(chainId, request, callback);
    }

    /**
     * Send a FeeDelegated(WithRatio) type raw transaction.
     * KAS pays the fee for this transaction.
     * If you want to send withRatio Transaction, you can set feeRatio field in request.
     * @param request The FDProcessRLPRequest instance to send a transaction.
     * @return FDTransactionResult
     * @throws ApiException
     */
    public FDTransactionResult requestFDRawTransactionPaidByGlobalFeePayer(FDProcessRLPRequest request) throws ApiException {
        return getFeeDelegatedTransactionPaidByKasApi().fDProcessRLP(chainId, request);
    }

    /**
     * Send a FeeDelegated(WithRatio) type raw transaction asynchronously.
     * KAS pays the fee for this transaction.
     * If you want to send withRatio Transaction, you can set feeRatio field in request.
     * @param request The FDProcessRLPRequest instance to send a transaction.
     * @param callback The callback function to handle response.
     * @return Call
     * @throws ApiException
     */
    public Call requestFDRawTransactionPaidByGlobalFeePayerAsync(FDProcessRLPRequest request, ApiCallback callback) throws ApiException {
        return getFeeDelegatedTransactionPaidByKasApi().fDProcessRLPAsync(chainId, request, callback);
    }

    /**
     * Send a FeeDelegatedAccountUpdate(WithRatio) transaction.
     * KAS pays the fee for this transaction.
     * If you want to send withRatio Transaction, you can set feeRatio field in request.
     * @param request The FDAccountUpdateTransactionRequest instance to send a transaction.
     * @return FDTransactionResult
     * @throws ApiException
     */
    public FDTransactionResult requestFDAccountUpdatePaidByGlobalFeePayer(FDAccountUpdateTransactionRequest request) throws ApiException {
        request.setAccountKey(makeUncompressedKeyFormat(request.getAccountKey()));
        return getFeeDelegatedTransactionPaidByKasApi().fDAccountUpdateTransactionResponse(chainId, request);
    }

    /**
     * Send a FeeDelegatedAccountUpdate(WithRatio) transaction asynchronously.
     * KAS pays the fee for this transaction.
     * If you want to send withRatio Transaction, you can set feeRatio field in request.
     * @param request The FDAccountUpdateTransactionRequest instance to send a transaction.
     * @param callback The callback function to handle response.
     * @return Call
     * @throws ApiException
     */
    public Call requestFDAccountUpdatePaidByGlobalFeePayerAsync(FDAccountUpdateTransactionRequest request, ApiCallback<FDTransactionResult> callback) throws ApiException {
        request.setAccountKey(makeUncompressedKeyFormat(request.getAccountKey()));
        return getFeeDelegatedTransactionPaidByKasApi().fDAccountUpdateTransactionResponseAsync(chainId, request, callback);
    }

    /**
     * Send a FeeDelegatedValueTransfer(WithRatio) transaction.
     * The feePayer defined by the user pays the fee for this transaction.
     * If you want to send withRatio Transaction, you can set feeRatio field in request.
     * @param request The FDUserValueTransferTransactionRequest instance to send a transaction.
     * @return FDTransactionResult
     * @throws ApiException
     */
    public FDTransactionResult requestFDValueTransferPaidByUser(FDUserValueTransferTransactionRequest request) throws ApiException {
        return getFeeDelegatedTransactionPaidByUserApi().uFDValueTransferTransaction(chainId, request);
    }

    /**
     * Send a FeeDelegatedValueTransfer(WithRatio) transaction asynchronously.
     * The feePayer defined by the user pays the fee for this transaction.
     * If you want to send withRatio Transaction, you can set feeRatio field in request.
     * @param request The FDUserValueTransferTransactionRequest instance to send a transaction.
     * @param callback The callback function to handle response.
     * @return Call
     * @throws ApiException
     */
    public Call requestFDValueTransferPaidByUserAsync(FDUserValueTransferTransactionRequest request, ApiCallback<FDTransactionResult> callback) throws ApiException {
        return getFeeDelegatedTransactionPaidByUserApi().uFDValueTransferTransactionAsync(chainId, request, callback);
    }

    /**
     * Send a FeeDelegatedSmartContractDeploy(WithRatio) transaction.
     * The feePayer defined by the user pays the fee for this transaction.
     * If you want to send withRatio Transaction, you can set feeRatio field in request.
     * @param request The FDUserContractDeployTransactionRequest instance to send a request.
     * @return FDTransactionResult
     * @throws ApiException
     */
    public FDTransactionResult requestFDSmartContractDeployPaidByUser(FDUserContractDeployTransactionRequest request) throws ApiException {
        return getFeeDelegatedTransactionPaidByUserApi().uFDContractDeployTransaction(chainId, request);
    }

    /**
     * Send a FeeDelegatedSmartContractDeploy(WithRatio) transaction asynchronously.
     * The feePayer defined by the user pays the fee for this transaction.
     * If you want to send withRatio Transaction, you can set feeRatio field in request.
     * @param request The FDUserContractDeployTransactionRequest instance to send a request.
     * @param callback The callback function to handle response.
     * @return Call
     * @throws ApiException
     */
    public Call requestFDSmartContractDeployPaidByUserAsync(FDUserContractDeployTransactionRequest request, ApiCallback<FDTransactionResult> callback) throws ApiException {
        return getFeeDelegatedTransactionPaidByUserApi().uFDContractDeployTransactionAsync(chainId, request, callback);
    }

    /**
     * Send a FeeDelegatedSmartContractExecution(WithRatio) transaction.
     * The feePayer defined by the user pays the fee for this transaction.
     * If you want to send withRatio Transaction, you can set feeRatio field in request.
     * @param request The FDUserContractExecutionTransactionRequest instance to send a request.
     * @return FDTransactionResult
     * @throws ApiException
     */
    public FDTransactionResult requestFDSmartContractExecutionPaidByUser(FDUserContractExecutionTransactionRequest request) throws ApiException {
        return getFeeDelegatedTransactionPaidByUserApi().uFDContractExecutionTransaction(chainId, request);
    }

    /**
     * Send a FeeDelegatedSmartContractExecution(WithRatio) transaction asynchronously.
     * The feePayer defined by the user pays the fee for this transaction.
     * If you want to send withRatio Transaction, you can set feeRatio field in request.
     * @param request The FDUserContractExecutionTransactionRequest instance to send a request.
     * @param callback The callback function to handle response.
     * @return Call
     * @throws ApiException
     */
    public Call requestFDSmartContractExecutionPaidByUserAsync(FDUserContractExecutionTransactionRequest request, ApiCallback<FDTransactionResult> callback) throws ApiException {
        return getFeeDelegatedTransactionPaidByUserApi().uFDContractExecutionTransactionAsync(chainId, request, callback);
    }

    /**
     * Send a FeeDelegatedCancel(WithRatio) transaction.
     * The feePayer defined by the user pays the fee for this transaction.
     * If you want to send withRatio Transaction, you can set feeRatio field in request.
     * @param request The FDUserCancelTransactionRequest instance to send a request.
     * @return FDTransactionResult
     * @throws ApiException
     */
    public FDTransactionResult requestFDCancelPaidByUser(FDUserCancelTransactionRequest request) throws ApiException {
        return getFeeDelegatedTransactionPaidByUserApi().uFDUserCancelTransaction(chainId, request);
    }

    /**
     * Send a FeeDelegatedCancel(WithRatio) transaction asynchronously.
     * The feePayer defined by the user pays the fee for this transaction.
     * If you want to send withRatio Transaction, you can set feeRatio field in request.
     * @param request The FDUserCancelTransactionRequest instance to send a request.
     * @param callback The callback function to handle response
     * @return Call
     * @throws ApiException
     */
    public Call requestFDCancelPaidByUserAsync(FDUserCancelTransactionRequest request, ApiCallback callback) throws ApiException {
        return getFeeDelegatedTransactionPaidByUserApi().uFDUserCancelTransactionAsync(chainId, request, callback);
    }

    /**
     * Send a FeeDelegatedChainDataAnchoring(WithRatio) transaction.
     * The feePayer defined by the user pays the fee for this transaction.
     * If you want to send withRatio Transaction, you can set feeRatio field in request.
     * @param request The FDUserAnchorTransactionRequest instance to send a request.
     * @return FDTransactionResult
     * @throws ApiException
     */
    public FDTransactionResult requestFDChainDataAnchoringPaidByUser(FDUserAnchorTransactionRequest request) throws ApiException {
        return getFeeDelegatedTransactionPaidByUserApi().uFDAnchorTransaction(chainId, request);
    }

    /**
     * Send a FeeDelegatedChainDataAnchoring(WithRatio) transaction asynchronously.
     * The feePayer defined by the user pays the fee for this transaction.
     * If you want to send withRatio Transaction, you can set feeRatio field in request.
     * @param request The FDUserAnchorTransactionRequest instance to send a request.
     * @param callback The callback function to handle response.
     * @return Call
     * @throws ApiException
     */
    public Call requestFDChainDataAnchoringPaidByUserAsync(FDUserAnchorTransactionRequest request, ApiCallback<FDTransactionResult> callback) throws ApiException {
        return getFeeDelegatedTransactionPaidByUserApi().uFDAnchorTransactionAsync(chainId, request, callback);
    }

    /**
     * Send a FeeDelegated(WithRatio) raw transaction.
     * The feePayer defined by the user pays the fee for this transaction.
     * If you want to send withRatio Transaction, you can set feeRatio field in request.
     * @param request The FDUserProcessRLPRequest instance to send a request.
     * @return FDTransactionResult
     * @throws ApiException
     */
    public FDTransactionResult requestFDRawTransactionPaidByUser(FDUserProcessRLPRequest request) throws ApiException {
        return getFeeDelegatedTransactionPaidByUserApi().uFDProcessRLP(chainId, request);
    }

    /**
     * Send a FeeDelegated(WithRatio) raw transaction asynchronously.
     * The feePayer defined by the user pays the fee for this transaction.
     * If you want to send withRatio Transaction, you can set feeRatio field in request.
     * @param request The FDUserProcessRLPRequest instance to send a request.
     * @param callback The callback function to handle response.
     * @return Call
     * @throws ApiException
     */
    public Call requestFDRawTransactionPaidByUserAsync(FDUserProcessRLPRequest request, ApiCallback<FDTransactionResult> callback) throws ApiException {
        return getFeeDelegatedTransactionPaidByUserApi().uFDProcessRLPAsync(chainId, request, callback);
    }

    /**
     * Send a FeeDelegatedAccountUpdate(WithRatio) transaction.
     * The feePayer defined by the user pays the fee for this transaction.
     * If you want to send withRatio Transaction, you can set feeRatio field in request.
     * @param request The FDUserAccountUpdateTransactionRequest instance to send a request.
     * @return FDTransactionResult
     * @throws ApiException
     */
    public FDTransactionResult requestFDAccountUpdatePaidByUser(FDUserAccountUpdateTransactionRequest request) throws ApiException {
        request.setAccountKey(makeUncompressedKeyFormat(request.getAccountKey()));
        return getFeeDelegatedTransactionPaidByUserApi().uFDAccountUpdateTransaction(chainId, request);
    }

    /**
     * Send a FeeDelegatedAccountUpdate(WithRatio) transaction asynchronously.
     * The feePayer defined by the user pays the fee for this transaction.
     * If you want to send withRatio Transaction, you can set feeRatio field in request.
     * @param request The FDUserAccountUpdateTransactionRequest instance to send a request.
     * @param callback The callback function to handle response.
     * @return Call
     * @throws ApiException
     */
    public Call requestFDAccountUpdatePaidByUserAsync(FDUserAccountUpdateTransactionRequest request, ApiCallback<FDTransactionResult> callback) throws ApiException {
        request.setAccountKey(makeUncompressedKeyFormat(request.getAccountKey()));
        return getFeeDelegatedTransactionPaidByUserApi().uFDAccountUpdateTransactionAsync(chainId, request, callback);
    }

    /**
     * Get pending transaction list.
     * It will send a request without filter options.
     * @param address The sender address to get pending transaction list.
     * @return MultisigTransactions
     * @throws ApiException
     */
    public MultisigTransactions getMultiSigTransactionList(String address) throws ApiException {
        return getMultiSigTransactionList(address, new WalletQueryOptions());
    }

    /**
     * Get pending transaction list asynchronously.
     * It will send a request without filter options.
     * @param address The sender address to get pending transaction list.
     * @param callback The callback function to handle response.
     * @return Call
     * @throws ApiException
     */
    public Call getMultiSigTransactionListAsync(String address, ApiCallback<MultisigTransactions> callback) throws ApiException {
        return getMultiSigTransactionListAsync(address, new WalletQueryOptions(), callback);
    }

    /**
     * Get pending transaction list.
     * @param address The sender address to retrieve pending transaction list.
     * @param options Filter required when retrieving data. `cursor`, `to-timestamp`, `from-timestamp`
     * @return MultisigTransactions
     * @throws ApiException
     */
    public MultisigTransactions getMultiSigTransactionList(String address, WalletQueryOptions options) throws ApiException {
        return getMultisigTransactionManagementApi().retrieveMultisigTransactions(chainId, address, options.getSize(), options.getCursor(), options.getToTimestamp(), options.getFromTimestamp());
    }

    /**
     * Get pending transaction list asynchronously.
     * @param address The sender address to retrieve pending transaction list.
     * @param options Filter required when retrieving data. `cursor`, `to-timestamp`, `from-timestamp`
     * @param callback The callback function to handle response.
     * @return Call
     * @throws ApiException
     */
    public Call getMultiSigTransactionListAsync(String address, WalletQueryOptions options, ApiCallback<MultisigTransactions> callback) throws ApiException {
        return getMultisigTransactionManagementApi().retrieveMultisigTransactionsAsync(chainId, address, options.getSize(), options.getCursor(), options.getToTimestamp(), options.getFromTimestamp(), callback);
    }

    /**
     * Sign a pending transaction.
     * @param address The singer address to sign.
     * @param transactionId The pending transaction id.
     * @return MultisigTransactionStatus
     * @throws ApiException
     */
    public MultisigTransactionStatus signMultiSigTransaction(String address, String transactionId) throws ApiException {
        return getMultisigTransactionManagementApi().signPendingTransaction(chainId, address, transactionId);
    }

    /**
     * Sign a pending transaction asynchronously.
     * @param address The singer address to sign.
     * @param transactionId The pending transaction id.
     * @param callback The callback function to handle response.
     * @return Call
     * @throws ApiException
     */
    public Call signMultiSigTransactionAsync(String address, String transactionId, ApiCallback<MultisigTransactionStatus> callback) throws ApiException {
        return getMultisigTransactionManagementApi().signPendingTransactionAsync(chainId, address, transactionId, callback);
    }

    /**
     * Append a signature to pending transaction.
     * @param transactionId The transaction id to append signature.
     * @param request The SignPendingTransactionBySigRequest instance to send a request.
     * @return MultisigTransactionStatus
     * @throws ApiException
     */
    public MultisigTransactionStatus appendSignatures(String transactionId, SignPendingTransactionBySigRequest request) throws ApiException {
        return getMultisigTransactionManagementApi().signPendingTransactionBySig(chainId, transactionId, request);
    }

    /**
     * Append a signature to pending transaction asynchronously.
     * @param transactionId The transaction id to append signature.
     * @param request The SignPendingTransactionBySigRequest instance to send a request.
     * @param callback The callback function to handle response.
     * @return Call
     * @throws ApiException
     */
    public Call appendSignaturesAsync(String transactionId, SignPendingTransactionBySigRequest request, ApiCallback<MultisigTransactionStatus> callback) throws ApiException {
        return getMultisigTransactionManagementApi().signPendingTransactionBySigAsync(chainId, transactionId, request, callback);
    }

    /**
     * Return the number of accounts in KAS.
     * GET /v2/stat/count
     * @return AccountCountByAccountID
     * @throws ApiException
     */
    public AccountCountByAccountID getAccountCount() throws ApiException {
        return getStatisticsApi().getAccountCountByAccountID(getChainId());
    }

    /**
     * Return the number of accounts in KAS asynchronously.
     * GET /v2/stat/count
     * @param callback The callback function to handle response.
     * @return Call
     * @throws ApiException
     */
    public Call getAccountCountAsync(ApiCallback<AccountCountByAccountID> callback) throws ApiException {
        return getStatisticsApi().getAccountCountByAccountIDAsync(getChainId(), callback);
    }

    /**
     * Return the number of accounts by passed as KRN in KAS.
     * It use default krn.
     * GET /v2/stat/count/krn
     * @return AccountCountByKRN
     * @throws ApiException
     */
    public AccountCountByKRN getAccountCountByKRN() throws ApiException {
        return getAccountCountByKRN(null);
    }

    /**
     * Return the number of accounts by passed as KRN in KAS asynchronously.
     * It use default krn.
     * GET /v2/stat/count/krn
     * @param callback The callback function to handle response.
     * @return AccountCountByKRN
     * @throws ApiException
     */
    public Call getAccountCountByKRNAsync(ApiCallback<AccountCountByKRN> callback) throws ApiException {
        return getAccountCountByKRNAsync(null, callback);
    }

    /**
     * Return the number of accounts by passed as KRN in KAS.
     * GET /v2/stat/count/krn
     * @param krn The krn string to search
     * @return AccountCountByKRN
     * @throws ApiException
     */
    public AccountCountByKRN getAccountCountByKRN(String krn) throws ApiException {
        return getStatisticsApi().getAccountCountByKRN(getChainId(), krn);
    }

    /**
     * Return the number of accounts by passed as KRN in KAS asynchronously.
     * GET /v2/stat/count/krn
     * @param krn The krn string to search
     * @param callback The callback function to handle response.
     * @return AccountCountByKRN
     * @throws ApiException
     */
    public Call getAccountCountByKRNAsync(String krn, ApiCallback<AccountCountByKRN> callback) throws ApiException {
        return getStatisticsApi().getAccountCountByKRNAsync(getChainId(), krn, callback);
    }


    /**
     * Getter function for accountApi.
      * @return AccountApi
     */
    public AccountApi getAccountApi() {
        return accountApi;
    }

    /**
     * Getter function for basicTransactionApi
     * @return BasicTransactionApi
     */
    public BasicTransactionApi getBasicTransactionApi() {
        return basicTransactionApi;
    }

    /**
     * Getter function for feeDelegatedTransactionPaidByKasApi
     * @return FeeDelegatedTransactionPaidByKasApi
     */
    public FeeDelegatedTransactionPaidByKasApi getFeeDelegatedTransactionPaidByKasApi() {
        return feeDelegatedTransactionPaidByKasApi;
    }

    /**
     * Getter function for feeDelegatedTransactionPaidByUserApi
     * @return FeeDelegatedTransactionPaidByUserApi
     */
    public FeeDelegatedTransactionPaidByUserApi getFeeDelegatedTransactionPaidByUserApi() {
        return feeDelegatedTransactionPaidByUserApi;
    }

    /**
     * Getter function for multisigTransactionManagementApi
     * @return MultisigTransactionManagementApi
     */
    public MultisigTransactionManagementApi getMultisigTransactionManagementApi() {
        return multisigTransactionManagementApi;
    }

    /**
     * Getter function for statisticsApi
     * @return StatisticsApi
     */
    public StatisticsApi getStatisticsApi() {
        return statisticsApi;
    }

    /**
     * Getter function for chainId
     * @return String
     */
    public String getChainId() {
        return chainId;
    }

    /**
     * Setter function for accountApi
     * @param accountApi Account API rest client object.
     */
    public void setAccountApi(AccountApi accountApi) {
        this.accountApi = accountApi;
    }

    /**
     * Setter function for basicTransactionApi
     * @param basicTransactionApi Basic transaction API rest client object.
     */
    public void setBasicTransactionApi(BasicTransactionApi basicTransactionApi) {
        this.basicTransactionApi = basicTransactionApi;
    }

    /**
     * Setter function for feeDelegatedTransactionPaidByKasApi
     * @param feeDelegatedTransactionPaidByKasApi Fee delegated transaction(fee paid by KAS) API rest client object.
     */
    public void setFeeDelegatedTransactionPaidByKasApi(FeeDelegatedTransactionPaidByKasApi feeDelegatedTransactionPaidByKasApi) {
        this.feeDelegatedTransactionPaidByKasApi = feeDelegatedTransactionPaidByKasApi;
    }

    /**
     * Setter function for feeDelegatedTransactionPaidByUserApi
     * @param feeDelegatedTransactionPaidByUserApi Fee delegated transaction(fee paid by user) API rest client object.
     */
    public void setFeeDelegatedTransactionPaidByUserApi(FeeDelegatedTransactionPaidByUserApi feeDelegatedTransactionPaidByUserApi) {
        this.feeDelegatedTransactionPaidByUserApi = feeDelegatedTransactionPaidByUserApi;
    }

    /**
     * Setter function for multisigTransactionManagementApi
     * @param multisigTransactionManagementApi Multiple signature transaction management API rest client object.
     */
    public void setMultisigTransactionManagementApi(MultisigTransactionManagementApi multisigTransactionManagementApi) {
        this.multisigTransactionManagementApi = multisigTransactionManagementApi;
    }

    /**
     * Setter function for statisticsApi
     * @param statisticsApi statistics API rest client object.
     */
    public void setStatisticsApi(StatisticsApi statisticsApi) {
        this.statisticsApi = statisticsApi;
    }

    /**
     * Setter function for chainId
     * @param chainId Klaytn network id.
     */
    public void setChainId(String chainId) {
        this.chainId = chainId;
    }

    private List<MultisigKey> convertMultiSigKey(AccountKeyWeightedMultiSig weightedMultiSig) {
        return weightedMultiSig.getWeightedPublicKeys().stream()
                .map(weightedPublicKey -> {
                    MultisigKey multisigKey = new MultisigKey();
                    multisigKey.setPublicKey(weightedPublicKey.getPublicKey());
                    multisigKey.setWeight(weightedPublicKey.getWeight().longValue());

                    return multisigKey;
                }).collect(Collectors.toList());
    }

    AccountUpdateKey makeUncompressedKeyFormat(AccountUpdateKey updateKey) {
        if(updateKey instanceof EmptyUpdateKeyType) {
            return updateKey;
        }

        if(updateKey instanceof PubkeyUpdateKeyType) {
            ((KeyTypePublic)updateKey).setKey(KASUtils.addUncompressedKeyPrefix(((KeyTypePublic) updateKey).getKey()));
        } else if(updateKey instanceof MultisigUpdateKeyType) {
            MultisigUpdateKey keys = ((KeyTypeMultiSig) updateKey).getKey();
            keys.getWeightedKeys().stream().forEach(weightKey -> {
                weightKey.setPublicKey(KASUtils.addUncompressedKeyPrefix(weightKey.getPublicKey()));
            });
        } else if(updateKey instanceof RoleBasedUpdateKeyType) {
            List<AccountUpdateKey> roleKeyList = ((KeyTypeRoleBased) updateKey).getKey();
            roleKeyList.stream().forEach(roleKey -> makeUncompressedKeyFormat(roleKey));
        } else {
            throw new IllegalArgumentException("Not supported update Key type.");
        }

        return updateKey;
    }
}
