package com.project.expenseTracker.incomeandexpenses;

public class IncomeAndExpenses {
    private int amount;
    private String remark;
    private String category;

    public IncomeAndExpenses() {
        this.amount = 0;
        this.remark = "";
        this.category = "";
    }

    public IncomeAndExpenses(int amount, String remark, String category) {
        this.amount = amount;
        this.remark = remark;
        this.category = category;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public int getAmount() {
        return amount;
    }

    public String getRemark() {
        return remark;
    }

    public String getCategory() {
        return category;
    }
}
