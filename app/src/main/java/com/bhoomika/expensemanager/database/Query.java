package com.bhoomika.expensemanager.database;

import com.bhoomika.expensemanager.utils.Constant;
import com.raizlabs.android.dbflow.sql.language.SQLite;

import java.util.Date;
import java.util.List;

import static com.bhoomika.expensemanager.database.TransactionTable_Table.amountType;


/**
 * Created by bhoomika on 30/1/17.
 */


public class Query {


    public static boolean incomeTransaction(String amount, int amountType, String incomeResource) {

        TransactionTable transactionTable = new TransactionTable();
        transactionTable.setAmount(Long.parseLong((amount)));
        transactionTable.setAmountType(amountType);
        transactionTable.setDescription(incomeResource);
        transactionTable.setSign(Constant.INCOME);
        transactionTable.setDate(new Date());
        transactionTable.save();
        return true;


    }


    public static boolean expensesTransaction(String amount, int amountType, String expensDescription) {

        TransactionTable transactionTable = new TransactionTable();
        transactionTable.setAmount(Long.parseLong((amount)));
        transactionTable.setAmountType(amountType);
        transactionTable.setDescription(expensDescription);
        transactionTable.setSign(Constant.EXPENSES);
        transactionTable.setDate(new Date());
        transactionTable.save();
        return true;
    }


    public static float totalAmountCash() {
        float totalAmount = 0;

        List<TransactionTable> getAllData = SQLite.select(TransactionTable_Table.amount, amountType, TransactionTable_Table.sign)
                .from(TransactionTable.class)
                .where(amountType.eq(Constant.CASH))
                .queryList();


        for (int i = 0; i < getAllData.size(); i++) {

            String sign = getAllData.get(i).getSign();
            if (Constant.INCOME.equalsIgnoreCase(sign))
                totalAmount = totalAmount + getAllData.get(i).getAmount();
            else {
                totalAmount = (totalAmount) - (getAllData.get(i).getAmount());
            }
        }
        return totalAmount;

    }


    public static float totalAmountCard() {

        float totalAmount = 0;

        List<TransactionTable> getAllData = SQLite.select()
                .from(TransactionTable.class)
                .where(amountType.eq(Constant.CARD))
                .queryList();


        for (int i = 0; i < getAllData.size(); i++) {

            String sign = getAllData.get(i).getSign();

            if (Constant.INCOME.equalsIgnoreCase(sign))

                totalAmount = totalAmount + getAllData.get(i).getAmount();
            else {
                totalAmount = (totalAmount) - (getAllData.get(i).getAmount());
            }
        }
        return totalAmount;

    }

    public static List<TransactionTable> getAllCashData() {

        return SQLite.select()
                .from(TransactionTable.class)
                .where(amountType.eq(Constant.CASH))
                .queryList();

    }

    public static int totalCardRecord()
    {
        return (int) SQLite.select().from(TransactionTable.class).where(amountType.eq(Constant.CARD)).count();
    }
    public static int totalCashRecord()
    {
        return (int) SQLite.select().from(TransactionTable.class).where(amountType.eq(Constant.CASH)).count();
    }
    public static List<TransactionTable> getAllBankData() {
        return SQLite.select()
                .from(TransactionTable.class)
                .where(amountType.eq(Constant.CARD))

                .queryList();

    }


    public static boolean futureIncomeTransaction(String amount, int amountType, String incomeResource, String date) {
        FTransaction futureTransaction = new FTransaction();
        futureTransaction.setAmount(Long.parseLong((amount)));
        futureTransaction.setAmounttype(amountType);
        futureTransaction.setDescription(incomeResource);
        futureTransaction.setSign(Constant.INCOME);
        futureTransaction.setF_date(date);
        futureTransaction.save();
        return true;


    }


    public static boolean futureExpensesTransaction(String amount, int amountType, String incomeResource, String date) {
        FTransaction futureTransaction = new FTransaction();
        futureTransaction.setAmount(Long.parseLong((amount)));
        futureTransaction.setAmounttype(amountType);
        futureTransaction.setDescription(incomeResource);
        futureTransaction.setSign(Constant.EXPENSES);
        futureTransaction.setF_date(date);
        futureTransaction.save();
        return true;


    }
    public static int totalFCardRecord()
    {
        return (int) SQLite.select().from(FTransaction.class).where(amountType.eq(Constant.CARD)).count();
    }
    public static int totalFCashRecord()
    {
        return (int) SQLite.select().from(FTransaction.class).where(amountType.eq(Constant.CASH)).count();
    }

    public static List<FTransaction> getAllFutureCashData() {

        return SQLite.select()
                .from(FTransaction.class)
                .where(FTransaction_Table.amounttype.eq(Constant.CASH))
                .queryList();

    }

    public static List<FTransaction> getAllFutureBankData() {

        return SQLite.select()
                .from(FTransaction.class)
                .where(FTransaction_Table.amounttype.eq(Constant.CARD))
                .queryList();

    }

    public static float totalFutureAmountCash() {
        float totalAmount = 0;

        List<FTransaction> getAllData = SQLite.select(FTransaction_Table.amount, FTransaction_Table.amounttype, FTransaction_Table.sign)
                .from(FTransaction.class)
                .where(FTransaction_Table.amounttype.eq(Constant.CASH))
                .queryList();


        for (int i = 0; i < getAllData.size(); i++) {

            String sign = getAllData.get(i).getSign();
            if (Constant.INCOME.equalsIgnoreCase(sign))
                totalAmount = totalAmount + getAllData.get(i).getAmount();
            else {
                totalAmount = (totalAmount) - (getAllData.get(i).getAmount());
            }
        }
        return totalAmount;

    }

    public static float totalFutureAmountBank() {
        float totalAmount = 0;

        List<FTransaction> getAllData = SQLite.select(FTransaction_Table.amount, FTransaction_Table.amounttype, FTransaction_Table.sign)
                .from(FTransaction.class)
                .where(FTransaction_Table.amounttype.eq(Constant.CARD))
                .queryList();


        for (int i = 0; i < getAllData.size(); i++) {

            String sign = getAllData.get(i).getSign();
            if (Constant.INCOME.equalsIgnoreCase(sign))
                totalAmount = totalAmount + getAllData.get(i).getAmount();
            else {
                totalAmount = (totalAmount) - (getAllData.get(i).getAmount());
            }
        }
        return totalAmount;

    }

}
