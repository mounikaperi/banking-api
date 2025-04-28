import com.bankingapi.accounts.dto.BankAccountDetailsDTO;
import org.springframework.stereotype.Component;

@Component
public class AccountAdapter {

    public BankAccountDetailsDTO fromAxis(AxisTransaction txn) {
        TransactionResponse response = new TransactionResponse();
        response.setTransactionId(String.valueOf(txn.getTxnId()));
        response.setAccountNumber(txn.getAccNo());
        response.setAmount(txn.getTxnAmount());
        response.setDescription(txn.getTxnDesc());
        response.setTransactionDate(txn.getTxnTime());
        response.setBankName("AXIS");
        return response;
    }

    public TransactionResponse fromIcici(IciciTransaction txn) {
        TransactionResponse response = new TransactionResponse();
        response.setTransactionId(String.valueOf(txn.getId()));
        response.setAccountNumber(txn.getAccountNumber());
        response.setAmount(txn.getAmount().doubleValue());
        response.setDescription(txn.getNarration());
        response.setTransactionDate(txn.getTransactionTimestamp().toLocalDateTime());
        response.setBankName("ICICI");
        return response;
    }

    public TransactionResponse fromHdfc(HdfcTransaction txn) {
        TransactionResponse response = new TransactionResponse();
        response.setTransactionId(txn.getTransactionId());
        response.setAccountNumber(txn.getCustAccount());
        response.setAmount(txn.getDebitCreditAmount());
        response.setDescription(txn.getRemarks());
        response.setTransactionDate(
                txn.getTransactionDate().toInstant()
                        .atZone(ZoneId.systemDefault())
                        .toLocalDateTime()
        );
        response.setBankName("HDFC");
        return response;
    }
}
