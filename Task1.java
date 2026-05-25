package Assignment;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Task1 {

    public List<LoanAccount> getOverdueLoans(List<LoanAccount> accounts) {

        // FIX: Initialize result list to avoid NullPointerException
        List<LoanAccount> result = new ArrayList<>();

        // FIX: Added null check for accounts list
        if (accounts == null) {
            return result;
        }

        for (LoanAccount account : accounts) {

            // FIX: Added null check for dueDate before calling before()
            if (account.getDueDate() != null
                    && account.getDueDate().before(new Date())) {

                // FIX: Only overdue accounts with positive outstanding balance should be returned
                if (account.getOutstandingBalance() > 0) {
                    result.add(account);
                }
            }
        }

        return result;
    }
}

class LoanAccount {

    private Date dueDate;
    private double outstandingBalance;
    private String accountId;

    public Date getDueDate() {
        return dueDate;
    }

    public double getOutstandingBalance() {
        return outstandingBalance;
    }

    public String getAccountId() {
        return accountId;
    }
}
